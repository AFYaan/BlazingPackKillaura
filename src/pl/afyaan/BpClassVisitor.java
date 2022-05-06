package pl.afyaan;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class BpClassVisitor extends ClassVisitor {
    private ClassVisitor cw;
    private String className;
    public BpClassVisitor(ClassWriter classVisitor, String className) {
        super(Opcodes.ASM9, classVisitor);
        this.cw = classVisitor;
        this.className = className;
    }



    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        //System.out.println("\n\n\n" + descriptor);
        MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
        //mv.
        if(name.equalsIgnoreCase("hashCode") || name.equalsIgnoreCase("equals")) return mv;
        if((access & (Opcodes.ACC_NATIVE | Opcodes.ACC_ABSTRACT)) != 0){
            return mv;
        }else{
            //return mv;
            return new BpMethodVisitor(mv, className, name, descriptor);
        }



    }
}
