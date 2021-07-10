package tr.edu.iyte.fesg.webapi;

import org.json.JSONObject;

import tr.edu.iyte.fesg.conversion.JSONFileToFeaturedESGConverter;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class JSONFileToFeaturedESGConversionAPI {
	
	public FeaturedESG parseJSONFile(String fileName) {
		JSONFileToFeaturedESGConverter JSONFileToFeaturedESGConverter = new JSONFileToFeaturedESGConverter();
		FeaturedESG featuredESG = JSONFileToFeaturedESGConverter.parseJSONFileForFeaturedESGCreation(fileName);
		
		return featuredESG;
	}
	

	public FeaturedESG parseJSONObject(JSONObject JSONObject) {
		JSONFileToFeaturedESGConverter JSONFileToFeaturedESGConverter = new JSONFileToFeaturedESGConverter();
		FeaturedESG featuredESG = JSONFileToFeaturedESGConverter.parseJSONObjectForFeaturedESGCreation(JSONObject);
		return featuredESG;
	}
	

}
