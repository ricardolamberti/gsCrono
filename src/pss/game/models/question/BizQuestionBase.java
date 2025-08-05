package pss.game.models.question;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.graalvm.polyglot.Context;

import pss.core.services.fields.JHtml;
import pss.core.services.fields.JLong;
import pss.core.services.fields.JString;
import pss.core.services.records.JRecord;
import pss.core.services.records.JRecords;
import pss.core.tools.JPair;
import pss.core.tools.JTools;
import pss.core.tools.collections.JCollectionFactory;
import pss.core.tools.collections.JIterator;
import pss.core.tools.collections.JMap;
import pss.core.win.JWins;
import pss.game.models.difficulty.GuiDifficulties;
import pss.game.models.familyQuestion.BizFamilyQuestionBase;
import pss.game.models.familyQuestion.GuiFamilyQuestions;
import pss.game.models.game.GuiGames;
import pss.game.models.questionOption.BizQuestionOption;
import pss.game.models.questionOption.BizQuestionOptionBase;
import pss.game.models.questionType.BizQuestionType;
import pss.game.models.questionType.GuiQuestionTypes;
import pss.game.models.variable.BizVariable;
import pss.game.models.variableOption.BizVariableOptionBase;

public class BizQuestionBase extends JRecord {

	protected JString pCompany = new JString();
	protected JLong pId = new JLong();
	protected JLong pQuestionTypeid = new JLong();
	protected JString pStatement = new JString();

	protected JLong pGameId = new JLong();
	protected JLong pDifficultyId = new JLong();
	protected JLong pFamilyId = new JLong();

	protected JString pQuestionTypeName = new JString();
	protected JString pGameName = new JString();
	protected JString pDifficultyName = new JString();
	protected JString pFamilyName = new JString();
	protected JString pError = new JString();
	protected JString pStyleImage = new JString();
	protected JString pImage = new JString();
	protected JString pImage2 = new JString();
	protected JString pNumImage = new JString();
	protected JString pNumImage2 = new JString();

	protected JHtml pExample = new JHtml();

	
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Getter & Setters methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public void setId(long zValue) throws Exception {
		pId.setValue(zValue);
	}

	public long getId() throws Exception {
		return pId.getValue();
	}

	public boolean isNullId() throws Exception {
		return pId.isNull();
	}

	public void setNullToId() throws Exception {
		pId.setNull();
	}

	public void setQuestionTypeid(long zValue) throws Exception {
		pQuestionTypeid.setValue(zValue);
	}

	public long getQuestionTypeid() throws Exception {
		return pQuestionTypeid.getValue();
	}

	public boolean isNullQuestionTypeid() throws Exception {
		return pQuestionTypeid.isNull();
	}

	public void setNullToQuestionTypeid() throws Exception {
		pQuestionTypeid.setNull();
	}
	

	public void setStatement(String zValue) throws Exception {
		pStatement.setValue(zValue);
	}

	public String getStatement() throws Exception {
		return pStatement.getValue();
	}

	public boolean isNullStatement() throws Exception {
		return pStatement.isNull();
	}

	public void setNullToStatement() throws Exception {
		pStatement.setNull();
	}

	public void setGameId(long zValue) throws Exception {
		pGameId.setValue(zValue);
	}

	public long getGameId() throws Exception {
		return pGameId.getValue();
	}

	public boolean isNullGameId() throws Exception {
		return pGameId.isNull();
	}

	public void setNullToGameId() throws Exception {
		pGameId.setNull();
	}

	public void setDifficultyId(long zValue) throws Exception {
		pDifficultyId.setValue(zValue);
	}

	public long getDifficultyId() throws Exception {
		return pDifficultyId.getValue();
	}

	public boolean isNullDifficultyId() throws Exception {
		return pDifficultyId.isNull();
	}

	public void setNullToDifficultyId() throws Exception {
		pDifficultyId.setNull();
	}

	public void setCompany(String zValue) throws Exception {
		pCompany.setValue(zValue);
	}

