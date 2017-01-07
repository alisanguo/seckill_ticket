create database seckill_ticket  DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;

CREATE table db_ticket(
  id INT PRIMARY KEY AUTO_INCREMENT ,
  name VARCHAR (30) NOT NULL ,
  num INT NOT NULL,
  price INT not null,
  start_time TIMESTAMP NOT NULL ,
  end_time TIMESTAMP NOT NULL
);

CREATE table db_order(
  ticket_id int not null,
  email VARCHAR(40) not null,
  ticket_name VARCHAR (30) NOT NULL,
  create_time TIMESTAMP not null,
  PRIMARY key(ticket_id,email),
  FOREIGN key(ticket_id) REFERENCES db_ticket(id)
);

INSERT INTO db_ticket(name,num,price,start_time,end_time) VALUES ('你的名字电影票',200,30,20161203000000,now());
INSERT INTO db_ticket(name,num,price,start_time,end_time) VALUES ('你的名字电影票',200,30,20160103000000,20160109000000);

delimiter $$;
CREATE PROCEDURE `seckill_ticket`.`execute_seckill`(in v_ticket_id INT,in v_ticket_name VARCHAR(30) ,in v_email VARCHAR(40),in v_kill_time TIMESTAMP ,out r_result INT )
BEGIN
DECLARE insert_count INT DEFAULT 0;
START TRANSACTION;
INSERT ignore into db_order(ticket_id,ticket_name,email,create_time) VALUES (v_ticket_id,v_ticket_name,
v_email,v_kill_time);
SELECT ROW_COUNT() INTO insert_count;
IF (insert_count=0) THEN
ROLLBACK ;
SET r_result=0;
ELSEIF (insert_count<0) THEN
ROLLBACK;
SET  r_result=-3;
ELSE
UPDATE db_ticket SET num=num-1 WHERE id=v_ticket_id AND start_time<=v_kill_time
AND end_time>v_kill_time AND  num>0;
SELECT ROW_COUNT() INTO insert_count;
IF (insert_count=0) THEN
ROLLBACK ;
SET r_result=-1;
ELSEIF (insert_count<0) THEN
ROLLBACK ;
SET r_result=-3;
ELSE
COMMIT ;
SET r_result=1;
END IF;
END IF;
END;
$$

DELIMITER ;

SET @result=-4;
call execute_seckill(1,'变形金刚电影票','1539724141@qq.com',now(),@result);
SELECT @result;





