package serial;

import proxy.Count;

import java.io.Serializable;

public class User implements Serializable,Cloneable{

    Count count = new Count() {
        @Override
        public void queryCount() {

        }

        @Override
        public void updateCount() {

        }
    };

    private int age;

    private boolean gender;

    private String name;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        User user = new User();
        System.out.println(user.count.getClass());
    }
}
