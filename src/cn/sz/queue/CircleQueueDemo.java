package cn.sz.queue;

import java.util.Scanner;

public class CircleQueueDemo {
    public static void main(String[] args) {
//测试一把
        System.out.println("测试数组模拟环形队列的案例~~~");

        // 创建一个环形队列
        CircleQueue queue = new CircleQueue(4); //说明设置4, 其队列的有效数据最大是3
        char key = ' '; // 接收用户输入
        Scanner scanner = new Scanner(System.in);//
        boolean loop = true;
        // 输出一个菜单
        while (loop) {
            System.out.println("s(show): 显示队列");
            System.out.println("e(exit): 退出程序");
            System.out.println("a(add): 添加数据到队列");
            System.out.println("g(get): 从队列取出数据");
            System.out.println("h(head): 查看队列头的数据");
            key = scanner.next().charAt(0);// 接收一个字符
            switch (key) {
                case 's':
                    queue.shoQueue();
                    break;
                case 'a':
                    System.out.println("输出一个数");
                    int value = scanner.nextInt();
                    queue.addQueue(value);
                    break;
                case 'g': // 取出数据
                    try {
                        int res = queue.getQueue();
                        System.out.printf("取出的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h': // 查看队列头的数据
                    try {
                        int res = queue.headQueue();
                        System.out.printf("队列头的数据是%d\n", res);
                    } catch (Exception e) {
                        // TODO: handle exception
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e': // 退出
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("程序退出~~");
    }
    }



class CircleQueue{
    private int front;
    private int rear;
    private int maxSize;
    private int[] arr;

    public CircleQueue(int maxSize) {
        this.maxSize = maxSize;
        arr=new int[maxSize];
    }

    public boolean isFull(){
        return (rear+1)%maxSize==front;
    }

    public boolean isEmpty(){
        return rear==front;
    }

    public void addQueue(int n){
        if(isFull()){
            System.out.println("队列满，不能加入队列");
            return;
        }
        arr[rear]=n;
        rear=(rear+1)%maxSize;
    }

    public int getQueue(){
        if(isEmpty()){
            throw new RuntimeException("对列空，不能取出数据");
        }
        // 这里需要分析出 front是指向队列的第一个元素
        // 1. 先把 front 对应的值保留到一个临时变量
        // 2. 将 front 后移, 考虑取模
        // 3. 将临时保存的变量返回
        int vlaue=arr[front];
        front=(front+1)%maxSize;
        return vlaue;
    }

    public void shoQueue(){
    if(isEmpty()){
        System.out.println("队列空的，没有数据");
        return;
    }
    for (int i=front;i<front+size();i++){
        System.out.printf("arr[%d]==%d\n",i%maxSize, arr[i%maxSize]);
    }
    }

    public int size(){
    return (rear+maxSize-front)%maxSize;
    }

    public int headQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列空的，没有数据");

        }
        return arr[front];
    }
}

