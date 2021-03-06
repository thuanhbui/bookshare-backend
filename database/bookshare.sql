DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
    admin_id INT(15) PRIMARY KEY, 
    username CHAR(32), 
    password CHAR(32), 
    registered_date DATE
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=admin, VALUE_TYPE=model.Entity.Admin";

DROP TABLE IF EXISTS eCatalog;
CREATE TABLE eCatalog(
    catalog_id INT(15), 
    name_catalog CHAR(128), 
    eBook_id CHAR, 
    PRIMARY KEY (catalog_id, eBook_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=eBook_id, CACHE_NAME=catalog, KEY_TYPE=model.Key.eCatalogKey, VALUE_TYPE=model.Entity.eCatalog";

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    user_id INT(15), 
    username CHAR(32), 
    password CHAR(32), 
    email CHAR(50), 
    phone CHAR(15), 
    address CHAR(128), 
    registered_date DATE, 
    admin_id INT(15), 
    PRIMARY KEY (user_id, admin_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=admin_id, CACHE_NAME=user, KEY_TYPE=model.Key.UserKey, VALUE_TYPE=model.Entity.User";

DROP TABLE IF EXISTS eBook;
CREATE TABLE eBook(
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
    PRIMARY KEY (eBook_id, user_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=user_id, CACHE_NAME=eBook, KEY_TYPE=model.Key.eBookKey, VALUE_TYPE=model.Entity.eBook";

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



INSERT INTO admin(admin_id, username, password, registered_date) VALUES(1, 'tran hien', '123456', null);

INSERT INTO user(user_id, username, password, email, phone, address, registered_date, admin_id) 
VALUES(1, 'eBookshare', '123456', '19020281@vnu.edu.vn', '1234567890', 'abcd', null, 1);
INSERT INTO user(user_id, username, password, email, phone, address, registered_date, admin_id) 
VALUES(2, 'XZ', '09876', 'adghj@gmail.com', '76543', 'UET', null, 1);

INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(1, 'Nhi???p ???nh, d???ng phim', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(2, 'H??n nh??n & Gia ????nh', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(3, 'Phong c??ch s???ng', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(4, 'S???c kh???e - Gi???i t??nh', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(5, 'C??ng ngh??? th??ng tin', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(6, 'Sale - B??n h??ng', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(7, 'Ph??t tri???n c?? nh??n', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(8, 'Kinh doanh - Kh???i nghi???p', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(9, 'Thi???t k???', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(10, 'Tin h???c v??n ph??ng', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(11, 'Marketing', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(12, 'Ngo???i Ng???', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(13, '????? Thi', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(14, 'Th?? Vi???n Ph??p Lu???t', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(15, 'S??ch khoa h???c', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(16, 'S??ch ngo???i ng???', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(17, 'S??ch kinh doanh', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(18, 'Ph??t tri???n c?? nh??n', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(19, 'S??ch v??n h???c', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(20, 'S??ch kinh t???', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(21, '????m th????c - N????u ??n', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(22, 'Ti????u thuy????t', 1);
INSERT INTO eCatalog(catalog_id, name_catalog, eBook_id) VALUES(23, 'N??ng - L??m - Ng??', 1);

INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('abc', 'T?? Duy Nhanh V?? Ch???m', null, null, null,'Vi????t Nam', null, '2021-01-01', 0, 1);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('2', 'B???n th???t s??? c?? t??i', null, null, null,'Vi????t Nam', null, '2021-01-02', 0, 1);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('3', 'N???u T??i Bi???t ???????c Khi C??n 20', null, null, null,'Vi????t Nam', null, '2001-06-23', 0, 2);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('4', 'Kh??o ??n N??i S??? C?? ???????c Thi??n H???', null, null, null,'Vi????t Nam', null, '1991-10-05', 0, 1);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('5', 'Ph???i Tr??i ????ng Sai', null, null, null,'Vi????t Nam', null, '2001-01-28', 0, 2);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('6', 'L???i S???ng T???i Gi???n C???a Ng?????i Nh???t', null, null, null,'Vi????t Nam', null, '2018-12-31', 0, 1);
INSERT INTO eBook(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id)
VALUES('7', '?????i Ng???n ?????ng Ng??? D??i', null, null, null,'Vi????t Nam', null, '2021-11-06', 0, 2);


















