package com.sopho.chat.DecoratorPattern;

import com.sopho.chat.factoryModel.Shape;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class RedShapeDecorator extends ShapeDecorator {


    public RedShapeDecorator(Shape decoratedShape) {
        super(decoratedShape);
    }

    @Override
    public void draw() {
        decoratedShape.draw();
        setRedBorder(decoratedShape);
    }

    private void setRedBorder(Shape decoratedShape){
        System.out.println("Border Color: Red");
    }
}
