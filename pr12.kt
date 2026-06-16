fun parseAge(
  input: String
): Int {
    val age = input.toIntOrNull() ?: return 0
    return if (age < 0) 0 else age
}

fun registerUser(
  login: String, 
  password: String, 
  age: Int
) {
    require(login.isNotBlank()) { "Логин не может быть пустым" }
    require(password.length >= 6) { "Пароль должен содержать минимум 6 символов" }
    require(age >= 16) { "Возраст должен быть не меньше 16 лет" }
    
    println("Пользователь $login успешно зарегистрирован!")
}

class BankAccount(var balance: Double) {
    fun withdraw(amount: Double) {
        require(amount > 0) { "Сумма снятия должна быть положительной" }
        check(balance >= amount) { "Недостаточно средств на счёте" }
        
        balance -= amount
        println("Снято $amount. Остаток: ${balance.roundToTwoDecimals()} ₽")
    }
}

fun getProductByIndex(
  products: List<String>, 
  index: Int): String {
    return products.getOrNull(index) ?: "Товар не найден"
}

sealed class LoginResult {
    data class Success(val login: String) : LoginResult()
    object WrongPassword : LoginResult()
    object UserBlocked : LoginResult()
    data class ServerError(val message: String) : LoginResult()
}

fun login(
  login: String, 
  password: String
): LoginResult {
    if (login == "blocked") return LoginResult.UserBlocked
    if (password != "1234") return LoginResult.WrongPassword
    if (Random.nextBoolean()) return LoginResult.ServerError("Сервер недоступен")
    return LoginResult.Success(login)
}

sealed class ValidationResult {
    object Success : ValidationResult()
    data class Error(val message: String) : ValidationResult()
}

fun validateEmail(email: String): ValidationResult {
    if (email.isEmpty()) return ValidationResult.Error("Email не может быть пустым")
    if (!email.contains("@")) return ValidationResult.Error("Email должен содержать @")
    if (!email.contains(".")) return ValidationResult.Error("Email должен содержать .")
    return ValidationResult.Success
}

data class User(val id: Int, val name: String)

fun loadUser(
  id: Int
): Result<User> {
    return runCatching {
        require(id > 0) { "ID пользователя должен быть больше 0" }
        User(id, "Пользователь_$id")
    }
}

fun createOrder(products: List<String>) {
    check(products.isNotEmpty()) { "Корзина не должна быть пустой" }
    println("Заказ оформлен! Товаров: ${products.size}")
}

fun parsePrice(input: String): Result<Double> {
    return runCatching {
        val price = input.toDouble()
        require(price > 0) { "Цена должна быть больше нуля" }
        price
    }
}

enum class OrderStatus { CREATED, PAID, DELIVERED, CANCELED }

fun deliverOrder(
  status: OrderStatus
) {
    check(status == OrderStatus.PAID) { "Доставить можно только заказ со статусом 'PAID'" }
    println("Статус изменен на DELIVERED")
}

class UserNotFoundException(message: String) : Exception(message)

fun findUser(
  id: Int
): User {
    if (id == 1) return User(1, "Иван")
    throw UserNotFoundException("Пользователь с ID=$id не найден")
}

fun parsePort(
  portText: String
): Int {
    return try {
        val port = portText.toInt()
        if (port in 1..65535) port else 8080
    } catch (e: NumberFormatException) {
        8080
    }
}

fun setGrade(
  grade: Int
) {
    require(grade in 2..5) { "Оценка должна быть от 2 до 5" }
    println("Оценка сохранена: $grade")
}

fun main() {
    println(parseAge("25"))
    println(parseAge("abc"))
    println(parseAge("-5"))

    registerUser("user1", "qwerty123", 20)

    val account = BankAccount(1000.0)
    account.withdraw(200.0)

    val goods = listOf("Хлеб", "Молоко", "Сыр")
    println(getProductByIndex(goods, 1))
    println(getProductByIndex(goods, 10))

    when (val result = login("admin", "wrong_pass")) {
        is LoginResult.Success -> println("Вход выполнен: ${result.login}")
        LoginResult.WrongPassword -> println("Неверный пароль")
        LoginResult.UserBlocked -> println("Пользователь заблокирован")
        is LoginResult.ServerError -> println("Ошибка сервера: ${result.message}")
    }

    when (val result = validateEmail("test@mail.com")) {
        is ValidationResult.Success -> println("Email валиден")
        is ValidationResult.Error -> println(result.message)
    }

    loadUser(5).onSuccess { user ->
        println("Пользователь загружен: ${user.name}")
    }.onFailure { error ->
        println("Ошибка загрузки: ${error.message}")
    }

    createOrder(listOf("Книга"))
    
    val priceResult = parsePrice("1500.5")
    val finalPrice = priceResult.getOrElse { 0.0 }
    println("Итоговая цена: ${finalPrice.roundToTwoDecimals()} ₽")

    deliverOrder(OrderStatus.PAID)

    try {
        val user = findUser(2)
        println(user)
    } catch (e: UserNotFoundException) {
        println(e.message)
    }

    println("Порт 1: ${parsePort("8080")}")
    println("Порт 2: ${parsePort("abc")}")
    println("Порт 3: ${parsePort("70000")}")

    setGrade(4)
}
