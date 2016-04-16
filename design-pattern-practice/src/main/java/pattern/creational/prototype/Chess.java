package pattern.creational.prototype;

public class Chess implements Cloneable {

    private int count;

    public void setCount(int count) {
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}
