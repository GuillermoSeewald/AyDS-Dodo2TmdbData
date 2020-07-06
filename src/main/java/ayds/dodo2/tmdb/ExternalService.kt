package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.entities.TmdbMovie

interface ExternalService {
    fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovie
}