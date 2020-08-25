package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable
import java.util.*

open class Game : RealmObject(), Serializable {
    var date: Date = Date() //日時
    var title: String = "" //テーブル名
    var count: Int = 0 //行われたゲーム数
    var dateString = ""

    //idをプライマリーキーとして設定
    @PrimaryKey
    var id: Int = 0
}