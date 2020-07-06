package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.entities.*
import ayds.dodo2.tmdb.external.entities.TmdbMovieRequest
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