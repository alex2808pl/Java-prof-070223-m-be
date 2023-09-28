package de.telran._2023_09_28;

public class TestRecord {
    public static void main(String[] args) {
        Human hm1 = new Human("Вася", 18);
        Human hm2 = new Human("Вася", 18);
//        hm1.setAge(20);
        System.out.println(hm1.getName());
        System.out.println(hm1);
        System.out.println(hm1.equals(hm2));
        System.out.println(hm1==hm2);



        HumanRecord hmRecord1 = new HumanRecord("Петя", 20);
        HumanRecord hmRecord2 = new HumanRecord("Петя", 20);
        System.out.println(hmRecord1.name());
        System.out.println(hmRecord1);
        System.out.println(hmRecord1.equals(hmRecord2));
        System.out.println(hmRecord1==hmRecord2);

        HumanLombok hmLombok = new HumanLombok("Лида", 20);
        System.out.println(hmLombok);

    }
}
