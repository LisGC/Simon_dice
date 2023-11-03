package cl.lisgc.simondice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        ///About
        val aboutNavegation = findViewById<Button>(R.id.buttonAbout)

        aboutNavegation.setOnClickListener {
            val intentAbout = Intent(this,AboutActivity::class.java)
            startActivity(intentAbout)
        }

        ///Settings
        val settingsNavegation = findViewById<Button>(R.id.buttonSettings)

        settingsNavegation.setOnClickListener {
            val intentAbout = Intent(this,SettingsActivity2::class.java)
            startActivity(intentAbout)
        }

        ///Game
        val gameNavegation = findViewById<Button>(R.id.buttonPlay)

        gameNavegation.setOnClickListener {
            val intentAbout = Intent(this,GameActivity::class.java)
            startActivity(intentAbout)
        }

    }

}