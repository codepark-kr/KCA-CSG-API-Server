package com.kca.csg.util;

import com.kca.csg.exception.ApiException;
import org.springframework.http.HttpStatus;

public class ValidationUtils {
    public static void pageValidation(int page, int size){
        if(page < 0){ throw new ApiException(HttpStatus.BAD_REQUEST, "Page number cannot be less than zero"); }
        if(size < 0){ throw new ApiException(HttpStatus.BAD_REQUEST, "Size number cannot be less than zero"); }
        if(size > AppConstants.MAX_PAGE_SIZE){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
    }
}
