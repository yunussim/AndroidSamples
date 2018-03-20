package yunussimulya.gmail.com.mysample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import yunussimulya.gmail.com.mysample.activity.simple.OKHttpActivity
import yunussimulya.gmail.com.mysample.activity.simple.OldApacheActivity
import yunussimulya.gmail.com.mysample.activity.simple.ReactiveRetrofitActivity
import yunussimulya.gmail.com.mysample.activity.simple.RetrofitActivity
import yunussimulya.gmail.com.mysample.databinding.ProgressActivity
import yunussimulya.gmail.com.mysample.mvp.MvpLoginActivity

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
        bRetrofit.setOnClickListener {
            val intent = Intent(applicationContext, ReactiveRetrofitActivity::class.java)
            startActivity(intent)
        }
        bMvpLogin.setOnClickListener {
            val intent = Intent(applicationContext, MvpLoginActivity::class.java)
            startActivity(intent)
        }
    }

}