package yunussimulya.gmail.com.mysample.activity.simple

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import yunussimulya.gmail.com.mysample.R
import yunussimulya.gmail.com.mysample.network.ApacheConnection
import yunussimulya.gmail.com.mysample.model.ConnectionResult

class OldApacheActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_old_apache)

        val bCall = findViewById<Button>(R.id.bCall)
        bCall.setOnClickListener { ApacheTask("https://jsonplaceholder.typicode.com/users").execute() }
    }

    class ApacheTask constructor(val url: String) : AsyncTask<Void, Int, ConnectionResult>() {

        override fun doInBackground(vararg voids: Void): ConnectionResult {
            return ApacheConnection.GET(url, null)
        }

        override fun onPostExecute(s: ConnectionResult) {
            super.onPostExecute(s)
            Log.e("content", s.toString())
        }
    }


}