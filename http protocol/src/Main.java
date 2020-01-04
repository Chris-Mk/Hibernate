import java.time.LocalDate;
import java.util.*;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String input = getRequest();
        String[] validUrls = getValidUrls(input);
        String request = input.substring(input.indexOf(System.lineSeparator()) + 2);

        HttpRequest httpRequest = new HttpRequestImpl(request);
        HttpResponse httpResponse = new HttpResponseImpl(httpRequest, validUrls);

//        System.out.println(new String(httpResponse.getBytes()));
        httpRequest.getCookies().forEach(System.out::println);

//        final List<String> validUrls = getValidUrls();
//        final List<String> requestHeader = getRequestHeader();
//        final String requestMethod = getRequestMethod(requestHeader.get(0));
//        final String requestUrl = getRequestUrl(requestHeader.get(0));
//        final Map<String, String> requestHeaderParams = getHeaderParams(requestHeader);
//        final Map<String, String> requestBodyParams = getRequestBodyParams();
//
//        if (!validUrls.contains(requestUrl)) {
//            printHttpNotFoundResponse(requestHeaderParams);
//        } else if (!requestHeaderParams.containsKey("Authorization")) {
//            printHttpUnauthorizedResponse(requestHeaderParams);
//        } else if (requestMethod.equals("POST") && requestBodyParams.isEmpty()) {
//            printHttpBadRequestResponse(requestHeaderParams);
//        } else {
//            printHttpOkResponse(requestHeaderParams, requestBodyParams);
//        }

    }

    private static void printHttpOkResponse(Map<String, String> requestHeaderParams, Map<String, String> requestBodyParams) {
        if (!requestHeaderParams.containsKey("Date")) {
            requestHeaderParams.put("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 200 OK")
                .append(System.lineSeparator());

        requestHeaderParams.entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator());
        if (requestBodyParams.isEmpty()) {
            builder.append(String.format("Greetings %s!", new String(Base64.getDecoder()
                    .decode(requestHeaderParams.get("Authorization").split("\\s+")[1]))));
        } else {
            builder.append(String.format("Greetings %s! You have successfully created %s with quantity – %s, price – %s.",
                    new String(Base64.getDecoder().decode(requestHeaderParams.get("Authorization").split("\\s+")[1])),
                    requestBodyParams.get("name"),
                    requestBodyParams.get("quantity"),
                    requestBodyParams.get("price")));
        }

        System.out.println(builder.toString());
    }

    private static void printHttpBadRequestResponse(Map<String, String> requestHeaderParams) {
        if (!requestHeaderParams.containsKey("Date")) {
            requestHeaderParams.put("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 400 Bad Request")
                .append(System.lineSeparator());

        requestHeaderParams.entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("There was an error with the requested functionality due to malformed request.");
        System.out.println(builder.toString());
    }

    private static void printHttpUnauthorizedResponse(Map<String, String> requestHeaderParams) {
        if (!requestHeaderParams.containsKey("Date")) {
            requestHeaderParams.put("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 401 Unauthorized")
                .append(System.lineSeparator());

        requestHeaderParams.entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("You are not authorized to access the requested functionality.");
        System.out.println(builder.toString());
    }

    private static void printHttpNotFoundResponse(Map<String, String> requestHeaderParams) {
        if (!requestHeaderParams.containsKey("Date")) {
            requestHeaderParams.put("Date", LocalDate.now().toString());
        }

        StringBuilder builder = new StringBuilder();
        builder.append("HTTP/1.1 404 Not Found")
                .append(System.lineSeparator());

        requestHeaderParams.entrySet()
                .stream()
                .filter(header -> !header.getKey().equals("Authorization"))
                .forEach(kvp ->
                        builder.append(String.format("%s: %s", kvp.getKey(), kvp.getValue()))
                                .append(System.lineSeparator()));

        builder.append(System.lineSeparator())
                .append("The requested functionality was not found.");
        System.out.println(builder.toString());
    }

    private static Map<String, String> getRequestBodyParams() {
        Map<String, String> bodyParams = new LinkedHashMap<>();
        final String requestBody = scanner.nextLine();

        if (!requestBody.isEmpty()) {
            Arrays.stream(requestBody.split("&"))
                    .map(value -> value.split("="))
                    .forEach(kvp -> bodyParams.put(kvp[0], kvp[1]));
        }

        return bodyParams;
    }

    private static Map<String, String> getHeaderParams(List<String> requestHeader) {
        Map<String, String> header = new LinkedHashMap<>();
        requestHeader.stream()
                .skip(1)
                .map(line -> line.split(":\\s+"))
                .forEach(kvp -> header.put(kvp[0], kvp[1]));

        return header;
    }

    private static String getRequestUrl(String requestLine) {
        return requestLine.split("\\s+")[1];
    }

    private static String getRequestMethod(String requestLine) {
        return requestLine.split("\\s+")[0];
    }

    private static String[] getValidUrls(String request) {
        return Arrays.stream(request.split("\r\n")[0].split("\\s+"))
                .toArray(String[]::new);
    }

    private static String getRequest() {
        StringBuilder request = new StringBuilder();
        String line;

        while (!"".equals(line = scanner.nextLine())) {
            request.append(line)
                    .append(System.lineSeparator());
        }

        if (!"".equals(line = scanner.nextLine())) {
            request.append(System.lineSeparator())
                    .append(line);
        }

        return request.toString();
    }
}
