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
        "username": "fe93bdd8-b01a-4487-8b91-be594faa82be",
        "password": "oa8IO4cVuROM"
      }
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
		service.setUsernameAndPassword("fe93bdd8-b01a-4487-8b91-be594faa82be", "oa8IO4cVuROM");
		TranslateOptions translateOptions = new TranslateOptions.Builder()
		  .text(text)
		  .source(Language.ENGLISH)
		  .target(Language.SPANISH)
		  .build();

		TranslationResult result = service.translate(translateOptions)
		  .execute();
		//TranslationResult translationResult = service.translate(text, Language.ENGLISH, Language.SPANISH).execute();
		return translationResult.getFirstTranslation();
	 }
}
