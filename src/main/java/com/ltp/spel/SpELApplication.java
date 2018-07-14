package com.ltp.spel;

import com.sun.org.apache.xalan.internal.extensions.ExpressionContext;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.Date;

public class SpELApplication {
    public static void main(String[] args) {
        ExpressionParser parser = new SpelExpressionParser();

//        EvaluationContext context = new StandardEvaluationContext(System.class);

        Long time = parser.parseExpression("currentTimeMillis()").getValue(System.class, Long.class);
        Date date = new Date(time);
        System.out.println(date.toString());
    }
}