	public String getCompany() throws Exception {
		return pCompany.getValue();
	}

	public boolean isNullCompany() throws Exception {
		return pCompany.isNull();
	}

	public void setNullToCompany() throws Exception {
		pCompany.setNull();
	}

	public void setFamilyId(long zValue) throws Exception {
		pFamilyId.setValue(zValue);
	}

	public long getFamilyId() throws Exception {
		return pFamilyId.getValue();
	}

	public boolean isNullFamilyId() throws Exception {
		return pFamilyId.isNull();
	}

	public void setNullToFamilyId() throws Exception {
		pFamilyId.setNull();
	}

	public void setError(String zValue) throws Exception {
		pError.setValue(zValue);
	}

	public String getError() throws Exception {
		return pError.getValue();
	}

	public void setStyleImage(String zValue) throws Exception {
		pStyleImage.setValue(zValue);
	}

	public String getStyleImage() throws Exception {
		return pStyleImage.getValue();
	}

	public void setImage(String zValue) throws Exception {
		pImage.setValue(zValue);
	}

	public String getImage() throws Exception {
		return pImage.getValue();
	}
	public void setImage2(String zValue) throws Exception {
		pImage2.setValue(zValue);
	}

	public String getImage2() throws Exception {
		return pImage2.getValue();
	}
	public void setNumImage2(long zValue) throws Exception {
		pNumImage2.setValue(zValue);
	}

	public String getNumImage2() throws Exception {
		return pNumImage2.getValue();
	}
	public void setNumImage1(long zValue) throws Exception {
		pNumImage.setValue(zValue);
	}

	public String getNumImage1() throws Exception {
		return pNumImage.getValue();
	}

	
	public static final String STYLE_SUMA = "STYLE_SUMA";
  public static final String STYLE_RESTA = "STYLE_RESTA";
  public static final String STYLE_MULTI = "STYLE_MULTI";
  public static final String STYLE_DIV = "STYLE_DIV";
  public static final String STYLE_SIMPLE = "STYLE_SIMPLE";
  public static final String STYLE_UNION = "STYLE_UNION";
  


	public static JMap<String, String> getTiposConocidos(JMap<String, String> cc) {
		cc.addElement(BizQuestionBase.STYLE_SUMA, "Graficos suma");
		cc.addElement(BizQuestionBase.STYLE_RESTA, "Graficos resta");
		cc.addElement(BizQuestionBase.STYLE_MULTI, "Graficos Multiplicacion");
		cc.addElement(BizQuestionBase.STYLE_DIV, "Graficos Division");
		cc.addElement(BizQuestionBase.STYLE_SIMPLE, "Preguntas con op.horizontales");
		cc.addElement(BizQuestionBase.STYLE_UNION, "Union de opciones");
		return cc;
	}

	static JMap<String, String> types;

	public static JMap<String, String> getEstilosImagenes() throws Exception {
		if (types != null)
			return types;
		JMap<String, String> maps = JCollectionFactory.createMap();
		return types = getTiposConocidos(maps);
	}


	/**
	 * Constructor de la Clase
	 */
	public BizQuestionBase() throws Exception {
	}

	public void createProperties() throws Exception {
		addItem("id", pId);
		addItem("game_id", pGameId);
		addItem("difficulty_id", pDifficultyId);
		addItem("family_id", pFamilyId);
		addItem("question_type_id", pQuestionTypeid);
		addItem("game_name", pGameName);
		addItem("difficulty_name", pDifficultyName);
		addItem("family_name", pFamilyName);
		addItem("question_type_name", pQuestionTypeName);
		addItem("statement", pStatement);
		addItem("company", pCompany);
		addItem("example", pExample);
		addItem("style_image", pStyleImage);
		addItem("num_image", pNumImage);
		addItem("image", pImage);
		addItem("num_image2", pNumImage2);
		addItem("image2", pImage2);
		addItem("error", pError);
	}

