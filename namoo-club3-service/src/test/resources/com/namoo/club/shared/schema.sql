-- Community
DROP TABLE IF EXISTS Community RESTRICT;

-- CommunityCategory
DROP TABLE IF EXISTS CommunityCategory RESTRICT;

-- Club
DROP TABLE IF EXISTS Club RESTRICT;

-- ClubMember
DROP TABLE IF EXISTS Member RESTRICT;

-- SocialPerson
DROP TABLE IF EXISTS SocialPerson RESTRICT;

-- Community
CREATE TABLE Community (
	cmId          INTEGER      PRIMARY KEY AUTO_INCREMENT, -- 커뮤니티아이디
	cmName        VARCHAR(50)  NOT NULL, -- 커뮤니티이름
	cmDescription VARCHAR(255) NOT NULL, -- 커뮤니티내용
	cmDate        DATETIME     NOT NULL  -- 커뮤니티날짜
);

-- CommunityCategory
CREATE TABLE CommunityCategory (
	cgId   INTEGER     PRIMARY KEY AUTO_INCREMENT, -- 카테고리아이디
	cmId   INTEGER     NOT NULL, -- 커뮤니티아이디
	cgName VARCHAR(50) NOT NULL  -- 카테고리이름
);

-- Club
CREATE TABLE Club (
	clid          INTEGER     PRIMARY KEY AUTO_INCREMENT, -- 클럽아이디
	cmId          INTEGER      NOT NULL, -- 커뮤니티아이디
	cgId          INTEGER      NOT NULL, -- 카테고리아이디
	clName        VARCHAR(50)  NOT NULL, -- 클럽이름
	clDescription VARCHAR(255) NOT NULL, -- 클럽내용
	clDate        DATETIME     NOT NULL  -- 클럽날짜
);

-- ClubMember
CREATE TABLE Member (
	email       VARCHAR(40) NOT NULL, -- 이메일
	id          INTEGER     NOT NULL, -- 아이디
	kind        CHAR(1)     NOT NULL, -- 구분
	manager     CHAR(1)     NOT NULL, -- 매니저
	mainManager CHAR(1)     NULL      -- 대표매니저
);

-- ClubMember
ALTER TABLE Member
	ADD CONSTRAINT PK_Member -- ClubMember 기본키
		PRIMARY KEY (
			email, -- 이메일
			id,    -- 아이디
			kind   -- 구분
		);

-- SocialPerson
CREATE TABLE SocialPerson (
	email    VARCHAR(40) NOT NULL, -- 이메일
	userName VARCHAR(50) NOT NULL, -- 이름
	password VARCHAR(10) NOT NULL  -- 비밀번호
);
