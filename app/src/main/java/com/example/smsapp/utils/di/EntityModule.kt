package com.example.smsapp.utils.di

import android.content.Context
import com.example.smsapp.data.model.Message
import com.example.smsapp.utils.NotificationHandler
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ActivityComponent::class)
object EntityModule {

    @Provides
    fun provideEntity() = Message()

}