package com.example.poker

import android.content.Intent
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.text.Editable
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.*
import io.realm.Realm
import io.realm.kotlin.where
import jp.playing.table.poker.Game
import jp.playing.table.poker.Member
import jp.playing.table.poker.Player
import jp.playing.table.poker.User
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_setting.*

import kotlinx.android.synthetic.main.activity_turn.*
import kotlin.math.log

class TurnActivity : AppCompatActivity() {

    private var spinnerText1 = ""
    private var spinnerText2 = ""
    private var spinnerText3 = ""
    private var spinnerText4 = ""
    private var spinnerText5 = ""
    private var spinnerText6 = ""
    private var spinnerText7 = ""
    private var spinnerText8 = ""
    private var spinnerText9 = ""
    private var spinnerText10 = ""

    private var chips1 = "none"
    private var chips2 = "none"
    private var chips3 = "none"
    private var chips4 = "none"
    private var chips5 = "none"
    private var chips6 = "none"
    private var chips7 = "none"
    private var chips8 = "none"
    private var chips9 = "none"
    private var chips10 = "none"

    private var mMember = 0

    private var mChips = 0

    private var mPlayer: Player? = null

    private var mMemBerPlay: Member? = null

    private var mGame: Game? = null

    private var mUser: User? = null

    private lateinit var mRealm: Realm

    private var doneChecker1 = "NO"
    private var doneChecker2 = "NO"
    private var doneChecker3 = "NO"
    private var doneChecker4 = "NO"
    private var doneChecker5 = "NO"
    private var doneChecker6 = "NO"
    private var doneChecker7 = "NO"
    private var doneChecker8 = "NO"
    private var doneChecker9 = "NO"
    private var doneChecker10 = "NO"

    private var roundPlayer = ""

    private var gameLength = 0

    private var startNum = 0

    private var btn = 0

    private var sb = 0

    private var bb = 0

    private var playingNum = 0

    private var myRound = 0

