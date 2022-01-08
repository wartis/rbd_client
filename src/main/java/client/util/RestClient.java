package client.util;

import client.auth.User;
import client.commands.Command;
import lombok.Setter;
import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class RestClient {

    private final static String BASE_URL = "http://localhost:8080/turtle/api";
    private final static String REGISTER_URL = "http://localhost:8080/auth/registration";
    private final static String LOGIN_URL = "http://localhost:8080/auth/login";

    @Setter
    private String JSESSIONID = "";

    private final OkHttpClient client = new OkHttpClient();

    public String get(Command command, @NotNull HashMap<String, String> params) {
        if (!isLogin()) {
            return "Для начала требуется авторизоваться!";
        }
        final String url = BASE_URL + command.relUrl + "?" + getQueryString(params);
        final Request request = RequestHandler.authenticatedGet(url, JSESSIONID);
        final Call call = client.newCall(request);

        try {
            final Response response = call.execute();
            return response.body().string();
        } catch (IOException e) {
            return "Произошла ошибка при выполнении запроса.";
        }
    }

    public boolean isLogin() {
        return !JSESSIONID.isEmpty();
    }


    public String login(String username, String password) {
        final User user = new User(username, password);
        final Request request = RequestHandler.loginOrRegister(LOGIN_URL, user);

        final Call call = client.newCall(request);
        try {
            final Response response = call.execute();
            List<String> cookielist = response.headers().values("Set-Cookie");

            JSESSIONID = (cookielist.get(0).split(";"))[0].split("=")[1];

            return "Вы успешно авторизовались. ";
        } catch (IOException e) {
            return "Произошла ошибка при выполнении запроса.";
        }
    }

    public String register(String username, String password) {
        final User user = new User(username, password);
        final Request request = RequestHandler.loginOrRegister(REGISTER_URL, user);

        final Call call = client.newCall(request);
        try {
            final Response response = call.execute();
            List<String> cookielist = response.headers().values("Set-Cookie");

            JSESSIONID = (cookielist.get(0).split(";"))[0].split("=")[1];

            return "Вы успешно зарегистрировались и авторизовались. ";
        } catch (IOException e) {
            return "Произошла ошибка при выполнении запроса.";
        }
    }

    private String getQueryString(HashMap<String, String> params) {
        final Set<Map.Entry<String, String>> entries = params.entrySet();

        return entries.size() == 0 ? "" : entries.stream()
            .map(ent -> ent.getKey() + "=" + ent.getValue())
            .collect(Collectors.joining("&"));
    }

}
