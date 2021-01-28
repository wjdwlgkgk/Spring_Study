package chap02;

import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext("classpath:applicationContext.xml");
        Greeter g = ctx.getBean("greeter",Greeter.class);
        String msg = g.greet("½ºÇÁ¸µ");
        System.out.println(msg);

        Greeter g1 = ctx.getBean("greeter",Greeter.class);
        Greeter g2 = ctx.getBean("greeter",Greeter.class);

        System.out.println("(g1 == g2) = " + (g1 == g2));
        ctx.close();
    }
}
