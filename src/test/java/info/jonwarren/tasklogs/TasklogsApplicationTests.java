package info.jonwarren.tasklogs;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import info.jonwarren.tasklogs.TasklogsApplication;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = TasklogsApplication.class)
@WebAppConfiguration
public class TasklogsApplicationTests {

	@Test
	public void contextLoads() {
	}

}
