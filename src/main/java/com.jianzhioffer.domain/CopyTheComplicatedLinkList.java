package com.jianzhioffer.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * 复杂链表的复制
 *
 * 其实就是通过第一次遍历
 *  记住原始链表中所有元素 alreadyOriginalVisited
 *  新链表中所有元素 newalreadyNewVisited
 *  原始每个元素的random集合 pointerNumIndex
 *
 * 通过第二次遍历 设置新链表中 random
 *从pointerNumIndex 每个元素获取 alreadyOriginalVisited中的位置  ，
 * 再在newalreadyNewVisited集合中提取出相应元素
 */
public class CopyTheComplicatedLinkList {

   //原始集合中的元素集合
    private static final List<RandomListNode> alreadyOriginalVisited = new ArrayList<>();
    // 复制新集合中元素
    private static final List<RandomListNode> newalreadyNewVisited = new ArrayList<>();
    // random 原始元素记录 获取位置信息
    private static final List<RandomListNode> pointerNumIndex = new ArrayList<>();

    public RandomListNode Clone(RandomListNode pHead)
    {
        if(pHead==null) return null;
        /**
         * 清空初始数据集合
         *
         */
        alreadyOriginalVisited.clear();
        newalreadyNewVisited.clear();
        pointerNumIndex.clear();
        RandomListNode xHead = new RandomListNode(pHead.label);
        RandomListNode nxHead = xHead;
        RandomListNode npHead = pHead;
        alreadyOriginalVisited.add(npHead);
        pointerNumIndex.add(npHead.random);
        newalreadyNewVisited.add(nxHead);
        npHead = npHead.next;
        while (npHead != null) {
            alreadyOriginalVisited.add(npHead);
            pointerNumIndex.add(npHead.random);
            RandomListNode randomListNode = new RandomListNode(npHead.label);
            nxHead.next = randomListNode;
            nxHead = randomListNode;
            newalreadyNewVisited.add(nxHead);
            npHead = npHead.next;
        }
        RandomListNode nnxHead = xHead;
        //设置 每个节点的random 节点
        int count = 0;
        while (pHead != null) {
            RandomListNode randomListNode = pointerNumIndex.get(count++);
            int i = alreadyOriginalVisited.indexOf(randomListNode);
            if (i != -1) {
                nnxHead.random = newalreadyNewVisited.get(i);
            }
            pHead = pHead.next;
            nnxHead = nnxHead.next;
        }
        return xHead;
    }


    static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }


    public static void main(String... args) {

        int[] pas = new int[]{1, 2, 3, 4, 5};
        RandomListNode root = null;
        RandomListNode randomListNode1 = new RandomListNode(1);
        root = randomListNode1;
        RandomListNode randomListNode2 = new RandomListNode(2);
        RandomListNode randomListNode3 = new RandomListNode(3);
        RandomListNode randomListNode4 = new RandomListNode(4);
        RandomListNode randomListNode5 = new RandomListNode(5);
        randomListNode1.next = randomListNode2;
        randomListNode2.next = randomListNode3;
        randomListNode3.next = randomListNode4;
        randomListNode4.next = randomListNode5;
        randomListNode5.random = randomListNode3;
        randomListNode3.random = randomListNode2;

        RandomListNode clone = new CopyTheComplicatedLinkList().Clone(root);
        System.out.println(clone);
    }
}
