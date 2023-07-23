import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the user's input from the request parameter
        String word = request.getParameter("word");

        // Perform dictionary lookup operations and retrieve the necessary information
        List<String> meaning = DictionaryService.getMeanings(word);
        List<String> synonyms = DictionaryService.getSynonyms(word);
        List<String> antonyms = DictionaryService.getAntonyms(word);
        List<String> examples = DictionaryService.getExamples(word);

        // Forward the results to a JSP page for display
        request.setAttribute("word", word);
        request.setAttribute("meaning", meaning);
        request.setAttribute("synonyms", synonyms);
        request.setAttribute("antonyms", antonyms);
        request.setAttribute("examples", examples);
        request.getRequestDispatcher("result.jsp").forward(request, response);
    }
}