package hello.core.beandefinition;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanDefinitionTest {

    // ApplicationContext ac = ~ 로 안하는 이유 -> .getBeanDefinition을 못함. 이게 없다.
    // 왜냐면 실제로 이런 BeanDefinition 정보를 뽑아서 쓸 일이 없기 때문
    // 그래서 ApplicationContext 안에는 이런 복잡한 메서드들이 정의가 안되어있다.
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    // xml로 했을때는, bean에 대한 정보가 명확히 등록되어 있다.
    // factoryMethod, factoryMethodName이 빠져잇는데
    //    GenericXmlApplicationContext ac = new GenericXmlApplicationContext("appConfig.xml");


    @Test
    @DisplayName("빈 설정 메타정보 확인")
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            // ApplicationContext에서 getBeanDefinition의 정보 얻어오기
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);

            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                System.out.println("beanDefinitionName = " + beanDefinitionName +
                        "beanDefinition = " + beanDefinition);
            }

        }
    }
}
