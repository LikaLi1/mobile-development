using System;
using System.Collections.Generic;
using System.Threading.Tasks;

class Task
{
    public int Number { get; set; }
    public string Description { get; set; }
    public bool Done { get; set; }
}

class proga
{
    static List<Task> tasks = new List<Task>();

    static void Main()
    {
        bool exit = false;

        while (!exit)
        {
            Console.WriteLine("Выберите дейтвие: \n" +
            "1. Добавить задачу\n" +
            "2. Удалить задачу\n" +
            "3. Редактировать задачу\n" +
            "4. Показать все задачи\n" +
            "5. Выход\n");

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
                    exit = true;
                    Console.WriteLine("Программа завершена. Нажмите любую клавишу...");
                    break;
                default:
                    Console.WriteLine("Неправильная команда, попробуйте еще раз");
                    break;
            }
            Console.WriteLine();
            if (!exit)
            {
                Console.WriteLine("Нажмите любую клавишу для продолжения...");
                Console.ReadKey();
                Console.Clear();
            }
        }
    }

    static void AddTask()
    {
        string description = "";
        while (string.IsNullOrWhiteSpace(description))
        {
            Console.Write("Опишите задание: ");
            description = Console.ReadLine();
            if (string.IsNullOrWhiteSpace(description))
            {
                Console.WriteLine("Описание не может быть пустым.");
            }
        }


        Task newTask = new Task
        {
            Number = tasks.Count + 1,
            Description = description,
            Done = false
        };

        tasks.Add(newTask);
        Console.WriteLine($"Задача '{description}' успешно добавлена.");
    }

    static void DeleteTask()
    {
        if (tasks.Count == 0)
        {
            Console.WriteLine("Нет задач для удаления.");
            return;
        }

        ShowTask();

        Console.Write("Введите номер задачи, которую хотите удалить: ");
        if (int.TryParse(Console.ReadLine(), out int num))
        {
            if (num > 0 && num <= tasks.Count)
            {
                tasks.RemoveAt(num - 1);

                for (int i = 0; i < tasks.Count; i++)
                {
                    tasks[i].Number = i + 1;
                }
                Console.WriteLine("Задача успешно удалена.");
            }
            else
            {
                Console.WriteLine("Неверный номер задачи. Попробуйте снова.");
            }
        }
        else
        {
            Console.WriteLine("Неверный ввод. Введите числовой номер задачи.");
        }
    }

    static void EditTask()
    {
        if (tasks.Count == 0)
        {
            Console.WriteLine("Нет задач для редактирования.");
            return;
        }

        ShowTask();

        Console.Write("Введите номер задачи, которую хотите редактировать: ");
        if (int.TryParse(Console.ReadLine(), out int num))
        {
            if (num > 0 && num <= tasks.Count)
            {
                Task taskToEdit = tasks[num - 1];

                Console.Write($"Текущее описание: '{taskToEdit.Description}'. Введите новое описание (оставьте пустым, чтобы не менять): ");
                string newDescription = Console.ReadLine();
                if (!string.IsNullOrWhiteSpace(newDescription))
                {
                    taskToEdit.Description = newDescription;
                }

                Console.Write($"Текущий статус: {(taskToEdit.Done ? "выполнена" : "не выполнена")}. Задача выполнена? (да/нет, оставьте пустым, чтобы не менять): ");
                string inputDone = Console.ReadLine().Trim().ToLower();
                if (inputDone == "да")
                {
                    taskToEdit.Done = true;
                    Console.WriteLine("Статус задачи изменен на 'выполнена'.");
                }
                else if (inputDone == "нет")
                {
                    taskToEdit.Done = false;
                    Console.WriteLine("Статус задачи изменен на 'не выполнена'.");
                }
                else if (!string.IsNullOrWhiteSpace(inputDone))
                {
                    Console.WriteLine("Неверный ввод статуса. Статус не изменен.");
                }
                else
                {
                    Console.WriteLine("Статус выполнения не изменен.");
                }

                Console.WriteLine($"Задача {num} успешно обновлена.");
            }
            else
            {
                Console.WriteLine("Неверный номер задачи. Попробуйте снова.");
            }
        }
        else
        {
            Console.WriteLine("Неверный ввод. Введите числовой номер задачи.");
        }
    }

    static void ShowTask()
    {
        if (tasks.Count == 0)
        {
            Console.WriteLine("Список задач пуст. Добавьте новые задачи.");
            return;
        }

        Console.WriteLine("Ваши задачи: ");
        foreach (var task in tasks)
        {
            Console.WriteLine($"{task.Number}: {task.Description} | Статус: {(task.Done ? "Выполнена" : "Не выполнена")}");
        }
    }
}
