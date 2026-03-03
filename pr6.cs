using System;
using System.Threading.Tasks;

class Task
{
    public int Number {  get; set; }
    public string Description { get; set; }
    public bool Done {  get; set; }
}

class proga
{
    static void Main()
    {
        List<Task> tasks = new List<Task>();
        bool exit = false;

        while (!exit)
        {
            Console.WriteLine("Выберите дейтвие: " +
            "1. Добавить задачу" +
            "2. Удалить задачу" +
            "3. Редактировать задачу" +
            "4. Удалить задачу");
        }
    }
}
