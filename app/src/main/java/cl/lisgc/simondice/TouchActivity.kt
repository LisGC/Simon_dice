package cl.lisgc.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import androidx.core.view.GestureDetectorCompat

private const val DEBUG_TAG = "Gestures"

    class TouchActivity : AppCompatActivity() {

        private lateinit var ratImg : ImageView
        private lateinit var mDetector: GestureDetectorCompat

        public override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.activity_touch)
            mDetector = GestureDetectorCompat(this, MyGestureListener())

            ratImg = findViewById(R.id.ratImage)

        }

        override fun onTouchEvent(event: MotionEvent): Boolean {
            mDetector.onTouchEvent(event)

            ratImg.setImageResource(R.drawable.rata2)

            return super.onTouchEvent(event)
        }


        private class MyGestureListener : GestureDetector.SimpleOnGestureListener() {

            override fun onDown(event: MotionEvent): Boolean {
                Log.d(DEBUG_TAG, "onDown: $event")
                return true
            }
            override fun onFling( event1: MotionEvent,event2: MotionEvent,velocityX: Float,velocityY: Float): Boolean {
                Log.d(DEBUG_TAG, "onFling: $event1 $event2")
                return true
            }
        }

    }