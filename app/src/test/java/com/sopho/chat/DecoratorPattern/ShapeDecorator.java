package com.sopho.chat.DecoratorPattern;

import com.sopho.chat.factoryModel.Shape;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public abstract class ShapeDecorator implements Shape {
    protected Shape decoratedShape;

    public ShapeDecorator(Shape decoratedShape){
        this.decoratedShape = decoratedShape;
    }

    public void draw(){
        decoratedShape.draw();
    }
}
