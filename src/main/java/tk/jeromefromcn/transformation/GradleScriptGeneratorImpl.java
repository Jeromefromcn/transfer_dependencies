package tk.jeromefromcn.transformation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class GradleScriptGeneratorImpl implements GradleScriptGenerator {

	@Override
	public void generateGradleScript(
			Map<File, List<Artifact>> buildFileArtifactsMap) {
		File baseFile = new File(
				"src/main/resources/tk/jeromefromcn/transformation/base_build.gradle");
		for (File file : buildFileArtifactsMap.keySet()) {
			try {
				// 读取基础构建文件
				BufferedReader baseFileReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(baseFile)));

				String line = baseFileReader.readLine();
				String baseFileContent = "";
				while (line != null) {
					baseFileContent += line + "\n";
					line = baseFileReader.readLine(); // 一次读入一行数据
				}
				baseFileReader.close();
				// 替换artifactId为工程的名称
				baseFileContent = baseFileContent.replace(
						Constant.ARTIFACTID_PLACEHOLDER, file.getParentFile()
								.getName());
				// 替换依赖为gradle格式依赖
				baseFileContent = baseFileContent.replace(
						Constant.DEPENDENCIES_PLACEHOLDER,
						getGraldeDepens(buildFileArtifactsMap.get(file)));
				File gradleBuildFile = new File(file.getParent() + "/"
						+ Constant.GRADLE_BUILD_FILE_NAME);
				gradleBuildFile.deleteOnExit();
				gradleBuildFile.createNewFile();
				BufferedWriter writer = new BufferedWriter(new FileWriter(
						gradleBuildFile));
				writer.write(baseFileContent);
				writer.flush(); // 把缓存区内容压入文件
				writer.close(); // 最后记得关闭文件
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * 根据依赖的列表生成gradle格式的依赖
	 */
	private String getGraldeDepens(List<Artifact> list) {
		String gradleDepensString = "";
		for (Artifact artifact : list) {
			gradleDepensString += "\t" + artifact.getGradleFormat() + "\n";
		}
		return gradleDepensString;
	}

}
