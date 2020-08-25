package jp.playing.table.poker

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.tableplay.poker.R

class UserAdapter(context: Context): BaseAdapter() {
    private val mLayoutInflater: LayoutInflater
    var userList = mutableListOf<User>()

    init {
        this.mLayoutInflater = LayoutInflater.from(context)
    }

    override fun getCount(): Int {
        return userList.size
    }

    override fun getItem(position: Int): Any {
        return userList[position]
    }

    override fun getItemId(position: Int): Long {
        return userList[position].id.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView = convertView

        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.list_add_member, parent, false)
        }

        val nameText = convertView!!.findViewById<View>(R.id.addMemberListNameText) as TextView
        nameText.text = userList[position].name

        return convertView
    }

    fun setUserArrayList(userArrayList: ArrayList<User>) {
        userList = userArrayList
    }

}