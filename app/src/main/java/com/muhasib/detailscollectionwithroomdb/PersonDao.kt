package com.muhasib.detailscollectionwithroomdb

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow


@Dao
interface PersonDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun savePerson(person: Person)

    @Delete
    suspend fun deletePerson(person : Person)

    @Update
    suspend fun updatePeron(person: Person)


    @Query("SELECT * FROM person_table")

    fun getAllData(): Flow<List<Person>>

    @Query("SELECT * FROM person_table WHERE person_name LIKE '%' || :query || '%' or person_city LIKE '%' || :query || '%' or person_age LIKE '%' || :query || '%' ")

    fun searchedData(query : String ) : Flow<List<Person>>
}