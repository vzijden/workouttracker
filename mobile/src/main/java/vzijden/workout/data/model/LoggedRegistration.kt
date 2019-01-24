package vzijden.workout.data.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class LoggedRegistration(
    @Embedded(prefix = "_registration")
    val registration: Registration
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}