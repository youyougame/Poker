package com.tableplay.poker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import jp.playing.table.poker.Member
import jp.playing.table.poker.Turn
import kotlinx.android.synthetic.main.activity_playing.*

class PlayingActivity : AppCompatActivity() {

    private var mTurn: Turn? = null

    private var mMember: Member? = null

    private lateinit var mRealm: Realm

    //ハンド選択

    private var chipsNum1 = ""
    private var chipsNum2 = ""
    private var chipsNum3 = ""
    private var chipsNum4 = ""
    private var chipsNum5 = ""
    private var chipsNum6 = ""
    private var chipsNum7 = ""

    private var memberNum = 0
    private var game_id = 0
    private var count = 0
    private var round = ""
    private var round_count = 0
    private var roundNum = 0
    private var myRound = 0
    private var cardHand1 = ""
    private var cardHand2 = ""
    private var cardCom1 = ""
    private var cardCom2 = ""
    private var cardCom3 = ""
    private var cardCom4 = ""
    private var cardCom5 = ""
    private var bigBlind = 0
    private var smallBlind = 0
    private var tableChips = 0
    private var tableTotalChips = 0
    private var startNum = 0
    private var flopNum = 0
    private var preFlopNum = 0
    private var bigBlindNum = 0
    private var playingNum = 0
    private var foldPlayer = 0
    private var sameChipsPlayer = 0

    private var memberChips = 0
    private var turnMemberChips = 0
    private var playChips = 0
    private var player_id = ""
    private var playingCheck = ""

    private var turnDataChips = 0
    private var turnDataCount = 0
    private var turnDataRound = ""
    private var turnDataRoundCount = 0
    private var turnDataPlayer = ""

    private var firstRealm = ""

    private var btn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playing)

        Log.d("kotlintest", "自分のプレイアクティビティ")

        //Realmの設定
        mRealm = Realm.getDefaultInstance()

        val memberChipsRealm = mRealm.where(Member::class.java).findAll()
        val memberChipsRealmId = memberChipsRealm.max("id")!!.toInt()

        memberChips = memberChipsRealm[memberChipsRealmId]!!.memberChips


        val intent = intent
        memberNum = intent.getIntExtra("memberNum", 0)
        game_id = intent.getIntExtra("game_id", 0)
        count = intent.getIntExtra("count", 0)
        round = intent.getStringExtra("round")
        round_count = intent.getIntExtra("round_count", 0)
        roundNum = intent.getIntExtra("roundNum", 0)
        myRound = intent.getIntExtra("myRound", 0)
        cardHand1 = intent.getStringExtra("cardHand1")
        cardHand2 = intent.getStringExtra("cardHand2")
        cardCom1 = intent.getStringExtra("cardCom1")
        cardCom2 = intent.getStringExtra("cardCom2")
        cardCom3 = intent.getStringExtra("cardCom3")
        cardCom4 = intent.getStringExtra("cardCom4")
        cardCom5 = intent.getStringExtra("cardCom5")
        bigBlind = intent.getIntExtra("bigBlind", 0)
        smallBlind = intent.getIntExtra("smallBlind", 0)
        tableChips = intent.getIntExtra("tableChips", 0)
        tableTotalChips = intent.getIntExtra("tableTotalChips", 0)
        flopNum = intent.getIntExtra("flopNum", 0)
        preFlopNum = intent.getIntExtra("preFlopNum", 0)
        bigBlindNum = intent.getIntExtra("bigBlindNum", 0)
        btn = intent.getIntExtra("BTN", 0)
        playingNum = intent.getIntExtra("playingNum", 0)
        foldPlayer = intent.getIntExtra("foldPlayer", 0)
        sameChipsPlayer = intent.getIntExtra("sameChipsPlayer", 0)
        firstRealm = intent.getStringExtra("firstRealm")

        supportActionBar?.title = "あなたの順番です" + "：" + round

        if (round == "flop") {
            startNum = flopNum
        } else {
            startNum = preFlopNum
        }

        val memberRealmResults = mRealm.where(Member::class.java).findAll()
        val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
        Log.d("kotlintest", memberPlayerNumRealmResults.toString())
        val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
        val memberRealmData = mRealm.where(Member::class.java).equalTo("id", playerNumId).findFirst()
        var memberPlayingCheck = "play"
        if (memberRealmData!!.hand_count == count && memberRealmData!!.game_id == game_id) {
            memberPlayingCheck = memberRealmData!!.playingCheck
        }
        Log.d("kotlintest", "調べたID：" + memberRealmData!!.id.toString() + "：" + playerNumId.toString())
        Log.d("kotlintest", "memberRealmData!!.hand_count:" + memberRealmData!!.hand_count.toString() + " count:" + count.toString() + " memberRealmData!!.game_id:" + memberRealmData!!.game_id.toString() + " game_id:" + game_id.toString() + " memberRealmData!!.playingCheck:" + memberRealmData!!.playingCheck)
        val turnChips = memberRealmData!!.memberChips
        val totalChipsAllIn = memberRealmData!!.memberChips
