package com.qing.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRecord {
    //存取交易变量
    private  int cnumber;
    private  int dtransaction ;
    private  String ddate;
    private  int dsurplus;
    //透支交易变量
    private  int otransaction;
    private  String odate;
    private  int osurplus;
    //透支交易变量
    private  int rtransaction;
    private  String rtransactionr_term;
    private String rdate;

}
