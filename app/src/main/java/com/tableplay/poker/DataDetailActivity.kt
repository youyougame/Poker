package com.tableplay.poker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import io.realm.Realm
import io.realm.RealmChangeListener
import jp.playing.table.poker.DataDetailAdapter
import jp.playing.table.poker.Game
import jp.playing.table.poker.Turn
import kotlinx.android.synthetic.main.content_data_detail.*

class DataDetailActivity : AppCompatActivity() {

    private lateinit var mRealm: Realm

    private lateinit var mDataDetailAdapter: DataDetailAdapter

    private var mGame: Game? = null

    private var mTurn: Turn? = null

    private var dataId = 0

    private val mRealmListener = object : RealmChangeListener<Realm> {
        override fun onChange(t: Realm) {
            reloadListView()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_detail)

        mRealm = Realm.getDefaultInstance()
        mRealm.addChangeListener(mRealmListener)


        mDataDetailAdapter = DataDetailAdapter(this@DataDetailActivity)

        val intent = intent
        dataId = intent.getIntExtra("dataId", 0)

        val gameData = mRealm.where(Game::class.java).equalTo("id", dataId).findFirst()
        val gameTitle = gameData!!.title
        val gameDateString = gameData!!.dateString

        supportActionBar?.title = gameTitle + "：" + gameDateString

        reloadListView()

    }

    private fun reloadListView() {
        val turnRealmResults = mRealm.where(Turn::class.java).equalTo("game_id", dataId).findAll()

        mDataDetailAdapter.turnList = mRealm.copyFromRealm(turnRealmResults)

        dataDetailListView.adapter = mDataDetailAdapter

        mDataDetailAdapter.notifyDataSetChanged()
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }
}
