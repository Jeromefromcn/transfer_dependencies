package tk.jeromefromcn.transformation;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformerImpl implements Transformer {

	@Autowired
	private AntDependenciesCollector antDependenciesCollector;

	@Autowired
	private DependenciesMapper dependenciesMapper;

	@Autowired
	private JarFilesMover jarFilesMover;

	@Autowired
	private GradleScriptGenerator gradleScriptGenerator;

	@Override
	public void transfer() {
		// TODO Auto-generated method stub
		Set<String> dependenciesSet = antDependenciesCollector
				.collectDependencies();
		for (String string : dependenciesSet) {
			System.out.println(string);
		}
		System.out.println("--------------------------------------------");
		Map<String, String> dependenciesMap = dependenciesMapper
				.mapDependencies(dependenciesSet);
		System.out.println("--------------------------------------------");
		gradleScriptGenerator.generateGradleScript(
				antDependenciesCollector.getBuildFilePaths(), dependenciesMap);
	}

	@Override
	public void setBasePath(String basePath) {
		antDependenciesCollector.setBasePath(basePath);
	}

	@Override
	public void transferWithRepositoryGenerated() {
		Set<String> dependenciesSet = antDependenciesCollector
				.collectDependencies();
		for (String string : dependenciesSet) {
			System.out.println(string);
		}
		System.out.println("--------------------------------------------");
		Map<String, String> dependenciesMap = dependenciesMapper
				.mapDependencies(dependenciesSet);
		System.out.println("--------------------------------------------");
		jarFilesMover.moveJarFiles("D:/GitLab/tool/.m2/repository",
				dependenciesMap);
	}

}
