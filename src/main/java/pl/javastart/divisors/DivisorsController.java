package pl.javastart.divisors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;
import java.util.stream.Stream;

@WebServlet("/divisors")
public class AllDivisorsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        setContentType(response);
        sendHtmlResponse(request, response);
    }

    private void sendHtmlResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Divisors divisors = new Divisors();
        String n = request.getParameter("n");
        Stream<Integer> allDivisors = divisors.findAllDivisors(Integer.parseInt(n));
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.printf("<h1>Podzielniki liczby %s</h1>", n);
        writer.println("<ul>");
        allDivisors.forEach(divisor -> writer.printf("<li>%s</li>", divisor));
        writer.println("</ul>");
        writer.println("</body>");
        writer.println("</html>");
    }

    private void setContentType(HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
    }
}
