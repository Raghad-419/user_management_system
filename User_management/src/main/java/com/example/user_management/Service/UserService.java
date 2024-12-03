package com.example.user_management.Service;

import com.example.user_management.ApiResponse.ApiException;
import com.example.user_management.Model.User;
import com.example.user_management.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> getAllUsers(){
        return userRepository.findAll();
    }


    public void addUser(User user){
        userRepository.save(user);
    }

    public void updateUser(Integer id , User user){
        User oldUser = userRepository.findUserById(id);
        if(oldUser==null){
            throw new ApiException("User not found");
        }
        oldUser.setAge(user.getAge());
        oldUser.setRole(user.getRole());
        oldUser.setEmail(user.getEmail());
        oldUser.setName(user.getName());
        oldUser.setPassword(user.getPassword());
        oldUser.setUsername(user.getUsername());
        userRepository.save(oldUser);

    }


    public void deleteUser(Integer id){
        User user =userRepository.findUserById(id);
        if(user==null){
            throw new ApiException("User not found");
        }
        userRepository.delete(user);
    }

    public void login(String username ,String password){
        User user =userRepository.giveUserByUsernameAndPassword(username,password);
        if(user==null){
            throw new ApiException("User not found");
        }
    }


    public User getUserByEmail(String email){
        User user =userRepository.giveUserByEmail(email);
        if(user==null){
            throw new ApiException("User not found");
        }
        return user;
    }

    public List<User> getUsersByRole(String role){
        List<User> userList =userRepository.findUserByRole(role);
        if(userList.isEmpty()){
            throw new ApiException("users not found ");
        }
        return userList;
    }

    public List<User> getAgeOrAbove(Integer age){
        List<User> userList =userRepository.findUserByAgeGreaterThanEqual(age);
        if(userList.isEmpty()){
            throw new ApiException("Users not found");
        }
        return userList;
    }


}
