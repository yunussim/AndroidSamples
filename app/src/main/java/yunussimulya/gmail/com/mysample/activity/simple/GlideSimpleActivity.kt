package yunussimulya.gmail.com.mysample.activity.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

import com.bumptech.glide.Glide

import yunussimulya.gmail.com.mysample.R

class GlideSimpleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_glide_simple)

        val bCall = findViewById<Button>(R.id.bCall)
        val ivImage = findViewById<ImageView>(R.id.ivImage)
        bCall.setOnClickListener { v ->
            Glide.with(applicationContext)
                    .load("http://via.placeholder.com/300.png")
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(ivImage)
        }
    }
}
