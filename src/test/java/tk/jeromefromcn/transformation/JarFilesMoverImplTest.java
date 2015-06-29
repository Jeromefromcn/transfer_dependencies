package tk.jeromefromcn.transformation;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

public class JarFilesMoverImplTest {

	private JarFilesMover tester = new JarFilesMoverImpl();

	@Test
	public void testMoveJarFiles() {

		Map<String, String> dependenciesMap = new HashMap<String, String>();
		dependenciesMap.put("${M2_REPO}/wrapper/gradle-wrapper.jar",
				"compile 'starext:gradle-wrapper:1.0'");
		tester.moveJarFiles("gradle", dependenciesMap);
		boolean isExsitence = new File(
				"gradle/starext/gradle-wrapper/1.0/gradle-wrapper-1.0.jar")
				.exists();
		deleteFile(new File("gradle/starext"));
		assertEquals(isExsitence, true);

	}

	private void deleteFile(File file) {
		if (file.exists()) {
			if (file.isFile()) {
				file.delete();
			} else if (file.isDirectory()) {
				File files[] = file.listFiles();
				for (int i = 0; i < files.length; i++) {
					this.deleteFile(files[i]);
				}
			}
			file.delete();
		}
	}

}