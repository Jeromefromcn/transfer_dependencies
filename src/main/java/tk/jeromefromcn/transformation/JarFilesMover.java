package tk.jeromefromcn.transformation;

import java.util.Set;

/**
 * ����������groupid��artifactid��version�����µĴ��·�����ƶ�����·��
 * 
 * @author jerom
 *
 */
public interface JarFilesMover {
	/**
	 * �ƶ�jar�ļ���maven��׼·����
	 * 
	 * @param m2Repo
	 * @param dependenciesMap
	 */

	void moveJarFiles(String m2Repo, Set<String> dependenciesSet);
}
