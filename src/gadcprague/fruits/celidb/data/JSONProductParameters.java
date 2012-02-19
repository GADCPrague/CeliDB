package gadcprague.fruits.celidb.data;

import java.util.ArrayList;

/**
 * JSON Parser
 */
public class JSONProductParameters {
	ArrayList<ProductParameter> product_parameters_declaration;
	ArrayList<JSONProductParametersData> product_parameters_data;

	public JSONProductParameters() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ArrayList<ProductParameter> getProduct_parameters() {
		return product_parameters_declaration;
	}

	public void setProduct_parameters(ArrayList<ProductParameter> product_parameters) {
		this.product_parameters_declaration = product_parameters;
	}

	public ArrayList<JSONProductParametersData> getProduct_parameters_data() {
		return product_parameters_data;
	}

	public void setProduct_parameters_data(
			ArrayList<JSONProductParametersData> product_parameters_data) {
		this.product_parameters_data = product_parameters_data;
	}


}
