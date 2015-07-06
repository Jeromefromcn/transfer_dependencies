package tk.jeromefromcn.transformation;

/**
 * ���ε��������ռ�����Ӧ��ϵ���ɡ�����jar��·�����ɡ�gradle�ű����ɽӿڣ����ant�ű���gradle�ű���ת��
 * 
 * @author jerome
 *
 */

public interface Transformer {

	void setBasePath(String basePath);

	void setRepoPath(String repoPath);

	/**
	 * ����stariboss�����Ŀ¼����ant����ת����gradle����������gradle�ű�
	 * 
	 */
	void transferDependencies();

	/**
	 * ����stariboss�����Ŀ¼�ͱ��زֿ��Ŀ¼����������jar������maven�ֿ��ʽ��������
	 * 
	 */
	void transferArtifacts();

}
