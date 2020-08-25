package com.tableplay.poker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import jp.playing.table.poker.Hand
import jp.playing.table.poker.Member
import jp.playing.table.poker.Turn
import kotlinx.android.synthetic.main.activity_show_down.*

class ShowDownActivity : AppCompatActivity() {

    private var mTurn: Turn? = null

    private var mHand: Hand? = null

    private var mMember: Member? = null

    private lateinit var mRealm: Realm

    private var cardSuit = ""

    private var cardNumber1 = ""

    private var cardNumber2 = ""

    private var playerHand1 = ""

    private var playerHand2 = ""

    private var handCard1Set = ""

    private var handCard2Set = ""

    private var cardSelect = "hand1"

    private var memberRound = 1 //入力しているプレーヤー

    private var hand1 = ""

    private var hand2 = ""

    private var onePairNum = 0

    private var finalHand = ""

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

    private var firstRealm = ""

    private var btn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_down)

        supportActionBar?.title = "Showdown"

        mRealm = Realm.getDefaultInstance()

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
        memberRound = intent.getIntExtra("memberRound", 1)
        firstRealm = intent.getStringExtra("firstRealm")

        cardSetting1()
        cardSetting2()
        cardSetting3()
        cardSetting4()
        cardSetting5()

        val memberRealmResults = mRealm.where(Member::class.java).findAll()
        val memberPlayerNumRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", memberRound).findAll()
        val playerNumId = memberPlayerNumRealmResults.max("id")!!.toInt()
        val memberData = mRealm.where(Member::class.java).equalTo("id", playerNumId).findFirst()
        val memberPlayingCheck = memberData!!.playingCheck
        val dataPlayerId = memberData!!.member_id
        val memberName = memberData!!.memberName

//        val turnRealmResults = mRealm.where(Turn::class.java).findAll()
//        val turnPlayerNumRealmResults = mRealm.where(Turn::class.java).equalTo("player", memberName).findAll()
//        val playerId = turnPlayerNumRealmResults.max("id")!!.toInt()
//        val turnData = mRealm.where(Turn::class.java).equalTo("id", playerId).findFirst()

