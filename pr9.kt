enum class TicketStatus(
  val description: String
) {
    NEW("Новая"),
    IN_PROGRESS("В работе"),
    WAITING_FOR_USER("Ожидает ответа пользователя"),
    RESOLVED("Решена"),
    CLOSED("Закрыта")
}
data class Ticket(
    val number: Int,
    val userName: String,
    val subject: String,
    val status: TicketStatus,
    val priority: Int
)

enum class UserRole(
    val title: String,
    val canEdit: Boolean,
    val canDelete: Boolean
) {
    ADMIN("Администратор", true, true),
    MODERATOR("Модератор", true, false),
    TEACHER("Преподаватель", true, false),
    STUDENT("Студент", false, false),
    GUEST("Гость", false, false)
}
data class User(
  val login: String, val email: String, val role: UserRole
)

sealed class LoginResult
object Success : LoginResult()
object WrongPassword : LoginResult()
object UserBlocked : LoginResult()
object ServerError : LoginResult()

data class Book(val title: String, val author: String, val year: Int, val genre: String)
sealed class SearchResult
class Found(val book: Book) : SearchResult()
object NotFound : SearchResult()
object Error : SearchResult()
val library = listOf(
    Book("Мастер и Маргарита", "Булгаков М.А.", 1966, "Роман"),
    Book("Война и мир", "Толстой Л.Н.", 1869, "Эпопея"),
    Book("Преступление и наказание", "Достоевский Ф.М.", 1866, "Философский роман"),
    Book("Пикник на обочине", "Стругацкие А. и Б.", 1972, "Фантастика"),
    Book("Трудно быть богом", "Стругацкие А. и Б.", 1964, "Фантастика"),
    Book("Евгений Онегин", "Пушкин А.С.", 1833, "Роман в стихах"),
    Book("Собачье сердце", "Булгаков М.А.", 1925, "Повесть"),
    Book("Мертвые души", "Гоголь Н.В.", 1842, "Поэма"),
    Book("Герой нашего времени", "Лермонтов М.Ю.", 1840, "Роман"),
    Book("Анна Каренина", "Толстой Л.Н.", 1877, "Роман")
)
fun searchBook(title: String): SearchResult =
    library.find { it.title.equals(title, ignoreCase = true) }?.let { Found(it) } ?: NotFound

enum class CourseStatus {
    PLANNED, STARTED, IN_PROGRESS, FINISHED, ARCHIVED
}
data class Course(
  val name: String, 
  val teacher: String, 
  val status: CourseStatus
)

enum class PaymentMethod { CARD, CASH, SBP, CRYPTO }
sealed class PaymentResult
object SuccessPayment : PaymentResult()
object Declined : PaymentResult()
object ErrorPayment : PaymentResult()
fun makePayment(amount: Double, method: PaymentMethod): PaymentResult {
    return when (method) {
        PaymentMethod.CASH -> SuccessPayment
        PaymentMethod.CARD -> if (amount > 0 && amount < 10000) SuccessPayment else Declined
        PaymentMethod.SBP, PaymentMethod.CRYPTO -> if (Random.nextBoolean()) SuccessPayment else ErrorPayment
    }
}

enum class ServerStatus {
    ONLINE, OFFLINE, MAINTENANCE, OVERLOADED
}
data class Server(
  val name: String, 
  val ipAddress: String, 
  val status: ServerStatus
)

sealed class OperationResult<T>
data class Success<T>(val data: T) : OperationResult<T>()
object Error : OperationResult<Nothing>()
object Loading : OperationResult<Nothing>()
object Empty : OperationResult<Nothing>()
fun fetchBooks(): OperationResult<List<Book>> = if (library.isNotEmpty()) Success(library) else Empty
fun <T> handleResult(result: OperationResult<T>) {
    when (result) {
        is Success -> println("Успешная операция. Данные получены: ${result.data.size} элементов.")
        is Error -> println("Операция завершилась ошибкой.")
        is Loading -> println("Данные загружаются...")
        is Empty -> println("Результат пуст. Данных нет.")
    }
}

