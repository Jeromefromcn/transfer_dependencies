package tk.jeromefromcn.transformation;

import java.io.File;
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
	 * @param buildFileArtifactsMap
	 */

	void generateGradleScript(Map<File, List<Artifact>> buildFileArtifactsMap);
}
