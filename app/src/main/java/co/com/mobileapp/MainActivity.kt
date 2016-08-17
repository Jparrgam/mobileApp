package co.com.mobileapp

import android.content.Intent
import co.com.mobileapp.util.util
import com.daimajia.androidanimations.library.Techniques
import com.viksaa.sssplash.lib.activity.AwesomeSplash
import com.viksaa.sssplash.lib.cnst.Flags
import com.viksaa.sssplash.lib.model.ConfigSplash

/**
 * @author Jaime Gamboa
 * @see AwesomeSplash
 */
class MainActivity : AwesomeSplash() {

    /**
     * init splash screen
     */
    override fun initSplash(configSplash: ConfigSplash) {

        //configuration circular reveal
        configSplash.backgroundColor = R.color.colorPrimary
        configSplash.animCircularRevealDuration = 2000
        configSplash.revealFlagX = Flags.REVEAL_RIGHT
        configSplash.revealFlagY = Flags.REVEAL_BOTTOM


        //customize path file
        configSplash.pathSplash  = util.constants.DROID_LOGO
        configSplash.originalHeight = 400
        configSplash.originalHeight = 400
        configSplash.animPathStrokeDrawingDuration = 1000
        configSplash.pathSplashStrokeSize = 3
        configSplash.pathSplashStrokeColor = android.R.color.white
        configSplash.animPathFillingDuration = 2000
        configSplash.pathSplashFillColor = android.R.color.white

        //customize title
        configSplash.titleSplash = resources.getString(R.string.title_splash)
        configSplash.titleTextColor = android.R.color.white
        configSplash.titleTextSize = 30f
        configSplash.animTitleDuration = 2000
        configSplash.animTitleTechnique = Techniques.FlipInX
    }

    /**
     * finish anim splash screen
     */
    override fun animationsFinished() {
        val intent = Intent(this, ListApp::class.java)
        startActivity(intent)
    }
}