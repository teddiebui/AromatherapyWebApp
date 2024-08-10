# LAB_4_Aromatherapy

Project Name: Aromatherapy & massage.
Project introduction: This is a coursework project about Java Web Development.
It's a web app that offers articles about healthcare, courses & service offers in aromatherapy & massage.

Project Architecture & Design: 
 - N-tier layered architecture:
  + Presentation
  + Service Layer 
  + Data Access Layer
  + Database
 - Software Oriented architecture (SOA):
  + decompose architecture's into components that act as an Rest API services



Technologies & Tools:
 - Project build with Maven tool
 - Pure Servlet technology
 - JUnit 4
 - HikariCP datasource + JDBC connection
 - SQL with SQL Server 2022
 - Web UI: HTML/CSS with Bootstrap
 - Front end scripting language: Javascript vanilla


Preresquisites for running:
 - Please create your sample database in SQL Server
 - In web.xml please change some properties: sample database name, username, password
 - In SQL Server please run SQL Scripts to create tables and populate sample data:
 -   "create_database_and_tables.sql" file is located at \WEB-INF\resources\sql folder
 -   "sample_datasql.sql" file is located at the same folder


ChangeLog
-----------------------------------------------
v1.0
 - Introduce web app with 4 module:
   - PostService API allowing get, create, update post articles for websites
   - CourseService API allowing get, create, update new course offers to customers on website
   - Service API allowing get, create, update new service offers to customers on website
   - Employee API allowing get, create, update employees of the websites
   - 5 pages of Front end UI
