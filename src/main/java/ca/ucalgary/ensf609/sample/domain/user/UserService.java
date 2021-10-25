package ca.ucalgary.ensf609.sample.domain.user;

import ca.ucalgary.ensf609.sample.app.errors.UserNotFoundException;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public String create(NewUser user) {
        return userRepository.create(user);
    }

    public List<User> getUsers(){return  userRepository.getUsers();}

    public void deleteUser(String id) throws UserNotFoundException{
        Objects.requireNonNull(id,"User id is required");
        userRepository.deleteUser(id);
    }
    public User updateUser(User user) throws UserNotFoundException{
        Objects.requireNonNull(user.getId(),"User id is required for update");
        Objects.requireNonNull(user.getLogin(),"User login is required for update");
        Objects.requireNonNull(user.getPassword(),"User password is required for update");
        return  userRepository.updateUser(user);

    }



}
