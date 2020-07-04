package ayds.dodo2.tmdb.external.tmdb

import ayds.dodo2.tmdb.external.ExtraInfoExternalService
import ayds.dodo2.tmdb.external.tmdb.entities.EmptyTmdbMovieResponse
import ayds.dodo2.tmdb.external.tmdb.entities.TmdbMovieRequest
import ayds.dodo2.tmdb.external.tmdb.entities.TmdbMovieResponse
import retrofit2.Response

class TmdbService(
    private val tmdbAPI: TheMovieDBAPI,
    private val tmdbMovieResolver: TmdbResponseToTmdbMovieResolver
) : ExtraInfoExternalService {

    override fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovieResponse {
        val callResponse = getTmdbMovieInfoFromService(movie.title)
        return callResponse?.let{
            tmdbMovieResolver.getMovieInfoFromExternalData(it.body(), movie.year)
        } ?: EmptyTmdbMovieResponse
    }

    private fun getTmdbMovieInfoFromService(title: String): Response<String?>? {
        return tmdbAPI.getTerm(title)?.execute()
    }
}