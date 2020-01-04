package fdmc.web.servlets;

import fdmc.util.FileUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/")
public class HomeServlet extends HttpServlet {
    private static final String FILE_PATH =
            "C:\\Users\\User\\OneDrive\\Desktop\\java programs\\tomEE\\src\\main\\resources\\view\\home.html";

    private final FileUtil fileUtil;

    @Inject
    public HomeServlet(FileUtil fileUtil) {
        this.fileUtil = fileUtil;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String html = this.fileUtil.readFile(FILE_PATH);
        resp.getWriter().println(html);
    }
}
