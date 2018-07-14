package com.ltp.spel;

import com.ltp.spel.entity.DatabaseConfig;
import com.ltp.spel.entity.Person;
import com.ltp.spel.entity.SystemPropertiesBean;
import com.ltp.spel.entity.User;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.*;

public class SpELApplication {

    /**
     * 解析文本
     */
    @Test
    public void test_parseText(){
        ExpressionParser parser = new SpelExpressionParser();

        String str = parser.parseExpression("'hello world'").getValue(String.class);
        System.out.println(str);

        // 静态方法调用
        Integer integer = parser.parseExpression("T(java.lang.Math).random() * 100").getValue(Integer.class);
        System.out.println(integer);

        Boolean bool = parser.parseExpression("true").getValue(Boolean.class);
        System.out.println(bool);

        // 调用字符串中的方法（实例方法调用）
        String substr = parser.parseExpression("'Hello world'.substring(2)").getValue(String.class);
        System.out.println(substr);

    }

    /**
     * 解析对象,实例方法调用
     */
    @Test
    public void test_parseObject(){

        User user = new User();
        user.setUsername("tom");
        user.setPassword("123");
        user.setAge(11);

        ExpressionParser parser = new SpelExpressionParser();
        EvaluationContext context = new StandardEvaluationContext(user);

        String username = parser.parseExpression("getUsername()").getValue(context, String.class);
        System.out.println(username);

        String password = parser.parseExpression("getPassword()").getValue(context, String.class);
        System.out.println(username);

        Integer age = parser.parseExpression("getAge()").getValue(context, Integer.class);
        System.out.println(age);

    }


    /**
     * 解析集合
     */
    @Test
    public void test_parseCollection() {
        ExpressionParser parser = new SpelExpressionParser();

        List<Integer> list = parser.parseExpression("{1, 2, 3}").getValue(ArrayList.class);
        System.out.println(list);

        Map<Integer, String> map = parser.parseExpression("{username:'tom', password:'123', age:12}").getValue(HashMap.class);
        System.out.println(map);

    }

    /**
     * 在XML中使用SpEL，使用两个配置文件systemProperties和SystemEnvironments
     */
    @Test
    public void test_parseXML(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        SystemPropertiesBean systemPropertiesBean = context.getBean(SystemPropertiesBean.class);
        System.out.println(systemPropertiesBean);
    }

    /**
     * 在注解中使用SpEL
     */
    @Test
    public void test_parseAnnotaion(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
        Person person = context.getBean(Person.class);

        System.out.println(person);
    }

    /**
     * 在注解中使用SpEL
     */
    @Test
    public void test_parseProperties(){
        ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");

        DatabaseConfig databaseConfig = context.getBean(DatabaseConfig.class);
        System.out.println(databaseConfig);
    }
}

