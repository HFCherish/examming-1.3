CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  role VARCHAR(255),
  password varchar(255)
);

insert into users (id, name, email, role, password) values ("001", "admin", "admin@example.com", "BACKGROUND_JOB", "$2a$04$DbgJbGA4dkQSzAvXvJcGBOv5kHuMBzrWfne3x3Cx4JQv4IJcxtBIW");


CREATE TABLE departments (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles (
  id VARCHAR(255) PRIMARY KEY,
  title VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employees (
  id VARCHAR(255) PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  department_id VARCHAR(255) NOT NULL,
  role_id VARCHAR(255) NOT NULL,
  gender VARCHAR(255) NOT NULL,
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);