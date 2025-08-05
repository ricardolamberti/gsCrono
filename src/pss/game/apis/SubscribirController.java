package pss.game.apis;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.f1j.mvc.q;

import edu.emory.mathcs.backport.java.util.Collections;
import pss.common.customList.config.relation.JRelations;
import pss.common.restJason.JServiceApiProcess;
import pss.core.services.records.JRecords;
import pss.core.tools.JTools;
import pss.core.tools.PssLogger;
import pss.core.tools.collections.JIterator;
import pss.game.models.avatar.BizAvatar;
import pss.game.models.game.BizGame;
import pss.game.models.gameplayer.BizGamePlayer;
import pss.game.models.gameplayer.BizGamePlayerBase;
import pss.game.models.player.BizPlayer;
import pss.game.models.playerAnswers.BizPlayerAnswerBase;
import pss.game.models.preGeneratedQuestionOption.BizpreGeneratedQuestionOptionBase;
import pss.game.models.preGeneratedQuestions.BizPreGeneratedQuestionBase;
import pss.game.models.question.BizQuestion;
import pss.game.models.questionType.BizQuestionType;

@Path("/api")
public class SubscribirController {

	private static final String AES_KEY = "F921CDGj39FMDjx8hmMqY0ydEQqdJJnj";
	private static final String AES_IV = "1zg8R3fKEB32l3KQ";

	@POST
	@Path("/subscribir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response subscribir(SubscribirRequest request) {

		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();
		try {

			resolver.openApp("GameServer");

			response = internalSubscribe(request);

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Suscripción fallada :" + e.getMessage();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
			resolver.closeApp();
		}

		return response;
	}

	private Response internalSubscribe(SubscribirRequest request) throws Exception {

		BizPlayer player = new BizPlayer();
		player.dontThrowException(true);

		if (player.readByName(request.getNombre().toUpperCase())) {
			String message = "Usuario existente :" + request.getNombre();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, player.getName());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		BizGame game = new BizGame();
		game.dontThrowException(true);
		if (!game.read(request.getIdGame())) {
			String message = "Juego inexistente :" + request.getIdGame();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, player.getName());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}
		BizAvatar avatar = new BizAvatar();
		avatar.dontThrowException(true);
		if (!avatar.read(request.getAvatar())) {
			ResponseMessage responseMessage = new ResponseMessage("ERROR", "Avatar inexistente :" + request.getAvatar(), player.getName());
      return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		player.setName(request.getNombre().toUpperCase());
		player.setPassword(request.getClave());
		player.setCompany(game.getCompany());
		player.setIdavatar(request.getAvatar());
		player.setBirthday(request.getFecha());
		player.execProcessSubscribir(request.getIdGame());

		String message = "Suscripción exitosa :" + request.getNombre();
		ResponseMessage responseMessage = new ResponseMessage("OK", "", player.getName());

		// Devolver la respuesta como JSON
		return Response.ok(responseMessage).build();
	}

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response login(LoginRequest request) {

		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();
		try {

			resolver.openApp("GameServer");

			response = internalLogin(request);

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Login fallada :" + e.getMessage();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, "");
			response =  Response.status(Response.Status.UNAUTHORIZED).entity(responseMessage).build();
			resolver.closeApp();
		}

		return response;
	}

	private Response internalLogin(LoginRequest request) throws Exception {

		BizPlayer player = new BizPlayer();
		player.dontThrowException(true);

		if (!player.readByName(request.getNombre().toUpperCase())) {
			String message = "Usuario inexistente :" + request.getNombre();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, player.getName());
      return Response.status(Response.Status.UNAUTHORIZED).entity(responseMessage).build();
		}

