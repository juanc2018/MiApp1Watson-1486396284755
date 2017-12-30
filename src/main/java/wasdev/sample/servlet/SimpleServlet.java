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
 {
  "url": "https://gateway.watsonplatform.net/language-translator/api",
  "username": "8c1026f8-f3b0-4bba-9e5f-6fc7a3c22f91",
  "password": "sVP00531nzbJ"
 }
 */
     @Override
	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 response.setContentType("text/html");
	     response.getWriter().print(translateToSpanish("This would be a much more complex message to translate"));
	 }

	 private String translateToSpanish(String text){
		LanguageTranslator service = new LanguageTranslator();
		service.setEndPoint("https://gateway.watsonplatform.net/language-translator/api");
		service.setUsernameAndPassword("8c1026f8-f3b0-4bba-9e5f-6fc7a3c22f91", "sVP00531nzbJ");
		TranslationResult translationResult = service.translate(text, Language.ENGLISH, Language.SPANISH).execute();
		return translationResult.getFirstTranslation();
	 }
}
