package proxy;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLibClass {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "E:\\studyNeed\\git repository\\secondTargetStudyStart20220809\\class");
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(Test.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
//                System.out.println(o);
//                method.invoke(o,objects);
//                System.out.println("intercept");
//                System.out.println(o.toString());
//                methodProxy.invokeSuper(o,objects);
                System.out.println("intercept");
                return null;
            }
        });
        Test o = (Test) enhancer.create();
        o.move();
    }

}
interface Test{
    public void move();
}