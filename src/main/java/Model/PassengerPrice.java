package Model;

public class PassengerPrice {
    private float normalPrice;
    private float acPrice;
    private float luxuryPrice;
    private int deleteState;

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

    public int getDeleteState() {
        return deleteState;
    }

    public void setDeleteState(int deleteState) {
        this.deleteState = deleteState;
    }
    public PassengerPrice() {
    }
    public PassengerPrice(float normalPrice, float acPrice, float luxuryPrice) {
        this.normalPrice = normalPrice;
        this.acPrice = acPrice;
        this.luxuryPrice = luxuryPrice;
    }
    public PassengerPrice(float normalPrice, float acPrice, float luxuryPrice, int deleteState) {
        this.normalPrice = normalPrice;
        this.acPrice = acPrice;
        this.luxuryPrice = luxuryPrice;
        this.deleteState = deleteState;
    }
    public PassengerPrice(int deleteState) {
        this.deleteState = deleteState;
    }
}
