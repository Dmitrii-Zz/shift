package ru.shift;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        System.out.print("Укажите размер таблицы: ");
        int tableSize;

        try {
            tableSize = scan.nextInt();
            Validator.validateSize(tableSize);
        } catch (InputNumberValidateException e) {
            System.out.println(e.getMessage());
            return;
        } catch (InputMismatchException e) {
            System.out.println("Ошибка: введено не число");
            return;
        }

        Table table = new Table(tableSize);
        table.printTable();
        //Написал коммент
    }
}