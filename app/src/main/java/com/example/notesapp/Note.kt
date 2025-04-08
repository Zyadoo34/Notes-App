package com.example.notesapp

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize


//snake_case

@kotlinx.parcelize.Parcelize

@Entity(tableName = "note")
class Note(
    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    val id :Int =0,
    val details :String
):Parcelable