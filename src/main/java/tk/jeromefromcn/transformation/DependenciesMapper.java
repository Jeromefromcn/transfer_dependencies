package tk.jeromefromcn.transformation;

import java.util.Set;

import java.util.Map;

/**
 * ���ݴ����Set������maven��׼��lib�ֿ⣬������ant������gradle������map
 * 
 * @author Jerome
 *
 */
public interface DependenciesMapper {
	/**
	 * ��ant����ת����gradle��������
	 * 
	 * @param antDependenciesSet
	 * @return
	 */
	Map<String, String> mapDependencies(Set<String> antDependenciesSet);
}
