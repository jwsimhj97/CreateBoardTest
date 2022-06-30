create table user(
    id VARCHAR(30) NOT NULL UNIQUE COMMENT '아이디',
    pw VARCHAR(30) NOT NULL COMMENT '비밀번호',
    name VARCHAR(30) NOT NULL COMMENT '이름',
    role INT(10) NOT NULL COMMENT '역할',
    CONSTRAINT user_idName PRIMARY KEY(id, name)
)DEFAULT CHARSET=utf8;

create INDEX user_table_name ON user(name);

create table board(
    num INT(30) AUTO_INCREMENT PRIMARY KEY COMMENT 'No.',
    title VARCHAR(300) NOT NULL COMMENT '제목',
    content TEXT NULL COMMENT '내용',
    file_name VARCHAR(300) COMMENT '첨부파일명',
    date DATETIME NOT NULL DEFAULT NOW() COMMENT '날짜',
    
    user_name VARCHAR(30) NOT NULL COMMENT '이름',
    FOREIGN KEY(user_name) REFERENCES user(name)
)DEFAULT CHARSET=utf8;


========================================

-- role 0=관리자, 1=일반회원, 2=탈퇴회원
insert into user(id, pw, name, role) values ('test', 'test1234', '홍길동', 1);

insert into board(num, title, content, file_name, date, user_name) values (1, '안녕하세요', '사이트보고 문의드릴게 있는데 어디로 연락하면 될까요?', '', NOW(), '홍길동');
insert into board(num, title, content, file_name, date, user_name) values (2, 'test title', 'test content text...', '', NOW(), '홍길동');