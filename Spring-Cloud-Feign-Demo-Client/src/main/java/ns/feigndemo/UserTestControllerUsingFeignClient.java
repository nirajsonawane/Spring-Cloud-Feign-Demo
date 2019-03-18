package ns.feigndemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user-feign-client/")
public class UserTestControllerUsingFeignClient {

    @Autowired
    private UserClient userClient;

    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Calling User Service using Feign Client!!");
        return userClient.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable("id") int id) {
        System.out.println("Calling User Service using Feign Client!! " + id);
        return userClient.getUser(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity deleteUser(@PathVariable("id") int id) {
        System.out.println("In Delete User");
        System.out.println("New Values");
        return userClient.deleteUser(id);
    }
    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        System.out.println("ADD User!!!");
        return userClient.addUser(user);
    }
    @PutMapping
    public ResponseEntity updateUser(@RequestBody User user){
        System.out.println("Update User");
        System.out.println(user.toString());
        return userClient.updateUser(user);

    }

}
