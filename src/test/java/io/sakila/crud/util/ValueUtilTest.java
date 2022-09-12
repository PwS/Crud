package io.sakila.crud.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class ValueUtilTest {

    @Test
    void test() {
        Assertions.assertNotNull(ValueUtil.hasValue("true"));
        Assertions.assertNotNull(ValueUtil.hasValue(true));
        Assertions.assertNotNull(ValueUtil.hasValue(false));
    }
}

