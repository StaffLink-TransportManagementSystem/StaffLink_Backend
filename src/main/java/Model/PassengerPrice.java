package Model;

public class PassengerPrice {
    private float normalPrice;
    private float acPrice;
    private float luxuryPrice;

    public float getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(float normalPrice) {
        this.normalPrice = normalPrice;
    }

    public float getAcPrice() {
        return acPrice;
    }

    public void setAcPrice(float acPrice) {
        this.acPrice = acPrice;
    }

    public float getLuxuryPrice() {
        return luxuryPrice;
    }

    public void setLuxuryPrice(float luxuryPrice) {
        this.luxuryPrice = luxuryPrice;
    }
}
