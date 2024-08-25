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
**v1.1 - Added AuthenticationService**
- Features:
    - JWT Authentication (refresh & access token)
    - One-session at a time authentication
    - Preventing same device access login endpoint
    - Logging out user by invalidating JWT stored in database
 - Endpoint:
 - `/authenticationService/authen/login`
   - Do the authentication by sending body containing `username` & `password` in POST request.
   - Only one session at a time authentication, mean no support for multiple device logging in.
   - Allowed methods: POST.
   - Possible returning HTTP CODE: `200`, `400`, `401`, `403`, `405`, `500`.
 - `/authenticationService/authen/logout`
   - Will log out user and invalidate current JWT token.
   - Allowed method: GET.
   - Possible returning HTTP CODE: `200`, `400`, `401`, `403`, `405`, `500`.
 - `/authenticationService/authen/access-token`
   - Will return a new access-token ONLY for anthenticated user if their JWT not expired/ invalidated.
   - Allowed method: GET.
   - Possible returning HTTP CODE: `200`, `400`, `401`, `403`, `405`, `500`.
-----------------------------------------------
v1.0
 - Introduce web app with 4 module:
   - PostService API allowing get, create, update post articles for websites
   - CourseService API allowing get, create, update new course offers to customers on website
   - Service API allowing get, create, update new service offers to customers on website
   - Employee API allowing get, create, update employees of the websites
   - 5 pages of Front end UI
