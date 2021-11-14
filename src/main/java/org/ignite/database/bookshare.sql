DROP TABLE IF EXISTS admin1;
CREATE TABLE admin1(
    admin_id INT(15) PRIMARY KEY,
    username CHAR(32),
    password CHAR(32),
    registered_date DATE
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=admin1, VALUE_TYPE=org.ignite.Entity.Admin";

DROP TABLE IF EXISTS eCatalog1;
CREATE TABLE eCatalog1(
    catalog_id INT(15),
    name_catalog CHAR(128),
    admin_id INT(15),
    PRIMARY KEY (catalog_id, admin_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=admin_id, CACHE_NAME=catalog1, KEY_TYPE=org.ignite.Entity.eCatalogKey, VALUE_TYPE=org.ignite.Entity.eCatalog";

DROP TABLE IF EXISTS user1;
CREATE TABLE user1(
    user_id INT(15),
    username CHAR(32),
    password CHAR(32),
    email CHAR(50),
    phone CHAR(15),
    avatar CHAR(128),
    registered_date DATE,
    admin_id INT(15),
    PRIMARY KEY (user_id, admin_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=admin_id, CACHE_NAME=user1, KEY_TYPE=org.ignite.Entity.UserKey, VALUE_TYPE=org.ignite.Entity.User";

DROP TABLE IF EXISTS eBook1;
CREATE TABLE eBook1(
    eBook_id CHAR,
    title CHAR(128),
    description CHAR(1000),
    image_link CHAR(50),
    file_link CHAR(50),
    language CHAR(50),
    release_year YEAR,
    last_update date,
    viewers INT(11),
    eCatalog_id INT(15),
    user_id INT(15),
    PRIMARY KEY (eBook_id, user_id, eCatalog_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=user_id, CACHE_NAME=eBook1, KEY_TYPE=org.ignite.Entity.eBookKey, VALUE_TYPE=org.ignite.Entity.eBook";

DROP TABLE if EXISTS eBookshelf;
CREATE TABLE eBookshelf(
    user_id INT(15) PRIMARY KEY,
    eBook_id INT(15)
) WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=eBookshelf";

DROP TABLE if EXISTS histories;
CREATE TABLE histories (
    user_id INT(15) PRIMARY KEY,
    eBook_id INT(15),
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=histories";

SET STREAMING ON;

INSERT INTO admin1(admin_id, username, password, registered_date) VALUES(1, 'tran hien', '123456', null);

INSERT INTO user1(user_id, username, password, email, phone, avatar, registered_date, admin_id)
VALUES(1, 'eBookshare', '123456', '19020281@vnu.edu.vn', '1234567890', null, null, 1);
INSERT INTO user1(user_id, username, password, email, phone, avatar, registered_date, admin_id)
VALUES(2, 'XZ', '09876', 'adghj@gmail.com', '76543', null, null, 1);

INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(1, 'Nhiếp ảnh, dựng phim', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(2, 'Hôn nhân & Gia đình', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(3, 'Phong cách sống', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(4, 'Sức khỏe - Giới tính', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(5, 'Công nghệ thông tin', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(6, 'Sale - Bán hàng', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(7, 'Phát triển cá nhân', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(8, 'Kinh doanh - Khởi nghiệp', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(9, 'Thiết kế', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(10, 'Tin học văn phòng', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(11, 'Marketing', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(12, 'Ngoại Ngữ', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(13, 'Đề Thi', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(14, 'Thư Viện Pháp Luật', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(15, 'Sách khoa học', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(16, 'Sách ngoại ngữ', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(17, 'Sách kinh doanh', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(18, 'Phát triển cá nhân', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(19, 'Sách văn học', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(20, 'Sách kinh tế', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(21, 'Ẩm thực - Nấu ăn', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(22, 'Tiểu thuyết', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(23, 'Nông - Lâm - Ngư', 1);

INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('abc', 'Tư Duy Nhanh Và Chậm', null, null, null,'Việt Nam', null, '2021-01-01', 0, 1, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('2', 'Bạn thật sự có tài', null, null, null,'Việt Nam', null, '2021-01-02', 0, 1, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('3', 'Nếu Tôi Biết Được Khi Còn 20', null, null, null,'Việt Nam', null, '2001-06-23', 0, 2, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('4', 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ', null, null, null,'Việt Nam', null, '1991-10-05', 0, 1, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('5', 'Phải Trái Đúng Sai', null, null, null,'Việt Nam', null, '2001-01-28', 0, 2, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('6', 'Lối Sống Tối Giản Của Người Nhật', null, null, null,'Việt Nam', null, '2018-12-31', 0, 1, null);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('7', 'Đời Ngắn Đừng Ngủ Dài', null, null, null,'Việt Nam', null, '2021-11-06', 0, 2, null);



SET STREAMING OFF;
