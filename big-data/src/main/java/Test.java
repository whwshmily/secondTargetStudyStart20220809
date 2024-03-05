import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;
import java.util.Stack;

public class Test {

    private static Properties PROPERTIES = new Properties();

    private static String IsStart;

    private static long lastModified;

    static {
        try {
            initParam();
        } catch (Exception e) {
            System.out.println(e);
        }
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000 * 20);
                    initParam();
                } catch (Exception e) {
                    //
                }
            }
        }).start();
    }

    private static void initParam() throws Exception {
        InputStream stream = null;
//        System.out.println("init");
        URL url = Thread.currentThread().getClass().getResource("/ncpxs-param.properties");
//
//        System.out.println(url.getPath());
//        String urlPath = url.getPath();
        PROPERTIES.clear();
        File file = new File(url.getPath());
        long l = file.lastModified();
        System.out.println("modified:" + l);
        lastModified = l;
        stream = new FileInputStream(file);
        PROPERTIES.load(stream);
        IsStart = PROPERTIES.getProperty("thread-param");
        System.out.println(Thread.currentThread().getName() + ",自动任务更新参数成功,IsStart =" + IsStart);
        stream.close();
    }

    private static Integer revStack(Stack<Integer> stack) {
        Integer pop = stack.pop();
        if (stack.isEmpty()) {
            return pop;
        }
        Integer integer = revStack(stack);
        stack.push(pop);
        return integer;
    }

    private static void reverse(Stack<Integer> stack) {
        if(stack.isEmpty()){
            return;
        }
        Integer integer = revStack(stack);
        reverse(stack);
        stack.push(integer);
    }

    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(2);
        stack.push(1);
        System.out.println(stack);
        reverse(stack);
        System.out.println(stack);


//        List list = new ArrayList();
//        list.add(1);
//        list.add(3);
//        list.add(1);
//        list.add(2);
//        System.out.println(list.get(1));
//        new Test();
//        for (int i = 0; i < 2; i++) {
//            new Thread(() -> {
//                while (true) {
//                    System.out.println(Thread.currentThread().getName() + "---->" + IsStart);
//                    try {
//                        Thread.sleep(1000 * 8);
//                    } catch (Exception e) {
//                        //
//                    }
//                }
//            }, "testThread" + i).start();
//        }
//
//        while (true) {
//            //
//        }

    }


}
