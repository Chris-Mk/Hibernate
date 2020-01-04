package fdmc.web.servlets;

import fdmc.domain.entities.Cat;
import fdmc.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/cats/profile")
public class CatProfileServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\cat-profile.html";
    private static final String NON_EXISTENCE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\cat-nonexistence.html";

    private final FileUtil fileUtil;

    @Inject
    public CatProfileServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cat cat = ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                .get(req.getQueryString().split("=")[1]);

        String html;
        if (cat == null) {
            html = this.fileUtil.readFile(NON_EXISTENCE_PATH)
                    .replace("{{catName}}", req.getQueryString().split("=")[1]);
        } else {
            html = this.fileUtil.readFile(FILE_PATH)
                    .replace("{{catName}}", cat.getName())
                    .replace("{{catBreed}}", cat.getBreed())
                    .replace("{{catColor}}", cat.getColor())
                    .replace("{{numberOfLegs}}", String.valueOf(cat.getNumberOfLegs()));
        }

        resp.getWriter().println(html);
    }
}
