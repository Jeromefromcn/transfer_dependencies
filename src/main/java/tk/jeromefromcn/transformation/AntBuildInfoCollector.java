package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Set;

/**
 * ant�ű�������Ϣ��ȡ��
 * 
 * @author Jerome
 *
 */
public interface AntBuildInfoCollector {

	/**
	 * ��ȡ�ڲ�����
	 * 
	 * @return
	 */
	Set<String> collectInternalDependencies();

	/**
	 * ��ȡstariboss����Ŀ¼�����й��̵ĵ���������������Set��ʽ�ַ�������
	 * 
	 * @return
	 */
	Set<String> collectThirdDependencies();

	void setBasePath(String basePath);

	List<String> collectBuildFilePaths();
}
