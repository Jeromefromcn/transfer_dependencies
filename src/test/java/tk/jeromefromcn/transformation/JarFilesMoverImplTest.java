package tk.jeromefromcn.transformation;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class JarFilesMoverImplTest {

	private JarFilesMover tester = new JarFilesMoverImpl();

	@Test
	public void testMoveJarFiles() {

		Set<String> dependenciesSet = new HashSet<String>();
		dependenciesSet.add("${M2_REPO}/wrapper/gradle-wrapper.jar");
		tester.moveJarFiles("gradle", dependenciesSet);
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