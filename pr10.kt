fun String?.orNotSpecified(): String = this ?: "Не указано"
fun Int.isAdult(): Boolean = this >= 18
fun String.normalizeName(): String = this.trim().replace("\\s+".toRegex(), " ")
fun Double.toRatingText(): String = "${this}/10"
fun Double?.toPriceText(): String = if (this != null && this > 0) "$this ₽" else orNotSpecified()

data class User(
    var firstName: String,
    var lastName: String,
    val age: Int,
    val email: String?,
    val phone: String?
)

fun User.fullName(): String = "$firstName $lastName"
fun User.getShortInfo(): String = "${fullName()} ($age лет)"

data class Movie(
    val title: String,
    val rating: Double,
    val genre: String,
    val duration: Int
)

fun Movie.toCardText(): String = "Рейтинг: ${rating.toRatingText()} Жанр: $genre"
fun List<Movie>.topRated(): List<Movie> = filter { it.rating > 8 }
fun List<Movie>.shortMovies(): List<Movie> = filter { it.duration < 100 }

data class Product(
    val name: String,
    val price: Double?,
    val category: String
)

fun Product.toCardText(): String {
    return "Товар: $name , Категория: $category, Цена: ${price.toPriceText()}"
}

data class Student(
    val name: String,
    val email: String?,
    val group: String?,
    val phone: String?
)

fun List<Product>.averagePrice(): Double = mapNotNull { it.price }.average()
fun List<Movie>.averageRating(): Double = map { it.rating }.average()
fun List<Int>.averageValue(): Double = average()
fun Int.square(): Int = this * this

fun main() {
    val users = listOf(
        User("Иван", "Петров", 25, "ivan@mail.com", "+799999999"),
        User("Анна", "Сидорова", 16, null, "+75"),
        User("Алексей", "Иванов", 30, "alex@mail.ru", null),
        User("Мария", "Васильева", 45, "maria@mail.com", "+733333333"),
        User("Дмитрий", "Смирнов", 17, "dima@mail.com", "+755153148")
    )

    println("Полная информация о пользователях:")
    users.forEach { user -> println("${user.getShortInfo()}, Email: ${user.email.orNotSpecified()}, Телефон: ${user.phone.orNotSpecified()}")
    }

    println("Cовершеннолетние:")
    users.filter { it.age.isAdult() }.forEach { println(it.fullName()) }

    println("Без email:")
    users.filter { it.email == null }.forEach { println(it.fullName()) }

    println("Без телефона:")
    users.filter { it.phone == null }.forEach { println(it.fullName()) }

    val movies = listOf(
        Movie("Начало", 8.8, "Экшен", 148),
        Movie("Интерстеллар", 8.6, "Фантастика", 169),
        Movie("Назад в будущее", 8.5, "Приключения", 116),
        Movie("Большой куш", 8.5, "Комедия", 102),
        Movie("Достать ножи", 8.0, "Детектив", 130),
        Movie("Джуманджи", 7.0, "Приключения", 119),
        Movie("Один дома", 7.6, "Комедия", 103),
        Movie("Терминал", 7.4, "Драма", 124),
        Movie("Карты, деньги, два ствола", 8.2, "Криминал", 107),
        Movie("Нечего терять", 7.7, "Боевик", 97)
    )

    println("Рейтинг > 8:")
    movies.topRated().forEach { println("- ${it.title}") }

    println("Время < 100 минут:")
    movies.shortMovies().forEach { println("- ${it.title} (${it.duration} мин)") }

    println("Карточки фильмов:")
    movies.forEach { println(it.toCardText()) }

    val newUsers = mutableListOf<User>()
    repeat(5) { index ->
        val user = User("", "", 0, null, null).apply {
            firstName = "Новый_${index + 1}"
            lastName = "Пользователь"
            age = Random.nextInt(15, 60)
            email = if (Random.nextBoolean()) "new_user_${index}@mail.com" else null
        }.also {
            println("Пользователь ${it.firstName} успешно создан")
            newUsers.add(it)
        }
    }

    val searchEmail = "alex@mail.ru"
    val foundUser = users.find { it.email == searchEmail }

    foundUser?.let {
        println("Пользователь найден!")
        println("Имя: ${it.fullName()}, Возраст: ${it.age}")
    } ?: run {
        println("Пользователь не найден")
    }

    movies.run {
        val count = size
        val avgRating = averageRating().roundToOneDecimal()
        val maxRating = maxByOrNull { it.rating }?.rating ?: 0.0
        val minRating = minByOrNull { it.rating }?.rating ?: 0.0

        println("Отчет по каталогу фильмов:")
        println("Количество фильмов: $count")
        println("Средний рейтинг: $avgRating")
        println("Максимальный рейтинг: $maxRating")
        println("Минимальный рейтинг: $minRating")
    }

    val selectedUser = users.random()
    with(selectedUser) {
        println("Информация о пользователе:")
        println("Имя: $firstName")
        println("Возраст: $age")
        println("Email: ${email.orNotSpecified()}")
        println("Телефон: ${phone.orNotSpecified()}")
    }

    val students = listOf(
        Student("Сергей", "serg@uni.ru", "Группа А-101", "+7-111-000"),
        Student("Ольга", null, null, "+7-222-000"),
        Student("Виктор", "viktor@uni.ru", "Группа Б-202", null)
    )

    students.forEach { student -> println("${student.name}: Email - ${student.email.orNotSpecified()}, Группа - ${student.group.orNotSpecified()}, Телефон - ${student.phone.orNotSpecified()}")
    }

    val products = listOf(
        Product("Ноутбук", 85000.0, "Электроника"),
        Product("Книга 'Kotlin для профессионалов'", 1200.0, "Литература"),
        Product("Наушники", 5000.0, "Электроника"),
        Product("Курс на Udemy", 15000.0, "Образование"),
        Product("Смартфон", null, "Электроника"), // Без цены
        Product("Обед в столовой", 350.0, "Питание"),
        Product("Подписка Premium", 999.0, "Сервисы"),
        Product("Кофе американо", 150.0, "Питание"),
        Product("Внешний SSD диск", 12000.0, "Электроника"),
        Product("Рюкзак", 4500.0, "Аксессуары")
    )

    println("Карточки товаров:")
    products.forEach { println(it.toCardText()) }

    println("Дороже 10000:")
    products.filter { it.price != null && it.price!! > 10000 }.forEach { println("- ${it.name} (${it.price!!.toPriceText()})") }

    println("Без цены:")
    products.filter { it.price == null }.forEach { println("- ${it.name}") }

    println("Средняя цена: ${products.averagePrice().roundToOneDecimal()} ₽")
    
    println("Средний рейтинг фильмов: ${movies.averageRating().roundToOneDecimal()}")
    println("Среднее значение списка чисел [1, 5, 10]: ${listOf(1, 5, 10).averageValue()}")
    println("Квадрат числа 7: ${7.square()}")
}
