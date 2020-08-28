package com.tableplay.poker

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import io.realm.Realm
import jp.playing.table.poker.Game
import kotlinx.android.synthetic.main.activity_title.*
import java.util.*

class TitleActivity : AppCompatActivity() {
    private var mYear = 0
    private var mMonth = 0
    private var mDay = 0
    private var mGame: Game? = null
    private var mDate = ""


    private val mOnDateClickListner = View.OnClickListener {
        val datePickerDialog = DatePickerDialog(this,
            DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
                mYear = year
                mMonth = month
                mDay = dayOfMonth
                val dateString = mYear.toString() + "/" + String.format("%02d", mMonth + 1) + "/" + String.format("%02d", mDay)
                titleDateButton.text = dateString
                mDate = dateString
            }, mYear, mMonth, mDay)
        datePickerDialog.show()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_title)

        supportActionBar?.title = "タイトル・日付設定"

        //UI部品の設定
        //日付設定リスナー
        titleDateButton.setOnClickListener(mOnDateClickListner)
        //決定ボタンのリスナー
        titleDoneButton.setOnClickListener {
            if (titleNameEdit.text.toString() != "" && titleDateButton.text.toString() != "") {
                addTask()
                val intent = Intent(this@TitleActivity, SettingActivity::class.java)
                startActivity(intent)
            } else {
                val toast = Toast.makeText(applicationContext, "テーブル名と日付を設定してください", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

        val calendar = Calendar.getInstance()
        mYear = calendar.get(Calendar.YEAR)
        mMonth = calendar.get(Calendar.MONTH)
        mDay = calendar.get(Calendar.DAY_OF_MONTH)
    }

    private fun addTask() {
        val realm = Realm.getDefaultInstance()

        realm.beginTransaction()

        mGame = Game()

        val gameRealmResults = realm.where(Game::class.java).findAll()

        //idの設定
        val identifier: Int =
            if (gameRealmResults.max("id") != null) {
                gameRealmResults.max("id")!!.toInt() + 1
            } else {
                0
            }

        mGame!!.id = identifier

        //タイトルの設定
        val title = titleNameEdit.text.toString()
        mGame!!.title = title

        //日付の設定
        val calendar = GregorianCalendar(mYear, mMonth, mDay)
        val date = calendar.time
        mGame!!.date = date

        mGame!!.dateString = mDate

        realm.copyToRealmOrUpdate(mGame!!)
        realm.commitTransaction()

        realm.close()

    }
}
