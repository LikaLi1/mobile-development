data class Movie(
    val title: String,
    val genre: String,
    val year: Int,
    val rating: Double,
    val durationMinutes: Int,
    val isAvailable: Boolean
)

data class MovieCard(
    val title: String,
    val info: String
)

fun main() {
    val movies = listOf(
        Movie("первое", "Action", 2019, 7.8, 110, true),
        Movie("второе", "Comedy", 2021, 8.2, 95, false),
        Movie("третье", "Drama", 2020, 8.4, 130, true),
        Movie("четвертое", "Sci-Fi", 2022, 9.0, 140, true),
        Movie("пятое", "Horror", 2018, 6.5, 100, false),
        Movie("шестое", "Animation", 2023, 8.7, 85, true),
        Movie("седьмое", "Action", 2021, 7.2, 105, true),
        Movie("восьмое", "Comedy", 2019, 8.1, 98, false),
        Movie("девятое", "Drama", 2023, 9.1, 115, true),
        Movie("десятое", "Horror", 2022, 4.8, 92, true)
    )

    println("Доступные:")
    movies.filter { it.isAvailable }.forEach { println(it) }

    println("Рейтинг выше 8:")
    movies.filter { it.rating > 8.0 }.forEach { println(it) }

    println("Новинки:")
    movies.filter { it.year > 2020 }.forEach { println(it) }

    println("Меньше 10 минут:")
    movies.filter { it.durationMinutes < 100 }.forEach { println(it) }

    println("Action:")
    movies.filter { it.genre == "Action" }.forEach { println(it) }

  println("Список1 :")
  movies.sortedBy { it.year }.forEach { println(it) }

println("Список2 :")
movies.sortedByDescending { it.rating }.forEach { println(it) }

println("Список 3 :")
movies.sortedBy { it.durationMinutes }.forEach { println(it) }

  println("Топ-3:")
  movies.sortedByDescending { it.rating }.take(3).forEach { println(it) }

val searchTitle = "второе"
val foundMovie = movies.find { it.title == searchTitle }
if (foundMovie != null) {
    println("Фильм: ${foundMovie.title}, рейтинг: ${foundMovie.rating}")
} else {
    println("Фильм не найден")
}

println("Хоть 1 Horror?")
println(movies.any { it.genre == "Horror" })

println("Ниже 5?")
println(movies.any { it.rating < 5.0 })

println("Блокировка(....)?")
println(movies.any { !it.isAvailable })

println("Выше 0?")
println(movies.all { it.rating > 0 })

println("Нет ли старых?")
println(movies.all { it.year > 1980 })

println("Все ли фильмы доступны?")
println(movies.all { it.isAvailable })

println("То что разрешено:")
println(movies.count { it.isAvailable })

println("Выше 8.0:")
println(movies.count { it.rating > 8.0 })

println("Все жанры:")
movies.groupBy { it.genre }.forEach { (genre, list) ->
    println("$genre: ${list.size}")
}

println("Новинки:")
println(movies.count { it.year > 2020 })

val moviesByGenre = movies.groupBy { it.genre }
println("Жанры:")
moviesByGenre.forEach { (genre, list) ->
    println("Жанр: $genre")
    println("Количество фильмов: ${list.size}")
    println("Фильмы: ${list.map { it.title }}")
}

val moviesByYear = movies.groupBy { it.year }
println("Группировка по году выпуска:")
moviesByYear.forEach { (year, list) ->
    println("Год: $year, Количество: ${list.size}")
}

val moviesByAvailability = movies.groupBy { it.isAvailable }
println("Группировка по доступности:")
moviesByAvailability.forEach { (available, list) ->
    println("Доступных: $available, Количество: ${list.size}")
}

val movieCards = movies.map { movie ->
    MovieCard(
        title = movie.title,
        info = "Жанр: ${movie.genre}, рейтинг: ${movie.rating}, год: ${movie.year}"
    )
}
println("Карточки фильмов:")
movieCards.forEach { println(it) }

val recommendedTitles = movies
    .filter { it.isAvailable }
    .filter { it.rating > 7.5 }
    .sortedByDescending { it.rating }
    .map { it.title }

println("Рекомендуемые фильмы: $recommendedTitles")

fun filterMovies(
    movies: List<Movie>,
    condition: (Movie) -> Boolean
): List<Movie> {
    return movies.filter(condition)
}

val availableMovies = filterMovies(movies) { it.isAvailable }
val comedyMovies = filterMovies(movies) { it.genre == "Comedy" }
val highRatedMovies = filterMovies(movies) { it.rating > 8.5 }
val moviesAfter2020 = filterMovies(movies) { it.year > 2020 }
val longMovies = filterMovies(movies) { it.durationMinutes > 120 }

println("Доступные: $availableMovies")
println("Комедии: $comedyMovies")
println("Рейтинг выше 8.5: $highRatedMovies")
println("После 2020: $moviesAfter2020")
println("Больше 120 минут: $longMovies")

fun processRating(
    rating: Double,
    operation: (Double) -> Double
): Double {
    return operation(rating)
}

val originalRating = 8.0
println("Рейтинг: $originalRating")
println("Увеличение на 0.2: ${processRating(originalRating) { it + 0.2 }}")
println("Уменьшение на 0.5: ${processRating(originalRating) { it - 0.5 }}")
println("Перевод в 5-балльную систему: ${processRating(originalRating) { it / 2 }}")

fun analyzeMovies(
    movies: List<Movie>,
    filterCondition: (Movie) -> Boolean,
    sortCondition: (Movie) -> Double
): List<Movie> {
    return movies.filter(filterCondition).sortedBy { sortCondition(it) }
}

println("Анализ: доступные фильмы, по рейтингу:")
val availableSortedByRating = analyzeMovies(movies, { it.isAvailable }, { it.rating })
availableSortedByRating.forEach { println(it) }

println("Фильмы после 2020, по рейтингу:")
val after2020Sorted = analyzeMovies(movies, { it.year > 2020 }, { it.rating })
after2020Sorted.forEach { println(it) }

println("Меньше 100 минут:")
val shortMovies = analyzeMovies(movies, { it.durationMinutes < 100 }, { it.durationMinutes })
shortMovies.forEach { println(it) }

println("Рекомендуемые фильмы:")
movies.filter {
    it.isAvailable &&
    it.rating > 7.5 &&
    it.durationMinutes < 140 &&
    it.genre != "Horror"
}.forEach {
    println("Рекомендуем: ${it.title} — рейтинг ${it.rating}")
}
}
