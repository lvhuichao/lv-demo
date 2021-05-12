package demo.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/5/12
 */
public class CGLIBTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(User.class);
        enhancer.setCallback(new UserInterceptor());
        User user = (User) enhancer.create();
        user.hello();
    }

    public static class UserInterceptor implements MethodInterceptor {

        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
            System.out.println("before method invoke.");
            proxy.invokeSuper(obj, args);
            System.out.println("after method invoke.");
            return obj;
        }
    }

    public static class User {

        public void hello() {
            System.out.println("hello world");
        }
    }
}
