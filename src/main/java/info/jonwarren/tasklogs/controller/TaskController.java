package info.jonwarren.tasklogs.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController extends TasklogsController {

	public TaskController() {
		super();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {

		StringBuilder result = new StringBuilder();

		result.append("applicationSetup=[").append(applicationSetup).append("]");

		return result.toString();
	}

}
