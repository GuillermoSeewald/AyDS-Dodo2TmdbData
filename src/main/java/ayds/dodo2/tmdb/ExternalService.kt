package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.TmdbMovie

interface ExternalService {
    fun getMovieInfo(movieTitle: String, movieYear:String): TmdbMovie
}