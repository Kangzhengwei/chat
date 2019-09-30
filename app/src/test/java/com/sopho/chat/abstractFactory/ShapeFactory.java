package com.sopho.chat.abstractFactory;

import com.sopho.chat.factoryModel.Circle;
import com.sopho.chat.factoryModel.Rectangle;
import com.sopho.chat.factoryModel.Shape;
import com.sopho.chat.factoryModel.Square;

/**
 * author: kang4
 * Date: 2019/5/28
 * Description:
 */
public class ShapeFactory extends AbstractFactory  {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if (shape == null) {
            return null;
        }
        if (shape.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shape.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shape.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
