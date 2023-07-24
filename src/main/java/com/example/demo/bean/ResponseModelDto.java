package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class ResponseModelDto<T> {
    private T body;
    private boolean result;
    private String msgHeader;
    private String msgDetail;

    public ResponseModelDto(boolean result) {
        this.result = result;
    }

    public ResponseModelDto(T body, boolean result) {
        this.body = body;
        this.result = result;
    }
}
