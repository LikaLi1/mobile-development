int number, number2;
// int.Parse(nuber);
// Convert.ToInt32();
int GetNumber(string msg)
{
    while (true)
    {
        Console.WriteLine($"{msg}");
        var numberString = Console.ReadLine();
        if (!int.TryParse(numberString, out number))
        {
            Console.WriteLine("Invalid input");
            continue;
        }
        return number;
    }
}

bool CheckValue(int value, out int num)
{
    num = value;
    if (value == 0)
    {
        Console.WriteLine("Second number must be greater then 0");
        return false;
    }
    return true;
}


var numberInt = GetNumber("Input number 1:");
var numberInt2 = GetNumber("Input number 2:");

char operation;
while (true)
{
    Console.WriteLine("Check operation\n" +
                      "1 - Addition\n" +
                      "2 - Substraction\n" +
                      "3 - Multiply\n" +
                      "4 - Divide");
    var operationString = Console.ReadLine();
    if (!Char.TryParse(operationString, out operation))
    {
        Console.WriteLine("Invalid input");
        continue;
    }

    break;
}

float result = 0f;
switch (operation)
{
    case '1':
        result = numberInt + numberInt2;
        break;
    case '2':
        result = numberInt - numberInt2;
        break;
    case '3':
        result = numberInt * numberInt2;
        break;
    case '4':
        while (true)
        {
            numberInt2 = GetNumber("Input number 2:");
            if (CheckValue(numberInt2, out var num))
            {
                continue;
            }
            break;
        }
        result = (float)number / (float)numberInt2;
        break;
}
Console.WriteLine($"Result {result}");
