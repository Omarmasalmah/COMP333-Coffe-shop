drop database sinjel_cafe;
create database sinjel_cafe;
use sinjel_cafe;

CREATE TABLE customers (
    customer_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    customer_name VARCHAR(255),
    phone VARCHAR(255)
);

CREATE TABLE employee (
    emp_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    employee_name VARCHAR(60),
    birthday DATE,
    emp_phone VARCHAR(255), 
    emp_password VARCHAR(30),
    date_of_employment DATE,
    amount_paid REAL
);

CREATE TABLE orders (
    order_id INT PRIMARY KEY NOT NULL AUTO_INCREMENT,
    order_date DATE,
    emp_id INT,
     customer_id INT,
	FOREIGN KEY (customer_id) REFERENCES customers (customer_id) ON DELETE CASCADE ON UPDATE CASCADE,
     FOREIGN KEY (emp_id) REFERENCES employee (emp_id) ON DELETE CASCADE ON UPDATE CASCADE
);
CREATE TABLE categores (
    cat_id INT PRIMARY KEY NOT NULL,
    categores_name VARCHAR(60),
    number_of_item INT 
);

CREATE TABLE items (
    item_id INT,
    item_name VARCHAR(30),
	sale_price REAL,
    origen_price REAL,
    quantity INT,
    discription VARCHAR(70),
    size VARCHAR(2),
     cat_id INT,
     PRIMARY KEY (item_id),
    FOREIGN KEY (cat_id) REFERENCES categores(cat_id)
    
    );

CREATE TABLE employee_item (
    emp_id INT,
    item_id INT,
	cat_id INT,
    PRIMARY KEY (emp_id, item_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)

);

CREATE TABLE employee_order (
    emp_id INT,
    order_id INT,
    PRIMARY KEY (emp_id, order_id),
    FOREIGN KEY (emp_id) REFERENCES employee(emp_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE customer_order (
    customer_id INT,
    order_id INT,
    PRIMARY KEY (customer_id, order_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id)
);

CREATE TABLE order_item (
    order_id INT,
    item_id INT,
    PRIMARY KEY (order_id, item_id),
    FOREIGN KEY (order_id) REFERENCES orders(order_id),
    FOREIGN KEY (item_id) REFERENCES items(item_id)
);

CREATE TABLE feedback (
    customer_id INT,
    feedback_number INT auto_increment,
    feedback_rating INT,

    PRIMARY KEY (feedback_number,customer_id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

CREATE TABLE customer_feedback (
    customer_id INT,
    feedback_number INT,
    PRIMARY KEY (customer_id, feedback_number),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
    FOREIGN KEY (feedback_number) REFERENCES feedback(feedback_number)
);

CREATE TABLE invoice (
    order_id INT, 
	order_date Date,
    item_id INT NOT NULL,
    quantity INT,
    sale_price REAL,
    original_price REAL,
    profits REAL,
   
    PRIMARY KEY (item_id , order_id),
    FOREIGN KEY (order_id)
        REFERENCES orders (order_id)
        ON DELETE NO ACTION ON UPDATE CASCADE,
    FOREIGN KEY (item_id)
        REFERENCES items (item_id)
        ON DELETE NO ACTION ON UPDATE CASCADE
);

insert into employee (employee_name,birthday,emp_phone,date_of_employment,emp_password) value ("Khalid",'2000-08-2','0592501178','2020-03-15',"admin");
insert into customers(customer_id,customer_name,phone) value (3,"yazeed",'09548799879');
insert into employee (employee_name,birthday,emp_phone,date_of_employment,emp_password,amount_paid) value ("mahmoud",'2020-08-2','0592501166','2021-03-15',"123456",22.5);



 show tables; 
      select * from customer_feedback;
      select * from customer_order;
	  select * from customers;
      select * from categores;
      select * from employee;
      select * from items;
	  select * from employee_item;
	  select * from employee_order;
	  select * from feedback;
      select * from orders;
	  select * from order_item;
	  select * from invoice;
      select avg(feedback_rating) from feedback;