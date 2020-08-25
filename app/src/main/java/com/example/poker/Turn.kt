package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Turn : RealmObject(), Serializable {
    var game_id: Int = 0
    var count: Int = 0 // ゲーム数
    var round: String = "" //プリフロップ等のラウンド
    var round_count: Int = 0 //ラウンド内での週数
    var player: String = "" // プレイヤー名
    var playerNum: Int = 0
    var playerRound: Int = 0
    var player_id: String = "" //プレイヤーid
    var playChips: Int = 0 // プレイされたチップ
    var memberChips: Int = 0 //プレイヤーの所持チップ
    var tableChips: Int = 0 //テーブルに出されたチップ
    var tableTotalChips: Int = 0 //テーブルの合計チップ
    var hand1 = ""
    var hand2 = ""
    var winnerHand = ""

    @PrimaryKey
    var id: Int = 0
}