//        Log.d("kotlintest", "受け取ったID：" + playerNumId)
//        Log.d("kotlintest", memberRealmResults[playerNumId]!!.memberName)
//        val memberPlayingCheck = memberRealmResults[playerNumId]!!.playingCheck
//        val turnChips = memberRealmResults[playerNumId]!!.memberChips

        var allInNum = 0
        for (i in 1..memberNum) {
            val playerNumRealmResults =
                mRealm.where(Member::class.java).equalTo("memberRound", i).findAll()
            val playerNumId = playerNumRealmResults.max("id")!!.toInt()
            val playerData =
                mRealm.where(Member::class.java).equalTo("id", playerNumId)
                    .findFirst()
            val playerChips = playerData!!.memberChips
            if (playerChips == 0) {
                allInNum++
            }
//            if (playingNum != i) {
//                if (playerChips == 0) {
//                    sameChipsPlayer++
//                }
//            }
            Log.d("kotlintest", "allInNum == memberNum - foldPlayer:" + allInNum.toString() + " == " + memberNum.toString() + " - " + foldPlayer.toString())
        }

        if (memberPlayingCheck == "fold") {
            Log.d("kotlintest", "PA:Fold")
            if (sameChipsPlayer == memberNum - foldPlayer) {
                when (round) {
                    "preflop" -> round = "flop"
                    "flop" -> round = "turn"
                    "turn" -> round = "river"
                    "turn" -> round = "showdown"
                }

                // CardActivityへ移動
                val intent = Intent(this@PlayingActivity, CardActivity::class.java)
                Log.d("kotlintest", "通過PA1")
                Log.d("kotlintest", "PlayingActivity -> CardActivity[count]:" + count.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[round]:" + round)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[btn]:" + btn.toString())

                intent.putExtra("memberNum", memberNum)
                intent.putExtra("game_id", game_id)
                intent.putExtra("count", count)
                intent.putExtra("round", round)
                intent.putExtra("round_count", round_count)
                intent.putExtra("roundNum", roundNum)
                intent.putExtra("myRound", myRound)
                intent.putExtra("cardHand1", cardHand1)
                intent.putExtra("cardHand2", cardHand2)
                intent.putExtra("cardCom1", cardCom1)
                intent.putExtra("cardCom2", cardCom2)
                intent.putExtra("cardCom3", cardCom3)
                intent.putExtra("cardCom4", cardCom4)
                intent.putExtra("cardCom5", cardCom5)
                intent.putExtra("bigBlind", bigBlind)
                intent.putExtra("smallBlind", smallBlind)
                intent.putExtra("tableChips", tableChips)
                intent.putExtra("tableTotalChips", tableTotalChips)
                intent.putExtra("flopNum", flopNum)
                intent.putExtra("preFlopNum", preFlopNum)
                intent.putExtra("bigBlindNum", bigBlindNum)
                intent.putExtra("BTN", btn)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)

            }

            if (roundNum == memberNum) {
                round_count++
                roundNum = 1
            } else {
                roundNum++
            }

            if (playingNum == memberNum) {
                playingNum = 1
            } else {
                playingNum++
            }

            // MemberPlayingActivityに移動
            val intent = Intent(this@PlayingActivity, MemberPlayingActivity::class.java)
            Log.d("kotlintest", "通過PA2")
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[count]:" + count.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[round]:" + round)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[round_count]:" + round_count.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[roundNum]:" + roundNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[myRound]:" + myRound.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[flopNum]:" + flopNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[preFlopNum]:" + preFlopNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlindNum]" + bigBlindNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[playingNum]:" + playingNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[foldPlayer]:" + foldPlayer.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips)
            Log.d("kotlintest", "PlayingActivity -> MemnerPlsyingActivity[btn]:" + btn.toString())

            intent.putExtra("memberNum", memberNum)
            intent.putExtra("game_id", game_id)
            intent.putExtra("count", count)
            intent.putExtra("round", round)
            intent.putExtra("round_count", round_count)
            intent.putExtra("roundNum", roundNum)
            intent.putExtra("myRound", myRound)
            intent.putExtra("cardHand1", cardHand1)
            intent.putExtra("cardHand2", cardHand2)
            intent.putExtra("cardCom1", cardCom1)
            intent.putExtra("cardCom2", cardCom2)
            intent.putExtra("cardCom3", cardCom3)
            intent.putExtra("cardCom4", cardCom4)
            intent.putExtra("cardCom5", cardCom5)
            intent.putExtra("bigBlind", bigBlind)
            intent.putExtra("smallBlind", smallBlind)
            intent.putExtra("tableChips", tableChips)
            intent.putExtra("tableTotalChips", tableTotalChips)
            intent.putExtra("flopNum", flopNum)
            intent.putExtra("preFlopNum", preFlopNum)
            intent.putExtra("bigBlindNum", bigBlindNum)
            intent.putExtra("BTN", btn)
            intent.putExtra("playingNum", playingNum)
            intent.putExtra("foldPlayer", foldPlayer)
            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
            intent.putExtra("firstRealm", firstRealm)
            startActivity(intent)
        } else if (totalChipsAllIn == 0) {
            Log.d("kotlintest", "PA:AllLost")
            var playerAllIn = 0
            for (i in 1..memberNum) {
                val playerRealmResults = mRealm.where(Member::class.java).findAll()
                val playerNumRealmResults =
                    mRealm.where(Member::class.java).equalTo("memberRound", i).findAll()
                val playerNumId = playerNumRealmResults.max("id")!!.toInt()
                val playerData =
                    mRealm.where(Member::class.java).equalTo("id", playerNumId)
                        .findFirst()
                val playerChips = playerData!!.memberChips
                val playerFoldCheck = playerData!!.playingCheck
                if (playingNum != i) {
                    if (playerChips == 0 && playerFoldCheck == "play") {
                        playerAllIn++
                    }
                }
                Log.d("kotlintest", "player:" + playerData!!.memberName + " playerChips:" + playerChips.toString() + " playerFoldCheck:" + playerFoldCheck)
            }

            Log.d("kotlintest", "playerAllIn:" + playerAllIn.toString() + " == memberNum - 1:" + memberNum.toString())

            if (sameChipsPlayer >= memberNum - foldPlayer) {
                when (round) {
                    "preflop" -> round = "flop"
                    "flop" -> round = "turn"
                    "turn" -> round = "river"
                    "turn" -> round = "showdown"
                }

                when {
                    playerAllIn == memberNum - 1 -> round = "showdown"
                    memberNum - foldPlayer == 2 && playerAllIn == 1 -> round = "showdown"
                    allInNum == sameChipsPlayer -> round = "showdown"
                }

                Log.d("kotlintest", "allInNum == sameChipsPlayer:" + allInNum.toString() + " == " + sameChipsPlayer.toString())

                // CardActivityへ移動
                val intent = Intent(this@PlayingActivity, CardActivity::class.java)
                Log.d("kotlintest", "通過PA1")
                Log.d("kotlintest", "PlayingActivity -> CardActivity[count]:" + count.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[round]:" + round)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips)
                Log.d("kotlintest", "PlayingActivity -> CardActivity[btn]:" + btn.toString())

                intent.putExtra("memberNum", memberNum)
                intent.putExtra("game_id", game_id)
                intent.putExtra("count", count)
                intent.putExtra("round", round)
                intent.putExtra("round_count", round_count)
                intent.putExtra("roundNum", roundNum)
                intent.putExtra("myRound", myRound)
                intent.putExtra("cardHand1", cardHand1)
                intent.putExtra("cardHand2", cardHand2)
                intent.putExtra("cardCom1", cardCom1)
                intent.putExtra("cardCom2", cardCom2)
                intent.putExtra("cardCom3", cardCom3)
                intent.putExtra("cardCom4", cardCom4)
                intent.putExtra("cardCom5", cardCom5)
                intent.putExtra("bigBlind", bigBlind)
                intent.putExtra("smallBlind", smallBlind)
                intent.putExtra("tableChips", tableChips)
                intent.putExtra("tableTotalChips", tableTotalChips)
                intent.putExtra("flopNum", flopNum)
                intent.putExtra("preFlopNum", preFlopNum)
                intent.putExtra("bigBlindNum", bigBlindNum)
                intent.putExtra("BTN", btn)
                intent.putExtra("playingNum", playingNum)
                intent.putExtra("foldPlayer", foldPlayer)
                intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)

            }

            if (roundNum == memberNum) {
                round_count++
                roundNum = 1
            } else {
                roundNum++
            }

            if (playingNum == memberNum) {
                playingNum = 1
            } else {
                playingNum++
            }

            // MemberPlayingActivityに移動
            val intent = Intent(this@PlayingActivity, MemberPlayingActivity::class.java)
            Log.d("kotlintest", "通過PA2")
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[count]:" + count.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[round]:" + round)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[round_count]:" + round_count.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[roundNum]:" + roundNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[myRound]:" + myRound.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[flopNum]:" + flopNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[preFlopNum]:" + preFlopNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlindNum]" + bigBlindNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[playingNum]:" + playingNum.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[foldPlayer]:" + foldPlayer.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips.toString())
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips)
            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips)
            Log.d("kotlintest", "PlayingActivity -> MemnerPlsyingActivity[btn]:" + btn.toString())

            intent.putExtra("memberNum", memberNum)
            intent.putExtra("game_id", game_id)
            intent.putExtra("count", count)
            intent.putExtra("round", round)
            intent.putExtra("round_count", round_count)
            intent.putExtra("roundNum", roundNum)
            intent.putExtra("myRound", myRound)
            intent.putExtra("cardHand1", cardHand1)
            intent.putExtra("cardHand2", cardHand2)
            intent.putExtra("cardCom1", cardCom1)
            intent.putExtra("cardCom2", cardCom2)
            intent.putExtra("cardCom3", cardCom3)
            intent.putExtra("cardCom4", cardCom4)
            intent.putExtra("cardCom5", cardCom5)
            intent.putExtra("bigBlind", bigBlind)
            intent.putExtra("smallBlind", smallBlind)
            intent.putExtra("tableChips", tableChips)
            intent.putExtra("tableTotalChips", tableTotalChips)
            intent.putExtra("flopNum", flopNum)
            intent.putExtra("preFlopNum", preFlopNum)
            intent.putExtra("bigBlindNum", bigBlindNum)
            intent.putExtra("BTN", btn)
            intent.putExtra("playingNum", playingNum)
            intent.putExtra("foldPlayer", foldPlayer)
            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
            intent.putExtra("firstRealm", firstRealm)
            startActivity(intent)
        }

        val turnRealmResults = mRealm.where(Turn::class.java).findAll()
        val turnDataRealmResults = mRealm.where(Turn::class.java).equalTo("player", "自分").findAll()
        if (turnDataRealmResults.toString() != "[]") {
            val turnDataRealmResultsId = turnDataRealmResults.max("id")!!.toInt()
            turnDataChips = turnRealmResults[turnDataRealmResultsId]!!.playChips
            turnDataRound = turnRealmResults[turnDataRealmResultsId]!!.round //前回の自分のラウンド
            turnDataCount = turnRealmResults[turnDataRealmResultsId]!!.count //前回のゲーム数
            turnDataRoundCount = turnRealmResults[turnDataRealmResultsId]!!.round_count //ラウンド内の周数
            turnDataPlayer = turnRealmResults[turnDataRealmResultsId]!!.player //プレイヤー名

            if (turnDataChips == null || turnDataRound != round) {
                turnDataChips = 0
            }
        }

        playingHandText.text = "ミニマムベット：" + tableChips + " " + "ポット：" + tableTotalChips

        Log.d("kotlintest", turnDataChips.toString())

        playingChipsText.text = "0"



        cardSetting1()
        cardSetting2()

        //アクションボタンのUI表示設定
        if (tableChips != playingChipsText.text.toString().toInt()) {
            playingCheckButton.isEnabled = false
        } else {
            playingCheckButton.isEnabled = true
        }

        if (playingChipsText.text.toString().toInt() < tableChips) {
            playingCallButton.isEnabled = true
        } else {
            playingCallButton.isEnabled = false
        }

        //UI部品の設定
        playingCheckButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playingChipsText.text = playChips.toString()
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingCallButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""

            if (turnDataCount == count && turnDataRound == round && turnDataRoundCount + 1 == round_count) {
                Log.d("kotlintest", "playChips = tableChips - turnDataChips")
                playChips = tableChips - turnDataChips
            } else {
                Log.d("kotlintest", "playChips = tableChips")
                playChips = tableChips
            }

            Log.d("kotlintest", "turnDataCount:" + turnDataCount.toString())
            Log.d("kotlintest", "count:" + count.toString())
            Log.d("kotlintest", "turnDataRound:" + turnDataRound)
            Log.d("kotlintest", "round:" + round)
            Log.d("kotlintest", "turnDataRoundCount:" + turnDataRoundCount.toString())
            Log.d("kotlintest", "round_count:" + round_count.toString())
            Log.d("kotlintest", "tableChips:" + tableChips.toString())
            Log.d("kotlintest", "turnDataChips:" + turnDataChips.toString() )
            Log.d("kotlintest", "playChips:" + playChips.toString())

            playingChipsText.text = playChips.toString()
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingFoldButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playingChipsText.text = "0"
            playingCheck = "fold"

            playingFoldText.text = "fold"
        }

        playingAllInButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playChips = turnChips
            playingChipsText.text = turnChips.toString()
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum1.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "1"
                chipsNum2 == "" -> chipsNum2 = "1"
                chipsNum3 == "" -> chipsNum3 = "1"
                chipsNum4 == "" -> chipsNum4 = "1"
                chipsNum5 == "" -> chipsNum5 = "1"
                chipsNum6 == "" -> chipsNum6 = "1"
                chipsNum7 == "" -> chipsNum7 = "1"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum2.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "2"
                chipsNum2 == "" -> chipsNum2 = "2"
                chipsNum3 == "" -> chipsNum3 = "2"
                chipsNum4 == "" -> chipsNum4 = "2"
                chipsNum5 == "" -> chipsNum5 = "2"
                chipsNum6 == "" -> chipsNum6 = "2"
                chipsNum7 == "" -> chipsNum7 = "2"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum3.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "3"
                chipsNum2 == "" -> chipsNum2 = "3"
                chipsNum3 == "" -> chipsNum3 = "3"
                chipsNum4 == "" -> chipsNum4 = "3"
                chipsNum5 == "" -> chipsNum5 = "3"
                chipsNum6 == "" -> chipsNum6 = "3"
                chipsNum7 == "" -> chipsNum7 = "3"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum4.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "4"
                chipsNum2 == "" -> chipsNum2 = "4"
                chipsNum3 == "" -> chipsNum3 = "4"
                chipsNum4 == "" -> chipsNum4 = "4"
                chipsNum5 == "" -> chipsNum5 = "4"
                chipsNum6 == "" -> chipsNum6 = "4"
                chipsNum7 == "" -> chipsNum7 = "4"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum5.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "5"
                chipsNum2 == "" -> chipsNum2 = "5"
                chipsNum3 == "" -> chipsNum3 = "5"
                chipsNum4 == "" -> chipsNum4 = "5"
                chipsNum5 == "" -> chipsNum5 = "5"
                chipsNum6 == "" -> chipsNum6 = "5"
                chipsNum7 == "" -> chipsNum7 = "5"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum6.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "6"
                chipsNum2 == "" -> chipsNum2 = "6"
                chipsNum3 == "" -> chipsNum3 = "6"
                chipsNum4 == "" -> chipsNum4 = "6"
                chipsNum5 == "" -> chipsNum5 = "6"
                chipsNum6 == "" -> chipsNum6 = "6"
                chipsNum7 == "" -> chipsNum7 = "6"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum7.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "7"
                chipsNum2 == "" -> chipsNum2 = "7"
                chipsNum3 == "" -> chipsNum3 = "7"
                chipsNum4 == "" -> chipsNum4 = "7"
                chipsNum5 == "" -> chipsNum5 = "7"
                chipsNum6 == "" -> chipsNum6 = "7"
                chipsNum7 == "" -> chipsNum7 = "7"

            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum8.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "8"
                chipsNum2 == "" -> chipsNum2 = "8"
                chipsNum3 == "" -> chipsNum3 = "8"
                chipsNum4 == "" -> chipsNum4 = "8"
                chipsNum5 == "" -> chipsNum5 = "8"
                chipsNum6 == "" -> chipsNum6 = "8"
                chipsNum7 == "" -> chipsNum7 = "8"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum9.setOnClickListener {
            if (chipsNum1 == "0") {
                chipsNum1 == ""
            }
            when {
                chipsNum1 == "" -> chipsNum1 = "9"
                chipsNum2 == "" -> chipsNum2 = "9"
                chipsNum3 == "" -> chipsNum3 = "9"
                chipsNum4 == "" -> chipsNum4 = "9"
                chipsNum5 == "" -> chipsNum5 = "9"
                chipsNum6 == "" -> chipsNum6 = "9"
                chipsNum7 == "" -> chipsNum7 = "9"


            }
            playingChipsText.text = chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
            playingCheck = "play"
            playingFoldText.text = ""
        }

        playingNum0.setOnClickListener {
            if (playingChipsText.text != "0") {
                when {
                    chipsNum2 == "" -> chipsNum2 = "0"
                    chipsNum3 == "" -> chipsNum3 = "0"
                    chipsNum4 == "" -> chipsNum4 = "0"
                    chipsNum5 == "" -> chipsNum5 = "0"
                    chipsNum6 == "" -> chipsNum6 = "0"
                    chipsNum7 == "" -> chipsNum7 = "0"
                }
                playingChipsText.text =
                    chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
                playingCheck = "play"
                playingFoldText.text = ""
            }
        }

        playingNum00.setOnClickListener {
            if (playingChipsText.text != "0") {
                when {
                    chipsNum2 == "" -> {
                        chipsNum2 = "0"
                        chipsNum3 = "0"
                    }
                    chipsNum3 == "" -> {
                        chipsNum3 = "0"
                        chipsNum4 = "0"
                    }
                    chipsNum4 == "" -> {
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                    }
                    chipsNum5 == "" -> {
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                    }
                    chipsNum6 == "" -> {
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum7 == "" -> {
                        chipsNum7 = "0"
                    }
                }
                playingChipsText.text =
                    chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
                playingCheck = "play"
                playingFoldText.text = ""
            }
        }

        playingNum000.setOnClickListener {
            if (playingChipsText.text != "0") {
                when {
                    chipsNum2 == "" -> {
                        chipsNum2 = "0"
                        chipsNum3 = "0"
                        chipsNum4 = "0"
                    }
                    chipsNum3 == "" -> {
                        chipsNum3 = "0"
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                    }
                    chipsNum4 == "" -> {
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                    }
                    chipsNum5 == "" -> {
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum6 == "" -> {
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum7 == "" -> {
                        chipsNum7 = "0"
                    }
                }
                playingChipsText.text =
                    chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
                playingCheck = "play"
                playingFoldText.text = ""
            }
        }

        playingNum0000.setOnClickListener {
            if (playingChipsText.text != "0") {
                when {
                    chipsNum2 == "" -> {
                        chipsNum2 = "0"
                        chipsNum3 = "0"
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                    }
                    chipsNum3 == "" -> {
                        chipsNum3 = "0"
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                    }
                    chipsNum4 == "" -> {
                        chipsNum4 = "0"
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum5 == "" -> {
                        chipsNum5 = "0"
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum6 == "" -> {
                        chipsNum6 = "0"
                        chipsNum7 = "0"
                    }
                    chipsNum7 == "" -> {
                        chipsNum7 = "0"
                    }
                }
                playingChipsText.text =
                    chipsNum1 + chipsNum2 + chipsNum3 + chipsNum4 + chipsNum5 + chipsNum6 + chipsNum7
                playingCheck = "play"
                playingFoldText.text = ""
            }
        }

        playingDaleteButton.setOnClickListener {
            chipsNum1 = ""
            chipsNum2 = ""
            chipsNum3 = ""
            chipsNum4 = ""
            chipsNum5 = ""
            chipsNum6 = ""
            chipsNum7 = ""
            playingCheck = "play"

            playingChipsText.text = "0"
            playingFoldText.text = ""
        }

        playingDoneButton.setOnClickListener {
            //チップ値が入力されているか確認
            if (tableChips == 0 || chipsNum1 != "0") {
                if (playingChipsText.text != "") {
                    tableTotalChips = tableTotalChips + playingChipsText.text.toString().toInt()
                    //テーブルチップとの比較
                    if (turnDataCount == count && turnDataRound == round && turnDataRoundCount + 1 == round_count) {
                        when {
                            playingChipsText.text.toString()
                                .toInt() == tableChips - turnDataChips -> {
                                if (playingCheck != "fold") {
                                    sameChipsPlayer++
                                }
                            }
                            playingChipsText.text.toString()
                                .toInt() > tableChips - turnDataChips -> {
                                sameChipsPlayer = 1
                                tableChips = playingChipsText.text.toString().toInt()
                            }
                        }
                    } else {
                        when {
                            playingChipsText.text.toString().toInt() == tableChips -> {
                                if (playingCheck != "fold") {
                                    sameChipsPlayer++
                                }
                            }
                            playingChipsText.text.toString().toInt() > tableChips -> {
                                sameChipsPlayer = 1
                                tableChips = playingChipsText.text.toString().toInt()
                            }
                        }
                    }
                    mRealm.beginTransaction()
                    val turnRealmResults = mRealm.where(Turn::class.java).findAll()

                    //Turnへデータ保存
                    if (mTurn == null) {
                        mTurn = Turn()

                        val identifier: Int =
                            if (turnRealmResults.max("id") != null) {
                                turnRealmResults.max("id")!!.toInt() + 1
                            } else {
                                0
                            }
                        mTurn!!.id = identifier
                        Log.d("kotlintest", "Turn保存：" + identifier.toString())
                    }

                    mMember = Member()

//                val memberRealmResults = mRealm.where(Member::class.java).findAll()
                    //player_idを取得
//                val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", playingNum).findAll()
//                val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()

//                    val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
                    player_id = memberRealmData!!.member_id
                    val playerTotalChips = memberRealmData!!.memberChips
//                    player_id = memberRealmResults[playerNumId]!!.member_id
//                    val playerTotalChips = memberRealmResults[playerNumId]!!.memberChips

                    //Memberを更新
//                    var member =
//                        mRealm.where(Member::class.java).equalTo("id", playerNumId).findFirst()
//                    Log.d("kotlintest", "memberId:" + playerNumId.toString())

                    //Turnを新規登録
                    mTurn!!.playerNum = memberNum
                    mTurn!!.playerRound = playingNum
                    mTurn!!.game_id = game_id
                    mTurn!!.count = count
                    mTurn!!.round = round
                    mTurn!!.round_count = round_count
                    mTurn!!.player = "自分"
                    mTurn!!.player_id = player_id
                    if (playingCheck == "fold" && round == "preflop" && round_count == 1) {
                        when (playingNum) {
                            preFlopNum -> {
                                Log.d("kotlintest", "登録PA:SB")
                                mTurn!!.playChips = smallBlind
                                mTurn!!.memberChips = playerTotalChips - smallBlind
                                memberRealmData!!.playChips = smallBlind
                                memberRealmData!!.memberChips = playerTotalChips - smallBlind
                                tableTotalChips += smallBlind
                            }
                            bigBlindNum -> {
                                Log.d("kotlintest", "登録PA:BB")
                                mTurn!!.playChips = bigBlind
                                mTurn!!.memberChips = playerTotalChips - bigBlind
                                memberRealmData!!.playChips = bigBlind
                                memberRealmData!!.memberChips = playerTotalChips - bigBlind
                                tableTotalChips += bigBlind
                            }
                            else -> {
                                Log.d("kotlintest", "登録PA:else")
                                mTurn!!.playChips = 0
                                mTurn!!.memberChips = playerTotalChips
                                memberRealmData!!.playChips = 0
                                memberRealmData!!.memberChips = playerTotalChips

                            }
                        }
                    } else {
                        Log.d("kotlintest", "登録PA:play")
                        mTurn!!.playChips = playingChipsText.text.toString().toInt()
                        mTurn!!.memberChips =
                            playerTotalChips - playingChipsText.text.toString().toInt()
                        memberRealmData!!.playChips = playingChipsText.text.toString().toInt()
                        memberRealmData!!.memberChips =
                            playerTotalChips - playingChipsText.text.toString().toInt()
                    }
                    mTurn!!.tableChips = tableChips
                    mTurn!!.tableTotalChips = tableTotalChips

                    Log.d("kotlintest", "mTurn!!.game_id:" + mTurn!!.game_id.toString())
                    Log.d("kotlintest", "mTurn!!.count:" + mTurn!!.count.toString())
                    Log.d("kotlintest", "mTurn!!.round:" + mTurn!!.round)
                    Log.d("kotlintest", "mTurn!!.round_count:" + mTurn!!.round_count.toString())
                    Log.d("kotlintest", "mTurn!!.player:" + mTurn!!.player)
                    Log.d("kotlintest", "mTurn!!.player_id:" + mTurn!!.player_id)
                    Log.d("kotlintest", "mTurn!!.playChips:" + mTurn!!.playChips.toString())
                    Log.d("kotlintest", "mTurn!!.memberChips:" + mTurn!!.memberChips.toString())
                    Log.d("kotlintest", "mTurn!!.tableChips:" + mTurn!!.tableChips.toString())
                    Log.d(
                        "kotlintest",
                        "mTurn!!.tableTotalChips:" + mTurn!!.tableTotalChips.toString()
                    )


                    if (playingCheck == "fold") {
                        memberRealmData!!.playingCheck = "fold"
                        foldPlayer++

                    }


                    memberRealmData!!.tableChips = tableChips
                    memberRealmData!!.tableTotalChips = tableTotalChips

                    Log.d("kotlintest", "memberRealmData!!.playChips:" + memberRealmData!!.playChips.toString())
                    Log.d("kotlintest", "memberRealmData!!.memberChips:" + memberRealmData!!.memberChips.toString())
                    Log.d("kotlintest", "memberRealmData!!.tableChips:" + memberRealmData!!.tableChips.toString())
                    Log.d(
                        "kotlintest",
                        "memberRealmData!!.tableTotalChips:" + memberRealmData!!.tableTotalChips.toString()
                    )



                    mRealm.copyToRealmOrUpdate(mTurn!!)
                    mRealm.copyToRealmOrUpdate(mMember!!)
                    mRealm.commitTransaction()

                    allInNum = 0
                    for (i in 1..memberNum) {
                        val playerNumRealmResults =
                            mRealm.where(Member::class.java).equalTo("memberRound", i).findAll()
                        val playerNumId = playerNumRealmResults.max("id")!!.toInt()
                        val playerData =
                            mRealm.where(Member::class.java).equalTo("id", playerNumId)
                                .findFirst()
                        val playerChips = playerData!!.memberChips
                        if (playerChips == 0) {
                            allInNum++
                        }
//                        if (playingNum != i) {
//                            if (playerChips == 0) {
//                                sameChipsPlayer++
//                            }
//                        }
                        Log.d("kotlintest", "allInNum == memberNum - foldPlayer:" + allInNum.toString() + " == " + memberNum.toString() + " - " + foldPlayer.toString())
                    }

                    var playerAllIn = 0
                    for (i in 1..memberNum) {
                        val playerRealmResults = mRealm.where(Member::class.java).findAll()
                        val playerNumRealmResults =
                            mRealm.where(Member::class.java).equalTo("memberRound", i).findAll()
                        val playerNumId = playerNumRealmResults.max("id")!!.toInt()
                        val playerData =
                            mRealm.where(Member::class.java).equalTo("id", playerNumId)
                                .findFirst()
                        val playerChips = playerData!!.memberChips
                        val playerFoldCheck = playerData!!.playingCheck
                        if (playingNum != i) {
                            if (playerChips == 0 && playerFoldCheck == "play") {
                                playerAllIn++
                            }
                        }
                        Log.d("kotlintest", "player:" + playerData!!.memberName + " playerChips:" + playerChips.toString() + " playerFoldCheck:" + playerFoldCheck)
                    }

                    Log.d("kotlintest", "playerAllIn:" + playerAllIn.toString() + " == memberNum - 1:" + memberNum.toString())

                    if (allInNum == memberNum - foldPlayer) {
                        sameChipsPlayer = allInNum
                    }

                    if (foldPlayer == memberNum - 1) {
//                        count++
//                        if (flopNum == memberNum) {
//                            flopNum = 1
//                            playingNum = 1
//                        } else {
//                            flopNum++
//                            playingNum = flopNum
//                        }

//                        if (round == "preflop") {
//                            for (i in 1..memberNum) {
//                                val memberRealmResultsWinner =
//                                    mRealm.where(Member::class.java).equalTo("memberRound", i)
//                                        .findAll()
//                                val memberIdWinner = memberRealmResultsWinner.max("id")!!.toInt()
//                                val memberDataWinner =
//                                    mRealm.where(Member::class.java).equalTo("id", memberIdWinner)
//                                        .findFirst()
//                                val winnerPlayingCheck = memberDataWinner!!.playingCheck
//                                val winnerGame = memberDataWinner!!.game_id
//                                val winnerCount = memberDataWinner!!.hand_count
//
//                                if (winnerGame == game_id && winnerCount == count && winnerPlayingCheck != "fold") {
//                                    when (i) {
//                                        preFlopNum -> {
//                                            memberDataWinner!!.playChips = smallBlind
//                                            memberDataWinner!!.memberChips = playerTotalChips - smallBlind
//                                        }
//
//                                        bigBlindNum -> {
//                                            memberDataWinner!!.playChips = bigBlind
//                                            memberDataWinner!!.memberChips = playerTotalChips - bigBlind
//                                        }
//                                    }
//                                }
//                            }
//                        }

                        //WinnerCheckActivityへ移動
                        val intent = Intent(this@PlayingActivity, WinnerCheckActivity::class.java)
                        Log.d("kotlintest", "通過PA3")
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[count]:" + count.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[round]:" + round
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[round_count]:" + round_count.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[roundNum]:" + roundNum.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[myRound]:" + myRound.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[flopNum]:" + flopNum.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[preFlopNum]:" + preFlopNum.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[bigBlindNum]" + bigBlindNum.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[playingNum]:" + playingNum.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[foldPlayer]:" + foldPlayer.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[sameChipsPlayer]:" + sameChipsPlayer.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[tableChips]:" + tableChips.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips.toString()
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[tableChips]:" + tableChips
                        )
                        Log.d(
                            "kotlintest",
                            "PlayingActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips
                        )
                        Log.d("kotlintest", "PlayingActivity -> WinnerCheckActivity[btn]:" + btn.toString())

                        intent.putExtra("memberNum", memberNum)
                        intent.putExtra("game_id", game_id)
                        intent.putExtra("count", count)
                        intent.putExtra("round", round)
                        intent.putExtra("round_count", round_count)
                        intent.putExtra("roundNum", roundNum)
                        intent.putExtra("myRound", myRound)
                        intent.putExtra("cardHand1", cardHand1)
                        intent.putExtra("cardHand2", cardHand2)
                        intent.putExtra("cardCom1", cardCom1)
                        intent.putExtra("cardCom2", cardCom2)
                        intent.putExtra("cardCom3", cardCom3)
                        intent.putExtra("cardCom4", cardCom4)
                        intent.putExtra("cardCom5", cardCom5)
                        intent.putExtra("bigBlind", bigBlind)
                        intent.putExtra("smallBlind", smallBlind)
                        intent.putExtra("tableChips", tableChips)
                        intent.putExtra("tableTotalChips", tableTotalChips)
                        intent.putExtra("flopNum", flopNum)
                        intent.putExtra("preFlopNum", preFlopNum)
                        intent.putExtra("bigBlindNum", bigBlindNum)
                        intent.putExtra("BTN", btn)
                        intent.putExtra("playingNum", playingNum)
                        intent.putExtra("foldPlayer", foldPlayer)
                        intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                        intent.putExtra("firstRealm", firstRealm)
                        startActivity(intent)

                    } else {
                        if (sameChipsPlayer >= memberNum - foldPlayer) {
                            when (round) {
                                "preflop" -> round = "flop"
                                "flop" -> round = "turn"
                                "turn" -> round = "river"
                                "river" -> round = "showdown"
                            }

                            when {
                                playerAllIn == memberNum - 1 -> round = "showdown"
                                memberNum - foldPlayer == 2 && playerAllIn == 1 -> round = "showdown"
                                allInNum == sameChipsPlayer -> round = "showdown"
                            }

                            Log.d("kotlintest", "allInNum == sameChipsPlayer:" + allInNum.toString() + " == " + sameChipsPlayer.toString())

                            playingNum = preFlopNum
                            roundNum = 1
                            round_count = 1
                            tableChips = 0
                            sameChipsPlayer = 0

                            // CardActivityへ移動

                            val intent = Intent(this@PlayingActivity, CardActivity::class.java)
                            Log.d("kotlintest", "通過PA4")
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[count]:" + count.toString()
                            )
                            Log.d("kotlintest", "PlayingActivity -> CardActivity[round]:" + round)
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[round_count]:" + round_count.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[roundNum]:" + roundNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[myRound]:" + myRound.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[flopNum]:" + flopNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[preFlopNum]:" + preFlopNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[bigBlindNum]" + bigBlindNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[playingNum]:" + playingNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[foldPlayer]:" + foldPlayer.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[sameChipsPlayer]:" + sameChipsPlayer.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[tableChips]:" + tableChips.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[bigBlind]:" + bigBlind
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[smallBlind]:" + smallBlind
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[tableChips]:" + tableChips
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> CardActivity[tableTotalChips]:" + tableTotalChips
                            )
                            Log.d("kotlintest", "PlayingActivity -> CardActivity[btn]:" + btn.toString())

                            intent.putExtra("memberNum", memberNum)
                            intent.putExtra("game_id", game_id)
                            intent.putExtra("count", count)
                            intent.putExtra("round", round)
                            intent.putExtra("round_count", round_count)
                            intent.putExtra("roundNum", roundNum)
                            intent.putExtra("myRound", myRound)
                            intent.putExtra("cardHand1", cardHand1)
                            intent.putExtra("cardHand2", cardHand2)
                            intent.putExtra("cardCom1", cardCom1)
                            intent.putExtra("cardCom2", cardCom2)
                            intent.putExtra("cardCom3", cardCom3)
                            intent.putExtra("cardCom4", cardCom4)
                            intent.putExtra("cardCom5", cardCom5)
                            intent.putExtra("bigBlind", bigBlind)
                            intent.putExtra("smallBlind", smallBlind)
                            intent.putExtra("tableChips", tableChips)
                            intent.putExtra("tableTotalChips", tableTotalChips)
                            intent.putExtra("flopNum", flopNum)
                            intent.putExtra("preFlopNum", preFlopNum)
                            intent.putExtra("bigBlindNum", bigBlindNum)
                            intent.putExtra("BTN", btn)
                            intent.putExtra("playingNum", playingNum)
                            intent.putExtra("foldPlayer", foldPlayer)
                            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                            intent.putExtra("firstRealm", firstRealm)
                            startActivity(intent)


                        } else {

                            if (roundNum == memberNum) {
                                round_count++
                                roundNum = 1
                            } else {
                                roundNum++
                            }

                            if (playingNum == memberNum) {
                                playingNum = 1
                            } else {
                                playingNum++
                            }

                            // MemberPlayingActivityに移動

                            val intent =
                                Intent(this@PlayingActivity, MemberPlayingActivity::class.java)
                            Log.d("kotlintest", "通過PA5")
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[count]:" + count.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[round]:" + round
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[round_count]:" + round_count.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[roundNum]:" + roundNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[myRound]:" + myRound.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[flopNum]:" + flopNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[preFlopNum]:" + preFlopNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[bigBlindNum]" + bigBlindNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[playingNum]:" + playingNum.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[foldPlayer]:" + foldPlayer.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[sameChipsPlayer]:" + sameChipsPlayer.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips.toString()
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[bigBlind]:" + bigBlind
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[smallBlind]:" + smallBlind
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[tableChips]:" + tableChips
                            )
                            Log.d(
                                "kotlintest",
                                "PlayingActivity -> MemberPlayingActivity[tableTotalChips]:" + tableTotalChips
                            )
                            Log.d("kotlintest", "PlayingActivity -> MemberPlayingActivity[btn]:" + btn.toString())


                            intent.putExtra("memberNum", memberNum)
                            intent.putExtra("game_id", game_id)
                            intent.putExtra("count", count)
                            intent.putExtra("round", round)
                            intent.putExtra("round_count", round_count)
                            intent.putExtra("roundNum", roundNum)
                            intent.putExtra("myRound", myRound)
                            intent.putExtra("cardHand1", cardHand1)
                            intent.putExtra("cardHand2", cardHand2)
                            intent.putExtra("cardCom1", cardCom1)
                            intent.putExtra("cardCom2", cardCom2)
                            intent.putExtra("cardCom3", cardCom3)
                            intent.putExtra("cardCom4", cardCom4)
                            intent.putExtra("cardCom5", cardCom5)
                            intent.putExtra("bigBlind", bigBlind)
                            intent.putExtra("smallBlind", smallBlind)
                            intent.putExtra("tableChips", tableChips)
                            intent.putExtra("tableTotalChips", tableTotalChips)
                            intent.putExtra("flopNum", flopNum)
                            intent.putExtra("preFlopNum", preFlopNum)
                            intent.putExtra("bigBlindNum", bigBlindNum)
                            intent.putExtra("BTN", btn)
                            intent.putExtra("playingNum", playingNum)
                            intent.putExtra("foldPlayer", foldPlayer)
                            intent.putExtra("sameChipsPlayer", sameChipsPlayer)
                            intent.putExtra("firstRealm", firstRealm)
                            startActivity(intent)

                        }
                    }
                }
            }
        }
    }

    private fun cardSetting1() {
        when (cardHand1){
            "club1" -> playingHand1.setImageResource(R.drawable.club1)
            "club2" -> playingHand1.setImageResource(R.drawable.club2)
            "club3" -> playingHand1.setImageResource(R.drawable.club3)
            "club4" -> playingHand1.setImageResource(R.drawable.club4)
            "club5" -> playingHand1.setImageResource(R.drawable.club5)
            "club6" -> playingHand1.setImageResource(R.drawable.club6)
            "club7" -> playingHand1.setImageResource(R.drawable.club7)
            "club8" -> playingHand1.setImageResource(R.drawable.club8)
            "club9" -> playingHand1.setImageResource(R.drawable.club9)
            "club10" -> playingHand1.setImageResource(R.drawable.club10)
            "club11" -> playingHand1.setImageResource(R.drawable.club11)
            "club12" -> playingHand1.setImageResource(R.drawable.club12)
            "club13" -> playingHand1.setImageResource(R.drawable.club13)

            "diamond1" -> playingHand1.setImageResource(R.drawable.diamond1)
            "diamond2" -> playingHand1.setImageResource(R.drawable.diamond2)
            "diamond3" -> playingHand1.setImageResource(R.drawable.diamond3)
            "diamond4" -> playingHand1.setImageResource(R.drawable.diamond4)
            "diamond5" -> playingHand1.setImageResource(R.drawable.diamond5)
            "diamond6" -> playingHand1.setImageResource(R.drawable.diamond6)
            "diamond7" -> playingHand1.setImageResource(R.drawable.diamond7)
            "diamond8" -> playingHand1.setImageResource(R.drawable.diamond8)
            "diamond9" -> playingHand1.setImageResource(R.drawable.diamond9)
            "diamond10" -> playingHand1.setImageResource(R.drawable.diamond10)
            "diamond11" -> playingHand1.setImageResource(R.drawable.diamond11)
            "diamond12" -> playingHand1.setImageResource(R.drawable.diamond12)
            "diamond13" -> playingHand1.setImageResource(R.drawable.diamond13)

            "heart1" -> playingHand1.setImageResource(R.drawable.heart1)
            "heart2" -> playingHand1.setImageResource(R.drawable.heart2)
            "heart3" -> playingHand1.setImageResource(R.drawable.heart3)
            "heart4" -> playingHand1.setImageResource(R.drawable.heart4)
            "heart5" -> playingHand1.setImageResource(R.drawable.heart5)
            "heart6" -> playingHand1.setImageResource(R.drawable.heart6)
            "heart7" -> playingHand1.setImageResource(R.drawable.heart7)
            "heart8" -> playingHand1.setImageResource(R.drawable.heart8)
            "heart9" -> playingHand1.setImageResource(R.drawable.heart9)
            "heart10" -> playingHand1.setImageResource(R.drawable.heart10)
            "heart11" -> playingHand1.setImageResource(R.drawable.heart11)
            "heart12" -> playingHand1.setImageResource(R.drawable.heart12)
            "heart13" -> playingHand1.setImageResource(R.drawable.heart13)

            "spade1" -> playingHand1.setImageResource(R.drawable.spade1)
            "spade2" -> playingHand1.setImageResource(R.drawable.spade2)
            "spade3" -> playingHand1.setImageResource(R.drawable.spade3)
            "spade4" -> playingHand1.setImageResource(R.drawable.spade4)
            "spade5" -> playingHand1.setImageResource(R.drawable.spade5)
            "spade6" -> playingHand1.setImageResource(R.drawable.spade6)
            "spade7" -> playingHand1.setImageResource(R.drawable.spade7)
            "spade8" -> playingHand1.setImageResource(R.drawable.spade8)
            "spade9" -> playingHand1.setImageResource(R.drawable.spade9)
            "spade10" -> playingHand1.setImageResource(R.drawable.spade10)
            "spade11" -> playingHand1.setImageResource(R.drawable.spade11)
            "spade12" -> playingHand1.setImageResource(R.drawable.spade12)
            "spade13" -> playingHand1.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting2() {
        when(cardHand2) {
            "club1" -> playingHand2.setImageResource(R.drawable.club1)
            "club2" -> playingHand2.setImageResource(R.drawable.club2)
            "club3" -> playingHand2.setImageResource(R.drawable.club3)
            "club4" -> playingHand2.setImageResource(R.drawable.club4)
            "club5" -> playingHand2.setImageResource(R.drawable.club5)
            "club6" -> playingHand2.setImageResource(R.drawable.club6)
            "club7" -> playingHand2.setImageResource(R.drawable.club7)
            "club8" -> playingHand2.setImageResource(R.drawable.club8)
            "club9" -> playingHand2.setImageResource(R.drawable.club9)
            "club10" -> playingHand2.setImageResource(R.drawable.club10)
            "club11" -> playingHand2.setImageResource(R.drawable.club11)
            "club12" -> playingHand2.setImageResource(R.drawable.club12)
            "club13" -> playingHand2.setImageResource(R.drawable.club13)

            "diamond1" -> playingHand2.setImageResource(R.drawable.diamond1)
            "diamond2" -> playingHand2.setImageResource(R.drawable.diamond2)
            "diamond3" -> playingHand2.setImageResource(R.drawable.diamond3)
            "diamond4" -> playingHand2.setImageResource(R.drawable.diamond4)
            "diamond5" -> playingHand2.setImageResource(R.drawable.diamond5)
            "diamond6" -> playingHand2.setImageResource(R.drawable.diamond6)
            "diamond7" -> playingHand2.setImageResource(R.drawable.diamond7)
            "diamond8" -> playingHand2.setImageResource(R.drawable.diamond8)
            "diamond9" -> playingHand2.setImageResource(R.drawable.diamond9)
            "diamond10" -> playingHand2.setImageResource(R.drawable.diamond10)
            "diamond11" -> playingHand2.setImageResource(R.drawable.diamond11)
            "diamond12" -> playingHand2.setImageResource(R.drawable.diamond12)
            "diamond13" -> playingHand2.setImageResource(R.drawable.diamond13)

            "heart1" -> playingHand2.setImageResource(R.drawable.heart1)
            "heart2" -> playingHand2.setImageResource(R.drawable.heart2)
            "heart3" -> playingHand2.setImageResource(R.drawable.heart3)
            "heart4" -> playingHand2.setImageResource(R.drawable.heart4)
            "heart5" -> playingHand2.setImageResource(R.drawable.heart5)
            "heart6" -> playingHand2.setImageResource(R.drawable.heart6)
            "heart7" -> playingHand2.setImageResource(R.drawable.heart7)
            "heart8" -> playingHand2.setImageResource(R.drawable.heart8)
            "heart9" -> playingHand2.setImageResource(R.drawable.heart9)
            "heart10" -> playingHand2.setImageResource(R.drawable.heart10)
            "heart11" -> playingHand2.setImageResource(R.drawable.heart11)
            "heart12" -> playingHand2.setImageResource(R.drawable.heart12)
            "heart13" -> playingHand2.setImageResource(R.drawable.heart13)

            "spade1" -> playingHand2.setImageResource(R.drawable.spade1)
            "spade2" -> playingHand2.setImageResource(R.drawable.spade2)
            "spade3" -> playingHand2.setImageResource(R.drawable.spade3)
            "spade4" -> playingHand2.setImageResource(R.drawable.spade4)
            "spade5" -> playingHand2.setImageResource(R.drawable.spade5)
            "spade6" -> playingHand2.setImageResource(R.drawable.spade6)
            "spade7" -> playingHand2.setImageResource(R.drawable.spade7)
            "spade8" -> playingHand2.setImageResource(R.drawable.spade8)
            "spade9" -> playingHand2.setImageResource(R.drawable.spade9)
            "spade10" -> playingHand2.setImageResource(R.drawable.spade10)
            "spade11" -> playingHand2.setImageResource(R.drawable.spade11)
            "spade12" -> playingHand2.setImageResource(R.drawable.spade12)
            "spade13" -> playingHand2.setImageResource(R.drawable.spade13)
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }
}
