package com.karumi

import android.app.Application
import android.content.Context
import com.github.tmurakami.dexopener.DexOpener
import com.karumi.shot.ShotTestRunner

class TestRunner : ShotTestRunner() {
    override fun newApplication(
        cl: ClassLoader?,
        className: String?,
        context: Context?
    ): Application {
        DexOpener.install(this)
        return super.newApplication(cl, className, context)
    }
}