package com.attech.service;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.user.ResponseUser;
import com.attech.model.dto.user.UserRequest;
import com.attech.model.entity.Avatar;
import com.attech.model.entity.Roles;
import com.attech.model.entity.User;
import com.attech.repository.AvatarRepository;
import com.attech.repository.RoleRepository;
import com.attech.repository.UserRepository;
import com.attech.repository.WebsiteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService{

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private WebsiteRepository webRepo;

    @Autowired
    private AvatarRepository avatarRepo;

    @Autowired
    EntityManager entityManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    private User convertToEntity(UserRequest requestUser){
        Roles roles = roleRepo.findByName(requestUser.getRoleName());

        User user = new User().builder()
                .userName(requestUser.getUserName())
                .email(requestUser.getEmail())
                .password(passwordEncoder.encode(requestUser.getPassWord()))
                .firstName(requestUser.getFirstName())
                .lastName(requestUser.getLastName())
                .role(roles != null ? roles.getId() : null)
                .status(true)
                .createdDate(new Date())
                .phone(requestUser.getPhone())
                .build();

        return user;
    }

    private ResponseUser convertToDTO(User user, AvatarRepository avatarRepo) throws ParseException{

        Avatar avatar = avatarRepo.findByUserID(user.getId()).get();
        Roles roles = roleRepo.findById(user.getRole()).orElse(null);

        ResponseUser responseUser = new ResponseUser().builder()
                .id(user.getId())
                .userName(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roleName(roles != null ? roles.getName() : "")
                .status(user.getStatus() ? "Active" : "Non-Active")
                .avatarName(avatar != null ? avatar.getAvaName() : "")
                .build();

        if(!StringUtils.isEmpty(user.getPhone())){
            String convertPhone = String.valueOf(new StringBuilder(user.getPhone())
                    .insert(3,".")
                    .insert(7,"."));

            responseUser.setPhone(convertPhone);
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        if(user.getCreatedDate() != null) {
            Instant instant = user.getCreatedDate().toInstant();
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            String formattedDateTime = localDateTime.format(formatter);

            responseUser.setCreatedDate(formattedDateTime);
        }

        if (user.getUpdatedDate() != null){
            Instant instant = user.getUpdatedDate().toInstant();
            LocalDateTime localDateTime = instant.atZone(ZoneId.systemDefault()).toLocalDateTime();

            String formattedDateTime = localDateTime.format(formatter);

            responseUser.setUpdatedDate(formattedDateTime);
        }

        return responseUser;
    }

    public ResponseObject createUser(UserRequest requestUser) throws ParseException {
        ResponseObject responseObject = failObject();

        if (!userRepo.existsByUserNameOrEmail(requestUser.getUserName(), requestUser.getEmail())) {
            User user = convertToEntity(requestUser);
            userRepo.save(user);
            responseObject = successObject();
            responseObject.setResData(convertToDTO(user,avatarRepo));
        }

        return responseObject;
    }

    public ResponseObject getAllUser(){
        List<ResponseUser> list = userRepo.findAll().stream().map(user -> {
                    try {
                        return convertToDTO(user,avatarRepo);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        ResponseObject responseObject = successObject();
        responseObject.setResData(list);

        return responseObject;
    }

    public ResponseObject getAllUserByKeySearch(String keySearch) {
        ResponseObject responseObject = failObject();

        String sql ="select  \n" +
                "\t  u.id,\n" +
                "    u.username,\n" +
                "    u.email,\n" +
                "    u.phone,\n" +
                "    u.firstname,\n" +
                "    u.lastname,\n" +
                "    r.role_name,\n" +
                "    u.website_id,\n" +
                "    u.avatar,\n" +
                "    date_format(u.createddate,'%d/%m/%Y %h:%m:%s'),\n" +
                "    date_format( u.updateddate,'%d/%m/%Y %h:%m:%s'),\n" +
                "    u.status\n" +
                "from user u \n" +
                "left join user_role us on u.id = us.user_id\n" +
                "left join roles r on r.id = us.role_id\n" +
                "where 1=1 ";

        if (!StringUtils.isEmpty(keySearch)) {
            sql += "and upper(u.username) like upper(:keySearch)\n" +
                    "or upper(u.email) like upper(:keySearch)\n" +
                    "or upper(u.phone) like upper(:keySearch)";
        }

        Query query = entityManager.createNativeQuery(sql);

        if(!StringUtils.isEmpty(keySearch)){
            query.setParameter("keySearch",'%' + keySearch.trim() + '%');
        }

        List<Object[]> list = query.getResultList();
        List<ResponseUser> lands = new ArrayList<>();

        if (list.isEmpty()) {
            responseObject.setResDesc("Not Found Object");

        }else{
            for (Object[] od : list) {

                    ResponseUser resUser = new ResponseUser();
                    resUser.setId(od[0] != null ? Long.parseLong(od[0].toString()) : null);
                    resUser.setUserName(od[1] != null ? od[1].toString() : "");
                    resUser.setEmail(od[2] != null ? od[2].toString() : "");

                    if (od[3] != null) {
                        String phoneConvert = String.valueOf(
                                new StringBuilder(od[3].toString())
                                        .insert(3, ".")
                                        .insert(7, "."));
                        resUser.setPhone(phoneConvert);
                    }

                    resUser.setFirstName(od[4] != null ? od[4].toString() : "");
                    resUser.setLastName(od[5] != null ? od[5].toString() : "");
                    resUser.setRoleName(od[6] != null ? od[6].toString() : "");
                    resUser.setWebsites(new HashSet<>());
                    resUser.setAvatarName(od[8] != null ? od[8].toString() : "");
                    resUser.setCreatedDate(od[9] != null ? od[9].toString() : "");
                    resUser.setUpdatedDate(od[10] != null ? od[10].toString() : "");
                    resUser.setStatus(od[11].toString().equals("1") ? "Active" : "Non-active");

                    lands.add(resUser);

            }
            responseObject = successObject();
            responseObject.setResData(lands);

        }
        return responseObject;
    }

    public ResponseObject findUserByID(Long id) throws ParseException {
        User user = userRepo.findById(id).orElse(null);

        ResponseObject responseObject = failObject();

        if(user != null){
            ResponseUser responseUser = convertToDTO(user,avatarRepo);

            responseObject = successObject();
            responseObject.setResData(responseUser);
        }

        return responseObject;
    }

    public ResponseObject editUser(Long id, UserRequest request) throws ParseException {
        User user = userRepo.findById(id).orElse(null);

        ResponseObject responseObject = failObject();

        if(user == null){
            responseObject.setResData("not found user");
        }

        else if(userRepo.existsByUserNameAndIdNot(request.getUserName(),id)){
            responseObject.setResData("user name has been used");
        }

        else if(userRepo.existsByEmailAndIdNot(request.getEmail(), id)){
            responseObject.setResData("email has been used");
        }

        else if(userRepo.existsByPhoneAndIdNot(request.getPhone(), id)){
            responseObject.setResData("phone number has been used");
        }

        else if(user != null){
            user = convertToEntity(request);
            user.setUpdatedDate(new Date());

            userRepo.save(user);

            responseObject = successObject();
            responseObject.setResData(convertToDTO(user,avatarRepo));
        }

        return responseObject;
    }

    public ResponseObject deleteUserById(Long id){
        ResponseObject responseObject = failObject();

        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            responseObject.setResDesc("not found user");
        }else {
            userRepo.deleteById(id);
            responseObject = successObject();

            responseObject.setResData(userRepo.findAll().stream()
                    .map(user1 -> {
                        try {
                            return convertToDTO(user, avatarRepo);
                        } catch (ParseException e) {
                            throw new RuntimeException(e);
                        }
                    }));
        }
        return responseObject;
    }

    public ResponseObject uploadAvatar(Long id, MultipartFile file) throws ParseException {
        ResponseObject responseObject = failObject();

        String fileName = file.getOriginalFilename();
        if (!fileName.toLowerCase().endsWith(".jpg") && !fileName.toLowerCase().endsWith(".png")) {
            responseObject.setResDesc("Only JPG and PNG files are allowed");

        }
        User user = userRepo.findById(id).orElse(null);
        Optional<Avatar> optionalAvatar = avatarRepo.findByUserID(user.getId());
        Avatar avatar = new Avatar();
        if (user == null) {
            responseObject.setResDesc("not found user");

            return responseObject;
        } else {
            Path path = Paths.get("/upload");

            if (!Files.exists(path)) {
                try {
                    Files.createDirectories(path);

                } catch (IOException e) {
                    responseObject.setResDesc(e.getMessage());

                }
            }
            try{
                InputStream inputStream = file.getInputStream();

                Files.copy(inputStream, path.resolve(file.getOriginalFilename()),
                        StandardCopyOption.REPLACE_EXISTING);

            } catch (IOException e) {
                responseObject.setResDesc(e.getMessage());

            }

            if (optionalAvatar.isPresent()) {
                avatar = optionalAvatar.get();

                avatar.setAvaName(fileName);

            }else {
                avatar = new Avatar().builder()
                        .avaName(file.getOriginalFilename())
                        .userID(user.getId())
                        .build();
            }
            avatarRepo.save(avatar);
            user.setAvatar(avatar.getId());
            user.setUpdatedDate(new Date());
            userRepo.save(user);

            responseObject = successObject();
            responseObject.setResData(convertToDTO(user,avatarRepo));
        }
        return responseObject;
    }

    public ResponseObject changeStatus(Long id) throws ParseException {
        User user = userRepo.findById(id).orElse(null);
        ResponseObject responseObject = failObject();

        if(user == null){
            responseObject.setResData("not found user");
            return responseObject;
        }

        user.setStatus(!user.getStatus());
        user.setUpdatedDate(new Date());
        userRepo.save(user);

        ResponseUser responseUser = convertToDTO(user,avatarRepo);
        responseObject = successObject();
        responseObject.setResData(responseUser);

        return responseObject;
    }

    public ResponseObject listUserByRole(String roleName){
        Roles roles = roleRepo.findByName(roleName);

        List<ResponseUser> list = userRepo.findAll().stream()
                .filter(user -> user.getRole() == roles.getId())
                .map(user -> {
                    try {
                        return convertToDTO(user,avatarRepo);
                    } catch (ParseException e) {
                        throw new RuntimeException(e);
                    }
                })
                .collect(Collectors.toList());

        ResponseObject responseObject = successObject();
        responseObject.setResData(list);
        return responseObject;
    }

    public ResponseObject permissionRole(String roleName, String userName) throws ParseException {
        ResponseObject responseObject = successObject();

        User user = userRepo.findByUserName(userName);
        Roles roles = roleRepo.findByName(roleName);
        if(user == null || roles == null){
            responseObject = failObject();

        }else {
            user.setRole(roles.getId());
            userRepo.save(user);
            responseObject.setResData(convertToDTO(user,avatarRepo));
        }

        return responseObject;
    }
}