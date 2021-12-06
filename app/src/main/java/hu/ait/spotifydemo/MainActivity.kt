package hu.ait.spotifydemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import hu.ait.spotifydemo.data.AudioFeatures
import hu.ait.spotifydemo.data.Base
import hu.ait.spotifydemo.data.Token
import hu.ait.spotifydemo.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.d("CREATED", "PROJECT CREATED")

        binding.btnSearch.setOnClickListener {
            getAuthorization()
        }
    }

    fun getAuthorization() {
        var retrofit = Retrofit.Builder()
               .baseUrl("https://accounts.spotify.com")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var authorizationAPI = retrofit.create(Authorization::class.java)
        val call = authorizationAPI.getToken()

        call.enqueue(object : Callback<Token>{
            override fun onResponse(call: Call<Token>, response: Response<Token>) {
                var authorizationResult = response.body()
                val TOKEN = authorizationResult?.access_token.toString()

                Log.d("THIS WORKED", "Token is: ${authorizationResult?.access_token}")

                getTrackFeatures(TOKEN)

            }

            override fun onFailure(call: Call<Token>, t: Throwable) {
                TODO("Not yet implemented")

            }
        })

    }

    fun getTrackFeatures(Token : String){
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.spotify.com/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var spotifyAPI = retrofit.create(SpotifyAPI::class.java)
        val call = spotifyAPI.getTrackFeatures("Bearer "+Token)

        call.enqueue(object : Callback<AudioFeatures>{

            override fun onResponse(call: Call<AudioFeatures>, response: Response<AudioFeatures>) {
                var spotifyResult = response.body()
                Log.d("HIT2", "The response is "+"${spotifyResult?.acousticness}")
                binding.tvDemo.text = "Acoustincess: ${spotifyResult?.acousticness}"
            }

            override fun onFailure(call: Call<AudioFeatures>, t: Throwable) {
                TODO("Not yet implemented")


            }

        })


    }

}




