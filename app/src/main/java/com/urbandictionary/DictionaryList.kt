package com.urbandictionary

import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.urbandictionary.adapter.DictionaryAdapter
import com.urbandictionary.entities.Dictionary
import com.urbandictionary.entities.Information
import com.urbandictionary.util.ApiClient
import com.urbandictionary.util.ApiInterface
import com.urbandictionary.util.Common
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class DictionaryList : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.dictionary_list)


        var term = ""

        if (intent != null && intent.hasExtra("term")) {
            term = intent.extras?.get("term").toString()
        }

        setupTitle(term.toUpperCase(Locale.getDefault()))

        if (Common.isInternetConnected(this)) {
            getList(term)
        } else {
            Common.showErrorDialog(this, getString(R.string.CONNECTION_ERROR))
        }
    }

    /**
     * API call to retrieve list and populate with RecyclerView on success
     */
    private fun getList(term: String) {
        showProgressDialog()

        val apiInterface = ApiClient.getClient()?.create(ApiInterface::class.java)

        val call = apiInterface?.getList(term)
        call?.enqueue(object : Callback<Dictionary> {
            override fun onResponse(call: Call<Dictionary>, response: Response<Dictionary>) {
                hideProgressDialog()
                val list = response.body()
                if (list != null && list.dictionary!!.isNotEmpty()) {
                    Log.i("TAG", "List: " + list.dictionary)
                    list.dictionary?.let { setupData(it) }
                } else {
                    Common.showErrorDialog(this@DictionaryList, getString(R.string.NO_DATA))
                }
            }

            override fun onFailure(call: Call<Dictionary>, t: Throwable) {
                hideProgressDialog()
                Log.i("TAG", "Error: $t")
                Common.showErrorDialog(this@DictionaryList, getString(R.string.SERVER_ERROR))
            }
        })
    }

    /**
     * @param fInformationList List retrieved from API
     */
    fun setupData(fInformationList: List<Information>) {
        val rvDictionary = findViewById<RecyclerView>(R.id.rvDictionaryList)
        val manager = LinearLayoutManager(this)
        manager.orientation = RecyclerView.VERTICAL
        rvDictionary.layoutManager = manager
        rvDictionary.adapter = DictionaryAdapter(this, fInformationList)
    }
}
