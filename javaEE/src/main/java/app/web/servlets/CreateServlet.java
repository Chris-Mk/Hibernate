package app.web.servlets;

import app.domain.models.binding.CarCreateBindingModel;
import app.domain.models.service.CarServiceModel;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/create")
public class CreateServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\javaEE\\src\\main\\webapp\\views\\create.html";

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final CarService carService;

    @Inject
    public CreateServlet(FileUtil fileUtil, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CarCreateBindingModel carCreateBindingModel = new CarCreateBindingModel();
        carCreateBindingModel.setBrand(req.getParameter("brand"));
        carCreateBindingModel.setModel(req.getParameter("model"));
        carCreateBindingModel.setYear(Integer.parseInt(req.getParameter("year")));
        carCreateBindingModel.setEngine(req.getParameter("engine"));

        this.carService.createCar(this.modelMapper.map(carCreateBindingModel, CarServiceModel.class));
        resp.sendRedirect("/all");
    }
}
