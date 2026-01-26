using System;
using System.ComponentModel.Design;

class Proga
{
    static void Main()
    {
        var userBalance = 10000;
        var userBalance2 = 10000;
        int money;
        var cardNumber = "1894629649";

        while (true)
        {
            Console.WriteLine("Внесите сумму, которую хотите внести: ");
            var userInputString = Console.ReadLine();
            if (!int.TryParse(userInputString, out money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }

            var moneyToDeposit = money;
            userBalance += moneyToDeposit;
            Console.WriteLine($"Сумма успешно внесена! Ваш новый баланс: {userBalance}");
            break;
        }

        while (true)
        {
            Console.WriteLine("Внесите сумму, которую хотите снять: ");
            var userInputString2 = Console.ReadLine();
            if (!int.TryParse(userInputString2, out money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }

            if (money > userBalance)
            {
                Console.WriteLine("Недостаточно средств на счете!");
                continue;
            }

            userBalance -= money;
            Console.WriteLine($"Сумма успешно снята! Ваш новый баланс: {userBalance}");
            break;
        }

        while (true)
        {
            Console.WriteLine("Внесите сумму, которую хотите перевести: ");
            var userInputString3 = Console.ReadLine();
            if (!int.TryParse(userInputString3, out  money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }

            Console.WriteLine("Введите номер карты второго пользователя: ");
            var userInputString4 = Console.ReadLine();
            if (userInputString4 != cardNumber)
            {
                Console.WriteLine("Вы ввели неверные данные второго пользователя!");
                continue;
            }

            var moneyToSend = money;
            if (moneyToSend > userBalance)
            {
                Console.WriteLine("Недостаточно средств!");
                continue;
            }

            userBalance -= moneyToSend;
            userBalance2 += moneyToSend;
            Console.WriteLine($"Перевод успешно выполнен! Остаток на вашем счете: {userBalance}");
            break;
        }
    }
}
