package com.sopho.chat.abstractFactory;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class Green implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