	/**
	 * Adds the fixed object properties
	 */
	public void createFixedProperties() throws Exception {
		addFixedItem(KEY, "id", "id", false, false, 64);
		addFixedItem(FIELD, "game_id", "Juego", true, false, 64);
		addFixedItem(FIELD, "difficulty_id", "Dificultad", true, false, 64);
		addFixedItem(FIELD, "question_type_id", "Tipo pregunta", true, false, 64);
		addFixedItem(FIELD, "family_id", "Familia pregunta", true, true, 64);
		addFixedItem(FOREIGN, "game_name", "Juego", true, false, 250);
		addFixedItem(FOREIGN, "difficulty_name", "Dificultad", true, false, 250);
		addFixedItem(FOREIGN, "question_type_name", "Tipo pregunta", true, false, 250);
		addFixedItem(FOREIGN, "family_name", "Familia pregunta", true, true, 250);
		addFixedItem(FIELD, "statement", "Sentencia", true, true, 1000);
		addFixedItem(FIELD, "company", "company", true, true, 15);
		addFixedItem(FIELD, "style_image", "Style_image", true, false, 50);
		addFixedItem(FIELD, "num_image", "num image", true, false, 50);
		addFixedItem(FIELD, "image", "image", true, false, 250);
		addFixedItem(FIELD, "num_image2", "num image2", true, false, 50);
		addFixedItem(FIELD, "image2", "image2", true, false, 250);
		addFixedItem(VIRTUAL, "example", "example", true, false, 4000);
		addFixedItem(VIRTUAL, "error", "error", true, false, 4000);
	}

	@Override
	public void createControlProperties() throws Exception {
		this.addControlsProperty("question_type_id", createControlCombo(GuiQuestionTypes.class, "id", new JPair<String, String>("company", "company")));
		this.addControlsProperty("game_id", createControlCombo(GuiGames.class, "id_game", new JPair<String, String>("company", "company")));
		this.addControlsProperty("difficulty_id", createControlCombo(GuiDifficulties.class, "id", new JPair<String, String>("company", "company")));
		this.addControlsProperty("family_id", createControlCombo(GuiFamilyQuestions.class, "id", new JPair<String, String>("company", "company")));
		this.addControlsProperty("style_image", createControlCombo(JWins.createVirtualWinsFromMap(getEstilosImagenes()),null, null) );
  	super.createControlProperties();
	}
  /**
	 * Returns the table name
	 */
	public String GetTable() {
		return "gms_question";
	}

	@Override
	public String GetTableTemporal() throws Exception {
		String sql = "";
		sql += "select gms_question.*, gms_family.name as family_name, gms_difficulty.name as difficulty_name, gms_question_type.name as question_type_name ,gms_game.game as game_name \n" + "from gms_question \n" + "join gms_difficulty on gms_difficulty.id=gms_question.difficulty_id\n" + "join gms_family on gms_family.id=gms_question.family_id\n" + "join gms_game on gms_game.id_game=gms_question.game_id\n " + "left join gms_question_type on gms_question_type.id=gms_question.question_type_id";

		return "(" + sql + ") as " + GetTable();
	}

	@Override
	protected boolean loadForeignFields() throws Exception {
		return true;
	}
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// Functionality methods
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void processUpdate() throws Exception {
		updateRecord();
	}

	/**
	 * Default read() method
	 */
	public boolean read(long zId) throws Exception {
		addFilter("id", zId);
		return read();
	}

	public void processGenerateQuestion() throws Exception {
		String output = "";
		for (int i = 0; i < 5; i++) {
			clearCacheVariable();
			output += processGenerateOneQuestion(i + 1);
		}
		pExample.setValue(output);
	}

	transient BizFamilyQuestionBase objFamily;

	public BizFamilyQuestionBase getObjFamily() throws Exception {
		if (isNullFamilyId())
			return null;
		if (objFamily != null)
			return objFamily;
		BizFamilyQuestionBase fam = new BizFamilyQuestionBase();
		fam.dontThrowException(true);
		if (!fam.read(getFamilyId()))
			return null;
		return objFamily = fam;
	}

