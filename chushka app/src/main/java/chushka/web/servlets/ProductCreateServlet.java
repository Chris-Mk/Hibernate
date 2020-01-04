package chushka.web.servlets;

import chushka.domain.entities.Product;
import chushka.domain.entities.Type;
import chushka.domain.models.ProductCreateModel;
import chushka.service.ProductService;
import chushka.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/products/create")
public class ProductCreateServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\chushka app\\src\\main\\resources\\views\\create-product.html";

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ProductService productService;

    @Inject
    public ProductCreateServlet(FileUtil fileUtil, ModelMapper modelMapper, ProductService productService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.productService = productService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ProductCreateModel productCreateModel = new ProductCreateModel.Builder()
                .name(req.getParameter("name"))
                .description(req.getParameter("description"))
                .type(req.getParameter("type"))
                .build();

        Product product = this.modelMapper.map(productCreateModel, Product.class);
        product.setType(Type.valueOf(productCreateModel.getType()));

        this.productService.persist(product);
        resp.sendRedirect("/products/all");
    }
}
