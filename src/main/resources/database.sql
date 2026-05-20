CREATE TABLE meals (
    id              INT NOT NULL AUTO_INCREMENT,
    meal_name      VARCHAR(255) NOT NULL,
    calories int NOT NULL,
    meal_description  text,
    CONSTRAINT pk_meals PRIMARY KEY (id)
) ENGINE=INNODB;


CREATE TABLE items (
    id              INT NOT NULL AUTO_INCREMENT,
    item_name      VARCHAR(255) NOT NULL,
    calories INT NOT NULL,
    CONSTRAINT pk_items PRIMARY KEY (id)
) ENGINE=INNODB;


CREATE TABLE meal_items (
    id              INT NOT NULL AUTO_INCREMENT,
    id_item      int NOT NULL,
    id_meal INT NOT NULL,
    CONSTRAINT pk_meal_items PRIMARY KEY (id),
    CONSTRAINT fk_item_id
        FOREIGN KEY (id_item) REFERENCES `items` (id),
    CONSTRAINT fk_meal_id
	FOREIGN KEY (id_meal) REFERENCES `meals` (id)
) ENGINE=INNODB;

CREATE TABLE users (
    id              INT NOT NULL AUTO_INCREMENT,
    username      VARCHAR(255) NOT NULL,
    user_password TEXT NOT NULL,
    user_role varchar(255) NOT NULL,
     CONSTRAINT pk_users PRIMARY KEY (id)
) ENGINE=INNODB;


alter table users ADD refresh_token TEXT;