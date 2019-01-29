package pages;

public abstract class Animal {

    private String name;

    public Animal() {
        name = "Nameless one";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void walk() {
        System.out.println(getClass() + " " + getName() + " is walking...");
    }

    public void sleep() {
        System.out.println(getClass() + " " + getName() + " is sleeping...");
    }

    public void eat(String what) {
        System.out.println(getClass() + " " + getName() + " is eating " + what);
    }










}
