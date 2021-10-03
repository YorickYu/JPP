package com.yy.leecode;

import java.util.ArrayList;
import java.util.List;

// 同包下可用
class OutNode {

}

/**
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 *
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 *
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 * 示例：
 *
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 0 -> 8
 * 原因：342 + 465 = 807
 */
public class LinkNodeAdds {

    // 内部类，LinkNodeAdds.LinkNode
    public static class ListNode {
        private int val;
        private ListNode next;

        public ListNode(int val) {
            this.val = val;
        }

        public static String nodePrint(ListNode l) {
            StringBuffer stringBuffer = new StringBuffer();
            while (l.next != null) {
                stringBuffer.append(l.val);
                l = l.next;
            }
//            System.out.println(stringBuffer);
//            System.out.println(stringBuffer.toString());
            return stringBuffer.toString();
        }
    }


    /**
     *  输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
     *  输出：7 -> 0 -> 8
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
//        if (l1 == null) {return null;}

        ListNode targetNode = new ListNode(0);
        ListNode pinNode = targetNode;
        int overOne = 0;
        while (l1 != null || l2 != null || overOne != 0) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            pinNode.next = new ListNode((x + y + overOne) % 10);
            pinNode = pinNode.next;
            overOne = (x + y + overOne) / 10;

            if (l1.next != null) {
                l1 = l1.next;
            }
            if (l2.next != null) {
                l2 = l2.next;
            }
        }
        return targetNode.next;
    }

    public static void main(String[] args) {
        // test node1
        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(8);

        ListNode l2 = new ListNode(0);

        ListNode target = addTwoNumbers(l1, l2);
        System.out.println(ListNode.nodePrint(target));

        List<String> finalRecipeIds = new ArrayList<>();
        finalRecipeIds.add("123");
        List<String> strings = finalRecipeIds.subList(0, 20);
        System.out.println(strings);

    }


}
