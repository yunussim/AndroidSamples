package yunussimulya.gmail.com.mysample.activity.simple

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

import java.io.IOException
import java.util.HashMap

import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import yunussimulya.gmail.com.mysample.R
import yunussimulya.gmail.com.mysample.data.DataHandler
import yunussimulya.gmail.com.mysample.network.OKHttpConnection

class OKHttpActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_okhttp)

        val bCall = findViewById<Button>(R.id.bCall)
        bCall.setOnClickListener { makeRequest() }
    }

    private fun makeRequest() {
        val url = "https://www.jualo.com/categories.json"
        val headers = HashMap<String, String>()
        headers["Authorization"] = DataHandler.getB64Auth("jualo", "railsrails")

        val request = OKHttpConnection.GET(url, headers)
        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                val result = if (response.body() != null) response.body()!!.string() else ""
                Log.e("" + response.code(), result)
            }
        })
    }
}
