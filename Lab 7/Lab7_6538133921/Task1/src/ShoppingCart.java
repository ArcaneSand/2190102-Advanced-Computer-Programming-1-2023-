import java.util.ArrayList;
import java.util.List;
public class ShoppingCart {
    public List<String> shopingList = new ArrayList<>(); //Create a list of items in the shopping cart

    /*since when apply coupon we will create a new shoping cart replace the previous cart so it will invole transfer fieds and components of
     old cart to the new cart. we will use this "public ShoppingCart cart;" to hold the information of the previous cart when transfer the information to new cart*/
    public ShoppingCart cart; //when first create normal cart it will be null

    //this three variables are set to confirm that each coupon will only be used once, represent each coupon types ps. you can also just simply use bool to check.
    //when first create they will all be null, then when apply coupon I will assign the coupon to them and check if each type is ==null to check for Unique coupoon
    public DiscountByPercentDecorator discountByPercent;
    public DiscountByAmountDecorator discountByAmount;
    public FreeDeliveryDecorator freeDeliveryDecorator;

    //the coupon work only for netPrice not totalPrice, so we will split the amount in to 3 for easy calculation;
    public double deliveryFee = 0.0;
    public double totalPrice = 0.0;
    public double netPrice = 0.0;

    public void addItem(String newItem){//add new item to list
        shopingList.add(newItem);
    }

    public void addDeliveryFee(int Fee){//add delivery fee
        deliveryFee += Fee;
    }

    public void isFeeApplied(){//this method is used with Free Delivery coupon if the coupon exists it will omit the entire delivery fee. It will be use when callculate netPrice.
        if(freeDeliveryDecorator != null) deliveryFee = 0;
    }

    public double calculateTotal(){//this method calculate total price for shoping cart
        double sum = 0;
        for (String item : shopingList) {
            String[] part = item.split(":");
            sum += Double.parseDouble(part[1]) * Double.parseDouble(part[2]);
        }
        totalPrice = sum;
        return sum;
    }
    //the following two methods are use for get netPrice.
    public double getNetPrice(){
        SetNet();
        isFeeApplied();
        return netPrice + deliveryFee;
    }
    //this method is call when transfer cart to ensure that netPrice is not zero.
    //since we will manipulate the netPrice we will need to call netPrice = totalPrice for only the first time cart created.
    public void SetNet(){ 
        if(netPrice==0){
            calculateTotal();
            netPrice = totalPrice;
        }
    }

    public void SuperCart(){//this method use for transfer old cart to new cart see below to understand why.
        shopingList = cart.shopingList;
        discountByPercent = cart.discountByPercent;
        discountByAmount = cart.discountByAmount;
        freeDeliveryDecorator = cart.freeDeliveryDecorator;
        deliveryFee = cart.deliveryFee;
        totalPrice = cart.totalPrice;
        netPrice = cart.netPrice;
    }
}

class DiscountByPercentDecorator extends ShoppingCart{
    public double Percent;

    public DiscountByPercentDecorator(ShoppingCart cart,double Percent){
        this.cart = cart;
        cart.SetNet();//we call this to ensure that the netPrice and totalPrice are not zero.
        SuperCart();//transfer the information of old cart to new cart.
        if(discountByPercent==null){// check the unique of Percent coupon if there already exists it will ignore any modification. This way we ensure that only first percent coupon will apply.  
            this.Percent = Percent;
            discountByPercent = this;// assign the Percent coupon to percent coupon variable.
            netPrice = netPrice * (100-Percent)/100 ;//Modify only netPrice here.
        }
    }

}

class DiscountByAmountDecorator extends ShoppingCart{//same as Percent Coupon just change to Amount coupon.
    public double Amount;
    public DiscountByAmountDecorator(ShoppingCart cart,double Amount){
        this.cart = cart;
        cart.SetNet();
        SuperCart();
        if(discountByAmount==null){
            this.Amount = Amount;
            discountByAmount = this;
            netPrice = netPrice - Amount;
        }
    }
}

class FreeDeliveryDecorator extends ShoppingCart{//use with isFeeApplie method in super class.
    public FreeDeliveryDecorator(ShoppingCart cart){
        this.cart = cart;
        cart.freeDeliveryDecorator = this;
        cart.SetNet();
        SuperCart();

    }
}

