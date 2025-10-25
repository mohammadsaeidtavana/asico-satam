package com.asico.hr.web;


import com.asico.hr.domain.CourseModel;
import com.asico.hr.service.CourseService;
import com.asico.hr.service.UserCourseService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author mohammad saeid tavana
 * @since 2024
 */
@Controller
public class CoursePageController {


    CourseService courseService;

    UserCourseService userCourseService;

    public CoursePageController(CourseService courseService, UserCourseService userCourseService) {
        this.courseService = courseService;
        this.userCourseService = userCourseService;
    }

    private final String baseDirectory = "course/";

    private final String baseDirectoryCourseEpisode = "course/episode/";

    @RequestMapping(value = "/javase")
    public String javase(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/javase");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "javase";
        } else {

            return baseDirectory + "javase-panel";
        }


    }

    @RequestMapping(value = "/javaee")
    public String javaee(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/javaee");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "javaee";
        } else {

            return baseDirectory + "javaee-panel";
        }
    }
        @RequestMapping(value = "/git")
        public String git(Model model, HttpSession httpSession) {
            CourseModel courseModel = courseService.findByCoursePage("/git");
            model.addAttribute("course", courseModel);

            if (httpSession.getAttribute("phoneNumber") == null) {

                return baseDirectory + "git";
            } else {

                return baseDirectory + "git-panel";
            }


    }

    @RequestMapping(value = "/spring")
    public String spring(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/spring");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "spring";
        } else {

            return baseDirectory + "spring-panel";
        }
    }

    @RequestMapping(value = "/security")
    public String security(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/security");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "security";
        } else {

            return baseDirectory + "security-panel";
        }
    }

    @RequestMapping(value = "/devops")
    public String devops(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/devops");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "devops";
        } else {

            return baseDirectory + "devops-panel";
        }
    }

    @RequestMapping(value = "/redis")
    public String redis(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/redis");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "redis";
        } else {

            return baseDirectory + "redis-panel";
        }
    }

    @RequestMapping(value = "/microservice")
    public String microservice(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/microservice");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "microservice";
        } else {

            return baseDirectory + "microservice-panel";
        }
    }

    @RequestMapping(value = "/intellij")
    public String intellij(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/intellij");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "intellij";
        } else {

            return baseDirectory + "intellij-panel";
        }
    }

    @RequestMapping(value = "/springcloud")
    public String springcloud(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/springcloud");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "springcloud";
        } else {

            return baseDirectory + "springcloud-panel";
        }
    }

    @RequestMapping(value = "/designpattern")
    public String designpattern(Model model,HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/designpattern");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "designpattern";
        } else {

            return baseDirectory + "designpattern-panel";
        }
    }

    @RequestMapping(value = "/cleancode")
    public String cleancode(Model model,HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/cleancode");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "cleancode";
        } else {

            return baseDirectory + "cleancode-panel";
        }
    }

    @RequestMapping(value = "/intro")
    public String intro(Model model,HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/intro");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectory + "intro";
        } else {

            return baseDirectory + "intro-panel";
        }
    }


    //////////////////////// course episode ///////////////////////////////

    @RequestMapping(value = "/javase-episode")
    public String javaseepisode(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/javase");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectoryCourseEpisode + "javase-course-episode";
        } else {

            return baseDirectoryCourseEpisode + "javase-panel-course-episode";
        }
    }

    @RequestMapping(value = "/git-episode")
    public String gitepisode(Model model, HttpSession httpSession) {
        CourseModel courseModel = courseService.findByCoursePage("/git");
        model.addAttribute("course", courseModel);

        if (httpSession.getAttribute("phoneNumber") == null) {

            return baseDirectoryCourseEpisode + "git-course-episode";
        } else {

            return baseDirectoryCourseEpisode + "git-panel-course-episode";
        }
    }




}
