package app.web.servlets;

import app.domain.entities.Tube;
import app.domain.models.view.TubeViewModel;
import app.services.TubeService;
import app.utils.Mapper;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tubes/details")
public class TubeDetailsServlet extends HttpServlet {

    private final TubeService tubeService;
    private final Mapper mapper;

    @Inject
    public TubeDetailsServlet(TubeService tubeService, Mapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String tubeName = req.getQueryString().split("=")[1];

        Tube tube = this.tubeService.getTubeByName(tubeName);
        TubeViewModel viewModel = this.mapper.map(tube, TubeViewModel.class);

        req.setAttribute("viewModel", viewModel);
        req.getRequestDispatcher("/details-tubes.jsp").forward(req, resp);
    }
}
