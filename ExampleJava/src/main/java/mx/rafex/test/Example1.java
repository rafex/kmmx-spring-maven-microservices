package mx.rafex.test;

public class Example1 {

    public static void main(String[] args) {

        Person one = new Person("Raul", "Gonzalez");

        //Person two = new Person("Jose","ramirez");
        System.out.println(one);
        System.out.println("Name: " + one.getName());
        System.out.println("LastName: " + one.getLastName());

        Person two = one;

        two.setName("Pepe");
        two.setLastName("Ramirez");
        //System.out.println(one);
        System.out.println("------------");
        System.out.println(two);
        System.out.println("Name: " + two.getName());
        System.out.println("LastName:" + two.getLastName());

        System.out.println("------------");

        System.out.println(one);
        System.out.println("Name: " + one.getName());
        System.out.println("LastName: " + one.getLastName());

    }
}
