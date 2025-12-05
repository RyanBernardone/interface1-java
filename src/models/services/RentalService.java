package models.services;

import models.entities.CarRental;
import models.entities.Invoice;

import java.time.Duration;

public class RentalService {
    private Double pricePerHour;
    private Double pricePerDay;

    private TaxInterface taxInterface;

    public RentalService(Double pricePerHour, Double pricePerDay, TaxInterface taxInterface) {
        this.pricePerHour = pricePerHour;
        this.pricePerDay = pricePerDay;
        this.taxInterface = taxInterface;
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

        double tax = taxInterface.tax(basicPayment);
        carRental.setInvoice(new Invoice(basicPayment, tax));
    }
}
