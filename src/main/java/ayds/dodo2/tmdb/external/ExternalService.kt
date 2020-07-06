package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.external.entities.TmdbMovieResponse

interface ExternalService {
    fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovieResponse
}