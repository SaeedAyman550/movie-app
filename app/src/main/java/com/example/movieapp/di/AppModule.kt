package com.example.movieapp.di

import com.example.data.remote.api.MovieApi
import com.example.data.repository.MovieRepoImp
import com.example.domain.repository.MovieRepo
import com.example.domain.utils.Constant
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideRetrofit():Retrofit=
        Retrofit.Builder()
            .baseUrl("${Constant.baseUrl}")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Singleton
    @Provides
    fun provideMovieApi(retrofit:Retrofit): MovieApi =
        retrofit.create(MovieApi::class.java)


    @Singleton
    @Provides
    fun provideMovieRepo(api:MovieApi):MovieRepo=MovieRepoImp(api)

}