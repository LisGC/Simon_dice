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
import android.os.Handler
import android.os.Looper
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

class GameActivity : AppCompatActivity() , SensorEventListener {
    private lateinit var ratImg : ImageView
    private lateinit var touchText : TextView
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var music: MediaPlayer
    private lateinit var win: MediaPlayer
    private lateinit var lose: MediaPlayer
    private var sensorManager: SensorManager? = null
    private var accelerometer: Sensor? = null
    private val handler = Handler(Looper.getMainLooper())
    private lateinit var points: TextView
    private var point = 0
    private var seAgito: Boolean = false

    private val instructions = listOf(
        "¡Desliza!",
        "¡Agita!",
        "¡Presiona por un tiempo!"
    )
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

        points = findViewById(R.id.textPuntos)
        points.text = point.toString()

        showRandomInstruction()

    }

    private fun showRandomInstruction() {
        val randomIndex = (0 until instructions.size).random()
        val randomInstruction = instructions[randomIndex]
        touchText.text = randomInstruction
    }

    private fun scheduleRandomInstruction(){
        handler.postDelayed({
            seAgito = false
            showRandomInstruction()
        } ,3000)
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

            if(touchText.text == "¡Desliza!")
            {
                ratImg.setImageResource(R.drawable.rata2)
                touchText.setText(R.string.done)
                point += 1
                points.text = point.toString()
                win.start()
                scheduleRandomInstruction()
            }
            else
            {
                ratImg.setImageResource(R.drawable.rata3)
                touchText.setText(R.string.notDone)
                lose.start()
            }
            return true
        }

        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            if(touchText.text == "¡Presiona por un tiempo!")
            {
                ratImg.setImageResource(R.drawable.rata1)
                touchText.setText(R.string.done)
                point += 1
                points.text = point.toString()
                win.start()
                scheduleRandomInstruction()
            }
            else
            {
                ratImg.setImageResource(R.drawable.rata3)
                touchText.setText(R.string.notDone)
                lose.start()
            }

        }
    }

    override fun onSensorChanged(p0: SensorEvent) {
        if (p0.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            seAgito = true
            val x = p0.values[0]
            val y = p0.values[1]
            val z = p0.values[2]
            val acceleration = Math.sqrt(x * x + y * y + z * z.toDouble()).toFloat()

            // You can adjust the acceleration threshold based on your needs
            val threshold = 12.0f

            if (acceleration > threshold) {
                if(touchText.text == "¡Agita!")
                {
                    ratImg.setImageResource(R.drawable.rata4)
                    touchText.setText(R.string.done)
                    point += 1
                    points.text = point.toString()
                    win.start()
                    scheduleRandomInstruction()
                }
                else if(seAgito == false)
                {
                    ratImg.setImageResource(R.drawable.rata3)
                    touchText.setText(R.string.notDone)
                    lose.start()
                }

            }
        }
    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

}