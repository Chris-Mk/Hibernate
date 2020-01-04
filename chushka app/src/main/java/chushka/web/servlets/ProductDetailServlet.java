package chushka.web.servlets;

import chushka.domain.entities.Product;
import chushka.service.ProductService;
import chushka.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/details")
public class ProductDetailServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\chushka app\\src\\main\\resources\\views\\details-product.html";

    private final FileUtil fileUtil;
    private final ProductService productService;

    @Inject
    public ProductDetailServlet(FileUtil fileUtil, ProductService productService) {
        this.fileUtil = fileUtil;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Product product = this.productService.getProductByName(req.getQueryString().split("=")[1]);

        String html = this.fileUtil.readFile(FILE_PATH)
                .replace("{{name}}", product.getName())
                .replace("{{description}}", product.getDescription())
                .replace("{{type}}", product.getType().toString().toUpperCase());

        resp.getWriter().println(html);
    }
}
