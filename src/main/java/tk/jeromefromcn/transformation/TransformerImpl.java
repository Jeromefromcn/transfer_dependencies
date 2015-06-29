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
	public void transferDependencies(String basePath) {
		antDependenciesCollector.setBasePath(basePath);
		Set<String> dependenciesSet = antDependenciesCollector
				.collectThirdDependencies();
		// for (String string : dependenciesSet) {
		// System.out.println(string);
		// }
		System.out.println("--------------------------------------------");
		Map<String, String> dependenciesMap = dependenciesMapper
				.mapDependencies(dependenciesSet);
		System.out.println("--------------------------------------------");
		gradleScriptGenerator.generateGradleScript(
				antDependenciesCollector.getBuildFilePaths(), dependenciesMap);
	}

	@Override
	public void transferArtifacts(String basePath, String repoPath) {
		antDependenciesCollector.setBasePath(basePath);
		Set<String> dependenciesSet = antDependenciesCollector
				.collectThirdDependencies();
		for (String string : dependenciesSet) {
			System.out.println(string);
		}
		System.out.println("--------------------------------------------");
		jarFilesMover.moveJarFiles(repoPath, dependenciesSet);
	}

}
