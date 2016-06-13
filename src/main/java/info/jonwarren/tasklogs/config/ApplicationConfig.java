package info.jonwarren.tasklogs.config;

import java.io.IOException;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.YamlPropertySourceLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

@Configuration
@EnableAutoConfiguration
@ComponentScan
@EnableConfigurationProperties(ApplicationSetup.class)
public class ApplicationConfig {
	
	private final static String CONFIG_FILENAME = "application.yml";

	@Bean
	public PropertySource<?> yamlPropertySourceLoader() throws IOException {
		YamlPropertySourceLoader loader = new YamlPropertySourceLoader();
		Resource yamlFile = new ClassPathResource(CONFIG_FILENAME);
		PropertySource<?> propSource = loader.load(CONFIG_FILENAME, yamlFile, "default");
		return propSource;
	}

}
