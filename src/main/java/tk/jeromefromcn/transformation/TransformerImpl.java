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

	@Override
	public void transferDependencies() {
		Map<String, List<Artifact>> buildFileArtifactsMap = antBuildInfoCollector
				.collectBuildFileAndArtifactsMap(basePath);

		gradleScriptGenerator.generateGradleScript(buildFileArtifactsMap);
	}

	@Override
	public void transferArtifacts() {
		Set<String> dependenciesSet = antBuildInfoCollector
				.collectThirdDependencies(basePath);

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
