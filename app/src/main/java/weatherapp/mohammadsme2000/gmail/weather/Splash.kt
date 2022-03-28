package weatherapp.mohammadsme2000.gmail.weather

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import kotlinx.android.synthetic.main.activity_splash.*

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val media = MediaPlayer.create(this,R.raw.rp)
        media.start()

        val animation_imageview=imagview_inflater

        animation_imageview.animate().apply {

            duration=2000
            rotation(360f)
                .start()


        }
        Handler().postDelayed({
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        },2000)
    }
}