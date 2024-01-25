package neuland;

import java.time.LocalDate;

public record Payment(LocalDate date, Direction direction, double amount, Currency currency) {
    public Payment scale(double factor) {
        return new Payment(date, direction, amount*factor, currency);
    }

    public Payment reverse() {
        return new Payment(date, direction == Direction.LONG ? Direction.SHORT : Direction.LONG,
                amount, currency);
    }
}
