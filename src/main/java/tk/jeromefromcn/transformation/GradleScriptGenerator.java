package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Map;

/**
 * ����stariboss�����Ŀ¼��������Ӧ��ϵ������gradle�ű�
 * 
 * @author Jerome
 *
 */
public interface GradleScriptGenerator {

	/**
	 * ����build�ļ�λ�ú�����ӳ���ϵ������gradle�ű�
	 * 
	 * @param buildFilePaths
	 * @param dependenciesMap
	 */
	void generateGradleScript(List<String> buildFilePaths,
			Map<String, String> dependenciesMap);
}
