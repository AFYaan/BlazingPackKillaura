package pl.afyaan;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BpMethodVisitor extends MethodVisitor {
    private String className;
    private String methodName;
    private String signature;
    public BpMethodVisitor(MethodVisitor mv, String className, String methodName, String signature) {
        super(Opcodes.ASM9, mv);
        this.className = className;
        this.methodName = methodName;
        this.signature = signature;
    }




    private final Label start = new Label(),
            end = new Label(),
            handler = new Label();
    @Override
    public void visitCode() {


        //mv.visitFieldInsn(Opcodes.GETSTATIC, "pl/afyaan/ThreadFilter", "arg1", "Ljava/lang/String;");
        //mv.visitFieldInsn(Opcodes.GETSTATIC, "pl/afyaan/ThreadFilter", "arg2", "Ljava/lang/String;");
        String className_ = "] static catch , return transient ` char instanceof default 7 implements 9 throws catch 1 protected extends + long strictfp 7 \" ` catch class true goto = void strictfp goto { | byte default % if 1 ! true protected continue super native if + interface native private +";

        String methodName_ = "continue static";
        String desc = "class if ] for throw true if false int 3 , true { 6 protected ] this \" native short ^ private break interface interface double ? for 8 const 6 goto 9 $ package boolean ! ! volatile ' 4 import goto throws 5 goto private extends ~ final while boolean continue";
        String ignoreDesc = "null break while switch 3 package ' else { % super for ` boolean % const 6 , break else = float case goto static switch ' true 7 9 try , try ~ ? % null instanceof 3 false 3 public abstract class super 7 % instanceof throw throws super ' public synchronized";


        boolean search = false;
        if(className.contains(className_) && methodName.contains("if volatile") && signature.contains(ignoreDesc)) {
            super.visitCode();

            if(!search) {
                System.out.println("sdad");
                mv.visitLdcInsn(signature);
                mv.visitVarInsn(Opcodes.ALOAD, 1);
                mv.visitVarInsn(Opcodes.ALOAD, 0);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "pl/afyaan/ThreadManager", "clickAura", "(Ljava/lang/String;Ljava/lang/Object;Ljava/lang/Object;)V", false);
            }else {
                System.out.println("ta");
                mv.visitLdcInsn(className);
                mv.visitLdcInsn(methodName);
                mv.visitLdcInsn(signature);
                mv.visitMethodInsn(Opcodes.INVOKESTATIC, "pl/afyaan/ThreadManager", "methodHook", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", false);
            }
       }
       // mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
       // mv.visitLdcInsn("chuj");
       // mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
        //printInfo(className + "->" + methodName);

        /*printThreadInfo();

        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "err", "Ljava/io/PrintStream;");
        mv.visitLdcInsn("Stack trace of current thread using dumpStack() method");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

        //Dumping the thread stack trace
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/Throwable");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/Throwable", "<init>", "()V", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Throwable", "printStackTrace", "()V", false);*/


    }



    private void printInfo(String info) {
        mv.visitMaxs(0, 0);
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("[AGENT] - " + info + " [TIME]: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitTypeInsn(Opcodes.NEW, "java/util/Date");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/util/Date", "<init>", "()V", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/util/Date", "getTime", "()J", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    private void printThreadInfo() {
        printThreadID();
        printThreadName();
        printThreadGroup();
    }

    private void printThreadID() {
        //printing thread id
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("Thread Id: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Thread", "getName", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);

    }

    private void printThreadName() {
        //printing thread name
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("Thread Name: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Thread", "getId", "()J", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(J)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

    private void printThreadGroup() {
        //printing thread group
        mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
        mv.visitTypeInsn(Opcodes.NEW, "java/lang/StringBuilder");
        mv.visitInsn(Opcodes.DUP);
        mv.visitMethodInsn(Opcodes.INVOKESPECIAL, "java/lang/StringBuilder", "<init>", "()V", false);
        mv.visitLdcInsn("Thread Group: ");
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/String;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "currentThread", "()Ljava/lang/Thread;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/Thread", "getThreadGroup", "()Ljava/lang/ThreadGroup;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "append", "(Ljava/lang/Object;)Ljava/lang/StringBuilder;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/lang/StringBuilder", "toString", "()Ljava/lang/String;", false);
        mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
    }

   /* @Override
    public void visitInsn(int opcode) {
        if (opcode == Opcodes.RETURN) {
            printInfo("Finished executing %s %s function");
        }
        super.visitInsn(opcode);
    }*/
}
