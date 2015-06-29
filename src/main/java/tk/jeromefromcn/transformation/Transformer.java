package tk.jeromefromcn.transformation;

/**
 * ���ε��������ռ�����Ӧ��ϵ���ɡ�����jar��·�����ɡ�gradle�ű����ɽӿڣ����ant�ű���gradle�ű���ת��
 * 
 * @author jerome
 *
 */

public interface Transformer {

	/**
	 * ����stariboss�����Ŀ¼����ant����ת����gradle����������gradle�ű�
	 * 
	 * @param basePath
	 */
	void transferDependencies(String basePath);

	/**
	 * ����stariboss�����Ŀ¼�ͱ��زֿ��Ŀ¼����������jar������maven�ֿ��ʽ��������
	 * 
	 * @param basePath
	 * @param repoPath
	 */
	void transferArtifacts(String basePath, String repoPath);

}
