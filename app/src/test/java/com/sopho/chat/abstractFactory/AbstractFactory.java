package com.sopho.chat.abstractFactory;

import com.sopho.chat.factoryModel.Shape;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape) ;
}
