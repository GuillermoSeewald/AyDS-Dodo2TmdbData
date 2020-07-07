package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.TmdbMovie

interface TmdbService {
    fun getMovieInfo(movieTitle: String, movieYear:String): TmdbMovie
}