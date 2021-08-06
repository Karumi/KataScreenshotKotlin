package com.karumi.ui.view

import android.content.Context
import android.view.LayoutInflater
import androidx.test.platform.app.InstrumentationRegistry.getInstrumentation
import com.karumi.R
import com.karumi.domain.model.SuperHero
import com.karumi.shot.ScreenshotTest
import com.karumi.ui.presenter.SuperHeroesPresenter
import com.karumi.ui.view.adapter.SuperHeroViewHolder
import org.junit.Test
import org.mockito.Mockito.mock

class SuperHeroViewHolderTest : ScreenshotTest {

    @Test
    fun showsAnySuperHero() {
        val superHero = givenASuperHero()
        val holder = givenASuperHeroViewHolder()

        holder.render(superHero)
        compareScreenshot(holder.itemView, widthInPx = R.dimen.super_hero_row_height.toDim())
    }
    

    private fun givenASuperHeroViewHolder(): SuperHeroViewHolder {
        val context = getInstrumentation().targetContext
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.super_hero_row, null, false)
        return SuperHeroViewHolder(
            view,
            mock<SuperHeroesPresenter>(SuperHeroesPresenter::class.java)
        )
    }

    private fun givenASuperHero(
        superHeroName: String = "Super Hero Name",
        superHeroDescription: String = "Super Hero Description",
        isAvenger: Boolean = false
    ): SuperHero = SuperHero(superHeroName, null, isAvenger, superHeroDescription)
}

private fun Int.toDim(context: Context = getInstrumentation().targetContext): Int? =
    context.resources.getDimension(this).toInt()

