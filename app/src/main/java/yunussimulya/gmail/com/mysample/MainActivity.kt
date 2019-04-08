package yunussimulya.gmail.com.mysample

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import yunussimulya.gmail.com.mysample.activity.dagger.DaggerActivity
import yunussimulya.gmail.com.mysample.activity.simple.*
import yunussimulya.gmail.com.mysample.databinding.ProgressActivity
import yunussimulya.gmail.com.mysample.mvp.MvpLoginActivity
import yunussimulya.gmail.com.mysample.mvvm.SimpleMVVMActivity

class MainActivity : AppCompatActivity() {

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
        bReactive.setOnClickListener {
            val intent = Intent(applicationContext, ReactiveActivity::class.java)
            startActivity(intent)
        }
        bGlide.setOnClickListener {
            val intent = Intent(applicationContext, GlideSimpleActivity::class.java)
            startActivity(intent)
        }
        bMvvmSimple.setOnClickListener {
            val intent = Intent(applicationContext, SimpleMVVMActivity::class.java)
            startActivity(intent)
        }
        bDagger.setOnClickListener {
            val intent = Intent(applicationContext, DaggerActivity::class.java)
            startActivity(intent)
        }
        bCamera.setOnClickListener {
            val intent = Intent(applicationContext, CameraActivity::class.java)
            startActivity(intent)
        }
    }

}