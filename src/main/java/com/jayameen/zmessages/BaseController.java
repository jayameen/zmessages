package com.jayameen.zmessages;

import com.jayameen.zmessages.dto.AppResponse;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;

import java.util.Arrays;

/**
 *
 * @author Madan KN
 */

public class BaseController <T>{


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void handleListSuccess(AppResponse response, Page<T> pageRec){
        response.setStatus(HttpStatus.OK.getReasonPhrase().toLowerCase());
        response.setCode(HttpStatus.OK.toString());
        response.setDescription("Operation Completed Successfully!");
        response.setRecordsTotal(pageRec.getTotalElements());
        response.setPagesTotal(pageRec.getTotalPages());
        response.setRecordsFiltered(pageRec.getTotalElements());
        response.setData(pageRec.getContent());
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    protected void handleObjectSuccess(AppResponse response, Object object){
        response.setData(Arrays.asList(object));
        response.setStatus(HttpStatus.OK.getReasonPhrase().toLowerCase());
        response.setCode(HttpStatus.OK.toString());
        response.setDescription("Operation Completed Successfully!");
    }
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
}