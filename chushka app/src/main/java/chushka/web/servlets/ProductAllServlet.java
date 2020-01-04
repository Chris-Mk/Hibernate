package chushka.web.servlets;

import chushka.service.ProductService;
import chushka.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

@WebServlet("/products/all")
public class ProductAllServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\chushka app\\src\\main\\resources\\views\\all-products.html";

    private final FileUtil fileUtil;
    private final ProductService productService;

    @Inject
    public ProductAllServlet(FileUtil fileUtil, ProductService productService) {
        this.fileUtil = fileUtil;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.fileUtil.readFile(FILE_PATH)
                .replace("{{products}}", "<ul>" + this.productService
                .getAllProducts()
                .stream()
                .map(product -> String.format("<li><a href=\"products/details?name=%s\">%s</li>",
                        product.getName(),
                        product.getName()))
                .collect(Collectors.joining()) + "</ul>");

        resp.getWriter().println(html);
    }
}
