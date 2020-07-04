package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.tmdb.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.external.tmdb.entities.TmdbMovieResponse


interface ExtraInfoExternalService {
    fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovieResponse
}