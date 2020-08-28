package com.tableplay.poker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.realm.Realm
import jp.playing.table.poker.Game
import jp.playing.table.poker.Hand
import jp.playing.table.poker.Member
import jp.playing.table.poker.Turn
import kotlinx.android.synthetic.main.activity_hand.*

class HandActivity : AppCompatActivity() {

    private var mHand: Hand? = null

    private var mMember: Member? = null

    private lateinit var mRealm: Realm

    //チップ一桁目
    private var firstNum:Int = 1

    //チップ2桁目（0）
    private var secondNum:String = ""

    private var smallFirstNum: Int = 1

    private var smallSecondNum:String = ""

    //スート
    private var cardSuit = ""

    //カード1桁目
    private var cardNumber1 = ""

    //カード2桁目
    private var cardNumber2 = ""

    //ハンド選択
    private var cardSelect = "hand1"

    private var playerHand1 = ""

    private var playerHand2 = ""

    private var flopNum = 0

    private var preFlopNum = 0

    private var bigBlindNum = 0

    private var playingNum = 0

    private var count = 0

    private var bigBlind = 0

    private var smallBlind = 0

    private var myRound = 0

    private var roundPlayer = ""

    private var handCard1Set = ""

    private var handCard2Set = ""

    private var firstRealm = ""

    private var btn = 0

    private var sb = 0

    private var bb = 0

