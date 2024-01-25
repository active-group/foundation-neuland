package neuland;

import java.time.LocalDate;

/*
    "Ich bekomme am 24.12.2024 100€."
 */
public record ZeroCouponBond(LocalDate date, double amount, Currency currency) /* implements Contract */ {
}
