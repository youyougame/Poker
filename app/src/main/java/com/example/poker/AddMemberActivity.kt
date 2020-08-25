package com.example.poker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.poker.R
import io.realm.Realm
import jp.playing.table.poker.User
import kotlinx.android.synthetic.main.activity_add_member.*

class AddMemberActivity : AppCompatActivity() {

    private var mName = ""

    private var mUser: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_member)

        supportActionBar?.title = "プレイヤー新規登録"

        //UIの設定
        //決定ボタン
        addMemberDone.setOnClickListener {
            addUser()
            finish()
        }
    }

    //RealmUserへの登録処理
    private fun addUser() {
        val realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        mUser = User()
        val userRealmResults = realm.where(User::class.java).findAll()

        //idの設定
        val identifier: Int =
            if (userRealmResults.max("id") != null) {
                userRealmResults.max("id")!!.toInt() + 1
            } else {
                0
            }

        mUser!!.id = identifier

        //ユーザー名の登録
        val name = addMemberEdit.text.toString()
        mUser!!.name = name

        realm.copyToRealmOrUpdate(mUser!!)
        realm.commitTransaction()

        realm.close()
    }
}
