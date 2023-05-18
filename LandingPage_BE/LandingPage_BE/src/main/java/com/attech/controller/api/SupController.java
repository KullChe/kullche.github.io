package com.attech.controller.api;

import com.attech.model.dto.ResponseObject;
import com.attech.model.dto.user.UserRequest;
import com.attech.model.entity.Roles;
import com.attech.service.RoleService;
import com.attech.service.UserService;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/sup")
public class SupController {
    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;
//    user management

    @PostMapping("/user/add")
    public ResponseEntity<?> addUser(@RequestBody UserRequest requestUser) throws ParseException {
        return ResponseEntity.ok(userService.createUser(requestUser));
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseObject> showAllListUser(@RequestParam("keySearch") String keySearch){
        return StringUtils.isEmpty(keySearch) ?
                ResponseEntity.ok(userService.getAllUserByKeySearch(keySearch)) :
                ResponseEntity.ok(userService.getAllUser());
    }

    @PostMapping("/user/upload")
    public ResponseEntity<?> uploadAvatar(@RequestParam(value = "file") MultipartFile path,@RequestParam(value = "id") Long id) throws ParseException{
        ResponseObject responseObject = userService.uploadAvatar(id,path);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/user/detail")
    public ResponseEntity<?> findUserById(@RequestParam(value = "id") Long id) throws ParseException {
        ResponseObject responseObject = userService.findUserByID(id);
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/user/edit")
    public ResponseEntity<?> editUser(@RequestParam(value = "id") Long id,@RequestBody UserRequest request) throws ParseException {
        return ResponseEntity.ok(userService.editUser(id, request));
    }

    @DeleteMapping("/user/del")
    public ResponseEntity<ResponseObject> deleteUser(@RequestParam Long id){
        ResponseObject responseObject = userService.deleteUserById(id);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/user/status/change")
    public ResponseEntity<ResponseObject> changeStatus(@RequestParam Long id) throws ParseException{
        ResponseObject responseObject = userService.changeStatus(id);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/user/role")
    public ResponseEntity<ResponseObject> listUserByRole(@RequestParam String roleName){
        ResponseObject responseObject = userService.listUserByRole(roleName);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("user/role/permission")
    public ResponseEntity<ResponseObject> permissionUserRole(@RequestParam(value = "roleName") String roleName, @RequestParam(value = "userName") String userName) throws ParseException{
        ResponseObject responseObject = userService.permissionRole(roleName,userName);
        return ResponseEntity.ok(responseObject);
    }

//    role management

    @PostMapping("/role/add")
    public ResponseEntity<ResponseObject> addRole(@RequestBody String roleName) {
        ResponseObject responseObject = roleService.createRole(roleName);
        return ResponseEntity.ok(responseObject);
    }

    @PostMapping("/role")
    public ResponseEntity<ResponseObject> showAllListRole(@RequestParam(value = "keySearch") String keySearch){
        ResponseObject responseObject = roleService.findByKeySearch(keySearch);
        return ResponseEntity.ok(responseObject);
    }

    @PutMapping("/role/edit/{id}")
    public ResponseEntity<?> editRole(@PathVariable Long id,@RequestBody String roleName) throws ParseException {
        Roles roles = roleService.updateRole(id,roleName);
        return new ResponseEntity<>(roles,HttpStatus.OK);
    }

    @DeleteMapping("/role/del/{id}")
    public ResponseEntity<ResponseObject> deleteRole(@PathVariable Long id){
        ResponseObject responseObject = roleService.deleteRoleByID(id);
        return ResponseEntity.ok(responseObject);
    }

//    website management


}
