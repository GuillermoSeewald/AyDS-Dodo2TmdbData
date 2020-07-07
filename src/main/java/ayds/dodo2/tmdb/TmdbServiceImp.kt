package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.EmptyTmdbMovie
import ayds.dodo2.tmdb.entities.TmdbMovie
import retrofit2.Response

internal class TmdbServiceImp(
    private val tmdbAPI: TheMovieDBAPI,
    private val tmdbMovieResolver: TmdbResponseToTmdbMovieResolver
) : TmdbService {

    override fun getMovieInfo(movieTitle:String,movieYear:String): TmdbMovie {
        val callResponse = getTmdbMovieInfoFromService(movieTitle)
        return callResponse?.let{
            tmdbMovieResolver.getMovieInfoFromExternalData(it.body(), movieYear)
        } ?: EmptyTmdbMovie
    }

    private fun getTmdbMovieInfoFromService(title: String): Response<String?>? {
        return tmdbAPI.getTerm(title)?.execute()
    }
}