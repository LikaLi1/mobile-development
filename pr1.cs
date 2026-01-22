using System;
using System.ComponentModel.Design;

class Proga
{
    static void Main()
    {
        //byte a = 5;
        //byte b = 10;
        //byte c = (byte)a - (byte)b;
        //Console.WriteLine(c);

        int userNumInt1;
        int userNumInt2;

        while (true)
        {
            while (true)
            {
                Console.WriteLine("Введите число 1: ");
                var num1 = Console.ReadLine();

                if (!int.TryParse(num1, out userNumInt1))
                {
                    Console.WriteLine("Ошибка");
                    continue;
                }
                Console.WriteLine("Введите число 2: ");
                var num2 = Console.ReadLine();
                if (!int.TryParse(num2, out userNumInt2))
                {
                    Console.WriteLine("Ошибка");
                    continue;
                }
                break;
            }

            Console.WriteLine("Введите операцию(+,-,*,/): ");
            var operation = Console.ReadLine();
            if (operation == "+")
            {
                Console.WriteLine(userNumInt1 + userNumInt2);
            }
            else if (operation == "-")
            {
                Console.WriteLine(userNumInt1 - userNumInt2);
            }
            else if (operation == "*")
            {
                Console.WriteLine(userNumInt1 * userNumInt2);
            }
            else if (operation == "/")
            {
                Console.WriteLine(userNumInt1 / userNumInt2);
            }
            return;
        }
    }
}
