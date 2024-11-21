package net.engineeringdigest.journalApp;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class JournalAppApplicationTests {

	@Disabled
	@Test
	void contextLoads() {
	}

	@Autowired
	private RedisTemplate redisTemplate;

	@Disabled
	@Test
	public void testRedis(){
		redisTemplate.opsForValue().set("email", "example@email.com");
		Object email = redisTemplate.opsForValue().get("email");
		System.out.println(redisTemplate.opsForValue().get("email"));
		int a = 1;
	}

}
