package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JarFilesMoverImpl implements JarFilesMover {

	private static final String STAREXT = "/starext";

	@Override
	public void moveJarFiles(String m2Repo, Map<String, String> dependenciesMap) {
		File m2RepoDir = new File(m2Repo);
		if (m2RepoDir.isDirectory()) {
			File groupIdDir = new File(m2RepoDir.getPath() + STAREXT);
			System.out.println(groupIdDir.getAbsolutePath());
			if (!groupIdDir.exists()) {
				groupIdDir.mkdir();
			}
			for (String key : dependenciesMap.keySet()) {
				
			}
		} else {
			System.out.println("给定的仓库目录不存在");
		}

	}

}
