package pl.edu.zut.mad.schedule;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(properties = {
        ApiProperties.DATABASE_USERNAME,
        ApiProperties.DATABASE_PASSWORD
})
public class ScheduleApplicationTests {

    @Test
    public void contextLoads() {
    }

}
