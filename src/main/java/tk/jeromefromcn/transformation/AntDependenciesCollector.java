package tk.jeromefromcn.transformation;

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
	 */
	Set<String> collectDependencies();

	void setBasePath(String basePath);
}
