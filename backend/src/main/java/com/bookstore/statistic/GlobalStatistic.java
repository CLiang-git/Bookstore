package com.bookstore.statistic;


public class GlobalStatistic {
    public static volatile long dailyVisit = 0L;
    public static synchronized long addVisit() {
        return ++dailyVisit;
    }
    public static volatile Integer commentNum = 0;
    public static synchronized Integer checkCommentNum(){return ++commentNum;}
}
