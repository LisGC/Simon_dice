package cl.lisgc.simondice

import android.content.Context
import android.content.pm.ActivityInfo
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

private const val DEBUG_TAG = "Gestures"
class GameActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var ratImg : ImageView
    private lateinit var touchText : TextView
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var music: MediaPlayer
    private lateinit var win: MediaPlayer
    private lateinit var lose: MediaPlayer
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        accelerometer = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        music = MediaPlayer.create(this,R.raw.temafondo)
        win = MediaPlayer.create(this,R.raw.nice)
        lose = MediaPlayer.create(this,R.raw.bad)

        music.start()
        music.isLooping = true

        mDetector = GestureDetectorCompat(this, GestureListener())

        ratImg = findViewById(R.id.ratImage)
        touchText = findViewById(R.id.textTouch)

        val victory = findViewById<Button>(R.id.buttonVictory)
        victory.setOnClickListener{

            if(!win.isPlaying)
            {
                win.start()
            }
        }

        val defeat = findViewById<Button>(R.id.buttonDefeat)
        defeat.setOnClickListener{

            if(!lose.isPlaying)
            {
                lose.start()
            }
        }
    }
    override fun onPause() {
        super.onPause()
        sensorManager?.unregisterListener(this)
        if (music.isPlaying || win.isPlaying || lose.isPlaying)
        {
            music.pause()
            win.pause()
            lose.pause()
        }
    }

    override fun onResume() {
        super.onResume()
        sensorManager?.registerListener(this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL)
        if(!music.isPlaying)
        {
            music.start()
        }
    }
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }


    inner class GestureListener : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
            ratImg.setImageResource(R.drawable.rata2)
            touchText.setText(R.string.fling)
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            ratImg.setImageResource(R.drawable.rata1)
            touchText.setText(R.string.press)
        }
    }

    override fun onSensorChanged(p0: SensorEvent) {
        if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val x = p0.values[0]
            val y = p0.values[1]
            val z = p0.values[2]
            val acceleration = Math.sqrt(x * x + y * y + z * z.toDouble()).toFloat()

            // You can adjust the acceleration threshold based on your needs
            val threshold = 15.0f

            if (acceleration > threshold) {
                // Change the background color to black
                ratImg.setImageResource(R.drawable.rata4)
                touchText.setText(R.string.swing)
            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}