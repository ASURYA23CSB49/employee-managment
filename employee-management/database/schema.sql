-- Employee Management System - MySQL Schema
-- Run this script to initialize the database

CREATE DATABASE IF NOT EXISTS employee_db
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;

USE employee_db;

CREATE TABLE IF NOT EXISTS employees (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    name         VARCHAR(100)   NOT NULL,
    email        VARCHAR(255)   NOT NULL UNIQUE,
    phone_number VARCHAR(15)    NOT NULL,
    department   VARCHAR(100)   NOT NULL,
    designation  VARCHAR(100)   NOT NULL,
    salary       DECIMAL(12, 2) NOT NULL CHECK (salary > 0),
    created_at   DATETIME       DEFAULT CURRENT_TIMESTAMP,
    updated_at   DATETIME       DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Sample Data
INSERT INTO employees (name, email, phone_number, department, designation, salary) VALUES
('Alice Johnson',    'alice.johnson@company.com',    '12345678901', 'Engineering', 'Senior Software Engineer', 95000.00),
('Bob Martinez',     'bob.martinez@company.com',     '12345678902', 'Engineering', 'DevOps Engineer',          82000.00),
('Carol White',      'carol.white@company.com',      '12345678903', 'HR',          'HR Manager',               70000.00),
('David Lee',        'david.lee@company.com',        '12345678904', 'Finance',     'Financial Analyst',        75000.00),
('Eva Brown',        'eva.brown@company.com',        '12345678905', 'Marketing',   'Marketing Lead',           68000.00),
('Frank Wilson',     'frank.wilson@company.com',     '12345678906', 'Sales',       'Sales Manager',            72000.00),
('Grace Kim',        'grace.kim@company.com',        '12345678907', 'Engineering', 'Frontend Developer',       80000.00),
('Henry Davis',      'henry.davis@company.com',      '12345678908', 'Operations',  'Operations Manager',       78000.00),
('Irene Taylor',     'irene.taylor@company.com',     '12345678909', 'Marketing',   'Content Strategist',       62000.00),
('James Anderson',   'james.anderson@company.com',   '12345678910', 'Engineering', 'Backend Developer',        85000.00);
