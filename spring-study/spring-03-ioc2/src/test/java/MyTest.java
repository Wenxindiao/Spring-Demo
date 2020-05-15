import com.kuang.pojo.User;
import com.kuang.pojo.UserT;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class MyTest {
    public static void main(String[] args) {
        //Spring容器
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        User user = (User) context.getBean("user");
        User user2 = (User) context.getBean("user");
        UserT userT2 = (UserT) context.getBean("userT2");
        UserT userT3 = (UserT) context.getBean("userT3");
        System.out.println(user == user2);

       userT3.show();
    }
}
