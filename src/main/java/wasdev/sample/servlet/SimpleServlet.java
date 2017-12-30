package wasdev.sample.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ibm.watson.developer_cloud.language_translator.v2.LanguageTranslator;
import com.ibm.watson.developer_cloud.language_translator.v2.model.Language;
import com.ibm.watson.developer_cloud.language_translator.v2.model.TranslationResult;

/**
 * Servlet implementation class SimpleServlet
 */
@WebServlet("/SimpleServlet")
public class SimpleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

/**
 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
 * este json de abajo se obtiene del servicio al que esta conectado esta aplicacion en bluemix
 "credentials": {
        "url": "https://gateway.watsonplatform.net/language-translator/api",
        "username": "803c8f1e-4e9a-44bf-885b-08d34f9f51b6",
        "password": "HhEW5N57r7gp"
      },
 */
     @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	     response.getWriter().print(translateToSpanish("This would be a much more complex message to translate"));
	     //response.getWriter().print("This would be a much more complex message to translate");
	 }

	 private String translateToSpanish(String text){
		LanguageTranslator service = new LanguageTranslator();
		service.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		service.setUsernameAndPassword("803c8f1e-4e9a-44bf-885b-08d34f9f51b6", "HhEW5N57r7gp");
		TranslationResult translationResult = service.translate(text, Language.ENGLISH, Language.SPANISH).execute();
		return translationResult.getFirstTranslation();
	 }
}
