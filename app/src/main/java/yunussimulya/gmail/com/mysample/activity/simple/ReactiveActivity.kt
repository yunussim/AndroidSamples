package yunussimulya.gmail.com.mysample.activity.simple

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import yunussimulya.gmail.com.mysample.R
import java.util.concurrent.TimeUnit

class ReactiveActivity : AppCompatActivity() {


    internal var disposable: Disposable? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reactive)

        val bIterator = findViewById<Button>(R.id.bIterator)
        bIterator.setOnClickListener {
            Observable
                    .range(1, 10)
                    .subscribe { integer -> Log.e("obser", integer!!.toString() + "") }
        }

        val bInterval = findViewById<Button>(R.id.bInterval)
        bInterval.setOnClickListener {
            disposable = Observable.interval(1, TimeUnit.SECONDS).subscribe(
                    { aLong -> Log.e("tick", "" + aLong!!) },
                    { Log.e("error", "aye") },
                    { Log.e("complete", "aye") })
        }
    }

    override fun onDestroy() {
        if (disposable != null) {
            disposable!!.dispose()
        }
        super.onDestroy()
    }
}
