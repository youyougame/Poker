package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Hand : RealmObject(), Serializable {
    var count: Int = 0 //ゲーム数
    var game_id: Int = 0 //テーブルのid

    //カードの情報
    var hand1 :String = "" //ハンド
    var hand2 :String = "" //ハンド
    var table1 :String = "" //テーブル
    var table2 :String = "" //テーブル
    var table3 :String = "" //テーブル
    var table4 :String = "" //テーブル
    var table5 :String = "" //テーブル
    var bigBlind :Int = 0 //ビッグブラインド
    var smallBlind :Int = 0 //スモールブラインド

    @PrimaryKey
    var id: Int = 0
}