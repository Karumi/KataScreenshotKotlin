package com.karumi.ui.view

import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.karumi.asApp
import com.karumi.ui.LifecycleEmitter
import com.karumi.ui.LifecycleReceiver
import com.karumi.ui.lifeCycleLinker

abstract class BaseActivity : KodeinAppCompatActivity(), LifecycleEmitter by lifeCycleLinker {

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.asApp().addModule(activityModules())
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        registerToLifecycle(obtainPresenter())
        initialize()
    }

    abstract fun getLayoutId(): Int

    abstract fun obtainPresenter(): LifecycleReceiver

    abstract fun activityModules(): Module

    override fun onResume() {
        super.onResume()
        update()
    }

}