package neuland;

import java.util.ArrayList;
import java.util.List;

public record PaymentPlan(List<Payment> payments) {
    static PaymentPlan zero = new PaymentPlan(new ArrayList<>());
    static PaymentPlan one(Payment payment) {
        List<Payment> payments = new ArrayList<>();
        payments.add(payment);
        return new PaymentPlan(payments);
    }

    public PaymentPlan scale(double factor) {
        List<Payment> newPayments = new ArrayList<>();
        for (Payment payment: payments) {
            newPayments.add(payment.scale(factor));
        }
        return new PaymentPlan(newPayments);
    }

    public PaymentPlan reverse() {
        List<Payment> newPayments = new ArrayList<>();
        for (Payment payment: payments) {
            newPayments.add(payment.reverse());
        }
        return new PaymentPlan(newPayments);
    }

    public PaymentPlan and(PaymentPlan plan2) {
        List<Payment> newPayments = new ArrayList<>();
        newPayments.addAll(this.payments);
        newPayments.addAll(plan2.payments);
        return new PaymentPlan(newPayments);
    }
}
