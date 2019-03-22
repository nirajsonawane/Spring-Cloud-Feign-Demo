package ns.feigndemo;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(name = "User", url = "http://localhost:8080",configuration=UserClientConfig.class)
public interface UserClient {

    @RequestMapping(method = RequestMethod.GET, value = "/user")
    List<User> getAllUsers();

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    User getUser(@PathVariable("id") int id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/user/{id}")
    ResponseEntity deleteUser(@PathVariable("id") int id);

    @RequestMapping(method = RequestMethod.POST, value = "/user/")
    ResponseEntity addUser(@RequestBody User user);

    @RequestMapping(method = RequestMethod.PUT, value = "/user/")
    ResponseEntity updateUser(User user);
}

