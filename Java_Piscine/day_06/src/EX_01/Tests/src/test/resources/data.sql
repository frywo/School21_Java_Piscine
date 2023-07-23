DELETE FROM products;
INSERT INTO products (name, price) VALUES ('Apl', 96);
INSERT INTO products (name, price) VALUES ('Mango', 250);
INSERT INTO products (name, price) VALUES ('Chicken', 200)
INSERT INTO products (name, price) VALUES ('Milk', 95);
INSERT INTO products (name, price) VALUES ('Cookies', 150);

DELETE FROM users;
INSERT INTO users (login, password, authenticationSuccessStatus) VALUES ('login123', 'password123', false);
INSERT INTO users (login, password, authenticationSuccessStatus) VALUES ('admin', 'admin123', true);
INSERT INTO users (login, password, authenticationSuccessStatus) VALUES ('namelogin@main.ru', 'my_password_null', false);