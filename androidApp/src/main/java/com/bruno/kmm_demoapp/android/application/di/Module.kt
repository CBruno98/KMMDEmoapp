package com.bruno.kmm_demoapp.android.application.di

import org.koin.dsl.module

internal val uiModule = module {
}

internal val dataModule = module {

}

val appModule = dataModule + uiModule