package ns.feigndemo;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

	private List<User> list = new ArrayList<>();

	int counter = 1;

	@PostConstruct
	public void setup() {
		User user = new User();
		user.setId(counter++);
		user.setAge(33);
		user.setFirstName("Niraj");
		user.setLastName("Sonawane");
		user.setMiddleName("Ashok");
		list.add(user);

	}

	public List<User> getAllUsers() {
		return list;
	}

	public User getUser(int id) {
		return list.stream()
				.filter(user -> user.getId() == id)
				.findFirst()
				.orElseThrow(() -> new CustomException("No user found"));

	}

	public void addUser(User user) {
		user.setId(counter++);
		list.add(user);

	}

	public void updateUser(@RequestBody User user) {

		list.stream()
				.filter(u -> u.getId() == user.getId())
				.findFirst()
				.orElseThrow(() -> new CustomException("No user with user id " + user.getId()));

		list.removeIf(u -> u.getId() == user.getId());
		list.add(user);

	}

	public void deleteUser(int id) {
		list.removeIf(u -> u.getId() == id);

	}
}