	public boolean isRendered() throws Exception {
		if (getObjFamily() == null)
			return false;
		return getObjFamily().getObjLogica().isRendered();
	}

	public class ResultParser {
		public ResultParser() {
		}

		public String statment;
		public String image1;
		public String image2;
		public String styleImage;
		public long numImage1;
		public long numImage2;
	}

	public ResultParser getRenderedStatement() throws Exception {
		return parseFormulaWithConditions(getStatement(), true, true);
	}

	public ResultParser getRenderedOptionStatement(BizQuestionOptionBase option) throws Exception {
		ResultParser result = parseFormulaWithConditions(option.getAnswerFormula(), false, true);
		return result;
	}
	public ResultParser getRenderedOptionExtraStatement(BizQuestionOptionBase option) throws Exception {
		ResultParser result = parseFormulaWithConditions(option.getExtraDatos(), false, true);
		return result;
	}

	public String processGenerateOneQuestion(int questionNumber) throws Exception {
		StringBuilder output = new StringBuilder();

		String error = (this.getObjFamily() == null) ? "" : (this.getObjFamily().getObjLogica().validate(this.getStatement()));

		// Estilo para el contorno redondeado y el fondo suave
		String style = "style=\"border: 2px solid #A9A9A9; border-radius: 10px; background-color: #F0F8FF; padding: 15px; margin-bottom: 10px;\"";
		ResultParser parser = getRenderedStatement();
		output.append("<div ").append(style).append(">");
		output.append("<b>Pregunta N: ").append(questionNumber).append("</b><br>");
		output.append("<b>").append(parser.statment).append("</b><br>");

		JRecords<BizQuestionOption> recs = new JRecords<>(BizQuestionOption.class);
		recs.addFilter("question_id", getId());
		JIterator<BizQuestionOption> it = recs.getStaticIterator();

		// Iterar sobre las opciones de la pregunta
		while (it.hasMoreElements()) {
			BizQuestionOption option = it.nextElement();
			System.out.println(option.getAnswerFormula());
			String formula = parseFormulaWithConditions(option.getAnswerFormula(), false, false).statment;
			String result = "" + parseFormulaWithConditions(option.getAnswerFormula(), false, true).statment;
			String color = option.getIscorrect() ? "GREEN" : "RED";
			output.append("<div style=\"color:").append(color).append("\">").append(result).append("<I> (").append(formula).append(")</I>").append("</div>");
		}
		output.append("<div ").append(">");
		output.append("Estilo ").append(parser.styleImage).append("<br>");
		output.append("Nro imagen1 ").append(parser.numImage1).append(" imagen: ").append(parser.image1).append("<br>");
		output.append("Nro imagen2 ").append(parser.numImage2).append(" imagen: ").append(parser.image2).append("<br>");
		output.append("</div>"); // Cerrar el div con estilo
		setError(error);
		output.append("</div>"); // Cerrar el div con estilo
		return output.toString();
	}

	Map<String, BizVariableOptionBase> cacheVariables;

	public void clearCacheVariable() {
		cacheVariables = null;
	}

	public Map<String, BizVariableOptionBase> getCacheVariable() {
		if (cacheVariables == null) {
			cacheVariables = new HashMap<String, BizVariableOptionBase>();
		}
		return cacheVariables;
	}

	public BizVariableOptionBase findVariable(String variableClass, String variableName, String operator, String valueComp) throws Exception {
		String cacheKey = variableClass + ":" + variableName;

		if (getCacheVariable().get(cacheKey) != null)
			return getCacheVariable().get(cacheKey);

		BizVariable var = new BizVariable();
		BizVariableOptionBase value = null;
		var.dontThrowException(true);
		if (var.readByName(variableClass)) {
			value = var.getOneValue(operator, valueComp);
		} else {
			value = new BizVariableOptionBase("Error: Variable no encontrada " + variableClass);
		}

		getCacheVariable().put(cacheKey, value);
		return value;
	}

