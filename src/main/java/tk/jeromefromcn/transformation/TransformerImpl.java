package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformerImpl implements Transformer {

	@Autowired
	private AntBuildInfoCollector antBuildInfoCollector;

	@Autowired
	private DependenciesMapper dependenciesMapper;

	@Autowired
	private JarFilesMover jarFilesMover;

	@Autowired
	private GradleScriptGenerator gradleScriptGenerator;

	private String basePath;

	private String repoPath;

	private Set<String> antDepenPathsSet;

	private Map<String, List<Artifact>> buildFileArtifactMap;

	@Override
	public void transferDependencies() {
		antBuildInfoCollector.setBasePath(basePath);
		Set<String> dependenciesSet = antBuildInfoCollector
				.collectThirdDependencies();
		System.out.println("--------------------------------------------");
		Map<String, String> dependenciesMap = dependenciesMapper
				.mapDependencies(dependenciesSet);
		System.out.println("--------------------------------------------");
		gradleScriptGenerator.generateGradleScript(
				antBuildInfoCollector.collectBuildFilePaths(), dependenciesMap);
	}

	@Override
	public void transferArtifacts() {
		antBuildInfoCollector.setBasePath(basePath);
		Set<String> dependenciesSet = antBuildInfoCollector
				.collectThirdDependencies();
		jarFilesMover.moveJarFiles(repoPath, dependenciesSet);
	}

	@Override
	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	@Override
	public void setRepoPath(String repoPath) {
		this.repoPath = repoPath;
	}

}
