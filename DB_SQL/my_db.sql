Drop database myErp;
Create database myErp;
use myErp;

Create table users (
	username Varchar(50) NOT NULL DEFAULT "",
	password Varchar(50) NOT NULL DEFAULT "",
	PRIMARY KEY (username)
);

Create table address (
	address_id int NOT NULL AUTO_INCREMENT,
	address Varchar(200) NOT NULL DEFAULT "",
	PRIMARY KEY (address_id)
);

Create table job_status (
	job_status_id int NOT NULL AUTO_INCREMENT,
	job_status_text Varchar(100) NOT NULL,
	PRIMARY KEY (job_status_id)
);

Create table employee (
	employee_id	int NOT NULL,
	name Varchar(200) NOT NULL,
	job_status_id int NOT NULL,
	dob Date NOT NULL,
	email Varchar(100) NOT NULL,
	username Varchar(50) NOT NULL,
	address_id int,
	salary_per_hour int NOT NULL,
	PRIMARY KEY (employee_id),
	FOREIGN KEY (username) REFERENCES myErp.users(username),
	FOREIGN KEY (address_id) REFERENCES myErp.address (address_id),
	FOREIGN KEY (job_status_id) REFERENCES myErp.job_status(job_status_id)
);

Create table employee_resume (
	resume_id int NOT NULL,
	employee_id int NOT NULL,
	resume_date Date,
	resume_text varchar(3000) NOT NULL,
	PRIMARY KEY (resume_id),
	FOREIGN KEY (employee_id) REFERENCES myErp.employee(employee_id)
);
	
Create table skills (
	skill_id int NOT NULL,
	skill_name Varchar(50) NOT NULL,
	PRIMARY KEY (skill_id)
);
	
Create table employee_skills (
	skill_id int NOT NULL,
	employee_id int NOT NULL,
	years_experience int,
	FOREIGN KEY (skill_id) REFERENCES myErp.skills (skill_id),
	FOREIGN KEY (employee_id) REFERENCES myErp.employee (employee_id),
	PRIMARY KEY (skill_id, employee_id)
);

Create table employee_performance (
	employee_performance_id int NOT NULL,
	employee_id int NOT NULL,
	rating_year int NOT NULL,
	rating_scale_ten int NOT NULL,
	PRIMARY KEY (employee_performance_id),
	FOREIGN KEY (employee_id) REFERENCES myErp.employee (employee_id)
);

Create table project (
	project_id int NOT NULL,
	start_date DATE NOT NULL,
	end_date DATE NOT NULL,
	PRIMARY KEY (project_id)
);

Create table project_budget (
	project_budget_id int NOT NULL,
	project_id int NOT NULL,
	project_budget_amt int NOT NULL,
	PRIMARY KEY (project_budget_id),
	FOREIGN KEY (project_id) REFERENCES myErp.project_budget(project_budget_id)
);

Create table project_employees (
	project_id int NOT NULL,
	employee_id int NOT NULL,
	PRIMARY KEY (project_id, employee_id),
	FOREIGN KEY (employee_id) REFERENCES myErp.employee (employee_id),
	FOREIGN KEY (project_id) REFERENCES myErp.project_budget(project_budget_id)
);

Create table project_vacancy (
	project_vacancy_id int NOT NULL,
	project_id int NOT NULL,
	job_status_id int NOT NULL,
	est_start_date DATE NOT NULL,
	est_end_date DATE NOT NULL,
	PRIMARY KEY (project_vacancy_id),
	FOREIGN KEY (job_status_id) REFERENCES myErp.job_status(job_status_id),
	FOREIGN KEY (project_id) REFERENCES myErp.project(project_id)
);

Create table project_vacancy_budget (
	project_vacancy_budget_id int NOT NULL,
	project_vacancy_id int NOT NULL,
	project_vacancy_budget_amt int NOT NULL,
	PRIMARY KEY (project_vacancy_budget_id),
	FOREIGN KEY (project_vacancy_id) REFERENCES myErp.project_vacancy(project_vacancy_id)
);

Create table project_vacancy_skills (
	project_vacancy_id int NOT NULL,
	skill_id int NOT NULL,
	years_experience int,
	PRIMARY KEY (project_vacancy_id, skill_id),
	FOREIGN KEY (skill_id) REFERENCES myErp.skills (skill_id),
	FOREIGN KEY (project_vacancy_id) REFERENCES myErp.project_vacancy (project_vacancy_id)
);