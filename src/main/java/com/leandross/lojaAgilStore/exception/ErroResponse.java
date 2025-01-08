package com.leandross.lojaAgilStore.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErroResponse {
    private HttpStatus status;

    private String menssage;

    public ErroResponse(HttpStatus status, String menssage) {
        this.status = status;
        this.menssage = menssage;
    }
}
