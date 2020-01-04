package app.web.filters;

import app.domain.models.binding.TubeCreateBindingModel;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/tubes/create")
public class TubeCreateFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        if (req.getMethod().equalsIgnoreCase("post")) {
            TubeCreateBindingModel bindingModel = new TubeCreateBindingModel();
            bindingModel.setTitle(req.getParameter("title"));
            bindingModel.setDescription(req.getParameter("description"));
            bindingModel.setYoutubeLink(req.getParameter("youtubeLink"));
            bindingModel.setUploader(req.getParameter("uploader"));

            req.setAttribute("bindingModel", bindingModel);
        }

        chain.doFilter(req, res);
    }
}
