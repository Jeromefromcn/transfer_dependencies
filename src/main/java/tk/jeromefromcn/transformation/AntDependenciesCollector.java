package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Set;

/**
 * ���ݸ�����stariboss�����Ŀ¼����ȡ������Ŀ¼��build.xml�е�����
 * 
 * @author Jerome
 *
 */
public interface AntDependenciesCollector {
	/**
	 * ��ȡstariboss����Ŀ¼�����й��̵ĵ���������������Set��ʽ�ַ�������
	 * 
	 * @return
	 */
	Set<String> collectThirdDependencies();
	


	void setBasePath(String basePath);

	List<String> getBuildFilePaths();
}
