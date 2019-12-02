package com.passwd.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.passwd.domain.PasswordDto
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface PasswordDao {

    @Query("SELECT * from password where id = :id LIMIT 1")
    fun get(id: Int): Single<PasswordDto>

    @Query("SELECT * FROM password")
    fun getAll(): Single<List<PasswordDto>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(vararg passwordDto: PasswordDto): Completable

    @Delete
    fun delete(vararg passwordDto: PasswordDto): Completable

    @Update
    fun update(vararg passwordDto: PasswordDto): Completable
}