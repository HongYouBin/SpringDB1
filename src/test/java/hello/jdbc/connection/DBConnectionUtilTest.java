package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;

@Slf4j
public class DBConnectionUtilTest {

    @Test
    void connection(){
        Connection connection = DBConnectionUtil.getConnectioin();
        Assertions.assertThat(connection).isNotNull();
    }
}
