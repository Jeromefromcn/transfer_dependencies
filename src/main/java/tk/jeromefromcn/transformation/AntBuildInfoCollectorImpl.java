package tk.jeromefromcn.transformation;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private static final String FILESET_ELEMENT = "fileset";
	private static final String INCLUDE_ELEMENT = "include";
	private static final String NAME_ATTRIBUTE = "name";

	private String basePath;

	public String getBasePath() {
		return basePath;
	}

	public void setBasePath(String basePath) {
		this.basePath = basePath;
	}

	public AntBuildInfoCollectorImpl(String basePath) {
		this.basePath = basePath;
	}

	public AntBuildInfoCollectorImpl() {
	}

	@Override
	public Map<String, Artifact> collectThirdDependencies(String basePath) {

		List<File> buildFiles = getBuildFiles(basePath);

		return getThirdDepensMap(buildFiles);
	}

	/*
	 * 解析所有build.xml文件，获取第三方依赖
	 */
	private Map<String, Artifact> getThirdDepensMap(List<File> buildFiles) {
		Map<String, Artifact> dependenciesMap = new HashMap<String, Artifact>();
		for (File buildFile : buildFiles) {
			Node nodeInSecondLevel = getPathElementNodes(buildFile);
			NodeList nodeListInSecondLevel = nodeInSecondLevel.getChildNodes();
			for (int c = 0; c < nodeListInSecondLevel.getLength(); c++) {
				Node nodeInThirdLevel = nodeListInSecondLevel.item(c);
				if (nodeInThirdLevel.getNodeType() == Node.ELEMENT_NODE
						&& nodeInThirdLevel.getNodeName().equals(
								PATHELEMENT_ELEMENT)
						&& nodeInThirdLevel.hasAttributes()) {
					NamedNodeMap nodeMap = nodeInThirdLevel.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node attr = nodeMap.item(i);
						if (attr.getNodeName().equals(LOCATION_ATTRIBUTE)) {
							dependenciesMap.put(attr.getNodeValue(),
									generateArtifactFromThirdDepen(attr
											.getNodeValue()));
						}

					}
				}
			}
		}

		return dependenciesMap;
	}

	/*
	 * 解析build.xml文件，获取path节点
	 */
	private Node getPathElementNodes(File buildFile) {
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		Node node = null;
		try {
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(buildFile);
			if (doc.hasChildNodes()) {
				Node projectNode = doc.getChildNodes().item(0);
				if (projectNode.hasChildNodes()) {
					NodeList nodeListInFirstLevel = projectNode.getChildNodes();
					for (int count = 0; count < nodeListInFirstLevel
							.getLength(); count++) {
						Node nodeInSecondLevel = nodeListInFirstLevel
								.item(count);
						if (nodeInSecondLevel.getNodeType() == Node.ELEMENT_NODE
								&& nodeInSecondLevel.getNodeName().equals(
										PATH_ELEMENT)
								&& nodeInSecondLevel.hasChildNodes()) {
							node = nodeInSecondLevel;
						}
					}
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return node;

	}

	/*
	 * 根据第三方依赖生成Artifact
	 */
	private Artifact generateArtifactFromThirdDepen(String thirdDepen) {
		String artifactId = thirdDepen.substring(
				thirdDepen.lastIndexOf('/') + 1, thirdDepen.length() - 4);

		return new Artifact(Constant.STAREXT, artifactId, Constant.VERSION);
	}

	/*
	 * 获得所有build.xml的路径
	 */
	private List<File> getBuildFiles(String path) {
		List<File> buildFiles = new ArrayList<File>();
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
									buildFiles.add(fileInFourthLevel);
								}
							}
						}
					}
				}
			}
		}
		return buildFiles;
	}

	@Override
	public Map<File, List<Artifact>> collectBuildFileAndArtifactsMap(
			String basePath) {
		List<File> buildFiles = getBuildFiles(basePath);

		return getBuildFileAndArtifactsMap(buildFiles);

	}

	/*
	 * 解析所有build.xml文件，获取所有依赖,包括第三方依赖和内部依赖
	 */
	private Map<File, List<Artifact>> getBuildFileAndArtifactsMap(
			List<File> buildFiles) {
		Map<File, List<Artifact>> map = new HashMap<File, List<Artifact>>();
		for (File buildFile : buildFiles) {
			List<Artifact> artfacts = new ArrayList<Artifact>();
			Node nodeInSecondLevel = getPathElementNodes(buildFile);
			NodeList nodeListInSecondLevel = nodeInSecondLevel.getChildNodes();
			for (int c = 0; c < nodeListInSecondLevel.getLength(); c++) {
				Node nodeInThirdLevel = nodeListInSecondLevel.item(c);
				if (nodeInThirdLevel.getNodeType() == Node.ELEMENT_NODE
						&& nodeInThirdLevel.getNodeName().equals(
								FILESET_ELEMENT)
						&& nodeInThirdLevel.hasChildNodes()) {
					NodeList nodeListInThirdLevel = nodeInThirdLevel
							.getChildNodes();
					for (int cc = 0; cc < nodeListInThirdLevel.getLength(); cc++) {
						Node nodeInFourthLevel = nodeListInThirdLevel.item(cc);
						if (nodeInFourthLevel.getNodeType() == Node.ELEMENT_NODE
								&& nodeInFourthLevel.getNodeName().equals(
										INCLUDE_ELEMENT)
								&& nodeInFourthLevel.hasAttributes()) {
							NamedNodeMap nodeMap = nodeInFourthLevel
									.getAttributes();
							for (int i = 0; i < nodeMap.getLength(); i++) {
								Node attr = nodeMap.item(i);
								if (attr.getNodeName().equals(NAME_ATTRIBUTE)) {
									artfacts.add(generateArtifactFromInternalDepen(attr
											.getNodeValue()));
								}
							}
						}
					}
				} else if (nodeInThirdLevel.getNodeType() == Node.ELEMENT_NODE
						&& nodeInThirdLevel.getNodeName().equals(
								PATHELEMENT_ELEMENT)
						&& nodeInThirdLevel.hasAttributes()) {
					NamedNodeMap nodeMap = nodeInThirdLevel.getAttributes();
					for (int i = 0; i < nodeMap.getLength(); i++) {
						Node attr = nodeMap.item(i);
						if (attr.getNodeName().equals(LOCATION_ATTRIBUTE)) {
							artfacts.add(generateArtifactFromThirdDepen(attr
									.getNodeValue()));
						}

					}
				}
			}
			map.put(buildFile, artfacts);
		}
		return map;
	}

	/*
	 * 根据内部依赖生成Artifact
	 */
	private Artifact generateArtifactFromInternalDepen(String InternalDepen) {
		String artifactId = Constant.STARIBOSS
				+ InternalDepen.substring(InternalDepen.indexOf('-'),
						InternalDepen.lastIndexOf('-') + 1) + Constant.BIN;

		return new Artifact(Constant.STARIBOSS, artifactId,
				Constant.INTERNAL_VERSION);
	}

}
