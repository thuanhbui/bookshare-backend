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
<<<<<<< HEAD
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(3, 'Kỹ năng sống', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(4, 'Sức khỏe - Giới tính', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(5, 'Công nghệ thông tin', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(6, 'Sale - Bán hàng', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(7, 'Sách hay về cuộc sống', 1);
=======
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(3, 'Phong cách sống', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(4, 'Sức khỏe - Giới tính', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(5, 'Công nghệ thông tin', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(6, 'Sale - Bán hàng', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(7, 'Phát triển cá nhân', 1);
>>>>>>> 74f707ff095b7f5a0a324ca3fa4c9323c094c52a
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
<<<<<<< HEAD
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(19, 'Sách văn học Việt Nam', 1);
=======
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(19, 'Sách văn học', 1);
>>>>>>> 74f707ff095b7f5a0a324ca3fa4c9323c094c52a
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(20, 'Sách kinh tế', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(21, 'Ẩm thực - Nấu ăn', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(22, 'Tiểu thuyết', 1);
INSERT INTO eCatalog1(catalog_id, name_catalog, admin_id) VALUES(23, 'Nông - Lâm - Ngư', 1);
<<<<<<< HEAD
=======

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
>>>>>>> 74f707ff095b7f5a0a324ca3fa4c9323c094c52a

INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('1', 'Tư Duy Nhanh Và Chậm', null, 'Tu-Duy-Nhanh-Va-Cham.jpg', 'Tu-duy-nhanh-va-cham.pdf','Việt Nam', null, '2021-01-01', 0, 1, 18);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('2', 'Bạn thật sự có tài', null, 'ban-that-su-co-tai.jpg', 'ban-that-su-co-tai.pdf','Việt Nam', null, '2021-01-02', 0, 1, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('3', 'Nếu Tôi Biết Được Khi Còn 20', null, 'neu-toi-biet-duoc-khi-con-20.png', 'neu-toi-biet-duoc-khi-con-20.pdf','Việt Nam', null, '2001-06-23', 0, 2, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('4', 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ', null, 'kheo-an-noi-se-co-duoc-thien-ha.jpg', 'kheo-an-noi-se-co-duoc-thien-ha.pdf','Việt Nam', null, '1991-10-05', 0, 1, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('5', 'Phải Trái Đúng Sai', null, 'phai-trai-dung-sai.jpg', 'phai-trai-dung-sai.pdf','Việt Nam', null, '2001-01-28', 0, 2, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('6', 'Lối Sống Tối Giản Của Người Nhật', null, 'loi-song-toi-gian-cua-nguoi-Nhat.png', 'loi-song-toi-gian-cua-nguoi-Nhat.pdf','Việt Nam', null, '2018-12-31', 0, 1, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('7', 'Đời Ngắn Đừng Ngủ Dài', null, 'doi-ngan-dung-ngu-dai.jpg', 'doi-ngan-dung-ngu-dai.pdf','Việt Nam', null, '2021-11-06', 0, 2, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('8', 'Đọc Vị Bất Kỳ Ai', null, 'Doc-vi-bat-ky-ai.ipg', 'Doc-vi-bat-ky-ai.pdf', 'Việt Nam', null, null, 0, 1, 3);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('9', 'Cỗ Máy Bán Hàng Tối Ưu', null, 'Co-may-ban-hang-toi-uu.jpg', 'Co-may-ban-hang-toi-uu.pdf', 'Việt Nam', null, null, 0, 1, 20);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('10', 'Những Nguyên Tắc Thành Công', null, 'Nhung-nguyen-tac-thanh-cong.jpg', 'Nhung-nguyen-tac-thanh-cong.pdf', 'Việt Nam', null, null, 0, 1, 18);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('11', '21 nguyên tắc tự do tài chính - Brian Tracy', null, '21-nguyen-tac-tu-do-tai-chinh.jpg', '21-nguyen-tac-tu-do-tai-chinh.pdf', 'Việt Nam', null, null, 0, 1, 20);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('12', '7 Chiến Lược Thịnh Vượng Và Hạnh Phúc', null, '7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.jpg', '7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.pdf', 'Việt Nam', null, null, 0, 1, 20);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('13', 'Bí Mật Dotcom', null, 'bi-mat-dotcom,jpg', 'bi-mat-dotcom.pdf', null, null, null, 0, 1, 17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('14', 'Viết Gì Cũng Đúng', null, 'viet-gi-cung-dung.jpg', 'viet-gi-cung-dung.pdf', null, null, null, 0, 1, 17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('15', 'Bán Niềm Tin', null, 'Ban-Niem-Tin.jpg', null, null, null, null, 0, 1, 17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('16', 'Cổ Phiếu Thường Lợi Nhuận Phi Thường', null, 'co-phieu-thuong-loi-nhuan-phi-thuong-min.jpg', 'co-phieu-thuong-loi-nhuan-phi-thuong-min.pdf', null, null, null, 0, 1, 17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('17', 'Điểm Bùng Phát', null, 'diem-bung-phat.jpg', 'diem-bung-phat.pdf', null, null, null, 0, 1, 17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('18', 'Trí Tuệ Tài Chính', null, 'tri-tue-tai-chinh.jpg', 'tri-tue-tai-chinh.pdf', null, null, null, 0, 1,17);
INSERT INTO eBook1(eBook_id, title, description, image_link, file_link, language, release_year, last_update, viewers, user_id, eCatalog_id)
VALUES('19', 'Tung Sản phẩm', null, 'tung-san-pham.jpg', null, 'Việt Nam', null, null, 0, 1, 17),
('20', 'Thư Bán Hàng Đỉnh Cao', null, 'thu-ban-hang-đinh-cao.jpg', null, 'Việt Nam', null, null, 0, 1, 17),
('21', 'Tiếp Thị Số Từ A Đến Z', null, 'tiepthisotuatoiz.jpg', 'tiepthisotuatoiz.pdf', 'Việt Nam', null, null, 0, 1, 17),
('22', 'Kinh Doanh Như Một Cuộc Chơi', null, 'kinh-doanh-nhu-mot-cuoc-choi.jpg', 'kinh-doanh-nhu-mot-cuoc-choi.pdf', 'Việt Nam', null, null, 0, 1, 17),
('23', 'Marketing Truyền Miệng', null, 'marketing-truyen-mieng.jpg', 'marketing-truyen-mieng.pdf', 'Việt Nam', null, null, 0, 1, 17),
('24', 'Xì Xầm Vs Hét To - Bí Kíp X Trong Quảng Cáo PR', null, 'xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.jpg', 'xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.pdf', 'Việt Nam', null, null, 0, 1, 17),
('25', 'Vượt Lên Những Chuyện Nhỏ Trong Công Việc', null, 'vuot-len-nhung-chuyen-nho-trong-cong-viec.jpg', 'vuot-len-nhung-chuyen-nho-trong-cong-viec.pdf', 'Việt Nam', null, null, 0, 1, 3),
('26', 'Kẻ Thành Công Phải Biết Lắng Nghe', null, 'ke-thanh-cong-phai-biet-lang-nghe.jpg', 'ke-thanh-cong-phai-biet-lang-nghe.pdf', null, null, null, 0, 1, 3),
('27', 'Nửa Kia Của Hitler', null, 'Nua-kia-cua-Hitler.jpg', 'Nua-kia-cua-Hitler.pdf', null, null, null, 0, 1, 19),
('28', 'Đứng Dậy Mạnh Mẽ', null, 'Dung-day-manh-me.png', 'Dung-day-manh-me.pdf', null, null, null, 0, 1, 7),
('29', 'Bí Quyết Làm Giàu Của Napoleon Hill', null, 'bi-quyet-lam-giau-cua-napoleon-hill.jpg', 'bi-quyet-lam-giau-cua-napoleon-hill.pdf', null, null, null, 0, 1, 17),
('30', 'Phụ Nữ 20 Thay Đổi Để Thành Công', null, 'phu-nu-20-thay-doi-de-thanh-cong.jpg', 'phu-nu-20-thay-doi-de-thanh-cong.pdf', null, null, null, 0, 1, 7),
('31', 'Hành Trình Biến Thương Hiệu Thành Biểu Tượng', null, 'Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.jpg', 'Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.pdf', null, null, null, 0, 1, 17),
('32', 'Thuật Lãnh Đạo', null, 'thuat-lanh-dao.jpg', 'thuat-lanh-dao.pdf', null, null, null, 0, 1, 18),
('33', 'Thuật Đàm Phán', null, 'thuat-dam-phan.jpg', 'thuat-dam-phan.pdf', null, null, null, 0, 1, 18),
('34', 'Bạn Không Thể Đổi Hướng Gió, Nhưng Có Thể Điều Khiển Cánh Buồm', null, 'ban-khong-the-thay-doi-huong-gio.png', 'ban-khong-the-thay-doi-huong-gio.pdf', null, null, null, 0, 1, 7),
('35', 'Cho Tôi Xin Một Vé Đi Tuổi Thơ', null, 'cho-toi-xin-mot-ve-di-tuoi-tho.jpg', 'cho-toi-xin-mot-ve-di-tuoi-tho.pdf', 'Viêt Nam', null, null, 0, 1, 19),
('36', 'Cô Gái Đến Từ Hôm Qua', null, 'co-gai-den-tu-hom-qua.jpg', 'co-gai-den-tu-hom-qua.pdf', 'Việt Nam', null, null, 0, 1, 19),
('37', 'Quốc Gia Khởi Nghiệp', null, 'quoc-gia-khoi-nghiep.jpg', 'quoc-gia-khoi-nghiep.pdf', null, null, null, 0, 1, 8);


SET STREAMING OFF;
