package ayds.dodo2.tmdb.external.tmdb

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonObject

interface TmdbResponseToTmdbMovieResolver {

    fun getMovieInfoFromExternalData(body: String?, movieYear: String): TmdbMovieResponse
}

internal class TmdbResponseToTmdbMovieResolverImpl :
    TmdbResponseToTmdbMovieResolver {
    private val noResults = "No Results"

    override fun getMovieInfoFromExternalData(body: String?, movieYear: String): TmdbMovieResponse {
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

    private fun jsonObjectToMovieInfoMapper(jsonObject: JsonObject?): TmdbMovieResponse =
        jsonObject?.let {
            val movieInfo = TmdbMovieResponse()
            val overviewJson = jsonObject["overview"]
            val backdropPathJson = jsonObject["backdrop_path"]
            val posterPath = getPath(jsonObject["poster_path"])

            movieInfo.title = jsonObject["title"].asString
            movieInfo.plot = getOverviewText(overviewJson, posterPath)
            backdropPathJson?.let {
                movieInfo.imageUrl = "https://image.tmdb.org/t/p/w400/${backdropPathJson.asString}" }
            movieInfo
        } ?: EmptyMovieInfo

    private fun getOverviewText(overviewJson: JsonElement?, posterPath: String) =
        overviewJson?.let { it.asString.replace("\\n", "\n") + posterPath.trimIndent()} ?: noResults

    private fun getPath(posterPath: JsonElement?): String =
        posterPath?.let { "<a href=https://image.tmdb.org/t/p/w400/${posterPath.asString}>View Movie Poster</a>" } ?: ""
}