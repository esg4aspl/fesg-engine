package tr.edu.iyte.fesg.conversion;

import java.io.FileNotFoundException;
import java.io.FileReader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import tr.edu.iyte.esg.conversion.json.JSONFileToESGConverter;
import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class JSONFileToFeaturedESGConverter extends JSONFileToESGConverter {
	
	public JSONFileToFeaturedESGConverter() {
		super();
	}
	
	
	/**
	 * Parse the given file to create a featured ESG
	 * 
	 * @param fileName
	 * @return
	 * @throws FileNotFoundException
	 */
	public FeaturedESG parseJSONFileForFeaturedESGCreation(String fileName) {
		JSONTokener tokener = null;
		try {
			tokener = new JSONTokener(new FileReader(fileName));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return parseJSONObjectForFeaturedESGCreation((JSONObject) tokener.nextValue());
	}
	
	/**
	 * Parse the given json object to create a featured ESG
	 *
	 * @param esgJsonObject
	 * @return ESG object
	 */
	public FeaturedESG parseJSONObjectForFeaturedESGCreation(JSONObject featuredESGJSONObject) {
		FeaturedESG featuredESG = null;
		
		try {
			String name = featuredESGJSONObject.getString("name");
			JSONObject coreESGObject = featuredESGJSONObject.getJSONObject("c-esg");
			ESG coreESG = parseJSONObjectForESGSimpleCreation(coreESGObject);
			featuredESG = new FeaturedESG(name, coreESG.getName());
			featuredESG.setCoreESGName(coreESG.getName());
			featuredESG.setCoreESG(coreESG);
			
			JSONArray featureESGs = featuredESGJSONObject.getJSONArray("f-esgs");
			createFeatureESGs(featuredESG, featureESGs);
			
		}catch (JSONException e) {
			e.printStackTrace();
		}
		
		
		return featuredESG;
		
	}
	
	protected void createFeatureESGs(FeaturedESG featuredESG, JSONArray JSONVertices) {

		try {
			for (int i = 0; i < JSONVertices.length(); i++) {
				JSONObject featureESGObject = JSONVertices.getJSONObject(i);
				ESG featureESG = parseJSONObjectForESGSimpleCreation(featureESGObject);
				featuredESG.addFeatureESG(featureESG);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}

	}

}
