package tk.jeromefromcn.transformation;

import java.util.Map;

/**
 * 根据依赖的groupid、artifactid、version生成新的存放路径并移动到新路径
 * 
 * @author jerom
 *
 */
public interface JarFilesMover {
	/**
	 * 移动jar文件到maven标准路径下
	 * 
	 * @param m2Repo
	 * @param dependenciesMap
	 */

	void moveJarFiles(String m2Repo, Map<String, String> dependenciesMap);
}
