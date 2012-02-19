package gadcprague.fruits.celidb.data;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Set;

public class Product {
	private int id = 0;
	private String barCode = null;
	private String name = null;
	private String nameLower = null;
	private String description = null;
	private Integer status = 1;
	private Date dateChange;
	private String changeUser;

	private ArrayList<Integer> categoryList = new ArrayList<Integer>();
	/**
	 * Integer - parameterId
	 * String - value
	 */
	private HashMap<Integer, String> parameterList = new HashMap<Integer, String>();

	public Product() {
		super();
	}

	public Product(int id) {
		super();
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getBarCode() {
		return barCode;
	}

	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}

	public String getName() {
		return name;
	}

	public String getNameLower() {
		return nameLower;
	}

	public void setName(String name) {
		this.name = name;
		this.nameLower = name.toLowerCase();
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getDateChange() {
		return dateChange;
	}

	public void setDateChange(Date dateChange) {
		this.dateChange = dateChange;
	}

	public String getChangeUser() {
		return changeUser;
	}

	public void setChangeUser(String changeUser) {
		this.changeUser = changeUser;
	}

	public ArrayList<Integer> getCategoryList() {
		return categoryList;
	}

	public String getParameter(Integer parameterId) {
		return this.parameterList.get(parameterId);
	}

	public void setParameter(Integer parameterId, String value) {
		Set<Integer> paramIdList = this.parameterList.keySet();
		if(paramIdList.contains(parameterId))
			;//this.parameterList.
		else
			this.parameterList.put(parameterId, value);
	}

	public HashMap<Integer, String> getParameters() {
		return parameterList;
	}

	public void addCategory(Integer categoryId) {
		this.categoryList.add(categoryId);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", barCode=" + barCode + ", name=" + name
				+ ", description=" + description + "]";
	}


}
