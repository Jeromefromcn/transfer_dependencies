package tk.jeromefromcn.transformation;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class AntBuildInfoCollectorImplTest {

	private AntBuildInfoCollector tester = new AntBuildInfoCollectorImpl();

	private String basePath = "src/test/resources/tk/jeromefromcn/transformation/firstlevel";

	private Artifact a1 = new Artifact(Constant.STAREXT,
			"org.springframework.web-3.0.3.RELEASE", Constant.VERSION);
	private Artifact a2 = new Artifact(Constant.STAREXT,
			"org.springframework.web.servlet-3.0.3.RELEASE", Constant.VERSION);
	private Artifact a3 = new Artifact(Constant.STAREXT,
			"org.springframework.core-3.0.3.RELEASE", Constant.VERSION);

	private Artifact a4 = new Artifact(Constant.STARIBOSS,
			"stariboss-common-bin", Constant.INTERNAL_VERSION);
	private Artifact a5 = new Artifact(Constant.STARIBOSS,
			"stariboss-domain-bin", Constant.INTERNAL_VERSION);
	private Artifact a6 = new Artifact(Constant.STARIBOSS,
			"stariboss-accept-bin", Constant.INTERNAL_VERSION);

	@Test
	public void testCollectThirdDependencies() {
		Map<String, Artifact> dependenciesMap = tester
				.collectThirdDependencies(basePath);
		Map<String, Artifact> compareMap = new HashMap<String, Artifact>();
		String key1 = "${M2_REPO}/spring3.0/server/org.springframework.web-3.0.3.RELEASE.jar";
		String key2 = "${M2_REPO}/spring3.0/server/org.springframework.web.servlet-3.0.3.RELEASE.jar";
		String key3 = "${M2_REPO}/spring3.0/server/org.springframework.core-3.0.3.RELEASE.jar";
		compareMap.put(key1, a1);
		compareMap.put(key2, a2);
		compareMap.put(key3, a3);
		assertEquals(dependenciesMap, compareMap);
	}

	@Test
	public void testCollectBuildFileAndArtifactsMap() {
		Map<File, List<Artifact>> map = tester
				.collectBuildFileAndArtifactsMap(basePath);
		Map<File, List<Artifact>> compareMap = new HashMap<File, List<Artifact>>();

		compareMap.put(
				new File(basePath + "/secondlevel/thirdlevel/build.xml"),
				Arrays.asList(a4, a5, a1, a2));
		compareMap.put(
				new File(basePath + "/secondlevel/thirdlevel2/build.xml"),
				Arrays.asList(a4, a6, a1, a3));
		assertEquals(map, compareMap);
	}
}
