package gadcprague.fruits.celidb.data;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class Data {
	/* Singleton */
	private static Data instance;

	/* JSON */
	private HashMap<String, String> jsonUrlList = null;// = new HashMap<String, String>();
	private HashMap<String, String> jsonStringList;

	private boolean synchronizedData = false;

	/* Internal Data Structure */
	private HashMap<Integer, Product> products = null;
	private ArrayList<Category> categories = null;
	private ArrayList<ProductParameter> productParameters = null;

	/* Internal class use only */
	private JSONProductCategories jpc = null;
	private JSONProductParameters jpp = null;

	private Data() {
		super();

		this.jsonUrlList = new HashMap<String, String>();
		this.jsonUrlList.put("products", "http://vps.kemza.com/hackaton/products.php");
		this.jsonUrlList.put("product_parameters", "http://vps.kemza.com/hackaton/product_parameters.php");
		this.jsonUrlList.put("product_categories", "http://vps.kemza.com/hackaton/product_categories.php");

		this.jsonStringList = new HashMap<String, String>();
		this.categories = new ArrayList<Category>();

		this.products = new HashMap<Integer, Product>();
		this.categories = new ArrayList<Category>();
		this.productParameters = new ArrayList<ProductParameter>();
	}

	public static Data getInstance() {
		if(instance == null) {
			instance = new Data();
		}
		return instance;
	}

	public boolean synchronize() {
		Set<String> urlList = this.jsonUrlList.keySet();
		for (String key : urlList) {
			this.fetchJsonFile(key);
		}

		this.synchronizedData = this.parseJsonFiles();
		return this.synchronizedData;
	}

	public boolean isSynchronizedData() {
		return synchronizedData;
	}

	public Product getProduct(Integer productId) {
		return this.products.containsKey(productId) ? this.products.get(productId) : null;
	}

	public HashMap<Integer, Product> getProducts() {
		if(!this.synchronizedData)
			this.synchronize();

		return products;
	}

	public ArrayList<Product> searchProduct(String search) {
		ArrayList<Product> pList = new ArrayList<Product>();

		// Search in barcode, name and parameters
		String searchLower = search.toLowerCase();
		for(Integer key : this.products.keySet()) {
			if(this.products.get(key).getNameLower().contains(searchLower) ||
					this.products.get(key).getBarCode().contains(search))
				pList.add(this.products.get(key));
		}

		return pList;
	}

	public ArrayList<Product> getProductsInCategory(Integer categoryId) {
		ArrayList<Product> pList = new ArrayList<Product>();

		for(Integer key : this.products.keySet()) {
			if(this.products.get(key).getCategoryList().contains(categoryId))
				pList.add(this.products.get(key));
		}

		return pList;
	}

	public ArrayList<Category> getCategories() {
		if(!this.synchronizedData)
			this.synchronize();

		return categories;
	}

	public ArrayList<Category> getCategoriesWithParent(int parentId) {
		if(!this.synchronizedData)
			this.synchronize();

		ArrayList<Category> categoryList = new ArrayList<Category>();
		for(int i = 0; i < this.categories.size(); i++)
			if(this.categories.get(i).getParentId() == parentId)
				categoryList.add(this.categories.get(i));

		return categoryList;
	}

	public ArrayList<ProductParameter> getProductParameters() {
		return productParameters;
	}

	public ProductParameter getProductParameter(Integer parameterId) {
		for(int i = 0; i < this.productParameters.size(); i++)
			if(this.productParameters.get(i).getId() == parameterId)
				return this.productParameters.get(i);

		return null;
	}

	//----------------------------------------------------------------------
	// JSON Parsers
	//----------------------------------------------------------------------
	/**
	 * Downloads JSON file and saves JSON string into jsonStrinList variable
	 *
	 * @param jsonUrlKey
	 * @return
	 */
	private boolean fetchJsonFile(String jsonUrlKey) {
		String line = null;
		if(this.jsonStringList.containsKey(jsonUrlKey))
			this.jsonStringList.remove(jsonUrlKey);

		try {
			String jsonString = new String("");

			URL url = new URL(this.jsonUrlList.get(jsonUrlKey));
			System.out.println("fetchJsonFile("+jsonUrlKey+"), URL: "+this.jsonUrlList.get(jsonUrlKey));
			URLConnection urlConn = url.openConnection();
			urlConn.setUseCaches(false);

			BufferedReader in = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));

			while ((line = in.readLine()) != null)
		    {
				//System.out.println(line);
				jsonString = jsonString.concat(line);
		    }

			in.close();
			this.jsonStringList.put(jsonUrlKey, jsonString);

			//System.out.println("fetchJsonFile("+jsonUrlKey+") OK");
			//System.out.println("fetchJsonFile("+jsonUrlKey+"), jsonString="+jsonString);

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return true;
	}

	private boolean parseJsonFiles() {
		//System.out.println("parseJsonFiles()");

		//Set<String> keys = this.jsonStringList.keySet();
		for(String key:this.jsonStringList.keySet()) {
			//this.parseJsonFile(key);
			String jsonString = this.jsonStringList.get(key).toString();

			if(key.equalsIgnoreCase("products"))
				this.parseJsonProducts(jsonString);
			else if(key.equalsIgnoreCase("product_categories"))
				this.parseJsonProductCategories(jsonString);
			else if(key.equalsIgnoreCase("product_parameters"))
				this.parseJsonProductParameters(jsonString);
		}

		this.fillCategories();
		this.fillParameters();

		return true;
	}


	private void parseJsonProductParameters(String jsonString) {
		// TODO Auto-generated method stub
		Type type = new TypeToken<JSONProductParameters>(){}.getType();
		JSONProductParameters paramList = new Gson().fromJson(jsonString, type);

		this.jpp = paramList;

		for(int i = 0; i < paramList.getProduct_parameters().size(); i++) {
			ProductParameter parameter = paramList.getProduct_parameters().get(i);
			if(!parameter.getName().equalsIgnoreCase("0"))
				this.productParameters.add(parameter);
		}
	}

	/**
	 * Parse json product categories
	 *
	 * @param jObject
	 * @return
	 */
	private boolean parseJsonProductCategories(String jsonString) {
		Type type = new TypeToken<JSONProductCategories>(){}.getType();
		JSONProductCategories catList = new Gson().fromJson(jsonString, type);

		this.jpc = catList;

		for(int i = 0; i < catList.getProduct_category_declaration().size(); i++) {
			Category category = new Category(catList.getProduct_category_declaration().get(i).getId());
			category.setName(catList.getProduct_category_declaration().get(i).getName());
			category.setParentId(catList.getProduct_category_declaration().get(i).getParentId());

			this.categories.add(category);
		}

		return true;
	}

	private boolean parseJsonProducts(String jsonString) {

		Type type = new TypeToken<List<Product>>(){}.getType();
		List<Product> pList = new Gson().fromJson(jsonString, type);

		this.products = new HashMap<Integer, Product>();
		for(Iterator<Product> i = pList.iterator(); i.hasNext();) {
			Product p = i.next();
			p.setNameLower(p.getName().toLowerCase());
			this.products.put(p.getId(), p);
		}

		return true;

	}

	/**
	 * Add categories to products
	 * @return
	 */
	private boolean fillCategories() {
		// Fill Product.categoryList
		for(int i = 0; i < this.jpc.getProduct_category_data().size(); i++) {
			Integer productId = this.jpc.getProduct_category_data().get(i).getProductId();
			Integer categoryId = this.jpc.getProduct_category_data().get(i).getCategoryId();

			//System.out.println("product_id="+productId+", category_id="+categoryId);

			if(this.products.containsKey(productId)) {
				//System.out.println("pridavam produkt "+productId+" do kategorie "+categoryId);
				this.products.get(productId).addCategory(categoryId);
			}
		}

		return true;
	}

	/**
	 * Add parameters to products
	 *
	 * @return
	 */
	private boolean fillParameters() {
		if(this.jpp.getProduct_parameters_data() == null)
			return false;

		for(int i = 0; i < this.jpp.getProduct_parameters_data().size(); i++) {
			Integer productId = this.jpp.getProduct_parameters_data().get(i).getProductId();
			Integer parameterId = this.jpp.getProduct_parameters_data().get(i).getParameterId();
			String value = this.jpp.getProduct_parameters_data().get(i).getValue();

			if(this.products.containsKey(productId)) {
				//System.out.println("productId="+productId+", parameterId="+parameterId+", value="+value);
				this.products.get(productId).setParameter(parameterId, value);
			}
		}

		return true;
	}
}
