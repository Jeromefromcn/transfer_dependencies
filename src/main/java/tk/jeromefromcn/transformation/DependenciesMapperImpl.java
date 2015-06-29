package tk.jeromefromcn.transformation;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class DependenciesMapperImpl implements DependenciesMapper {

	@Override
	public Map<String, String> mapDependencies(Set<String> antDependenciesSet) {
		Map<String, String> dependenciesMap = new HashMap<String, String>();

		for (String antDepen : antDependenciesSet) {
			String gradleDepen = getGradleDepen(antDepen);
			dependenciesMap.put(antDepen, gradleDepen);
		}

		return dependenciesMap;
	}

	/*
	 * ͨ��ant�ű��е�depen��ȡgradle��ʽ��depen,artifact������ȡjar������
	 */
	private String getGradleDepen(String antDepen) {

		String groupId = "starext";
		String artifactId = antDepen.substring(antDepen.lastIndexOf('/') + 1,
				antDepen.length() - 4);
		String version = "1.0";
		String gradleDepen = "compile '" + groupId + ":" + artifactId + ":"
				+ version + "'";
		return gradleDepen;
	}
}
