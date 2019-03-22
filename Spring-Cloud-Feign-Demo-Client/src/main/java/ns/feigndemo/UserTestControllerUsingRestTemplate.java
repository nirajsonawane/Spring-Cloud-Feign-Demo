package ns.feigndemo;

import jdk.nashorn.internal.ir.RuntimeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user-rest-template-client/")
public class UserTestControllerUsingRestTemplate {


    @GetMapping
    public List<User> getAllUsers() {
        System.out.println("Calling User Service using Feign Client!!");
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<User>> response = restTemplate.exchange(
                "http://localhost:8080/user/",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<User>>() {
                });
        List<User> users = response.getBody();
        return users;

    }

    @GetMapping("{id}")
    public User getUserById(@PathVariable("id") int id) {
        Map<String, String> uriParams = new HashMap<String, String>();
        uriParams.put("id", String.valueOf(id));

        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:8080/user/{id}")
                .buildAndExpand(uriParams)
                .toUri();

        System.out.println(uri);
        RestTemplate restTemplate = new RestTemplate();
        User forEntity = restTemplate.getForObject(uri, User.class);

        return forEntity;
    }

    @PostMapping
    public ResponseEntity addUser(@RequestBody User user) {
        System.out.println("Add user");
        System.out.println(user.toString());

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<User> request = new HttpEntity<>(user);


        ResponseEntity exchange = restTemplate
                .exchange("http://localhost:8080/user/", HttpMethod.POST, request, String.class);

        return exchange;
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteUser(@PathVariable int id) {
        System.out.println("delete user");
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(id));
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete("http://localhost:8080/user/{id}", params);
        return new ResponseEntity("User Deleted successfully", HttpStatus.OK);
    }
}
