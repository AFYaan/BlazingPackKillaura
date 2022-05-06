package pl.afyaan;

import javassist.*;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.ClassRemapper;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;


public class Killaura {
    public static ClassLoader loader = null;
    private boolean run = false;
    int i = 0;
    public int loadedClasses = 0;

    public void run(){
        try {
            ClassPool pool = ClassPool.getDefault();
            CtClass threadClass = pool.getCtClass(Thread.class.getName());
            CtClass newClass = pool.makeClass("pl.afyaan.ThreadFilter", threadClass);
            newClass.addField(CtField.make("public static java.util.Set methods = new java.util.HashSet();", newClass));
            newClass.addField(CtField.make("private java.lang.reflect.Field[] intFields;", newClass));
            newClass.addField(CtField.make("private java.lang.Object instance;", newClass));
            newClass.addField(CtField.make("public static java.lang.String arg1 = \"class\";", newClass));
            newClass.addField(CtField.make("public static java.lang.String arg2 = \"method\";", newClass));
            CtConstructor newConstructor = CtNewConstructor.make("public " + newClass.getSimpleName() +
                    "(java.lang.reflect.Field[] intFields, java.lang.Object instance) {" +
                    "this.intFields = intFields;" +
                    "this.instance = instance;" +
                    "}", newClass);
            newClass.addConstructor(newConstructor);

            CtMethod hookMethod = CtNewMethod.make("public static void methodHook(String className, String methodName){" +
                    "System.out.println(className + \" - \" + methodName);" +
                    "}", newClass);

            CtMethod runMethod = CtNewMethod.make(
                    "public void run(){" +
                            "try{" +

                                "while(true){" +
                                "Thread.sleep(1000L);" +
                                "System.out.println(\"================================\");" +
                                "int fieldsSize = intFields.length;" +
                                    "for (int i = 0; i < fieldsSize; i++) {" +
                                        "java.lang.reflect.Field f_ = intFields[i];" +
                                        "f_.setAccessible(true);" +
                                        "System.out.println(instance.getClass().getName());" +
                                       // "int fieldValue_ = (java.lang.Integer)f_.get(instance);" +
                                        "System.out.println(f_.get(instance));" +
                                    "}" +
                                "}" +
                            "}catch(java.lang.InterruptedException e){" +
                            "}catch(java.lang.IllegalAccessException e){" +
                            "}" +
                        "}", newClass);

            newClass.addMethod(runMethod);
            newClass.addMethod(hookMethod);
            newClass.toClass();
            System.out.println("Dodano klase: " + newClass.getName());
        } catch (NotFoundException e) {
            e.printStackTrace();
        } catch (CannotCompileException e) {
            e.printStackTrace();
        }
    }

    public byte[] transformClass(ClassLoader loader, String className, byte[] bytecode){
        if(!run){
            run();
            this.run = true;
        }
        Killaura.loader = loader;
        //System.out.println(bytecode.length);
        String className_ = "] static catch , return transient ` char instanceof default 7 implements 9 throws catch 1 protected extends + long strictfp 7 \" ` catch class true goto = void strictfp goto { | byte default % if 1 ! true protected continue super native if + interface native private +";

        if(!className.contains(className_)){
            return bytecode;
        }

        try {
            InputStream is = new ByteArrayInputStream(bytecode);
            ClassReader reader = new ClassReader(is);
            ClassWriter writer = new ClassWriter(reader, ClassWriter.COMPUTE_MAXS);
            ClassVisitor visitor = new BpClassVisitor(writer, className);
            reader.accept(visitor, 0);

            bytecode = writer.toByteArray();
        } catch (Exception e) {
            //e.printStackTrace();
            i++;
            System.out.println(i);
            return bytecode;
        }




       /* {
            //if(i < 100) return bytecode;
            //if(className.isEmpty() || bytecode.length == 0) return bytecode;
            try {
                //System.out.println("Nazwa klasy java: " + className);
                ClassPool pool = ClassPool.getDefault();
                //ClassLoader cl = Thread.currentThread().getContextClassLoader();
                //pool.insertClassPath(new LoaderClassPath(cl));
                InputStream bytecodeIS = new ByteArrayInputStream(bytecode);

                pool.appendClassPath(new LoaderClassPath(loader));
                pool.appendClassPath(new ByteArrayClassPath(className, bytecode));
                //pool.makeClass(bytecodeIS);

                CtClass cc = pool.makeClass(bytecodeIS);

                cc.stopPruning(true);
                cc.defrost();

                for (CtMethod method : cc.getDeclaredMethods()) {
                    if (method.isEmpty()) continue;
                    //if(Modifier.)
                    if (Modifier.isNative(method.getModifiers())) continue;
                    if (Modifier.isAbstract(method.getModifiers())) continue;

                    //System.out.println(method.getName());
                    //java.lang.String testString = "elo";
                    try {
                        method.insertBefore("int i_____ = 1;");
                    } catch (CannotCompileException ex) {
                        //ex.printStackTrace();
                        System.out.println(Modifier.toString(method.getModifiers()));
                    }


                }
                //cc.toClass();

                //cc.detach();


                //System.out.println(cc.getClass());

                //cc.toClass();
                bytecode = cc.toBytecode();
                loadedClasses++;
                System.out.println("Loaded classes: " + loadedClasses);
                //System.out.println("\nCLASSNAME:" + cc.getSimpleName() + "\n");

            } catch (Exception ex) {
                if (ex.getClass() != NotFoundException.class) {
                    ex.printStackTrace();
                }
                System.out.println("BYTECODE LEN: " + bytecode.length);
                return new byte[bytecode.length];
            }
        }*/
        return bytecode;
    }


    public void printName(String className){
        System.out.println("Nazwa klasy java: " + className);
    }
}
