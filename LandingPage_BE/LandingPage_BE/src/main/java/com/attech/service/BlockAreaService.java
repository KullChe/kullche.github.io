package com.attech.service;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.blockArea.BlockAreaRequest;
import com.attech.model.dto.website.WebsitesRequest;
import com.attech.model.entity.BlockArea;
import com.attech.model.entity.Websites;
import com.attech.repository.BlockAreaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;

@Service
@Slf4j
public class BlockAreaService {
    @Autowired
    private BlockAreaRepository blockAreaRepository;

    private ResponseObject failObject(){
        ResponseObject responseObject = new ResponseObject().builder()
                .resCode(AppConst.FAIL_CODE)
                .resDesc(AppConst.FAIL_DESC)
                .resData(null)
                .build();
        return responseObject;
    }
    private ResponseObject successObject(){
        ResponseObject responseObject = new ResponseObject().builder()
                .resCode(AppConst.SUCCESS_CODE)
                .resDesc(AppConst.SUCCESS_DESC)
                .resData(null)
                .build();
        return responseObject;
    }
    private BlockArea convertToEntity(BlockAreaRequest blockAreaRequest)throws ParseException {
        BlockArea blockArea = new BlockArea().builder()
                .areaCode(blockAreaRequest.getAreaCode())
                .textTitle(blockAreaRequest.getTextTitle())
                .textContent(blockAreaRequest.getTextContent())
                .image(blockAreaRequest.getImage().toString())
                .build();
        return blockArea;
    }
//    private BlockArea convertToDTO(BlockArea blockArea) throws ParseException{
//
//    }



}
