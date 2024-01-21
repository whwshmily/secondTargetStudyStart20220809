package node;

//用数组实现栈和队列
public class NodeCode2 {

    private static class MyStack {

        private int[] arr = new int[5];

        private int size;

        private boolean isEmpty() {
            return size == 0;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return arr[size - 1];
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return arr[--size];
        }

        public void add(int value) {
            if (size == arr.length) {
                throw new RuntimeException("over flows");
            }
            arr[size++] = value;
        }

    }

    private static class MyQueue {

        private int[] arr = new int[5];

        private int size;

        private int startPoint;

        private int endPoint;

        private int limit = arr.length;

        private boolean isEmpty() {
            return size == 0;
        }

        public int peek() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            return arr[startPoint];
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("stack is empty");
            }
            if (startPoint == limit) {
                startPoint = 0;
            }
            size--;
            return arr[startPoint++];
        }

        public void add(int value) {
            if (size == limit) {
                throw new RuntimeException("over flows");
            }
            if (endPoint == limit) {
                endPoint = 0;
            }
            arr[endPoint++] = value;
            size++;
        }

    }

    public static void main(String[] args) {
        MyQueue stack = new MyQueue();
        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
//        stack.add(6);
        System.out.println(stack.peek());
//        System.out.println(stack.toSting());
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "  ");
//            System.out.println(stack.peek() + "---->" + stack.size);
        }
        stack.add(6);
        stack.add(7);
        stack.add(8);
        stack.add(9);
        stack.add(10);
        while (!stack.isEmpty()) {
            System.out.print(stack.pop() + "  ");
//            System.out.println(stack.peek() + "---->" + stack.size);
        }
    }

}
