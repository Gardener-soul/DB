-- ssafy 데이터 베이스 생성 및 사용
CREATE DATABASE IF NOT EXISTS SSAFY;
USE SSAFY;



-- 테이블 생성
CREATE TABLE ssafy_user (
    user_num INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) NOT NULL,
    user_name VARCHAR(20) NOT NULL,
    user_password VARCHAR(20) NOT NULL,
    user_email VARCHAR(30),
    signup_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 테이블 정보 확인
DESC ssafy_user;

-- 전체 테이블 데이터 조회
SELECT * FROM ssafy_user;


-- 모든 컬럼 입력 (컬럼명 생략 가능)
INSERT INTO ssafy_user
VALUES (1, "godqhr", "양명균", "1234", "godqhr@gmail.com", now());

-- 원하는 컬럼만 입력 NOT NULL이 있으면 그 컬럼에 무조건 입력
INSERT INTO ssafy_user (user_id, user_name, user_password)
VALUES("kimssafy", "김싸피", "1q2w3e4r!@");

-- 여러행 입력
INSERT INTO ssafy_user (user_id, user_name, user_password)
VALUES  ("leessafy", "이싸피", "0000"),
		("parkssafy", "박싸피", "1111"),
        ("5Ssafy", "오싸피", "2222");
        
-- 데이터 수정 조건x(safe modE 해제) Edit -> preferences -> SQLEditor
UPDATE ssafy_user
SET user_name = 'anonymous';

-- usEr_num가 3번인 학생 비밀번호 1234로 수정
UPDATE ssafy_user
SET user_password = '1234'
WHERE user_num = 3;


-- 삭제
-- user_num가 4인 학생 삭제
DELETE FROM ssafy_user
WHERE user_num = 4;


SELECT * FROM ssafy_user;

