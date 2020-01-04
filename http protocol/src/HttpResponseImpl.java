import java.time.LocalDate;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;

public class HttpResponseImpl implements HttpResponse {
    private HashMap<String, String> headers;
    private int statusCode;
    private byte[] content;
    private byte[] bytes;

    public HttpResponseImpl(HttpRequest httpRequest, String[] validUrls) {
        this.headers = new HashMap<>();
        this.init(httpRequest, validUrls);
    }

    private void init(HttpRequest httpRequest, String[] validUrls) {
        if (!Arrays.asList(validUrls).contains(httpRequest.getRequestUrl())) {
            this.setNotFoundResponse(httpRequest);
        } else if (!httpRequest.getHeaders().containsKey("Authorization")) {
            this.setUnauthorizedResponse(httpRequest);
        } else if (httpRequest.getMethod().equals("POST") && httpRequest.getBodyParameters().isEmpty()) {
            this.setBadRequestResponse(httpRequest);
        } else {
            this.setOkResponse(httpRequest);
        }
    }

    private void setOkResponse(HttpRequest httpRequest) {
        if (!httpRequest.getHeaders().containsKey("Date")) {
            httpRequest.addHeader("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK")
                .append(System.lineSeparator());

        httpRequest.getHeaders().entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator());
        String content;
        if (httpRequest.getBodyParameters().isEmpty()) {
            content = String.format("Greetings %s!", new String(Base64.getDecoder()
                    .decode(httpRequest.getHeaders().get("Authorization").split("\\s+")[1])));
            builder.append(content);
        } else {
            content = String.format("Greetings %s! You have successfully created %s with quantity – %s, price – %s.",
                    new String(Base64.getDecoder().decode(httpRequest.getHeaders().get("Authorization").split("\\s+")[1])),
                    httpRequest.getBodyParameters().get("name"),
                    httpRequest.getBodyParameters().get("quantity"),
                    httpRequest.getBodyParameters().get("price"));
            builder.append(content);
        }

        this.setContent(content.getBytes());
        this.bytes = builder.toString().getBytes();
    }

    private void setBadRequestResponse(HttpRequest httpRequest) {
        if (!httpRequest.getHeaders().containsKey("Date")) {
            httpRequest.addHeader("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 400 Bad Request")
                .append(System.lineSeparator());

        httpRequest.getHeaders().entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("There was an error with the requested functionality due to malformed request.");

        this.setContent("There was an error with the requested functionality due to malformed request.".getBytes());
        this.bytes = builder.toString().getBytes();
    }

    private void setUnauthorizedResponse(HttpRequest httpRequest) {
        if (!httpRequest.getHeaders().containsKey("Date")) {
            httpRequest.addHeader("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 401 Unauthorized")
                .append(System.lineSeparator());

        httpRequest.getHeaders().entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("You are not authorized to access the requested functionality.");

        this.setContent("You are not authorized to access the requested functionality.".getBytes());
        this.bytes = builder.toString().getBytes();
    }

    private void setNotFoundResponse(HttpRequest httpRequest) {
        if (!httpRequest.getHeaders().containsKey("Date")) {
            httpRequest.addHeader("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 404 Not Found")
                .append(System.lineSeparator());

        httpRequest.getHeaders().entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("The requested functionality was not found.");

        this.setContent("The requested functionality was not found.".getBytes());
        this.bytes = builder.toString().getBytes();
    }


    @Override
    public HashMap<String, String> getHeaders() {
        return this.headers;
    }

    @Override
    public int getStatusCode() {
        return this.statusCode;
    }

    @Override
    public byte[] getContent() {
        return this.content;
    }

    @Override
    public byte[] getBytes() {
        return this.bytes;
    }

    @Override
    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public void addHeader(String header, String value) {
        this.headers.put(header, value);
    }
}
