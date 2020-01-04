package app.web.servlets;

import app.domain.models.service.UserLoginServiceModel;
import app.domain.models.view.UserViewModel;
import app.service.abstraction.UserService;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final String SALT = "askldhbfgasejlfbwepiurhw49ptyw4hrpQ'FKMwef[wowe[RIQ3POR8";
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;

    private final UserService userService;

    @Inject
    public LoginServlet(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsps/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserLoginServiceModel userLoginServiceModel = (UserLoginServiceModel) req.getAttribute("userLoginServiceModel");
        UserViewModel userViewModel = null;

        try {
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            KeySpec keySpec = new PBEKeySpec(userLoginServiceModel.getPassword(), SALT.getBytes(), ITERATIONS, KEY_LENGTH);
            byte[] secretKey = secretKeyFactory.generateSecret(keySpec).getEncoded();

             userViewModel = this.userService.getUserByUsernameAndPassword(userLoginServiceModel.getUsername(),
                    Base64.getEncoder().encodeToString(secretKey));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }

        if (userViewModel != null) {
            req.setAttribute("username", userViewModel.getUsername());
            req.setAttribute("allTubes", userViewModel.getTubes());
            req.getRequestDispatcher("/jsps/home.jsp").forward(req, resp);
        }
    }
}
