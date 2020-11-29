package com.vaxapp.covid19.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.inputmethod.EditorInfo
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.vaxapp.covid19.R
import com.vaxapp.covid19.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {

    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.view = this
        editText.setOnEditorActionListener(object : TextView.OnEditorActionListener {
            override fun onEditorAction(v: TextView?, actionId: Int, event: KeyEvent?): Boolean {
                if (actionId == EditorInfo.IME_ACTION_DONE && v != null) {
                    val input = v.text.toString()
                    presenter.loadCases(input)
                    return true
                }
                return false
            }
        })
    }

    override fun showError(error: Throwable) {
        toast(getString(R.string.error_loading_toast))
        error(error)
    }

    private fun toast(string: String) {
        Toast.makeText(this, string, Toast.LENGTH_LONG).show()
    }

    override fun showLoading() {
        // TODO("not implemented")
    }

    override fun hideLoading() {
        // TODO("not implemented")
    }

    // TODO: refactor strings
    override fun display(response: ViewResponse, town: String) {
        Log.d("MainActivity", "channel $response")
        descriptionTv.text = "$town Today"
        field1Content.text = "Suspicious cases: ${response.casesNumberSuspicious}"
        field2Content.text = "Confirmed cases: ${response.casesNumberConfirmed}"
        totalTv.text =
            "Total cases ${response.casesNumberSuspicious + response.casesNumberConfirmed}"
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
    }
}
