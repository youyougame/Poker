package com.tableplay.poker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity() {

    private var SpinnerText = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        supportActionBar?.title = "プレイヤー数・チップ数入力"

        //Sppinerの準備
        val spinnerItems = arrayOf("2", "3","4", "5", "6", "7", "8", "9", "10")

        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )

        settingSpinner.adapter = adapter

        //UI部品の設定
        //Spinnerのリスナー
        settingSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                SpinnerText = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //決定ボタンのリスナー
        settingDoneButton.setOnClickListener {
            if (settingChipsEdit.text.toString() != "" && SpinnerText != "") {
                val intent = Intent(this@SettingActivity, TurnActivity::class.java)
                intent.putExtra("Member", SpinnerText)
                intent.putExtra("Chips", settingChipsEdit.text.toString())
                startActivity(intent)
            } else {
                val toast = Toast.makeText(applicationContext, "チップ数とメンバー数を入力してください", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                toast.show()
            }
        }

    }
}
