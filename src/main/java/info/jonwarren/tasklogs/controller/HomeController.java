package info.jonwarren.tasklogs.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController extends TasklogsController {
	
	public HomeController() {
		super();
	}

	@RequestMapping("/")
	public String home(Model model) {
		return "You are home... or... well... at the start of this whole app, at least. You might be at work for all I know.";
	}

	@RequestMapping("/login")
	public String login(Model model) {
		return "What? You wanna log in to this or something? Good luck with that!";
	}

}
