package ayds.dodo2.tmdb

import ayds.dodo2.tmdb.entities.EmptyTmdbMovie
import ayds.dodo2.tmdb.entities.TmdbMovie
import ayds.dodo2.tmdb.entities.*
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

interface TmdbResponseToTmdbMovieResolver {

    fun getMovieInfoFromExternalData(body: String?, movieYear: String): TmdbMovie
}

internal class TmdbResponseToTmdbMovieResolverImpl :
    TmdbResponseToTmdbMovieResolver {
    private val noResults = "No Results"

    override fun getMovieInfoFromExternalData(body: String?, movieYear: String): TmdbMovie {
        return jsonObjectToMovieInfoMapper(createJsonObject(body,movieYear))
    }

    private fun createJsonObject(body: String?, movieYear: String): JsonObject? {
        val gson = Gson()
        val jsonObject = gson.fromJson(body, JsonObject::class.java)
        val resultIterator: Iterator<JsonElement> = jsonObject["results"].asJsonArray.iterator()
        var result: JsonObject? = null

        while (resultIterator.hasNext() && result == null) {
            val nextResult = resultIterator.next().asJsonObject
            if (foundYear(nextResult["release_date"], movieYear))
                result = nextResult
        }
        return result
    }

    private fun foundYear(releaseDate: JsonElement?, movieYear: String) =
        releaseDate?.let {
            val year = releaseDate.asString.split("-").toTypedArray()[0]
            year == movieYear
        } ?: false

    private fun jsonObjectToMovieInfoMapper(jsonObject: JsonObject?): TmdbMovie =
        jsonObject?.let {
            val movieInfo = TmdbMovie()
            val overviewJson = jsonObject["overview"]
            val backdropPathJson = jsonObject["backdrop_path"]

            movieInfo.title = jsonObject["title"].asString
            movieInfo.plot = getOverviewText(overviewJson)
            movieInfo.posterPath = getPath(jsonObject["poster_path"])
            backdropPathJson?.let {
                movieInfo.imageUrl = "https://image.tmdb.org/t/p/w400/${backdropPathJson.asString}" }
            movieInfo
        } ?: EmptyTmdbMovie

    private fun getOverviewText(overviewJson: JsonElement?) =
        overviewJson?.asString?.replace("\\n", "\n")?.trimIndent() ?: noResults

    private fun getPath(posterPath: JsonElement?): String =
        posterPath?.let { "https://image.tmdb.org/t/p/w400/${posterPath.asString}" } ?: ""
}