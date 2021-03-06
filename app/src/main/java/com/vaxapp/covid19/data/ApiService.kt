package com.vaxapp.covid19.data

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import com.vaxapp.covid19.BuildConfig
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("resource/jj6z-iyrp.json")
    suspend fun fetchCases(@Query("municipidescripcio") town: String): List<Response>

    @GET("resource/jj6z-iyrp.json")
    suspend fun fetchAllCases(): List<Response>

    companion object {

        private val REWRITE_RESPONSE_INTERCEPTOR = Interceptor { chain ->
            val originalResponse: okhttp3.Response = if (BuildConfig.API_TOKEN.isNotEmpty()) {
                chain.proceed(chain.request()).newBuilder()
                    .header("X-App-Token", BuildConfig.API_TOKEN)
                    .build()
            } else {
                chain.proceed(chain.request())
            }
            val cacheControl = originalResponse.header("Cache-Control")
            if (cacheControl == null || cacheControl.contains("no-store") || cacheControl.contains("no-cache") ||
                cacheControl.contains("must-revalidate") || cacheControl.contains("max-age=0")
            ) {
                originalResponse.newBuilder()
                    .removeHeader("Pragma") // TODO: is this needed here?
                    .header("Cache-Control", "public, max-age=" + 5000)
                    .build()
            } else {
                originalResponse
            }
        }

        fun create(context: Context): ApiService {

            val interceptor = HttpLoggingInterceptor()
            if (BuildConfig.DEBUG) {
                interceptor.level = HttpLoggingInterceptor.Level.BODY
            }
            // val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val cacheSize = (5 * 1024 * 1024).toLong()
            val myCache = Cache(context.cacheDir, cacheSize)
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                // Specify the cache we created earlier.
                .cache(myCache)
                // Add an Interceptor to the OkHttpClient.
                .addInterceptor { chain ->

                    /*
                    *  Leveraging the advantage of using Kotlin,
                    *  we initialize the request and change its header depending on whether
                    *  the device is connected to Internet or not.
                    */
                    val request = when {
                        hasNetwork(context) -> createRecentCacheRequest(chain.request())
                        else -> createOlderCacheRequest(chain.request())
                    }

                    // Add the modified request to the chain.
                    chain.proceed(request)
                }
                .addNetworkInterceptor(REWRITE_RESPONSE_INTERCEPTOR)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://analisi.transparenciacatalunya.cat")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retrofit.create(ApiService::class.java)
        }

        /*
         *  If there is no Internet, get the cache that was stored 7 days ago.
         *  If the cache is older than 7 days, then discard it,
         *  and indicate an error in fetching the response.
         *  The 'max-stale' attribute is responsible for this behavior.
         *  The 'only-if-cached' attribute indicates to not retrieve new data; fetch the cache only instead.
         */
        private fun createOlderCacheRequest(request: Request): Request {
            Log.d("ApiService", "createOlderCacheRequest")
            return request.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, only-if-cached, max-stale=" + 60 * 60 * 24 * 7)
                .build()
        }

        /**
         * If there is Internet, get the cache that was stored 5 seconds ago.
         *  If the cache is older than 5 seconds, then discard it,
         *  and indicate an error in fetching the response.
         *  The 'max-age' attribute is responsible for this behavior.
         */
        private fun createRecentCacheRequest(request: Request): Request {
            Log.d("ApiService", "createRecentCacheRequest")
            return request.newBuilder()
                .removeHeader("Pragma")
                .header("Cache-Control", "public, max-age=" + 5)
                .build()
        }

        private fun hasNetwork(context: Context): Boolean {
            var isConnected = false
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
            if (activeNetwork != null && activeNetwork.isConnected) {
                isConnected = true
            }
            return isConnected
        }
    }
}
