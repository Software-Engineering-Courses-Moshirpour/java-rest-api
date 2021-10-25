package ca.ucalgary.ensf609.sample.app.api.user;

import ca.ucalgary.ensf609.sample.domain.user.User;
import lombok.Value;

import java.util.List;

@Value
public class UserListResponse {
    List<User> users;
}