    private var firstRealm = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_turn)
        setSupportActionBar(turnToolbar)

        supportActionBar?.title = "詳細設定"

        //Realmの設定
        mRealm = Realm.getDefaultInstance()
        //Todo mRealm.addChangeListener(mRealmListener)




        //インテントを受け取る
        val extras = intent.extras
        mMember = extras!!.getString("Member")!!.toInt()
        mChips = extras!!.getString("Chips")!!.toInt()




        //UI部品の設定
        //Spinnerのリスナー
        turnSpinner1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit1.setText(mChips.toString())
                }

                spinnerText1 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit2.setText(mChips.toString())
                }

                spinnerText2 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner3.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit3.setText(mChips.toString())
                }

                spinnerText3 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner4.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit4.setText(mChips.toString())
                }

                spinnerText4 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner5.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit5.setText(mChips.toString())
                }

                spinnerText5 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner6.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit6.setText(mChips.toString())
                }

                spinnerText6 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner7.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit7.setText(mChips.toString())
                }

                spinnerText7 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner8.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit8.setText(mChips.toString())
                }

                spinnerText8 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner9.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit9.setText(mChips.toString())
                }

                spinnerText9 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        turnSpinner10.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String

                if (item == "自分"){
                    turnEdit10.setText(mChips.toString())
                }
                spinnerText10 = item
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        //プレイヤー新規登録ボタンのリスナー
        newPlayerButton.setOnClickListener {
            val intent = Intent(this@TurnActivity, AddMemberListActivity::class.java)
            startActivity(intent)
        }

        //全員同額ボタンのリスナー
        sameChipsButton.setOnClickListener {
            turnEdit1.setText(mChips.toString())
            turnEdit2.setText(mChips.toString())
            turnEdit3.setText(mChips.toString())
            turnEdit4.setText(mChips.toString())
            turnEdit5.setText(mChips.toString())
            turnEdit6.setText(mChips.toString())
            turnEdit7.setText(mChips.toString())
            turnEdit8.setText(mChips.toString())
            turnEdit9.setText(mChips.toString())
            turnEdit10.setText(mChips.toString())
        }

        //決定ボタンのリスナー
        turnDoneButton.setOnClickListener {


            cheackSpinner()

            cheackChips()


            if (
                spinnerText1 == "自分" || spinnerText2 == "自分" || spinnerText3 == "自分" || spinnerText4 == "自分" || spinnerText5 == "自分" ||
                spinnerText6 == "自分" || spinnerText7 == "自分" || spinnerText8 == "自分" || spinnerText9 == "自分" || spinnerText10 == "自分"
                    ) {
                if (
                    chips1 != "" && chips2 != "" && chips3 != "" && chips4 != "" && chips5 != "" &&
                    chips6 != "" && chips7 != "" && chips8 != "" && chips9 != "" && chips10 != ""
                        ) {
                    if (
                        chips1 != "0" && chips2 != "0" && chips3 != "0" && chips4 != "0" && chips5 != "0" &&
                        chips6 != "0" && chips7 != "0" && chips8 != "0" && chips9 != "0" && chips10 != "0"
                            ) {

                        if (
                            doneChecker1 == "OK" && doneChecker2 == "OK" && doneChecker3 == "OK" && doneChecker4 == "OK" && doneChecker5 == "OK" &&
                            doneChecker6 == "OK" && doneChecker7 == "OK" && doneChecker8 == "OK" && doneChecker9 == "OK" && doneChecker10 == "OK"
                        ) {
                            when (mMember) {
                                2 -> {
                                    if (spinnerText2 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 2
                                        playingNum = 2
                                        myRound = 2
                                        sb = 2
                                        btn = 1
                                        bb = 2

                                    } else {
                                        roundPlayer = "other"
                                        startNum = 2
                                        playingNum = 2
                                        sb = 2
                                        btn = 1
                                        bb = 2
                                    }
                                }

                                3 -> {
                                    if (spinnerText1 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 1
                                        playingNum = 1
                                        myRound = 1
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 1
                                        playingNum = 1
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                        }
                                    }
                                }

                                4 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                        }
                                    }
                                }

                                5 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                        }
                                    }
                                }

                                6 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                            spinnerText6 -> myRound = 6
                                        }
                                    }
                                }

                                7 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                            spinnerText6 -> myRound = 6
                                            spinnerText7 -> myRound = 7
                                        }
                                    }
                                }

                                8 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                            spinnerText6 -> myRound = 6
                                            spinnerText7 -> myRound = 7
                                            spinnerText8 -> myRound = 8
                                        }
                                    }
                                }

                                9 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                            spinnerText6 -> myRound = 6
                                            spinnerText7 -> myRound = 7
                                            spinnerText8 -> myRound = 8
                                            spinnerText9 -> myRound = 9
                                        }
                                    }
                                }

                                10 -> {
                                    if (spinnerText4 == "自分") {
                                        roundPlayer = "you"
                                        startNum = 4
                                        playingNum = 4
                                        myRound = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                    } else {
                                        roundPlayer = "other"
                                        startNum = 4
                                        playingNum = 4
                                        btn = 1
                                        sb = 2
                                        bb = 3
                                        when ("自分") {
                                            spinnerText1 -> myRound = 1
                                            spinnerText2 -> myRound = 2
                                            spinnerText3 -> myRound = 3
                                            spinnerText5 -> myRound = 5
                                            spinnerText6 -> myRound = 6
                                            spinnerText7 -> myRound = 7
                                            spinnerText8 -> myRound = 8
                                            spinnerText9 -> myRound = 9
                                            spinnerText10 -> myRound = 10
                                        }
                                    }
                                }
                            }
                            addPlayer()


                            val realmrealm = mRealm.where(Member::class.java).findAll()
                            Log.d("kotlintest", realmrealm.toString())
                            val intent = Intent(this@TurnActivity, HandActivity::class.java)
                            intent.putExtra("memberNum", mMember)
                            intent.putExtra("roundPlayer", roundPlayer)
                            intent.putExtra("game_id", gameLength)
                            intent.putExtra("flopNum", startNum)
                            intent.putExtra("playingNum", playingNum)
                            intent.putExtra("count", 1)
                            intent.putExtra("bigBlind", 0 )
                            intent.putExtra("smallBlind", 0)
                            intent.putExtra("myRound", myRound)
                            intent.putExtra("firstRealm", firstRealm)
                            intent.putExtra("BTN", btn)
                            intent.putExtra("SB", sb)
                            intent.putExtra("BB", bb)
                            intent.putExtra("foldPlayer", 0)
                            intent.putExtra("send", "TurnActivity")
                            startActivity(intent)
                        }
                    }
                }
            }
        }
    }

    //プレイヤー登録
    private fun addPlayer() {
        Log.d("kotlintest", "通過1")

        val userRealmResutls = mRealm.where(User::class.java).contains("name", String()).findAll()
        val length = userRealmResutls.size

        Log.d("kotlintest", "通過2")

        var playerId1 = ""
        var playerId2 = ""
        var playerId3 = ""
        var playerId4 = ""
        var playerId5 = ""
        var playerId6 = ""
        var playerId7 = ""
        var playerId8 = ""
        var playerId9 = ""
        var playerId10 = ""

        Log.d("kotlintest", "通過3")

        for (i in 0 .. length - 1) {
            Log.d("kotlintest", "通過4")
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText1) {
                playerId1 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText2) {
                playerId2 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText3) {
                playerId3 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText4) {
                playerId4 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText5) {
                playerId5 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText6) {
                playerId6 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText7) {
                playerId7 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText8) {
                playerId8 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText9) {
                playerId9 = i.toString()
            }
        }

        for (i in 0 .. length - 1) {
            val name = userRealmResutls[i]!!.name
            if (name == spinnerText10) {
                playerId10 = i.toString()
            }
        }

        Log.d("kotlintest", "通過5")

        val gameRealmResults = mRealm.where(Game::class.java).findAll()

        gameLength = gameRealmResults.max("id")!!.toInt()

        mRealm.beginTransaction()
        Log.d("kotlintest", "通過6")

        var round = 1

        val userArray = arrayOf(playerId1, playerId2, playerId3, playerId4, playerId5, playerId6, playerId7, playerId8, playerId9, playerId10)

        mPlayer = Player()

        Log.d("kotlintest", "通過7")

        for (i  in userArray) {
            Log.d("kotlintest", "通過8")
            if (i != "") {
                val playerRealmResults = mRealm.where(Player::class.java).findAll()

                val identifier: Int =
                    if (playerRealmResults.max("id") != null ) {
                        playerRealmResults.max("id")!!.toInt() + 1
                    } else {
                        0
                    }
                mPlayer!!.id = identifier

                mPlayer!!.game_id = gameLength
                mPlayer!!.playerId = i.toInt()
                mPlayer!!.playerRound = round
                mPlayer!!.playerNum = mMember
                mRealm.copyToRealmOrUpdate(mPlayer!!)
            }


            round++
        }

        val memberArray = arrayOf(spinnerText1, spinnerText2, spinnerText3, spinnerText4, spinnerText5, spinnerText6, spinnerText7, spinnerText8, spinnerText9, spinnerText10)
        var playRound = 1

        mMemBerPlay = Member()

        for (i in memberArray) {
            if (i != "") {
                Log.d("kotlintest", "通過9")
                val memberRealmResults = mRealm.where(Member::class.java).findAll()

                val identifier: Int =
                    if (memberRealmResults.max("id") != null) {
                        memberRealmResults.max("id")!!.toInt() + 1
                    } else {
                        0
                    }
                mMemBerPlay!!.id = identifier
                Log.d("kotlintest","Member保存：" + identifier.toString())
                mMemBerPlay!!.hand_count = 1
                mMemBerPlay!!.memberName = i
                mMemBerPlay!!.memberRound = playRound
                mMemBerPlay!!.game_id = gameLength
                mMemBerPlay!!.playingCheck = "play"
                mMemBerPlay!!.memberNum = mMember

                if (identifier == 0) {
                    firstRealm = i
                }

                Log.d("kotlintest", "通過10")

                when (i) {
                    spinnerText1 -> if (playerId1 != "") {mMemBerPlay!!.member_id = playerId1}
                    spinnerText2 -> if (playerId2 != "") {mMemBerPlay!!.member_id = playerId2}
                    spinnerText3 -> if (playerId3 != "") {mMemBerPlay!!.member_id = playerId3}
                    spinnerText4 -> if (playerId4 != "") {mMemBerPlay!!.member_id = playerId4}
                    spinnerText5 -> if (playerId5 != "") {mMemBerPlay!!.member_id = playerId5}
                    spinnerText6 -> if (playerId6 != "") {mMemBerPlay!!.member_id = playerId6}
                    spinnerText7 -> if (playerId7 != "") {mMemBerPlay!!.member_id = playerId7}
                    spinnerText8 -> if (playerId8 != "") {mMemBerPlay!!.member_id = playerId8}
                    spinnerText9 -> if (playerId9 != "") {mMemBerPlay!!.member_id = playerId9}
                    spinnerText10 -> if (playerId10 != "") {mMemBerPlay!!.member_id = playerId10}

                }

                Log.d("kotlintest", "通過11")

                when (playRound) {
                    1 -> mMemBerPlay!!.memberChips = chips1.toInt()
                    2 -> mMemBerPlay!!.memberChips = chips2.toInt()
                    3 -> mMemBerPlay!!.memberChips = chips3.toInt()
                    4 -> mMemBerPlay!!.memberChips = chips4.toInt()
                    5 -> mMemBerPlay!!.memberChips = chips5.toInt()
                    6 -> mMemBerPlay!!.memberChips = chips6.toInt()
                    7 -> mMemBerPlay!!.memberChips = chips7.toInt()
                    8 -> mMemBerPlay!!.memberChips = chips8.toInt()
                    9 -> mMemBerPlay!!.memberChips = chips9.toInt()
                    10 -> mMemBerPlay!!.memberChips = chips10.toInt()

                }

                Log.d("kotlintest", "id:" + mMemBerPlay!!.id.toString())
                Log.d("kotlintest", "hand_count:" + mMemBerPlay!!.hand_count.toString())
                Log.d("kotlintest", "memberName:" + mMemBerPlay!!.memberName)
                Log.d("kotlintest", "memberRound:" + mMemBerPlay!!.memberRound.toString())
                Log.d("kotlintest", "game_id:" + mMemBerPlay!!.game_id.toString())
                Log.d("kotlintest", "playingCheck:" + mMemBerPlay!!.playingCheck)
                Log.d("kotlintest", "member_id:" + mMemBerPlay!!.member_id)
                Log.d("kotlintest", "memberChips:" + mMemBerPlay!!.memberChips.toString())

                mRealm.copyToRealmOrUpdate(mMemBerPlay!!)
                Log.d("kotlintest", "通過12")

            }

            playRound++
        }
        mRealm.commitTransaction()




    }


    //チップを設定
    private fun cheackChips() {
        when (mMember) {
            2 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
            }

            3 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
            }

            4 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
            }

            5 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
            }

            6 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
                chips6 = turnEdit6.text.toString()
            }

            7 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
                chips6 = turnEdit6.text.toString()
                chips7 = turnEdit7.text.toString()
            }

            8 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
                chips6 = turnEdit6.text.toString()
                chips7 = turnEdit7.text.toString()
                chips8 = turnEdit8.text.toString()
            }

            9 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
                chips6 = turnEdit6.text.toString()
                chips7 = turnEdit7.text.toString()
                chips8 = turnEdit8.text.toString()
                chips9 = turnEdit9.text.toString()
            }

            10 -> {
                chips1 = turnEdit1.text.toString()
                chips2 = turnEdit2.text.toString()
                chips3 = turnEdit3.text.toString()
                chips4 = turnEdit4.text.toString()
                chips5 = turnEdit5.text.toString()
                chips6 = turnEdit6.text.toString()
                chips7 = turnEdit7.text.toString()
                chips8 = turnEdit8.text.toString()
                chips9 = turnEdit9.text.toString()
                chips10 = turnEdit10.text.toString()
            }
        }
    }

    //プレイヤーに重複がないかチェック
    private fun cheackSpinner() {
        when (mMember) {
            2 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else {
                        doneChecker1 = "OK"
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    doneChecker2 = "OK"
                }

                doneChecker3 = "OK"
                doneChecker4 = "OK"
                doneChecker5 = "OK"
                doneChecker6 = "OK"
                doneChecker7 = "OK"
                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            3 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else {
                        doneChecker2 = "OK"
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    doneChecker3 = "OK"
                }

                doneChecker4 = "OK"
                doneChecker5 = "OK"
                doneChecker6 = "OK"
                doneChecker7 = "OK"
                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            4 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else {
                        doneChecker3 = "OK"
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    doneChecker4 = "OK"
                }

                doneChecker5 = "OK"
                doneChecker6 = "OK"
                doneChecker7 = "OK"
                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            5 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else {
                        doneChecker4 = "OK"
                        doneChecker5 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker5 = "OK"
                } else {
                    doneChecker5 = "OK"
                }

                doneChecker6 = "OK"
                doneChecker7 = "OK"
                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            6 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText1 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker6 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText2 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker6 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText3 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker6 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText4 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker6 = "NO"
                    } else {
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker5 = "OK"
                } else {
                    if (spinnerText5 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker6 = "NO"
                    } else {
                        doneChecker5 = "OK"
                        doneChecker6 = "OK"
                    }
                }

                if (spinnerText6 == "") {
                    spinnerText6 = "player6"
                    doneChecker6 = "OK"
                } else {
                    doneChecker6 = "OK"
                }

                doneChecker7 = "OK"
                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            7 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText1 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText1 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText2 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText2 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText3 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText3 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText4 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText4 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker5 = "OK"
                } else {
                    if (spinnerText5 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText5 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker5 = "OK"
                    }
                }

                if (spinnerText6 == "") {
                    spinnerText6 = "player6"
                    doneChecker6 = "OK"
                } else {
                    if (spinnerText6 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker7 = "NO"
                    } else {
                        doneChecker6 = "OK"
                        doneChecker7 = "OK"
                    }
                }

                if (spinnerText7 == "") {
                    spinnerText7 = "player7"
                    doneChecker7 = "OK"
                } else {
                    doneChecker7 = "OK"
                }

                doneChecker8 = "OK"
                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            8 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText1 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText1 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText1 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText2 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText2 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText2 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText3 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText3 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText3 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText4 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText4 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText4 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker5 = "OK"
                } else {
                    if (spinnerText5 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText5 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText5 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker5 = "OK"
                    }
                }

                if (spinnerText6 == "") {
                    spinnerText6 = "player6"
                    doneChecker6 = "OK"
                } else {
                    if (spinnerText6 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText6 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker6 = "OK"
                    }
                }

                if (spinnerText7 == "") {
                    spinnerText7 = "player7"
                    doneChecker7 = "OK"
                } else {
                    if (spinnerText7 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker8 = "NO"
                    } else {
                        doneChecker7 = "OK"
                        doneChecker8 = "OK"
                    }
                }

                if (spinnerText8 == "") {
                    spinnerText8 = "player8"
                    doneChecker8 = "OK"
                } else {
                    doneChecker8 = "OK"
                }

                doneChecker9 = "OK"
                doneChecker10 = "OK"
            }

            9 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText1 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText1 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText1 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText1 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText2 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText2 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText2 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText2 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText3 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText3 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText3 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText3 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText4 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText4 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText4 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText4 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText5 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText5 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText5 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText5 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker5 = "OK"
                    }
                }

                if (spinnerText6 == "") {
                    spinnerText6 = "player6"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText6 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText6 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText6 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker6 = "OK"
                    }
                }

                if (spinnerText7 == "") {
                    spinnerText7 = "player7"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText7 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText7 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker7 = "OK"
                    }
                }

                if (spinnerText8 == "") {
                    spinnerText8 = "player8"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText8 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker8 = "NO"
                        doneChecker9 = "NO"
                    } else {
                        doneChecker8 = "OK"
                        doneChecker9 = "OK"
                    }
                }

                if (spinnerText9 == "") {
                    spinnerText9 = "player9"
                    doneChecker9 = "OK"
                } else {
                    doneChecker9 = "OK"
                }

                doneChecker10 = "OK"
            }

            10 -> {
                if (spinnerText1 == "") {
                    spinnerText1 = "player1"
                    doneChecker1 = "OK"
                } else {
                    if (spinnerText1 == spinnerText2) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker2 = "NO"
                    } else if (spinnerText1 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText1 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText1 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText1 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText1 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText1 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText1 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText1 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker1 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker1 = "OK"
                    }
                }

                if (spinnerText2 == "") {
                    spinnerText2 = "player2"
                    doneChecker2 = "OK"
                } else {
                    if (spinnerText2 == spinnerText3) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker3 = "NO"
                    } else if (spinnerText2 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText2 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText2 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText2 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText2 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText2 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText2 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker2 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker2 = "OK"
                    }
                }

                if (spinnerText3 == "") {
                    spinnerText3 = "player3"
                    doneChecker3 = "OK"
                } else {
                    if (spinnerText3 == spinnerText4) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker4 = "NO"
                    } else if (spinnerText3 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText3 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText3 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText3 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText3 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText3 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker3 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker3 = "OK"
                    }
                }

                if (spinnerText4 == "") {
                    spinnerText4 = "player4"
                    doneChecker4 = "OK"
                } else {
                    if (spinnerText4 == spinnerText5) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker5 = "NO"
                    } else if (spinnerText4 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText4 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText4 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText4 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText4 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker4 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker4 = "OK"
                    }
                }

                if (spinnerText5 == "") {
                    spinnerText5 = "player5"
                    doneChecker5 = "OK"
                } else {
                    if (spinnerText5 == spinnerText6) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker6 = "NO"
                    } else if (spinnerText5 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText5 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText5 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText5 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker5 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker5 = "OK"
                    }
                }

                if (spinnerText6 == "") {
                    spinnerText6 = "player6"
                    doneChecker6 = "OK"
                } else {
                    if (spinnerText6 == spinnerText7) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker7 = "NO"
                    } else if (spinnerText6 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText6 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText6 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker6 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker6 = "OK"
                    }
                }

                if (spinnerText7 == "") {
                    spinnerText7 = "player7"
                    doneChecker7 = "OK"
                } else {
                    if (spinnerText7 == spinnerText8) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker8 = "NO"
                    } else if (spinnerText7 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText7 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker7 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker7 = "OK"
                    }
                }

                if (spinnerText8 == "") {
                    spinnerText8 = "player8"
                    doneChecker8 = "OK"
                } else {
                    if (spinnerText8 == spinnerText9) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker8 = "NO"
                        doneChecker9 = "NO"
                    } else if (spinnerText8 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker8 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker8 = "OK"
                    }
                }

                if (spinnerText9 == "") {
                    spinnerText9 = "player9"
                    doneChecker9 = "OK"
                } else {
                    if (spinnerText9 == spinnerText10) {
                        val toast = Toast.makeText(applicationContext, "プレイヤー名が重複しています", Toast.LENGTH_SHORT)
                        toast.setGravity(Gravity.CENTER or Gravity.CENTER, 0, 0)
                        toast.show()
                        doneChecker9 = "NO"
                        doneChecker10 = "NO"
                    } else {
                        doneChecker9 = "OK"
                        doneChecker10 = "OK"
                    }
                }

                if (spinnerText10 == "") {
                    spinnerText10 = "player10"
                    doneChecker10 = "OK"
                } else {
                    doneChecker10 = "OK"
                }
            }

        }
    }

    override fun onResume() {
        super.onResume()

        //Spinnerをセット
        val userRealmResults = mRealm.where(User::class.java).contains("name", String()).findAll()

        val spinnerItems = ArrayList<String>()

        spinnerItems.add("")
        spinnerItems.add("自分")

        val length = userRealmResults.size

        for (i in 0 .. length - 1) {
            spinnerItems.add(userRealmResults[i]!!.name)
        }

        val adapter = ArrayAdapter(
            applicationContext,
            android.R.layout.simple_spinner_dropdown_item,
            spinnerItems
        )

        turnSpinner1.adapter = adapter
        turnSpinner2.adapter = adapter
        turnSpinner3.adapter = adapter
        turnSpinner4.adapter = adapter
        turnSpinner5.adapter = adapter
        turnSpinner6.adapter = adapter
        turnSpinner7.adapter = adapter
        turnSpinner8.adapter = adapter
        turnSpinner9.adapter = adapter
        turnSpinner10.adapter = adapter

        //設定したプレイヤー数のみ表示する
        when {
            (mMember == 2) -> {
                turnText3.visibility = View.INVISIBLE
                turnText4.visibility = View.INVISIBLE
                turnText5.visibility = View.INVISIBLE
                turnText6.visibility = View.INVISIBLE
                turnText7.visibility = View.INVISIBLE
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit3.visibility = View.INVISIBLE
                turnEdit4.visibility = View.INVISIBLE
                turnEdit5.visibility = View.INVISIBLE
                turnEdit6.visibility = View.INVISIBLE
                turnEdit7.visibility = View.INVISIBLE
                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner3.visibility = View.INVISIBLE
                turnSpinner4.visibility = View.INVISIBLE
                turnSpinner5.visibility = View.INVISIBLE
                turnSpinner6.visibility = View.INVISIBLE
                turnSpinner7.visibility = View.INVISIBLE
                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 3) -> {
                turnText4.visibility = View.INVISIBLE
                turnText5.visibility = View.INVISIBLE
                turnText6.visibility = View.INVISIBLE
                turnText7.visibility = View.INVISIBLE
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit4.visibility = View.INVISIBLE
                turnEdit5.visibility = View.INVISIBLE
                turnEdit6.visibility = View.INVISIBLE
                turnEdit7.visibility = View.INVISIBLE
                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner4.visibility = View.INVISIBLE
                turnSpinner5.visibility = View.INVISIBLE
                turnSpinner6.visibility = View.INVISIBLE
                turnSpinner7.visibility = View.INVISIBLE
                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 4) -> {
                turnText5.visibility = View.INVISIBLE
                turnText6.visibility = View.INVISIBLE
                turnText7.visibility = View.INVISIBLE
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit5.visibility = View.INVISIBLE
                turnEdit6.visibility = View.INVISIBLE
                turnEdit7.visibility = View.INVISIBLE
                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner5.visibility = View.INVISIBLE
                turnSpinner6.visibility = View.INVISIBLE
                turnSpinner7.visibility = View.INVISIBLE
                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 5) -> {
                turnText6.visibility = View.INVISIBLE
                turnText7.visibility = View.INVISIBLE
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit6.visibility = View.INVISIBLE
                turnEdit7.visibility = View.INVISIBLE
                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner6.visibility = View.INVISIBLE
                turnSpinner7.visibility = View.INVISIBLE
                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 6) -> {
                turnText7.visibility = View.INVISIBLE
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit7.visibility = View.INVISIBLE
                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner7.visibility = View.INVISIBLE
                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 7) -> {
                turnText8.visibility = View.INVISIBLE
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit8.visibility = View.INVISIBLE
                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner8.visibility = View.INVISIBLE
                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 8) -> {
                turnText9.visibility = View.INVISIBLE
                turnText10.visibility = View.INVISIBLE

                turnEdit9.visibility = View.INVISIBLE
                turnEdit10.visibility = View.INVISIBLE

                turnSpinner9.visibility = View.INVISIBLE
                turnSpinner10.visibility = View.INVISIBLE
            }

            (mMember == 9) -> {
                turnText10.visibility = View.INVISIBLE

                turnEdit10.visibility = View.INVISIBLE

                turnSpinner10.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mRealm.close()
    }

}