package pl.javastart.divisors;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.stream.Stream;

@WebServlet("/divisors")
public class DivisorsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nParam = request.getParameter("n");
        if (nParam == null || nParam.isEmpty()) {
            response.sendError(400, "Brak parametru N");
        } else {
            int number = Integer.parseInt(nParam);
            setContentType(response);
            sendHtmlResponse(request, response, number);
        }
    }

    private void sendHtmlResponse(HttpServletRequest request, HttpServletResponse response, int number) throws IOException {
        DivisorsFinder1 divisorsFinder = new DivisorsFinder1();
        Stream<Integer> allDivisors = divisorsFinder.findAllDivisors(number);
        PrintWriter writer = response.getWriter();
        writer.println("<html>");
        writer.println("<body>");
        writer.printf("<h1>Podzielniki liczby %s</h1>", number);
        writer.println("<ul>");
        if (number == 0)
            writer.println("<li>Liczba 0 ma nieskończenie wiele dzielników</li>");
        else {
            allDivisors.forEach(divisor -> writer.printf("<li>%s</li>", divisor));
        }
        writer.println("</ul>");
        writer.println("</body>");
        writer.println("</html>");
    }

    private void setContentType(HttpServletResponse response) {
        response.setContentType("text/html");
        response.setCharacterEncoding("UTF-8");
    }
}
