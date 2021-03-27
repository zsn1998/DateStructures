package cn.sz.link;

import java.awt.desktop.ScreenSleepEvent;
import java.awt.geom.FlatteningPathIterator;
import java.util.List;
import java.util.Stack;
import java.util.prefs.NodeChangeEvent;

public class SingleLinkedListDemo {
    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        HeroNode hero5 = new HeroNode(5, "宋江5", "及时雨");
        HeroNode hero6 = new HeroNode(6, "卢俊义6", "玉麒麟");
        HeroNode hero7 = new HeroNode(7, "吴用7", "智多星");
        HeroNode hero8 = new HeroNode(8, "林冲8", "豹子头");

        SingleLinkedList singleLinkedList = new SingleLinkedList();
        SingleLinkedList singleLinkedList1 = new SingleLinkedList();
        singleLinkedList1.addByOrder(hero5);
        singleLinkedList1.addByOrder(hero6);
        singleLinkedList1.addByOrder(hero7);
        singleLinkedList1.addByOrder(hero8);

//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);
        //测试有序插入
        singleLinkedList.addByOrder(hero2);
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
//        singleLinkedList.list();

//        HeroNode hero5 = new HeroNode(2, "林4", "豹子头");
//        System.out.println("修改后~~~~\n");
//        singleLinkedList.update(hero5);
//        singleLinkedList.list();
//        System.out.println("删除后\n");
//        singleLinkedList.del(2);
//        singleLinkedList.list();
//        System.out.println(getLength(singleLinkedList.getHead()));
//        System.out.println(findLastNode(singleLinkedList.getHead(),2));
//        reverseList(singleLinkedList.getHead());
//        singleLinkedList.list();
//            printReverseList(singleLinkedList.getHead());
        SingleLinkedList mergeTwoList = mergeTwoList(singleLinkedList1, singleLinkedList);
        mergeTwoList.list();

    }

    /**
     * 求单链表有效节点的个数
     * @param heroNode
     * @return 节点的个数
     */
    public static int getLength(HeroNode heroNode){
            if(heroNode.next==null){
                return 0;
            }
            int length=0;
            HeroNode cur=heroNode.next;
            while (cur!=null){
                length++;
                cur=cur.next;
            }
            return length;
    }

    /**
     * 查找倒数第n个节点
     * @param head
     * @param index
     * @return
     */
    public static HeroNode findLastNode(HeroNode head,int index){
        if(head.next==null){
            return null;
        }
        int size=getLength(head);
        if(index<=0||index>size){
            return null;
        }
        HeroNode cur=head.next;
        for(int i=0;i<size-index;i++){
            cur=cur.next;
        }
        return cur;
    }

    /**
     * 将单链表反转
     * @param head
     */
    public static void reverseList(HeroNode head){
        if(head.next==null||head.next.next==null){
            return;
        }
        HeroNode cur=head.next;
        HeroNode next=null;
        HeroNode reverseNode = new HeroNode(0, "", "");
        while (cur!=null){
            next=cur.next;
            cur.next=reverseNode.next;
            reverseNode.next=cur;
            cur=next;
        }
        head.next=reverseNode.next;
    }

    /**
     * 倒序打印单链表
     * @param head
     */
    public static void printReverseList(HeroNode head){
        if(head.next==null){
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur=head.next;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }

    /**
     * 合并两个有序单链表
     * @param singleLinkedList1
     * @param singleLinkedList2
     * @return
     */
    public static SingleLinkedList mergeTwoList(SingleLinkedList singleLinkedList1,SingleLinkedList singleLinkedList2){
      HeroNode heroNode=new HeroNode(0,"","");//新链表的头节点
      HeroNode pre= heroNode;//指向当前待排序节点的前一个节点
      SingleLinkedList singleLinkedList=new SingleLinkedList();
      singleLinkedList.setHead(heroNode);//设置新链表的头节点;
      HeroNode heroNode1=singleLinkedList1.getHead().next;
      HeroNode heroNode2=singleLinkedList2.getHead().next;
      while (heroNode1!=null&&heroNode2!=null){
          if(heroNode1.no<heroNode2.no){
              pre.next=heroNode1;
              heroNode1=heroNode1.next;
          }else {
              pre.next=heroNode2;
              heroNode2=heroNode2.next;
          }
          pre=pre.next;
      }
    pre.next=(heroNode1==null)?heroNode2:heroNode1;
      return singleLinkedList;
    }
}
class SingleLinkedList{
    private HeroNode head=new HeroNode(0,"","");
    public void setHead(HeroNode head){
        this.head=head;
    }
    public HeroNode getHead() {
        return head;
    }

    public void add(HeroNode heroNode){
        //辅助变量temp
        HeroNode temp=head;
        while (true){
        if(temp.next==null){
            break;
        }
        temp=temp.next;
        }
        temp.next=heroNode;
    }

    public void addByOrder(HeroNode heroNode){

        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no>heroNode.no){
                break;
            }
            else if(temp.next.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            System.out.printf("准备插入的英雄的编号%d已经存在了，不能加入\n",heroNode.no);
        }
        else {
            heroNode.next=temp.next;
            temp.next=heroNode;

        }
    }

    public void update(HeroNode heroNode){
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        HeroNode temp=head.next;
        boolean flag=false;
        while (true){
            if(temp==null){
                break;
            }
            if(temp.no==heroNode.no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.name= heroNode.name;
            temp.nickName= heroNode.nickName;
        }else{
            System.out.printf("编号为%d的节点没找到，不能修改\n",heroNode.no);
        }
    }
    public void del(int no){
        HeroNode temp=head;
        boolean flag=false;
        while (true){
            if(temp.next==null){
                break;
            }
            if(temp.next.no==no){
                flag=true;
                break;
            }
            temp=temp.next;
        }
        if(flag){
            temp.next=temp.next.next;
        }else {
            System.out.println("找不到要删除的节点");
        }
    }

    public void list(){
        //判断链表是否为空
        if(head.next==null){
            System.out.println("链表为空");
            return;
        }
        //头节点不能动，定义临时变量
        HeroNode temp=head.next;
        while (true){
            if(temp==null){
                break;
            }
            System.out.println(temp);
            temp=temp.next;
        }
    }


}
class HeroNode {
    public  int no;
    public  String name;
    public  String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }


    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}

