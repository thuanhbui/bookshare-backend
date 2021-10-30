DROP TABLE IF EXISTS admin;
CREATE TABLE admin(
    admin_id INT(15) PRIMARY KEY, 
    username VARCHAR(32), 
    password VARCHAR(32), 
    registered_date DATE
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=admin, VALUE_TYPE=demo.model.Admin";

DROP TABLE IF EXISTS catalog;
CREATE TABLE catalog(
    catalog_id INT(15), 
    name_catalog VARCHAR(128), 
    admin_id INT(15), 
    PRIMARY KEY (catalog_id, admin_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, AFFINITY_KEY=admin_id, CACHE_NAME=catalog, KEY_TYPE=demo.model.CatalogKey, VALUE_TYPE=demo.model.Catalog";

DROP TABLE IF EXISTS user;
CREATE TABLE user(
    user_id INT(15), 
    username VARCHAR(32), 
    password VARCHAR(32), 
    email VARCHAR(50), 
    phone VARCHAR(15), 
    address VARCHAR(128), 
    registered_date DATE, 
    admin_id INT(15), 
    PRIMARY KEY (user_id, admin_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, AFFINITY_KEY=admin_id, CACHE_NAME=user, KEY_TYPE=demo.model.UserKey, VALUE_TYPE=demo.model.User";

DROP TABLE IF EXISTS book;
CREATE TABLE book(
    book_id INT(15), 
    title VARCHAR(128), 
    description VARCHAR(1000), 
    image_link VARCHAR(50), 
    file_link VARCHAR(50), 
    language VARCHAR(50), 
    release_year YEAR,
    last_update date,
    viewers INT(11),
    catalog_id INT(15),
    user_id INT(15),
    PRIMARY KEY (book_id, catalog_id, user_id)
)WITH "TEMPLATE=partitioned, BACKUPS=1, AFFINITY_KEY=user_id, CACHE_NAME=book, KEY_TYPE=demo.model.UserKey, VALUE_TYPE=demo.model.User";

DROP TABLE if EXISTS bookshelf;
CREATE TABLE bookshelf(
    user_id INT(15) PRIMARY KEY,
    book_id INT(15)
) WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=bookshelf, VALUE_TYPE=demo.model.Bookshelf";

DROP TABLE if EXISTS histories;
CREATE TABLE histories (
    user_id INT(15) PRIMARY KEY,
    book_id INT(15),
)WITH "TEMPLATE=partitioned, BACKUPS=1, CACHE_NAME=histories, VALUE_TYPE=demo.model.Histories";
