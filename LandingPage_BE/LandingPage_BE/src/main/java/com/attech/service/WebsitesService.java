package com.attech.service;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.user.ResponseUser;
import com.attech.model.dto.website.ResponseWebsite;
import com.attech.model.dto.website.WebsitesRequest;
import com.attech.model.entity.Roles;
import com.attech.model.entity.User;
import com.attech.model.entity.Websites;
import com.attech.repository.UserRepository;
import com.attech.repository.WebsiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.Query;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class WebsitesService {
    @Autowired
    private WebsiteRepository websiteRepository;

    @Autowired
    private UserRepository userRepository;
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

    private Websites convertToEntity(WebsitesRequest requestWebsite){
        Websites websites = new Websites().builder()
                .name(requestWebsite.getName())
                .aliasWebsite(requestWebsite.getAliasWebsite())
                .domain(requestWebsite.getDomain())
                .nameServer(requestWebsite.getNameServer())
                .tokenWebsite(requestWebsite.getTokenWebsite())
                .dateCreate(new Date())
                .lastEdited(new Date())
                .build();
        return websites;
    }

    private ResponseWebsite convertToDTO(Websites websites) throws ParseException{
        ResponseWebsite responseWebsite = new ResponseWebsite().builder()
                .id(websites.getId())
                .name(websites.getName())
                .aliasWebsite(websites.getAliasWebsite())
                .tokenWebsite(websites.getTokenWebsite())
                .nameServer(websites.getNameServer())
                .domain(websites.getDomain())
                .build();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        if(websites.getDateCreate()!=null){
            Instant instant = websites.getDateCreate().toInstant();
            LocalDateTime localDateTime =instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            String formattedDatetime = localDateTime.format(formatter);
            responseWebsite.setDateCreate(formattedDatetime);
        }
        if(websites.getLastEdited()!=null){
            Instant instant = websites.getLastEdited().toInstant();
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();
            String formattedDatetime = localDateTime.format(formatter);
            responseWebsite.setLastEdited(formattedDatetime);
        }
        return responseWebsite;
    }
    public ResponseObject createdWebsite(WebsitesRequest websitesRequest) throws ParseException{
        ResponseObject responseObject =failObject();

        if (!websiteRepository.existsByNameAndAliasWebsite(websitesRequest.getName(), websitesRequest.getAliasWebsite())) {
            Websites websites = convertToEntity(websitesRequest);
            websiteRepository.save(websites);

            responseObject = successObject();
            responseObject.setResData(convertToDTO(websites));
        }
        return responseObject;
    }
    public ResponseObject getAllWebsite(){
        List<ResponseWebsite> list = websiteRepository.findAll().stream().map(websites ->{
            try{
                return convertToDTO(websites);

            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
        ResponseObject responseObject = successObject();
        responseObject.setResData(list);
        return responseObject;
    }

    public ResponseObject deleteWeb(Long id){
        Websites websites = websiteRepository.findById(id).orElse(null);
        ResponseObject responseObject=failObject();
        if(websites!=null){
            websiteRepository.delete(websites);
            responseObject=successObject();
        }
        return responseObject;
    }
    public ResponseObject updateWeb(Long id, WebsitesRequest websitesRequest)throws ParseException{
        Websites websites = websiteRepository.findById(id).orElse(null);
        ResponseObject responseObject = failObject();
        if(websiteRepository.existsByNameAndAliasWebsite(websitesRequest.getName(),websitesRequest.getAliasWebsite())){
            responseObject.setResData("name or aliasWebsite has been used");
        }
        else if(websites==null){
            responseObject.setResData("not found website");
        }
        else if(websitesRequest.getName()==null){
            responseObject.setResData("not found tokenWeb");
        }
        else if(websitesRequest.getNameServer()==null){
            responseObject.setResData("not found name server");
        } else if (websitesRequest.getDomain()==null) {
            responseObject.setResData("not found domain ");
        } else if (websites!=null) {
            websites=convertToEntity(websitesRequest);
            websites.setLastEdited(new Date());
            websiteRepository.save(websites);
            responseObject.setResData(convertToDTO(websites));
        }
        return responseObject;

    }
    public ResponseObject deleteWebById(Long id){
        ResponseObject responseObject = failObject();

        Websites websites = websiteRepository.findById(id).orElse(null);
        if (websites == null) {
            responseObject.setResDesc("not found websites");
        }else {
            websiteRepository.deleteById(id);
            responseObject = successObject();

            responseObject.setResData(websiteRepository.findAll().stream()
                    .map(websites1 -> {
                        try {
                            return convertToDTO(websites);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }
        return responseObject;
    }
    public ResponseObject findWebByID(Long id) throws ParseException {
        Websites websites= websiteRepository.findById(id).orElse(null);

        ResponseObject responseObject = failObject();

        if(websites != null){
            ResponseWebsite responseWebsite = convertToDTO(websites);

            responseObject = successObject();
            responseObject.setResData(responseWebsite);
        }
        return responseObject;
    }

//    public ResponseObject listWebByUserId(Long userId) throws ParseException{
//        ResponseObject responseObject = new ResponseObject();
//        User user = userRepository.findByUserId(userId);
//        List<ResponseWebsite> websites = websiteRepository.findAllByUserId(userId);
//        if(websites!=null){
//            responseObject.setResCode(AppConst.SUCCESS_CODE);
//            responseObject.setResData(websites);
//            responseObject.setResDesc(AppConst.SUCCESS_DESC);
//        }
//        return responseObject;
//    }
}
