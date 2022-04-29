package tr.edu.iyte.fesg.fullesgtoproductesg;

import java.util.LinkedList;
import java.util.List;

import tr.edu.iyte.esg.model.ESG;

public class FeatureCombinationUtility {

	private List<List<ESG>> featureCombinationList;

	public FeatureCombinationUtility() {
		featureCombinationList = new LinkedList<List<ESG>>();
	}

	public List<List<ESG>> getFeatureCombinationList() {
		return featureCombinationList;
	}

	public void combinationUtil(ESG candidateFeatureESGArr[], int n, int r, int index, ESG featureESGCombinationArr[], int i) {

		if (index == r) {
			List<ESG> combination = new LinkedList<ESG>();
			for (int j = 0; j < r; j++) {
				combination.add(j, featureESGCombinationArr[j]);
			}
			featureCombinationList.add(combination);
			return;
		}

		if (i >= n) {
			return;
		}

		featureESGCombinationArr[index] = candidateFeatureESGArr[i];
		combinationUtil(candidateFeatureESGArr, n, r, index + 1, featureESGCombinationArr, i + 1);

		combinationUtil(candidateFeatureESGArr, n, r, index, featureESGCombinationArr, i + 1);
	}

	public String toString() {
		String str = "";

		for (List<ESG> ESGList : featureCombinationList) {
			for (ESG ESG : ESGList) {
				str += ESG.getName() + " ";
			}
			str += "\n";
		}

		return "Feature Combinations list:\n" + str;
	}

}
