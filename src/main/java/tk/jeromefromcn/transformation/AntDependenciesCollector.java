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
	 * ��ȡstariboss����Ŀ¼�����й��̵�����������Set��ʽ�ַ�������
	 * 
	 * @return
	 */
	Set<String> collectDependencies();

	void setBasePath(String basePath);

	List<String> getBuildFilePaths();
}
