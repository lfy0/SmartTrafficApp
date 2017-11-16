package com.example.nappy.util;

import com.example.nappy.beans.LifeBean;
import com.example.nappy.beans.LifeInfoBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liufangya on 2017/10/24.
 */

public class LifeUtils {

    private static String []Ultraviolet={"辐射较弱，涂擦 SPF12~15、PA+护肤品","涂擦 SPF 大于 15、 PA+防晒护肤品 ","尽量减少外出，需要涂 抹高倍数防晒霜"};//紫外线
    private static String []Catch={"温度低，风较大，较易发生 感冒，注意防护 ","无明显降温，感冒机率 较低"};//感冒
    private static String []Dress={"建议穿长袖衬 衫、单裤等服装 ","建议穿短袖衬衫、单裤等 服装 ","适合穿 T 恤、短薄外套 等夏季服装 "};//穿衣
    private static String []Movement={"气候适宜，推荐您进 行户外运动 ","易感人群应适当减少 室外活动 ","空气氧气含量低，请在 室内进行休闲运动 "};//运动
    private static String []air={"空气质量非常好，非常 适合户外活动，趁机出 去多呼吸新鲜空气 ","易感人群应 适当减少室 外活动","空气质量差，不适合户 外活动 "};//空气

    public static List<LifeBean> getLifeUtils(LifeInfoBean lifeInfoBean){
        List<LifeBean> lifeBeens=new ArrayList<>();
        LifeBean lifeBean1=new LifeBean();//紫外线
        LifeBean lifeBean2=new LifeBean();//感冒
        LifeBean lifeBean3=new LifeBean();//穿衣
        LifeBean lifeBean4=new LifeBean();//运动
        LifeBean lifeBean5=new LifeBean();//空气
        int light=lifeInfoBean.getLight();
        int temperature=lifeInfoBean.getTemperature();
        int co2=lifeInfoBean.getCo2();
        int pm2_5=lifeInfoBean.getPm2();
        if (light>0&&light<1000){
            lifeBean1.setJibie("弱");
            lifeBean1.setTishiInfo(Ultraviolet[0]);
        }else if (light>1000&&light<3000){
            lifeBean1.setJibie("中等");
            lifeBean1.setTishiInfo(Ultraviolet[1]);
        }else if (light>3000){
            lifeBean1.setJibie("强");
            lifeBean1.setTishiInfo(Ultraviolet[2]);
        }
        if (temperature<8){
            lifeBean2.setJibie("较易发");
            lifeBean2.setTishiInfo(Catch[0]);
        }else {
            lifeBean2.setJibie("少发");
            lifeBean2.setTishiInfo(Catch[1]);
        }
        if (temperature<12){
            lifeBean3.setJibie("冷");
            lifeBean3.setTishiInfo(Dress[0]);
        }else if (temperature>12&&temperature<21){
            lifeBean3.setJibie("舒适");
            lifeBean3.setTishiInfo(Dress[1]);
        }else {
            lifeBean3.setJibie("热");
            lifeBean3.setTishiInfo(Dress[2]);
        }
        if (co2>0&&co2<3000){
            lifeBean4.setJibie("适宜");
            lifeBean4.setTishiInfo(Movement[0]);
        }else if (co2>3000&&co2<6000){
            lifeBean4.setJibie("中");
            lifeBean4.setTishiInfo(Movement[1]);
        }else {
            lifeBean4.setJibie("较不宜");
            lifeBean4.setTishiInfo(Movement[2]);
        }
        if (pm2_5>0&&pm2_5<30){
            lifeBean5.setJibie("优");
            lifeBean5.setTishiInfo(air[0]);
        }else if (pm2_5>30&&pm2_5<100){
            lifeBean5.setJibie("良");
            lifeBean5.setTishiInfo(air[1]);
        }else {
            lifeBean5.setJibie("污染");
            lifeBean5.setTishiInfo(air[2]);
        }
        lifeBeens.add(lifeBean1);
        lifeBeens.add(lifeBean2);
        lifeBeens.add(lifeBean3);
        lifeBeens.add(lifeBean4);
        lifeBeens.add(lifeBean5);
        return lifeBeens;
    }
}
