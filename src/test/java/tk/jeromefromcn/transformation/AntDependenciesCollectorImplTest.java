package tk.jeromefromcn.transformation;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

public class AntDependenciesCollectorImplTest {

	private AntDependenciesCollector tester = new AntDependenciesCollectorImpl();

	@Test
	public void testCollectDependencies() {
		tester.setBasePath("src/test/resources/tk/jeromefromcn/transformation/firstlevel");
		Set<String> dependenciesSet = tester.collectDependencies();
		Set<String> compareSet = new HashSet<String>();
		compareSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web-3.0.3.RELEASE.jar");
		compareSet
				.add("${M2_REPO}/spring3.0/server/org.springframework.web.servlet-3.0.3.RELEASE.jar");
		assertEquals(dependenciesSet, compareSet);
	}
}
