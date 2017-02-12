# User Service

[![Codacy Badge](https://api.codacy.com/project/badge/Grade/f69d21092a374cc59555baad0a6e662d)](https://www.codacy.com/app/antonchar/User-Service?utm_source=github.com&utm_medium=referral&utm_content=antonchar/User-Service&utm_campaign=badger)
[![Build Status](https://travis-ci.org/antonchar/User-Service.svg?branch=master)](https://travis-ci.org/antonchar/User-Service)
[![Dependency Status](https://dependencyci.com/github/antonchar/User-Service/badge)](https://dependencyci.com/github/antonchar/User-Service)
[![codecov](https://codecov.io/gh/antonchar/User-Service/branch/master/graph/badge.svg)](https://codecov.io/gh/antonchar/User-Service)
[![License](http://img.shields.io/:license-apache-blue.svg)](http://www.apache.org/licenses/LICENSE-2.0.html)

This is a simple CRUD MVC project implemented with Spring Boot, MySQL and Thymeleaf technologies. 
The project is not finished and is under development. It is updated from time to time.

##Working Version

The application deployed at Heroku. You can try it out [HERE](https://user-service-antonchar.herokuapp.com).

> The free Heroku plan is used, so after an hour period of inactivity the application is frozen. 
It may take some time while the application is waking up.
If you have been redirected to this page from the Heroku link, please try to visit the url again.

> First of all the project is aimed to develop and enhance skills of writing Java code.
Therefore, there is less focus on the web part of the application (js, css, html).
**The project is tested using Google Chrome web browser. There is no any warranty of compatibility with other browsers.**

![screenshot](https://github.com/antonchar/User-Service/blob/master/app.png "App's Screenshot")

## List of used technologies

 Functionality | Technology / Library
--- | ---
Core Functionality | Java 8, Spring Boot, MVC, Spring MVC
Database | PostgreSQL, JPA, Hibernate, Spring Data JPA, Liquibase
Tests | JUnit, Hamcrest, Mockito, DataJpaTest, WebMvcTest
Security | TODO
Other | Lombok, i18n, Modelmapper
Frontend | Html, Css, JS, Bootstrap, Thymeleaf
Build Manager | Maven
CI and Code Quality | Travis-CI, Dependency-CI, Codacy, Codecov
Deployment | Heroku

##License

The code is under the [Apache Licence v2](https://www.apache.org/licenses/LICENSE-2.0.txt).