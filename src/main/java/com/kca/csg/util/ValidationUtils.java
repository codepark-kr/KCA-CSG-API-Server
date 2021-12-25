package com.kca.csg.util;

import com.kca.csg.exception.ApiException;
import com.kca.csg.payload.response.ApiResponse;
import org.springframework.http.HttpStatus;
import static com.kca.csg.util.Constants.*;

public class ValidationUtils {
    public static void pageValidation(int page, int size){
        if(page < 0){ throw new ApiException(HttpStatus.BAD_REQUEST, "Page number cannot be less than zero"); }
        if(size < 0){ throw new ApiException(HttpStatus.BAD_REQUEST, "Size number cannot be less than zero"); }
        if(size > Constants.MAX_PAGE_SIZE){
            throw new ApiException(HttpStatus.BAD_REQUEST, "Page size must not be greater than " + Constants.MAX_PAGE_SIZE);
        }
    }

    public static ApiResponse noPermissionResponse(){ return new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO_MAKE_OPERATION); }}
//    public static Exception noPermissionException(){return new UnauthorizedException(new ApiResponse(Boolean.FALSE, NO_PERMISSION_TO_MAKE_OPERATION));}}