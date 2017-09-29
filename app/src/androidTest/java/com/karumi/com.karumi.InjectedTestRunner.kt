
package com.karumi

import android.app.Application
import android.os.Bundle
import com.facebook.testing.screenshot.ScreenshotRunner
import com.github.tmurakami.dexopener.DexOpenerAndroidJUnitRunner

class InjectedTestRunner : DexOpenerAndroidJUnitRunner() {

    override fun callApplicationOnCreate(app: Application) {
        app.asApp().kodein.mutable = true
        super.callApplicationOnCreate(app)
    }

    override fun onCreate(args: Bundle) {
        super.onCreate(args)
        ScreenshotRunner.onCreate(this, args)
    }

    override fun finish(resultCode: Int, results: Bundle) {
        ScreenshotRunner.onDestroy()
        super.finish(resultCode, results)
    }
}