	public BizVariableOptionBase findVariable(String variableClass, String variableName) throws Exception {
		String cacheKey = variableClass + ":" + variableName;

		if (getCacheVariable().get(cacheKey) != null)
			return getCacheVariable().get(cacheKey);

		BizVariable var = new BizVariable();
		BizVariableOptionBase value = null;
		var.dontThrowException(true);
		if (var.readByName(variableClass)) {
			value = var.getOneValue();
		} else {
			value = new BizVariableOptionBase("Error: Variable no encontrada " + variableClass);
		}

		getCacheVariable().put(cacheKey, value);
		return value;
	}

	public ResultParser parseFormulaWithConditions(String formula, boolean question, boolean evalScript) throws Exception {
		Pattern pattern = Pattern.compile("<(.*?)>");
		Matcher matcher = pattern.matcher(formula);
		ResultParser parser = new ResultParser();
		StringBuffer result = new StringBuffer();
		int lastImage = 0;
		parser.styleImage = getStyleImage();
		parser.image1 = parseFormula(getImage(),true);
		parser.image2 = parseFormula(getImage2(),true);
		parser.numImage1=getNumImage1().equals("")?0:Long.parseLong(parseFormula(getNumImage1(),true));
		parser.numImage2=getNumImage2().equals("")?0:Long.parseLong(parseFormula(getNumImage2(),true));
		
		while (matcher.find()) {
			String variableSyntax = matcher.group(1); // Captura lo que hay dentro de <>
			String[] parts = variableSyntax.split("\\|");

			// Extraer la variable y el nombre
			String[] variableParts = parts[0].split(":");
			String variable = variableParts[0]; // Ejemplo: "n1"
			String name = variableParts.length > 1 ? variableParts[1] : "";

			// Si hay una condición
			if (parts.length > 1 && parts[1].startsWith("force:")) {
				String condition = parts[1].substring(6); // Captura ">:n2"
				String[] conditionParts = condition.split(":");
				if (conditionParts.length != 3) {
					matcher.appendReplacement(result, "Error formato (" + variable + ":" + name + ")"); // Usar valor si cumple la condición
					continue;
				}
				String operator = conditionParts[0]; // Ejemplo: ">"
				String comparisonVariable = conditionParts[1]; // Ejemplo: "n2"
				String comparisonName = conditionParts[2]; // Ejemplo: "n2"

				BizVariableOptionBase comparisonValue = findVariable(comparisonVariable, comparisonName);
				// Obtener los valores de las variables
				BizVariableOptionBase variableValue = findVariable(variable, name, operator, comparisonValue.getValue(question, false));
				matcher.appendReplacement(result, variableValue.getValue(question, false)); // Usar valor si cumple la condición
				String image = variableValue.getImagen();
				if (image.startsWith("STYLE_")) {
					parser.styleImage = image;
				} 
			} else if (parts.length > 1 && parts[1].startsWith("pl:")) {
				String condition = parts[1].substring(3); // Captura "n2"
				String[] conditionParts = condition.split(":");
				if (conditionParts.length != 2) {
					matcher.appendReplacement(result, "Error formato (" + variable + ":" + name + ")"); // Usar valor si cumple la condición
					continue;
				}
				String comparisonVariable = conditionParts[0]; // Ejemplo: ">"
				String comparisonName = conditionParts[1]; // Ejemplo: "n2"

				BizVariableOptionBase comparisonValue = findVariable(comparisonVariable, comparisonName);
				// Obtener los valores de las variables
				BizVariableOptionBase variableValue = findVariable(variable, name);

				double compValue = Double.parseDouble(comparisonValue.getValue(question, false));
				boolean plural = compValue != 1.0;

				matcher.appendReplacement(result, variableValue.getValue(question, plural)); // Usar valor si cumple la condición

				String image = variableValue.getImagen();
				if (image.startsWith("STYLE_")) {
					parser.styleImage = image;
				} else if (image != null && lastImage == 0) {
					parser.numImage1 = Long.parseLong(comparisonValue.getValue(false, false));
					parser.image1 = image;
					lastImage++;
				} else if (image != null && lastImage == 1) {
					parser.numImage2 = Long.parseLong(comparisonValue.getValue(false, false));
					parser.image2 = image;
					lastImage++;
				}

			} else {
				// Si no hay condición, proceder como antes
				BizVariableOptionBase variableValue = findVariable(variable, name);
				matcher.appendReplacement(result, variableValue.getValue(question, false)); // Reemplaza la variable en la fórmula
				String image = variableValue.getImagen();
				if (image.startsWith("STYLE_")) {
					parser.styleImage = image;
				}
				
			}
		}
		matcher.appendTail(result);

		if (evalScript)
			result = new StringBuffer(evaluateJavaScriptExpressions(result.toString()));

		parser.statment = result.toString();
		return parser;
	}

// Método para evaluar expresiones JavaScript dentro de corchetes {}
	private String evaluateJavaScriptExpressions(String formula) throws ScriptException {
		Pattern pattern = Pattern.compile("\\{(.*?)\\}"); // Buscar expresiones entre {}
		Matcher matcher = pattern.matcher(formula);
		String javaVendor = System.getProperty("java.vendor");
		 Context context=null;
		 ScriptEngineManager manager=null;
		 ScriptEngine engine=null;
		 
		if (!javaVendor.toLowerCase().contains("oracle corporation")) {
	     context = Context.create();
		} else {
			manager = new ScriptEngineManager();
			engine = manager.getEngineByName("javascript");
		}

		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			String expression = matcher.group(1); // Captura lo que hay dentro de los {}
			Object evalResult;
			if (engine==null) {
	    	evalResult= context.eval("js", expression);
			}  else {
			  evalResult = engine.eval(expression); // Evaluar la expresión
			}

			
			matcher.appendReplacement(result, evalResult.toString()); // Reemplazar la expresión con el resultado
			if (JTools.isNumber(result.toString()) && !JTools.isIntegerNumber(result.toString()))
				result = new StringBuffer("" + JTools.rdCeiling(Float.parseFloat(result.toString()), 2));
			System.out.println("EVAL: " + expression + " = " + result);
		}
		matcher.appendTail(result);

