package jp.playing.table.poker

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.io.Serializable

open class Player : RealmObject(), Serializable {
    var game_id = 0
    var playerId = 0
    var playerRound = 0 //スタートの順番
    var playerNum = 0

    @PrimaryKey
    var id: Int = 0
}