package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.VisibleForTesting;
import org.junit.jupiter.api.Test;

import java.net.ConnectException;
import java.sql.SQLException;

@Slf4j
public class UnCheckedAppTest {

    @Test
    void unChecked(){
        Controller controller = new Controller();
        Assertions.assertThatThrownBy(()->controller.request())
                .isInstanceOf(Exception.class);
    }

    @Test
    void printEx(){
        Controller controller = new Controller();
        try{
            controller.request();
        } catch(Exception e){
            log.info("ex", e);
        }
    }

    static class Controller{
        Service service = new Service();

        public void request() {
            service.logic();
        }
    }

    static class Service{
        Repository repository = new Repository();
        NetworkClient networkClient = new NetworkClient();

        public void logic() {
            repository.call();
            networkClient.call();
        }
    }
    static class NetworkClient{
        public void call() {
            throw  new RuntimeConnectException("연결 실패");
        }
    }
    static class Repository{
        public void call() {
            try{
                runSQL();
            } catch (SQLException e){
                //밖으로 던질 때 sqlException을 runtime으로 바꿔서 던진다.
                throw new RuntimeSQLException(e); //기존 예외(e)를 넣어줘야 한다.
            }
        }
        public void runSQL() throws SQLException{
            throw new SQLException("ex");
        }
    }

    static class RuntimeConnectException extends RuntimeException{
        public RuntimeConnectException(String message){
            super(message);
        }

        public RuntimeConnectException(){};
    }

    static class RuntimeSQLException extends RuntimeException{
        public RuntimeSQLException(Throwable cause){
            super(cause);
        }
    }
}
