1. Описать объектную модель БД. При описании entity классов использовать аннотации валидации
2. Согласно задуманному функционалу реализовать:
- POST запрос(ы)
- PUT запрос(ы)
- GET запрос(ы) с параметрами  и без
- DELETE запрос не реализовываем
3. При реализации бизнес логики необходимо выделять слои: 
- репозитории
- сервисы
- контроллеры
4. Реализовать возможность загрузки файла на сервер
5. Реализовать возможность управлять ролями и доступами через Spring Security
6. При необходимости отправлять запросы на сторонние сервисы использоать 
OpenFeign, RestTemplate или WebClient (в случае работы со spring webflux)
7. Ошибки, которые возвращаются клиенту должны формироваться единообразно
8. Логирование:
- успешное выполнение запроса необходимо логировать в контроллерах (log.info)
- все исключения необходимо логировать в блоках catch (log.error)
9. Покрытие юнит тестами - не менее 30%

GET /course/12

POST: {course_id: 12, name: JJD}

PUT: {course_id: 12, enable: false}

PATCH: /course/12
       {enable: false}

DELETE: X