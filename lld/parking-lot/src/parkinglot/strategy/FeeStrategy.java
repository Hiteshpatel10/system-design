package parkinglot.strategy;

import parkinglot.models.Ticket;

public abstract class FeeStrategy {
    public abstract double calculate(Ticket ticket);
}