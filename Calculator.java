import java.util.Scanner;
 
public class Main{
    public static void main(String[] args) {
        Scanner gg = new Scanner(System.in); 
                       
        System.out.println("Напишите первое число: ");
        double num1 = gg.nextDouble(); 
        System.out.print("Напишите второе число: ");
        double num2 = gg.nextDouble();

        double jjj;
        String operation;
System.out.println("Выбрать действие(+,-,*,/): ");
 
operation = gg.next();

switch (operation) {
    case "+":
        jjj = num1 + num2;
        break;
 
    case "-":
        jjj = num1 - num2;
        break;

    case "*":
        jjj = num1 * num2;
        break;

    case "/":
        if (num2 == 0) {
            System.out.println("Делить на 0 нельзя");
        } else {
            jjj = num1 / num2;
            break;
        }

 
        default:
        System.out.println("Введена неверная операция");
        return;
 

}
        System.out.printf("%.4f %s %.4f = %.4f%n", num1, operation, num2, jjj );

}


    }
