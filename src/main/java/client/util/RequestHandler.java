package client.util;

import client.auth.User;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestHandler {

    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");


    public static Request authenticatedGet(String urlWithParams, String jsessionId) {
        return new Request.Builder()
            .url(urlWithParams)
            .addHeader("Cookie", "JSESSIONID=" + jsessionId)
            .build();
    }

    public static Request loginOrRegister(String url, User user) {
        return new Request.Builder()
            .url(url)
            .post(RequestBody.create(user.toJson(), JSON))
            .build();
    }


    private RequestHandler() {

    }
}
