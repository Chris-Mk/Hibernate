package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@WebServlet("/cats/create")
public class CatCreateServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\cat-create.html";

    private final FileUtil fileUtil;

    @Inject
    public CatCreateServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cat cat = new Cat.Builder()
                .name(req.getParameter("name"))
                .breed(req.getParameter("breed"))
                .color(req.getParameter("color"))
                .numberOfLegs(Integer.parseInt(req.getParameter("numberOfLegs")))
                .build();

        if (req.getSession().getAttribute("cats") == null) {
            req.getSession().setAttribute("cats", new LinkedHashMap<>());
        }

        ((Map<String, Cat>) req.getSession().getAttribute("cats")).putIfAbsent(cat.getName(), cat);
        resp.sendRedirect("/cats/profile?catName=" + cat.getName());
    }
}
