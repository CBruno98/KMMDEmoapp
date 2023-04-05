package com.bruno.kmm_demoapp.android.application

import android.app.Application
import android.content.Context
import android.util.Log
import com.bruno.kmm_demoapp.android.application.di.appModule
import com.bruno.kmm_demoapp.android.data.di.dataModule
import com.bruno.kmm_demoapp.android.domain.di.domainModule
import com.bruno.kmm_demoapp.android.ui.di.uiModule
import org.greenrobot.eventbus.Subscribe
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module


class CulqiApplication : Application() {

//    private var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        initializeInjector()
//        initPos()
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            androidContext(this@CulqiApplication)
            modules(dataModule, domainModule, uiModule)
            modules(appModule)
//            modules(
//                module {
//                    single<PosApp> { this@CulqiApplication }
//                    single<Pos> { get<PosApp>().pos }
//                }
//            )
        }
//        Setup.initialize(CULQI_URL, this)
    }

//    private fun initPos() {
//        val posOptions = PosOptions()
//        posOptions.ivController = MapIVController()
//        posOptions.track2FitMode = FitMode.F_FIT
//        posOptions.track2PaddingMode = PaddingMode.PKCS5
//        posOptions.authProcessingCode = 0x00.toByte()
//        posOptions.reverseProcessingCode = 0x00.toByte()
//        posOptions.aidTables = Defaults.AID_TABLES
//        pos = Pos(this, posOptions)
//        pos.setPinLength(4)
//    }

//    override fun getPos(): Pos = pos

    private fun initializeInjector() {

        /*   appComponent = DaggerAppComponent.builder()
               .appModule(AppModule(this))
               .build()*/
    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        // MultiDex.install(this)
    }

//    fun getAppComponent(): AppComponent? {
//        return appComponent
//    }

//    @Subscribe
//    fun onEvent(info: EventActionInfo) {
//        when (info.action) {
//            EventBusContent.EVENT_BUS_ONLINE_PIN_START -> getPos().startInputPin(info)
//            EventBusContent.EVENT_BUS_ONLINE_PIN_END -> getPos().endInputPin(info)
//        }
//    }
}