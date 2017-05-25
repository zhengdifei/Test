package annotation.Fruit;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2017/5/25 0025.
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
