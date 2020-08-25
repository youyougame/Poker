package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class User : RealmObject(), Serializable {
    var name: String = "" //他のプレーヤーの名前

    @PrimaryKey
    var id: Int = 0
}