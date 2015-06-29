package tk.jeromefromcn.transformation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
public class Application {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(
				Application.class);
		Transformer transformer = context.getBean(TransformerImpl.class);
		// transformer
		// .transferDependencies("/Users/Jerome/Documents/gitlab/stariboss-os-demo");
		transformer.transferArtifacts(
				"/Users/Jerome/Documents/gitlab/stariboss-os-demo",
				"/Users/Jerome/Documents/gitlab/tool/m2/repository");
	}
}
