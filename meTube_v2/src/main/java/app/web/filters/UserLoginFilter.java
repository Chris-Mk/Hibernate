package app.web.filters;

import app.domain.models.binding.UserLoginBindingModel;
import app.domain.models.service.UserLoginServiceModel;
import org.modelmapper.ModelMapper;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebFilter("/login")
public class UserLoginFilter implements Filter {

    private final Validator validator;
    private final ModelMapper modelMapper;

    @Inject
    public UserLoginFilter(Validator validator, ModelMapper modelMapper) {
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

            UserLoginBindingModel userLoginBindingModel = new UserLoginBindingModel();
            userLoginBindingModel.setUsername(username);
            userLoginBindingModel.setPassword(password);

            if (!this.validator.validate(userLoginBindingModel).isEmpty()) {
                List<String> violationMessages = new ArrayList<>();

                for (ConstraintViolation<UserLoginBindingModel> violation : this.validator.validate(userLoginBindingModel)) {
                    violationMessages.add(violation.getMessage());
                }

                req.setAttribute("violationMessages", violationMessages);
                req.getRequestDispatcher("/jsps/error.jsp").forward(request, response);
                return;
            }

            UserLoginServiceModel userLoginServiceModel = this.modelMapper.map(userLoginBindingModel, UserLoginServiceModel.class);
            req.setAttribute("userLoginServiceModel", userLoginServiceModel);
        }

        chain.doFilter(req, res);
    }
}
