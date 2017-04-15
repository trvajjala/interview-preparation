package pattern.creational.prototype;

public abstract class Item implements Cloneable {

    private String title;

    private String name;

    private Double cost;



    @Override
    public Object clone() throws CloneNotSupportedException{

	return super.clone();
    }

    public String getTitle() {
	return title;
    }

    public void setTitle(String title) {
	this.title = title;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Double getCost() {
	return cost;
    }

    public void setCost(Double cost) {
	this.cost = cost;
    }

}
