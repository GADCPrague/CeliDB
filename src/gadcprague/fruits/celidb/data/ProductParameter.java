package gadcprague.fruits.celidb.data;

public class ProductParameter {
	private Integer id;
	private String name;
	private String description;
	/**
	 * Parameter data type (REAL, ...)
	 */
	private String type;
	private Integer required;


	public ProductParameter() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProductParameter(Integer id) {
		super();
		this.id = id;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getRequired() {
		return required;
	}


	public void setRequired(Integer required) {
		this.required = required;
	}



}
