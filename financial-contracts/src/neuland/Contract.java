package neuland;

/*
- "Einfaches Beispiel"
  Zero-Bound / zero-coupon bond
  "Ich bekomme am 24.12.2024 100€."

 - Zerlegen in "atomare Ideen"
   - Währung: "Ich bekomme einen € jetzt."
   - Betrag: "Ich bekomme 100 €jetzt."
   - Später: "Ich bekomme am 24.12.2024 100€."
 - weiteres Beispiel:
   Fx Swap:
   "Am 24.12.2024 bekomme ich 100€ UND bezahle 100$."

 - später: "irgendwann"
 */

import java.time.LocalDate;
import java.util.ArrayList;

sealed public interface Contract permits And, ContractAmount, Later, One, Reverse, Zero {

    // Zahlungen bis jetzt
    default Tuple2<PaymentPlan, Contract> paymentPlan(LocalDate now) {
        return switch (this) {
            case Zero() ->
                Tuple2.of(PaymentPlan.zero, new Zero());
            case One(Currency currency) ->
                Tuple2.of(PaymentPlan.one(new Payment(now, Direction.LONG, 1, currency)),
                        new Zero());
            case ContractAmount(double amount, Contract contract) -> {
                Tuple2<PaymentPlan, Contract> tuple = contract.paymentPlan(now);
                yield Tuple2.of(tuple.first().scale(amount),
                        contractAmount(amount, tuple.second()));
            }
            case Later(LocalDate date, Contract contract) -> {
                if (now.isBefore(date)) {
                    yield Tuple2.of(PaymentPlan.zero, this);
                } else {
                    yield contract.paymentPlan(now);
                }
            }
            case Reverse(Contract contract) -> {
                var tuple = contract.paymentPlan(now);
                yield Tuple2.of(tuple.first().reverse(),
                        reverse(tuple.second()));
            }
            case And(Contract contract1, Contract contract2) -> {
                var tuple1 = contract1.paymentPlan(now);
                var tuple2 = contract2.paymentPlan(now);
                yield Tuple2.of(tuple1.first().and(tuple2.first()),
                        and(tuple1.second(), tuple2.second()));
            }
        };
     }

    static Contract one(Currency currency) {
        return new One(currency);
    }
    static Contract contractAmount(double amount, Contract contract) {
        return new ContractAmount(amount, contract);
    }

    static Contract later(LocalDate date, Contract contract) {
        return new Later(date, contract);
    }

    static Contract reverse(Contract contract) {
        return new Reverse(contract);
    }

    static Contract and(Contract contract1, Contract contract2) {
        return new And(contract1, contract2);
    }

    static Contract andn(Contract... contracts) {
        Contract result = new Zero();
        for (Contract contract : contracts) {
            result = and(result, contract);
        }
        return result;
    }

    static Contract zeroCouponBond(LocalDate date, double amount, Currency currency) {
        return later(date,
                contractAmount(amount,
                        one(currency)));
    }

    static Contract fxSwap(LocalDate date, double amount1, Currency currency1,
                           double amount2, Currency currency2) {
        return and(zeroCouponBond(date, amount1, currency1),
          reverse(zeroCouponBond(date, amount2, currency2)));
    }
}
