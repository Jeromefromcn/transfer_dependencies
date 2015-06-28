package tk.jeromefromcn.collector;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class AntDependenciesCollectorTest {

	private AntDependenciesCollector tester = new AntDependenciesCollector(
			"src/test/resources/tk/jeromefromcn/firstlevel");

	@Test
	public void testCollectDependencies() {
		Set<String> dependenciesSet = tester.collectDependencies();
		Set<String> compareSet = new HashSet<String>();
		compareSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web-3.0.3.RELEASE.jar");
		compareSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web.servlet-3.0.3.RELEASE.jar");
		assertEquals(dependenciesSet, compareSet);
	}

}
