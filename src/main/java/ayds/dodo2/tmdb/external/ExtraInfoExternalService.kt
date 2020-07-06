package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.external.entities.TmdbMovieResponse

interface ExtraInfoExternalService {
    fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovieResponse
}