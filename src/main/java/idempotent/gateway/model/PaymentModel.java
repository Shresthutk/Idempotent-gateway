package idempotent.gateway.model;

import idempotent.gateway.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="payment")
public class PaymentModel {

    @Id
    private String id;
    private String currency;
    private int amount;
    @Enumerated(EnumType.STRING)
    private Status status;

    public PaymentModel(){
    }

    public PaymentModel(String id, String currency, int amount, Status staus){
        this.id = id;
        this.currency = currency;
        this.amount = amount;
        this.status = staus;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Status getStatus(){
        return status;
    }

    public void setStatus(Status status){
        this.status = status;
    }

}
