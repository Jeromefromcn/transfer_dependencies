package tk.jeromefromcn.transformation;

import java.util.Set;

/**
 * 根据给定的stariboss代码根目录，提取所有子目录下build.xml中的依赖
 * 
 * @author Jerome
 *
 */
public interface AntDependenciesCollector {
	/**
	 * 获取stariboss代码目录下所有工程的依赖，返回Set格式字符串集合
	 */
	Set<String> collectDependencies();

	void setBasePath(String basePath);
}
