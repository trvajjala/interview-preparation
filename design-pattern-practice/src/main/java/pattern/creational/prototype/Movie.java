package pattern.creational.prototype;

public class Movie extends Item {




    private String title;

    private String name;

    private Double cost;




    public Movie(String title, String name, Double cost) {
	super();
	this.title = title;
	this.name = name;
	this.cost = cost;
    }

    @Override
    public String getTitle() {
	return title;
    }

    @Override
    public void setTitle(String title) {
	this.title = title;
    }

    @Override
    public String getName() {
	return name;
    }

    @Override
    public void setName(String name) {
	this.name = name;
    }

    @Override
    public Double getCost() {
	return cost;
    }

    @Override
    public void setCost(Double cost) {
	this.cost = cost;
    }



}
