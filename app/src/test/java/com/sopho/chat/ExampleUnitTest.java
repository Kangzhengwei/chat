package com.sopho.chat;

import com.sopho.chat.DecoratorPattern.RedShapeDecorator;
import com.sopho.chat.DecoratorPattern.ShapeDecorator;
import com.sopho.chat.FiltterPattern.AndCriteria;
import com.sopho.chat.FiltterPattern.Criteria;
import com.sopho.chat.FiltterPattern.CriteriaFemale;
import com.sopho.chat.FiltterPattern.CriteriaMale;
import com.sopho.chat.FiltterPattern.CriteriaSingle;
import com.sopho.chat.FiltterPattern.OrCriteria;
import com.sopho.chat.FiltterPattern.Person;
import com.sopho.chat.ObserverPattern.BinaryObserver;
import com.sopho.chat.ObserverPattern.HexaObserver;
import com.sopho.chat.ObserverPattern.OctalObserver;
import com.sopho.chat.ObserverPattern.Subject;
import com.sopho.chat.abstractFactory.AbstractFactory;
import com.sopho.chat.abstractFactory.Color;
import com.sopho.chat.abstractFactory.FactoryProducer;
import com.sopho.chat.factoryModel.Circle;
import com.sopho.chat.factoryModel.Rectangle;
import com.sopho.chat.factoryModel.Shape;
import com.sopho.chat.factoryModel.ShapeFactory;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        //factory();
        //abastactFactory();
        //filtter();
        //decorator();
        observer();
    }

    public void factory() {
        ShapeFactory factory = new ShapeFactory();
        Shape shape1 = factory.getShape("CIRCLE");
        shape1.draw();
        Shape shape2 = factory.getShape("RECTANGLE");
        shape2.draw();
        Shape shape3 = factory.getShape("SQUARE");
        shape3.draw();
    }

    public void abastactFactory() {
        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE");
        //获取形状为 Circle 的对象
        Shape shape1 = shapeFactory.getShape("CIRCLE");
        //调用 Circle 的 draw 方法
        shape1.draw();
        //获取形状为 Rectangle 的对象
        Shape shape2 = shapeFactory.getShape("RECTANGLE");
        //调用 Rectangle 的 draw 方法
        shape2.draw();
        //获取形状为 Square 的对象
        Shape shape3 = shapeFactory.getShape("SQUARE");
        //调用 Square 的 draw 方法
        shape3.draw();

        //获取颜色工厂
        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");
        //获取颜色为 Red 的对象
        Color color1 = colorFactory.getColor("RED");
        //调用 Red 的 fill 方法
        color1.fill();
        //获取颜色为 Green 的对象
        Color color2 = colorFactory.getColor("Green");
        //调用 Green 的 fill 方法
        color2.fill();
        //获取颜色为 Blue 的对象
        Color color3 = colorFactory.getColor("BLUE");
        //调用 Blue 的 fill 方法
        color3.fill();
    }

    public void filtter() {
        List<Person> persons = new ArrayList<>();
        persons.add(new Person("雷军", "男", "SINGLE"));
        persons.add(new Person("马化腾", "男", "married"));
        persons.add(new Person("马云", "男", "married"));
        persons.add(new Person("李彦宏", "男", "married"));
        persons.add(new Person("陈芸妮", "女", "SINGLE"));

        Criteria male           = new CriteriaMale();
        Criteria female         = new CriteriaFemale();
        Criteria single         = new CriteriaSingle();
        Criteria singlemale     = new AndCriteria(single,male);
        Criteria singleorFemale = new OrCriteria(single,female);

        System.out.println("男性：");
        printPersons(male.meetCriteria(persons));
        System.out.println("女性：");
        printPersons(female.meetCriteria(persons));
        System.out.println("Single Males:");
        printPersons(singlemale.meetCriteria(persons));
        System.out.println("Single or Females：");
        printPersons(singleorFemale.meetCriteria(persons));
    }

    public static void printPersons(List<Person> persons){
        for (Person person:persons){
            System.out.println("人:名称"+person.getName()+"性别："+person.getGender()+"婚姻状态："+person.getMaritalStatus()+"]");
        }
    }

    public void decorator(){
        Shape          cricle       = new Circle();
        ShapeDecorator redCircle    = new RedShapeDecorator(new Circle());
        ShapeDecorator redRectangle = new RedShapeDecorator(new Rectangle());

        System.out.println("Circle with normal border");
        cricle.draw();
        System.out.println("\nCircle of red border");
        redCircle.draw();
        System.out.println("\nRectangle of red border");
        redRectangle.draw();
    }

    public void observer(){
        Subject subject = new Subject();

        new HexaObserver(subject);
        new OctalObserver(subject);
        new BinaryObserver(subject);

        System.out.println("First state change: 15");
        subject.setState(15);
        System.out.println("Second state change: 10");
        subject.setState(10);
    }


}