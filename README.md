# AyDS-Dodo2TmdbData
Esta librería permite obtener información acerca de una determinada película según su título y año mediante el uso de la api TMDB.

### Utilización de la librería
La comunicación con los servicios de esta librería se realizará mediante la interfaz denominada **TmdbService**, la cual cuenta con un servicio llamado *getMovieInfo*.

```
fun getMovieInfo(movieTitle: String, movieYear: String): TmdbMovie
```
 * Parámetro:
   * movieTitle: String -> Título de la película buscada
   * movieYear: String -> Año de la película buscada

 * Retorno:
   * TmdbMovie -> Encapsula la información buscada de la película solicitada. En caso de que que no se encuentre información relacionada a esa película u ocurra un error se obtendrá una EmptyTmdbMovie, la cual es la instancia por defecto de TmdbMovie.

Para la utilización de este servicio se provee una implementación de dicha interface la cual se obtiene invocando al servicio *getService* ubicado en el módulo **TmdbDataModule**

```
fun getService(): TmdbService
```

### Entidades
#### TmdbMovie
Un elemento de tipo TmdbMovie contiene la información acerca de una película dada por el siguiente conjunto de elementos:
* *title* (título de la película)
* *plot* (descipción de la trama)
* *imageUrl* (dirección url de la imagen de la película)
* *posterUrl* (dirección url del poster de la película)
