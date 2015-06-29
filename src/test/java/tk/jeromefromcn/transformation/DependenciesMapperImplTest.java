package tk.jeromefromcn.transformation;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class DependenciesMapperImplTest {

	private DependenciesMapper tester = new DependenciesMapperImpl();

	@Test
	public void testMapDependencies() {
		Set<String> antDependenciesSet = new HashSet<String>();
		antDependenciesSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web-3.0.3.RELEASE.jar");
		antDependenciesSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web.servlet-3.0.3.RELEASE.jar");
		Map<String, String> compareMap = new HashMap<String, String>();
		compareMap
				.put("${M2_REPO}/spring3.0/server/org.springframework.web-3.0.3.RELEASE.jar",
						"compile 'starext:org.springframework.web-3.0.3.RELEASE:1.0'");
		compareMap
				.put("${M2_REPO}/spring3.0/server/org.springframework.web.servlet-3.0.3.RELEASE.jar",
						"compile 'starext:org.springframework.web.servlet-3.0.3.RELEASE:1.0'");
		Map<String, String> dependenciesMap = new HashMap<String, String>();
		dependenciesMap = tester.mapDependencies(antDependenciesSet);
		assertEquals(dependenciesMap, compareMap);
	}
}
