package tk.jeromefromcn.transformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.Map;

import org.springframework.stereotype.Component;

@Component
public class JarFilesMoverImpl implements JarFilesMover {

	@Override
	public void moveJarFiles(String m2Repo,
			Map<String, Artifact> dependenciesMap) {
		System.out.println(dependenciesMap.size());
		File m2RepoDir = new File(m2Repo);
		if (m2RepoDir.isDirectory()) {
			File groupIdDir = new File(m2RepoDir.getParent() + "/"
					+ Constant.STAREXT);
			if (!groupIdDir.exists()) {
				groupIdDir.mkdir();
			}
			for (String depen : dependenciesMap.keySet()) {
				System.out.println(depen);
				String artifactId = depen.substring(depen.lastIndexOf('/') + 1,
						depen.length() - 4);
				File artifactPath = new File(groupIdDir.getPath() + "/"
						+ artifactId + "/" + Constant.VERSION);
				if (!artifactPath.exists()) {
					artifactPath.mkdirs();
					String jarFilePath = depen.replace("${M2_REPO}", m2Repo);
					File targetFile = new File(artifactPath + "/" + artifactId
							+ "-" + Constant.VERSION + ".jar");
					if (!targetFile.exists()) {
						try {
							fileChannelCopy(new File(jarFilePath), targetFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

			}
		} else {
			System.out.println("�����Ĳֿ�Ŀ¼������");
		}

	}

	/*
	 * 
	 * ʹ���ļ�ͨ���ķ�ʽ�����ļ�
	 * 
	 * @param s Դ�ļ�
	 * 
	 * @param t ���Ƶ������ļ�
	 */

	private void fileChannelCopy(File sourceFile, File targetFile) {

		FileInputStream fi = null;

		FileOutputStream fo = null;

		FileChannel in = null;

		FileChannel out = null;

		try {

			fi = new FileInputStream(sourceFile);

			fo = new FileOutputStream(targetFile);

			in = fi.getChannel();// �õ���Ӧ���ļ�ͨ��

			out = fo.getChannel();// �õ���Ӧ���ļ�ͨ��

			in.transferTo(0, in.size(), out);// ��������ͨ�������Ҵ�inͨ����ȡ��Ȼ��д��outͨ��

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				fi.close();

				in.close();

				fo.close();

				out.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

	}

}
