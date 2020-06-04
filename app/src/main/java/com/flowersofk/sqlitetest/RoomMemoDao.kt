package com.flowersofk.sqlitetest

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface RoomMemoDao {

    @Query("select * from orm_memo")
    fun getAll() : MutableList<RoomMemo>

    // 동일한 값이 입력 되었을때 UPDATE
    @Insert(onConflict = REPLACE)
    fun insert(memo : RoomMemo)

    @Delete
    fun delete(memo : RoomMemo)
}