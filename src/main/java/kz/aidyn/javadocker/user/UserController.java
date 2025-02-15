package kz.aidyn.javadocker.user;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @GetMapping("/index")
    public List<User> findAll(){
        return userRepository.findAll();
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable("id") Long id){
        return userRepository.findById(id);
    }

    @PostMapping("/new")
    public ResponseEntity<String> create(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("User created successfully");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody User user, @PathVariable("id") Long id){
        userRepository.update(id, user);
        return ResponseEntity.ok("User updated successfully");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id){
        userRepository.delete(id);
        return ResponseEntity.ok("User deleted successfully");
    }




}
