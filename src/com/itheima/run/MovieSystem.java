package com.itheima.run;

import com.itheima.bean.Business;
import com.itheima.bean.Customer;
import com.itheima.bean.Movie;
import com.itheima.bean.User;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Logger;

public class MovieSystem {
    // 定义一个静态的User变量保存当前登录的用户
    public static User loginUser;
    public static SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    public static final Logger LOGGER= (Logger) LoggerFactory.getLogger("MovieSystem.class");
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
        showMain();
    }

    /**
     * 首页
     */
    public static void showMain(){
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
                    // 4、登录成功，记住登录成功的用户
                    loginUser=u;
                    LOGGER.info(u.getLoginName()+"登录系统");
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
     * 展示顾客的界面
     */
    private static void showCustomerMain() {
        while (true) {
            System.out.println("============黑马电影客户界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex()=='男'? "先生":"女士" + "欢迎您进入系统" +
                    "\t余额：" + loginUser.getMoney()));
            System.out.println("请您选择要操作的功能：");
            System.out.println("1、展示全部影片信息功能:");
            System.out.println("2、根据电影名称查询电影信息:");
            System.out.println("3、评分功能:");
            System.out.println("4、购票功能:");
            System.out.println("5、退出系统:");
            System.out.println("请输入您要操作的命令：");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息

                    break;
                case "2":
                    break;
                case "3":
                    // 评分功能
                    break;
                case "4":
                    // 购票功能
                    break;
                case "5":
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }
    /**
     * 展示商家的界面
     */
    private static void showBusinessMain() {
        while (true) {
            System.out.println("============黑马电影商家界面===================");
            System.out.println(loginUser.getUserName() + (loginUser.getSex()=='男'? "先生":"女士" + "欢迎您进入系统"));
            System.out.println("1、展示详情:");
            System.out.println("2、上架电影:");
            System.out.println("3、下架电影:");
            System.out.println("4、修改电影:");
            System.out.println("5、退出:");

            System.out.println("请输入您要操作的命令：");
            String command = SYS_SC.nextLine();
            switch (command){
                case "1":
                    // 展示全部排片信息
                    showBusinessInfo();
                    break;
                case "2":
                    // 上架电影信息
                    break;
                case "3":
                    // 下架电影信息
                    break;
                case "4":
                    // 修改电影信息
                    break;
                case "5":
                    System.out.println(loginUser.getUserName() +"请您下次再来啊~~~");
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    /**
     * 展示当前商家的详细信息
     */
    private static void showBusinessInfo() {
        System.out.println("--------商家详情界面--------");
        // 根据商家对象提取对应的值，商家对象作为键
        List<Movie> movies=ALL_MOVIES.get(loginUser);
        Business business=(Business)loginUser;
        System.out.println(business.getShopName()+"\t\t电话"+business.getPhone()+"\t\t地址"+business.getAddress());
        System.out.println("片名\t\t\t主演\t\t\t时长\t\t\t评分\t\t\t票价\t\t\t余票数量\t\t\t放映时间");

        if (movies.size()>0) {
            for (Movie movie : movies) {
                System.out.println(movie.getName()+"\t\t\t" + movie.getActor()+ "\t\t" + movie.getTime()
                        + "\t\t" + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t" + movie.getNumber() + "\t\t"
                        +   sdf.format(movie.getStartTime()));
        }
            } else {
                System.out.println("您的店铺当前无片可播");
            }
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
