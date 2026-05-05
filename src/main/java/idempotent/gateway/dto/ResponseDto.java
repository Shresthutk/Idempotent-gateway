package idempotent.gateway.dto;

import idempotent.gateway.util.Status;

public class ResponseDto {
    private Status status;

    public ResponseDto (){};

    public ResponseDto (Status status){
        this.status = status;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
