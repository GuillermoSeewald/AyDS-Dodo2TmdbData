package ayds.dodo2.tmdb.external.tmdb

import ayds.dodo2.tmdb.external.ExtraInfoExternalService
import retrofit2.Response

class TmdbService(
    private val tmdbAPI: TheMovieDBAPI,
    private val tmdbMovieResolver: TmdbResponseToTmdbMovieResolver
) : ExtraInfoExternalService {

    override fun getMovieInfo(movie: OmdbMovie): TmdbMovieResponse {
        val callResponse = getTmdbMovieInfoFromService(movie.title)
        return callResponse?.let{
            tmdbMovieResolver.getMovieInfoFromExternalData(it.body(), movie.year)
        } ?: EmptyMovieInfo
    }

    private fun getTmdbMovieInfoFromService(title: String): Response<String?>? {
        return tmdbAPI.getTerm(title)?.execute()
    }
}