package app.web.servlets;

import app.domain.models.view.CarViewModel;
import app.service.CarService;
import app.util.FileUtil;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/all")
public class AllServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\javaEE\\src\\main\\webapp\\views\\all.html";

    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final CarService carService;

    @Inject
    public AllServlet(FileUtil fileUtil, ModelMapper modelMapper, CarService carService) {
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.carService = carService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        List<CarViewModel> carViewModels =
                this.modelMapper.map(this.carService.allCars(), new TypeToken<List<CarViewModel>>() {}.getType());

        StringBuilder builder = new StringBuilder();
        for (CarViewModel carViewModel : carViewModels) {
            builder.append(String.format("<li class=\"d-flex justify-content-around\">\n" +
                "<div class=\"col-md-4 d-flex flex-column text-center mb-3\">\n" +
                "    <h2 class=\"text-white text-center\">Brand: %s</h2>\n" +
                "    <h4 class=\"text-white text-center\">Model: %s</h4>\n" +
                "    <h4 class=\"text-white text-center\">Year: %s</h4>\n" +
                "    <h4 class=\"text-white text-center\">Engine: %s</h4>\n" +
                "</div>" +
            "</li>", carViewModel.getBrand(), carViewModel.getModel(), carViewModel.getYear(), carViewModel.getEngine()));
        }

        html = html.replace("{{replace}}", builder.toString());

        resp.getWriter().println(html);
    }
}
