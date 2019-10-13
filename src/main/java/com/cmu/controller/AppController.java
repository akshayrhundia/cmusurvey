package com.cmu.controller;

import com.cmu.model.Admin;
import com.cmu.model.FileBucket;
import com.cmu.model.QuestionAudioForAudio;
import com.cmu.model.QuestionAudioForText;
import com.cmu.model.QuestionText;
import com.cmu.model.User;
import com.cmu.model.UserAnswers;
import com.cmu.model.UserDocument;
import com.cmu.pojo.AdminPojo;
import com.cmu.pojo.TextQuestion;
import com.cmu.pojo.UserAnswersPojo;
import com.cmu.service.AdminService;
import com.cmu.service.QuestionService;
import com.cmu.service.UserAnswersService;
import com.cmu.service.UserDocumentService;
import com.cmu.service.UserService;
import com.cmu.util.FileValidator;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.view.RedirectView;

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

  @RequestMapping(value = {"/echo"}, method = RequestMethod.GET)
  @ResponseBody
  public String getAllAdmin() {

    return "All is well";
  }

  @RequestMapping(value = {"/{type}/speak"}, method = RequestMethod.GET)
  public String getAllAdmin(ModelMap model, @PathVariable("type") String type) {
    List<Admin> myList = adminService.findAllAdmins();
    if (myList.size() != 0) {
      model.addAttribute("speakfirstpage", new String(myList.get(myList.size() - 1).getSpeakfirstpage()));
    } else {
      model.addAttribute("speakfirstpage", "");
    }
    User user = new User();
    model.addAttribute("user", user);
    return "firstpage";
  }

  @RequestMapping(value = {"/{type}/write"}, method = RequestMethod.GET)
  public String getFirstWritePage(ModelMap model, @PathVariable("type") String type) {
    List<Admin> myList = adminService.findAllAdmins();
    for (Admin m : myList) {
      System.out.println(m.getWritefirstpage());
    }
    model.addAttribute("writefirstpage", new String(myList.get(myList.size() - 1).getWritefirstpage()));
    User user = new User();
    model.addAttribute("user", user);
    return "firstpage";
  }

  /**
   * This method will be called on form submission, handling POST request for saving user in database. It also validates
   * the user input
   */
  @RequestMapping(value = {"/{type}/speak"}, method = RequestMethod.POST)
  public String saveUserRegistration(@Valid User user, BindingResult result, ModelMap model,
                                     HttpServletRequest request, @PathVariable("type") String type) {

    System.out.println(request.getContextPath());
    if (result.hasErrors()) {
      return "speak";
    }

    if (!userService.isUseruserUnique(user.getId(), user.getUserId())) {
      model.addAttribute("user", user);
      System.out.println("**********saving username:*******" + user.getUserId());
      request.getSession().setAttribute("username", user.getUserId());
      return "redirect:../../surveys/cmu/" + type + "/speakins";
    }

    userService.saveUser(user);

    model.addAttribute("user", user);
    System.out.println("**********saving username:*******" + user.getUserId());
    request.getSession().setAttribute("username", user.getUserId());
    model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
    return "redirect:../../surveys/cmu/" + type + "/speakins";
  }

  /**
   * This method will be called on form submission, handling POST request for saving user in database. It also validates
   * the user input
   */
  @RequestMapping(value = {"{type}/write"}, method = RequestMethod.POST)
  public String saveWriteSurvey(@Valid User user, BindingResult result, ModelMap model, HttpServletRequest request,
                                @PathVariable("type") String type) {

    if (result.hasErrors()) {
      return "redirect:write";
    }

    if (!userService.isUseruserUnique(user.getId(), user.getUserId())) {
      System.out.println("========FOUND=========" + user.getUserId());
      model.addAttribute("user", user);
      request.getSession().setAttribute("username", user.getUserId());
      return getWriteInstructions(model, type);
    }

    userService.saveUser(user);

    model.addAttribute("user", user);
    request.getSession().setAttribute("username", user.getUserId());
    model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
    return getWriteInstructions(model, type);
  }

  @RequestMapping(value = {"/{type}/speakins"}, method = RequestMethod.GET)
  public String getSpeakInstructions(HttpServletRequest request, ModelMap model, @PathVariable("type") String type) {
    System.out.println("************");
    System.out.println("************" + request.getSession().getAttribute("username"));
    List<Admin> myList = adminService.findAllAdmins();
    if (type.equalsIgnoreCase("audioaudio")) {
      model.addAttribute("qId", "AUDIOAUDIO_0");
    }
    if (type.equalsIgnoreCase("audiotext")) {
      model.addAttribute("qId", "AUDIOTEXT_0");
    }
    if (type.equalsIgnoreCase("text")) {
      model.addAttribute("qId", "1");
    }
    model.addAttribute("survey", "speaksurvey");
    if (type.equalsIgnoreCase("audioaudio")) {
      model.addAttribute("speakIns", new String(myList.get(myList.size() - 1).getSpeakAudioIns()));
    }
    //if(type.equalsIgnoreCase("audiotext"))
    //model.addAttribute("speakIns", new String(myList.get(myList.size() - 1).getWriteIns()));
    if (type.equalsIgnoreCase("text")) {
      model.addAttribute("speakIns", new String(myList.get(myList.size() - 1).getSpeakIns()));
    }
    //if(type.equalsIgnoreCase("audioaudio"))
    //model.addAttribute("speakIns", new String(myList.get(myList.size() - 1).getSpeakAudioIns()));

    return "instructions";
  }

  // @RequestMapping(value = {"{type}/writeins"}, method = RequestMethod.GET)
  public String getWriteInstructions(ModelMap model, String type) {
    List<Admin> myList = adminService.findAllAdmins();
    if (type.equalsIgnoreCase("audioaudio")) {
      model.addAttribute("qId", "AUDIOAUDIO_0");
    }
    if (type.equalsIgnoreCase("audiotext")) {
      model.addAttribute("qId", "AUDIOTEXT_0");
    }
    if (type.equalsIgnoreCase("text")) {
      model.addAttribute("qId", "1");
    }
    model.addAttribute("survey", "writesurvey");
    //model.addAttribute("writeIns", new String(myList.get(myList.size() - 1).getSpeakIns()));

    if (type.equalsIgnoreCase("audiotext")) {
      model.addAttribute("writeIns", new String(myList.get(myList.size() - 1).getWriteAudioIns()));
    }
    if (type.equalsIgnoreCase("text")) {
      model.addAttribute("writeIns", new String(myList.get(myList.size() - 1).getWriteIns()));
    }

    return "instructions";
  }

  @RequestMapping(value = {"{type}/writesurvey/{qId}"}, method = RequestMethod.GET)
  public String getWritesurvey(@PathVariable("qId") String qId, @RequestParam("user") String username, ModelMap model,
                               @PathVariable("type") String type) {
    model.addAttribute("titletype", type);
    model.addAttribute("username", username);
    if (type.equalsIgnoreCase("text")) {
      try {
        QuestionText que = questionService.findTextById(Integer.parseInt(qId));
        if (que != null) {
          model.addAttribute("question", que);
          model.addAttribute("qId", que.getId() + 1);
          return "wsurvey";
        } else {
          List<Admin> admins = adminService.findAllAdmins();
          Admin temp = admins.get(admins.size() - 1);
          model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
          return "over";
        }
      } catch (Exception ex) {
        return "redirect:write";
      }
    } else if (type.equalsIgnoreCase("audiotext")) {
      try {
        QuestionAudioForText que = questionService.findAudioForTextById(qId);
        if (que != null) {
          model.addAttribute("question", que);
          model.addAttribute("qId", que.getId() + 1);
          //model.addAttribute("type", type);
          return "wsurvey";
        } else {
          List<Admin> admins = adminService.findAllAdmins();
          Admin temp = admins.get(admins.size() - 1);
          model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
          return "over";
        }
      } catch (Exception ex) {
        return "redirect:write";
      }
    } else {
      try {
        QuestionAudioForAudio que = questionService.findAudioForAudioById(qId);
        if (que != null) {
          model.addAttribute("question", que);
          model.addAttribute("qId", que.getId() + 1);
          return "wsurvey";
        } else {
          List<Admin> admins = adminService.findAllAdmins();
          Admin temp = admins.get(admins.size() - 1);
          model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
          return "over";
        }
      } catch (Exception ex) {
        return "redirect:write";
      }
    }
  }

  @RequestMapping(value = {"/{type}/speaksurvey/{qId}"}, method = RequestMethod.GET)
  public String getSpeakSurvey(HttpServletRequest request, @RequestParam("user") String username,
                               @PathVariable("qId") String qId, ModelMap model, @PathVariable("type") String type) {
    System.out.println("************");
    System.out.println("************" + request.getParameter("jsessionid"));
    HttpSession session = request.getSession();
    String sessionId = session.getId();
    System.out.println("************");
    System.out.println("************" + sessionId);

    model.addAttribute("username", username);
    if (type.equalsIgnoreCase("text")) {
      //String[] sp=qId.split("_");

      QuestionText que = questionService.findTextById(Integer.parseInt(qId));
      if (que != null) {
        model.addAttribute("question", que);
        model.addAttribute("qId", que.getId() + 1);
        model.addAttribute("titletype", type);
        return "ssurvey";
      } else {
        List<Admin> admins = adminService.findAllAdmins();
        Admin temp = admins.get(admins.size() - 1);
        model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
        return "over";
      }
    } else if (type.equalsIgnoreCase("audiotext")) {
      QuestionAudioForText que = questionService.findAudioForTextById(qId);
      if (que != null) {
        model.addAttribute("question", que);
        String[] sp = qId.split("_");
        model.addAttribute("qId", Integer.parseInt(sp[1]) + 1);
        model.addAttribute("titletype", type);
        return "ssurvey";
      } else {
        List<Admin> admins = adminService.findAllAdmins();
        Admin temp = admins.get(admins.size() - 1);
        model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
        return "over";
      }
    } else {
      QuestionAudioForAudio que = questionService.findAudioForAudioById(qId);
      if (que != null) {
        model.addAttribute("question", que);
        String[] sp = qId.split("_");
        model.addAttribute("qId", Integer.parseInt(sp[1]) + 1);
        model.addAttribute("titletype", type);
        return "ssurvey";
      } else {
        List<Admin> admins = adminService.findAllAdmins();
        Admin temp = admins.get(admins.size() - 1);
        model.addAttribute("secondlastpage", new String(temp.getSecondlastpage()));
        return "over";
      }
    }

  }

  @RequestMapping(value = {"/{type}/savewriteans"}, method = RequestMethod.POST)
  public RedirectView saveAnswerText(HttpServletRequest request, @RequestParam("reply") String reply,
                                     @RequestParam("user") String username,
                                     @RequestParam("qId") String qId, @PathVariable("type") String qType) {
    System.out.println(username);
    if (qType.equalsIgnoreCase("text")) {
      try {
        QuestionText que = questionService.findTextById(Integer.parseInt(qId));
        if (que != null) {
          UserAnswers ans = new UserAnswers();
          ans.setqId(Integer.parseInt(qId));
          ans.setReply(reply.getBytes());
          ans.setType("text");
          ans.setQtype(qType);
          ans.setUserId(username);
          ans.setQue(que.getTitle().getBytes());
          if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(qId),
                                                        username) != null) {
            userAnsService.updateUserAnswer(ans);
          } else {
            userAnsService.saveUserAnswer(ans);
          }
          System.out.println("--------------------");
          return new RedirectView("../" + qType + "/writesurvey/"
                                  + (Integer.parseInt(qId) + 1)
                                  + "?user="
                                  + username);
        } else {
          return new RedirectView("/writesurvey/" + (qId) + "?user=" + username);
        }
      } catch (Exception ex) {
        return new RedirectView("write");
      }
    } else if (qType.equalsIgnoreCase("audiotext")) {
      try {
        String[] sp = qId.split("_");
        QuestionAudioForText que = questionService.findAudioForTextById(qId);
        if (que != null) {
          UserAnswers ans = new UserAnswers();
          ans.setqId(Integer.parseInt(sp[1]));
          ans.setReply(reply.getBytes());
          ans.setType("text");
          ans.setQtype(qType);
          ans.setUserId(username);
          ans.setQue(que.getTitle());
          if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(sp[1]),
                                                        username) != null) {
            userAnsService.updateUserAnswer(ans);
          } else {
            userAnsService.saveUserAnswer(ans);
          }
          return new RedirectView(qType + "/writesurvey/" + ("AUDIOTEXT_" + (Integer.parseInt(sp[1])
                                                                             + 1)) + "?user=" + username);
        } else {
          return new RedirectView(
              qType
              + "/writesurvey/"
              + ("AUDIOTEXT_" + (Integer.parseInt(sp[1])))
              + "?user="
              + username);
        }
      } catch (Exception ex) {
        return new RedirectView("write");
      }
    } else {
      try {
        String[] sp = qId.split("_");
        QuestionAudioForAudio que = questionService.findAudioForAudioById(qId);
        if (que != null) {
          UserAnswers ans = new UserAnswers();
          ans.setqId(Integer.parseInt(sp[1]));
          ans.setReply(reply.getBytes());
          ans.setType("text");
          ans.setQtype(qType);
          ans.setUserId(username);
          ans.setQue(que.getTitle());
          if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(sp[1]),
                                                        username) != null) {
            userAnsService.updateUserAnswer(ans);
          } else {
            userAnsService.saveUserAnswer(ans);
          }
          return new RedirectView(
              qType
              + "/writesurvey/"
              + ("AUDIOAUDIO_" + (Integer.parseInt(sp[1])
                                  + 1))
              + "?user="
              + username);
        } else {
          return new RedirectView(
              qType
              + "/writesurvey/"
              + ("AUDIOAUDIO_" + (Integer.parseInt(sp[1])))
              + "?user="
              + username);
        }
      } catch (Exception ex) {
        return new RedirectView(":write");
      }
    }
  }

  @ResponseBody
  @RequestMapping(value = {"/{type}/savespeakans"}, method = RequestMethod.POST)
  public String saveAnswerAudio(HttpServletRequest request, @RequestParam("reply") MultipartFile reply,
                                @RequestParam("qId") String qId, @RequestParam("user") String username,
                                @PathVariable("type") String qType) throws IOException {
    System.out.println("************");
    System.out.println("************" + request.getSession().getAttribute("username"));
    if (qType.equalsIgnoreCase("text")) {
      TextQuestion temp = new TextQuestion();
      //String[] sp=qId.split("_");
      QuestionText que = questionService.findTextById(Integer.parseInt(qId));
      if (que != null) {
        UserAnswers ans = new UserAnswers();
        ans.setqId(Integer.parseInt(qId));
        ans.setReply(reply.getBytes());
        ans.setType("Audio");
        ans.setQtype(qType);
        //ans.setUserId((String) request.getSession().getAttribute("username"));
        ans.setUserId(username);
        ans.setQue(que.getTitle().getBytes());
        if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(qId),
                                                      username) != null) {
          userAnsService.updateUserAnswer(ans);
        } else {
          userAnsService.saveUserAnswer(ans);
        }
        return "../speaksurvey/" + (Integer.parseInt(qId) + 1) + "?user=" + username;
      } else {
        return "../speaksurvey/" + (Integer.parseInt(qId)) + "?user=" + username;
      }
    } else if (qType.equalsIgnoreCase("audiotext")) {
      QuestionAudioForText que = questionService.findAudioForTextById(qId);
      String[] sp = qId.split("_");
      if (que != null) {
        UserAnswers ans = new UserAnswers();
        ans.setqId(Integer.parseInt(sp[1]));
        ans.setReply(reply.getBytes());
        ans.setType("Audio");
        ans.setQtype(qType);
        //ans.setUserId((String) request.getSession().getAttribute("username"));
        ans.setUserId(username);
        ans.setQue(que.getTitle());
        if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(sp[1]),
                                                      username) != null) {
          userAnsService.updateUserAnswer(ans);
        } else {
          userAnsService.saveUserAnswer(ans);
        }
        return "../speaksurvey/" + ("AUDIOTEXT_" + (Integer.parseInt(sp[1]) + 1)) + "?user=" + username;
      } else {
        return "../speaksurvey/" + ("AUDIOTEXT_" + (Integer.parseInt(sp[1]))) + "?user=" + username;
      }
    } else {
      QuestionAudioForAudio que = questionService.findAudioForAudioById(qId);
      String[] sp = qId.split("_");
      if (que != null) {
        UserAnswers ans = new UserAnswers();
        ans.setqId(Integer.parseInt(sp[1]));
        ans.setReply(reply.getBytes());
        ans.setType("Audio");
        ans.setQtype(qType);
        //ans.setUserId((String) request.getSession().getAttribute("username"));
        ans.setUserId(username);
        ans.setQue(que.getTitle());
        if (userAnsService.findUserAnswerByQuestionId(Integer.parseInt(sp[1]),
                                                      username) != null) {
          userAnsService.updateUserAnswer(ans);
        } else {
          userAnsService.saveUserAnswer(ans);
        }
        return "../speaksurvey/" + ("AUDIOAUDIO_" + (Integer.parseInt(sp[1]) + 1)) + "?user=" + username;
      } else {
        return "../speaksurvey/" + ("AUDIOAUDIO_" + (Integer.parseInt(sp[1]))) + "?user=" + username;
      }
    }
  }

  @RequestMapping(value = {"/{type}/last"}, method = RequestMethod.GET)
  public String getLastPage(ModelMap model, HttpServletRequest request, @PathVariable("type") String qType) {
    List<Admin> myList = adminService.findAllAdmins();
    model.addAttribute("lastpage", new String(myList.get(myList.size() - 1).getLastpage()));
    request.getSession().setAttribute("username", null);
    return "last";
  }

  /*************** Admin *************************/
  @GetMapping(value = {"/admin"})
  public String admin(ModelMap model) {
    return "admin";
  }

  @RequestMapping(value = {"/addAdmin"}, method = RequestMethod.POST)
  @ResponseBody
  public String newSpeakingFirstPageText(@RequestParam("speakfirstpage") String speakfirstpage, String writefirstpage,
                                         @RequestParam("speakIns") String speakIns,
                                         @RequestParam("writeIns") String writeIns,
                                         @RequestParam("lastpage") String lastpage,
                                         @RequestParam("secondlastpage") String secondlastpage,
                                         @RequestParam("speakAudioIns") String speakAudioIns,
                                         @RequestParam("writeAudioIns") String writeAudioIns) {

    Admin temp = new Admin();
    temp.setSpeakfirstpage(speakfirstpage.getBytes());
    temp.setLastpage(lastpage.getBytes());
    temp.setSecondlastpage(secondlastpage.getBytes());
    temp.setSpeakIns(speakIns.getBytes());
    temp.setWritefirstpage(writefirstpage.getBytes());
    temp.setWriteIns(writeIns.getBytes());
    temp.setWriteAudioIns(writeAudioIns.getBytes());
    temp.setSpeakAudioIns(speakAudioIns.getBytes());
    adminService.saveAdmin(temp);
    return "success";
  }

  @RequestMapping(value = {"/getAdmin"}, method = RequestMethod.GET)
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
      if (temp.getSpeakAudioIns() == null) {
        adminPojo.setSpeakAudioIns("");
      } else {
        adminPojo.setSpeakAudioIns(new String(temp.getSpeakAudioIns()));
      }
      if (temp.getWriteAudioIns() == null) {
        adminPojo.setWriteAudioIns("");
      } else {
        adminPojo.setWriteAudioIns(new String(temp.getWriteAudioIns()));
      }
    }
    return new Gson().toJson(adminPojo);

  }

  /**************** Question management ************************/

  @RequestMapping(value = {"/newquestion"}, method = RequestMethod.GET)
  public String newQuestion(ModelMap model) {
    return "newquestion";
  }

  @RequestMapping(value = {"/managetextquestions"}, method = RequestMethod.GET)
  public String manageTextquestions(ModelMap model) {
    List<QuestionText> myList = questionService.findAllTextQuestions();
    System.out.println(myList.size());
    model.addAttribute("managequestions", myList);
    return "managetextquestions";
  }

  @RequestMapping(value = {"/manageaudiofortextquestions"}, method = RequestMethod.GET)
  public String manageAudioForTextquestions(ModelMap model) {
    List<QuestionAudioForText> myList = questionService.findAllAudioForTextQuestions();
    model.addAttribute("managequestions", myList);
    return "manageaudiofortextquestions";
  }

  @RequestMapping(value = {"/manageaudioforaudioquestions"}, method = RequestMethod.GET)
  public String manageAudioForAudioquestions(ModelMap model) {
    List<QuestionAudioForAudio> myList = questionService.findAllAudioForAudioQuestions();
    model.addAttribute("managequestions", myList);
    return "manageaudioforaudioquestions";
  }

  @RequestMapping(value = {"/newquestionastext"}, method = RequestMethod.POST)
  @ResponseBody
  public String saveQuestionText(@RequestParam("title") String title, @RequestParam("options") List<String> options) {
    QuestionText que = new QuestionText();
    que.setOptions(options);
    que.setTitle(title);
    questionService.saveTextQuestion(que);
    return "success";
  }

  @RequestMapping(value = {"/{type}/updatequestiontitle/{qId}"}, method = RequestMethod.POST)
  @ResponseBody
  public String updateQuestionText(@RequestParam("title") String title, @PathVariable("type") String type,
                                   @PathVariable("qId") int qId) {
    QuestionText que = new QuestionText();
    que.setId(qId);
    que.setTitle(title);
    questionService.updateTextQuestionTitle(que);
    return "success";
  }

  @RequestMapping(value = {"/newquestionasaudiofortext"}, method = RequestMethod.POST)
  @ResponseBody
  public String saveQuestionAudioForText(@RequestParam("data") MultipartFile data,
                                         @RequestParam("options") List<String> options) throws IOException {

    QuestionAudioForText que = new QuestionAudioForText();
    que.setOptions(options);
    que.setTitle(data.getBytes());
    questionService.saveAudioQuestionForText(que);
    return "success";
  }

  @RequestMapping(value = {"/newquestionasaudioforaudio"}, method = RequestMethod.POST)
  @ResponseBody
  public String saveQuestionAudioForAudio(@RequestParam("data") MultipartFile data,
                                          @RequestParam("options") List<String> options) throws IOException {

    QuestionAudioForAudio que = new QuestionAudioForAudio();
    que.setOptions(options);
    que.setTitle(data.getBytes());
    questionService.saveAudioQuestionForAudio(que);
    return "success";
  }

  @RequestMapping(value = {"/getAllTextQuestions"}, method = RequestMethod.GET)
  public String getAllTextQuestions() {
    List<QuestionText> myList = questionService.findAllTextQuestions();
    List<TextQuestion> myTList = new ArrayList<TextQuestion>();
    for (QuestionText que : myList) {
      TextQuestion temp = new TextQuestion();
      temp.setId(que.getId());
      temp.setOptions(que.getOptions());
      temp.setTitle(new String(que.getTitle()));
      temp.setTitletype("text");
      myTList.add(temp);
    }
    Gson g = new Gson();
    return g.toJson(myTList);
  }

  @RequestMapping(value = {"/getAllAudioForTextQuestions"}, method = RequestMethod.GET)
  public String getAllAudioForTextQuestions() {
    List<QuestionAudioForText> myList = questionService.findAllAudioForTextQuestions();
    List<TextQuestion> myTList = new ArrayList<TextQuestion>();
    for (QuestionAudioForText que : myList) {
      TextQuestion temp = new TextQuestion();
      String[] sp = que.getId().split("_");
      temp.setId(Integer.parseInt(sp[1]));
      temp.setOptions(que.getOptions());
      temp.setTitle(new String(que.getTitle()));
      temp.setTitletype("audio");
      myTList.add(temp);
    }
    Gson g = new Gson();
    return g.toJson(myTList);
  }

  @RequestMapping(value = {"/getAllAudioForAudioQuestions"}, method = RequestMethod.GET)
  public String getAllAudioForAudioQuestions() {
    List<QuestionAudioForAudio> myList = questionService.findAllAudioForAudioQuestions();
    List<TextQuestion> myTList = new ArrayList<TextQuestion>();
    for (QuestionAudioForAudio que : myList) {
      TextQuestion temp = new TextQuestion();
      String[] sp = que.getId().split("_");
      temp.setId(Integer.parseInt(sp[1]));
      temp.setOptions(que.getOptions());
      temp.setTitle(new String(que.getTitle()));
      temp.setTitletype("audio");
      myTList.add(temp);
    }
    Gson g = new Gson();
    return g.toJson(myTList);
  }

  @RequestMapping(value = "/getQuestionAudioForTextFile/{qId}")
  public void getQuestionAudioForTextFile(@PathVariable("qId") String id, ModelMap model, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("audio/vnd.wave");

    response.setHeader("Content-Disposition", "attachment; filename=\"" + "que.wav" + "\"");
    try {
      QuestionAudioForText myQue = questionService.findAudioForTextById(id);
      //System.out.println(myQue.getTitle().length);
      response.getOutputStream().write(myQue.getTitle());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @RequestMapping(value = "/getQuestionAudioForAudioFile/{qId}")
  public void getQuestionAudioForAudioFile(@PathVariable("qId") String id, ModelMap model, HttpServletResponse response)
      throws IOException, ServletException {

    response.setContentType("audio/vnd.wave");

    response.setHeader("Content-Disposition", "attachment; filename=\"" + "que.wav" + "\"");
    try {
      QuestionAudioForAudio myQue = questionService.findAudioForAudioById(id);
      //System.out.println(myQue.getTitle().length);
      response.getOutputStream().write(myQue.getTitle());

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @ResponseBody
  @RequestMapping(value = {"/{type}/delete/{qId}"}, method = RequestMethod.GET)
  public String deleteQuestion(@PathVariable("qId") String qId, ModelMap model, @PathVariable("type") String type) {
    if (type.equalsIgnoreCase("text")) {
      questionService.deleteTextById(Integer.parseInt(qId));
    } else if (type.equalsIgnoreCase("audiotext")) {
      questionService.deleteAudioForTextById(qId);
    } else {
      questionService.deleteAudioForAudioById(qId);
    }
    return "";
  }

  /****************** Results *************************/

  @RequestMapping(value = {"/{type}/result"}, method = RequestMethod.GET)
  public String getResult(ModelMap model, @PathVariable("type") String qType) {
    List<User> users = userService.findAllUsers();
    model.addAttribute("users", users);
    model.addAttribute("type", qType);
    return "result";
  }

  @RequestMapping(value = {"/{type}/useranswers/{userId}"}, method = RequestMethod.GET)
  public String getResults(ModelMap model, @PathVariable String userId, @PathVariable("type") String qType) {
    List<UserAnswers> ans = userAnsService.findAllUserAnswers(userId);
    List<UserAnswers> ret = new LinkedList<UserAnswers>();
    for (UserAnswers q : ans) {
      System.out.println(q.getQtype());
      if (q.getQtype().equalsIgnoreCase(qType)) {
        if (q.getType().equalsIgnoreCase("text")) {
          q.setType(new String(q.getReply()));
        }
        if (q.getQtype().equalsIgnoreCase("text")) {
          q.setQtype(new String(q.getQue()));
        }
        ret.add(q);
      }
    }
    model.addAttribute("answers", ret);
    model.addAttribute("type", qType);
    return "useranswers";
  }

  @RequestMapping(value = {"/{type}/getallanswers"}, method = RequestMethod.GET)
  public String getAllResults(ModelMap model, @PathVariable("type") String qType) {
    List<UserAnswers> ans = userAnsService.findUserAnswerByQuestionType(qType);
    List<UserAnswersPojo> ret = new LinkedList<UserAnswersPojo>();
    for (UserAnswers q : ans) {
      //System.out.println(q.getQtype());
      UserAnswersPojo p = new UserAnswersPojo();

      if (q.getQtype().equalsIgnoreCase(qType)) {
        User u = userService.findByuser(q.getUserId());
        if (q.getType().equalsIgnoreCase("text")) {
          q.setType(new String(q.getReply()));
        }
        if (q.getQtype().equalsIgnoreCase("text")) {
          q.setQtype(new String(q.getQue()));
        }
        p.setAns(q);
        p.setUser(u);
        ret.add(p);
      }
    }
    model.addAttribute("answers", ret);
    model.addAttribute("type", qType);
    return "useranswers";
  }

  @RequestMapping(value = "/getAnswerFile/{qId}")
  public void getAnswerFile(@PathVariable("qId") int id, @RequestParam String userId, ModelMap model,
                            HttpServletResponse response)
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
  @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
  public String listUsers(ModelMap model) {

    List<User> users = userService.findAllUsers();
    model.addAttribute("users", users);
    return "userslist";
  }

  /**
   * This method will provide the medium to add a new user.
   */
  @RequestMapping(value = {"/newuser"}, method = RequestMethod.GET)
  public String newUser(ModelMap model) {
    User user = new User();
    model.addAttribute("user", user);
    model.addAttribute("edit", false);
    return "registration";
  }

  /**
   * This method will be called on form submission, handling POST request for saving user in database. It also validates
   * the user input
   */
  @RequestMapping(value = {"/newuser"}, method = RequestMethod.POST)
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
                                                                                       new String[] {user.getUserId()},
                                                                                       Locale.getDefault()));
      result.addError(userError);
      return "registration";
    }

    userService.saveUser(user);

    model.addAttribute("user", user);
    model.addAttribute("success", "User " + user.getUserId() + " registered successfully");
    // return "success";
    return "registrationsuccess";
  }

  @RequestMapping(value = {"/test"}, method = RequestMethod.GET)
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
  @RequestMapping(value = {"/edit-user-{userId}"}, method = RequestMethod.GET)
  public String editUser(@PathVariable String userId, ModelMap model) {
    User user = userService.findByuser(userId);
    model.addAttribute("user", user);
    model.addAttribute("edit", true);
    return "registration";
  }

  /**
   * This method will be called on form submission, handling POST request for updating user in database. It also
   * validates the user input
   */
  @RequestMapping(value = {"/edit-user-{userId}"}, method = RequestMethod.POST)
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
  @RequestMapping(value = {"/delete-user-{userId}"}, method = RequestMethod.GET)
  public String deleteUser(@PathVariable String userId) {
    userService.deleteUserByuser(userId);
    return "redirect:/list";
  }

  @RequestMapping(value = {"/add-document-{userId}"}, method = RequestMethod.GET)
  public String addDocuments(@PathVariable int userId, ModelMap model) {
    User user = userService.findById(userId);
    model.addAttribute("user", user);

    FileBucket fileModel = new FileBucket();
    model.addAttribute("fileBucket", fileModel);

    List<UserDocument> documents = userDocumentService.findAllByUserId(userId);
    model.addAttribute("documents", documents);

    return "managedocuments";
  }

  @RequestMapping(value = {"/download-document-{userId}-{docId}"}, method = RequestMethod.GET)
  public String downloadDocument(@PathVariable int userId, @PathVariable int docId, HttpServletResponse response)
      throws IOException {
    UserDocument document = userDocumentService.findById(docId);
    response.setContentType(document.getType());
    response.setContentLength(document.getContent().length);
    response.setHeader("Content-Disposition", "attachment; filename=\"" + document.getName() + "\"");

    FileCopyUtils.copy(document.getContent(), response.getOutputStream());

    return "redirect:/add-document-" + userId;
  }

  @RequestMapping(value = {"/delete-document-{userId}-{docId}"}, method = RequestMethod.GET)
  public String deleteDocument(@PathVariable int userId, @PathVariable int docId) {
    userDocumentService.deleteById(docId);
    return "redirect:/add-document-" + userId;
  }

  @RequestMapping(value = {"/add-document-{userId}"}, method = RequestMethod.POST)
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
