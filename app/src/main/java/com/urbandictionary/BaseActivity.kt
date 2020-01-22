package com.urbandictionary

import android.app.ProgressDialog
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    internal var mProgressDialog: ProgressDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupProgressDialog()
    }

    /**
     * Setup title and back navigation arrow on Toolbar
     *
     * @param title title to show on toolbar
     */
    fun setupTitle(title: String) {
        if (supportActionBar != null) {
            supportActionBar!!.title = title
            supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        }
    }

    /**
     * Initialize ProgressDialog
     */
    fun setupProgressDialog() {
        mProgressDialog = ProgressDialog(this)
        mProgressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        mProgressDialog!!.setMessage("Please wait...")
        mProgressDialog!!.setCancelable(false)
    }

    /**
     * Show ProgressDialog
     */
    fun showProgressDialog() {
        if (mProgressDialog != null && !mProgressDialog!!.isShowing) {
            mProgressDialog!!.show()
        }
    }

    /**
     * Hide ProgressDialog
     */
    fun hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.dismiss()
        }
    }

    /**
     * Toolbar item click event
     *
     * @param item selected menu item
     * @return
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return true
    }
}
