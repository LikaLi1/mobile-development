using System;

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


Console.WriteLine("Варианты конвертации валют: " +
    "0. Доллар США" +
    "1. Евро" +
    "2. Фунт стерлингов" +
    "3. Индийская рупия");


int choice;
while (true)
{
    Console.WriteLine("Введите сумму которую хотите конвертировать: ");
    string input = Console.ReadLine();

    if (int.TryParse(input, out choice))
    {
        break;
    }
    else
    {
        Console.WriteLine("Ошибка! Введите число");
    }
}

switch (choice) {
    case 0:
        double US_dollars = NewRubles * 0.013;

        Console.WriteLine($"{NewRubles} рублей в долларах США равен {US_dollars}");
        Console.WriteLine($"Остаток баланса карты: {balanceRubles - NewRubles}, сумма которая была конвертированна: {NewRubles}, она равняется {US_dollars} USD");
        break;
    case 1:
        double euro = NewRubles * 0.011;

        Console.WriteLine($"{NewRubles} рублей в евро равен {euro}");
        Console.WriteLine($"Остаток баланса карты: {balanceRubles - NewRubles}, сумма которая была конвертированна: {NewRubles}, она равняется {euro} EUR");
        break;

    case 2:
        double pounds_sterling = NewRubles * 0.0096;

        Console.WriteLine($"{NewRubles} рублей в фунтах стерлингов равен {pounds_sterling}");
        Console.WriteLine($"Остаток баланса карты: {balanceRubles - NewRubles}, сумма которая была конвертированна: {NewRubles}, она равняется {pounds_sterling} GBR");
        break;
    case 3:
        double indian_rupee = NewRubles * 1.1829;

        Console.WriteLine($"{NewRubles} рублей в индийской рупии равен {indian_rupee}");
        Console.WriteLine($"Остаток баланса карты: {balanceRubles - NewRubles}, сумма которая была конвертированна: {NewRubles}, она равняется {indian_rupee} INR");
        break;
}
