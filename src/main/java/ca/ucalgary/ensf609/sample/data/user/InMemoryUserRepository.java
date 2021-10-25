package ca.ucalgary.ensf609.sample.data.user;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

import ca.ucalgary.ensf609.sample.app.errors.UserNotFoundException;
import ca.ucalgary.ensf609.sample.domain.user.NewUser;
import ca.ucalgary.ensf609.sample.domain.user.User;
import ca.ucalgary.ensf609.sample.domain.user.UserRepository;

public class InMemoryUserRepository implements UserRepository {

    private static final Map<String,User> USERS_STORE = new ConcurrentHashMap();

    @Override
    public String create(NewUser newUser) {
        String id = UUID.randomUUID().toString();
        User user = User.builder()
            .id(id)
            .login(newUser.getLogin())
            .password(newUser.getPassword())
            .build();
        USERS_STORE.put(id, user);

        return id;
    }
    @Override
    public List<User> getUsers(){
        return new ArrayList<>( USERS_STORE.values());
    }
    @Override
    public  void deleteUser(String id) throws UserNotFoundException {
         User user= Optional.of(USERS_STORE.get(id)).orElseThrow(()->  new UserNotFoundException(404, "User not found."));
         USERS_STORE.remove(user.getId(),user);
    }
    @Override
    public  User updateUser(User user){
        Optional.of(USERS_STORE.get(user.getId())).orElseThrow(()->  new UserNotFoundException(404, "User not found."));
        USERS_STORE.replace(user.getId(), user);
        return  user;

    }
}
