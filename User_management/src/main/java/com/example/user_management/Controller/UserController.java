package com.example.user_management.Controller;

import com.example.user_management.ApiResponse.ApiResponse;
import com.example.user_management.Model.User;
import com.example.user_management.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity getAllUsers(){
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("/add")
public ResponseEntity addUser(@RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }

        userService.addUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added"));
}

@PutMapping("/update/{id}")
public ResponseEntity updateUser(@PathVariable Integer id , @RequestBody @Valid User user , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        userService.updateUser(id,user);
        return ResponseEntity.status(200).body(new ApiResponse("User updated"));
}

@DeleteMapping("/delete/{id}")
public ResponseEntity deleteUser(@PathVariable Integer id){
        userService.deleteUser(id);
        return ResponseEntity.status(200).body(new ApiResponse("User deleted"));
}

@GetMapping("/login/{username}/{password}")
public ResponseEntity login(@PathVariable String username ,@PathVariable String password){
        userService.login(username,password);
            return ResponseEntity.status(200).body(new ApiResponse("Login successful"));

}

@GetMapping("/getByEmail/{email}")
public ResponseEntity getUserByEmail(@PathVariable String email){
       User user=  userService.getUserByEmail(email);
       return ResponseEntity.status(200).body(user);
}

@GetMapping("/getUsersByRole/{role}")
public ResponseEntity getUsersByRole(@PathVariable String role){
    List<User> userList =userService.getUsersByRole(role);
    return ResponseEntity.status(200).body(userList);
}

@GetMapping("/getAgeOrAbove/{age}")
public ResponseEntity getAgeOrAbove(@PathVariable Integer age){
        List<User> userList =userService.getAgeOrAbove(age);
        return ResponseEntity.status(200).body(userList);
}


}
