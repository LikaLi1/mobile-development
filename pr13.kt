class DatabaseConnection {
    init { println("Подключение к базе данных создано") }
}

class Report {
    val database by lazy { DatabaseConnection() }
    val report by lazy { "Отчёт сформирован" }
}

class Product(
  var name: String
) {
    var price: Double by Delegates.observable(0.0) { _, oldValue, newValue ->
        println("Цена товара '$name' изменена: $oldValue -> $newValue")
    }
}

class Employee(
  val name: String
) {
    var age: Int by Delegates.vetoable(18) { _, _, newAge ->
        if (newAge < 18 || newAge > 80) {
            println("Ошибка: Возраст должен быть от 18 до 80 лет.")
            false
        } else {
            true
        }
    }
}

class Discount {
    var percent: Int by Delegates.vetoable(0) { _, _, newPercent ->
        newPercent in 0..90
    }
}

object AppSettings {
    var appName = "MyApp"
    var version = "1.0"
    var theme = "Dark"
}

object UserStatistics {
    private var userCount = 0
    fun registerUser() {
        userCount++
        println("Пользователь зарегистрирован. Всего: $userCount")
    }
}

data class User(
  val login: String
)
class UserFactory {
    companion object {
        fun createGuest(): User = User("guest_${System.currentTimeMillis()}")
        fun createAdmin(login: String): User = User("admin_$login")
    }
}

data class ProductData(
  val title: String, 
  val cost: Double
)
class ProductCatalog {
    companion object {
        fun demoLaptop() = ProductData("Ноутбук", 65000.0)
        fun demoPhone() = ProductData("Смартфон", 35000.0)
        fun demoTablet() = ProductData("Планшет", 25000.0)
    }
}

val settings = object {
    val theme = "Light"
    val fontSize = 14
    val language = "RU"
}

interface Logger {
  fun log(message: String) 
}
class ConsoleLogger : Logger {
    override fun log(message: String) = println("[LOG] $message")
}

class FileLogger : Logger {
    override fun log(message: String) = println("[FILE] Запись в файл: $message")
}

class UserService(logger: Logger) : Logger by logger

interface Notifier { fun notify(message: String) }
class EmailNotifier : Notifier {
    override fun notify(message: String) = println("[EMAIL] Уведомление отправлено: $message")
}
class SmsNotifier : Notifier {
    override fun notify(message: String) = println("[SMS] Сообщение доставлено: $message")
}

class OrderService(private val notifier: Notifier) : Notifier by notifier

fun main() {
    val reportGenerator = Report()
    println("Программа запущена Объекты еще не созданы")
    println(reportGenerator.report)
    reportGenerator.database

    val laptop = Product("Игровой ноутбук")
    laptop.price = 75000.0
    laptop.price = 80000.0
    laptop.price = 79999.99

    val employee = Employee("Иван Иванов")
    employee.age = 25
    employee.age = 15
    employee.age = 90

    val discount = Discount()
    discount.percent = 50
    println("Текущая скидка: ${discount.percent}%")
    discount.percent = 100
    println("Текущая скидка после ошибки: ${discount.percent}%")

    println(AppSettings.appName)
    AppSettings.theme = "Light"
    println(AppSettings.version)

    UserStatistics.registerUser()
    UserStatistics.registerUser()
    UserStatistics.registerUser()

    val guest = UserFactory.createGuest()
    val admin = UserFactory.createAdmin("SuperRoot")
    println(guest)
    println(admin)

    val productsList = listOf(
        ProductCatalog.demoLaptop(),
        ProductCatalog.demoPhone(),
        ProductCatalog.demoTablet()
    )
    productsList.forEach { println(it) }

    println("Настройки темы: ${settings.theme}, Размер шрифта: ${settings.fontSize}")

    val consoleService = UserService(ConsoleLogger())
    consoleService.log("Пользователь вошел в систему")

    val fileService = UserService(FileLogger())
    fileService.log("Изменен профиль пользователя")

    val emailOrderService = OrderService(EmailNotifier())
    emailOrderService.notify("Заказ №123 оплачен")

    val smsOrderService = OrderService(SmsNotifier())
    smsOrderService.notify("Заказ №123 отправлен")

    object ApiClient {
        val connection by lazy { println("HTTP клиент создан"); "Connected" }
    }
    println("API")
    println(ApiClient.connection)
    println(ApiClient.connection)

    data class ServerStats(var totalChecks: Int = 0, var failedChecks: Int = 0)
    object ServerMonitor { val stats = ServerStats()}

    val serverA = Server("Server-A")
    serverA.status = "ONLINE"
    serverA.cpuLoad = 75
    serverA.memoryLoad = 40
    serverA.cpuLoad = 110
}
