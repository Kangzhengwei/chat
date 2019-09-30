package com.sopho.chat.abstractFactory;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
