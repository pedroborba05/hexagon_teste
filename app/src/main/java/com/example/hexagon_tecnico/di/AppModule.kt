package com.example.hexagon_tecnico.di

import android.content.Context
import androidx.room.Room
import com.example.hexagon_tecnico.core.Constants.Companion.USER_TABLE
import com.example.hexagon_tecnico.data.dao.UserDao
import com.example.hexagon_tecnico.data.network.UserDb
import com.example.hexagon_tecnico.data.repository.UserRepositoryImpl
import com.example.hexagon_tecnico.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    fun provideUserDb(
        @ApplicationContext
        context: Context
    ) = Room.databaseBuilder(
        context,
        UserDb::class.java,
        USER_TABLE
    ).build()

    @Provides
    fun provideUserDao(
        userDb: UserDb
    ) = userDb.userDao

    @Provides
    fun provideUserRepository(
        userDao: UserDao
    ): UserRepository = UserRepositoryImpl(
        userDao = userDao
    )
}