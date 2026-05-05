package idempotent.gateway.model;

import idempotent.gateway.util.Status;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import jakarta.persistence.Id;

@Entity
@Table(name = "idempotent")
public class IdempotentCheckModel {

    @Id
    private String headerKey;
    @Enumerated(EnumType.STRING)
    private Status status;

    public IdempotentCheckModel(){};

    public IdempotentCheckModel (String headerKey, Status status){
        this.headerKey = headerKey;
        this.status = status;
    }

    public String getHeaderKey() {
        return headerKey;
    }
    public void setKey(String headerKey) {
        this.headerKey = headerKey;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