fun main() {
    val tickets = List(12) { index ->
        Ticket(
            number = index + 1,
            userName = "User ${index + 1}",
            subject = "Проблема с ${listOf("интернетом", "печатью", "доступом")[Random.nextInt(3)]}",
            status = TicketStatus.values().random(),
            priority = Random.nextInt(1, 11)
        )
    }
    println("Все заявки:")
    tickets.forEach { println(it) }

    println("Количество заявок:")
    tickets.groupingBy { it.status }.eachCount().forEach { (status, count) ->
        println("${status.name}: $count шт.")
    }

    val openStatuses = setOf(TicketStatus.NEW, TicketStatus.IN_PROGRESS, TicketStatus.WAITING_FOR_USER)
    val openTickets = tickets.filter { it.status in openStatuses }
    println("Открытые заявки ($openStatuses):")
    openTickets.forEach { println(it) }

    tickets.take(3).forEach { ticket ->
        val desc = when (ticket.status) {
            TicketStatus.NEW -> "Заявка создана"
            TicketStatus.IN_PROGRESS -> "Специалист работает над заявкой"
            TicketStatus.WAITING_FOR_USER -> "Заявка ожидает ответа пользователя"
            TicketStatus.RESOLVED -> "Решение найдено"
            TicketStatus.CLOSED -> "Заявка закрыта"
        }
        println("${ticket.number}: $desc")
    }

    val users = listOf(
        User("admin_root", "root@site.ru", UserRole.ADMIN),
        User("moderator_vasya", "vasya@mail.ru", UserRole.MODERATOR),
        User("teacher_petrov", "petrov@school.ru", UserRole.TEACHER),
        User("student_ivanov", "ivanov@stud.ru", UserRole.STUDENT),
        User("guest_guest", "guest@guest.com", UserRole.GUEST),
        User("another_admin", "adm@corp.ru", UserRole.ADMIN),
        User("mod_mary", "mary@forum.ru", UserRole.MODERATOR),
        User("teach_sidorov", "sidorov@uni.ru", UserRole.TEACHER)
    )

    fun canDelete(user: User): Boolean = user.role.canDelete

    println("Только администраторы:")
    users.filter { it.role == UserRole.ADMIN }.forEach { println(it.login) }

    println("Удаление:")
    users.filter { canDelete(it) }.forEach { println(it.login) }

    println("Количество пользователей:")
    users.groupingBy { it.role }.eachCount().forEach { (role, count) ->
        println("${role.title}: $count")
    }

    fun login(login: String, password: String): LoginResult {
        return if (login == "user" && password == "pass") Success
        else if (password != "pass") WrongPassword
        else if (login == "blocked_user") UserBlocked
        else ServerError
    }
    fun processLogin(result: LoginResult) {
        val message = when (result) {
            is Success -> "Вход выполнен"
            is WrongPassword -> "Неверный пароль"
            is UserBlocked -> "Пользователь заблокирован"
            is ServerError -> "Ошибка сервера"
        }
        println(message)
    }
    processLogin(login("user", "wrong_pass"))
    processLogin(login("blocked_user", "pass"))
    processLogin(login("user", "pass"))

    val query = "мастер и маргарита"
    val result = searchBook(query)
    when (result) {
        is Found -> println("Книга найдена: '${result.book.title}' автора ${result.book.author}.")
        is NotFound -> println("Книга '$query' не найдена.")
        is Error -> println("Произошла ошибка при поиске.")
    }

    val courses = listOf(
        Course("Kotlin Basics", "Иван Иванов", CourseStatus.STARTED),
        Course("Android Dev", "Алексей Петров", CourseStatus.IN_PROGRESS),
        Course("Web Security", "Сергей Сидоров", CourseStatus.FINISHED),
        Course("Data Science", "Ольга Кузнецова", CourseStatus.PLANNED),
        Course("AI Ethics", "Дмитрий Смирнов", CourseStatus.ARCHIVED),
        Course("UI/UX Design", "Анна Волкова", CourseStatus.STARTED),
        Course("Databases", "Павел Козлов", CourseStatus.IN_PROGRESS),
        Course("Algorithms", "Георгий Морозов", CourseStatus.FINISHED),
        Course("Cloud Computing", "Татьяна Орлова", CourseStatus.PLANNED),
        Course("DevOps", "Максим Лебедев", CourseStatus.ARCHIVED)
    )
    println("Количество курсов каждого статуса:")
    courses.groupingBy { it.status }.eachCount().forEach { (status, count) ->
        println("$status: $count")
    }
    val activeCourses = courses.filter { it.status in setOf(CourseStatus.STARTED, CourseStatus.IN_PROGRESS) }
    println("Активные курсы:")
    activeCourses.forEach { println("- ${it.name}") }

    val paymentResult = makePayment(1500.0, PaymentMethod.CARD)
    when (paymentResult) {
        is SuccessPayment -> println("Оплата прошла успешно!")
        is Declined -> println("Отказано в проведении операции.")
        is ErrorPayment -> println("Произошла техническая ошибка.")
    }

    val servers = listOf(
        Server("Main DB", "0", ServerStatus.ONLINE),
        Server("Backup Storage", "1", ServerStatus.OFFLINE),
        Server("Web Frontend", "2", ServerStatus.ONLINE),
        Server("Auth Service", "3", ServerStatus.MAINTENANCE),
        Server("API Gateway", "4", ServerStatus.OVERLOADED),
        Server("Mail Relay", "5", ServerStatus.ONLINE),
        Server("Logs Collector", "6", ServerStatus.OFFLINE),
        Server("Cache Node", "7", ServerStatus.ONLINE)
    )
    println("Рабочие серверы:")
    servers.filter { it.status == ServerStatus.ONLINE }.forEach { println(it.name) }

    println("Перегруженные:")
    servers.filter { it.status == ServerStatus.OVERLOADED }.forEach { println(it.name) }

    println("Рекомендации администратора:")
    servers.forEach { server ->
        val advice = when (server.status) {
            ServerStatus.ONLINE -> "Работа в штатном режиме"
            ServerStatus.OFFLINE -> "Требуется проверка"
            ServerStatus.MAINTENANCE -> "Плановое обслуживание"
            ServerStatus.OVERLOADED -> "Необходимо перераспределить нагрузку"
        }
        println("${server.name} (${server.ipAddress}): $advice")
    }

    val booksOperationResult = fetchBooks()
    handleResult(booksOperationResult)
}
