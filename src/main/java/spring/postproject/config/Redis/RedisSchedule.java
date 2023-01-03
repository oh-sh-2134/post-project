package spring.postproject.config.Redis;


import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import spring.postproject.Post.Repository.PostRepositorySupport;
import spring.postproject.Post.Service.PostService;

import java.util.Iterator;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class RedisSchedule {

    private final RedisTemplate<String,Object> redisTemplate;
    private final PostService postService;

    //3분에 한번씩 업데이트
    @Scheduled(cron = "0 0/3 * * * ?")
    public void deleteViewCntCacheFromRedis() {
        RedisTemplate<String, Object> rt = redisTemplate;
        Set<String> redisKeys = redisTemplate.keys("postCnt*");
        if(redisKeys == null) return;
        redisKeys = redisKeys.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        for (String key : redisKeys) {
            Long postId = Long.parseLong(key.split("::")[1]);
            Long viewCnt = Long.parseLong(key);
            postService.addViewCntFromRedis(postId,viewCnt);
            rt.delete(key);
        }
    }

    //매일 세벽 1시에 실행
    @Scheduled(cron = "0 0 1 * * * ")
    public void deleteDuplicationInfoCacheFromRedis(){
        RedisTemplate<String,Object> rt = redisTemplate;
        Set<String> redisKeys = redisTemplate.keys("postDuplicationInfo*");
        if(redisKeys == null) return;
        redisKeys = redisKeys.stream().filter(Objects::nonNull).collect(Collectors.toSet());
        for (String key : redisKeys) {
            rt.delete(key);
        }
    }
}