    private var foldPlayer = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hand)

        supportActionBar?.title = "Hand"

        //Realmの設定
        mRealm = Realm.getDefaultInstance()

        //インテントを取得する
        val intent = intent

        val memberNum = intent.getIntExtra("memberNum", 0)
        flopNum = intent.getIntExtra("flopNum", 0)
        playingNum = intent.getIntExtra("playingNum", 0)
        count = intent.getIntExtra("count", 0)
        bigBlind = intent.getIntExtra("bigBlind", 0)
        smallBlind = intent.getIntExtra("smallBlind", 0)
        myRound = intent.getIntExtra("myRound", myRound)
        firstRealm = intent.getStringExtra("firstRealm")
        btn = intent.getIntExtra("BTN", 0)
        sb = intent.getIntExtra("SB", 0)
        bb = intent.getIntExtra("BB", 0)
        foldPlayer = intent.getIntExtra("foldPlayer", 0)

        if (bigBlind == 0) {
            handChipsText.setText(firstNum.toString())
        } else {
            handChipsText.setText(bigBlind.toString())
        }

        if (smallBlind == 0) {
            smallChipsText.setText(smallFirstNum.toString())
        } else {
            smallChipsText.setText(smallBlind.toString())
        }


        //ボタンのリスナー
        //終了ボタンのリスナー
        handEndButton.setOnClickListener {
            val intent = Intent(this@HandActivity, MainActivity::class.java)
            startActivity(intent)
        }

        //チップ上ボタン
        handUpButton.setOnClickListener {
            if (firstNum != 9) {
                firstNum++
            } else {
                firstNum = 1
            }

            if (secondNum == "") {
                handChipsText.setText(firstNum.toString())
            } else {
                handChipsText.setText(firstNum.toString() + secondNum)
            }
        }

        smallUpButton.setOnClickListener {
            if (smallFirstNum != 9) {
                smallFirstNum++
            } else {
                smallFirstNum = 1
            }

            if (smallSecondNum == "") {
                smallChipsText.setText(smallFirstNum.toString())
            } else {
                smallChipsText.setText(smallFirstNum.toString() + smallSecondNum)
            }
        }

        //チップ下ボタン
        handDownButton.setOnClickListener {
            if (firstNum != 1) {
                firstNum--
            } else {
                firstNum = 9
            }

            if (secondNum == "") {
                handChipsText.setText(firstNum.toString())
            } else {
                handChipsText.setText(firstNum.toString() + secondNum)
            }
        }

        smallDownButton.setOnClickListener {
            if (smallFirstNum != 1) {
                smallFirstNum--
            } else {
                smallFirstNum = 9
            }

            if (smallSecondNum == "") {
                smallChipsText.setText(smallFirstNum.toString())
            } else {
                smallChipsText.setText(smallFirstNum.toString() + smallSecondNum)
            }
        }

        //チップ右ボタン
        handRightButton.setOnClickListener {
            when(secondNum) {
                "" -> secondNum = "0"
                "0" -> secondNum = "00"
                "00" -> secondNum = "000"
                "000" -> secondNum = "0000"
                "0000" -> secondNum = "00000"
                "00000" -> secondNum = "000000"
                "000000" -> secondNum = "0000000"
                "0000000" -> secondNum = ""
            }

            if (secondNum == "") {
                handChipsText.setText(firstNum.toString())
            } else {
                handChipsText.setText(firstNum.toString() + secondNum)
            }
        }

        smallRightButton.setOnClickListener {
            when(smallSecondNum) {
                "" -> smallSecondNum = "0"
                "0" -> smallSecondNum = "00"
                "00" -> smallSecondNum = "000"
                "000" -> smallSecondNum = "0000"
                "0000" -> smallSecondNum = "00000"
                "00000" -> smallSecondNum = "000000"
                "000000" -> smallSecondNum = "0000000"
                "0000000" -> smallSecondNum = ""
            }

            if (smallSecondNum == "") {
                smallChipsText.setText(smallFirstNum.toString())
            } else {
                smallChipsText.setText(smallFirstNum.toString() + smallSecondNum)
            }
        }

        //チップ左ボタン
        handLeftButton.setOnClickListener {
            when(secondNum) {
                "" -> secondNum = "0000000"
                "0" -> secondNum = ""
                "00" -> secondNum = "0"
                "000" -> secondNum = "00"
                "0000" -> secondNum = "000"
                "00000" -> secondNum = "0000"
                "000000" -> secondNum = "00000"
                "0000000" -> secondNum = "000000"
            }

            if (secondNum == "") {
                handChipsText.setText(firstNum.toString())
            } else {
                handChipsText.setText(firstNum.toString() + secondNum)
            }
        }

        smallLeftButton.setOnClickListener {
            when(smallSecondNum) {
                "" -> smallSecondNum = "0000000"
                "0" -> smallSecondNum = ""
                "00" -> smallSecondNum = "0"
                "000" -> smallSecondNum = "00"
                "0000" -> smallSecondNum = "000"
                "00000" -> smallSecondNum = "0000"
                "000000" -> smallSecondNum = "00000"
                "0000000" -> smallSecondNum = "000000"
            }

            if (secondNum == "") {
                smallChipsText.setText(smallFirstNum.toString())
            } else {
                smallChipsText.setText(smallFirstNum.toString() + smallSecondNum)
            }
        }

        //ハートボタン
        handCardHeart.setOnClickListener {
            cardSuit = "heart"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //スペードボタン
        handCardSpade.setOnClickListener {
            cardSuit = "spade"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //クラブボタン
        handCardClub.setOnClickListener {
            cardSuit = "club"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //ダイヤボタン
        handCardDiamond.setOnClickListener {
            cardSuit = "diamond"

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //1ボタン
        handNum1.setOnClickListener {
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
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //2ボタン
        handNum2.setOnClickListener {
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
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //3ボタン
        handNum3.setOnClickListener {
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
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //4ボタン
        handNum4.setOnClickListener {
            cardNumber1 = "4"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //5ボタン
        handNum5.setOnClickListener {
            cardNumber1 = "5"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //6ボタン
        handNum6.setOnClickListener {
            cardNumber1 = "6"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //7ボタン
        handNum7.setOnClickListener {
            cardNumber1 = "7"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //8ボタン
        handNum8.setOnClickListener {
            cardNumber1 = "8"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //9ボタン
        handNum9.setOnClickListener {
            cardNumber1 = "9"
            cardNumber2 = ""

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //0ボタン
        handNum0.setOnClickListener {
            if (cardNumber1 == "1") {
                cardNumber2 = "0"
            }

            if (cardSuit != "" && cardNumber1 != "") {
                if (cardSelect == "hand1") {
                    cardSetting1()
                } else {
                    cardSetting2()
                }
            }
        }

        //デリートボタン
        handDaleteButton.setOnClickListener {
            cardSuit = ""
            cardNumber1 = ""
            cardNumber2 = ""
            playerHand1 = ""
            playerHand2 = ""

            handCard1.setImageResource(R.drawable.card_back)
            handCard2.setImageResource(R.drawable.card_back)

            cardSelect = "hand1"
            handDoneButton.text = "1枚目決定"

            handCard1Set = ""
            handCard2Set = ""
        }

        //決定ボタン
        handDoneButton.setOnClickListener {
            if (cardSelect == "hand1") {
                if (handCard1Set == "set") {
                    cardSelect = "hand2"
                    handDoneButton.text = "2枚目決定"
                    playerHand1 = cardSuit + cardNumber1 + cardNumber2

                    cardSuit = ""
                    cardNumber1 = ""
                    cardNumber2 = ""
                }
            } else {
                if (handCard2Set == "set") {

                    val gameRealmResults = mRealm.where(Game::class.java).findAll()

                    val game_id = gameRealmResults.max("id")!!.toInt()

                    if (cardSelect == "hand2") {
                        playerHand2 = cardSuit + cardNumber1 + cardNumber2
                        mRealm.beginTransaction()
                        if (mHand == null) {
                            mHand = Hand()

                            val handRealmResults = mRealm.where(Hand::class.java).findAll()

                            val identifier: Int =
                                if (handRealmResults.max("id") != null) {
                                    handRealmResults.max("id")!!.toInt() + 1
                                } else {
                                    1
                                }
                            mHand!!.id = identifier

                            mHand!!.count = count

                        }

                        mHand!!.game_id = game_id
                        mHand!!.hand1 = playerHand1
                        mHand!!.hand2 = playerHand2
                        mHand!!.bigBlind = handChipsText.text.toString().toInt()
                        mHand!!.smallBlind = smallChipsText.text.toString().toInt()

                        mMember = Member()

                        for (i in 1..memberNum) {
                            val memberRealmResults = mRealm.where(Member::class.java).equalTo("memberRound", i).findAll()
                            val memberId = memberRealmResults.max("id")!!.toInt()
                            val memberData = mRealm.where(Member::class.java).equalTo("id", memberId).findFirst()
                            val memberName = memberData!!.memberName
                            val member_id = memberData!!.member_id
                            val memberGameId = memberData!!.game_id
                            val memberPlayingCheck = memberData!!.playingCheck
                            val memberChips = memberData!!.memberChips

                            val memberRealm = mRealm.where(Member::class.java).findAll()

                            val identifier: Int =
                                if (memberRealm.max("id") != null) {
                                    memberRealm.max("id")!!.toInt() + 1
                                } else {
                                    0
                                }

                            mMember!!.id = identifier
                            mMember!!.memberName = memberName
                            mMember!!.member_id = member_id
                            mMember!!.memberRound = i
                            mMember!!.game_id = memberGameId
                            mMember!!.hand_count = count
                            mMember!!.memberChips = memberChips
                            mMember!!.playingCheck = memberPlayingCheck
                            mMember!!.memberNum = memberNum
                            if (myRound == i) {
                                mMember!!.hand1 = playerHand1
                                mMember!!.hand2 = playerHand2
                            }

                            mRealm.copyToRealmOrUpdate(mMember!!)
                        }

                        mRealm.copyToRealmOrUpdate(mHand!!)
                        mRealm.commitTransaction()


                        if (myRound == flopNum) {
                            roundPlayer = "you"
                        } else {
                            roundPlayer = "other"
                        }

                        playingNum = flopNum


                        when (roundPlayer) {
                            "you" -> {
                                val intent = Intent(this@HandActivity, PlayingActivity::class.java)


                                intent.putExtra("memberNum", memberNum.toInt())
                                intent.putExtra("game_id", game_id.toInt())
                                intent.putExtra("count", count)
                                intent.putExtra("round", "preflop")
                                intent.putExtra("round_count", 1)
                                intent.putExtra("roundNum", 1) // 一周の間の何人目か
                                intent.putExtra("myRound", myRound)
                                intent.putExtra("cardHand1", playerHand1)
                                intent.putExtra("cardHand2", playerHand2)
                                intent.putExtra("cardCom1", "")
                                intent.putExtra("cardCom2", "")
                                intent.putExtra("cardCom3", "")
                                intent.putExtra("cardCom4", "")
                                intent.putExtra("cardCom5", "")
                                intent.putExtra("bigBlind", handChipsText.text.toString().toInt())
                                intent.putExtra("smallBlind", smallChipsText.text.toString().toInt())
                                intent.putExtra("tableChips", handChipsText.text.toString().toInt())
                                intent.putExtra("tableTotalChips", 0)
                                intent.putExtra("flopNum", flopNum)
                                intent.putExtra("preFlopNum", sb)
                                intent.putExtra("bigBlindNum", bb)
                                intent.putExtra("BTN", btn)
                                intent.putExtra("playingNum", playingNum)
                                intent.putExtra("foldPlayer", foldPlayer)
                                intent.putExtra("sameChipsPlayer", 0)
                                intent.putExtra("firstRealm", firstRealm)
                                startActivity(intent)
                            }

                            "other" -> {
                                val intent =
                                    Intent(this@HandActivity, MemberPlayingActivity::class.java)

                                intent.putExtra("memberNum", memberNum.toInt())
                                intent.putExtra("game_id", game_id.toInt())
                                intent.putExtra("count", count)
                                intent.putExtra("round", "preflop")
                                intent.putExtra("round_count", 1)
                                intent.putExtra("roundNum", 1)
                                intent.putExtra("myRound", myRound)
                                intent.putExtra("cardHand1", playerHand1)
                                intent.putExtra("cardHand2", playerHand2)
                                intent.putExtra("cardCom1", "")
                                intent.putExtra("cardCom2", "")
                                intent.putExtra("cardCom3", "")
                                intent.putExtra("cardCom4", "")
                                intent.putExtra("cardCom5", "")
                                intent.putExtra("bigBlind", handChipsText.text.toString().toInt())
                                intent.putExtra("smallBlind", smallChipsText.text.toString().toInt())
                                intent.putExtra("tableChips", handChipsText.text.toString().toInt())
                                intent.putExtra("tableTotalChips", 0)
                                intent.putExtra("flopNum", flopNum)
                                intent.putExtra("preFlopNum", sb)
                                intent.putExtra("bigBlindNum", bb)
                                intent.putExtra("BTN", btn)
                                intent.putExtra("playingNum", playingNum)
                                intent.putExtra("foldPlayer", foldPlayer)
                                intent.putExtra("sameChipsPlayer", 0)
                                intent.putExtra("firstRealm", firstRealm)
                                startActivity(intent)
                            }
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mRealm.close()
    }

    private fun cardSetting1() {
        when(cardSuit + cardNumber1 + cardNumber2) {
            "club1" -> handCard1.setImageResource(R.drawable.club1)
            "club2" -> handCard1.setImageResource(R.drawable.club2)
            "club3" -> handCard1.setImageResource(R.drawable.club3)
            "club4" -> handCard1.setImageResource(R.drawable.club4)
            "club5" -> handCard1.setImageResource(R.drawable.club5)
            "club6" -> handCard1.setImageResource(R.drawable.club6)
            "club7" -> handCard1.setImageResource(R.drawable.club7)
            "club8" -> handCard1.setImageResource(R.drawable.club8)
            "club9" -> handCard1.setImageResource(R.drawable.club9)
            "club10" -> handCard1.setImageResource(R.drawable.club10)
            "club11" -> handCard1.setImageResource(R.drawable.club11)
            "club12" -> handCard1.setImageResource(R.drawable.club12)
            "club13" -> handCard1.setImageResource(R.drawable.club13)

            "diamond1" -> handCard1.setImageResource(R.drawable.diamond1)
            "diamond2" -> handCard1.setImageResource(R.drawable.diamond2)
            "diamond3" -> handCard1.setImageResource(R.drawable.diamond3)
            "diamond4" -> handCard1.setImageResource(R.drawable.diamond4)
            "diamond5" -> handCard1.setImageResource(R.drawable.diamond5)
            "diamond6" -> handCard1.setImageResource(R.drawable.diamond6)
            "diamond7" -> handCard1.setImageResource(R.drawable.diamond7)
            "diamond8" -> handCard1.setImageResource(R.drawable.diamond8)
            "diamond9" -> handCard1.setImageResource(R.drawable.diamond9)
            "diamond10" -> handCard1.setImageResource(R.drawable.diamond10)
            "diamond11" -> handCard1.setImageResource(R.drawable.diamond11)
            "diamond12" -> handCard1.setImageResource(R.drawable.diamond12)
            "diamond13" -> handCard1.setImageResource(R.drawable.diamond13)

            "heart1" -> handCard1.setImageResource(R.drawable.heart1)
            "heart2" -> handCard1.setImageResource(R.drawable.heart2)
            "heart3" -> handCard1.setImageResource(R.drawable.heart3)
            "heart4" -> handCard1.setImageResource(R.drawable.heart4)
            "heart5" -> handCard1.setImageResource(R.drawable.heart5)
            "heart6" -> handCard1.setImageResource(R.drawable.heart6)
            "heart7" -> handCard1.setImageResource(R.drawable.heart7)
            "heart8" -> handCard1.setImageResource(R.drawable.heart8)
            "heart9" -> handCard1.setImageResource(R.drawable.heart9)
            "heart10" -> handCard1.setImageResource(R.drawable.heart10)
            "heart11" -> handCard1.setImageResource(R.drawable.heart11)
            "heart12" -> handCard1.setImageResource(R.drawable.heart12)
            "heart13" -> handCard1.setImageResource(R.drawable.heart13)

            "spade1" -> handCard1.setImageResource(R.drawable.spade1)
            "spade2" -> handCard1.setImageResource(R.drawable.spade2)
            "spade3" -> handCard1.setImageResource(R.drawable.spade3)
            "spade4" -> handCard1.setImageResource(R.drawable.spade4)
            "spade5" -> handCard1.setImageResource(R.drawable.spade5)
            "spade6" -> handCard1.setImageResource(R.drawable.spade6)
            "spade7" -> handCard1.setImageResource(R.drawable.spade7)
            "spade8" -> handCard1.setImageResource(R.drawable.spade8)
            "spade9" -> handCard1.setImageResource(R.drawable.spade9)
            "spade10" -> handCard1.setImageResource(R.drawable.spade10)
            "spade11" -> handCard1.setImageResource(R.drawable.spade11)
            "spade12" -> handCard1.setImageResource(R.drawable.spade12)
            "spade13" -> handCard1.setImageResource(R.drawable.spade13)

        }
        handCard1Set = "set"
    }

    private fun cardSetting2() {
        when(cardSuit + cardNumber1 + cardNumber2) {
            "club1" -> handCard2.setImageResource(R.drawable.club1)
            "club2" -> handCard2.setImageResource(R.drawable.club2)
            "club3" -> handCard2.setImageResource(R.drawable.club3)
            "club4" -> handCard2.setImageResource(R.drawable.club4)
            "club5" -> handCard2.setImageResource(R.drawable.club5)
            "club6" -> handCard2.setImageResource(R.drawable.club6)
            "club7" -> handCard2.setImageResource(R.drawable.club7)
            "club8" -> handCard2.setImageResource(R.drawable.club8)
            "club9" -> handCard2.setImageResource(R.drawable.club9)
            "club10" -> handCard2.setImageResource(R.drawable.club10)
            "club11" -> handCard2.setImageResource(R.drawable.club11)
            "club12" -> handCard2.setImageResource(R.drawable.club12)
            "club13" -> handCard2.setImageResource(R.drawable.club13)

            "diamond1" -> handCard2.setImageResource(R.drawable.diamond1)
            "diamond2" -> handCard2.setImageResource(R.drawable.diamond2)
            "diamond3" -> handCard2.setImageResource(R.drawable.diamond3)
            "diamond4" -> handCard2.setImageResource(R.drawable.diamond4)
            "diamond5" -> handCard2.setImageResource(R.drawable.diamond5)
            "diamond6" -> handCard2.setImageResource(R.drawable.diamond6)
            "diamond7" -> handCard2.setImageResource(R.drawable.diamond7)
            "diamond8" -> handCard2.setImageResource(R.drawable.diamond8)
            "diamond9" -> handCard2.setImageResource(R.drawable.diamond9)
            "diamond10" -> handCard2.setImageResource(R.drawable.diamond10)
            "diamond11" -> handCard2.setImageResource(R.drawable.diamond11)
            "diamond12" -> handCard2.setImageResource(R.drawable.diamond12)
            "diamond13" -> handCard2.setImageResource(R.drawable.diamond13)

            "heart1" -> handCard2.setImageResource(R.drawable.heart1)
            "heart2" -> handCard2.setImageResource(R.drawable.heart2)
            "heart3" -> handCard2.setImageResource(R.drawable.heart3)
            "heart4" -> handCard2.setImageResource(R.drawable.heart4)
            "heart5" -> handCard2.setImageResource(R.drawable.heart5)
            "heart6" -> handCard2.setImageResource(R.drawable.heart6)
            "heart7" -> handCard2.setImageResource(R.drawable.heart7)
            "heart8" -> handCard2.setImageResource(R.drawable.heart8)
            "heart9" -> handCard2.setImageResource(R.drawable.heart9)
            "heart10" -> handCard2.setImageResource(R.drawable.heart10)
            "heart11" -> handCard2.setImageResource(R.drawable.heart11)
            "heart12" -> handCard2.setImageResource(R.drawable.heart12)
            "heart13" -> handCard2.setImageResource(R.drawable.heart13)

            "spade1" -> handCard2.setImageResource(R.drawable.spade1)
            "spade2" -> handCard2.setImageResource(R.drawable.spade2)
            "spade3" -> handCard2.setImageResource(R.drawable.spade3)
            "spade4" -> handCard2.setImageResource(R.drawable.spade4)
            "spade5" -> handCard2.setImageResource(R.drawable.spade5)
            "spade6" -> handCard2.setImageResource(R.drawable.spade6)
            "spade7" -> handCard2.setImageResource(R.drawable.spade7)
            "spade8" -> handCard2.setImageResource(R.drawable.spade8)
            "spade9" -> handCard2.setImageResource(R.drawable.spade9)
            "spade10" -> handCard2.setImageResource(R.drawable.spade10)
            "spade11" -> handCard2.setImageResource(R.drawable.spade11)
            "spade12" -> handCard2.setImageResource(R.drawable.spade12)
            "spade13" -> handCard2.setImageResource(R.drawable.spade13)

        }
        handCard2Set = "set"
    }
}
