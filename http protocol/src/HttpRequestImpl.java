import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class HttpRequestImpl implements HttpRequest {
    private String method;
    private String requestUrl;
    private HashMap<String, String> headers;
    private HashMap<String, String> bodyParameters;
    private List<HttpCookie> cookies;

    public HttpRequestImpl(String httpRequest) {
        this.headers = new HashMap<>();
        this.bodyParameters = new HashMap<>();
        this.cookies = new ArrayList<>();
        this.init(httpRequest);
    }

    private void init(String httpRequest) {
        String[] request = httpRequest.split("\r\n");

        String method = request[0].split("\\s+")[0];
        this.setMethod(method);

        String requestUrl = request[0].split("\\s+")[1];
        this.setRequestUrl(requestUrl);

        for (int i = 1; i < request.length; i++) {
            if (request[i].equals("")) {
                Arrays.stream(request[i + 1].split("&"))
                        .map(b -> b.split("="))
                        .forEach(bp -> bodyParameters.put(bp[0], bp[1]));
                break;
            }
            String[] kvp = request[i].split(": ");
            this.headers.put(kvp[0], kvp[1]);
        }

        Arrays.stream(this.headers.get("Cookie").split(";\\s+"))
                .map(v -> v.split("="))
                .forEach(c -> this.addCookie(new HttpCookie(c[0], c[1])));
    }

    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public HashMap<String, String> getBodyParameters() {
        return this.bodyParameters;
    }

    @Override
    public List<HttpCookie> getCookies() {
        return this.cookies;
    }

    @Override
    public void addCookie(HttpCookie cookie) {
        this.cookies.add(cookie);
    }

    @Override
    public String getMethod() {
        return this.method;
    }

    @Override
    public void setMethod(String method) {
        this.method = method;
    }

    @Override
    public String getRequestUrl() {
        return this.requestUrl;
    }

    @Override
    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }

    @Override
    public void addBodyParameter(String parameter, String value) {
        this.bodyParameters.put(parameter, value);
    }

    @Override
    public boolean isResource() {
        return false;
    }
}
