package com.koffe.koffe.utils;

public class HideInfo {
    public static String hideInfoTypePassword(String needHide) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < needHide.length(); i++) {
            sb.append("*");
        }
        return sb.toString();
    }
}
