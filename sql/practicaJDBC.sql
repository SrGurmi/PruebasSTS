
DROP DATABASE IF EXISTS practicaJDBC;
CREATE DATABASE practicaJDBC;

USE practicaJDBC;

CREATE TABLE employees(
	employee_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	first_name VARCHAR(15) NOT NULL,
	last_name VARCHAR(25),
	email VARCHAR(25) NOT NULL,
	salary DECIMAL(6,2) DEFAULT 0,
	last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE projects(
	project_id INT UNSIGNED AUTO_INCREMENT PRIMARY KEY,
	name VARCHAR(15) NOT NULL,
	description VARCHAR(50),
	last_update TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE employee_project(
	employee_id INT UNSIGNED,
	project_id INT UNSIGNED,
	PRIMARY KEY (employee_id, project_id),
	INDEX (employee_id),
	INDEX (project_id),
	CONSTRAINT fk_employee
	FOREIGN KEY (employee_id) REFERENCES employees(employee_id) ON DELETE CASCADE,
	CONSTRAINT fk_project  
	FOREIGN KEY (project_id)  REFERENCES projects(project_id)   ON DELETE CASCADE
);
