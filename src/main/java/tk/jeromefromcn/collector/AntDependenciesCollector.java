package tk.jeromefromcn.collector;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class AntDependenciesCollector {

	private static final String BUILD_FILE_NAME = "build.xml";
	private static final String PATH_ELEMENT = "path";
	private static final String PATHELEMENT_ELEMENT = "pathelement";
	private static final String LOCATION_ATTRIBUTE = "location";

	private String basePath;

	private List<String> buildFilePaths;

	private Set<String> dependenciesSet;

	public AntDependenciesCollector(String basePath) {
		this.basePath = basePath;
	}

	public List<String> getBuildFilePaths() {
		return buildFilePaths;
	}

	public Set<String> getDependenciesSet() {
		return dependenciesSet;
	}

	public static void main(String[] args) {
		AntDependenciesCollector collector = new AntDependenciesCollector(
				"/Users/Jerome/Documents/gitlab/stariboss-os-demo/");
		for (String d : collector.collectDependencies()) {
			System.out.println(d);
		}
	}

	/**
	 * 获取stariboss代码目录下所有工程的依赖，返回Set格式字符串集合
	 */
	public Set<String> collectDependencies() {
		// TODO Auto-generated method stub

		buildFilePaths = getBuildFilePaths(basePath);

		dependenciesSet = parseBuildFiles();

		return dependenciesSet;
	}

	/*
	 * 解析所有的build.xml文件，获取依赖的配置信息
	 */
	private Set<String> parseBuildFiles() {
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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return dependenciesSet;
	}

	/*
	 * 获得所有build.xml的路径
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

}
