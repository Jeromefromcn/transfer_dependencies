package tk.jeromefromcn.transformation;

/**
 * 根据依赖的groupid、artifactid、version生成新的存放路径并移动到新路径
 * 
 * @author jerom
 *
 */
public interface JarFilesMover {
	void moveJarFiles();
}
