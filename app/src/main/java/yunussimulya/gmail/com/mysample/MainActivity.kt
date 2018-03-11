package yunussimulya.gmail.com.mysample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import yunussimulya.gmail.com.mysample.activity.OKHttpActivity
import yunussimulya.gmail.com.mysample.activity.OldApacheActivity
import yunussimulya.gmail.com.mysample.databinding.ProgressActivity

class MainActivity : AppCompatActivity() {

    companion object {
        init {
            System.loadLibrary("native-lib")
        }
    }

    external fun stringFromJNI(): String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bDataBinding.setOnClickListener {
            val intent = Intent(applicationContext, ProgressActivity::class.java)
            startActivity(intent)
        }
        bOldApache.setOnClickListener {
            val intent = Intent(applicationContext, OldApacheActivity::class.java)
            startActivity(intent)
        }
        bOKHttp.setOnClickListener {
            val intent = Intent(applicationContext, OKHttpActivity::class.java)
            startActivity(intent)
        }
    }

}