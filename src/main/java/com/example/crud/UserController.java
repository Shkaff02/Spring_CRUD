package com.example.crud;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@RestController
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final Repository repository;

    public UserController(Repository repository){
        this.repository = repository;
    }

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    public User createUser(@RequestBody User user){
        return repository.save(user);
    }

    @GetMapping("/users")
    @ResponseStatus(HttpStatus.OK)
    public List<User> getAllUsers(){
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User getUserById(@PathVariable Integer id){
        User user = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id = " + id));
        return user;
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User refreshUser(@PathVariable("id") Integer id, @RequestBody User user){
        return repository.findById(id)
                .map(entity -> {
                    entity.setName(user.getName());
                    entity.setEmail(user.getEmail());
                    entity.setCountry(user.getCountry());
                    return repository.save(entity);
                })
                .orElseThrow(()->new EntityNotFoundException("User not found with id = " + id));
    }

    @DeleteMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeUserById(@PathVariable Integer id){
        User user = repository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("User not found with id = " + id));
                repository.delete(user);
    }

}
