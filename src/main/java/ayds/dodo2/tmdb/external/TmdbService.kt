package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.external.entities.TmdbMovie

interface TmdbService {
    fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovie
}