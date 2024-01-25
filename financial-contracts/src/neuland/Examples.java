package neuland;

import java.time.LocalDate;

import static neuland.Contract.*;

public class Examples {
        // var zcb1 = new ZeroCouponBond(LocalDate.of(2024, 12, 24), 100, Currency.EUR);
        // Ich bekomme 1€ jetzt
        public Contract c1 = new One(Currency.EUR);
        // Ich bekomme 100€ jetzt
        public Contract c2 = new ContractAmount(100, new One(Currency.EUR));
        public Contract zcb1 = new Later(LocalDate.of(2024, 12, 24),
                         new ContractAmount(100,
                                 new One(Currency.EUR)));
        public Contract zcb1_ = Contract.zeroCouponBond(LocalDate.of(2024, 12, 24), 100, Currency.EUR);
        // Ich bezahle 1€ jetzt.
        // Contract c3 = new ContractDirection(Direction.SHORT, one(Currency.EUR));
        // Contract c4 = new ContractDirection(Direction.SHORT, c3);
        // Contract c5 = new ContractDirection(Direction.LONG, one(Currency.EUR));

        public Contract c6 =
                contractAmount(100,
                        and(one(Currency.EUR),
                            later(LocalDate.of(2024,12,24), one(Currency.EUR))));
}
