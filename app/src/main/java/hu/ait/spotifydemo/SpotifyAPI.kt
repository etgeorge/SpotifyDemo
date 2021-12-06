package hu.ait.spotifydemo

import hu.ait.spotifydemo.data.AudioFeatures
import retrofit2.Call
import retrofit2.http.*

//https://api.spotify.com/v1/audio-analysis/11dFghVXANMlKmJXsNCbNl
//sample ID: 098ttCNmncrO4YvqWUNMvn
//sample token: Bearer BQBOtFvXlFqPFM_HRNc3eFVEduRjGK9fjN0bKJ194NslmpRjTiQv3-B2rQGjc-gxnDjenheBSH0F3HGVBNM

interface SpotifyAPI {
    @GET("audio-features/{id}")
    fun getTrackFeatures(@Header("Authorization") token: String?,
                         @Path("id") id: String = "098ttCNmncrO4YvqWUNMvn"): Call<AudioFeatures>

}