package com.cmu.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;

import com.cmu.model.Admin;
import com.cmu.model.FileBucket;
import com.cmu.model.Question;
import com.cmu.model.User;
import com.cmu.model.UserAnswers;
import com.cmu.model.UserDocument;
import com.cmu.pojo.AdminPojo;
import com.cmu.pojo.TextQuestion;
import com.cmu.service.AdminService;
import com.cmu.service.QuestionService;
import com.cmu.service.UserAnswersService;
import com.cmu.service.UserDocumentService;
import com.cmu.service.UserService;
import com.cmu.util.FileValidator;
import com.google.gson.Gson;

@Controller
@RequestMapping("/")
public class AppController {

	@Autowired
	QuestionService questionService;

	@Autowired
	UserService userService;

	@Autowired
	UserAnswersService userAnsService;

	@Autowired
	AdminService adminService;

	@Autowired
	UserDocumentService userDocumentService;

	@Autowired
	MessageSource messageSource;

	@Autowired
	FileValidator fileValidator;

	@InitBinder("fileBucket")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(fileValidator);
	}

	@RequestMapping(value = { "/speak" }, method = RequestMethod.GET)
	public String getAllAdmin(ModelMap model) {
		List<Admin> myList = adminService.findAllAdmins();
		model.addAttribute("speakfirstpage", new String(myList.get(myList.size() - 1).getSpeakfirstpage()));
		User user = new User();
		model.addAttribute("user", user);
		return "firstpage";
	}

	@RequestMapping(value = { "/write" }, method = RequestMethod.GET)
	public String getFirstWritePage(ModelMap model) {
		List<Admin> myList = adminService.findAllAdmins();
		model.addAttribute("writefirstpage", new String(myList.get(myList.size() - 1).getWritefirstpage()));
		User user = new User();
		model.addAttribute("user", user);
		return "firstpage";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/speak" }, method = RequestMethod.POST)
	public String saveUserRegistration(@Valid User user, BindingResult result, ModelMap model,
			HttpServletRequest request) {

		if (result.hasErrors()) {
			return "speak";
		}

		if (!userService.isUseruserUnique(user.getId(), user.getUserId())) {
			model.addAttribute("user", user);
			request.getSession().setAttribute("username", user.getUserId());
			return "redirect:speakins";
		}

		userService.saveUser(user);

		model.addAttribute("user", user);
		request.getSession().setAttribute("username", user.getUserId());
		model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
		return "redirect:speakins";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/write" }, method = RequestMethod.POST)
	public String saveWriteSurvey(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request) {

		if (result.hasErrors()) {
			return "redirect:write";
		}

		if (!userService.isUseruserUnique(user.getId(), user.getUserId())) {
			model.addAttribute("user", user);
			request.getSession().setAttribute("username", user.getUserId());
			return "redirect:writeins";
		}

		userService.saveUser(user);

		model.addAttribute("user", user);
		request.getSession().setAttribute("username", user.getUserId());
		model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
		return "redirect:writeins";
	}
	@RequestMapping(value = { "/speakins" }, method = RequestMethod.GET)
	public String getSpeakInstructions(ModelMap model) {
		List<Admin> myList = adminService.findAllAdmins();
		model.addAttribute("qId", "1");
		model.addAttribute("survey", "speaksurvey");
		model.addAttribute("speakIns", new String(myList.get(myList.size() - 1).getSpeakIns()));
		return "instructions";
	}

	@RequestMapping(value = { "/writeins" }, method = RequestMethod.GET)
	public String getWriteInstructions(ModelMap model) {
		List<Admin> myList = adminService.findAllAdmins();
		model.addAttribute("qId", "1");
		model.addAttribute("survey", "writesurvey");
		model.addAttribute("writeIns", new String(myList.get(myList.size() - 1).getSpeakIns()));
		return "instructions";
	}
	
	@RequestMapping(value = { "/writesurvey/{qId}" }, method = RequestMethod.GET)
	public String getWritesurvey(@PathVariable("qId") int qId, ModelMap model) {
		try{
		Question que = questionService.findById(qId);
		if (que != null) {
			if (que.getTitletype().equalsIgnoreCase("text")) {
				que.setTitletype(new String(que.getTitle()));
			}
			model.addAttribute("question", que);
			model.addAttribute("qId", que.getId() + 1);
			return "wsurvey";
		} else {
			List<Admin> admins = adminService.findAllAdmins();
			Admin temp = admins.get(admins.size() - 1);
			model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
			return "over";
		}}
		catch(Exception ex){
			return "redirect:write";
		}
	}
	@RequestMapping(value = { "/speaksurvey/{qId}" }, method = RequestMethod.GET)
	public String getSpeakSurvey(@PathVariable("qId") int qId, ModelMap model) {

		Question que = questionService.findById(qId);
		if (que != null) {
			if (que.getTitletype().equalsIgnoreCase("text")) {
				que.setTitletype(new String(que.getTitle()));
			}
			model.addAttribute("question", que);
			model.addAttribute("qId", que.getId() + 1);
			return "ssurvey";
		} else {
			List<Admin> admins = adminService.findAllAdmins();
			Admin temp = admins.get(admins.size() - 1);
			model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
			return "over";
		}
	}
	
	@RequestMapping(value = { "/savewriteans" }, method = RequestMethod.POST)
	public String saveAnswerText(HttpServletRequest request, @RequestParam("reply") String reply,
			@RequestParam("qId") Integer qId) {
		try{
		Question que = questionService.findById(qId);
		if (que != null) {
			UserAnswers ans = new UserAnswers();
			ans.setqId(qId);
			ans.setReply(reply.getBytes());
			ans.setType("text");
			ans.setQtype(que.getTitletype());
			ans.setUserId((String) request.getSession().getAttribute("username"));
			ans.setQue(que.getTitle());
			if (userAnsService.findUserAnswerByQuestionId(qId,
					(String) request.getSession().getAttribute("username")) != null) {
				userAnsService.updateUserAnswer(ans);
			} else {
				userAnsService.saveUserAnswer(ans);
			}
			return "redirect:writesurvey/" + (qId + 1);
		} else {
			return "redirect:writesurvey/" + (qId);
		}
		}
		catch(Exception ex){
			return "redirect:write";
		}
	}
	
	@ResponseBody
	@RequestMapping(value = { "/savespeakans" }, method = RequestMethod.POST)
	public String saveAnswerAudio(HttpServletRequest request, @RequestParam("reply") MultipartFile reply,
			@RequestParam("qId") Integer qId) throws IOException {
		Question que = questionService.findById(qId);
		if (que != null) {
			UserAnswers ans = new UserAnswers();
			ans.setqId(qId);
			ans.setReply(reply.getBytes());
			ans.setType("Audio");
			ans.setQtype(que.getTitletype());
			ans.setUserId((String) request.getSession().getAttribute("username"));
			ans.setQue(que.getTitle());
			if (userAnsService.findUserAnswerByQuestionId(qId,
					(String) request.getSession().getAttribute("username")) != null) {
				userAnsService.updateUserAnswer(ans);
			} else {
				userAnsService.saveUserAnswer(ans);
			}
			return "../speaksurvey/" + (qId + 1);
		} else {
			return "../speaksurvey/" + (qId);
		}
	}
	
	@RequestMapping(value = { "/last" }, method = RequestMethod.GET)
	public String getLastPage(ModelMap model, HttpServletRequest request) {
		List<Admin> myList = adminService.findAllAdmins();
		model.addAttribute("lastpage", new String(myList.get(myList.size() - 1).getLastpage()));
		request.getSession().setAttribute("username", null);
		return "last";
	}
	
	
	/*************** Admin *************************/
	@RequestMapping(value = { "/admin" }, method = RequestMethod.GET)
	public String admin(ModelMap model) {
		return "admin";
	}
	
	@RequestMapping(value = { "/addAdmin" }, method = RequestMethod.POST)
	@ResponseBody
	public String newSpeakingFirstPageText(@RequestParam("speakfirstpage") String speakfirstpage, String writefirstpage,
			@RequestParam("speakIns") String speakIns, @RequestParam("writeIns") String writeIns,
			@RequestParam("lastpage") String lastpage, @RequestParam("secondlastpage") String secondlastpage) {

		Admin temp = new Admin();
		temp.setSpeakfirstpage(speakfirstpage.getBytes());
		temp.setLastpage(lastpage.getBytes());
		temp.setSecondlastpage(secondlastpage.getBytes());
		temp.setSpeakIns(speakIns.getBytes());
		temp.setWritefirstpage(writefirstpage.getBytes());
		temp.setWriteIns(writeIns.getBytes());
		adminService.saveAdmin(temp);
		return "success";
	}

	@RequestMapping(value = { "/getAdmin" }, method = RequestMethod.GET)
	@ResponseBody
	public String getAdmin() {
		List<Admin> admins = adminService.findAllAdmins();
		AdminPojo adminPojo = new AdminPojo();
		if (admins != null && admins.size() > 0) {
			Admin temp = admins.get(admins.size() - 1);
			adminPojo.setLastpage(new String(temp.getLastpage()));
			adminPojo.setSpeakfirstpage(new String(temp.getSpeakfirstpage()));
			adminPojo.setSpeakIns(new String(temp.getSpeakIns()));
			adminPojo.setWritefirstpage(new String(temp.getWritefirstpage()));
			adminPojo.setWriteIns(new String(temp.getWriteIns()));
			adminPojo.setSecondlastpage(new String(temp.getSecondlastpage()));
		}
		return new Gson().toJson(adminPojo);

	}
	
	/**************** Question management ************************/
	
	@RequestMapping(value = { "/newquestion" }, method = RequestMethod.GET)
	public String newQuestion(ModelMap model) {
		return "newquestion";
	}
	
	@RequestMapping(value = { "/managequestions" }, method = RequestMethod.GET)
	public String managequestions(ModelMap model) {

		List<Question> myList = questionService.findAllQuestions();
		for (Question q : myList) {
			if (q.getTitletype().equalsIgnoreCase("text")) {
				q.setTitletype(new String(q.getTitle()));
			}
		}
		model.addAttribute("managequestions", myList);

		return "managequestions";
	}

	@RequestMapping(value = { "/newquestionastext" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveQuestionText(@RequestParam("title") String title, @RequestParam("options") List<String> options) {
		Question que = new Question();
		que.setOptions(options);
		que.setTitle(title.getBytes());
		que.setTitletype("text");
		questionService.saveQuestion(que);
		return "success";
	}

	@RequestMapping(value = { "/newquestionasaudio" }, method = RequestMethod.POST)
	@ResponseBody
	public String saveQuestionAudio(@RequestParam("data") MultipartFile data,
			@RequestParam("options") List<String> options) throws IOException {

		Question que = new Question();
		que.setOptions(options);
		que.setTitle(data.getBytes());
		que.setTitletype("Audio");
		questionService.saveQuestion(que);
		return "success";
	}


	@RequestMapping(value = { "/getAllQuestions" }, method = RequestMethod.GET)
	public String getAllQuestions() {
		List<Question> myList = questionService.findAllQuestions();
		List<TextQuestion> myTList = new ArrayList<TextQuestion>();
		for (Question que : myList) {
			TextQuestion temp = new TextQuestion();
			temp.setId(que.getId());
			temp.setOptions(que.getOptions());
			temp.setTitle(new String(que.getTitle()));
			temp.setTitletype(que.getTitletype());
			myTList.add(temp);
		}
		Gson g = new Gson();
		return g.toJson(myTList);
	}

	@RequestMapping(value = "/getQuestionFile/{qId}")
	public void getQuestionFile(@PathVariable("qId") int id, ModelMap model, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("audio/vnd.wave");

		response.setHeader("Content-Disposition", "attachment; filename=\"" + "que.wav" + "\"");
		try {
			Question myQue = questionService.findById(id);
			//System.out.println(myQue.getTitle().length);
			response.getOutputStream().write(myQue.getTitle());
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/****************** Results *************************/
	
	@RequestMapping(value = { "/result" }, method = RequestMethod.GET)
	public String getResults(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "result";
	}
	
	
	@RequestMapping(value = { "/useranswers/{userId}" }, method = RequestMethod.GET)
	public String getResults(ModelMap model, @PathVariable String userId) {
		List<UserAnswers> ans = userAnsService.findAllUserAnswers(userId);
		for (UserAnswers q : ans) {
			if (q.getType().equalsIgnoreCase("text")) {
				q.setType(new String(q.getReply()));
			}
			if (q.getQtype().equalsIgnoreCase("text")) {
				q.setQtype(new String(q.getQue()));
			}
		}
		model.addAttribute("answers", ans);
		return "useranswers";
	}

	@RequestMapping(value = "/getAnswerFile/{qId}")
	public void getAnswerFile(@PathVariable("qId") int id,@RequestParam String userId, ModelMap model, HttpServletResponse response)
			throws IOException, ServletException {

		response.setContentType("audio/vnd.wave");

		response.setHeader("Content-Disposition", "attachment; filename=\"" + "que.wav" + "\"");
		try {
			UserAnswers myAns = userAnsService.findUserAnswerByQuestionId(id, userId);
			//System.out.println(myQue.getTitle().length);
			response.getOutputStream().write(myAns.getReply());
	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	/**
	 * This method will list all existing users.
	 */
	@RequestMapping(value = { "/", "/list" }, method = RequestMethod.GET)
	public String listUsers(ModelMap model) {

		List<User> users = userService.findAllUsers();
		model.addAttribute("users", users);
		return "userslist";
	}

	/**
	 * This method will provide the medium to add a new user.
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.GET)
	public String newUser(ModelMap model) {
		User user = new User();
		model.addAttribute("user", user);
		model.addAttribute("edit", false);
		return "registration";
	}

	

	/**
	 * This method will be called on form submission, handling POST request for
	 * saving user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/newuser" }, method = RequestMethod.POST)
	public String saveUser(@Valid User user, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			return "registration";
		}

		/*
		 * Preferred way to achieve uniqueness of field [user] should be
		 * implementing custom @Unique annotation and applying it on field
		 * [user] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		if (!userService.isUseruserUnique(user.getId(), user.getUserId())) {
			FieldError userError = new FieldError("user", "userId", messageSource.getMessage("non.unique.userId",
					new String[] { user.getUserId() }, Locale.getDefault()));
			result.addError(userError);
			return "registration";
		}

		userService.saveUser(user);

		model.addAttribute("user", user);
		model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
		// return "success";
		return "registrationsuccess";
	}

	@RequestMapping(value = { "/test" }, method = RequestMethod.GET)
	@ResponseBody
	public String saveQuestion() {

		/*
		 * Preferred way to achieve uniqueness of field [user] should be
		 * implementing custom @Unique annotation and applying it on field
		 * [user] of Model class [User].
		 * 
		 * Below mentioned peace of code [if block] is to demonstrate that you
		 * can fill custom errors outside the validation framework as well while
		 * still using internationalized messages.
		 * 
		 */
		/*
		 * Question que = new Question(); if (que.getId() != null &&
		 * questionService.findById(que.getId()) != null) { FieldError userError
		 * = new FieldError("user", "userId",
		 * messageSource.getMessage("non.unique.userId", new String[] {
		 * String.valueOf(que.getId()) }, Locale.getDefault())); //
		 * result.addError(userError); return "registration"; } que.setTitle(new
		 * String("This is question").getBytes()); que.setTitletype("text");
		 * List<String> options = new ArrayList<String>(); options.add("Yes");
		 * options.add("No");
		 * 
		 * que.setOptions(options);
		 * 
		 * questionService.saveQuestion(que);
		 */

		// model.addAttribute("que", que);
		// model.addAttribute("success", "que " + que.getId() + " registered
		// successfully");
		// return "success";
		List<UserAnswers> ans = userAnsService.findAllUserAnswers("ahundia");
		System.out.println(ans.size());
		return new Gson().toJson(ans);

	}

	
	
	
	

	

	/**
	 * This method will list all existing users.
	 */


	/**
	 * This method will list all existing users.
	 */
	
	/**
	 * This method will provide the medium to update an existing user.
	 */
	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.GET)
	public String editUser(@PathVariable String userId, ModelMap model) {
		User user = userService.findByuser(userId);
		model.addAttribute("user", user);
		model.addAttribute("edit", true);
		return "registration";
	}

	/**
	 * This method will be called on form submission, handling POST request for
	 * updating user in database. It also validates the user input
	 */
	@RequestMapping(value = { "/edit-user-{userId}" }, method = RequestMethod.POST)
	public String updateUser(@Valid User user, BindingResult result, ModelMap model, @PathVariable String userId) {

		if (result.hasErrors()) {
			return "registration";
		}

		userService.updateUser(user);

		model.addAttribute("success", "User " + user.getUserId() + " updated successfully");
		return "registrationsuccess";
	}

	/**
	 * This method will delete an user by it's userID value.
	 */
	@RequestMapping(value = { "/delete-user-{userId}" }, method = RequestMethod.GET)
	public String deleteUser(@PathVariable String userId) {
		userService.deleteUserByuser(userId);
		return "redirect:/list";
	}

	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.GET)
	public String addDocuments(@PathVariable int userId, ModelMap model) {
		User user = userService.findById(userId);
		model.addAttribute("user", user);

		FileBucket fileModel = new FileBucket();
		model.addAttribute("fileBucket", fileModel);

		List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
		model.addAttribute("documents", documents);

		return "managedocuments";
	}

	@RequestMapping(value = { "/download-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response)
			throws IOException {
		UserDocument document = userDocumentService.findById(docId);
		response.setContentType(document.getType());
		response.setContentLength(document.getContent().length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");

		FileCopyUtils.copy(document.getContent(), response.getOutputStream());

		return "redirect:/add-document-" + userId;
	}

	@RequestMapping(value = { "/delete-document-{userId}-{docId}" }, method = RequestMethod.GET)
	public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
		userDocumentService.deleteById(docId);
		return "redirect:/add-document-" + userId;
	}

	@RequestMapping(value = { "/add-document-{userId}" }, method = RequestMethod.POST)
	public String uploadDocument(@Valid FileBucket fileBucket, BindingResult result, ModelMap model,
			@PathVariable int userId) throws IOException {

		if (result.hasErrors()) {
			System.out.println("validation errors");
			User user = userService.findById(userId);
			model.addAttribute("user", user);

			List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
			model.addAttribute("documents", documents);

			return "managedocuments";
		} else {

			System.out.println("Fetching file");

			User user = userService.findById(userId);
			model.addAttribute("user", user);

			saveDocument(fileBucket, user);

			return "redirect:/add-document-" + userId;
		}
	}

	private void saveDocument(FileBucket fileBucket, User user) throws IOException {

		UserDocument document = new UserDocument();

		MultipartFile multipartFile = fileBucket.getFile();

		document.setName(multipartFile.getOriginalFilename());
		document.setDescription(fileBucket.getDescription());
		document.setType(multipartFile.getContentType());
		document.setContent(multipartFile.getBytes());
		document.setUser(user);
		userDocumentService.saveDocument(document);
	}

}
