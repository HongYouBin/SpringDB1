package hello.jdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JdbcApplication {

	public static void main(String[] args) {
		SpringApplication.run(JdbcApplication.class, args);
	}

}

/*
drop table member if exists cascade;
create table member(
member_id varchar(10),
money integer not null default 0,
primary key(member_id)
);
 */
