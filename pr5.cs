using System;

delegate double CurrencyConverter(double rubles);
class Program
{
    static void Main()
    {
        int balanceRubles;
        while (true)
        {
            Console.WriteLine("Введите баланс карты(в рублях): ");
            string input = Console.ReadLine();

            if (int.TryParse(input, out balanceRubles))
            {
                break;
            }
            else
            {
                Console.WriteLine("Ошибка! Введите число");
            }
        }

        int NewRubles;
        while (true)
        {
            Console.WriteLine("Введите сумму которую хотите конвертировать: ");
            string input = Console.ReadLine();

            if (int.TryParse(input, out NewRubles))
            {
                break;
            }
            else
            {
                Console.WriteLine("Ошибка! Введите число");
            }
        }

        Console.WriteLine("Варианты конвертации валют:\n" +
            "0. Доллар США\n" +
            "1. Евро\n" +
            "2. Фунт стерлингов\n" +
            "3. Индийская рупия");

        int choice;
        while (true)
        {
            Console.Write("Выберите валюту: ");
            string input = Console.ReadLine();

            if (int.TryParse(input, out choice) && choice >= 0 && choice <= 3)
            {
                break;
            }
            else
            {
                Console.WriteLine("Ошибка! Выбирайте из списка");
            }
        }

        CurrencyConverter converter = null;

        switch (choice)
        {
            case 0:
                converter = ConvertToUSD;
                break;
            case 1:
                converter = ConvertToEURO;
                break;
            case 2:
                converter = ConvertToGBP;
                break;
            case 3:
                converter = ConvertToINR;
                break;
        }

        if (converter != null)
        {
            double convertedAmount = converter(NewRubles);
            Console.WriteLine($"{NewRubles} рублей в выбранной валюте равен {convertedAmount}");

            int newBalance = balanceRubles - NewRubles;
            Console.WriteLine($"Остаток баланса карты: {newBalance}");
            Console.WriteLine($"Сумма, конвертированная: {NewRubles}");
        }
    }
    static double ConvertToUSD(double rubles)
    {
        return rubles * 0.013;
    }

    static double ConvertToEURO(double rubles)
    {
        return rubles * 0.011;
    }

    static double ConvertToGBP(double rubles)
    {
        return rubles * 0.0096;
    }

    static double ConvertToINR(double rubles)
    {
        return rubles * 1.1829;
    }
}
