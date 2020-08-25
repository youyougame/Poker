package jp.playing.table.poker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.poker.R

class GameAdapter(context: Context): BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    var gameList = mutableListOf<Game>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return gameList.size
    }

    override fun getItem(position: Int): Any {
        return gameList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val convertView = convertView ?: mLayoutInflater.inflate(R.layout.list_main, null)

        val titleText = convertView!!.findViewById<View>(R.id.mainTitleText) as TextView
        val dateText = convertView!!.findViewById<View>(R.id.mainDateText) as TextView

        titleText.text = gameList[position].title
        dateText.text = gameList[position].dateString

        return convertView
    }

}