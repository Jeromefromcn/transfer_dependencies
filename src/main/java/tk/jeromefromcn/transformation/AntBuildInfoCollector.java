package tk.jeromefromcn.transformation;

import java.util.List;
import java.util.Map;
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
	Set<String> collectInternalDependencies(String basePath);

	/**
	 * ��ȡstariboss����Ŀ¼�����й��̵ĵ���������������Set��ʽ�ַ�������
	 * 
	 * @return
	 */
	Set<String> collectThirdDependencies(String basePath);

	/**
	 * ��ȡ�����ļ���artifacts֮��Ĺ�ϵ
	 * 
	 * @return
	 */
	Map<String, List<Artifact>> collectBuildFileAndArtifactsMap(String basePath);

}
