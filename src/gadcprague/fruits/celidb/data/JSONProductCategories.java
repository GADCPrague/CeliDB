package gadcprague.fruits.celidb.data;

import java.util.ArrayList;

public class JSONProductCategories {
	private ArrayList<JSONProductCategory> product_category_declaration;
	private ArrayList<JSONProductCategoryData> product_category_data;



	public JSONProductCategories() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<JSONProductCategory> getProduct_category_declaration() {
		return product_category_declaration;
	}

	public void setProduct_category_declaration(
			ArrayList<JSONProductCategory> product_category_declaration) {
		this.product_category_declaration = product_category_declaration;
	}

	public ArrayList<JSONProductCategoryData> getProduct_category_data() {
		return product_category_data;
	}
	public void setProduct_category_data(
			ArrayList<JSONProductCategoryData> product_category_data) {
		this.product_category_data = product_category_data;
	}


}
