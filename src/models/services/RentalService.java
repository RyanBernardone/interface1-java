package models.services;

import models.entities.CarRental;
import models.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;

    private BrazilTax brazilTax;

    public RentalService(Double pricePerHour, Double pricePerDay, BrazilTax brazilTax) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.brazilTax = brazilTax;
    }

    public void processInvoice(CarRental carRental){
        double minutes = Duration.between(carRental.getStart(), carRental.getFinish()).toMinutes();
        double hours = minutes / 60;

        double basicPayment;

        if (hours <= 12){
            basicPayment = pricePerHour * Math.ceil(hours);
        }else{
            basicPayment = pricePerDay * Math.ceil(hours / 24);
        }

        double tax = brazilTax.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
