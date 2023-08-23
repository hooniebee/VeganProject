package com.example.vegan

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface RecordDao {
    @Query("SELECT * FROM Record")
    fun getAll(): Flow<List<Record>>
    @Query("SELECT * FROM Record WHERE uid IN (:uid)")
    fun loadAllByIds(uid: Int): Flow<List<Record>>
    //  @Query("SELECT * FROM user WHERE first_name LIKE :first AND " +  //안쓰는 코드라 주석처리
//  "last_name LIKE :last LIMIT 1")
//  fun findByName(first: String, last: String): User
    @Insert
    fun insertAll(vararg records: Record)

    @Delete
    fun delete(records: Record)



}