package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.tmdb.TmdbMovieResponse


interface ExtraInfoExternalService {
    fun getMovieInfo(movie: OmdbMovie): TmdbMovieResponse
}