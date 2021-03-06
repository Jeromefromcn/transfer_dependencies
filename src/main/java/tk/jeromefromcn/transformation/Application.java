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

		transformer.setBasePath("D:\\GitLab\\stariboss-os-demo");
		transformer.setRepoPath("D:\\resources\\tool\\.m2\\repository");
//		transformer.transferDependencies();
		transformer.transferArtifacts();
	}
}
// 海外accept、callcenteragent jms包没有
// haiwai-proxy xfire 补丁包没有