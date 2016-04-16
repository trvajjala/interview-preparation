package puzzles;

public class IntegerBoxing extends Parent {

    public static void main(String[] args) {

        final Parent parent = new IntegerBoxing();
        parent.setAge(99);

        System.out.println(parent.getAge());

    }

    public void setAge(Integer age) {
        System.out.println("Object Age " + age);
    }

    @Override
    public int getAge() {
        return super.getAge();
    }

}

class Parent {

    protected int age;

    public void setAge(int age) {
        System.out.println("Primitive Age : " + age);
        this.age = age;
    }

    public int getAge() {
        return true ? null : age;
    }
}
