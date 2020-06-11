package com.ijikod.punkapp.ui.Activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        scheduleSplashScreen(this)
    }



     fun scheduleSplashScreen(context: Context){
        val splashDuration = getSplashScreenDuration()
        Handler().postDelayed({
            moveOn(context)
            finish()
        }, splashDuration)

    }


    private fun moveOn(context: Context){
        val intent = Intent(context, MainActivity::class.java)
        context.startActivity(intent)
    }





    private fun getSplashScreenDuration() = 4000L
}
