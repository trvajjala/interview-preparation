package pattern.creational.builder;

public class LunchOrder {
    String bread;
    String meat;
    String dressing;

    private LunchOrder(Builder builder) {
	bread = builder.bread;
	meat = builder.meat;
	dressing = builder.dressing;
    }

    public static class Builder {

	private String bread;
	private String meat;
	private String dressing;

	public Builder() {
	}

	public LunchOrder build() {
	    //this method build actual data
	    return new LunchOrder(this);
	}

	public Builder bread(String bread) {
	    this.bread = bread;
	    return this;
	}

	public Builder meat(String meat) {
	    this.meat = meat;
	    return this;
	}

	public Builder dressing(String dressing) {
	    this.dressing = dressing;
	    return this;
	}

    }

    public String getBread() {
	return bread;
    }

    public String getMeat() {
	return meat;
    }

    public String getDressing() {
	return dressing;
    }

    @Override
    public String toString() {
	return "LunchOrder [" + (bread != null ? "bread=" + bread + ", " : "") + (meat != null ? "meat=" + meat + ", " : "")
		+ (dressing != null ? "dressing=" + dressing : "") + "]";
    }








}
