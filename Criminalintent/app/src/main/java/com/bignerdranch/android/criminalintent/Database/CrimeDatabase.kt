package com.bignerdranch.android.criminalintent.Database
import androidx.room.*
import com.bignerdranch.android.criminalintent.Crime


@Database(entities = [ Crime::class ], version=1)
@TypeConverters(CrimeTypeConverters::class)
abstract class CrimeDatabase : RoomDatabase() {
    abstract fun crimeDao(): CrimeDao
}
