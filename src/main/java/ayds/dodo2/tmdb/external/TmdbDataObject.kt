package ayds.dodo2.tmdb.external

import ayds.dodo2.tmdb.external.tmdb.TheMovieDBAPI
import ayds.dodo2.tmdb.external.tmdb.TmdbResponseToTmdbMovieResolverImpl
import ayds.dodo2.tmdb.external.tmdb.TmdbService
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object TmdbDataObject{
    private fun getTmdbAPI(): TheMovieDBAPI = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TheMovieDBAPI::class.java)

    fun getService(): ExtraInfoExternalService = TmdbService(getTmdbAPI(),
            TmdbResponseToTmdbMovieResolverImpl())
}

