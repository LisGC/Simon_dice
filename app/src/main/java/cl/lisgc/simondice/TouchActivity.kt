package cl.lisgc.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.GestureDetectorCompat

private const val DEBUG_TAG = "Gestures"

    class TouchActivity : AppCompatActivity() {

        private lateinit var ratImg : ImageView
        private lateinit var touchText : TextView
        private lateinit var mDetector: GestureDetectorCompat

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_touch)
            mDetector = GestureDetectorCompat(this, GestureListener())

            ratImg = findViewById(R.id.ratImage)
            touchText = findViewById(R.id.textTouch)

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
            override fun onFling( event1: MotionEvent,event2: MotionEvent,velocityX: Float,velocityY: Float): Boolean {
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