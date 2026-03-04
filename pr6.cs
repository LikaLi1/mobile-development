using System;
using System.Threading.Tasks;

class Task
{
    public int Number { get; set; }
    public string Description { get; set; }
    public bool Done { get; set; }
}

class proga
{
    static void Main()
    {
        List<Task> tasks = new List<Task>();
        bool exit = false;
        int taskCounter = 1;

        while (!exit)
        {
            Console.WriteLine("Выберите дейтвие: " +
            "1. Добавить задачу" +
            "2. Удалить задачу" +
            "3. Редактировать задачу" +
            "4. Показать все задачи" +
            "5. Фильтр по задачам");
        }

        Console.Write("Введите номер команды: ");
        string input = Console.ReadLine().Trim();

        switch (input)
        {
            case "1":
                AddTask();
                break;
            case "2":
                DeleteTask();
                break;
            case "3":
                EditTask();
                break;
            case "4":
                ShowTask();
                break;
            case "5":
                FilterTask();
                break;
            default:
                Console.WriteLine("Неправильная команда, попробуйте еще раз.");
                break;
        }
    }
    static void AddTask()
    {
        Task task = new Task();
    }
}

