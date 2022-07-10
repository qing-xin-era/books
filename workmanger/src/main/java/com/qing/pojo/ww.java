package com.qing.pojo;

import lombok.Data;

import java.util.Calendar;
@Data
public class ww {






    public static void main(String[] args) {
        Calendar cel=Calendar.getInstance();
        Car car1=new Car("奥拓", 3.8, "土豪金", cel);
        Car car2=new Car("奥迪", 38, "黑色", cel);
        System.out.println(car1);
        System.out.println(car2);
    }
}
class Car{
    String brand;
    double price;
    String color;
    Calendar date;
    public Car(String brand, double price, String color, Calendar date) {
        super();
        this.brand = brand;
        this.price = price;
        this.color = color;
        this.date = date;
    }

    @Override
    public String toString() {
        return "品牌：\t"+ brand +"\n"+"颜色：\t"+color +"\n"+"价格：\t" + price + "万" +"\n"+ "销售日期：\t" + date.get(Calendar.YEAR) + ""+(date.get(Calendar.MONTH)+1)+"月"+date.get(Calendar.DAY_OF_MONTH)+"日"+date.get(Calendar.HOUR_OF_DAY)+":"+date.get(Calendar.MINUTE)+":"+date.get(Calendar.SECOND);
    }

}
