package com.vaxapp.covid19.view.main

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.vaxapp.covid19.R
import com.vaxapp.covid19.view.SettingsActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar.*
import org.jetbrains.anko.AnkoLogger
import org.jetbrains.anko.error
import org.jetbrains.anko.info
import org.jetbrains.anko.toast
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), AnkoLogger, MainView {

    private val presenter: MainPresenter by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        presenter.view = this
        presenter.onViewReady()
    }

    override fun showError(error: Throwable) {
        toast(getString(R.string.error_loading_toast))
        error(error)
    }

    override fun showLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun display(response: ViewResponse) {
        info("channel $response")
        descriptionTv.text = "Calella Today"
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
