package ns.feigndemo;

import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(value="UserResources", consumes="application/json", produces="application/json",protocols="http", description="Operations Related to User")
public class UserController {

	@Autowired
	private UserService userservice;

	
	@GetMapping("/{id}")
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "getUser" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully retrieved User"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})	
	public User getUser(@ApiParam("Id of user, Can not be null") @PathVariable int id) {
		System.out.println("Get USer !!!");
		return userservice.getUser(id);
	}
	@PostMapping
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "addUser" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Added User"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public ResponseEntity addUser(@RequestBody User user) {
		System.out.println("Post!!!1");
		userservice.addUser(user);
		return new ResponseEntity("User Added successfully", HttpStatus.OK);
	}
	@PutMapping
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "updateUser" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Updated User"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})
	public ResponseEntity updateUser(@RequestBody User user) {
		userservice.updateUser(user);
		return new ResponseEntity("User Updated successfully", HttpStatus.OK);
	}
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "deleteUser" )
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Successfully Deleted User"),
			@ApiResponse(code = 401, message = "The request has not been applied because it lacks valid authentication credentials for the target resource"),
			@ApiResponse(code = 403, message = "The server understood the request but refuses to authorize it"),
			@ApiResponse(code = 404, message = "The resource  not found")
	})

	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser(@PathVariable int id) {
		userservice.deleteUser(id);
		return new ResponseEntity("User Deleted successfully", HttpStatus.OK);
	}
	@ApiOperation(consumes="application/json", produces="application/json",protocols="http", value = "getAllUsers" )	
	@GetMapping	
	public List<User> getAllUsers() {
		return userservice.getAllUsers();
	}

}
