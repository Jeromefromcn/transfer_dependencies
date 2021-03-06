package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * 根据stariboss代码跟目录和依赖对应关系，生成gradle脚本
 * 
 * @author Jerome
 *
 */
public interface GradleScriptGenerator {

	/**
	 * 根据build文件位置和依赖映射关系，生成gradle脚本
	 * 
	 * @param buildFileArtifactsMap
	 */

	void generateGradleScript(Map<File, List<Artifact>> buildFileArtifactsMap);
}
