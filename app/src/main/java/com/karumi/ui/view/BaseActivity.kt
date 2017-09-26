package com.karumi.ui.view

import android.os.Bundle
import com.github.salomonbrys.kodein.Kodein.Module
import com.github.salomonbrys.kodein.android.KodeinAppCompatActivity
import com.karumi.asApp


abstract class BaseActivity : KodeinAppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        applicationContext.asApp().addModule(activityModules())
        super.onCreate(savedInstanceState)
    }

    abstract fun activityModules(): Module
}