		BizGame game = new BizGame();
		game.dontThrowException(true);
		if (!game.read(request.getIdGame())) {
			String message = "Juego inexistente :" + request.getIdGame();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, player.getName());
      return Response.status(Response.Status.BAD_REQUEST).entity(responseMessage).build();
		}

		if (!JTools.desencriptarAES(player.getPassword(), AES_KEY, AES_IV).equals(JTools.desencriptarAES(request.getClave(), AES_KEY, AES_IV))) {
			String message = "Clave erronea";
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, "");
      return Response.status(Response.Status.UNAUTHORIZED).entity(responseMessage).build();
		}

		// registrar login
		String message = "Login exitoso :" + request.getNombre();
		ResponseMessage responseMessage = new ResponseMessage("OK", "", player.getName());

		// Devolver la respuesta como JSON
		return Response.ok(responseMessage).build();
	}

	@POST
	@Path("/getInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getInfo(GetInfoRequest request) {

		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();
		try {

			resolver.openApp("GameServer");
			response = internalGetInfo(request);

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Info fallada :" + e.getMessage();
			ResponseGetinfo responseMessage = new ResponseGetinfo("ERROR", message, 0, "", 0, 0, 0);
			response =  Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
			resolver.closeApp();
		}

		return response;
	}

	private Response internalGetInfo(GetInfoRequest request) throws Exception {

		BizPlayer player = new BizPlayer();
		player.dontThrowException(true);

		if (!player.readByName(request.getNombre().toUpperCase())) {
			String message = "Usuario inexistente :" + request.getNombre();
			ResponseGetinfo responseMessage = new ResponseGetinfo("ERROR", message, 0, "", 0, 0, 0);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		BizGamePlayer gamePlayer = new BizGamePlayer();
		gamePlayer.dontThrowException(true);

		if (!gamePlayer.readByIdPlayerIdGame(player.getIdplayer(), request.getIdGame())) {
			String message = "subscripcion inexistente :" + player.getName();
			ResponseGetinfo responseMessage = new ResponseGetinfo("ERROR", message, 0, "", 0, 0, 0);
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		// registrar login
		String message = "getInfo exitoso :" + player.getName();
		ResponseGetinfo responseMessage = new ResponseGetinfo("OK", "", gamePlayer.getIdgameplayer(), player.getName(), gamePlayer.getPoints(), gamePlayer.getGems(), gamePlayer.getLevel());

		// Devolver la respuesta como JSON
		return Response.ok(responseMessage).build();
	}

	@POST
	@Path("/setInfo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response setInfo(SetInfoRequest request) {

		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();
		try {

			resolver.openApp("GameServer");

			response = internalSetInfo(request);

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);

			String message = "Info fallada :" + e.getMessage();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
			resolver.closeApp();
		}

		return response;
	}

	private Response internalSetInfo(SetInfoRequest request) throws Exception {

		BizPlayer player = new BizPlayer();
		player.dontThrowException(true);

		if (!player.readByName(request.getNombre().toUpperCase())) {
			String message = "Usuario inexistente :" + request.getNombre();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, "");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		BizGamePlayer gamePlayer = new BizGamePlayer();
		gamePlayer.dontThrowException(true);

		if (!gamePlayer.readByIdPlayerIdGame(player.getIdplayer(), request.getIdGame())) {
			String message = "subscripcion inexistente :" + player.getName();
			ResponseMessage responseMessage = new ResponseMessage("ERROR", message, "");
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(responseMessage).build();
		}

		gamePlayer.setPoints(request.getPoint());
		gamePlayer.setGems(request.getGems());
		gamePlayer.setLevel(request.getLevel());
		gamePlayer.execProcessUpdate();

		// registrar login
		String message = "setInfo exitoso :" + player.getName();
		ResponseMessage responseMessage = new ResponseMessage("OK", "", player.getName());

		// Devolver la respuesta como JSON
		return Response.ok(responseMessage).build();
	}

	@GET
	@Path("/getQuestion{idGamePlayer}/{questionTypeId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getGeneratedQuestions(@PathParam("idGamePlayer") long idGamePlayer, @PathParam("questionTypeId") String questionTypeId) {

		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();
		try {

			resolver.openApp("GameServer");

			List<QuestionDTO> questions = fetchGeneratedQuestions(idGamePlayer, questionTypeId);
			response = Response.ok(questions).build();

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Info question fallada :" + e.getMessage();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
			resolver.closeApp();
		}

		return response;
	}

	public void generarMoreQuestions(long idGamePlayer, String questionTypeId) throws Exception {
		BizGamePlayerBase gp = new BizGamePlayerBase();
		if (!gp.read(idGamePlayer))
			return;

		gp.execProcessGenerateQuestions(questionTypeId);
	}

	public List<QuestionDTO> fetchGeneratedQuestions(long idGamePlayer, String questionTypeId) throws Exception {
		String qId = questionTypeId;
		boolean withPacman=false;
		if (qId.endsWith("_WITHPACMAN")) {
			qId = qId.substring(qId.length()-"_WITHPACMAN".length());
			withPacman = true;
		}
		
		JRecords<BizPreGeneratedQuestionBase> recs = new JRecords<BizPreGeneratedQuestionBase>(BizPreGeneratedQuestionBase.class);
		recs.addJoin(JRelations.JOIN, "gms_question_type", "gms_question_type.id=gms_pre_generated_questions.question_type_id");
		recs.addFilter("gameplayer_id", idGamePlayer);
		recs.addFilter("gms_question_type", "type", qId,"=");
		recs.addFilter("used", "S", "<>");
		if (recs.selectCount() < 10)
			generarMoreQuestions(idGamePlayer, qId);

		JIterator<BizPreGeneratedQuestionBase> it = recs.getStaticIterator();
		List<QuestionDTO> out = new ArrayList<QuestionDTO>();
		while (it.hasMoreElements()) {
			BizPreGeneratedQuestionBase question = it.nextElement();
			if (withPacman && question.getStyleImage().equals(BizQuestion.STYLE_UNION)) continue;
			QuestionDTO questionOut = new QuestionDTO();
			questionOut.setRenderedQuestion(question.getRenderedQuestion());
			questionOut.setId(question.getId());
			questionOut.setImage1(question.getImage1());
			questionOut.setImage2(question.getImage2());
			questionOut.setStyleImage(question.getStyleImage());
			questionOut.setNumImage1(question.getNumImage1());
			questionOut.setNumImage2(question.getNumImage2());

			JRecords<BizpreGeneratedQuestionOptionBase> recsOpc = new JRecords<BizpreGeneratedQuestionOptionBase>(BizpreGeneratedQuestionOptionBase.class);
			recsOpc.addFilter("pre_question_id", question.getId());
			JIterator<BizpreGeneratedQuestionOptionBase> itOs = recsOpc.getStaticIterator();
			while (itOs.hasMoreElements()) {
				BizpreGeneratedQuestionOptionBase option = itOs.nextElement();
				
				questionOut.addOption(option.getId(), option.getAnswerFormula(),option.getExtraDatos(), option.getIscorrect());
			}
			Collections.shuffle(questionOut.getOptions());
			out.add(questionOut);

		}
		return out;
	}

	@GET
	@Path("/getQuestionType/{gameId}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getQuestionType(@PathParam("gameId") long gameId) {
		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();

		try {
			resolver.openApp("GameServer");

			List<QuestionTypeDTO> questionTypes = fetchQuestionTypesByGameId(gameId);

			response = Response.ok(questionTypes).build();
			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Error al obtener tipos de preguntas: " + e.getMessage();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
			resolver.closeApp();
		}

		return response;
	}

	private List<QuestionTypeDTO> fetchQuestionTypesByGameId(long gameId) throws Exception {
		List<QuestionTypeDTO> questionTypes = new ArrayList<>();

		JRecords<BizQuestionType> recs = new JRecords<>(BizQuestionType.class);
		recs.addFilter("game_id", gameId);
		JIterator<BizQuestionType> it = recs.getStaticIterator();

		while (it.hasMoreElements()) {
			BizQuestionType questionType = it.nextElement();

			QuestionTypeDTO dto = new QuestionTypeDTO();
			dto.setId(questionType.getId());
			dto.setDescription(questionType.getName());

			questionTypes.add(dto);
		}

		return questionTypes;
	}

	@POST
	@Path("/submit")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response submitPlayerAnswer(PlayerAnswerDTO answer) {
		Response response;
		JServiceApiProcess resolver = new JServiceApiProcess();

		try {
			resolver.openApp("GameServer");

			response = addPlayerAnswer(answer);

			resolver.closeApp();
			return response;

		} catch (Exception e) {
			PssLogger.logError(e);
			String message = "Error al obtener tipos de preguntas: " + e.getMessage();
			response = Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(message).build();
			resolver.closeApp();
		}

		return response;
	}

	public Response addPlayerAnswer(PlayerAnswerDTO answer) throws Exception {
		BizPlayerAnswerBase player = new BizPlayerAnswerBase();
		player.setDate(new Date());
		player.setAnswerId(answer.getAnswerId());
		player.setGivenAnswer(answer.getGivenAnswer());
		player.setQuestionId(answer.getQuestionId());
		player.setPointsAwarded(answer.getPointsAwarded());
		player.setGameplayerId(answer.getGameplayerId());
		player.execProcessInsert();

		return Response.ok("Respuesta registrada correctamente.").build();

	}

}
