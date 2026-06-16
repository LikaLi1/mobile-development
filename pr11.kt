interface Printable {
    fun printInfo()
}

class Box<T>(var value: T?) {
    fun getValue(): T? = value
    fun setValue(newValue: T?) { value = newValue }
}

class Catalog<T> {
    private val items: MutableList<T> = mutableListOf()
    fun add(item: T) { items.add(item) }
    fun remove(item: T) { items.remove(item) }
    fun getAll(): List<T> = items.toList()
    fun find(condition: (T) -> Boolean): T? = items.find(condition)
    fun count(condition: (T) -> Boolean): Int = items.count(condition)
}

interface Repository<T> {
    fun add(item: T)
    fun remove(item: T)
    fun update(oldItem: T, newItem: T)
    fun getAll(): List<T>
    fun find(id: String): T?
}
class MemoryRepository<T> : Repository<T> {
    private val storage: MutableMap<String, T> = mutableMapOf()
    override fun add(item: T) {}
    override fun remove(item: T) {}
    override fun update(oldItem: T, newItem: T) {}
    override fun getAll(): List<T> = storage.values.toList()
    override fun find(id: String): T? = storage[id]
}

data class User(
  val id: String, 
  val name: String, 
  val age: Int) : Printable {
    override fun printInfo() = println("Пользователь: $name, Возраст: $age")
}
data class Product(val id: String, val title: String, var price: Double) : Printable {
    override fun printInfo() = println("Товар: '$title', Цена: ${price.toPriceText()}")
}
data class Course(val id: String, val title: String, val isActive: Boolean) : Printable {
    override fun printInfo() = println("Курс: '$title', Активен: $isActive")
}
data class Student(val name: String, val grades: List<Int>)

fun <T : Number> averageValue(items: List<T>): Double =
    if (items.isEmpty()) 0.0 else items.sumOf { it.toDouble() } / items.size

fun <T : Number> calculateAverage(values: List<T>): Double = averageValue(values)

fun <T> List<T>.printAll() = this.forEach { println(it) }
fun <T> List<T>.secondOrNull(): T? = if (this.size >= 2) this[1] else null
fun <T> List<T>.lastOrDefault(defaultValue: T): T = this.lastOrNull() ?: defaultValue

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
    object Loading : Result<Nothing>()
}

fun getUser(): Result<User> = Result.Success(User("u1", "Иван Петров", 30))
fun getProducts(): Result<List<Product>> = Result.Success(
    listOf(Product("p1", "Ноутбук", 95000.0), Product("p2", "Мышь", 1500.0))
)

class Cache<T> {
    private var cachedValue: T? = null
    fun save(value: T) { cachedValue = value }
    fun get(): T? = cachedValue
    fun clear() { cachedValue = null }
    fun isEmpty(): Boolean = cachedValue == null
}

fun <T> findFirst(items: List<T>, condition: (T) -> Boolean): T? = items.firstOrNull(condition)

fun <T> printCollection(items: List<T>) {
    println("Коллекция (${items.size} элементов")
    items.printAll()
}

fun <T> countMatching(items: List<T>, condition: (T) -> Boolean): Int = items.count(condition)

data class PairBox<K, V>(
  val key: K, 
  val value: V)

fun printSize(items: List<*>) {
    println("Размер списка: ${items.size}")
    items.firstOrNull()?.let {
        println("Тип первого элемента: ${it::class.simpleName}")
    }
}

class DataManager<T> {
    private val items: MutableList<T> = mutableListOf()
    fun add(item: T) { items.add(item) }
    fun remove(item: T) { items.remove(item) }
    fun find(condition: (T) -> Boolean): T? = items.find(condition)
    fun sort(comparator: Comparator<T>) { items.sortWith(comparator) }
    fun filter(condition: (T) -> Boolean): List<T> = items.filter(condition)
    fun count(): Int = items.size
}

fun main() {
    val intBox = Box(10)
    val stringBox = Box("Текст")
    val userBox = Box(User("u1", "Пользователь", 25))

    println(intBox.getValue())
    stringBox.setValue("Новое значение")
    println(stringBox.getValue())
    println(userBox.getValue())

    val userCatalog = Catalog<User>()
    val productCatalog = Catalog<Product>()

    userCatalog.add(User("u1", "Анна", 28))
    productCatalog.add(Product("p1", "Книга", 500.0))

    println(userCatalog.getAll())
    println(productCatalog.find { it.title == "Книга" })

    val userRepo = MemoryRepository<User>()
    val courseRepo = MemoryRepository<Course>()

    userRepo.add(User("u2", "Борис", 40))
    courseRepo.add(Course("c1", "Kotlin", true))

    println(userRepo.getAll())
    println(courseRepo.find("c1"))

    val users = listOf(User("u3", "Елена", 22), User("u4", "Алексей", 35))
    val foundUser = findFirst(users) { it.name.startsWith("А") }
    println(foundUser)

    printCollection(listOf(1, 2, 3))
    printCollection(users)

    val numbersInt = listOf(10, 20, 30)
    val numbersDouble = listOf(1.5, 2.5, 3.5)

    println("Среднее (Int): ${averageValue(numbersInt)}")
    println("Среднее (Double): ${averageValue(numbersDouble).roundToTwoDecimals()}")

    listOf("Яблоко", "Груша", "Слива").printAll()
    println(listOf(1, 2, 3).secondOrNull())
    println(listOf<String>().lastOrDefault("Пусто"))

    when (val result = getUser()) {
        is Result.Success -> println("Пользователь получен: ${result.data.name}")
        is Result.Error -> println("Ошибка: ${result.message}")
        Result.Loading -> println("Загрузка...")
    }

    val cacheForUser = Cache<User>()
    cacheForUser.save(User("u5", "Кэшированный", 99))
    println(cacheForUser.get())
    cacheForUser.clear()
    println("Кеш пуст? ${cacheForUser.isEmpty()}")

    val student = Student("Сергей", listOf(4, 5, 5, 3, 4))
    println("Оценки: ${student.grades}")
    println("Средний балл: ${calculateAverage(student.grades).roundToTwoDecimals()}")
    println("Максимум: ${student.grades.maxOrNull()}, Минимум: ${student.grades.minOrNull()}")

    val products = listOf(
        Product("p2", "Дешевый товар", 100.0),
        Product("p3", "Дорогой товар", 15000.0)
    )
    val courses = listOf(Course("c2", "Java", false), Course("c3", "Python", true))

    println("Совершеннолетних: ${countMatching(users) { it.age >= 18 }}")
    println("Дорогих товаров: ${countMatching(products) { it.price > 5000 }}")
    println("Активных курсов: ${countMatching(courses) { it.isActive }}")

    val pair1 = PairBox("user_id_1", User("u6", "Пара", 10))
    val pair2 = PairBox("product_title", 999.99)
    println(pair1)
    println(pair2)

    printSize(listOf("Строка1", "Строка2"))
    printSize(listOf(1, 2, 3, 4))
    printSize(users)

    fun <T : Printable> printObject(item: T) {
        item.printInfo()
    }
    printObject(User("u7", "Интерфейс", 0))
    printObject(Product("p4", "Принтер", 10000.0))

    val manager = DataManager<User>()
    repeat(5) { index ->
        manager.add(User("id$index", "User_$index", Random.nextInt(18, 60)))
    }

    println("Все: ${manager.count()}")
    val filteredUsers = manager.filter { it.age > 30 }
    println("Cтарше 30: ${filteredUsers.size}")
}
