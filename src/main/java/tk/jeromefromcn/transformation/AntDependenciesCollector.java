package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Set;

/**
 * 根据给定的stariboss代码根目录，提取所有子目录下build.xml中的依赖
 * 
 * @author Jerome
 *
 */
public interface AntDependenciesCollector {
	/**
	 * 获取stariboss代码目录下所有工程的第三方依赖，返回Set格式字符串集合
	 * 
	 * @return
	 */
	Set<String> collectThirdDependencies();
	


	void setBasePath(String basePath);

	List<String> getBuildFilePaths();
}
