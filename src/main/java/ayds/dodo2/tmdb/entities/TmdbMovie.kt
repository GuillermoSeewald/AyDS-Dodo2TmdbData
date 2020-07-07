package ayds.dodo2.tmdb.entities

open class TmdbMovie {
    var title = ""
    var plot = ""
    var imageUrl = "https://www.themoviedb.org/assets/2/v4/logos/256x256-dark-bg-01a111196ed89d59b90c31440b0f77523e9d9a9acac04a7bac00c27c6ce511a9.png"
    var posterUrl = ""
}

object EmptyTmdbMovie: TmdbMovie()