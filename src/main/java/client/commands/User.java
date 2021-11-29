package client.commands;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class User {
    private String username;
    private String password;

    public String toJson() {
        return "{\"username\": \"" + username + "\", " + "\"password\": \"" + password + "\"}";
    }
}
