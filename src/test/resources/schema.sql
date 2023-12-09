CREATE TABLE IF NOT EXISTS DEPARTMENTS (
     code VARCHAR(40) PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     created_id VARCHAR(40) NOT NULL,
     created_dt TIMESTAMP NOT NULL,
     updated_id VARCHAR(40),
     updated_dt TIMESTAMP
);
COMMENT ON TABLE DEPARTMENTS IS '소속 지회';
COMMENT ON COLUMN DEPARTMENTS.code IS '소속 지회 코드';
COMMENT ON COLUMN DEPARTMENTS.name IS '소속 지회';
COMMENT ON COLUMN DEPARTMENTS.created_id IS '생성자';
COMMENT ON COLUMN DEPARTMENTS.created_dt IS '생성일';
COMMENT ON COLUMN DEPARTMENTS.updated_id IS '수정자';
COMMENT ON COLUMN DEPARTMENTS.updated_dt IS '수정일';

CREATE TABLE IF NOT EXISTS USERS (
   user_idx INT AUTO_INCREMENT PRIMARY KEY,
   user_name VARCHAR(40) NOT NULL,
   authority VARCHAR(10) NOT NULL,
   company VARCHAR(255) NOT NULL,
   certification_dt TIMESTAMP NOT NULL,
   department VARCHAR(40) NOT NULL,
   phone_number VARCHAR(13),
   email VARCHAR(255),
   created_id VARCHAR(40) NOT NULL,
   created_dt TIMESTAMP NOT NULL,
   updated_id VARCHAR(40),
   updated_dt TIMESTAMP,
   FOREIGN KEY(department) REFERENCES DEPARTMENTS(code)
);
COMMENT ON TABLE USERS IS '사용자 조회';
COMMENT ON COLUMN USERS.user_idx IS '사용자 번호';
COMMENT ON COLUMN USERS.user_name IS '사용자 이름';
COMMENT ON COLUMN USERS.authority IS '권한';
COMMENT ON COLUMN USERS.company IS '소속 회사';
COMMENT ON COLUMN USERS.certification_dt IS '명장 인증년도';
COMMENT ON COLUMN USERS.department IS '소속 지회 코드';
COMMENT ON COLUMN USERS.phone_number IS '휴대전화 번호';
COMMENT ON COLUMN USERS.email IS '이메일';
COMMENT ON COLUMN USERS.created_id IS '생성자';
COMMENT ON COLUMN USERS.created_dt IS '생성일';
COMMENT ON COLUMN USERS.updated_id IS '수정자';
COMMENT ON COLUMN USERS.updated_dt IS '수정일';