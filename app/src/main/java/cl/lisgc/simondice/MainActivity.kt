package cl.lisgc.simondice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.buttonAbout -> {
                val intentPreferences = Intent(this, AboutActivity::class.java)
                startActivity(intentPreferences)
                return true
            }

            R.id.buttonSettings -> {
                val intentPreferences = Intent(this, SettingsActivity::class.java)
                startActivity(intentPreferences)
                return true
            }

            R.id.buttonMusic -> {
                val intentPreferences = Intent(this, MusicActivity::class.java)
                startActivity(intentPreferences)
                return true
            }

            else -> return super.onOptionsItemSelected(item)
        }
    }

}