package com.attech.service;

import com.attech.contain.AppConst;
import com.attech.model.dto.ResponseObject;
import com.attech.model.entity.Roles;
import com.attech.repository.RoleRepository;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepo;

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

    public ResponseObject createRole(String roleName) {
        ResponseObject responseObject = failObject();

        if(!roleRepo.existsByName(roleName)){
            Roles roles = new Roles().builder()
                    .name(roleName)
                    .build();

            roleRepo.save(roles);
            responseObject = successObject();
            responseObject.setResData(roles);

        }

        return responseObject;
    }

    public ResponseObject findByKeySearch(String keySearch){
        List<Roles> roles = StringUtils.isEmpty(keySearch) ?
                roleRepo.findAll() :
                roleRepo.findByKeySearch(keySearch);

        ResponseObject responseObject = successObject();
        responseObject.setResData(roles);

        return responseObject;
    }

    public Roles updateRole(Long id, String roleName){
        Roles roles = roleRepo.findById(id).orElse(null);

        if(roles == null){
            throw new RuntimeException("roles not found");
        }

        if(!roleRepo.existsByName(roleName)){
            roles.setName(roleName);
            roleRepo.save(roles);
        }
        return roles;
    }

    public List<Roles> getAllRole() {
        return roleRepo.findAll();
    }

    public ResponseObject deleteRoleByID(Long id){
        Roles roles = roleRepo.findById(id).orElse(null);
        ResponseObject responseObject = failObject();

        if(roles != null){
            roleRepo.deleteById(id);
            responseObject = successObject();
        }

        return responseObject;
    }
}
