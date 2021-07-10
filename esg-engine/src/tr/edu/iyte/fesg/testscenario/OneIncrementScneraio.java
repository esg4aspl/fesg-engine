package tr.edu.iyte.fesg.testscenario;

import tr.edu.iyte.esg.model.ESG;
import tr.edu.iyte.fesg.model.FeaturedESG;

public class OneIncrementScneraio implements Comparable<OneIncrementScneraio> {

	private int id;
	private FeaturedESG existingProduct;
	private ESG incrementFeature;
	private FeaturedESG productUnderConsideration;

	public OneIncrementScneraio() {

	}

	public OneIncrementScneraio(int id, FeaturedESG existingProduct, ESG incrementFeature,
			FeaturedESG productUnderConsideration) {
		setId(id);
		setExistingProduct(existingProduct);
		setIncrementFeature(incrementFeature);
		setProductUnderConsideration(productUnderConsideration);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public FeaturedESG getExistingProduct() {
		return existingProduct;
	}

	public void setExistingProduct(FeaturedESG existingProduct) {
		this.existingProduct = existingProduct;
	}

	public ESG getIncrementFeature() {
		return incrementFeature;
	}

	public void setIncrementFeature(ESG incrementFeature) {
		this.incrementFeature = incrementFeature;
	}

	public FeaturedESG getProductUnderConsideration() {
		return productUnderConsideration;
	}

	public void setProductUnderConsideration(FeaturedESG productUnderConsideration) {
		this.productUnderConsideration = productUnderConsideration;
	}

	public String toString() {
		return id + ": " + existingProduct.getName() + " + " + incrementFeature.getName() + " = "
				+ productUnderConsideration.getName();
	}

	@Override
	public int compareTo(OneIncrementScneraio o) {
		
		if(this.existingProduct.getFeaturedESGSet().size() == o.getExistingProduct().getFeaturedESGSet().size()) {
			return 0;
		}else if(this.existingProduct.getFeaturedESGSet().size() < o.getExistingProduct().getFeaturedESGSet().size()) {
			return -1;
		}else
			return 1;
	}

}
