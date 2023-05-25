package com.itheima.run;

import com.itheima.bean.Business;
import com.itheima.bean.Customer;
import com.itheima.bean.Movie;
import com.itheima.bean.User;

import java.util.*;

public class MovieSystem {
    /**
     * 1、定义存储用户数据的容器
     */
    // 2、存储商家和顾客
    private static final List<User> ALL_USERS=new ArrayList<>();
    // 3、存储系统全部商家和排片信息
    private static Map<Business, List<Movie>> ALL_MOVIES=new HashMap<>();
    // 4、准备一些测试数据
    static {
        Customer c1 = new Customer();
        c1.setLoginName("zyf888");
        c1.setPassWord("123456");
        c1.setUserName("黑马刘德华");
        c1.setSex('男');
        c1.setMoney(10000);
        c1.setPhone("110110");
        ALL_USERS.add(c1);

        Customer c2 = new Customer();
        c2.setLoginName("gzl888");
        c2.setPassWord("123456");
        c2.setUserName("黑马关之琳");
        c2.setSex('女');
        c2.setMoney(2000);
        c2.setPhone("111111");
        ALL_USERS.add(c2);

        Business b1 = new Business();
        b1.setLoginName("baozugong888");
        b1.setPassWord("123456");
        b1.setUserName("黑马包租公");
        b1.setMoney(0);
        b1.setSex('男');
        b1.setPhone("110110");
        b1.setAddress("火星6号2B二层");
        b1.setShopName("甜甜圈国际影城");
        ALL_USERS.add(b1);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies1 = new ArrayList<>();
        ALL_MOVIES.put(b1 , movies1); // b = []

        Business b2 = new Business();
        b2.setLoginName("baozupo888");
        b2.setPassWord("123456");
        b2.setUserName("黑马包租婆");
        b2.setMoney(0);
        b2.setSex('女');
        b2.setPhone("110110");
        b2.setAddress("火星8号8B八层");
        b2.setShopName("巧克力国际影城");
        ALL_USERS.add(b2);
        // 注意，商家一定需要加入到店铺排片信息中去
        List<Movie> movies2 = new ArrayList<>();
        ALL_MOVIES.put(b2 , movies2); // b2 = []
    }
    public static final Scanner SYS_SC=new Scanner(System.in);
    public static void main(String[] args) {
        ALL_USERS;
    }

    /**
     * 首页
     */
    public void showMain(){
        while (true) {
            System.out.println("-----黑马电影首页-----");
            System.out.println("1、登录");
            System.out.println("2、用户注册");
            System.out.println("3、商家注册");
            System.out.println("请输入操作命令：");
            String command=SYS_SC.nextLine();
            switch(command){
                case "1":
                    // 登录
                    login();
                    break;
                case "2":
                    break;
                case "3":
                    break;
                default:
                    System.out.println("命令有误，请重新输入！");
            }
        }
    }
    private static void login(){
        while (true) {
            System.out.println("请输入用户名：");
            String loginName=SYS_SC.nextLine();
            System.out.println("请输入密码：");
            String loginPassword=SYS_SC.nextLine();
            // 1、根据用户名查询用户对象
            User u=getUserByloginName(loginName);
            // 2、判断用户对象是否存在
            if(u!=null){
                // 3、比对密码是否正确
                if(u.getPassword().equals(loginPassword)){
                    // 4、登录成功
                    // 5、判断用户的真实类型是商家还是顾客
                    if(u instanceof Customer){
                        // 当前登录的是顾客
                        showCustomerMain();
                    } else {
                        // 当前登录的是商家
                        showBusinessMain();
                    }
                    return;
                } else {
                    System.out.println("密码错误");
                }
            } else {
                System.out.println("用户名错误，请重新输入");
            }
        }
    }

    /**
     * 展示商家的界面
     */
    private static void showBusinessMain() {
    }

    /**
     * 展示顾客的界面
     */
    private static void showCustomerMain() {
    }

    public static User getUserByloginName(String loginName){
        for(User user:ALL_USERS){
            if(loginName.equals(user.getLoginName())){
                return user;
            }
        }
        return null;    //查无此用户
    }
}
