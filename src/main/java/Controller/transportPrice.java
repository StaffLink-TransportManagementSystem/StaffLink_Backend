package Controller;

public class transportPrice {
    float normalPrice;
    float acPrice;
    float luxuryPrice;
    private transportPrice(){
        normalPrice = 20;
        acPrice = 40;
        luxuryPrice = 60;
    }
    public static transportPrice getInstance(){
        return new transportPrice();
    }
    public float getNormalPrice(){
        return normalPrice;
    }
    public float getAcPrice(){
        return acPrice;
    }
    public float getLuxuryPrice(){
        return luxuryPrice;
    }
    public void changeTransportPrice(float precentage){
        normalPrice = normalPrice + (normalPrice * precentage);
        acPrice = acPrice + (acPrice * precentage);
        luxuryPrice = luxuryPrice + (luxuryPrice * precentage);
    }
}
