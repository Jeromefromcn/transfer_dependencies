package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * ant�ű�������Ϣ��ȡ��
 * 
 * @author Jerome
 *
 */
public interface AntBuildInfoCollector {

	/**
	 * ��ȡstariboss����Ŀ¼�����й��̵ĵ���������������ant��ʽ������artifact��map
	 * 
	 * @return
	 */
	Map<String, Artifact> collectThirdDependencies(String basePath);

	/**
	 * ��ȡ�����ļ���artifacts֮��Ĺ�ϵ
	 * 
	 * @return
	 */
	Map<File, List<Artifact>> collectBuildFileAndArtifactsMap(String basePath);

}
