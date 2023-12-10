CREATE TABLE IF NOT EXISTS DEPARTMENTS (
     code VARCHAR(40) PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     created_id VARCHAR(40) NOT NULL,
     created_dt TIMESTAMP NOT NULL,
     updated_id VARCHAR(40),
     updated_dt TIMESTAMP
);

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

CREATE TABLE IF NOT EXISTS USERS (
     user_idx INT AUTO_INCREMENT PRIMARY KEY,
     user_id VARCHAR(255) NOT NULL UNIQUE,
     password VARCHAR(255) NOT NULL,
     user_name VARCHAR(40) NOT NULL,
     phone_number VARCHAR(13),
     is_activated BOOLEAN NOT NULL DEFAULT true
);

CREATE TABLE IF NOT EXISTS AUTHORITIES (
   authority_idx INT AUTO_INCREMENT PRIMARY KEY,
   user_idx INT NOT NULL,
   authority_name VARCHAR(40) NOT NULL,
   FOREIGN KEY(user_idx) REFERENCES USERS(user_idx)
);
