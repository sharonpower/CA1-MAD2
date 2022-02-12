package ie.wit.perfectpizza.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import ie.wit.perfectpizza.R
import android.os.Handler


class ScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_screen)

        supportActionBar?.hide()

        Handler().postDelayed({
            val intent = Intent(this@ScreenActivity, PerfectPizzaListActivity::class.java)
            startActivity(intent)
              finish()
              }, 2500)
    }
}