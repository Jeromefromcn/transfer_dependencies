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
	 * ��ant����ת����gradle����������groupid�̶�Ϊstarext��artifactidȡjar�ļ������֣�version�̶�Ϊ1.0
	 * 
	 * @param antDependenciesSet
	 * @return
	 */
	Map<String, String> mapDependencies(Set<String> antDependenciesSet);
}