//        val memberPlayingCheck = memberRealmResults[playerNumId]!!.playingCheck //foldしたかチェック
//        val dataPlayerId = memberRealmResults[playerNumId]!!.member_id //該当プレイヤーのid
//        val memberName = memberRealmResults[playerNumId]!!.memberName //該当プレイヤーの名前

        Log.d("kotlintest", "memberRound:" + memberRound.toString() + " playerNumId:" + playerNumId.toString() + " memberPlayingCheck:" + memberPlayingCheck + " memberName:" + memberName)

        if (memberPlayingCheck != "fold" && memberName != "自分") {
            //titleBerに名前を追加
            supportActionBar?.title = "Showdown" + "　プレイヤー：" + memberName
        } else {
            //次のプレイヤーに画面遷移
            //全員終わった時点でHandActivityへ
            if (myRound != memberRound) {
                mRealm.beginTransaction()
                mMember = Member()
                memberData!!.hand1 = ""
                memberData!!.hand2 = ""
                mRealm.copyToRealmOrUpdate(mMember!!)

//                mTurn = Turn()
//                turnData!!.hand1 = ""
//                turnData!!.hand2 = ""
//                mRealm.copyToRealmOrUpdate(mTurn)
                mRealm.commitTransaction()
            }
            memberRound++
            if (memberRound > memberNum) {
                val intent = Intent(this@ShowDownActivity, WinnerCheckActivity::class.java)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[count]:" + count.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[round]:" + round)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips)
                Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[btn]:" + btn.toString())

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
                intent.putExtra("memberRound", memberRound)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)
            } else {
                val intent = Intent(this@ShowDownActivity, ShowDownActivity::class.java)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[count]:" + count.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[round]:" + round)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[round_count]:" + round_count.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[roundNum]:" + roundNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[myRound]:" + myRound.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[flopNum]:" + flopNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[preFlopNum]:" + preFlopNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlindNum]" + bigBlindNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[playingNum]:" + playingNum.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[foldPlayer]:" + foldPlayer.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[smallBlind]:" + smallBlind.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlind]:" + bigBlind.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableChips]:" + tableChips.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableTotalChips]:" + tableTotalChips.toString())
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlind]:" + bigBlind)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[smallBlind]:" + smallBlind)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableChips]:" + tableChips)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableTotalChips]:" + tableTotalChips)
                Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[btn]:" + btn.toString())

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
                intent.putExtra("memberRound", memberRound)
                intent.putExtra("firstRealm", firstRealm)
                startActivity(intent)
            }
        }

        //UIの設定
        showDownCardHeart.setOnClickListener {
            cardSuit = "heart"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }

        }

        showDownCardSpade.setOnClickListener {
            cardSuit = "spade"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }

        }

        showDownCardClub.setOnClickListener {
            cardSuit = "club"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }

        }

        showDownCardDiamond.setOnClickListener {
            cardSuit = "diamond"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }

        }

        showDownNum1.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "1"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "1"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "1"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum2.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "2"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "2"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "2"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum3.setOnClickListener {
            if (cardNumber1 != "1") {
                cardNumber1 = "3"
            } else {
                if (cardNumber2 == "") {
                    cardNumber2 = "3"
                } else {
                    cardNumber2 = ""
                    cardNumber1 = "3"
                }
            }

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum4.setOnClickListener {
            cardNumber1 = "4"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum5.setOnClickListener {
            cardNumber1 = "5"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum6.setOnClickListener {
            cardNumber1 = "6"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum7.setOnClickListener {
            cardNumber1 = "7"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum8.setOnClickListener {
            cardNumber1 = "8"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum9.setOnClickListener {
            cardNumber1 = "9"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownNum0.setOnClickListener {
            if (cardNumber1 == "1") {
                cardNumber2 = "0"
            }

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    handSetting1()
                } else {
                    handSetting2()
                }
            }
        }

        showDownDaleteButton.setOnClickListener {
            cardSuit = ""
            cardNumber1 = ""
            cardNumber2 = ""
            playerHand1 = ""
            playerHand2 = ""

            showDownHandImageView1.setImageResource(R.drawable.card_back)
            showDownHandImageView2.setImageResource(R.drawable.card_back)

            cardSelect = "hand1"
            showDownDoneButton.text = "1枚目決定"

            handCard1Set = ""
            handCard2Set = ""
        }

        showDownDoneButton.setOnClickListener {
            //Hand1 or Hand2
            if (cardSelect == "hand1") {
                //Hand1
                //cardSelectをhand2に変更
                cardSelect = "hand2"
                //ShowDownDoneButton.textを２枚目決定に変更
                showDownDoneButton.text = "2枚目決定"
                //playerHand1を設定
                if (handCard1Set == "set") {
                    playerHand1 = cardSuit + cardNumber1 + cardNumber2
                } else {
                    playerHand1 = ""
                }

                cardSuit = ""
                cardNumber1 = ""
                cardNumber2 = ""

            } else {
                //Hand2
                if (cardSelect == "hand2") {
                    if (handCard2Set == "set") {
                        playerHand2 = cardSuit + cardNumber1 + cardNumber2
                    } else {
                        playerHand2 = ""
                    }
                    mRealm.beginTransaction()
                    mMember = Member()
//                    mTurn = Turn()

                    val memberIdRealmResults = mRealm.where(Member::class.java).equalTo("id", playerNumId).findAll()
                    Log.d("kotlintest", "memberId:" + playerNumId.toString())
                    val memberMaxId = memberIdRealmResults.max("id")!!.toInt()
                    val member = mRealm.where(Member::class.java).equalTo("id", memberMaxId).findFirst()


                    member!!.hand1 = playerHand1
                    member!!.hand2 = playerHand2

                    mRealm.copyToRealmOrUpdate(mMember!!)

//                    val turnIdRealmResults = mRealm.where(Turn::class.java).equalTo("id", playerId).findAll()
//                    Log.d("kotlintest", "turnId:" + playerId.toString())
//                    val turnMaxId = turnIdRealmResults.max("id")!!.toInt()
//                    val turn = mRealm.where(Member::class.java).equalTo("id", turnMaxId).findFirst()
//
//                    turn!!.hand1 = playerHand1
//                    turn!!.hand1 = playerHand2
//
//                    mRealm.copyToRealmOrUpdate(mTurn!!)

                    mRealm.commitTransaction()
                    memberRound++
                    if (memberRound < memberNum) {
                        //ShowDownActivityへ
                        val intent = Intent(this@ShowDownActivity, ShowDownActivity::class.java)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[count]:" + count.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[round]:" + round)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[round_count]:" + round_count.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[roundNum]:" + roundNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[myRound]:" + myRound.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[flopNum]:" + flopNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[preFlopNum]:" + preFlopNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlindNum]" + bigBlindNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[playingNum]:" + playingNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[foldPlayer]:" + foldPlayer.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[smallBlind]:" + smallBlind.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlind]:" + bigBlind.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableChips]:" + tableChips.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableTotalChips]:" + tableTotalChips.toString())
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[bigBlind]:" + bigBlind)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[smallBlind]:" + smallBlind)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableChips]:" + tableChips)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[tableTotalChips]:" + tableTotalChips)
                        Log.d("kotlintest", "ShowDownActivity -> ShowDownActivity[btn]:" + btn.toString())

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
                        intent.putExtra("memberRound", memberRound)
                        intent.putExtra("firstRealm", firstRealm)
                        startActivity(intent)

                    } else {

                        //WinnerCheckActivityへ
                        val intent = Intent(this@ShowDownActivity, WinnerCheckActivity::class.java)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[count]:" + count.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[round]:" + round)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[round_count]:" + round_count.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[roundNum]:" + roundNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[myRound]:" + myRound.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[flopNum]:" + flopNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[preFlopNum]:" + preFlopNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlindNum]" + bigBlindNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[playingNum]:" + playingNum.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[foldPlayer]:" + foldPlayer.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[sameChipsPlayer]:" + sameChipsPlayer.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableChips]:" + tableChips.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips.toString())
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[bigBlind]:" + bigBlind)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[smallBlind]:" + smallBlind)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableChips]:" + tableChips)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[tableTotalChips]:" + tableTotalChips)
                        Log.d("kotlintest", "ShowDownActivity -> WinnerCheckActivity[btn]:" + btn.toString())

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

    private fun cardSetting1() {
        when (cardCom1){
            "club1" -> showDownComImageView1.setImageResource(R.drawable.club1)
            "club2" -> showDownComImageView1.setImageResource(R.drawable.club2)
            "club3" -> showDownComImageView1.setImageResource(R.drawable.club3)
            "club4" -> showDownComImageView1.setImageResource(R.drawable.club4)
            "club5" -> showDownComImageView1.setImageResource(R.drawable.club5)
            "club6" -> showDownComImageView1.setImageResource(R.drawable.club6)
            "club7" -> showDownComImageView1.setImageResource(R.drawable.club7)
            "club8" -> showDownComImageView1.setImageResource(R.drawable.club8)
            "club9" -> showDownComImageView1.setImageResource(R.drawable.club9)
            "club10" -> showDownComImageView1.setImageResource(R.drawable.club10)
            "club11" -> showDownComImageView1.setImageResource(R.drawable.club11)
            "club12" -> showDownComImageView1.setImageResource(R.drawable.club12)
            "club13" -> showDownComImageView1.setImageResource(R.drawable.club13)

            "diamond1" -> showDownComImageView1.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownComImageView1.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownComImageView1.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownComImageView1.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownComImageView1.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownComImageView1.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownComImageView1.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownComImageView1.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownComImageView1.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownComImageView1.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownComImageView1.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownComImageView1.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownComImageView1.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownComImageView1.setImageResource(R.drawable.heart1)
            "heart2" -> showDownComImageView1.setImageResource(R.drawable.heart2)
            "heart3" -> showDownComImageView1.setImageResource(R.drawable.heart3)
            "heart4" -> showDownComImageView1.setImageResource(R.drawable.heart4)
            "heart5" -> showDownComImageView1.setImageResource(R.drawable.heart5)
            "heart6" -> showDownComImageView1.setImageResource(R.drawable.heart6)
            "heart7" -> showDownComImageView1.setImageResource(R.drawable.heart7)
            "heart8" -> showDownComImageView1.setImageResource(R.drawable.heart8)
            "heart9" -> showDownComImageView1.setImageResource(R.drawable.heart9)
            "heart10" -> showDownComImageView1.setImageResource(R.drawable.heart10)
            "heart11" -> showDownComImageView1.setImageResource(R.drawable.heart11)
            "heart12" -> showDownComImageView1.setImageResource(R.drawable.heart12)
            "heart13" -> showDownComImageView1.setImageResource(R.drawable.heart13)

            "spade1" -> showDownComImageView1.setImageResource(R.drawable.spade1)
            "spade2" -> showDownComImageView1.setImageResource(R.drawable.spade2)
            "spade3" -> showDownComImageView1.setImageResource(R.drawable.spade3)
            "spade4" -> showDownComImageView1.setImageResource(R.drawable.spade4)
            "spade5" -> showDownComImageView1.setImageResource(R.drawable.spade5)
            "spade6" -> showDownComImageView1.setImageResource(R.drawable.spade6)
            "spade7" -> showDownComImageView1.setImageResource(R.drawable.spade7)
            "spade8" -> showDownComImageView1.setImageResource(R.drawable.spade8)
            "spade9" -> showDownComImageView1.setImageResource(R.drawable.spade9)
            "spade10" -> showDownComImageView1.setImageResource(R.drawable.spade10)
            "spade11" -> showDownComImageView1.setImageResource(R.drawable.spade11)
            "spade12" -> showDownComImageView1.setImageResource(R.drawable.spade12)
            "spade13" -> showDownComImageView1.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting2() {
        when (cardCom2){
            "club1" -> showDownComImageView2.setImageResource(R.drawable.club1)
            "club2" -> showDownComImageView2.setImageResource(R.drawable.club2)
            "club3" -> showDownComImageView2.setImageResource(R.drawable.club3)
            "club4" -> showDownComImageView2.setImageResource(R.drawable.club4)
            "club5" -> showDownComImageView2.setImageResource(R.drawable.club5)
            "club6" -> showDownComImageView2.setImageResource(R.drawable.club6)
            "club7" -> showDownComImageView2.setImageResource(R.drawable.club7)
            "club8" -> showDownComImageView2.setImageResource(R.drawable.club8)
            "club9" -> showDownComImageView2.setImageResource(R.drawable.club9)
            "club10" -> showDownComImageView2.setImageResource(R.drawable.club10)
            "club11" -> showDownComImageView2.setImageResource(R.drawable.club11)
            "club12" -> showDownComImageView2.setImageResource(R.drawable.club12)
            "club13" -> showDownComImageView2.setImageResource(R.drawable.club13)

            "diamond1" -> showDownComImageView2.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownComImageView2.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownComImageView2.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownComImageView2.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownComImageView2.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownComImageView2.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownComImageView2.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownComImageView2.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownComImageView2.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownComImageView2.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownComImageView2.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownComImageView2.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownComImageView2.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownComImageView2.setImageResource(R.drawable.heart1)
            "heart2" -> showDownComImageView2.setImageResource(R.drawable.heart2)
            "heart3" -> showDownComImageView2.setImageResource(R.drawable.heart3)
            "heart4" -> showDownComImageView2.setImageResource(R.drawable.heart4)
            "heart5" -> showDownComImageView2.setImageResource(R.drawable.heart5)
            "heart6" -> showDownComImageView2.setImageResource(R.drawable.heart6)
            "heart7" -> showDownComImageView2.setImageResource(R.drawable.heart7)
            "heart8" -> showDownComImageView2.setImageResource(R.drawable.heart8)
            "heart9" -> showDownComImageView2.setImageResource(R.drawable.heart9)
            "heart10" -> showDownComImageView2.setImageResource(R.drawable.heart10)
            "heart11" -> showDownComImageView2.setImageResource(R.drawable.heart11)
            "heart12" -> showDownComImageView2.setImageResource(R.drawable.heart12)
            "heart13" -> showDownComImageView2.setImageResource(R.drawable.heart13)

            "spade1" -> showDownComImageView2.setImageResource(R.drawable.spade1)
            "spade2" -> showDownComImageView2.setImageResource(R.drawable.spade2)
            "spade3" -> showDownComImageView2.setImageResource(R.drawable.spade3)
            "spade4" -> showDownComImageView2.setImageResource(R.drawable.spade4)
            "spade5" -> showDownComImageView2.setImageResource(R.drawable.spade5)
            "spade6" -> showDownComImageView2.setImageResource(R.drawable.spade6)
            "spade7" -> showDownComImageView2.setImageResource(R.drawable.spade7)
            "spade8" -> showDownComImageView2.setImageResource(R.drawable.spade8)
            "spade9" -> showDownComImageView2.setImageResource(R.drawable.spade9)
            "spade10" -> showDownComImageView2.setImageResource(R.drawable.spade10)
            "spade11" -> showDownComImageView2.setImageResource(R.drawable.spade11)
            "spade12" -> showDownComImageView2.setImageResource(R.drawable.spade12)
            "spade13" -> showDownComImageView2.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting3() {
        when (cardCom3){
            "club1" -> showDownComImageView3.setImageResource(R.drawable.club1)
            "club2" -> showDownComImageView3.setImageResource(R.drawable.club2)
            "club3" -> showDownComImageView3.setImageResource(R.drawable.club3)
            "club4" -> showDownComImageView3.setImageResource(R.drawable.club4)
            "club5" -> showDownComImageView3.setImageResource(R.drawable.club5)
            "club6" -> showDownComImageView3.setImageResource(R.drawable.club6)
            "club7" -> showDownComImageView3.setImageResource(R.drawable.club7)
            "club8" -> showDownComImageView3.setImageResource(R.drawable.club8)
            "club9" -> showDownComImageView3.setImageResource(R.drawable.club9)
            "club10" -> showDownComImageView3.setImageResource(R.drawable.club10)
            "club11" -> showDownComImageView3.setImageResource(R.drawable.club11)
            "club12" -> showDownComImageView3.setImageResource(R.drawable.club12)
            "club13" -> showDownComImageView3.setImageResource(R.drawable.club13)

            "diamond1" -> showDownComImageView3.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownComImageView3.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownComImageView3.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownComImageView3.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownComImageView3.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownComImageView3.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownComImageView3.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownComImageView3.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownComImageView3.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownComImageView3.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownComImageView3.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownComImageView3.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownComImageView3.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownComImageView3.setImageResource(R.drawable.heart1)
            "heart2" -> showDownComImageView3.setImageResource(R.drawable.heart2)
            "heart3" -> showDownComImageView3.setImageResource(R.drawable.heart3)
            "heart4" -> showDownComImageView3.setImageResource(R.drawable.heart4)
            "heart5" -> showDownComImageView3.setImageResource(R.drawable.heart5)
            "heart6" -> showDownComImageView3.setImageResource(R.drawable.heart6)
            "heart7" -> showDownComImageView3.setImageResource(R.drawable.heart7)
            "heart8" -> showDownComImageView3.setImageResource(R.drawable.heart8)
            "heart9" -> showDownComImageView3.setImageResource(R.drawable.heart9)
            "heart10" -> showDownComImageView3.setImageResource(R.drawable.heart10)
            "heart11" -> showDownComImageView3.setImageResource(R.drawable.heart11)
            "heart12" -> showDownComImageView3.setImageResource(R.drawable.heart12)
            "heart13" -> showDownComImageView3.setImageResource(R.drawable.heart13)

            "spade1" -> showDownComImageView3.setImageResource(R.drawable.spade1)
            "spade2" -> showDownComImageView3.setImageResource(R.drawable.spade2)
            "spade3" -> showDownComImageView3.setImageResource(R.drawable.spade3)
            "spade4" -> showDownComImageView3.setImageResource(R.drawable.spade4)
            "spade5" -> showDownComImageView3.setImageResource(R.drawable.spade5)
            "spade6" -> showDownComImageView3.setImageResource(R.drawable.spade6)
            "spade7" -> showDownComImageView3.setImageResource(R.drawable.spade7)
            "spade8" -> showDownComImageView3.setImageResource(R.drawable.spade8)
            "spade9" -> showDownComImageView3.setImageResource(R.drawable.spade9)
            "spade10" -> showDownComImageView3.setImageResource(R.drawable.spade10)
            "spade11" -> showDownComImageView3.setImageResource(R.drawable.spade11)
            "spade12" -> showDownComImageView3.setImageResource(R.drawable.spade12)
            "spade13" -> showDownComImageView3.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting4() {
        when (cardCom4){
            "club1" -> showDownComImageView4.setImageResource(R.drawable.club1)
            "club2" -> showDownComImageView4.setImageResource(R.drawable.club2)
            "club3" -> showDownComImageView4.setImageResource(R.drawable.club3)
            "club4" -> showDownComImageView4.setImageResource(R.drawable.club4)
            "club5" -> showDownComImageView4.setImageResource(R.drawable.club5)
            "club6" -> showDownComImageView4.setImageResource(R.drawable.club6)
            "club7" -> showDownComImageView4.setImageResource(R.drawable.club7)
            "club8" -> showDownComImageView4.setImageResource(R.drawable.club8)
            "club9" -> showDownComImageView4.setImageResource(R.drawable.club9)
            "club10" -> showDownComImageView4.setImageResource(R.drawable.club10)
            "club11" -> showDownComImageView4.setImageResource(R.drawable.club11)
            "club12" -> showDownComImageView4.setImageResource(R.drawable.club12)
            "club13" -> showDownComImageView4.setImageResource(R.drawable.club13)

            "diamond1" -> showDownComImageView4.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownComImageView4.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownComImageView4.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownComImageView4.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownComImageView4.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownComImageView4.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownComImageView4.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownComImageView4.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownComImageView4.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownComImageView4.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownComImageView4.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownComImageView4.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownComImageView4.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownComImageView4.setImageResource(R.drawable.heart1)
            "heart2" -> showDownComImageView4.setImageResource(R.drawable.heart2)
            "heart3" -> showDownComImageView4.setImageResource(R.drawable.heart3)
            "heart4" -> showDownComImageView4.setImageResource(R.drawable.heart4)
            "heart5" -> showDownComImageView4.setImageResource(R.drawable.heart5)
            "heart6" -> showDownComImageView4.setImageResource(R.drawable.heart6)
            "heart7" -> showDownComImageView4.setImageResource(R.drawable.heart7)
            "heart8" -> showDownComImageView4.setImageResource(R.drawable.heart8)
            "heart9" -> showDownComImageView4.setImageResource(R.drawable.heart9)
            "heart10" -> showDownComImageView4.setImageResource(R.drawable.heart10)
            "heart11" -> showDownComImageView4.setImageResource(R.drawable.heart11)
            "heart12" -> showDownComImageView4.setImageResource(R.drawable.heart12)
            "heart13" -> showDownComImageView4.setImageResource(R.drawable.heart13)

            "spade1" -> showDownComImageView4.setImageResource(R.drawable.spade1)
            "spade2" -> showDownComImageView4.setImageResource(R.drawable.spade2)
            "spade3" -> showDownComImageView4.setImageResource(R.drawable.spade3)
            "spade4" -> showDownComImageView4.setImageResource(R.drawable.spade4)
            "spade5" -> showDownComImageView4.setImageResource(R.drawable.spade5)
            "spade6" -> showDownComImageView4.setImageResource(R.drawable.spade6)
            "spade7" -> showDownComImageView4.setImageResource(R.drawable.spade7)
            "spade8" -> showDownComImageView4.setImageResource(R.drawable.spade8)
            "spade9" -> showDownComImageView4.setImageResource(R.drawable.spade9)
            "spade10" -> showDownComImageView4.setImageResource(R.drawable.spade10)
            "spade11" -> showDownComImageView4.setImageResource(R.drawable.spade11)
            "spade12" -> showDownComImageView4.setImageResource(R.drawable.spade12)
            "spade13" -> showDownComImageView4.setImageResource(R.drawable.spade13)
        }
    }

    private fun cardSetting5() {
        when (cardCom5){
            "club1" -> showDownComImageView5.setImageResource(R.drawable.club1)
            "club2" -> showDownComImageView5.setImageResource(R.drawable.club2)
            "club3" -> showDownComImageView5.setImageResource(R.drawable.club3)
            "club4" -> showDownComImageView5.setImageResource(R.drawable.club4)
            "club5" -> showDownComImageView5.setImageResource(R.drawable.club5)
            "club6" -> showDownComImageView5.setImageResource(R.drawable.club6)
            "club7" -> showDownComImageView5.setImageResource(R.drawable.club7)
            "club8" -> showDownComImageView5.setImageResource(R.drawable.club8)
            "club9" -> showDownComImageView5.setImageResource(R.drawable.club9)
            "club10" -> showDownComImageView5.setImageResource(R.drawable.club10)
            "club11" -> showDownComImageView5.setImageResource(R.drawable.club11)
            "club12" -> showDownComImageView5.setImageResource(R.drawable.club12)
            "club13" -> showDownComImageView5.setImageResource(R.drawable.club13)

            "diamond1" -> showDownComImageView5.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownComImageView5.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownComImageView5.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownComImageView5.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownComImageView5.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownComImageView5.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownComImageView5.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownComImageView5.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownComImageView5.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownComImageView5.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownComImageView5.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownComImageView5.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownComImageView5.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownComImageView5.setImageResource(R.drawable.heart1)
            "heart2" -> showDownComImageView5.setImageResource(R.drawable.heart2)
            "heart3" -> showDownComImageView5.setImageResource(R.drawable.heart3)
            "heart4" -> showDownComImageView5.setImageResource(R.drawable.heart4)
            "heart5" -> showDownComImageView5.setImageResource(R.drawable.heart5)
            "heart6" -> showDownComImageView5.setImageResource(R.drawable.heart6)
            "heart7" -> showDownComImageView5.setImageResource(R.drawable.heart7)
            "heart8" -> showDownComImageView5.setImageResource(R.drawable.heart8)
            "heart9" -> showDownComImageView5.setImageResource(R.drawable.heart9)
            "heart10" -> showDownComImageView5.setImageResource(R.drawable.heart10)
            "heart11" -> showDownComImageView5.setImageResource(R.drawable.heart11)
            "heart12" -> showDownComImageView5.setImageResource(R.drawable.heart12)
            "heart13" -> showDownComImageView5.setImageResource(R.drawable.heart13)

            "spade1" -> showDownComImageView5.setImageResource(R.drawable.spade1)
            "spade2" -> showDownComImageView5.setImageResource(R.drawable.spade2)
            "spade3" -> showDownComImageView5.setImageResource(R.drawable.spade3)
            "spade4" -> showDownComImageView5.setImageResource(R.drawable.spade4)
            "spade5" -> showDownComImageView5.setImageResource(R.drawable.spade5)
            "spade6" -> showDownComImageView5.setImageResource(R.drawable.spade6)
            "spade7" -> showDownComImageView5.setImageResource(R.drawable.spade7)
            "spade8" -> showDownComImageView5.setImageResource(R.drawable.spade8)
            "spade9" -> showDownComImageView5.setImageResource(R.drawable.spade9)
            "spade10" -> showDownComImageView5.setImageResource(R.drawable.spade10)
            "spade11" -> showDownComImageView5.setImageResource(R.drawable.spade11)
            "spade12" -> showDownComImageView5.setImageResource(R.drawable.spade12)
            "spade13" -> showDownComImageView5.setImageResource(R.drawable.spade13)
        }
    }

    private fun handSetting1() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> showDownHandImageView1.setImageResource(R.drawable.club1)
            "club2" -> showDownHandImageView1.setImageResource(R.drawable.club2)
            "club3" -> showDownHandImageView1.setImageResource(R.drawable.club3)
            "club4" -> showDownHandImageView1.setImageResource(R.drawable.club4)
            "club5" -> showDownHandImageView1.setImageResource(R.drawable.club5)
            "club6" -> showDownHandImageView1.setImageResource(R.drawable.club6)
            "club7" -> showDownHandImageView1.setImageResource(R.drawable.club7)
            "club8" -> showDownHandImageView1.setImageResource(R.drawable.club8)
            "club9" -> showDownHandImageView1.setImageResource(R.drawable.club9)
            "club10" -> showDownHandImageView1.setImageResource(R.drawable.club10)
            "club11" -> showDownHandImageView1.setImageResource(R.drawable.club11)
            "club12" -> showDownHandImageView1.setImageResource(R.drawable.club12)
            "club13" -> showDownHandImageView1.setImageResource(R.drawable.club13)

            "diamond1" -> showDownHandImageView1.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownHandImageView1.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownHandImageView1.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownHandImageView1.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownHandImageView1.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownHandImageView1.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownHandImageView1.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownHandImageView1.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownHandImageView1.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownHandImageView1.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownHandImageView1.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownHandImageView1.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownHandImageView1.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownHandImageView1.setImageResource(R.drawable.heart1)
            "heart2" -> showDownHandImageView1.setImageResource(R.drawable.heart2)
            "heart3" -> showDownHandImageView1.setImageResource(R.drawable.heart3)
            "heart4" -> showDownHandImageView1.setImageResource(R.drawable.heart4)
            "heart5" -> showDownHandImageView1.setImageResource(R.drawable.heart5)
            "heart6" -> showDownHandImageView1.setImageResource(R.drawable.heart6)
            "heart7" -> showDownHandImageView1.setImageResource(R.drawable.heart7)
            "heart8" -> showDownHandImageView1.setImageResource(R.drawable.heart8)
            "heart9" -> showDownHandImageView1.setImageResource(R.drawable.heart9)
            "heart10" -> showDownHandImageView1.setImageResource(R.drawable.heart10)
            "heart11" -> showDownHandImageView1.setImageResource(R.drawable.heart11)
            "heart12" -> showDownHandImageView1.setImageResource(R.drawable.heart12)
            "heart13" -> showDownHandImageView1.setImageResource(R.drawable.heart13)

            "spade1" -> showDownHandImageView1.setImageResource(R.drawable.spade1)
            "spade2" -> showDownHandImageView1.setImageResource(R.drawable.spade2)
            "spade3" -> showDownHandImageView1.setImageResource(R.drawable.spade3)
            "spade4" -> showDownHandImageView1.setImageResource(R.drawable.spade4)
            "spade5" -> showDownHandImageView1.setImageResource(R.drawable.spade5)
            "spade6" -> showDownHandImageView1.setImageResource(R.drawable.spade6)
            "spade7" -> showDownHandImageView1.setImageResource(R.drawable.spade7)
            "spade8" -> showDownHandImageView1.setImageResource(R.drawable.spade8)
            "spade9" -> showDownHandImageView1.setImageResource(R.drawable.spade9)
            "spade10" -> showDownHandImageView1.setImageResource(R.drawable.spade10)
            "spade11" -> showDownHandImageView1.setImageResource(R.drawable.spade11)
            "spade12" -> showDownHandImageView1.setImageResource(R.drawable.spade12)
            "spade13" -> showDownHandImageView1.setImageResource(R.drawable.spade13)
        }
        handCard1Set = "set"
    }

    private fun handSetting2() {
        when (cardSuit + cardNumber1 + cardNumber2){
            "club1" -> showDownHandImageView2.setImageResource(R.drawable.club1)
            "club2" -> showDownHandImageView2.setImageResource(R.drawable.club2)
            "club3" -> showDownHandImageView2.setImageResource(R.drawable.club3)
            "club4" -> showDownHandImageView2.setImageResource(R.drawable.club4)
            "club5" -> showDownHandImageView2.setImageResource(R.drawable.club5)
            "club6" -> showDownHandImageView2.setImageResource(R.drawable.club6)
            "club7" -> showDownHandImageView2.setImageResource(R.drawable.club7)
            "club8" -> showDownHandImageView2.setImageResource(R.drawable.club8)
            "club9" -> showDownHandImageView2.setImageResource(R.drawable.club9)
            "club10" -> showDownHandImageView2.setImageResource(R.drawable.club10)
            "club11" -> showDownHandImageView2.setImageResource(R.drawable.club11)
            "club12" -> showDownHandImageView2.setImageResource(R.drawable.club12)
            "club13" -> showDownHandImageView2.setImageResource(R.drawable.club13)

            "diamond1" -> showDownHandImageView2.setImageResource(R.drawable.diamond1)
            "diamond2" -> showDownHandImageView2.setImageResource(R.drawable.diamond2)
            "diamond3" -> showDownHandImageView2.setImageResource(R.drawable.diamond3)
            "diamond4" -> showDownHandImageView2.setImageResource(R.drawable.diamond4)
            "diamond5" -> showDownHandImageView2.setImageResource(R.drawable.diamond5)
            "diamond6" -> showDownHandImageView2.setImageResource(R.drawable.diamond6)
            "diamond7" -> showDownHandImageView2.setImageResource(R.drawable.diamond7)
            "diamond8" -> showDownHandImageView2.setImageResource(R.drawable.diamond8)
            "diamond9" -> showDownHandImageView2.setImageResource(R.drawable.diamond9)
            "diamond10" -> showDownHandImageView2.setImageResource(R.drawable.diamond10)
            "diamond11" -> showDownHandImageView2.setImageResource(R.drawable.diamond11)
            "diamond12" -> showDownHandImageView2.setImageResource(R.drawable.diamond12)
            "diamond13" -> showDownHandImageView2.setImageResource(R.drawable.diamond13)

            "heart1" -> showDownHandImageView2.setImageResource(R.drawable.heart1)
            "heart2" -> showDownHandImageView2.setImageResource(R.drawable.heart2)
            "heart3" -> showDownHandImageView2.setImageResource(R.drawable.heart3)
            "heart4" -> showDownHandImageView2.setImageResource(R.drawable.heart4)
            "heart5" -> showDownHandImageView2.setImageResource(R.drawable.heart5)
            "heart6" -> showDownHandImageView2.setImageResource(R.drawable.heart6)
            "heart7" -> showDownHandImageView2.setImageResource(R.drawable.heart7)
            "heart8" -> showDownHandImageView2.setImageResource(R.drawable.heart8)
            "heart9" -> showDownHandImageView2.setImageResource(R.drawable.heart9)
            "heart10" -> showDownHandImageView2.setImageResource(R.drawable.heart10)
            "heart11" -> showDownHandImageView2.setImageResource(R.drawable.heart11)
            "heart12" -> showDownHandImageView2.setImageResource(R.drawable.heart12)
            "heart13" -> showDownHandImageView2.setImageResource(R.drawable.heart13)

            "spade1" -> showDownHandImageView2.setImageResource(R.drawable.spade1)
            "spade2" -> showDownHandImageView2.setImageResource(R.drawable.spade2)
            "spade3" -> showDownHandImageView2.setImageResource(R.drawable.spade3)
            "spade4" -> showDownHandImageView2.setImageResource(R.drawable.spade4)
            "spade5" -> showDownHandImageView2.setImageResource(R.drawable.spade5)
            "spade6" -> showDownHandImageView2.setImageResource(R.drawable.spade6)
            "spade7" -> showDownHandImageView2.setImageResource(R.drawable.spade7)
            "spade8" -> showDownHandImageView2.setImageResource(R.drawable.spade8)
            "spade9" -> showDownHandImageView2.setImageResource(R.drawable.spade9)
            "spade10" -> showDownHandImageView2.setImageResource(R.drawable.spade10)
            "spade11" -> showDownHandImageView2.setImageResource(R.drawable.spade11)
            "spade12" -> showDownHandImageView2.setImageResource(R.drawable.spade12)
            "spade13" -> showDownHandImageView2.setImageResource(R.drawable.spade13)
        }
        handCard2Set = "set"
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }

}
