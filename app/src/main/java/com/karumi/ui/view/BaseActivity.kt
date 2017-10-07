package com.karumi.ui.view

import android.content.Intent
import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.karumi.asApp
import com.karumi.ui.LifecyclePublisher
import com.karumi.ui.LifecycleSubscriber
import com.karumi.ui.lifeCycleLinker

abstract class BaseActivity : KodeinAppCompatActivity(), LifecyclePublisher by lifeCycleLinker {

    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.asApp().addModule(activityModules)
        super.onCreate(savedInstanceState)
        setContentView(getLayoutId())
        registerToLifecycle(obtainPresenter())
        preparePresenter(intent)
        initialize()
    }

    abstract fun preparePresenter(intent: Intent?)
    abstract fun getLayoutId(): Int

    abstract fun obtainPresenter(): LifecycleSubscriber

    abstract val activityModules: Module

    override fun onResume() {
        super.onResume()
        update()
    }

}