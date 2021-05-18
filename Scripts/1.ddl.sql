-- Dog
DROP TABLE IF EXISTS dog_shopping.Dog RESTRICT;

-- 개_쇼핑몰
DROP SCHEMA IF EXISTS dog_shopping;

-- 개_쇼핑몰
CREATE SCHEMA dog_shopping;

-- Dog
CREATE TABLE dog_shopping.Dog (
	id        INT          NOT NULL 		  COMMENT '아이디', -- 아이디
	kind      VARCHAR(12)  NOT null unique    COMMENT '품종', -- 품종
	price     INT          NOT null			  COMMENT '가격', -- 가격
	image     VARCHAR(20)  NOT null			  COMMENT '이미지', -- 이미지
	country   VARCHAR(12)  NOT NULL 		  COMMENT '원산지', -- 원산지
	height    INT          NULL    			  COMMENT '평균신장', -- 평균신장
	weight    INT          NULL    			  COMMENT '평균체중', -- 평균체중
	content   VARCHAR(400) NULL     		  COMMENT '설명', -- 설명
	readcount INT          NULL     default 0 COMMENT '조회수' -- 조회수
)
COMMENT 'Dog';

-- Dog
ALTER TABLE dog_shopping.Dog
	ADD CONSTRAINT PK_Dog -- Dog 기본키
		PRIMARY KEY (
			id -- 아이디
		);

ALTER TABLE dog_shopping.Dog
	MODIFY COLUMN id INT NOT NULL AUTO_INCREMENT COMMENT '아이디';
	
