DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
    adminId INT(15) PRIMARY KEY,
    username CHAR(32),
    password CHAR(250),
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
    password CHAR(250),
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
    description CHAR(2000),
    imageLink CHAR(50),
    fileLink CHAR(50),
    language CHAR(50),
    releaseYear DATE,
    lastUpdate DATE,
    likes INT(11),
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
-- password = baogiomoihetbug
INSERT INTO admin(adminId, username, password, registeredDate) VALUES(1, 'bookshare', '$2a$10$x3oN4qvV/UVmSspU9RWGxeBYUZUxkqPd90RPidkw1ucqQzTYuaK4C', '2021-11-17');

-- password = 123456
INSERT INTO user(userId, username, password, email, phone, avatar, registeredDate, adminId)
VALUES(1, 'eBookshare', '$2a$10$S86yX6fWpg5bjL3H2aQSmeXmgj.6DwA9rreoPtKFRh2emVa7t29Py', '19020281@vnu.edu.vn', '1234567890', null, '2021-11-17', 1);
-- password = ignite
INSERT INTO user(userId, username, password, email, phone, avatar, registeredDate, adminId)
VALUES(2, 'XZ', '$2a$10$Ynb0K2xjbPZQRbhxpJ0Ld.8NoUbPu1Ob0EC3WpIqbzYK.jDim62jq', 'adghj@gmail.com', '76543', null, '2021-11-17', 1);

INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(1, 'Life skills', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(2, 'Entertainment', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(3, 'Science', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(4, 'Education', 1);
INSERT INTO eCatalog(catalogId, nameCatalog, adminId) VALUES(5, 'Economy', 1);




INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('1', 'Tư Duy Nhanh Và Chậm', 'Bạn nghĩ rằng bạn tư duy nhanh, hay chậm? Bạn tư duy và suy nghĩ theo lối “trông mặt bắt hình dong”, đánh giá mọi vật nhanh chóng bằng cảm quan, quyết định dựa theo cảm xúc hay tư duy một cách cẩn thận, chậm rãi nhưng logic hợp lý về một vấn đề. Thinking fast and slow sẽ đưa ra và lý giải hai hệ thống tư duy tác động đến con đường nhận thức của bạn. Kahneman gọi đó là: hệ thống 1 và hệ thống 2. Hệ thống 1, còn gọi là cơ chế nghĩ nhanh, tự động, thường xuyên được sử dụng, cảm tính, rập khuôn và tiềm thức. Hệ thống 2, còn gọi là cơ chế nghĩ chậm, đòi hỏi ít nỗ lực, ít được sử dụng, dùng logic có tính toán và ý thức. Trong một loạt thí nghiệm tâm lý mang tính tiên phong, Kahneman và Tversky chứng minh rằng, con người chúng ta thường đi đến quyết định theo cơ chế nghĩ nhanh hơn là ghĩ chậm. Phần lớn nội dung của cuốn sách chỉ ra những sai lầm của con người khi suy nghĩ theo hệ thống 1. Kahneman chứng minh rằng chúng ta tệ hơn những gì chúng ta tưởng: đó là chúng ta không biết những gì chúng ta không biết!', 'Tu-Duy-Nhanh-Va-Cham.jpg', 'Tu-duy-nhanh-va-cham.pdf','Việt Nam', '2020-01-01', '2021-01-01', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('2', 'Bạn thật sự có tài', 'Khả năng sáng tạo không phải một loại tài năng thiên bẩm chỉ dành cho một số người đặc biệt, nó là một kỹ năng bất cứ ai cũng có thể học hỏi. Dựa trên nền tảng đó, tác giả - một chuyên gia huấn luyện và cũng là một giáo sư của Đại học Stanford sẽ giúp độc giả hiểu đúng hơn về sự sáng tạo. Cùng với việc làm rõ bản chất của sáng tạo, bà còn cung cấp cho độc giả nhiều ví dụ cực kỳ thú vị về những ý tưởng đột phát ở khắp mọi nơi, trong đó có những công ty nổi tiếng như Google, Pixar, Facebook, IDEO', 'ban-that-su-co-tai.jpg', 'ban-that-su-co-tai.pdf','Việt Nam', null, '2021-01-02', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('3', 'Nếu Tôi Biết Được Khi Còn 20', '“Rất ít người cố gắng làm đủ mọi điều để nuôi dưỡng tư tưởng cách tân như Tina Seelig. Những nguyên tắc trong cuốn sách của cô chắc chắn sẽ làm bật lên nhiều ý tưởng mới mẻ. Đây thật sự là một cuốn sách rất cần thiết cho thế hệ doanh nhân mới, và cho cả những ai đã dạn dày trận mạc.” – David Kelley, nhà sáng lập của IDEO.', 'neu-toi-biet-duoc-khi-con-20.png', 'neu-toi-biet-duoc-khi-con-20.pdf','Việt Nam', null, '2001-06-23', 1, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('4', 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ', 'Khéo Ăn Nói Sẽ Có Được Thiên Hạ. Xã hội hiện đại, từ xin việc đến thăng chức, từ tình yêu đến hôn nhân, từ giao lưu đến hợp tác… không việc gì không cần tài ăn nói. Khéo ăn nói giống như sở hữu loại “dầu bôi trơn” đảm bảo các mối quan hệ của bạn “vận hành” trơn tru. Không khéo ăn nói, gặp chuyện nhỏ mắc trở ngại, gặp chuyện lớn vấp thất bại. Làm thế nào để nói năng trôi chảy? Làm thế nào để nói lời “đi vào lòng người”? Trong những dịp khác nhau, với những người khác nhau, ở những tình huống không giống nhau… có cuốn sách này gợi ý, bạn sẽ thành người khéo ăn nói.', 'kheo-an-noi-se-co-duoc-thien-ha.jpg', 'kheo-an-noi-se-co-duoc-thien-ha.pdf','Việt Nam', null, '1991-10-05', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('5', 'Phải Trái Đúng Sai', 'Sách bàn về vấn đề đạo đức dưới cái nhìn triết học. Tác giả đưa ra các vụ việc gây tranh cãi về vấn đề đạo đức để mổ xẻ dưới nhiều góc độ, theo quan điểm của các học thuyết triết học khác nhau, mỗi chương trình bày sâu về một học thuyết. Nhờ vậy, tư tưởng của Aristotle, Jeremy Bentham, Immanuel Kant, John Stuart Mill, Robert Nozick, và John Rawl được trình bày với sự rõ ràng và gần gũi, mà theo New York Times là "hiếm khi được giải thích dễ hiểu đến như vậy".', 'phai-trai-dung-sai.jpg', 'phai-trai-dung-sai.pdf','Việt Nam', null, '2001-01-28', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('6', 'Lối Sống Tối Giản Của Người Nhật', 'Chẳng có ai từ khi sinh ra đã có tài sản, đồ đạc gì trong tay. Vậy nên bất cứ ai khi mới chào đời đều là những người sống tối giản. Cứ mỗi lần bạn sở hữu trong tay những đồ dùng hơn mức cần thiết là một lần bạn lấy mất tự do của chính mình. Giá trị bản thân chúng ta không đo bằng những đồ dùng mà chúng ta sở hữu. Những đồ dùng này chỉ cho chúng ta một chút cảm giác hạnh phúc nhất thời mà thôi. Mang theo những đồ dùng hơn mức cần thiết sẽ lấy hết thời gian, năng lượng của bạn. Khi nhận ra được điều đó, tức là bạn đã bắt đầu trở thành một người sống tối giản. Những người sống tối giản luôn cảm thấy vui vẻ, mới lạ mỗi ngày. Cái cảm giác này, tôi nghĩ bất cứ ai cũng có thể cảm nhận được, dù bạn có phải là một người sống tối giản hay không, bởi bất cứ ai.', 'loi-song-toi-gian-cua-nguoi-Nhat.png', 'loi-song-toi-gian-cua-nguoi-Nhat.pdf','Việt Nam', null, '2018-12-31', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('7', 'Đời Ngắn Đừng Ngủ Dài', '“Mọi lựa chọn đều giá trị. Mọi bước đi đều quan trọng. Cuộc sống vẫn diễn ra theo cách của nó, không phải theo cách của ta. Hãy kiên nhẫn. Tin tưởng. Hãy giống như người thợ cắt đá, đều đặn từng nhịp, ngày qua ngày. Cuối cùng, một nhát cắt duy nhất sẽ phá vỡ tảng đá và lộ ra viên kim cương. Người tràn đầy nhiệt huyết và tận tâm với việc mình làm không bao giờ bị chối bỏ. Sự thật là thế.” Bằng những lời chia sẻ thật ngắn gọn, dễ hiểu về những trải nghiệm và suy ngẫm trong đời, Robin Sharma tiếp tục phong cách viết của ông từ cuốn sách Điều vĩ đại đời thường để mang đến cho độc giả những bài viết như lời tâm sự, vừa chân thành vừa sâu sắc.', 'doi-ngan-dung-ngu-dai.jpg', 'doi-ngan-dung-ngu-dai.pdf','Việt Nam', null, '2021-11-06', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('8', 'Đọc Vị Bất Kỳ Ai', 'Hãy chiếm thế thượng phong trong việc chủ động nhận biết điều cần tìm kiếm - ở bất kỳ ai bằng cách “thâm nhập vào suy nghĩ” của người khác. ĐỌC VỊ BẤT KỲ AI là cẩm nang dạy bạn cách thâm nhập vào tâm trí của người khác để biết điều người ta đang nghĩ. Cuốn sách này sẽ không giúp bạn rút ra các kết luận chung về một ai đó dựa vào cảm tính hay sự võ đoán. Những nguyên tắc được chia sẻ trong cuốn sách này không đơn thuần là những lý thuyết hay mẹo vặt chỉ đúng trong một số trường hợp hoặc với những đối tượng nhất định. Các kết quả nghiên cứu trong cuốn sách này được đưa ra dựa trên phương pháp S.N.A.P - cách thức phân tích và tìm hiểu tính cách một cách bài bản trong phạm vi cho phép mà không làm mếch lòng đối tượng được phân tích. Phương pháp này dựa trên những phân tích về tâm lý, chứ không chỉ đơn thuần dựa trên ngôn ngữ cử chỉ, trực giác hay võ đoán.', 'Doc-vi-bat-ky-ai.jpg', 'Doc-vi-bat-ky-ai.pdf', 'Việt Nam', null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('9', 'Cỗ Máy Bán Hàng Tối Ưu', 'Từng có một câu chuyện về một người bán hàng vĩ đại - người có thể bán bất cứ sản phẩm nào anh ta muốn. Và vì vậy anh ta đã trở thành bậc thầy bán hàng, một tài sản vô giá và là niềm mong ước của mọi doanh nghiệp. Trở thành bậc thầy về bán hàng là niềm mong ước của mọi doanh nghiệp. Điều đó cũng đồng nghĩa với việc tạo lập thành công cho mình một tương lai rộng mở và chắc chắn. Nếu bạn thật sự mong muốn điều đó, cuốn sách Cỗ máy bán hàng tối ưu này sẽ giúp bạn đạt được mục đích của mình. Với một điều kiện: Bạn phải đọc thật kỹ lưỡng, hiểu thật chính xác và làm theo một cách đầy đủ nhất những điều bạn đã đọc được trong cuốn sách này.', 'Co-may-ban-hang-toi-uu.jpg', 'Co-may-ban-hang-toi-uu.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('10', 'Những Nguyên Tắc Thành Công', 'Những Nguyên Tắc Thành Công sẽ dạy bạn cách củng cố, tăng cường lòng tự tin, vượt qua những khó khăn thách thức thường ngày, sống với đam mê và mục tiêu của mình, nhận ra tất cả những khát vọng của bản thân. Đây không phải là cuốn sách của những ý tưởng hay. Đây là cuốn sách tập hợp 64 nguyên tắc sống mãi với thời gian đã được nhiều người thành đạt áp dụng. Nếu thực hiện hàng ngày, những nguyên tắc này sẽ đưa cuộc sống của bạn vượt ra khỏi những giấc mơ mãnh liệt nhất. Với những câu chuyện truyền cảm hứng và hết sức ấn tượng về các doanh nhân, các vận động viên xuất sắc, về những người thành đạt trong tất cả mọi lĩnh vực, Những Nguyên Tắc Thành Công đã vẽ ra một tấm bản đồ bạn cần thực hiện để đạt được bất kỳ mục tiêu và mơ uớc nào trong cuộc sống.', 'Nhung-nguyen-tac-thanh-cong.jpg', 'Nhung-nguyen-tac-thanh-cong.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('11', '21 nguyên tắc tự do tài chính - Brian Tracy', 'Bạn có tin rằng người bình thường có thể đạt được những thành công tài chính phi thường? Chỉ với một cuốn sách nhỏ (nhưng là sự tích lũy 15 năm nghiên cứu, giảng dạy và trải nghiệm của Brian Tracy về chủ đề từ tay trắng trở thành triệu phú), tác giả sẽ cung cấp cho bạn phương thức để trở thành triệu phú, cho dù bạn là ai và có xuất phát điểm hiện tại như thế nào. Bạn sẽ học được cách xác lập mục tiêu, lên kế hoạch và tổ chức hành động để có thể đạt được nhiều mục tiêu tài chính hơn những gì bạn mong muốn. Những người thành công đơn giản là những người bình thường đã luyện tập và áp dụng thành công 21 nguyên tắc này để đạt được tự do tài chính. Và bạn hoàn toàn có thể giống họ.', '21-nguyen-tac-tu-do-tai-chinh.jpg', '21-nguyen-tac-tu-do-tai-chinh.pdf', 'Việt Nam', null, '2021-11-17', 0, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('12', '7 Chiến Lược Thịnh Vượng Và Hạnh Phúc', 'Năm 25 tuổi, Jim Rohn tổng kết lại chặng đường đã qua và hoảng hốt nhận ra rằng cuộc sống của ông khác xa với những mục tiêu mà ông tự đặt ra cho mình. Giữa lúc tuyệt vọng đó, ông gặp Earl Shoaff, và học được từ người thầy tinh thần này những bài học cuộc sống vô cùng quý báu. Jim Rohn đã áp dụng những bài học này vào thực tế và gặt hái được nhiều thành công trong cuộc sống, một cuộc sống giàu có và hạnh phúc. Qua cuốn sách này, Jim Rohn muốn truyền thông với người đọc một cách đơn giản và trực tiếp về những ý tưởng sẽ tạo nên sự khác biệt trong cách thức thay đổi cuộc sống của mỗi người.', '7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.jpg', '7-chien-luoc-thinh-vuong-va-hanh-phuc-jim-rohn.pdf', 'Việt Nam', null, '2021-11-17', 1, 3, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('13', 'Bí Mật Dotcom', '"Trước hết, nếu bạn đang kỳ vọng Bí Mật Dotcom sẽ đem đến cho bạn những "công cụ" mới mẻ, hấp dẫn, nhanh chóng, dễ dùng hay những mẹo quảng cáo khéo léo, những chiêu trò có thể giúp bạn kiếm tiền ngay hôm nay nhưng đòi hỏi bạn phải liên tục tìm ra những mánh lới khác. Đây không hẳn là điều bạn đang tìm kiếm. Nhưng nếu bạn đang muốn xây dựng một Công Ty Online bền vững, đây đích thị là cuốn sách dành cho bạn. Nó sẽ mang đến cho bạn một sự hiểu biết sâu sắc và một sự thấu suốt về cấu trúc và khoa học marketing hiệu quả để áp dụng chúng trên thế giới truyền thông online. Cuốn sách sẽ giúp bạn biết cách làm thế nào để cấu trúc lại sản phẩm và dịch vụ của công ty mình sao cho số tiền kiếm được nhiều gấp hai đến ba lần từ lượng truy cập bạn đang có. Khi làm theo các bước được hướng dẫn, mọi thứ sẽ bắt đầu vào guồng xoay của nó và bạn có thể đầu tư nhiều hơn để kiếm được nhiều khách hàng mới hơn."', 'bi-mat-dotcom.jpg', 'bi-mat-dotcom.pdf', null, null, '2021-11-17', 0, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('14', 'Viết Gì Cũng Đúng', 'Cuốn sách này giúp người đọc có một nhận thức đầy đủ và đúng đắn hơn về biện luận, đó không phải chỉ đơn thuần là việc đưa ra những ý kiến của mình dưới một dạng thức mới, mà là việc đưa ra hàng loạt lý do hay bằng chứng để củng cố cho kết luận. Biện luận không phải là tuyên bố hay tranh cãi về một quan điểm hay vấn đề nào đó, mà là cố gắng bảo vệ cho một quan điểm nhất định với các lý lẽ và bằng chứng rõ ràng.', 'viet-gi-cung-dung.jpg', 'viet-gi-cung-dung.pdf', null, null, '2021-11-17', 2, 4, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('15', 'Bán Niềm Tin', 'JOHN BRINKLEY từng là bác sĩ giàu có nhất thời của ông, cũng là nhà quảng cáo mang đậm phong cách Barnum nhất thời đại của ông. Ông bị gièm pha và bị truy tố vì tội lừa đảo, nhưng đồng thời cũng được ca ngợi như một vị thánh bởi không biết bao nhiêu người đàn ông đã được tận hưởng sức mạnh của “Suối nguồn tươi trẻ” dưới bàn tay ma thuật của ông. Và tất nhiên là các bà vợ của họ nữa. Cuốn sách này sẽ đi sâu vào tìm hiểu 21 NGUYÊN TẮC MARKETING của bác sĩ Brinkley, cung cấp cho bạn một bản hướng dẫn cho các hoạt động quảng cáo, marketing và quảng bá bản thân, cho phép bạn “chạm được mạch vàng” bất kể bạn đang hoạt động trong lĩnh vực nào. NẾU BẠN MUỐN – VÀ CHẮC CHẮN BẠN SẼ THU VỀ LỢI NHUẬN VÔ KỂ – từ việc khiến mình và công việc kinh doanh của mình trở nên nổi tiếng và có sức hút uy lực như nam châm, ở trong nước hay trên toàn thế giới, thì bản phân tích chuyên sâu về NHỮNG BÍ QUYẾT THẤT TRUYỀN đằng sau câu chuyện thành công đáng kinh ngạc này là dành cho bạn!', 'Ban-Niem-Tin.jpg', null, null, null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('16', 'Cổ Phiếu Thường Lợi Nhuận Phi Thường', 'Philip Fisher là một trong số các nhà đầu tư có ảnh hưởng lớn nhất mọi thời đại. Triết lý đầu tư của ông không chỉ được nhiều chuyên gia hiện đại nghiên cứu và áp dụng mà còn được rất nhiều nhà đầu tư coi đó là cẩm nang dẫn đường cho bản thân. Những triết lý này đã được tập hợp trong cuốn sách nổi tiếng của ông - Cổ phiếu thường, Lợi nhuận phi thường (Common Stocks and Uncommon Profits), một trong những giáo trình đầu tư kinh điển dành cho các nhà đầu tư hiện đại.', 'co-phieu-thuong-loi-nhuan-phi-thuong-min.jpg', 'co-phieu-thuong-loi-nhuan-phi-thuong-min.pdf', null, null, '2021-11-17', 0, 5,1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('17', 'Điểm Bùng Phát', 'Điểm Bùng Phát là một khoảnh khắc kỳ ảo, khi một ý tưởng, một xu thế, hay một hành vi xã hội vượt qua ngưỡng nhất định – bùng phát và lan ra như ngọn lửa hoang dã. Giống như chỉ một người bị ốm cũng có thể làm khởi phát cả dịch cúm, một mục tiêu nhỏ bé nhưng chính xác hoàn toàn có thể trở thành nguyên nhân thúc đẩy một xu hướng thời trang, nhân rộng việc tiêu thụ của sản phẩm, hay làm hạ tỷ lệ phạm tội, v.v. Trong cuốn sách kinh doanh được độc giả chào đón nồng nhiệt này, Malcolm Gladwell đã khám phá và khai mở cho chúng ta những hiểu biết sâu sắc về hiện tượng “điểm bùng phát”, nó sẽ làm thay đổi hoàn toàn nhận thức của tất cả mọi người trên khắp thế giới về việc tiêu thụ các sản phẩm và phổ biến các ý tưởng.', 'diem-bung-phat.jpg', 'diem-bung-phat.pdf', null, null, '2021-11-17', 0, 1, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('18', 'Trí Tuệ Tài Chính', 'Financial Intelligence – Trí tuệ tài chính ngay từ khi ra đời đã trở thành cuốn sách ưa thích của những nhà quản lý cần trang bị kiến thức về các con số bởi nó giúp họ hiểu được không chỉ ý nghĩa của các con số mà còn lý do tại sao chúng lại quan trọng. Cuốn sách tư duy này chứa đựng những thông tin cập nhật nhất cùng các kiến thức cơ bản về tài chính, từ đó dạy cho nhà quản lý biết cách sử dụng những dữ liệu tài chính để điều hành doanh nghiệp của mình. Ngoài ra, nó cũng nêu lên những vấn đề đang có vai trò ngày càng quan trọng trong những năm gần đây như các lo ngại về khủng hoảng tài chính và tình trạng thiếu kiến thức về kế toán và tài chính trên diện rộng.', 'tri-tue-tai-chinh.jpg', 'tri-tue-tai-chinh.pdf', null, null, '2021-11-17', 10, 5, 1);
INSERT INTO eBook(eBookId, title, description, imageLink, fileLink, language, releaseYear, lastUpdate, likes, catalogId, userId)
VALUES('19', 'Tung Sản phẩm', 'Dù bạn đã có công việc kinh doanh hay đang nhen nhóm ý định khởi nghiệp thì quyển sách này chính là công thức để giúp bạn có động lực hơn. Hãy nghĩ về điều này – sẽ như thế nào nếu bạn có thể cho ra mắt sản phẩm của bạn như Apple hay những studio lớn của Holywood? Sẽ như thế nào nếu khi mà người tiều dùng của bạn đếm ngược từng ngày để chờ đợi đến khi họ được mua sản phẩm của bạn? Sẽ như thế nào nếu bạn có thể tạo được một chỗ đứng vững chắc trên thị trường mà không đối thủ nào có thể cạnh tranh được với bạn? Và bạn có thể thực hiện được điều đó, dù doanh nghiệp của bạn có quy mô nhỏ hay ngân sách hạn chế.', 'tung-san-pham.jpg', null, 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('20', 'Thư Bán Hàng Đỉnh Cao', null, 'thu-ban-hang-đinh-cao.jpg', null, 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('21', 'Tiếp Thị Số Từ A Đến Z', null, 'tiepthisotuatoiz.jpg', 'tiepthisotuatoiz.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('22', 'Kinh Doanh Như Một Cuộc Chơi', null, 'kinh-doanh-nhu-mot-cuoc-choi.jpg', 'kinh-doanh-nhu-mot-cuoc-choi.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('23', 'Marketing Truyền Miệng', null, 'marketing-truyen-mieng.jpg', 'marketing-truyen-mieng.pdf', 'Việt Nam', null, '2021-11-17', 0, 5, 1),
('24', 'Xì Xầm Vs Hét To - Bí Kíp X Trong Quảng Cáo PR', null, 'xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.jpg', 'xi-xam-het-to-bi-kip-x-trong-quang-cao-pr.pdf', 'Việt Nam', null, '2021-11-17', 0, 4, 1),
('25', 'Vượt Lên Những Chuyện Nhỏ Trong Công Việc', null, 'vuot-len-nhung-chuyen-nho-trong-cong-viec.jpg', 'vuot-len-nhung-chuyen-nho-trong-cong-viec.pdf', 'Việt Nam', null, '2021-11-17', 0, 1, 1),
('26', 'Kẻ Thành Công Phải Biết Lắng Nghe', null, 'ke-thanh-cong-phai-biet-lang-nghe.jpg', 'ke-thanh-cong-phai-biet-lang-nghe.pdf', null, null, '2021-11-17', 0, 4, 1),
('27', 'Nửa Kia Của Hitler', null, 'Nua-kia-cua-Hitler.jpg', 'Nua-kia-cua-Hitler.pdf', null, null, '2021-11-17', 0, 2, 1),
('28', 'Đứng Dậy Mạnh Mẽ', null, 'Dung-day-manh-me.png', 'Dung-day-manh-me.pdf', null, null, '2021-11-17', 0, 1, 1),
('29', 'Bí Quyết Làm Giàu Của Napoleon Hill', null, 'bi-quyet-lam-giau-cua-napoleon-hill.jpg', 'bi-quyet-lam-giau-cua-napoleon-hill.pdf', null, null, '2021-11-17', 0, 3, 1),
('30', 'Phụ Nữ 20 Thay Đổi Để Thành Công', null, 'phu-nu-20-thay-doi-de-thanh-cong.jpg', 'phu-nu-20-thay-doi-de-thanh-cong.pdf', null, null, '2021-11-17', 0, 1, 1),
('31', 'Hành Trình Biến Thương Hiệu Thành Biểu Tượng', null, 'Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.jpg', 'Hanh-trinh-bien-thuong-hieu-thanh-bieu-tuong.pdf', null, null, '2021-11-17', 0, 5, 1),
('32', 'Thuật Lãnh Đạo', null, 'thuat-lanh-dao.jpg', 'thuat-lanh-dao.pdf', null, null, '2021-11-17', 0, 1, 1),
('33', 'Thuật Đàm Phán', null, 'thuat-dam-phan.jpg', 'thuat-dam-phan.pdf', null, null, '2021-11-17', 0, 1, 1),
('34', 'Bạn Không Thể Đổi Hướng Gió, Nhưng Có Thể Điều Khiển Cánh Buồm', null, 'ban-khong-the-thay-doi-huong-gio.png', 'ban-khong-the-thay-doi-huong-gio.pdf', null, null, '2021-11-17', 0, 1, 1),
('35', 'Cho Tôi Xin Một Vé Đi Tuổi Thơ', null, 'cho-toi-xin-mot-ve-di-tuoi-tho.jpg', 'cho-toi-xin-mot-ve-di-tuoi-tho.pdf', 'Viêt Nam', null, '2021-11-17', 0, 2, 1),
('36', 'Cô Gái Đến Từ Hôm Qua', null, 'co-gai-den-tu-hom-qua.jpg', 'co-gai-den-tu-hom-qua.pdf', 'Việt Nam', null, '2021-11-17', 0,2, 1),
('37', 'Quốc Gia Khởi Nghiệp', null, 'quoc-gia-khoi-nghiep.jpg', 'quoc-gia-khoi-nghiep.pdf', null, null, '2021-11-17', 0, 5, 1);


SET STREAMING OFF;
