package spring.postproject.ETC;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import static org.assertj.core.api.Assertions.*;
import java.time.Duration;

@SpringBootTest
public class RedisTest {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void redisConnTest() {
        ValueOperations<String, String> vop = redisTemplate.opsForValue();
        String key = "key";
        String data = "data";
        vop.set(key,data, Duration.ofSeconds(5));
        String result = vop.get(key);
        assertThat(data).isEqualTo(result);
    }
}
