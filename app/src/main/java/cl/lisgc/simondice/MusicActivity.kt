package cl.lisgc.simondice

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MusicActivity : AppCompatActivity() {

    private lateinit var music: MediaPlayer
    private lateinit var win: MediaPlayer
    private lateinit var lose: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_music)

        music = MediaPlayer.create(this,R.raw.orange_ocean)
        win = MediaPlayer.create(this,R.raw.victory_fanfare)
        lose = MediaPlayer.create(this,R.raw.ut_gameover)

        music.start()
        music.isLooping = true

        val reproduce = findViewById<Button>(R.id.buttonPlayMusic)
        reproduce.setOnClickListener{

            if(!music.isPlaying)
            {
                music.start()
            }
            else
            {
                music.pause()
            }
        }

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
}