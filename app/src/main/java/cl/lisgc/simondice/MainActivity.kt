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

        setSupportActionBar(findViewById(R.id.toolbar))

        ///Game
        val gameNavegation = findViewById<Button>(R.id.buttonPlay)

        gameNavegation.setOnClickListener {
            val intentAbout = Intent(this,GameActivity::class.java)
            startActivity(intentAbout)
        }

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean{
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean{
        when(item.itemId){
            R.id.action_settings-> {
                val intentSettings = Intent(this, SettingsActivity2::class.java)
                startActivity(intentSettings)
                return true

            }
            R.id.action_about -> {
                val intentAbout = Intent(this,AboutActivity::class.java)
                startActivity(intentAbout)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}