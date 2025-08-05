package pss.game.models.familyQuestion.logica;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IFamiliaLogicaMultiple implements IFamiliaLogica {
	public boolean isRendered() {
		return true;
	}

	@Override
	public String validate(String statement) throws Exception {
		return findSyntaxErrors(statement);
	}

	// Patr�n para identificar el formato correcto de <variable:nombre> (sin espacios)
	private static final String TAG_PATTERN = "<[a-zA-Z0-9_]+:[a-zA-Z0-9_]+(?:\\|[a-zA-Z0-9_]+(?::[<>=!]+)?(?::[a-zA-Z0-9_]+:[a-zA-Z0-9_]+)?)?>";

//M�todo para buscar errores de sintaxis y generar mensajes de advertencia
	public static String findSyntaxErrors(String formula) {
	   // Patr�n para encontrar todos los tags en la f�rmula
	   Pattern invalidPattern = Pattern.compile("<.*?>");
	   Matcher invalidMatcher = invalidPattern.matcher(formula);
	
	   StringBuilder errors = new StringBuilder();
	
	   while (invalidMatcher.find()) {
	       String tag = invalidMatcher.group();
	       // Validar si la estructura encontrada cumple con el formato correcto
	       if (!tag.matches(TAG_PATTERN)) {
	           errors.append("Invalid tag found: ").append(tag).append("\n");
	       }
	   }
	
	   // Si no hay errores, devolver un mensaje de �xito
	   if (errors.length() == 0) {
	       return "";
	   }
	
	   return errors.toString();
	}
}
