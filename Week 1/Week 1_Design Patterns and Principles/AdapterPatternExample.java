
interface PaymentProcessor {
    void processPayment(double amount);
}

// Adaptee Class 1
class PayPalGateway {
    public void makePayment(double amount) {
        System.out.println("Payment of $" + amount + " made through PayPal.");
    }
}

// Adaptee Class 2
class GpayGateway {
    public void pay(double amount) {
        System.out.println("Payment of $" + amount + " made through Gpay.");
    }
}

// Adapter Class for PayPal
class PayPalAdapter implements PaymentProcessor {
    private PayPalGateway payPalGateway;

    public PayPalAdapter(PayPalGateway payPalGateway) {
        this.payPalGateway = payPalGateway;
    }

    @Override
    public void processPayment(double amount) {
        payPalGateway.makePayment(amount);
    }
}

// Adapter Class for Gpay
class GpayAdapter implements PaymentProcessor {
    private GpayGateway gPayGateway;

    public GpayAdapter(GpayGateway gPayGateway) {
        this.gPayGateway = gPayGateway;
    }

    @Override
    public void processPayment(double amount) {
        gPayGateway.pay(amount);
    }
}

public class AdapterPatternExample 
{
    public static void main(String[] args) 
    {
        PaymentProcessor payPalProcessor = new PayPalAdapter(new PayPalGateway());
        PaymentProcessor gpayProcessor = new GpayAdapter(new GpayGateway());

        payPalProcessor.processPayment(100.0);
        gpayProcessor.processPayment(200.0);
    }
}
