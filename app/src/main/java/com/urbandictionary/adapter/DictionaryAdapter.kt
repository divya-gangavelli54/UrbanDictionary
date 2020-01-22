package com.urbandictionary.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.urbandictionary.R
import com.urbandictionary.entities.Information
import com.urbandictionary.util.Common

class DictionaryAdapter(
    private val foContext: Context,
    private val mInformationList: List<Information>
) : RecyclerView.Adapter<DictionaryAdapter.ViewHolder>() {

    override fun onBindViewHolder(foViewHolder: ViewHolder, fiPosition: Int) {
        val info = mInformationList[fiPosition]
        foViewHolder.getItem(info)

        foViewHolder.loTvTitle.text = info.title

        if (info.description.isNullOrEmpty()) {
            foViewHolder.loTvDescription.visibility = View.GONE
        } else {
            foViewHolder.loTvDescription.visibility = View.VISIBLE
            foViewHolder.loTvDescription.text = info.description!!.replace("[", "").replace("]", "")
        }

        if (info.example.isNullOrEmpty()) {
            foViewHolder.loTvExample.visibility = View.GONE
        } else {
            foViewHolder.loTvExample.visibility = View.VISIBLE
            foViewHolder.loTvExample.text = info.example!!.replace("[", "").replace("]", "")
        }

        if (info.author.isNullOrEmpty().not()) {
            foViewHolder.loTvAuthor.text = foContext.getString(R.string.TV_AUTHOR, info.author)
        }

        if (info.date.isNullOrEmpty().not()) {
            foViewHolder.loTvDate.text = Common.getRelativeTimeSpanString(info.date)
        }

        foViewHolder.loTvThumbsUp.text = info.thumbsUp.toString()
        foViewHolder.loTvThumbsDown.text = info.thumbsDown.toString()
    }

    override fun onCreateViewHolder(foViewGroup: ViewGroup, fiPosition: Int): ViewHolder {
        val loView = LayoutInflater.from(foViewGroup.context).inflate(
            R.layout.item_dictionary, foViewGroup, false
        )

        return ViewHolder(loView)
    }

    override fun getItemCount(): Int {
        return mInformationList.size
    }

    class ViewHolder(foView: View) : RecyclerView.ViewHolder(foView) {

        private lateinit var loInformation: Information

        var loTvTitle: TextView = foView.findViewById(R.id.tvTitle)
        var loTvDescription: TextView = foView.findViewById(R.id.tvDescription)
        var loTvExample: TextView = foView.findViewById(R.id.tvExample)
        var loTvAuthor: TextView = foView.findViewById(R.id.tvAuthor)
        var loTvDate: TextView = foView.findViewById(R.id.tvDate)
        var loTvThumbsUp: TextView = foView.findViewById(R.id.tvThumbsUp)
        var loTvThumbsDown: TextView = foView.findViewById(R.id.tvThumbsDown)

        fun getItem(foInformation: Information) {
            loInformation = foInformation
        }
    }
}