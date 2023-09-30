DROP TABLE IF EXISTS user_account CASCADE;
DROP TABLE IF EXISTS books CASCADE;
DROP TABLE IF EXISTS transactions CASCADE;
DROP TABLE IF EXISTS history CASCADE;

CREATE TABLE user_account(
                             id serial PRIMARY KEY,
                             email varchar(255),
                             pass varchar(255),
                             name varchar(100),
                             surname varchar(100),
                             phone_number varchar(15),
                             city varchar(50),
                             street_address varchar(100),
                             postal_code varchar(10),
                             user_admin boolean DEFAULT 'f'
);

CREATE TABLE books(
                      id serial PRIMARY KEY,
                      title varchar(150),
                      author varchar(100),
                      secondary_authors varchar(200) DEFAULT 'none',
                      publishing_date varchar(20),
                      current_owner integer,
                      cover_art varchar(255),
                      CONSTRAINT "FK_books.currentOwner"
                          FOREIGN KEY (current_owner)
                              REFERENCES user_account(id)
);

CREATE TABLE transactions(
                             id serial PRIMARY KEY,
                             book_id integer,
                             transaction_date Date,
                             book_received Date,
                             comments varchar(255),
                             CONSTRAINT "FK_transactions.bookId"
                                 FOREIGN KEY (book_id)
                                     REFERENCES books(id)
);

CREATE TABLE history(
                        id serial PRIMARY KEY,
                        id_user integer,
                        id_transaction integer,
                        book_received boolean,
                        CONSTRAINT "FK_history.idTransaction"
                            FOREIGN KEY (id_transaction)
                                REFERENCES transactions(id),
                        CONSTRAINT "FK_history.idUser"
                            FOREIGN KEY (id_user)
                                REFERENCES user_account(id)
);

CREATE TABLE users_roles(

)