package info.jonwarren.tasklogs.controller;

import javax.annotation.Resource;

import info.jonwarren.tasklogs.config.ApplicationSetup;

public class TasklogsController {

	@Resource
	protected ApplicationSetup applicationSetup;

	public TasklogsController() {
	}

}
