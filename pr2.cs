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
            if (int.TryParse(userInputString, out money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }
            break;
        }

        var moneyToDeposit = money;
        userBalance += moneyToDeposit;

        while (true)
        {
            Console.WriteLine("Внесите сумму, которую хотите снять: ");
            var userInputString2 = Console.ReadLine();
            if (!int.TryParse(userInputString2, out money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }
            break;
        }

        var moneyToTakeOff = money;
        if (moneyToTakeOff > userBalance)
        {
            Console.WriteLine("Недостаточно средств!");
        }
        else
        {
            userBalance -= moneyToTakeOff;
        }


        while (true)
        {
            Console.WriteLine("Внесите сумму, которую хотите перевести: ");
            var userInputString3 = Console.ReadLine();
            if (!int.TryParse(userInputString3, out money))
            {
                Console.WriteLine("Вы ввели неверное значение!");
                continue;
            }
            else if (userInputString3 != cardNumber)
            {
                Console.WriteLine("Вы ввели неверные данные второго пользователя!");
            }
            break;
        }

        var moneyToSend = money;
        if (moneyToSend > userBalance)
        {
            Console.WriteLine("Недостаточно средств!");
        }
        else
        {
            userBalance -= moneyToSend;
            userBalance2 += moneyToSend;
        }
    }
}
