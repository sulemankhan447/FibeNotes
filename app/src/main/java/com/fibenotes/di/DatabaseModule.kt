package com.fibenotes.di

import android.content.Context
import androidx.room.Room
import com.fibenotes.database.NotesDao
import com.fibenotes.database.NotesDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Provides
    fun providesRoomInstance(@ApplicationContext applicationContext: Context): NotesDatabase {
        return Room.databaseBuilder(
            applicationContext,
            NotesDatabase::class.java, "notes_db"
        ).build()
    }

    @Singleton
    @Provides
    fun providesNotesDao(notesDatabase: NotesDatabase): NotesDao {
        return notesDatabase.notesDao()
    }


}