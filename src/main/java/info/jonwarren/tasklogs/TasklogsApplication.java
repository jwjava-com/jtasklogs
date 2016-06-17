package info.jonwarren.tasklogs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
//@EnableOAuth2Client
public class TasklogsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TasklogsApplication.class, args);
    }

    @RequestMapping("/")
    public String home(Model model) {
        StringBuilder sb = new StringBuilder();

        sb.append("You are at the start of this whole app.");

        return sb.toString();
    }

    @RequestMapping("/login")
    public String login(Model model) {
        StringBuilder sb = new StringBuilder();

        sb.append("What? You wanna log in to this or something? Good luck with that!");

        return sb.toString();
    }

}
