package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

@Component
public class AntBuildInfoCollectorImpl implements AntBuildInfoCollector {

	private static final String BUILD_FILE_NAME = "build.xml";
	private static final String PATH_ELEMENT = "path";
	private static final String PATHELEMENT_ELEMENT = "pathelement";
	private static final String LOCATION_ATTRIBUTE = "location";

	public Set<String> collectThirdDependencies(String basePath) {

		List<String> buildFilePaths = getBuildFilePaths(basePath);

		return parseBuildFiles(buildFilePaths);
	}

	/*
	 * �������е�build.xml�ļ�����ȡ������������Ϣ
	 */
	private Set<String> parseBuildFiles(List<String> buildFilePaths) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Set<String> dependenciesSet = new HashSet<String>();
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			for (String buildFilePath : buildFilePaths) {
				Document doc = dBuilder.parse(buildFilePath);
				if (doc.hasChildNodes()) {
					Node projectNode = doc.getChildNodes().item(0);
					if (projectNode.hasChildNodes()) {
						NodeList nodeListInFirstLevel = projectNode
								.getChildNodes();
						for (int count = 0; count < nodeListInFirstLevel
								.getLength(); count++) {
							Node nodeInSecondLevel = nodeListInFirstLevel
									.item(count);
							if (nodeInSecondLevel.getNodeType() == Node.ELEMENT_NODE
									&& nodeInSecondLevel.getNodeName().equals(
											PATH_ELEMENT)
									&& nodeInSecondLevel.hasChildNodes()) {
								NodeList nodeListInSecondLevel = nodeInSecondLevel
										.getChildNodes();
								for (int c = 0; c < nodeListInSecondLevel
										.getLength(); c++) {
									Node nodeInThirdLevel = nodeListInSecondLevel
											.item(c);
									if (nodeInThirdLevel.getNodeType() == Node.ELEMENT_NODE
											&& nodeInThirdLevel
													.getNodeName()
													.equals(PATHELEMENT_ELEMENT)
											&& nodeInThirdLevel.hasAttributes()) {
										NamedNodeMap nodeMap = nodeInThirdLevel
												.getAttributes();
										for (int i = 0; i < nodeMap.getLength(); i++) {

											Node attr = nodeMap.item(i);
											if (attr.getNodeName().equals(
													LOCATION_ATTRIBUTE)) {
												dependenciesSet.add(attr
														.getNodeValue());
											}

										}
									}
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dependenciesSet;
	}

	/*
	 * �������build.xml��·��
	 */
	private List<String> getBuildFilePaths(String path) {
		List<String> buildFilePaths = new ArrayList<String>();
		File baseDir = new File(path);
		if (!baseDir.isFile()) {
			for (File fileInSecondLevel : baseDir.listFiles()) {
				if (fileInSecondLevel.isDirectory()) {
					for (File fileInThirdLevel : fileInSecondLevel.listFiles()) {
						if (fileInThirdLevel.isDirectory()) {
							for (File fileInFourthLevel : fileInThirdLevel
									.listFiles()) {
								if (fileInFourthLevel.isFile()
										&& fileInFourthLevel.getName().equals(
												BUILD_FILE_NAME)) {
									buildFilePaths.add(fileInFourthLevel
											.getAbsolutePath());
								}
							}
						}
					}
				}
			}
		}
		return buildFilePaths;
	}

	@Override
	public Set<String> collectInternalDependencies(String basePath) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, List<Artifact>> collectBuildFileAndArtifactsMap(
			String basePath) {
		// TODO Auto-generated method stub
		return null;
	}

}