		return result.toString();
	}

	public boolean evaluateCondition(String variableValue, String operator, String comparisonValue) throws Exception {
		double varValue = Double.parseDouble(variableValue);
		double compValue = Double.parseDouble(comparisonValue);

		switch (operator) {
		case "<":
			return varValue < compValue;
		case ">":
			return varValue > compValue;
		case "==":
			return varValue == compValue;
		case "<=":
			return varValue <= compValue;
		case ">=":
			return varValue >= compValue;
		default:
			throw new IllegalArgumentException("Operador desconocido: " + operator);
		}
	}

	public String parseFormula(String formula, boolean question) throws Exception {
		Pattern pattern = Pattern.compile("<(\\w+):(\\w+)>");
		Matcher matcher = pattern.matcher(formula);

		StringBuffer result = new StringBuffer();

		while (matcher.find()) {
			String variableClass = matcher.group(1);
			String variableName = matcher.group(2);

			BizVariableOptionBase variableValue = findVariable(variableClass, variableName);

			matcher.appendReplacement(result, Matcher.quoteReplacement(variableValue.getValue(false, false)));
		}
		matcher.appendTail(result);

		return result.toString();
	}

	public static Object evaluateMathExpression(String expression) throws ScriptException {
		try {
			String javaVendor = System.getProperty("java.vendor");
			 Context context=null;
			 ScriptEngineManager manager=null;
			 ScriptEngine engine=null;
			 
			 if (!javaVendor.toLowerCase().contains("oracle corporation")) {
				 context = Context.create();
			} else {
				manager = new ScriptEngineManager();
				engine = manager.getEngineByName("javascript");
			}
			
			Object evalResult;
			if (engine==null) {
	    	evalResult= context.eval("js", expression);
			}  else {
			  evalResult = engine.eval(expression); // Evaluar la expresión
			}

			return evalResult;
		} catch (Exception e) {
			return expression;
		}
	}
}
