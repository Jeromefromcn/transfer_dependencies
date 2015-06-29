package tk.jeromefromcn.transformation;

import java.util.Set;

import java.util.Map;

/**
 * 根据传入的Set，生成maven标准的lib仓库，并生成ant依赖与gradle依赖的map
 * 
 * @author Jerome
 *
 */
public interface DependenciesMapper {
	/**
	 * 将ant依赖转换成gradle类型依赖，groupid固定为starext，artifactid取jar文件的名字，version固定为1.0
	 * 
	 * @param antDependenciesSet
	 * @return
	 */
	Map<String, String> mapDependencies(Set<String> antDependenciesSet);
}
