package tables;

public class SubCategory {

	int code;
	String name;
	int father;
	
	public SubCategory(int code, String name, int father) {
		this.code = code;
		this.name = name;
		this.father = father;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getFather() {
		return father;
	}

	public void setFather(int father) {
		this.father = father;
	}
	
	
	
}
