package com.lv.demo.code;

/**
 * @desc:
 * @author: huichaolv@tencent.com
 * @create: 2021/6/29
 */
public class NodeUtils {

    public static void main(String[] args) {
        Node node = NodeBuilder.create(1, 2, 3, 4, 5);
        System.out.println(node);
        int length = length(node);
        Node firstTail = find(node, length / 2);
        Node reverse = reverse(firstTail.next);
        firstTail.next = null;//断开
        Node merge = merge(node, reverse);
        System.out.println(merge);
        //test find tail
        System.out.println(findTail(NodeBuilder.create(1, 2, 3, 4, 5)));
    }

    private static Node findTail(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            pre = cur;
            cur = cur.next;
        }
        return pre;
    }

    private static Node merge(Node h1, Node h2) {
        Node p2 = null, c1 = h1, c2 = h2, n1, n2;
        while (c1 != null && c2 != null) {
            n1 = c1.next;
            n2 = c2.next;
            c1.next = c2;
            c2.next = n1;
            p2 = c2;
            c1 = n1;
            c2 = n2;
        }
        if (c2 != null) {
            p2.next = c2;
        }
        return h1;
    }

    private static Node reverse(Node head) {
        Node pre = null;
        Node cur = head;
        while (cur != null) {
            Node next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    private static Node find(Node head, int index) {
        Node cur = head;
        for (int i = 0; i < index - 1; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private static int length(Node head) {
        int length = 0;
        Node cur = head;
        do {
            length++;
        } while ((cur = cur.next) != null);
        return length;
    }

    private static class NodeBuilder {

        public static Node create(int... ints) {
            Node pre = null;
            Node head = null;
            for (int i : ints) {
                Node cur = new Node(i, null);
                if (pre != null)
                    pre.next = cur;
                else
                    head = cur;
                pre = cur;
            }
            return head;
        }
    }

    private static class Node {

        private int value;
        private Node next;

        public Node() {
        }

        public Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return next == null ? value + "" : value + " => " + next.toString();
        }
    }
}
