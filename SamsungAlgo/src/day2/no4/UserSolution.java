package day2.no4;

import java.util.Optional;

class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next = null;
    }
}

public class UserSolution {

    private final static int MAX_NODE = 10000;

    private Node[] node = new Node[MAX_NODE];
    private int nodeCnt = 0;
    private Node head;
    private Node tail;

    public Node getNode(int data) {
        node[nodeCnt] = new Node(data);
        return node[nodeCnt++];
    }

    public void init() {
        head = getNode(-1);
        tail = head;
    }

    public void addNode2Head(int data) {
        Node newNode = getNode(data);
        newNode.next = head.next;
        head.next = newNode;
        if (tail == head) {
            tail = head.next;
        }
    }

    public void addNode2Tail(int data) {
        Node newNode = getNode(data);
        tail.next= newNode;
        tail = newNode;

    }

    public void addNode2Num(int data, int num) {
        if (num >= nodeCnt) {
            addNode2Tail(data);
            return;
        }

        Node curNode = head;
        Node beforeNode = head;
        for (int i=0;i <num; i++){
            beforeNode = curNode;
            curNode = curNode.next;
        }
        Node newNode = getNode(data);
        newNode.next = beforeNode.next;
        beforeNode.next = newNode;
    }

    public void removeNode(int data) {
        Node curNode = head;
        Node beforeNode = head;
        while(curNode != null)
        {
            if (curNode.data == data) {
                break;
            }
            beforeNode = curNode;
            curNode = curNode.next;
        }
        if (curNode == null) {
            return;
        }
        if (tail == curNode) {
            tail = beforeNode;
        }
        beforeNode.next = curNode.next;
        nodeCnt -= 1;
    }

    public int getList(int[] output) {
        Node curNode = head.next;
        System.out.println(head.data);
        System.out.println(tail.data);
        int idx = 0;
        while (curNode!=null){
            output[idx++] = curNode.data;
            curNode= curNode.next;
        }
        return idx;
    }
}