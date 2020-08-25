package com.example.poker

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.poker.R
import com.example.poker.TitleActivity
import io.realm.Realm
import io.realm.RealmChangeListener
import jp.playing.table.poker.Game
import jp.playing.table.poker.GameAdapter

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_add_member_list.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var mGameAdapter: GameAdapter

    private lateinit var mRealm: Realm

    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reloadListView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        //Realmの設定
        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)

        mGameAdapter = GameAdapter(this@MainActivity)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
            //新規作成画面に移動する
            val intent = Intent(this@MainActivity, TitleActivity::class.java)
            startActivity(intent)
        }

        mainListView.setOnItemClickListener { parent, view, position, id ->
            //データ詳細画面に移動する
            val game = parent.adapter.getItem(position) as Game

            val intent = Intent(this@MainActivity, DataDetailActivity::class.java)
            intent.putExtra("dataId", game.id)
            startActivity(intent)

        }

        mainListView.setOnItemLongClickListener { parent, view, position, id ->
            //該当記録を削除する
            val game = parent.adapter.getItem(position) as Game

            val results = mRealm.where(Game::class.java).equalTo("id", game.id).findAll()

            mRealm.beginTransaction()
            results.deleteAllFromRealm()
            mRealm.commitTransaction()

            reloadListView()

            true
        }

        reloadListView()
    }

    private fun reloadListView() {
        val gameRealmResults = mRealm.where(Game::class.java).findAll()

        mGameAdapter.gameList = mRealm.copyFromRealm(gameRealmResults)

        mainListView.adapter = mGameAdapter

        mGameAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }
}
