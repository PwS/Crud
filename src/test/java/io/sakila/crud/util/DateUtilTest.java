package io.sakila.crud.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class DateUtilTest {

    @Test
    void test() {
        Assertions.assertNotNull(DateUtil.getCurrentTimestamp());
        Assertions.assertNotNull(DateUtil.getCurrentTimestampGMT7());
        Assertions.assertNotNull(DateUtil.getCurrentTimestampGMT0());
        Assertions.assertNotNull(DateUtil.getCurrentTimestampGMT8());
        Assertions.assertNotNull(DateUtil.getCurrentTimestampGMT9());
    }

}
