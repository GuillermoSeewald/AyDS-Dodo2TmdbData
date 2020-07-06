package ayds.dodo2.tmdb

import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory

object TmdbDataModule{
    private fun getTmdbAPI(): TheMovieDBAPI = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/")
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
            .create(TheMovieDBAPI::class.java)

    fun getService(): ExternalService = TmdbServiceImp(
        getTmdbAPI(),
        TmdbResponseToTmdbMovieResolverImpl()
    )
}

