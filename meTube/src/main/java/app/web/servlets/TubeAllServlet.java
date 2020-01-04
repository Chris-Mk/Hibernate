package app.web.servlets;

import app.domain.entities.Tube;
import app.domain.models.view.TubeViewModel;
import app.services.TubeService;
import app.utils.Mapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/tubes/all")
public class TubeAllServlet extends HttpServlet {

    private final TubeService tubeService;
    private final Mapper mapper;

    @Inject
    public TubeAllServlet(TubeService tubeService, Mapper mapper) {
        this.tubeService = tubeService;
        this.mapper = mapper;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tube> allTubes = this.tubeService.getAllTubes();
        List<TubeViewModel> viewModel = this.mapper.map(allTubes, new TypeToken<List<TubeViewModel>>() {}.getType());

        req.setAttribute("allTubes", viewModel);
        req.getRequestDispatcher("all-tubes.jsp").forward(req, resp);
    }
}
