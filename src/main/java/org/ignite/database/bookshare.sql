DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
    adminId INT(15) PRIMARY KEY,
    username CHAR(32),
    password CHAR(32),
    registeredDate DATE
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=admin, VALUE_TYPE=org.ignite.Entity.Admin";

DROP TABLE IF EXISTS eCatalog;
CREATE TABLE eCatalog(
    catalogId INT(15),
    nameCatalog CHAR(128),
    adminId INT(15),
    PRIMARY KEY (catalogId, adminId)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=adminId, CACHE_NAME=catalog, KEY_TYPE=org.ignite.Entity.eCatalogKey, VALUE_TYPE=org.ignite.Entity.eCatalog";

--CREATE INDEX idx_nameCatalog ON eCatalog (nameCatalog);

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    userId INT(15),
    username CHAR(32),
    password CHAR(32),
    email CHAR(50),
    phone CHAR(15),
    avatar CHAR(128),
    registeredDate DATE,
    adminId INT(15),
    PRIMARY KEY (userId, adminId)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=adminId, CACHE_NAME=user, KEY_TYPE=org.ignite.Entity.UserKey, VALUE_TYPE=org.ignite.Entity.User";


DROP TABLE IF EXISTS eBook;
CREATE TABLE eBook(
    eBookId CHAR,
    title CHAR(128),
    description CHAR(1000),
    imageLink CHAR(50),
    fileLink CHAR(50),
    language CHAR(50),
    releaseYear DATE,
    lastUpdate DATE,
    viewers INT(11),
    catalogId INT(15),
    userId INT(15),
    PRIMARY KEY (eBookId, userId)
)WITH "TEMPLATE=partitioned, BACKUPS=1, atomicity=transactional, AFFINITY_KEY=userId, CACHE_NAME=eBook, KEY_TYPE=org.ignite.Entity.eBookKey, VALUE_TYPE=org.ignite.Entity.eBook";

--CREATE INDEX idx_title ON eBook (title);

DROP TABLE if EXISTS eBookshelf;
CREATE TABLE eBookshelf(
    userId INT(15) PRIMARY KEY,
    eBookId INT(15)
) WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=eBookshelf";

DROP TABLE if EXISTS histories;
CREATE TABLE histories (
    userId INT(15) PRIMARY KEY,
    eBookId INT(15),
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=histories";

SET STREAMING ON;

INSERT INTO admin(adminId, username, password, registeredDate) VALUES(1, 'bookshare', 'baogiomoihetbug', '2021-11-17');

INSERT INTO user(userId, username, password, email, phone, avatar, registeredDate, adminId)
VALUES(1, 'eBookshare', '123456', '19020281@vnu.edu.vn', '1234567890', null, '2021-11-17', 1);
INSERT INTO user(userId, username, password, email, phone, avatar, registeredDate, adminId)
VALUES(2, 'XZ', '09876', 'adghj@gmail.com', '76543', null, '2021-11-17', 1);

INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(1, 'Đời sống', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(2, 'Giải trí', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(3, 'Khoa học', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(4, 'Giáo dục', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(5, 'Kinh tế', 1);




INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('1', 'Tư Duy Nhanh Và Chậm', null, '/api/v1/FileUpload/files/Tu-Duy-Nhanh-Va-Cham.jpg', '/api/v1/FileUpload/files/Tu-duy-nhanh-va-cham.pdf','Việt Nam', '2020-01-01', '2021-01-01', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('2', 'Bạn thật sự có tài', null, '/api/v1/FileUpload/files/ban-that-su-co-tai.jpg', '/api/v1/FileUpload/files/ban-that-su-co-tai.pdf','Việt Nam', null, '2021-01-02', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('3', 'Nếu Tôi Biết Được Khi Còn 20', null, '/api/v1/FileUpload/files/neu-toi-biet-duoc-khi-con-20.png', '/api/v1/FileUpload/files/neu-toi-biet-duoc-khi-con-20.pdf','Việt Nam', null, '2001-06-23', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('4', 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ', null, '/api/v1/FileUpload/files/kheo-an-noi-se-co-duoc-thien-ha.jpg', '/api/v1/FileUpload/files/kheo-an-noi-se-co-duoc-thien-ha.pdf','Việt Nam', null, '1991-10-05', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('5', 'Phải Trái Đúng Sai', null, '/api/v1/FileUpload/files/phai-trai-dung-sai.jpg', '/api/v1/FileUpload/files/phai-trai-dung-sai.pdf','Việt Nam', null, '2001-01-28', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('6', 'Lối Sống Tối Giản Của Người Nhật', null, '/api/v1/FileUpload/files/loi-song-toi-gian-cua-nguoi-Nhat.png', '/api/v1/FileUpload/files/loi-song-toi-gian-cua-nguoi-Nhat.pdf','Việt Nam', null, '2018-12-31', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('7', 'Đời Ngắn Đừng Ngủ Dài', null, '/api/v1/FileUpload/files/doi-ngan-dung-ngu-dai.jpg', '/api/v1/FileUpload/files/doi-ngan-dung-ngu-dai.pdf','Việt Nam', null, '2021-11-06', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('8', 'Đọc Vị Bất Kỳ Ai', null, '/api/v1/FileUpload/files/Doc-vi-bat-ky-ai.ipg', '/api/v1/FileUpload/files/Doc-vi-bat-ky-ai.pdf', 'Việt Nam', null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('9', 'Cỗ Máy Bán Hàng Tối Ưu', null, '/api/v1/FileUpload/files/Co-may-ban-hang-toi-uu.jpg', '/api/v1/FileUpload/files/Co-may-ban-hang-toi-uu.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('10', 'Những Nguyên Tắc Thành Công', null, '/api/v1/FileUpload/files/Nhung-nguyen-tac-thanh-cong.jpg', '/api/v1/FileUpload/files/Nhung-nguyen-tac-thanh-cong.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('11', '21 nguyên tắc tự do tài chính - Brian Tracy', null, '/api/v1/FileUpload/files/21-nguyen-tac-tu-do-tai-chinh.jpg', '/api/v1/FileUpload/files/21-nguyen-tac-tu-do-tai-chinh.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('12', '7 Chiến Lược Thịnh Vượng Và Hạnh Phúc', null, '/api/v1/FileUpload/files/7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.jpg', '/api/v1/FileUpload/files/7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('13', 'Bí Mật Dotcom', null, '/api/v1/FileUpload/files/bi-mat-dotcom,jpg', '/api/v1/FileUpload/files/bi-mat-dotcom.pdf', null, null, '2021-11-17', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('14', 'Viết Gì Cũng Đúng', null, '/api/v1/FileUpload/files/viet-gi-cung-dung.jpg', '/api/v1/FileUpload/files/viet-gi-cung-dung.pdf', null, null, '2021-11-17', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('15', 'Bán Niềm Tin', null, '/api/v1/FileUpload/files/Ban-Niem-Tin.jpg', null, null, null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('16', 'Cổ Phiếu Thường Lợi Nhuận Phi Thường', null, '/api/v1/FileUpload/files/co-phieu-thuong-loi-nhuan-phi-thuong-min.jpg', '/api/v1/FileUpload/files/co-phieu-thuong-loi-nhuan-phi-thuong-min.pdf', null, null, '2021-11-17', 0, 5,1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('17', 'Điểm Bùng Phát', null, '/api/v1/FileUpload/files/diem-bung-phat.jpg', '/api/v1/FileUpload/files/diem-bung-phat.pdf', null, null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('18', 'Trí Tuệ Tài Chính', null, '/api/v1/FileUpload/files/tri-tue-tai-chinh.jpg', '/api/v1/FileUpload/files/tri-tue-tai-chinh.pdf', null, null, '2021-11-17', 0, 5, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, viewers, catalogId, userId)
VALUES('19', 'Tung Sản phẩm', null, '/api/v1/FileUpload/files/tung-san-pham.jpg', null, 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('20', 'Thư Bán Hàng Đỉnh Cao', null, '/api/v1/FileUpload/files/thu-ban-hang-đinh-cao.jpg', null, 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('21', 'Tiếp Thị Số Từ A Đến Z', null, '/api/v1/FileUpload/files/tiepthisotuatoiz.jpg', '/api/v1/FileUpload/files/tiepthisotuatoiz.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('22', 'Kinh Doanh Như Một Cuộc Chơi', null, '/api/v1/FileUpload/files/kinh-doanh-nhu-mot-cuoc-choi.jpg', '/api/v1/FileUpload/files/kinh-doanh-nhu-mot-cuoc-choi.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('23', 'Marketing Truyền Miệng', null, '/api/v1/FileUpload/files/marketing-truyen-mieng.jpg', '/api/v1/FileUpload/files/marketing-truyen-mieng.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('24', 'Xì Xầm Vs Hét To - Bí Kíp X Trong Quảng Cáo PR', null, '/api/v1/FileUpload/files/xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.jpg', '/api/v1/FileUpload/files/xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.pdf', 'Việt Nam', null, '2021-11-17', 0, 4, 1),
('25', 'Vượt Lên Những Chuyện Nhỏ Trong Công Việc', null, '/api/v1/FileUpload/files/vuot-len-nhung-chuyen-nho-trong-cong-viec.jpg', '/api/v1/FileUpload/files/vuot-len-nhung-chuyen-nho-trong-cong-viec.pdf', 'Việt Nam', null, '2021-11-17', 0, 1, 1),
('26', 'Kẻ Thành Công Phải Biết Lắng Nghe', null, '/api/v1/FileUpload/files/ke-thanh-cong-phai-biet-lang-nghe.jpg', '/api/v1/FileUpload/files/ke-thanh-cong-phai-biet-lang-nghe.pdf', null, null, '2021-11-17', 0, 4, 1),
('27', 'Nửa Kia Của Hitler', null, '/api/v1/FileUpload/files/Nua-kia-cua-Hitler.jpg', '/api/v1/FileUpload/files/Nua-kia-cua-Hitler.pdf', null, null, '2021-11-17', 0, 2, 1),
('28', 'Đứng Dậy Mạnh Mẽ', null, '/api/v1/FileUpload/files/Dung-day-manh-me.png', '/api/v1/FileUpload/files/Dung-day-manh-me.pdf', null, null, '2021-11-17', 0, 1, 1),
('29', 'Bí Quyết Làm Giàu Của Napoleon Hill', null, '/api/v1/FileUpload/files/bi-quyet-lam-giau-cua-napoleon-hill.jpg', '/api/v1/FileUpload/files/bi-quyet-lam-giau-cua-napoleon-hill.pdf', null, null, '2021-11-17', 0, 3, 1),
('30', 'Phụ Nữ 20 Thay Đổi Để Thành Công', null, '/api/v1/FileUpload/files/phu-nu-20-thay-doi-de-thanh-cong.jpg', '/api/v1/FileUpload/files/phu-nu-20-thay-doi-de-thanh-cong.pdf', null, null, '2021-11-17', 0, 1, 1),
('31', 'Hành Trình Biến Thương Hiệu Thành Biểu Tượng', null, '/api/v1/FileUpload/files/Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.jpg', '/api/v1/FileUpload/files/Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.pdf', null, null, '2021-11-17', 0, 5, 1),
('32', 'Thuật Lãnh Đạo', null, '/api/v1/FileUpload/files/thuat-lanh-dao.jpg', '/api/v1/FileUpload/files/thuat-lanh-dao.pdf', null, null, '2021-11-17', 0, 1, 1),
('33', 'Thuật Đàm Phán', null, '/api/v1/FileUpload/files/thuat-dam-phan.jpg', '/api/v1/FileUpload/files/thuat-dam-phan.pdf', null, null, '2021-11-17', 0, 1, 1),
('34', 'Bạn Không Thể Đổi Hướng Gió, Nhưng Có Thể Điều Khiển Cánh Buồm', null, '/api/v1/FileUpload/files/ban-khong-the-thay-doi-huong-gio.png', '/api/v1/FileUpload/files/ban-khong-the-thay-doi-huong-gio.pdf', null, null, '2021-11-17', 0, 1, 1),
('35', 'Cho Tôi Xin Một Vé Đi Tuổi Thơ', null, '/api/v1/FileUpload/files/cho-toi-xin-mot-ve-di-tuoi-tho.jpg', '/api/v1/FileUpload/files/cho-toi-xin-mot-ve-di-tuoi-tho.pdf', 'Viêt Nam', null, '2021-11-17', 0, 2, 1),
('36', 'Cô Gái Đến Từ Hôm Qua', null, '/api/v1/FileUpload/files/co-gai-den-tu-hom-qua.jpg', '/api/v1/FileUpload/files/co-gai-den-tu-hom-qua.pdf', 'Việt Nam', null, '2021-11-17', 0,2, 1),
('37', 'Quốc Gia Khởi Nghiệp', null, '/api/v1/FileUpload/files/quoc-gia-khoi-nghiep.jpg', '/api/v1/FileUpload/files/quoc-gia-khoi-nghiep.pdf', null, null, '2021-11-17', 0, 5, 1);


SET STREAMING OFF;
