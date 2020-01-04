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

@WebServlet("/cats/all")
public class AllCatsServlet extends HttpServlet {
    private static final String ALL_CATS_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\all-cats.html";
    private static final String NO_CATS_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\no-cats.html";

    private final FileUtil fileUtil;

    @Inject
    public AllCatsServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String html;

        if (req.getSession().getAttribute("cats") == null) {
            html = this.fileUtil.readFile(NO_CATS_PATH);
        } else {
            StringBuilder builder = new StringBuilder();

            ((Map<String, Cat>) req.getSession().getAttribute("cats"))
                    .values()
                    .stream()
                    .map(cat -> String.format("<a href=\"/cats/profile?catName=%s\">%s</a><br>",
                            cat.getName(),
                            cat.getName()))
                    .forEach(builder::append);

            html = this.fileUtil.readFile(ALL_CATS_PATH)
                    .replace("{{allCats}}", builder.toString());
        }

        resp.getWriter().println(html);
    }
}
