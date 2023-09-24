create database assignment;
use assignment;

create table Instructor(
	instructorCode int PRIMARY KEY,
    name varchar(20) not null,
    salary int not null,
    jobStartYear int not null);
    
create table InstructorInfo(
	recordId int PRIMARY KEY,
    instructorCode int references Instructor(instructorCode),
    address varchar(100),
    email varchar(30),
    phoneNumber varchar(10),
    firstLanguage varchar(20),
    secondLanguage varchar(20));
    
insert into Instructor values 
	(1001, 'Mahesh Pawar', 1500, 2010),
    (1002, 'Vinod Kaple', 2200, 2012),
    (1003, 'Rohan Gupta', 1700, 2008),
    (1004, 'Vaishnavi Khule', 2300, 2011),
    (1005, 'Shirish Kale', 4400, 2013),
    (1006, 'Ashish Vaidya', 2100, 2014),
    (1007, 'Suresh Chandra', 1900, 2009),
    (1008, 'Manish Gupta', 2200, 2015),
    (1009, 'Snehal Mahajan', 1400, 2016),
    (1010, 'Vivek Jiskar', 2300, 2017),
    (1011, 'Pawan Sonone', 1800, 2018),
    (1012, 'Shaunak Sakhare', 2100, 2019),
    (1013, 'Sham Wankhade', 4500, 2020),
    (1014, 'Sejal Kale', 2200, 2021),
    (1015, 'Juii Kaple', 1500, 2022);
    
select * from Instructor;

-- 1) Extract all instructors, salary greater than 2000

select * from Instructor where salary > 2000;

-- 2) Alter the instructor table and one more column TechnologyName varchar and InstructorType varchar
-- 			a. Instructor type can have two values Programming, Infra, Database, or Communication

alter table instructor
add column technologyName varchar(50),
add column technologyType varchar(20);

-- insert values for instructorType
update instructor 
set technologyType = 'Programming'
where instructorCode in (1001, 1005, 1012, 1015);

update instructor 
set technologyType = 'Infra'
where instructorCode in (1002, 1004, 1013, 1011);

update instructor 
set technologyType = 'Database'
where instructorCode in (1003, 1006, 1008, 1014);

update instructor 
set technologyType = 'Communication'
where instructorCode in (1007, 1009, 1010);

-- insert technologyName according to technologyType
update instructor
set technologyName = 'Java'
where technologyType = 'Programming' and instructorCode in (1001, 1005);

update instructor
set technologyName = 'Python'
where technologyType = 'Programming' and instructorCode in (1012, 1015);

-- insert technologyName according to technologyType
update instructor
set technologyName = 'Networking'
where technologyType = 'Infra' and instructorCode in (1002, 1011);

update instructor
set technologyName = 'Operating System'
where technologyType = 'Infra' and instructorCode in (1004, 1013);

-- insert technologyName according to technologyType
update instructor
set technologyName = 'SQL'
where technologyType = 'Database' and instructorCode in (1008, 1006);

update instructor
set technologyName = 'OracleDB'
where technologyType = 'Database' and instructorCode in (1014, 1003);

-- insert technologyName according to technologyType
update instructor
set technologyName = 'Soft Skills'
where technologyType = 'Communication' and instructorCode in (1009);

update instructor
set technologyName = 'Personality Development'
where technologyType = 'Communication' and instructorCode in (1010, 1007);

-- Extract all instructors based on technology type
select * from instructor where technologyType = 'Communication';

select * from instructor where technologyType = 'Programming';

select * from instructor where technologyType = 'Database';

select * from instructor where technologyType = 'Infra';


-- Increase the salary of all Instructors of Communication
update instructor
set salary = salary +1000
where technologyType = 'Communication';

--  How much total Salary paid to Programming Instructors?
select technologyType,sum(salary) as totalSalaryPaid from instructor
where technologyType = 'Programming';

-- Render Total Salary paid based on Instructor Type.
select technologyType,sum(salary) as totalSalaryPaid from instructor
group by technologyType;

-- Sort Instructor based on name.
select * from instructor order by name asc;

-- insert values in InstructorInfo Table 
-- Insert values into InstructorInfo table
insert into InstructorInfo (recordId, address, email, phoneNumber, firstLanguage, secondLanguage)
values
    (1, 'Bangalore', 'mahesh@outlook.com', '1234567890', 'English', 'Hindi'),
    (2, 'Mumbai', 'vinod@cap.com', '9876543210', 'English', 'Marathi'),
    (3, 'Bangalore', 'rohan@gmail.com', '3456789012', 'English', 'Hindi'),
    (4, 'Pune', 'vaishnavi@outlook.com', '2345678901', 'English', 'Marathi'),
    (5, 'Bangalore', 'shirish@yahoo.com', '8765432109', 'Spanish', 'English'),
    (6, 'Mumbai', 'ashish@yahoo.com', '6543210987', 'English', 'Marathi'),
    (7, 'Pune', 'suresh@cap.com', '7654321098', 'English', 'Hindi'),
    (8, 'Bangalore', 'manish@acc.com', '5432109876', 'English', 'Hindi'),
    (9, 'Mumbai', 'snehal@gmail.com', '4321098765', 'Spanish', 'English'),
    (10, 'Pune', 'vivek@acc.com', '3210987654', 'English', 'Hindi'),
    (11, 'Bangalore', 'pawan@outlook.com', '2109876543', 'English', 'Kannada'),
    (12, 'Mumbai', 'shaunak@cogni.com', '1098765432', 'Spanish', 'English'),
    (13, 'Bangalore', 'sham@gmail.com', '0987654321', 'English', 'Kannada'),
    (14, 'Mumbai', 'sejal@cogni.com', '9876543210', 'English', 'Marathi'),
    (15, 'Pune', 'juii@gmail.com', '8765432109', 'Spanish', 'English');
    
select * from instructorInfo;

-- Extract all Instructors from address “Bangalore”
select * from instructorInfo where address = 'Bangalore';

-- Extract all Instructor which contain “Java in technology Name"
select * from instructor where technologyName like "%Java%";

-- Get the name , code of All Instructors who has first language as “Spanish".
select i.instructorCode, i.name from instructor i
inner join instructorInfo t on i.instructorCode = t.instructorCode
where t.firstLanguage = 'Spanish';

-- Count an Instructor who can speak “Spanish"
select count(*) as Count from instructorInfo
where firstLanguage = 'Spanish' or secondLanguage = 'Spanish';

-- Update the Salary of All instructors who can speak “Spanish"
update instructor set salary = salary + 1000
where instructorCode in (
	select instructorCode from instructorInfo
    where firstLanguage = 'Spanish');

-- Display InstructorCode, JobStartYear,email,phone of Instructors who has first language as “Spanish”
select t.instructorCode, i.jobStartYear, t.email, t.phoneNumber
from instructorInfo t
inner join instructor i on t.instructorCode = i.instructorCode
where t.firstLanguage = 'Spanish';


--  SQL Query to drop column Second Language

alter table instructorInfo
drop column secondLanguage;