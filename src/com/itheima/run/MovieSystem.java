package com.itheima.run;

import com.itheima.bean.Business;
import com.itheima.bean.Customer;
import com.itheima.bean.Movie;
import com.itheima.bean.User;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
                    showAllMovies();
                    break;
                case "2":

                    break;
                case "3":
                    // 评分功能
                    score();
                    break;
                case "4":
                    // 购票功能
                    buyMovie();
                    break;
                case "5":
                    return; // 干掉方法
                default:
                    System.out.println("不存在该命令！！");
                    break;
            }
        }
    }

    // 评分
    private static void score() {

    }

    // 买票
    private static void buyMovie() {
        showAllMovies();
        System.out.println("---------用户购票功能---------");
        while (true) {
            System.out.println("请输入买票的门店");
            String shopName=SYS_SC.nextLine();
            Business business=getBusinessByShopName(shopName);
            if(business==null){
                System.out.println("对不起，没有该店铺");
            } else {
                List<Movie> movies=ALL_MOVIES.get(business);
                if(movies.size()>0){
                    // 选片
                    while (true) {
                        System.out.println("请您输入需要购买的电影名称");
                        String movieName=SYS_SC.nextLine();
                        Movie movie=getMovieByShopNameAndMovieName(movies,movieName);
                        if(movie!=null){
                            // 开始购票并且判断是否有余票
                            while (true) {
                                System.out.println("请输入要购买的票数");
                                String number=SYS_SC.nextLine();
                                int buyNumber=Integer.valueOf(number);
                                if(buyNumber<=movie.getNumber()){
                                    double money= BigDecimal.valueOf(movie.getPrice()).multiply(BigDecimal.valueOf(buyNumber)).doubleValue();
                                    if(money<=loginUser.getMoney()){
                                    System.out.println("购票成功，您买了"+movie.getName()+"，您买了"+buyNumber+"张票");
                                    movie.setNumber(movie.getNumber()-buyNumber);
                                    loginUser.setMoney(loginUser.getMoney()-money);
                                    business.setMoney(business.getMoney()+money);
                                    return;
                                    } else {
                                        System.out.println("钱不够，是否继续买票，y/n");
                                        String op=SYS_SC.nextLine();
                                        if(!op.equals("y"))return;
                                    }
                                } else{
                                    System.out.println("票数不够，当前最多可以买"+movie.getNumber()+"张电影票");
                                    System.out.println("是否继续买票，y/n");
                                    String op=SYS_SC.nextLine();
                                    if(!op.equals("y"))return;
                                }
                            }
                        } else {
                            System.out.println("电影名称有误");
                        }
                    }
                } else{
                    System.out.println("此店铺没有电影，是否继续买票，y/n");
                    String op=SYS_SC.nextLine();
                    if(!op.equals("y"))return;
                }
            }
        }
    }

    private static Movie getMovieByShopNameAndMovieName(List<Movie> movies, String movieName) {
        for(Movie movie:movies){
            if(movie.getName().contains(movieName)){
                return movie;
            }
        }
        return null;
    }

    // 根据商家名称查找商家对象
    private static Business getBusinessByShopName(String shopName) {
        Set<Business> businesses = ALL_MOVIES.keySet();
        for(Business business:businesses){
            if(business.getShopName().contains(shopName)){
                return business;
            }
        }
        return null;
    }


     // 展示所有商家的电影排片信息
    private static void showAllMovies() {
        System.out.println("-------展示所有商家排片信息-------");
        ALL_MOVIES.forEach((business, movies) -> {
            System.out.println(business.getShopName() + "\t\t电话" + business.getPhone() + "\t\t地址" + business.getAddress());
            System.out.println("片名\t\t\t主演\t\t\t时长\t\t\t评分\t\t\t票价\t\t\t余票数量\t\t\t放映时间");

            if (movies.size() > 0) {
                for (Movie movie : movies) {
                    System.out.println(movie.getName() + "\t\t\t" + movie.getActor() + "\t\t" + movie.getTime()
                            + "\t\t" + movie.getScore() + "\t\t" + movie.getPrice() + "\t\t" + movie.getNumber() + "\t\t"
                            + sdf.format(movie.getStartTime()));
                }
            }
        });
    }

    // 展示商家的界面
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
                    addMovie();
                    break;
                case "3":
                    // 下架电影信息
                    deleteMovie();
                    break;
                case "4":
                    // 修改电影信息
                    uodateMovie();
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

    // 下架影片
    private static void deleteMovie() {
        System.out.println("-------下架电影-------");
        Business business=(Business)loginUser;
        List<Movie> movies=ALL_MOVIES.get(business);
        if(movies.size()==0){
            System.out.println("当前没有电影可以下架");
            return;
        }
        while (true) {
            System.out.println("请输入需要下架的电影名称");
            String movieName=SYS_SC.nextLine();
            // 查询是否存在这个影片对象
            Movie movie=getMovieByName(movieName);
            if(movie!=null){
                movies.remove(movie);
                System.out.println("您的店铺成功下架电影："+movie.getName());
                showBusinessInfo();
            } else {
                System.out.println("您的店铺没有这个电影，下架失败");
                System.out.println("请问继续下架吗？y/n");
                String command = SYS_SC.nextLine();
                if(!command.equals("y")){
                        System.out.println("好的！");
                        return;
                }
            }
        }
    }

    // 更新电影信息
    private static void uodateMovie() {
        System.out.println("-------修改电影信息-------");
        Business business=(Business)loginUser;
        List<Movie> movies=ALL_MOVIES.get(business);
        if(movies.size()==0){
            System.out.println("当前没有电影可以修改");
            return;
        }
        while (true) {
            System.out.println("请输入需要修改的电影名称");
            String movieName=SYS_SC.nextLine();
            // 查询是否存在这个影片对象
            Movie movie=getMovieByName(movieName);
            if(movie!=null){
                System.out.println("请您输入修改后的片名：");
                String name  = SYS_SC.nextLine();
                System.out.println("请您输入修改后的主演：");
                String actor  = SYS_SC.nextLine();
                System.out.println("请您输入修改后的时长：");
                String time  = SYS_SC.nextLine();
                System.out.println("请您输入修改后的票价：");
                String price  = SYS_SC.nextLine();
                System.out.println("请您输入修改后的票数：");
                String totalNumber  = SYS_SC.nextLine();
                while(true){
                    try{
                        System.out.println("请您输入修改后的影片放映时间：");
                        String stime  = SYS_SC.nextLine();
                        movie.setName(name);
                        movie.setActor(actor);
                        movie.setTime(Double.valueOf(time));
                        movie.setPrice(Double.valueOf(price));
                        movie.setNumber(Integer.valueOf(totalNumber));
                        movie.setStartTime(sdf.parse(stime));
                        System.out.println("您成功修改了电影："+movie.getName()+"的信息");
                        showBusinessInfo();
                        return;
                    } catch (ParseException e) {
                            e.printStackTrace();
                            LOGGER.error("时间解析出了毛病");
                        }
                }
            } else {
                System.out.println("您的店铺没有这个电影，修改失败");
                System.out.println("请问继续修改吗？y/n");
                String command = SYS_SC.nextLine();
                switch (command) {
                    case "y":
                        break;
                    default:
                        System.out.println("好的！");
                        return;
                }
            }
        }
    }

    // 添加电影
    private static void addMovie() {
        System.out.println("-------上架电影-------");
        Business business=(Business)loginUser;
        List<Movie> movies=ALL_MOVIES.get(business);
        System.out.println("================上架电影====================");
        // 根据商家对象(就是登录的用户loginUser)，作为Map集合的键 提取对应的值就是其排片信息 ：Map<Business , List<Movie>> ALL_MOVIES

        System.out.println("请您输入片名：");
        String name  = SYS_SC.nextLine();
        System.out.println("请您输入主演：");
        String actor  = SYS_SC.nextLine();
        System.out.println("请您输入时长：");
        String time  = SYS_SC.nextLine();
        System.out.println("请您输入票价：");
        String price  = SYS_SC.nextLine();
        System.out.println("请您输入票数：");
        String totalNumber  = SYS_SC.nextLine();
        while (true) {
            try {
                System.out.println("请您输入影片放映时间：");
                String stime  = SYS_SC.nextLine();
                Movie movie = new Movie(name, actor ,Double.valueOf(time) , Double.valueOf(price),
                        Integer.valueOf(totalNumber) ,  sdf.parse(stime));
                movies.add(movie);
                System.out.println("您已经成功上架了：《" + movie.getName() + "》");
                return; // 直接退出去
            } catch (ParseException e) {
                e.printStackTrace();
                LOGGER.error("时间解析出了毛病");
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
        System.out.println(business.getShopName()+"\t\t电话"+business.getPhone()+"\t\t地址"+business.getAddress()+"\t\t余额"+business.getMoney());
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


    private static User getUserByloginName(String loginName){
        for(User user:ALL_USERS){
            if(loginName.equals(user.getLoginName())){
                return user;
            }
        }
        return null;    //查无此用户
    }

    /**
     * 根据电影名字查找电影
     * @param movieName
     * @return
     */
    private static Movie getMovieByName(String movieName){
        Business business=(Business)loginUser;
        List<Movie> movies=ALL_MOVIES.get(business);
        if(movies.size()==0)
            return null;

        for(int i=movies.size()-1; i>=0; i--){
            if(movies.get(i).getName().contains(movieName)){
                return movies.get(i);
            }
        }
        return null;
    }
}
