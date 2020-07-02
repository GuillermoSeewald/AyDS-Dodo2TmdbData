package ayds.dodo2.tmdb.external

import ayds.dodo.movieinfo.home.model.entities.OmdbMovie
import ayds.dodo.movieinfo.moredetails.model.entities.TmdbMovie

interface ExtraInfoExternalService {
    fun getMovieInfo(movie: OmdbMovie): TmdbMovie
}