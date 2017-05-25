package aop.asm;

import org.objectweb.asm.ClassAdapter;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;

public class AddSecurityCheckClassAdapter2 extends ClassAdapter{
	
	private String enhancedSuperName;
	
	public AddSecurityCheckClassAdapter2(ClassVisitor cv) {
		//Responsechain 的下一个 ClassVisitor，这里我们将传入 ClassWriter，
		//负责改写后代码的输出
		super(cv);
	}
	
	//在 AddSecurityCheckClassAdapter 类中，将重写 visit 方法
	public void visit(final int version, final int access, final String name,
			final String signature, final String superName,
			final String[] interfaces) {
		String enhancedName = name + "$EnhancedByASM";  //改变类命名
		enhancedSuperName = name; //改变父类，这里是”Account”
		super.visit(version, access, enhancedName, signature,
		enhancedSuperName, interfaces);
	}

	//改进 visitMethod 方法，增加对构造函数的处理
	public MethodVisitor visitMethod(final int access, final String name,
			final String desc, final String signature, final String[] exceptions) {
			MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
			MethodVisitor wrappedMv = mv;
			if (mv != null) {
				if (name.equals("operation")) {
					wrappedMv = new AddSecurityCheckMethodAdapter(mv);
				} else if (name.equals("<init>")) {
					wrappedMv = new ChangeToChildConstructorMethodAdapter(mv,enhancedSuperName);
				}
			}
			return wrappedMv;
		}

}
