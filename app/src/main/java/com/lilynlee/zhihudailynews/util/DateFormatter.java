package com.lilynlee.zhihudailynews.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by dell on 2017/2/26.
 */

public class DateFormatter {
    /**
     * 知乎日报
     * 将long类型的date转换为字符串类型
     * 转换格式为：yyyyMMdd
     * @param date
     * @return
     */
    public String ZhihuDailyDateFormat(long date){
        String sDate;
        Date d = new Date(date + 24*60*60*1000);
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        sDate = format.format(d);
        return sDate;
    }

    /**
     * 豆瓣一刻
     * 将long类型的date转换为字符串类型
     * 转换格式为：yyyy-MM-dd
     * @param date
     * @return
     */
    public String DoubanDateFormat(long date){
        String sDate;
        Date d = new Date(date);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        sDate = format.format(d);
        return sDate;
    }
}
