### curl samples (application deployed in application context `graduation`).
> For windows use `Git Bash`

#### get All Users
`curl -s http://localhost:8080/graduation/rest/admin/users --user admin@gmail.com:admin`

#### get Users 100001
`curl -s http://localhost:8080/graduation/rest/admin/users/100001 --user admin@gmail.com:admin`

#### register Users
`curl -s -i -X POST -d '{"name":"New User","email":"test@mail.ru","password":"test-password"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/profile/register`

#### get Profile
`curl -s http://localhost:8080/graduation/rest/profile --user user1@yandex.ru:password1`

#### update Profile
`curl -s -X PUT -d '{"name":"User11Upd","email":"user1@yandex.ru","password":"password1"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/profile --user user1@yandex.ru:password1`

#### delete Profile
`curl -s -X DELETE http://localhost:8080/graduation/rest/profile --user test@mail.ru:test-password`

#### get All Restaurants
`curl -s http://localhost:8080/graduation/rest/restaurants --user user1@yandex.ru:password1`

#### create Restaurants
`curl -s -X POST -d '{"name":"rest 4"}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/restaurants --user admin@gmail.com:admin`

#### get Restaurants 100007
`curl -s http://localhost:8080/graduation/rest/restaurants/100007  --user admin@gmail.com:admin`

#### get Restaurants with menu 100007
`curl -s http://localhost:8080/graduation/rest/restaurants/100006/fullData  --user admin@gmail.com:admin`

#### delete Restaurants 100008
`curl -s -X DELETE http://localhost:8080/graduation/rest/restaurants/100008 --user admin@gmail.com:admin`

#### get All today Menus in Restaurant 100006 
`curl -s http://localhost:8080/graduation/rest/restaurant/100006/menus --user user1@yandex.ru:password1`

#### create Menus in Restaurant 100006 
`curl -s -X POST -d '{"dish":"dish 1","price":1000}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/restaurant/100006/menus --user admin@gmail.com:admin`

#### update Menus 100013 in Restaurant 100007 
`curl -s -X PUT -d '{"date":"2020-05-01","dish":"dish 6","price":1000}' -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/restaurant/100007/menus/100013 --user admin@gmail.com:admin`

#### get Menus 100013 in Restaurant 100007  
`curl -s http://localhost:8080/graduation/rest/restaurant/100007/menus/100013 --user admin@gmail.com:admin`

#### delete Menus 100013
`curl -s -X DELETE http://localhost:8080/graduation/rest/restaurant/100007/menus/100013 --user admin@gmail.com:admin`

#### get Votes
`curl -s http://localhost:8080/graduation/rest/votes --user user2@yandex.ru:password2`

#### get today Votes
`curl -s http://localhost:8080/graduation/rest/votes/today --user user2@yandex.ru:password2`

#### get Votes not found
`curl -s http://localhost:8080/graduation/rest/votes/100028 --user user2@yandex.ru:password2`

#### delete Votes
`curl -s -X DELETE http://localhost:8080/graduation/rest/votes/100028 --user user3@yandex.ru:password3`

#### create Restaurant 100006 Votes 
`curl -s -X POST -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/votes/restaurant/100006 --user admin@gmail.com:admin`

#### update Restaurant 100006 Votes 100027
`curl -s -X PUT -H 'Content-Type:application/json;charset=UTF-8' http://localhost:8080/graduation/rest/votes/100027/restaurant/100006 --user user2@yandex.ru:password2`

