package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * ant脚本构建信息提取器
 * 
 * @author Jerome
 *
 */
public interface AntBuildInfoCollector {

	/**
	 * 获取内部依赖
	 * 
	 * @return
	 */
	Set<String> collectInternalDependencies(String basePath);

	/**
	 * 获取stariboss代码目录下所有工程的第三方依赖，返回Set格式字符串集合
	 * 
	 * @return
	 */
	Set<String> collectThirdDependencies(String basePath);

	/**
	 * 获取构建文件与artifacts之间的关系
	 * 
	 * @return
	 */
	Map<String, List<Artifact>> collectBuildFileAndArtifactsMap(String basePath);

}
