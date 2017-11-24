package com.xiangni.exporter.common;

public class Constants {

    private static int num = 0;

    public static synchronized void setNum(int n) { num = n; }
    public static int getNum() { return num; }
}
