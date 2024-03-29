package tr.edu.iyte.fesg.conversion;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import tr.edu.iyte.fesg.model.eliminationcondition.EliminationCondition;

public class JSONFileToEliminationConditionConverter {

	public static List<EliminationCondition> parseJSONFileForEliminationConditionCreation(String fileName) throws FileNotFoundException {
		JSONTokener tokener = new JSONTokener(new FileReader(fileName));
		
		List<EliminationCondition> eliminationConditionList = new LinkedList<EliminationCondition>();

		try {
			
			JSONObject JSONObject = (JSONObject) tokener.nextValue();
			JSONArray eliminationConditionsArray = JSONObject.getJSONArray("eliminationConditions");

			for (int i = 0; i < eliminationConditionsArray.length(); i++) {
				
				JSONObject JSONEliminationCondition = eliminationConditionsArray.getJSONObject(i);
				EliminationCondition eliminationCondition = new EliminationCondition();
				
				int ID = JSONEliminationCondition.getInt("ID");
				String conditionName = JSONEliminationCondition.getString("conditionName");
				boolean result = JSONEliminationCondition.getBoolean("result");
				JSONArray edgesToBeRemoved = JSONEliminationCondition.getJSONArray("edgesToBeRemoved");
				
				eliminationCondition.setID(ID);
				eliminationCondition.setConditionName(conditionName);
				eliminationCondition.setResult(result);
				
				for (int j = 0; j < edgesToBeRemoved.length(); j++) {
					String edgeToBeRemoved = edgesToBeRemoved.getString(j);
					String[] edge = edgeToBeRemoved.split("->");
					String source = edge[0];
					String target = edge[1];
					source.trim();
					target.trim();
					
					eliminationCondition.addEdgeToBeRemoved(source, target);
				}
				
				eliminationConditionList.add(eliminationCondition);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return eliminationConditionList;
	}

}
