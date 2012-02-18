package gadcprague.fruits.celidb.data;

import java.util.ArrayList;

public class Category {
	private int id;
	private int parentId = 0;
	private String name;
	private ArrayList<Integer> subCategoryList = null;
	
	public Category() {
		super();
	}

	public Category(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Integer> getSubCategoryList() {
		return subCategoryList;
	}
	
	public void addSubCategory(Integer categoryId) {
		this.subCategoryList.add(categoryId);
	}
}
