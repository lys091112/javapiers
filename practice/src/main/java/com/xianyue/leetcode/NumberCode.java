package com.xianyue.leetcode;

 /**
  * @author Xianyue
  */
public class NumberCode {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode head = new ListNode(0);
        ListNode tail = head;
        ListNode node1 = l1;
        ListNode node2 = l2;

        int sum = 0;
        while (node1 != null || node2 != null) {
            sum = sum / 10;
            if (node1 != null) {
                sum += node1.val;
                node1 = node1.next;
            }
            if (node2 != null) {
                sum += node2.val;
                node2 = node2.next;
            }
            tail.next = new ListNode(sum % 10);
            tail = tail.next;
        }
        if (sum / 10 == 1) {
//            tail.next = new ListNode(1);
            tail = new ListNode(1);  //这里如果使用tail的话，由于是链表形式，实际上是影响不到head链表的数据的，相当于tail又新创建了一个节点
        }

        return head.next;
    }

    public void printTest(ListNode node) {
        while(node != null) {
            System.out.println(node.val);
            node = node.next;
        }
    }

    static class ListNode {
        int      val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

     public static void main(String[] args) {
         NumberCode  code =new NumberCode();
         ListNode l1 = new ListNode(5);
         ListNode l2 = new ListNode(5);
         ListNode node = code.addTwoNumbers(l1,l2);
         code.printTest(node);
     }
}
