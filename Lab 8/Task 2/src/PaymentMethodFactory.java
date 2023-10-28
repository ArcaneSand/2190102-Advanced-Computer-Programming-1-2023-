interface PaymentMethod{
    void processPayment(double amount);
}

class CreditCardPayment implements PaymentMethod{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }

}

class PayPalPayment implements PaymentMethod{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }

}

class CryptoPayment implements PaymentMethod{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing cryptocurrency payment of $" + amount);
    }

}

public class PaymentMethodFactory {
    public PaymentMethod createPaymentMethod(String method){
        if(method.equals("credit card")){
            return new CreditCardPayment();
        }else if(method.equals("paypal")){
            return new PayPalPayment();
        }else if(method.equals("crypto")){
            return new CryptoPayment();
        }else{
            return null;
        }
    }
}
