package gadcprague.fruits.celidb.data;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Data {
	/* JSON */
	private HashMap<String, String> jsonUrlList = null;// = new HashMap<String, String>();
	private HashMap<String, String> jsonStringList = null;

	private boolean synchronizedData = false;
	
	/* Internal Data Structure */
	private HashMap<Integer, Product> products = null;
	private ArrayList<Category> categories = null;
	
	
	public Data() {
		super();
		
		this.jsonUrlList = new HashMap<String, String>();
		this.jsonUrlList.put("products", "http://vps.kemza.com/hackaton/products.json");
		this.jsonUrlList.put("product_parameters", "http://vps.kemza.com/hackaton/product_parameters.json");
		this.jsonUrlList.put("product_categories", "http://vps.kemza.com/hackaton/product_categories.json");
	}
	
	public boolean synchronize() {
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
	
		/*ArrayList<Product> productList = this.products.values();
		productList.
		for(int i = 0; i < productKeys.size(); i++) {
			productKeys.toString()
		}
		this.products.get(key)
		
		Iterator<Product> productListIterator = this.products.values().iterator();
		Product product;
		while(productListIterator.next() != null) {
			productListIterator.
		}*/
		
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
	 * Donwloads JSON file and saves JSON string into jsonStrinList variable
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
			URLConnection urlConn = url.openConnection();
			urlConn.setUseCaches(false);
			
			DataInputStream dis = new DataInputStream(urlConn.getInputStream());
			
			while ((line = dis.readLine()) != null)
		    { 
				jsonString.concat(line); 
		    } 
		    
			dis.close();
			this.jsonStringList.put(jsonUrlKey, jsonString);
			
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
		Set<String> keys = this.jsonStringList.keySet();
		for(int i = 0; i < keys.size(); i++) {
			this.parseJsonFile(keys.toString());
		}
		
		return true;
	}
	
	private boolean parseJsonFile(String jsonKey) {
		JSONObject jObject;
		try {
			jObject = new JSONObject(this.jsonStringList.get(jsonKey));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(jsonKey.equalsIgnoreCase("products"))
			return this.parseJsonProducts(jObject);
		else if(jsonKey.equalsIgnoreCase("product_categories")) {
			return this.parseJsonProductCategories(jObject);
		}
		
		return false;
	}
	
	/**
	 * Parse json categories
	 * 
	 * @param jObject
	 * @return
	 */
	private boolean parseJsonProductCategories(JSONObject jObject) {
		try {
			JSONObject pcObject = jObject.getJSONObject("product_categories");
			
			// productsArray obsahuje "product_category_declaration" a "product_category_data"
			JSONArray pcDeclaration = pcObject.getJSONArray("product_category_declaration");
			JSONArray pcData = pcObject.getJSONArray("product_category_data");
			
			// Parsing product_category_declaration
			for(int i = 0; i > pcDeclaration.length(); i++) {
				Category category = new Category();
				
				category.setId(Integer.parseInt(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id")));
				category.setName(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id"));
				category.setParentId(Integer.parseInt(this.fetchJsonPropertyFromArray(pcDeclaration, i, "id")));
				
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
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return false;
	}

	private boolean parseJsonProducts(JSONObject jObject) {
		try {
			JSONArray productsArray = jObject.getJSONArray("products");
			this.products = new HashMap<Integer, Product>();
			
			// Going trhrough all products
			for(int i = 0; i < productsArray.length(); i++) {
				// Parse product parameters
				Product product = new Product();
				/*if(productsArray.getJSONObject(i).get("id") != null) {
					product.setId(Integer.parseInt(productsArray.getJSONObject(i).get("id").toString()));
				}*/
				product.setId(Integer.parseInt(this.fetchJsonPropertyFromArray(productsArray, i, "id")));
				product.setBarCode(this.fetchJsonPropertyFromArray(productsArray, i, "barcode"));
				product.setName(this.fetchJsonPropertyFromArray(productsArray, i, "name"));
				product.setDateChange(Date.valueOf(this.fetchJsonPropertyFromArray(productsArray, i, "id")));
				
				// Dodat dalsi polozky
				
				this.products.put(product.getId(), product);
			}
			
			return true;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} 
	}
	
	private String fetchJsonPropertyFromArray(JSONArray jArray, int iterator, String key) {
		try {
			if(jArray.getJSONObject(iterator).get(key) != null)
				return jArray.getJSONObject(iterator).get(key).toString();
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return null;
	}
}
