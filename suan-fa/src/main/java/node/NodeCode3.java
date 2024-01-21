package node;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class NodeCode3 {

    /**
     * 用队列实现栈
     * 用两个队列实现，一个队列添加元素data，当需要取出元素的时候
     * 将data数据留下一个其余全部压入help队列 然后弹出
     * 然后 help队列变成data,data变成help
     */
    private static class MyStack {
        private Queue data = new LinkedList();
        private Queue help = new LinkedList();

        public boolean isEmpty() {
            return data.isEmpty() && help.isEmpty();
        }

        public void add(int value) {
            data.add(value);
        }

        public int pop() {
            if (isEmpty()) {
                throw new RuntimeException("no data");
            }

            while (data.size() != 1) {
                help.add(data.poll());
            }

            int result = (Integer) data.poll();
            Queue tmp = help;
            help = data;
            data = tmp;
            return result;
        }

    }

    /**
     * 用栈实现队列
     * 使用两个栈，一个进行添加元素 当需要取元素时把这个栈加入到另一个栈中 然后弹出
     * 当另一个栈中有元素的时候，需要先弹完，才能继续往里面加入
     */
    private static class MyQueue {
        private Stack push = new Stack();
        private Stack pop = new Stack();

        public void push(int value) {
            push.push(value);
        }

        public int peek() {
            if (push.isEmpty() && pop.isEmpty()) {
                throw new RuntimeException("no data");
            }
            if (pop.isEmpty()) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
            return (Integer) pop.peek();
        }


        public int pop() {
            if (push.isEmpty() && pop.isEmpty()) {
                throw new RuntimeException("no data");
            }
            if (pop.isEmpty()) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
            return (Integer) pop.pop();
        }

        public boolean isEmpty() {
            return push.isEmpty() && pop.isEmpty();
        }
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue();
        queue.push(1);
        queue.push(2);
        System.out.println(queue.peek());
        queue.push(3);
        queue.push(4);
        queue.push(5);
        while (!queue.isEmpty()) {
            System.out.print(queue.pop() + "  ");
        }
        System.out.println();
        MyStack stack = new MyStack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);
        stack.add(5);
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+"---");
        }

    }
}
