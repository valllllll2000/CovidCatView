package com.vaxapp.thingspeakviewer

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vaxapp.thingspeakviewer.data.ApiService
import com.vaxapp.thingspeakviewer.data.Channel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity() {

    val service by lazy {
        ApiService.create()
    }
    var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadData();
    }

    private fun loadData() {
        disposable =
                service.fetchFields("https://api.thingspeak.com/channels/298096/feeds.json?results=1")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                { result -> showResult(result.channel) },
                                { _ -> showError() }
                        )
    }

    private fun showError() {
        //make more Kotlin
        Toast.makeText(this, "Error loading channel data", Toast.LENGTH_LONG).show();
    }

    private fun showResult(channel: Channel) {

        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
