package tk.jeromefromcn.transformation;

import java.util.Set;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * ���ݴ����Set������maven��׼��lib�ֿ⣬������ant������gradle������map
 * 
 * @author Jerome
 *
 */
public interface DependenciesMapper {
	Map<String, String> mapDependencies(Set<String> antDependenciesSet);
}
