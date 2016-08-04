package ru.stqa.pft.sandbox;

/**
 * Created by popdv on 28.07.2016.
 */
public class Equality {

    public static void main (String[] args) {
        String s1 = "firefox 2.0";
        String s2 = "firefox " + Double.parseDouble(String.valueOf(Math.sqrt(4.0)));


        System.out.println(s1 == s2);
        System.out.println(s1.equals(s2));

    }
}
