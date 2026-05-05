package idempotent.gateway.dto;

public class RequestDto {
    private int amount;
    private String currency;
    public RequestDto (){}

    public RequestDto (String currency, int amount){
        this.amount = amount;
        this.currency = currency;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
