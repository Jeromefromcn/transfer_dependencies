package tk.jeromefromcn.collector;

import static org.junit.Assert.*;

import java.util.Set;

import org.junit.Test;

public class AntDependenciesCollectorTest {

	private AntDependenciesCollector tester = new AntDependenciesCollector(
			"src/test/resources/firstlevel");

	@Test
	public void testCollectDependencies() {
		Set<String> dependenciesSet = tester.collectDependencies();
		assertEquals(dependenciesSet.size(), 2);
	}

}
