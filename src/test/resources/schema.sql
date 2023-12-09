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

CREATE TABLE IF NOT EXISTS MEMBERS (
   member_idx INT AUTO_INCREMENT PRIMARY KEY,
   member_name VARCHAR(40) NOT NULL,
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
COMMENT ON TABLE MEMBERS IS '회원';
COMMENT ON COLUMN MEMBERS.member_idx IS '사용자 번호';
COMMENT ON COLUMN MEMBERS.member_name IS '사용자 이름';
COMMENT ON COLUMN MEMBERS.company IS '소속 회사';
COMMENT ON COLUMN MEMBERS.certification_dt IS '명장 인증년도';
COMMENT ON COLUMN MEMBERS.department IS '소속 지회 코드';
COMMENT ON COLUMN MEMBERS.phone_number IS '휴대전화 번호';
COMMENT ON COLUMN MEMBERS.email IS '이메일';
COMMENT ON COLUMN MEMBERS.created_id IS '생성자';
COMMENT ON COLUMN MEMBERS.created_dt IS '생성일';
COMMENT ON COLUMN MEMBERS.updated_id IS '수정자';
COMMENT ON COLUMN MEMBERS.updated_dt IS '수정일';