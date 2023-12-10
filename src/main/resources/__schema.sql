CREATE TABLE IF NOT EXISTS DEPARTMENTS (
       code VARCHAR(40) PRIMARY KEY COMMENT '소속 지회 코드'
     , name VARCHAR(255) NOT NULL COMMENT '소속 지회'
     , created_id VARCHAR(40) NOT NULL COMMENT '생성자'
     , created_dt TIMESTAMP NOT NULL COMMENT '생성일'
     , updated_id VARCHAR(40) COMMENT '수정자'
     , updated_dt TIMESTAMP COMMENT '수정일'
    )  COMMENT '소속 지회';

CREATE TABLE IF NOT EXISTS MEMBERS (
       member_idx INT AUTO_INCREMENT PRIMARY KEY COMMENT '회원 번호'
     , member_name VARCHAR(40) NOT NULL COMMENT '회원 이름'
     , company VARCHAR(255) NOT NULL COMMENT '소속 회사'
     , certification_dt TIMESTAMP NOT NULL COMMENT '명장 인증년도'
     , department VARCHAR(40) NOT NULL COMMENT '소속 지회 코드'
     , phone_number VARCHAR(13) COMMENT '휴대전화 번호'
     , email VARCHAR(255) COMMENT '이메일'
     , created_id VARCHAR(40) NOT NULL COMMENT '생성자'
     , created_dt TIMESTAMP NOT NULL COMMENT '생성일'
     , updated_id VARCHAR(40) COMMENT '수정자'
     , updated_dt TIMESTAMP COMMENT '수정일'
     , FOREIGN KEY(department) REFERENCES DEPARTMENTS(code)
     ) COMMENT '회원';

CREATE TABLE IF NOT EXISTS USERS (
       user_idx INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 번호'
     , user_id VARCHAR(255) NOT NULL UNIQUE COMMENT '사용자 아이디'
     , password VARCHAR(255) NOT NULL COMMENT '비밀번호'
     , user_name VARCHAR(40) NOT NULL COMMENT '사용자 이름'
     , phone_number VARCHAR(13) COMMENT '휴대전화 번호'
     , is_activated BOOLEAN NOT NULL DEFAULT true COMMENT ''
     , created_id VARCHAR(40) NOT NULL COMMENT '생성자'
     , created_dt TIMESTAMP NOT NULL COMMENT '생성일'
     , updated_id VARCHAR(40) COMMENT '수정자'
     , updated_dt TIMESTAMP COMMENT '수정일'
     ) COMMENT '사용자';

CREATE TABLE IF NOT EXISTS AUTHORITIES (
       authority_idx INT AUTO_INCREMENT PRIMARY KEY COMMENT '권한 번호'
     , user_idx INT NOT NULL COMMENT '사용자 번호'
     , authority_name VARCHAR(40) NOT NULL COMMENT '권한 이름'
     , FOREIGN KEY(user_idx) REFERENCES USERS(user_idx)
     ) COMMENT '권한';