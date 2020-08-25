package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Member: RealmObject(), Serializable {
    var memberName = "" // 参加中のメンバーの名前
    var memberNum = 0
    var member_id = ""// 参加中のメンバーのid
    var memberRound = 0 //参加中のメンバーの順番
    var game_id = 0 //テーブルのid
    var hand_count = 0 //ゲーム数
    var playChips: Int = 0 // プレイされたチップ
    var memberChips = 0 //プレイヤーのチップ数
    var playingCheck = "" //テーブルに残っているかフォールドしたか、ゲームから離れたかの確認
    var tableChips: Int = 0 //テーブルに出されたチップ
    var tableTotalChips: Int = 0 //テーブルの合計チップ
    var hand1 = ""
    var hand2 = ""
    var winnerHand = ""

    @PrimaryKey
    var id: Int? = null
}