package com.sopho.chat.abstractFactory;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class FactoryProducer {
    public static AbstractFactory getFactory(String choice){
        if(choice.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(choice.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
