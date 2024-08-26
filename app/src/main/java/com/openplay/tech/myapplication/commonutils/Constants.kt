package com.openplay.tech.myapplication.commonutils

class Constants {
    companion object {
        const val DATABASE_NAME: String = "MOVIES_DATA_BASE"
        // const val DATASTORE_PREF_NAME: String = "MOVIES_DATA_PREF"
        const val API_KEY = "560ef5926d666c9799beef9d281643c3"
        const val BearerToken = "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiI1NjBlZjU5MjZkNjY2Yzk3OTliZWVmOWQyODE2NDNjMyIsIm5iZiI6MTcyNDU4MTExOS44NjQ2ODMsInN1YiI6IjY2YzhmM2E1NGZhODI1MTAzZGIzMDY1YSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.peTwBYKPdKk-1vO1tGk4lB5qWgH9NvSr-shYGpzJzyc"
        //API URL
        const val BASE_URL = "https://api.themoviedb.org/3/movie/"
        const val GET_POPULAR = BASE_URL+"popular?"
    }
}