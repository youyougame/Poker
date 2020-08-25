package com.tableplay.poker

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.realm.Realm
import io.realm.RealmChangeListener
import jp.playing.table.poker.User
import jp.playing.table.poker.UserAdapter

import kotlinx.android.synthetic.main.activity_add_member_list.*
import kotlinx.android.synthetic.main.content_add_member_list.*

class AddMemberListActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm

    private lateinit var mUserAdapter: UserAdapter

    private val  mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reloadListView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member_list)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "プレイヤー登録"

        fab.setOnClickListener { view ->
            val intent = Intent(this@AddMemberListActivity, AddMemberActivity::class.java)
            startActivity(intent)
        }

        //Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        //ListViewの設定
        mUserAdapter = UserAdapter(this@AddMemberListActivity)

        //ListViewをタップした時の処理
        addMemberListView.setOnItemLongClickListener { parent, view, position, id ->
            //ユーザーを削除
            val user = parent.adapter.getItem(position) as User

            val results = mRealm.where(User::class.java).equalTo("id", user.id).findAll()

            mRealm.beginTransaction()
            results.deleteAllFromRealm()
            mRealm.commitTransaction()

            reloadListView()

            true
        }

        reloadListView()

    }

    private fun reloadListView() {
        val userRealmResults = mRealm.where(User::class.java).findAll()

        mUserAdapter.userList = mRealm.copyFromRealm(userRealmResults)

        addMemberListView.adapter = mUserAdapter

        mUserAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }

}
