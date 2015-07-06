package tk.jeromefromcn.transformation;

/**
 * 依次调用依赖收集、对应关系生成、依赖jar包路径生成、gradle脚本生成接口，完成ant脚本到gradle脚本的转换
 * 
 * @author jerome
 *
 */

public interface Transformer {

	void setBasePath(String basePath);

	void setRepoPath(String repoPath);

	/**
	 * 根据stariboss代码根目录，将ant依赖转换成gradle依赖并生成gradle脚本
	 * 
	 */
	void transferDependencies();

	/**
	 * 根据stariboss代码根目录和本地仓库根目录，将依赖的jar包按照maven仓库格式重新排列
	 * 
	 */
	void transferArtifacts();

}
