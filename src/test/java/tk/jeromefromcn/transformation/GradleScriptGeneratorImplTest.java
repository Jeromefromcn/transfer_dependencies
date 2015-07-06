package tk.jeromefromcn.transformation;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class GradleScriptGeneratorImplTest {

	private GradleScriptGenerator tester = new GradleScriptGeneratorImpl();

	private AntBuildInfoCollector antBuildInfoCollector = new AntBuildInfoCollectorImpl();

	private String basePath = "src/test/resources/tk/jeromefromcn/transformation/firstlevel";

	@Test
	public void testGenerateGradleScript() {
		Map<File, List<Artifact>> buildFileArtifactsMap = antBuildInfoCollector
				.collectBuildFileAndArtifactsMap(basePath);
		tester.generateGradleScript(buildFileArtifactsMap);
		List<Boolean> compareResultList = new ArrayList<Boolean>();
		for (File file : buildFileArtifactsMap.keySet()) {
			File gradleBuildFile = new File(file.getParent() + "/build.gradle");
			File compareBuildFile = new File(file.getParent()
					+ "/compare_build.gradle");

			try {
				BufferedReader buildFileReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(
								gradleBuildFile)));

				String line = buildFileReader.readLine();
				String buildFileContent = "";
				while (line != null) {
					buildFileContent += line + "\n";
					line = buildFileReader.readLine(); // 一次读入一行数据
				}
				buildFileReader.close();

				BufferedReader compareFileReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(
								compareBuildFile)));

				String line2 = compareFileReader.readLine();
				String compareFileContent = "";
				while (line2 != null) {
					compareFileContent += line2 + "\n";
					line2 = compareFileReader.readLine(); // 一次读入一行数据
				}
				compareFileReader.close();

				compareResultList.add(buildFileContent
						.equals(compareFileContent));
			} catch (IOException e) {
				e.printStackTrace();
			}
			gradleBuildFile.deleteOnExit();

		}

		for (Boolean b : compareResultList) {
			assertEquals(b, true);
		}
	}

}
