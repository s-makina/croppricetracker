package com.sps.compose.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.sps.compose.R
import com.sps.compose.data.Config.app_version
import com.sps.compose.data.room.dao.UserDao
import com.sps.compose.data.room.entity.UserEntity

@Database(
    entities = [UserEntity::class],
    version = app_version
)
@TypeConverters(Convertors::class)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: LocalDatabase? = null

        fun getInstance(context: Context): LocalDatabase {
            synchronized(this) {
                return INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    LocalDatabase::class.java,
                    context.getString(R.string.database_name)+"$app_version"
                ).build().also {
                    INSTANCE = it
//                    val settingsDao = it.settingsDao()
//                    CoroutineScope(Dispatchers.IO).launch {
//                        settingsDao.insert(
//                            SettingsEntity(
//                                id = 1,
//                                useDarkTheme = false,
//                                isOffline = false,
//                            )
//                        )
                        // TODO settings will be implemented in later versions
//                    }
                }
            }
        }
    }
}