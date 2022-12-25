package spring.postproject.config.Security.Annotation;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//런타임까지 유지
@Retention(RetentionPolicy.RUNTIME)
//타켓은 파라미터에만 붙임
@Target(ElementType.PARAMETER)
//익명사용자는 null
@AuthenticationPrincipal(expression = "#this == 'anonymousUser' ? null : Member")
public @interface CurrentMember {
}
