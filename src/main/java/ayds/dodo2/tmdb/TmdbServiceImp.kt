package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.EmptyTmdbMovie
import ayds.dodo2.tmdb.entities.TmdbMovie
import ayds.dodo2.tmdb.entities.*
import ayds.dodo2.tmdb.entities.TmdbMovieRequest
import retrofit2.Response

class TmdbServiceImp(
    private val tmdbAPI: TheMovieDBAPI,
    private val tmdbMovieResolver: TmdbResponseToTmdbMovieResolver
) : ExternalService {

    override fun getMovieInfo(movie: TmdbMovieRequest): TmdbMovie {
        val callResponse = getTmdbMovieInfoFromService(movie.title)
        return callResponse?.let{
            tmdbMovieResolver.getMovieInfoFromExternalData(it.body(), movie.year)
        } ?: EmptyTmdbMovie
    }

    private fun getTmdbMovieInfoFromService(title: String): Response<String?>? {
        return tmdbAPI.getTerm(title)?.execute()
    }
}