package tk.jeromefromcn.transformation;

import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 根据传入的Set，生成maven标准的lib仓库，并生成ant依赖与gradle依赖的map
 * 
 * @author Jerome
 *
 */
public interface DependenciesMapper {
	Map<String, String> mapDependencies(Set<String> antDependenciesSet);
}
