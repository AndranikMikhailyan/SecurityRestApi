[![Build Status](https://travis-ci.org/AndranikMikhailyan/SecurityRestApi.svg?branch=master)](https://travis-ci.org/AndranikMikhailyan/SecurityRestApi)


Задание

Реализовать приложение (REST API), которое позволяет хранить данные о разработчиках.
Разработчик должен содержать данные о навыках, аккаунте (анкета), имя, фамилия, ЗП, дата рождения, дата приема на работу
Необходимо реализовать разграничение доступа:
- Администратор (ROLE_ADMIN): полный доступ к приложению (управление разработчиками, навыками, аккаунтами и пользователями)
- Модератор (ROLE_MODERATOR): полное управление разработчиками, навыками, аккаунтами
- Пользователь (ROLE_USER): только чтение данных о разработчиках, навыках и аккаунтах

Требования:

    Приложение должно быть развернуто на heroku (https://www.heroku.com/)

    В github репозитории должен отображатся статус сборки (travis CI - https://travis-ci.org/)

    Необходимо реализовать регистрацию и аутентификацию пользователей.
    
    При регистрации - роль по умолчанию ROLE_USER
    
    Необходимо реализовать подтверждение регистрации по номеру телефона (twilio - https://www.twilio.com/)
    
    Необходимо реализовать два окружения запуска - стандартное и dev
    
    (application.properties / application-dev.properties)


Технологии: Java, MySQL, Spring (MVC, Web, Data, Security, Boot), Lombok, Maven, Liquibase.

Результатом выполнения приложения должно быть приложение, развернутое на heroku.
