package cl.lisgc.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SplashScreenActivity : AppCompatActivity() {
    val tagLog = "SplashScreenActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)


        // Getting preference
        //val sharedPreferences = getSharedPreferences("MyPreferences", Context.MODE_PRIVATE)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        // Read a preference
        val value = sharedPreferences.getString("splash_time", "1000")

        Log.i(tagLog,"Value of splash_time:" + value)

        // Load the fade-in animation
        val fadeIn = AnimationUtils.loadAnimation(applicationContext, R.anim.fadein)


        val imageView = findViewById<ImageView>(R.id.imageLogo)

        fadeIn.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // Animation started
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Animation ended; proceed to MainActivity
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish() // Optional: Close the splash screen activity
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // Animation repeated
            }
        })
        imageView.startAnimation(fadeIn)
        val seconds = value?.toLong()
        val delayMillis = seconds!!.toLong() // 2 seconds (adjust as needed)

///lo de hacer el fade y que termine
        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the splash screen activity
        }, delayMillis)
    }
}
}