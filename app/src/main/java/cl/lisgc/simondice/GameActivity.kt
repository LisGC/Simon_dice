package cl.lisgc.simondice

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
class GameActivity : AppCompatActivity() {
    private lateinit var ratImg : ImageView
    private lateinit var touchText : TextView
    private lateinit var mDetector: GestureDetectorCompat
    private lateinit var music: MediaPlayer
    private lateinit var win: MediaPlayer
    private lateinit var lose: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        music = MediaPlayer.create(this,R.raw.temafondo)
        win = MediaPlayer.create(this,R.raw.victory_fanfare)
        lose = MediaPlayer.create(this,R.raw.ut_gameover)

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
        if (music.isPlaying || win.isPlaying || lose.isPlaying)
        {
            music.pause()
            win.pause()
            lose.pause()
        }
    }

    override fun onResume() {
        super.onResume()
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

        override fun onDown(event: MotionEvent): Boolean {
            ratImg.setImageResource(R.drawable.rata3)
            touchText.setText(R.string.ontouch)
            return true
        }
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

}