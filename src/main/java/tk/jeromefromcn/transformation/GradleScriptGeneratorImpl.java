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
				// ��ȡ���������ļ�
				BufferedReader baseFileReader = new BufferedReader(
						new InputStreamReader(new FileInputStream(baseFile)));

				String line = baseFileReader.readLine();
				String baseFileContent = "";
				while (line != null) {
					baseFileContent += line + "\n";
					line = baseFileReader.readLine(); // һ�ζ���һ������
				}
				baseFileReader.close();
				// �滻artifactIdΪ���̵�����
				baseFileContent = baseFileContent.replace(
						Constant.ARTIFACTID_PLACEHOLDER, file.getParentFile()
								.getName());
				// �滻����Ϊgradle��ʽ����
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
				writer.flush(); // �ѻ���������ѹ���ļ�
				writer.close(); // ���ǵùر��ļ�
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	/*
	 * �����������б�����gradle��ʽ������
	 */
	private String getGraldeDepens(List<Artifact> list) {
		String gradleDepensString = "";
		for (Artifact artifact : list) {
			gradleDepensString += "\t" + artifact.getGradleFormat() + "\n";
		}
		return gradleDepensString;
	}

}
