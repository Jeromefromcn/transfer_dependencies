package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TransformerImpl implements Transformer {

	@Autowired
	private AntBuildInfoCollector antBuildInfoCollector;

	@Autowired
	private JarFilesMover jarFilesMover;

	@Autowired
	private GradleScriptGenerator gradleScriptGenerator;

	private String basePath;

	private String repoPath;

	@Override
	public void transferDependencies() {
		Map<File, List<Artifact>> buildFileArtifactsMap = antBuildInfoCollector
				.collectBuildFileAndArtifactsMap(basePath);

		gradleScriptGenerator.generateGradleScript(buildFileArtifactsMap);
	}

	@Override
	public void transferArtifacts() {
		Map<String, Artifact> dependenciesMap = antBuildInfoCollector
				.collectThirdDependencies(basePath);

		jarFilesMover.moveJarFiles(repoPath, dependenciesMap);
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
