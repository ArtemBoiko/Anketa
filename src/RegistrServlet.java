import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
@WebServlet(name = "RegServlet",urlPatterns = "/reg")
public class RegistrServlet extends HttpServlet {
    static final String HEADER = "<html>" +
            "<head><title>Registration</title></head>" +
            "<body>";
    static final String FOOTER = "</body></html>";

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String body = "";
        PrintWriter pw = resp.getWriter();
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        if (name.isEmpty() || surname.isEmpty()) {
            body += "<h1>Enter your name and surname!</h1>";
            pw.println(HEADER + body + FOOTER);
            pw.close();
        } else {
            body += name + " " + surname + "<br>";
        }
        String quest = req.getParameter("question");
        body += "Your answer = " + quest + "<br>";
        HttpSession hs = req.getSession();

        Statistic st = (Statistic) hs.getAttribute("stat");

        if (st == null) {
            st = Statistic.getInstance();
            hs.setAttribute("stat", st);
        }

        String voiceOne = req.getParameter("question");
        st.addVoice(voiceOne);
        String answerOne = "Statistic "+"<br>"+st.toString();
        pw.println(HEADER + body + answerOne + FOOTER);
        pw.close();
    }
}
