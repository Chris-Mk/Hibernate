package app.web.filters;

import app.domain.models.binding.UserRegistryBindingModel;
import app.domain.models.service.UserRegistryServiceModel;
import org.modelmapper.ModelMapper;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.*;

@WebFilter("/register")
public class UserRegistryFilter implements Filter {
    private static final String SALT = "askldhbfgasejlfbwepiurhw49ptyw4hrpQ'FKMwef[wowe[RIQ3POR8";
    private static final String ALGORITHM = "PBKDF2WithHmacSHA1";
    private static final int ITERATIONS = 65536;
    private static final int KEY_LENGTH = 128;

    private final Validator validator;
    private final ModelMapper modelMapper;

    @Inject
    public UserRegistryFilter(Validator validator, ModelMapper modelMapper) {
        this.validator = validator;
        this.modelMapper = modelMapper;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("post")) {
            String username = req.getParameter("username");
            char[] password = req.getParameter("password").toCharArray();
            char[] confirmPassword = req.getParameter("confirmPassword").toCharArray();
            String email = req.getParameter("email");

            UserRegistryBindingModel userRegistryBindingModel = new UserRegistryBindingModel();
            userRegistryBindingModel.setUsername(username);
            userRegistryBindingModel.setPassword(password);
            userRegistryBindingModel.setConfirmPassword(confirmPassword);
            userRegistryBindingModel.setEmail(email);

            if (areThereErrors(req, res, userRegistryBindingModel)) return;

            UserRegistryServiceModel userRegistryServiceModel =
                    this.modelMapper.map(userRegistryBindingModel, UserRegistryServiceModel.class);

            hashPassword(password, userRegistryServiceModel);
            req.setAttribute("userRegistryServiceModel", userRegistryServiceModel);
        }
        chain.doFilter(req, res);
    }

    private void hashPassword(char[] password, UserRegistryServiceModel userRegistryServiceModel) {
        try {
            KeySpec keySpec = new PBEKeySpec(password, SALT.getBytes(), ITERATIONS, KEY_LENGTH);

            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(ALGORITHM);

            byte[] secretKeyEncoded = secretKeyFactory.generateSecret(keySpec).getEncoded();

            userRegistryServiceModel.setPassword(Base64.getEncoder().encodeToString(secretKeyEncoded));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
    }

    private boolean areThereErrors(HttpServletRequest req, HttpServletResponse res, UserRegistryBindingModel userRegistryBindingModel) throws ServletException, IOException {
        List<String> violationMessages = new ArrayList<>();

        char[] password = userRegistryBindingModel.getPassword();
        char[] confirmPassword = userRegistryBindingModel.getConfirmPassword();

        if (!Arrays.toString(password).equals(Arrays.toString(confirmPassword))) {
            violationMessages.add("Passwords don't match!");
        }

        Set<ConstraintViolation<UserRegistryBindingModel>> violations =
                this.validator.validate(userRegistryBindingModel);

        if (!violations.isEmpty()) {
            for (ConstraintViolation<UserRegistryBindingModel> violation : violations) {
                violationMessages.add(violation.getMessage());
            }
        }

        if (!violationMessages.isEmpty()) {
            req.setAttribute("violationMessages", violationMessages);
            req.getRequestDispatcher("/jsps/error.jsp").forward(req, res);
            return true;
        }
        return false;
    }
}
