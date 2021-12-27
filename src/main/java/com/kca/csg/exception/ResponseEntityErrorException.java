package com.kca.csg.exception;

import com.kca.csg.payload.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public class ResponseEntityErrorException extends RuntimeException{

    private transient ResponseEntity<ApiResponse> apiResponse;

    public ResponseEntityErrorException(ResponseEntity<ApiResponse> apiResponse){
        this.apiResponse = apiResponse;
    }

    public ResponseEntity<ApiResponse> getApiResponse(){
        return apiResponse;
    }

}
