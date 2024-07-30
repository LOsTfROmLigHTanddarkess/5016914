interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card "+cardNumber+" from "+cardHolderName);
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;
    private String transactionId;

    public PayPalPayment(String email, String transactionId) {
        this.email = email;
        this.transactionId = transactionId;
    }

    @Override
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal by "+email+" Transaction ID: "+transactionId);
    }
}

class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void pay(int amount) {
        paymentStrategy.pay(amount);
    }
}

public class StrategyPatternExample 
{
    public static void main(String[] args) 
    {
        PaymentContext context = new PaymentContext();

        PaymentStrategy creditCardPayment = new CreditCardPayment("12021002029120", "Arghya Kundu");
        context.setPaymentStrategy(creditCardPayment);
        context.pay(100);

        PaymentStrategy payPalPayment = new PayPalPayment("arghya.kundu@gmail.com", "45878589");
        context.setPaymentStrategy(payPalPayment);
        context.pay(200);
    }
}
