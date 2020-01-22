package com.urbandictionary.util

import android.content.Context
import android.net.ConnectivityManager
import android.text.format.DateUtils
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.urbandictionary.R
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class Common {
    companion object {
        /**
         * @param context Application context
         * @return If internet connection is available or not
         */
        fun isInternetConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            val activeNetwork = cm.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        }

        /**
         * @param context Application context
         * @param message Error message
         */
        fun showErrorDialog(context: Context, message: String) {
            AlertDialog.Builder(context).setMessage(message)
                .setNeutralButton(
                    context.getString(R.string.ALERT_BUTTON_CLOSE)
                ) { _, _ -> (context as AppCompatActivity).finish() }.show()
        }

        /**
         *
         * @param date date string received from API
         * @return Converted relative time span date string
         */
        fun getRelativeTimeSpanString(date: String?): String {
            val sdf = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.ENGLISH)
            sdf.timeZone = TimeZone.getTimeZone("GMT")
            var time: Long = 0
            try {
                time = sdf.parse(date).time
            } catch (e: ParseException) {
                e.printStackTrace()
            }

            val now = System.currentTimeMillis()

            return DateUtils.getRelativeTimeSpanString(time, now, DateUtils.MINUTE_IN_MILLIS)
                .toString()
        }
    }
}