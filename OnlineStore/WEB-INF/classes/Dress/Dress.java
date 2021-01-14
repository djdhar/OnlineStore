package Dress;

import java.time.*;
import java.util.*;

public class Dress
{
    public Integer id;
    public String gender;
    public String name;
    public Date moment;
    public String time;

    public Integer price;
    public Integer discount;

    public Dress(Integer id , String gender, String name, Date moment, String time, Integer price, Integer discount){
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.moment = moment;
        this.price = price;
        this.time=time;
        this.discount = discount;
    }

    public Date getMoment(){
        return moment;
    }

    public Integer getDiscount(){
        if(discount==null){
            return 0;
        }
        return discount;
    }
}