package cn.sz.link;

import java.text.BreakIterator;
import java.util.concurrent.ForkJoinPool;

public class Josepfu {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        circleSingleLinkedList.addBoy(5);
        circleSingleLinkedList.listBoy();
        circleSingleLinkedList.count(1,2,5);
    }
}

class CircleSingleLinkedList{
    private Boy first;

    public void addBoy(int nums){
        if(nums<1){
            System.out.println("nums的值不对");
            return;
        }
        Boy curBoy=null;//辅助指针
        for (int i = 1; i <=nums; i++) {
            Boy boy = new Boy(i);
            if(i==1){
                first=boy;//初始化first节点
                boy.setNext(first);//构成环
                curBoy=first;//curboy指向第一个小孩
            }else {
                curBoy.setNext(boy);//将boy加到尾部
                boy.setNext(first);//构成环
                curBoy=boy;//curboy后移
            }
        }
    }

    /**
     *  约瑟夫问题
     * @param startNo 表示从第几个小孩开始
     * @param countNum 表示数几下
     * @param nums    表示最初有多少个小孩在圈中
     */
    public void count(int startNo,int countNum,int nums){
        if(first==null||startNo<1||startNo>nums){
            System.out.println("输入的参数有误");
            return;
        }
        //定义辅助指针
        Boy helper=first;
        //将辅助指针移到最后
        while (true){
            if(helper.getNext()==first){
                break;
            }
            helper=helper.getNext();
        }

        for (int i = 0; i < startNo-1; i++) {
            first=first.getNext();
            helper=helper.getNext();
        }
        while (true){
            if(helper==first){
                break;
            }
            for (int i = 0; i < countNum-1; i++) {
                first=first.getNext();
                helper=helper.getNext();
            }
            System.out.printf("小孩%d出圈\n",first.getNo());
            first=first.getNext();
            helper.setNext(first);
        }
        System.out.printf("最后留在圈中的小孩为%d\n",first.getNo());
    }


    public void listBoy(){
        if(first==null){
            System.out.println("没有小孩");
            return;
        }
        Boy curBoy=first;//辅助指针
        while (true){
            System.out.printf("小孩的编号%d\n",curBoy.getNo());
            if(curBoy.getNext()==first){
                break;//最后一个小孩
            }
            curBoy=curBoy.getNext();
        }
    }

}
class Boy{
    public Boy(int no) {
        this.no = no;

    }

    private int no;
    private Boy next;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}

