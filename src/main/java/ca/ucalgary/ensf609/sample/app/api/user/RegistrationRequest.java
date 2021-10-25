package ca.ucalgary.ensf609.sample.app.api.user;

import lombok.Value;

@Value
class RegistrationRequest {

    String login;
    String password;
}
