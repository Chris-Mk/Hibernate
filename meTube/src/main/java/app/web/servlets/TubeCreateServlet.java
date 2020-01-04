package app.web.servlets;

import app.domain.models.binding.TubeCreateBindingModel;
import app.domain.models.service.TubeServiceModel;
import app.services.TubeService;
import app.utils.Mapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/create")
public class TubeCreateServlet extends HttpServlet {

    private final TubeService tubeService;
    private final Mapper mapper;

    @Inject
    public TubeCreateServlet(TubeService tubeService, Mapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("create-tube.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        TubeCreateBindingModel bindingModel = ((TubeCreateBindingModel) req.getAttribute("bindingModel"));

        TubeServiceModel serviceModel = this.mapper.map(bindingModel, TubeServiceModel.class);
        this.tubeService.saveTube(serviceModel);

        resp.sendRedirect("/tubes/details?name=" + serviceModel.getTitle());
    }
}
