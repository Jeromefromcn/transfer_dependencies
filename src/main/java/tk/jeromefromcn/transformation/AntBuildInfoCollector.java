package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * ant脚本构建信息提取器
 * 
 * @author Jerome
 *
 */
public interface AntBuildInfoCollector {

	/**
	 * 获取stariboss代码目录下所有工程的第三方依赖，返回ant格式依赖与artifact的map
	 * 
	 * @return
	 */
	Map<String, Artifact> collectThirdDependencies(String basePath);

	/**
	 * 获取构建文件与artifacts之间的关系
	 * 
	 * @return
	 */
	Map<File, List<Artifact>> collectBuildFileAndArtifactsMap(String basePath);

}
