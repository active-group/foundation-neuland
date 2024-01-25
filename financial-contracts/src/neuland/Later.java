package neuland;

import java.time.LocalDate;

public record Later(LocalDate date, Contract contract) implements Contract {
}
