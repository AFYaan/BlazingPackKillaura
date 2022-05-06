package pl.afyaan;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static String[] ass = new String[]{"aaa", "bee kurwa", "ce", "xd"};
    public static Set methods = new HashSet();
    public static void main(String[] args) {
        if(!methods.contains("aaa")){
            System.out.println("1");
        }



        methods.add("aaa");

        if(!methods.contains("aaa")){
            System.out.println("2");
        }

        //System.out.println(Arrays.toString(ass));
    }

    public double elo(){
        return 0.0D;
    }

    public void test(String name, Object obj1, Object obj2){
        try{
           // ThreadManager.clickAura(name, obj1, obj2, this);
        }catch (Exception ex){}
    }
}
