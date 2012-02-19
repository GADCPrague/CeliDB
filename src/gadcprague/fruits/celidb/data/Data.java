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
	/* JSON */
	private HashMap<String, String> jsonUrlList = null;// = new HashMap<String, String>();
	private HashMap<String, String> jsonStringList;

	private boolean synchronizedData = false;

	/* Internal Data Structure */
	private HashMap<Integer, Product> products = null;
	private ArrayList<Category> categories = null;


	public Data() {
		super();

		this.jsonUrlList = new HashMap<String, String>();
		//this.jsonUrlList.put("products", "http://vps.kemza.com/hackaton/products.php");
		//this.jsonUrlList.put("product_parameters", "http://vps.kemza.com/hackaton/product_parameters.php");
		this.jsonUrlList.put("product_categories", "http://vps.kemza.com/hackaton/product_categories.php");

		this.jsonStringList = new HashMap<String, String>();
		this.categories = new ArrayList<Category>();

		this.products = new HashMap<Integer, Product>();
		this.categories = new ArrayList<Category>();
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

	public HashMap<Integer, Product> getProducts() {
		if(!this.synchronizedData)
			this.synchronize();

		return products;
	}

	public HashMap<Integer, Product> getProductsInCategory(Integer categoryId) {
		HashMap<Integer, Product> pList = new HashMap<Integer, Product>();

		ArrayList<Product> productList = (ArrayList<Product>) this.products.values();
		for(int i = 0; i < productList.size(); i++) {
			if(productList.get(i).getCategoryList().contains(categoryId)) {
				pList.put(productList.get(i).getId(), productList.get(i));
			}
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

			System.out.println("fetchJsonFile("+jsonUrlKey+") OK");
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
		System.out.println("parseJsonFiles()");

		//Set<String> keys = this.jsonStringList.keySet();
		for(String key:this.jsonStringList.keySet()) {
			//this.parseJsonFile(key);
			String jsonString = this.jsonStringList.get(key).toString();

			if(key.equalsIgnoreCase("products"))
				this.parseJsonProducts(jsonString);
			else if(key.equalsIgnoreCase("product_categories"))
				this.parseJsonProductCategories(jsonString);
		}
		/*for(int i = 0; i < keys.size(); i++) {
			this.parseJsonFile(keys.toString());
		}*/

		return true;
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

		// Fill Product.categoryList
		for(int i = 0; i < catList.getProduct_category_data().size(); i++) {
			//System.out.println("id="+catList.getProduct_category_data().get(i).getId());

			Integer productId = catList.getProduct_category_data().get(i).getProductId();
			Integer categoryId = catList.getProduct_category_data().get(i).getCategoryId();

			if(this.products.containsKey(productId) && this.products.get(productId) != null) {
				this.products.get(productId).addCategory(categoryId);
			}
		}

		for(int i = 0; i < catList.getProduct_category_declaration().size(); i++) {
			Category category = new Category(catList.getProduct_category_declaration().get(i).getId());
			category.setName(catList.getProduct_category_declaration().get(i).getName());
			category.setParentId(catList.getProduct_category_declaration().get(i).getParentId());

			this.categories.add(category);
		}

			//JSONArray pcObject = jObject.getJSONArray("product_categories");


			// productsArray obsahuje "product_category_declaration" a "product_category_data"
			//JSONArray pcDeclaration = pcObject.get(0);
			//JSONArray pcDeclaration = pcObject.getJSONArray("product_category_declaration");
			//JSONArray pcData = pcObject.getJSONArray("product_category_data");

			// Parsing product_category_declaration
			/*for(int i = 0; i > pcDeclaration.length(); i++) {
				Category category = new Category();

				category.setId(Integer.parseInt(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id")));
				category.setName(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id"));
				category.setParentId(Integer.parseInt(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id")));

				this.categories.add(category);

				// Pridani do seznamu podkategorii u parentCategory
				if(category.getParentId() > 0);
					this.categories.get(category.getParentId()).addSubCategory(category.getId());
			}

			// Parsing product_category_data
			for(int i = 0; i < pcData.length(); i++) {
				Integer categoryId = Integer.parseInt(this.fetchJsonPropertyFromArray(pcData, i, "category_id"));
				Integer productId = Integer.parseInt(this.fetchJsonPropertyFromArray(pcData, i, "id"));

				// Pridani kategorie k produktu
				if(this.products.containsKey(productId))
					this.products.get(productId).addCategory(categoryId);
			}*/

		return true;
	}

	private boolean parseJsonProducts(String jsonString) {

		Type type = new TypeToken<List<Product>>(){}.getType();
		List<Product> pList = new Gson().fromJson(jsonString, type);

		this.products = new HashMap<Integer, Product>();
		for(Iterator<Product> i = pList.iterator(); i.hasNext();) {
			Product p = i.next();
			this.products.put(p.getId(), p);
		}

		return true;

	}
}
