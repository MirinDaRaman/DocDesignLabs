DROP DATABASE IF EXISTS Workday;
create database Workday;
use Workday;
CREATE TABLE salary_plan
(
  id int NOT NULL AUTO_INCREMENT ,
  name nvarchar(50) NULL,
  description nvarchar(50) NULL,
  salary int NULL,
  Primary key (id)
);


CREATE TABLE workers
(
  id int NOT NULL AUTO_INCREMENT ,
  surname nvarchar(50) NULL,
  name nvarchar(50) NULL,
  unit_salary nvarchar(50) NULL,
  salary_id int NULL,
  Primary key (id),
  CONSTRAINT FK_workers_salaries FOREIGN KEY (salary_id)
  REFERENCES salary_plan (id)
);


CREATE TABLE commision_plans
(
  id int NOT NULL AUTO_INCREMENT ,
  commision_plan nvarchar(50) NOT NULL,
  worker_id int NOT NULL,
  Primary key (id)
);


CREATE TABLE worker_commision_plans
(
  id int NOT NULL AUTO_INCREMENT ,
  worker_id int NOT NULL,
  commision_plan_id int NOT NULL,
  CONSTRAINT FK_candidates FOREIGN KEY (worker_id)
  REFERENCES workers(id),
  CONSTRAINT FK_interview_results FOREIGN KEY (commision_plan_id)
  REFERENCES commision_plans (id),
  Primary key (id)
);