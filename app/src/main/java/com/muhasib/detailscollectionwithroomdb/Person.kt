package com.muhasib.detailscollectionwithroomdb

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Person_Table")
data class Person(
    @PrimaryKey(autoGenerate = true) val pID: Int,
    @ColumnInfo val person_name :String,
    @ColumnInfo val person_age : Int,
    @ColumnInfo val person_city : String
): Parcelable
