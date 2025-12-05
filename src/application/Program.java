package application;

import models.entities.CarRental;
import models.entities.Vehicle;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados do aluguel: ");
        System.out.print("Modelo do carro: ");
        String carModel = sc.nextLine();
        System.out.print("Retirada (DD/MM/AAAA HH:MM): ");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (DD/MM/AAAA HH:MM): ");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr  =  new CarRental(start, finish, new Vehicle(carModel));
    }
}
