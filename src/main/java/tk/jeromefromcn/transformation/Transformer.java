package tk.jeromefromcn.transformation;

/**
 * ���ε��������ռ�����Ӧ��ϵ���ɡ�����jar��·�����ɡ�gradle�ű����ɽӿڣ����ant�ű���gradle�ű���ת��
 * 
 * @author jerom
 *
 */

public interface Transformer {

	void transfer();

	void transferWithRepositoryGenerated();

	void setBasePath(String basePath);

}
