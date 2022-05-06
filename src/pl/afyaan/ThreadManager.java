package pl.afyaan;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ThreadManager {
    public static Set<String> methods = new HashSet<>();
    public static Method method = null;
    public static boolean run = false;
    public static double x = 0;
    public static double y = 0;
    public static double z = 0;
    public static Field xField = null;
    public static Field yField = null;
    public static Field zField = null;
    public static Field xAttacked = null;
    public static Field yAttacked = null;
    public static Field zAttacked = null;
    public static double xAtt = 0;
    public static double yAtt = 0;
    public static double zAtt = 0;
    public static Object instance_;
    public static Object instance2_;
    public static boolean inUsage = false;
    public static void clickAura(String className, Object arg0, Object instance){
        System.out.println("START");
        System.out.println(className);
        System.out.println("END");
        System.out.println(arg0);
        System.out.println(instance);
        System.out.println("=================================");
        //X 3 Y 34 Z 21
        //String className_ = ", const implements } throw # try interface synchronized double default 7 ~ ~ - \" class \" goto ] ` 6 9 | abstract # 3 ! 3 long void return 7 abstract $ ^ 9 + final byte double float | byte float goto # 9 ^ , * switch transient int static public if default this abstract";
        //String methodName_ = "if volatile";
        try {
            if(arg0 == null) return;
            System.out.println("1");
            if(instance == null) return;
            System.out.println("2");
            ThreadManager.instance_ = arg0;
            ThreadManager.instance2_ = instance;

            if(inUsage) return;
            if(run){
                //return;
            }
            System.out.println("3");
            inUsage = true;
            Class<?> thePlayer = arg0.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
            System.out.println("4");
            int fieldCount = 0;
            for(Field field : thePlayer.getDeclaredFields()){
                field.setAccessible(true);
                if(field.get(arg0) != null)
                    System.out.println(fieldCount + " Field " + field.get(arg0).getClass().getName() + "\n Field value: " + field.get(arg0));
                if(fieldCount == 63){
                    xAtt = (double) field.get(arg0);
                    ThreadManager.xAttacked = field;
                }
                if(fieldCount == 26){
                    yAtt = (double) field.get(arg0);
                    ThreadManager.yAttacked = field;
                }
                if(fieldCount == 35){
                    zAtt = (double) field.get(arg0);
                    ThreadManager.zAttacked = field;
                }

                fieldCount++;
            }
            fieldCount = 0;
            System.out.println("5");
            Class<?> attackedPlayer = instance.getClass().getSuperclass().getSuperclass().getSuperclass().getSuperclass();
            System.out.println(attackedPlayer.getName());
            for(Field field : attackedPlayer.getDeclaredFields()){
                field.setAccessible(true);
                System.out.println(fieldCount + " Field " + field.get(arg0));
                if(fieldCount == 63){
                    //xAttacked = field;
                    ThreadManager.xField = field;
                }
                if(fieldCount == 26){
                    //yAttacked = field;
                    ThreadManager.yField = field;
                }
                if(fieldCount == 35){
                    //zAttacked = field;
                    ThreadManager.zField = field;
                }

                fieldCount++;
            }
            System.out.println("6");
            int methodC = 0;
            for(Method method : attackedPlayer.getDeclaredMethods()){
                if(method.getParameterCount() == 5){
                    Arrays.stream(method.getParameterTypes()).forEach(aClass -> System.out.println(aClass.getName()));

                    if(method.getParameterTypes()[0].getName().equals("double") &&
                            method.getParameterTypes()[1].getName().equals("double") &&
                            method.getParameterTypes()[2].getName().equals("double") &&
                            method.getParameterTypes()[3].getName().equals("float") &&
                            method.getParameterTypes()[4].getName().equals("float")){
                        if(methodC == 0){
                            methodC++;
                            continue;
                        }

                        Thread thread = new Thread(()->{
                            try {
                                Thread.sleep(0);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            while (true){
                            //for (int i = 0; i < 1000; i++){
                                try {
                                    if(ThreadManager.instance_ == null) return;
                                    if(ThreadManager.instance2_ == null) return;
                                    ThreadManager.xField.setAccessible(true);
                                    ThreadManager.yField.setAccessible(true);
                                    ThreadManager.zField.setAccessible(true);
                                    ThreadManager.x = (double)ThreadManager.xField.getDouble(ThreadManager.instance_);
                                    ThreadManager.y = (double)ThreadManager.yField.getDouble(ThreadManager.instance_);
                                    ThreadManager.z = (double)ThreadManager.zField.getDouble(ThreadManager.instance_);
                                    ThreadManager.xAttacked.setAccessible(true);
                                    ThreadManager.yAttacked.setAccessible(true);
                                    ThreadManager.zAttacked.setAccessible(true);
                                    ThreadManager.xAtt = (double)ThreadManager.xAttacked.getDouble(ThreadManager.instance2_);
                                    ThreadManager.yAtt = (double)ThreadManager.yAttacked.getDouble(ThreadManager.instance2_);
                                    ThreadManager.zAtt = (double)ThreadManager.zAttacked.getDouble(ThreadManager.instance2_);

                                    //System.out.println(ThreadManager.xAtt);

                                    float distance = getDistanceTo(ThreadManager.x, ThreadManager.y, ThreadManager.z, (double)xAttacked.getDouble(instance2_), (double)yAttacked.getDouble(instance2_), (double)zAttacked.getDouble(instance2_));
                                    //System.out.println(distance);
                                    if(distance > 7) continue;
                                    //xField.set(instance, xAttacked.getDouble(instance_));
                                    //yField.set(instance, yAttacked.getDouble(instance_));
                                    //yField.set(instance, yAttacked.getDouble(instance_));
                                    method.setAccessible(true);
                                    method.invoke(instance2_, (double)x, (double)y, (double) z, (float)82.0F, (float)92.0F);
                                    method.setAccessible(false);
                                    Thread.sleep(50);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }
                            //ThreadManager.inUsage = false;
                        });
                        thread.start();
                       // return;

                    }
                }
            }

            System.out.println("7");


            /*Class clazz = Class.forName(className, false, Killaura.loader);
            for(Method method : clazz.getMethods()){
                if(method.getName().contains(methodName_)) {
                    if(method.getParameters().length == 2) {
                        ThreadManager.method = method;

                        for (Parameter parameter : method.getParameters()) {
                            System.out.println(parameter.getName());
                        }
                    }
                }
            }*/
            Thread thread = new Thread(()->{
                try {
                    while (true){
                        Thread.sleep(100);
                        System.out.println(instance);
                        System.out.println(arg0);
                        //System.out.println(arg1);
                        ThreadManager.method.setAccessible(true);
                        //ThreadManager.method.invoke(instance, arg0, arg1);

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            //thread.start();
           // run = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        /*try {
            method.invoke(instance, arg0, arg1);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }*/
    }

    private static float getDistanceTo(double x1, double y1, double z1, double x2, double y2, double z2){
        float f = (float)(x1 - x2);
        float f1 = (float)(y1 - y2);
        float f2 = (float)(z1 - z2);
        return sqrt_float(f * f + f1 * f1 + f2 * f2);
    }

    public static float sqrt_float(float value)
    {
        return (float)Math.sqrt((double)value);
    }

    public static void methodHook(String className, String methodName, Object signature){
        String className_ = "] static catch , return transient ` char instanceof default 7 implements 9 throws catch 1 protected extends + long strictfp 7 \" ` catch class true goto = void strictfp goto { | byte default % if 1 ! true protected continue super native if + interface native private +";

        String methodName_ = "continue static";
       // String className_ = "true 6 long throw volatile catch * , ] volatile ] short , 7 ! { double | ^ $ if $ { % extends | 9 - extends byte ! throw # , default case null native ] protected do ! default ' default final 2 continue long instanceof ] null false ~ , { final = break static";
       // String methodName_ = "new default";
        //System.out.println("asasa");
        System.out.println("bije gracza xD");
        if(className.contains(className_) && methodName.contains(methodName_)){
            System.out.println("bije gracza xD");
            System.out.println(signature);


            try {
                Class clazz = Class.forName(className, false, Killaura.loader);
                for(Method method : clazz.getMethods()){
                    if(method.getName().equals(methodName)) {
                        if(method.getParameters().length == 2) {
                            ThreadManager.method = method;
                            for (Parameter parameter : method.getParameters()) {
                                System.out.println(parameter.getName());
                            }
                        }
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }


        }




        String name = className + "->" + methodName;
        if(!methods.contains(name)){
            System.out.println("========================================================================\n" +
                    "CLASSNAME:\n" + className + "\nMETHODNAME:\n" + methodName + "\nSIGNATURE:\n" + signature);
            methods.add(name);
        }

    }


    public static void runThread(Field[] fields, Object instance){
        Thread thread = new Thread(()->{
            try {
                while (true) {
                    Thread.sleep(1000);
                    System.out.println("================================");
                    for (Field f_ : fields) {

                        System.out.println(f_.get(instance));
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        });
    }
}
