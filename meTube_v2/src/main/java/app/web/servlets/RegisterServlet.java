package app.web.servlets;

import app.domain.models.service.UserRegistryServiceModel;
import app.domain.models.view.TubeViewModel;
import app.service.abstraction.UserService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    private final UserService userService;

    @Inject
    public RegisterServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/register.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserRegistryServiceModel userRegistryServiceModel =
                (UserRegistryServiceModel) req.getAttribute("userRegistryServiceModel");

        this.userService.createUser(userRegistryServiceModel);
        Collection<TubeViewModel> allTubes = this.userService.getAllTubes(userRegistryServiceModel.getUsername());

        req.setAttribute("username", userRegistryServiceModel.getUsername());
        req.setAttribute("allTubes", allTubes);
        req.getRequestDispatcher("/jsps/home.jsp").forward(req, resp);
    }
}
