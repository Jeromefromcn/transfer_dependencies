package tk.jeromefromcn.transformation;

/**
 * 依次调用依赖收集、对应关系生成、依赖jar包路径生成、gradle脚本生成接口，完成ant脚本到gradle脚本的转换
 * 
 * @author jerom
 *
 */

public interface Transformer {

	void transfer();

	void transferWithRepositoryGenerated();

	void setBasePath(String basePath);

}
