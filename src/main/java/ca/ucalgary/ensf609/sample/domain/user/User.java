package ca.ucalgary.ensf609.sample.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Value;

@Value
@Getter
@Builder
public class User {

    String id;
    String login;
    String password;
}
