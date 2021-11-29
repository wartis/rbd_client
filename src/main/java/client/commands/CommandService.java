package client.commands;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.util.HashMap;

@ShellComponent
public class CommandService {
    private final RestClient restClient = new RestClient();

    @ShellMethod("Command to change turtle’s position on N steps")
    public String move(int steps) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("steps", String.valueOf(steps));

        return restClient.get(Command.MOVE, params);
    }

    @ShellMethod("Command to change turtle’s angle of direction to N degrees")
    public String angle(int angle) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("angle", String.valueOf(angle));

        return restClient.get(Command.ANGLE, params);
    }

    @ShellMethod("Command to put down the pen.")
    public String pd() {
        return restClient.get(Command.PUT_DOWN, new HashMap<>());
    }

    @ShellMethod("Command to put up the pen.")
    public String pu() {
        return restClient.get(Command.PUT_UP, new HashMap<>());
    }

    @ShellMethod("Command to change turtle’s color of the pen to {colorName} color. Possible values: black,\n" +
        "green.")
    public String color(String color) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("color", color.toUpperCase());
        return restClient.get(Command.COLOR, params);
    }

    @ShellMethod("Command to show all executed steps.")
    public String liststeps() {
        return restClient.get(Command.LIST_OF_STEPS, new HashMap<>());
    }

    @ShellMethod("Command to show all properties of completed figures")
    public String listfigures() {
        return restClient.get(Command.LIST_OF_FIGURES, new HashMap<>());
    }

    @ShellMethod("Command to registrate and login")
    public String register(String username, String password) {
        return restClient.register(username, password);
    }

    @ShellMethod("Command to login")
    public String login(String username, String password) {
        return restClient.login(username, password);
    }
}
