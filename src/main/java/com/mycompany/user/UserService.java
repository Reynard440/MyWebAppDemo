package com.mycompany.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepo;

    @Autowired
    public UserService(UserRepository userRepo){
        this.userRepo = userRepo;
    }

    public List<User> getUsers(){
        return userRepo.findAll();
    }

    public void addUser(User user) {
        Optional<User> userOptional = userRepo.findUserByEmail(user.getEmail());
        if(userOptional.isPresent()){
            throw new IllegalStateException("email already in use!");
        }
        userRepo.save(user);
    }

    public void deleteUser(Integer id) {
        boolean user = userRepo.existsById(id);
        if(!user){
            throw new IllegalStateException("user with id " + id + " does not exist!");
        }
        userRepo.deleteById(id);
    }

    @Transactional
    public void updateUser(Integer id, String firstname, String lastname) {
        User user = userRepo.findById(id).orElseThrow(() -> new IllegalStateException("user with id " + id + " does not exist!"));
        if (firstname != null && (firstname.length() > 0) && !Objects.equals(user.getFirstname(), firstname)) {
            user.setFirstname(firstname);
        }

        if (lastname != null && lastname.length() > 0 && !Objects.equals(user.getLastname(), lastname)){
            user.setLastname(lastname);
        }
    }
}
