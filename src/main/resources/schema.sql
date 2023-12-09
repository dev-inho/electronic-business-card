CREATE TABLE IF NOT EXISTS DEPARTMENTS (
     code VARCHAR(40) PRIMARY KEY COMMENT '소속 지회 코드'
    , name VARCHAR(255) NOT NULL COMMENT '소속 지회'
    , created_id VARCHAR(40) NOT NULL COMMENT '생성자'
    , created_dt TIMESTAMP NOT NULL COMMENT '생성일'
    , updated_id VARCHAR(40) COMMENT '수정자'
    , updated_dt TIMESTAMP COMMENT '수정일'
)  COMMENT '소속 지회';

CREATE TABLE IF NOT EXISTS MEMBERS (
      member_idx INT AUTO_INCREMENT PRIMARY KEY COMMENT '사용자 번호'
    , member_name VARCHAR(40) NOT NULL COMMENT '사용자 이름'
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