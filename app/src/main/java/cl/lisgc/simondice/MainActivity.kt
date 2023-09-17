package cl.lisgc.simondice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.mainmenu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.button_settings -> {
                val intentPreferences = Intent(this, SettingsActivity::class.java)
                startActivity(intentPreferences)
                return true
            }
            R.id.button_about -> {
                val intentPreferences = Intent(this, AboutActivity::class.java)
                startActivity(intentPreferences)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}