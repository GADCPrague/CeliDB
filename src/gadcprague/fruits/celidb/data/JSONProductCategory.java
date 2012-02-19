package gadcprague.fruits.celidb.data;

public class JSONProductCategory {
	private Integer id;
	private Integer parentId;
	private String name;

	public JSONProductCategory() {
		super();
	}

	public JSONProductCategory(Integer id, Integer parentId, String name) {
		super();
		this.id = id;
		this.parentId = parentId;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


}
