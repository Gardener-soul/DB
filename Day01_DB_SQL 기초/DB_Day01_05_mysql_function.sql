-- 2의 3제곱
SELECT POW(2, 3) AS "2^3"
FROM dual;

-- 8 나누기 3의 나머지
SELECT MOD(8, 3) AS "8을 3으로 나눈 나머지";

-- 최대값, 최솟값
SELECT greatest(8,17,86,17,100,77,999,2,13,31,97), least(8,17,86,17,100,77,999,2,13,31,97);

-- 반올림
SELECT round(1526.159), round(1526.159, 0), round(1526.159, 1), round(1526.159,2), round(1526.159, 3);

-- 아스키 코드값 얻기
SELECT ascii('0'), ascii('A'), ascii('a'); 

-- concat 메서드를 써보자.
SELECT CONCAT('PRESIDENT의 이름은 ', ename, ' 입니다.') AS 소개
FROM emp
WHERE job = 'PRESIDENT'; 


-- 이름의 길이가 5인 직원의 이름을 조회
SELECT ename
FROM emp
WHERE length(ename) = 5;

-- 김싸피
SELECT length('김싸피'), char_length('김싸피');

SELECT length('🍕'), char_length('🍕');

-- 문자열 변경
SELECT replace('Hello abc abc', 'abc', 'ssafy');

-- 문자열 인덱스
SELECT instr('Hello SSAFY', 'SSAFY');

-- 모든 직원의 이름 3자리조회
SELECT substr(ename, 1, 3)
FROM emp;

-- LPAD RPAD
SELECT LPAD('SSAFY',10,'*'), RPAD('SSAFY',10,'*');

-- REVERSE
SELECT REVERSE('HELLO SSAFY!');
SELECT REVERSE('우영우역삼역기러기인도인토마토별똥별스위스');

-- 2초 더하기
SELECT addtime("2022-02-13 17:29:21", "2");

-- 날짜차이
SELECT datediff("2008-02-18", "2006-02-21");

-- 오늘은?
SELECT now(), day(now()), month(now()), year(now()), yearweek(now());


-- 모든 사원에 대하여 사원수, 급여총액, 평균급여, 최고급여, 최저급여 조회
SELECT COUNT(*) 사원수, SUM(sal), AVG(sal), MAX(sal), MIN(sal)
FROM emp;


-- 모든 사원에 대하여 부서, 사원수, 급여총액, 평균급여, 최고급여, 최저급여를 부서별로 조회하고, 소수점 둘쨰자리 반올림
SELECT deptno 부서, COUNT(*) 사원수, SUM(sal) 급여총액, ROUND(AVG(sal), 2) 평균급여, MAX(sal) 최고급여, MIN(sal) 최저급여
FROM emp
GROUP BY deptno;

-- 모든 사원에 대하여 부서, 업무, 사원수, 급여총액, 평균급여, 최고급여, 최저급여를 부서별, 직급별로 조회
SELECT deptno 부서, job 업무, COUNT(*) 사원수, SUM(sal) 급여총액, ROUND(AVG(sal), 2) 평균급여, MAX(sal) 최고급여, MIN(sal) 최저급여
FROM emp
GROUP BY deptno, job
ORDER BY deptno;


-- 모든 사원에 대하여 이름, 부서, 업무, 사원수, 급여총액, 평균급여, 최고급여, 최저급여를 부서별, 직급별로 조회
SELECT ename 이름, deptno 부서,job 업무, COUNT(*) 사원수, SUM(sal) 급여총액, 
       ROUND(AVG(sal),2) 평균급여, MAX(sal) 최고급여, MIN(sal) 최저급여
FROM emp
GROUP BY deptno,job;


-- 급여(커미션포함) 평균이 2000이상인 부서번호, 부서별 사원수, 평균급여(커미션포함) 조회 
SELECT deptno, COUNT(*) 사원수, ROUND(AVG(sal+IFNULL(comm, 0)),2) AS "평균급여(커미션포함)"
FROM emp
GROUP BY deptno
HAVING AVG(sal + IFNULL(comm, 0)) >= 2000;


-- 오토커밋 나 안할래 
set autocommit = 0;

-- 트랜잭션
USE ssafy;
CREATE TABLE test_table(val VARCHAR(20));

-- 롤백
START TRANSACTION;
INSERT INTO test_table VALUES ('A');
INSERT INTO test_table VALUES ('B');
INSERT INTO test_table VALUES ('C');
INSERT INTO test_table VALUES ('D');
ROLLBACK;
SELECT * FROM test_table;


-- 커밋
START TRANSACTION;
INSERT INTO test_table VALUES ('S');
INSERT INTO test_table VALUES ('S');
INSERT INTO test_table VALUES ('A');
INSERT INTO test_table VALUES ('F');
INSERT INTO test_table VALUES ('Y');
COMMIT;
SELECT * FROM test_table;