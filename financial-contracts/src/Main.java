import neuland.Examples;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Examples e = new Examples();
        // System.out.println(e.zcb1);

        /*
        var tuple1 = e.zcb1.paymentPlan(LocalDate.of(2024, 12, 25));
        System.out.println(tuple1.first());
        var tuple2 = tuple1.second().paymentPlan(LocalDate.of(2024, 12, 31));
        System.out.println(tuple2.first());
        */
        System.out.println(e.c6.paymentPlan(LocalDate.of(2024,1,25)));

    }
}