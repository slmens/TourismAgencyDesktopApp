## INTRODUCTION
 This document provides the schema and sample data for four mysql tables: "hotels", "reservations","rooms","users".
 The provided SQL scripts allow for the creation of the database tables and insertion of sample data.
 
## Hotel Table
### Table Creation
> CREATE TABLE `hotels` (
`hotel_id` int NOT NULL AUTO_INCREMENT,
`hotel_city` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_district` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_adress` longtext COLLATE utf8mb4_general_ci NOT NULL,
`hotel_email` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_tel` varchar(30) COLLATE utf8mb4_general_ci NOT NULL,
`hotel_star` int NOT NULL,
`hotel_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`freePark` tinyint(1) NOT NULL,
`SPA` tinyint(1) NOT NULL,
`twentyForSevenService` tinyint(1) NOT NULL,
`freeWifi` tinyint(1) NOT NULL,
`swimmingPool` tinyint(1) NOT NULL,
`gym` tinyint(1) NOT NULL,
`concierge` tinyint(1) NOT NULL,
`ultraAllIncluded` tinyint(1) NOT NULL,
`allIncluded` tinyint(1) NOT NULL,
`roomBreakfast` tinyint(1) NOT NULL,
`fullType` tinyint(1) NOT NULL,
`halfType` tinyint(1) NOT NULL,
`onlyBed` tinyint(1) NOT NULL,
`fullCreditExceptAlcohol` tinyint(1) NOT NULL,
`kid_price_mult` double NOT NULL,
`ultra_all_inc_price_mult` double NOT NULL,
`all_inc_price_mult` double NOT NULL,
`room_break_price_mult` double NOT NULL,
`full_type_price_mult` double NOT NULL,
`half_type_price_mult` double NOT NULL,
`only_bed_price_mult` double NOT NULL,
`except_alc_price_mult` double NOT NULL,
`first_period_start_day` int NOT NULL,
`first_period_start_month` int NOT NULL,
`first_period_start_year` int NOT NULL,
`first_period_end_day` int NOT NULL,
`first_period_end_month` int NOT NULL,
`first_period_end_year` int NOT NULL,
`second_period_start_day` int NOT NULL,
`second_period_start_month` int NOT NULL,
`second_period_start_year` int NOT NULL,
`second_period_end_day` int NOT NULL,
`second_period_end_month` int NOT NULL,
`second_period_end_year` int NOT NULL,
PRIMARY KEY (`hotel_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

### Sample Data Insertion for Hotel Table
> INSERT INTO hotels (hotel_id,hotel_city,hotel_district,hotel_adress,hotel_email,hotel_tel,hotel_star,hotel_name,freePark,SPA,twentyForSevenService,freeWifi,swimmingPool,gym,concierge,ultraAllIncluded,allIncluded,roomBreakfast,fullType,halfType,onlyBed,fullCreditExceptAlcohol,kid_price_mult,ultra_all_inc_price_mult,all_inc_price_mult,room_break_price_mult,full_type_price_mult,half_type_price_mult,only_bed_price_mult,except_alc_price_mult,first_period_start_day,first_period_start_month,first_period_start_year,first_period_end_day,first_period_end_month,first_period_end_year,second_period_start_day,second_period_start_month,second_period_start_year,second_period_end_day,second_period_end_month,second_period_end_year) VALUES (1,"İstanbul","Bakırköy","Yeşilköy 34149 İskele Cd.","tanjanthotel@gmail.com","1111111111",5,"Tanjant Hotel",1,1,1,1,1,1,0,1,1,1,0,0,0,1,0.6,2,1.5,1.2,1,1,1,3,1,1,2023,30,6,2023,1,7,2023,31,12,2023),(2,"İstanbul","Beşiktaş","Akaretler 34890 Liman Cd.","alian@gmail.com","2222222222",4,"Alian Hotel",1,0,1,1,1,1,0,0,1,0,1,1,0,1,0.5,2,1.5,1.2,1,1,1,3,1,1,2023,31,5,2023,1,6,2023,31,12,2023),(3,"İstanbul","Bakırköy","Yeşilköy 34149 Candan Cd.","bismuth@gmail.com","3333333333",5,"Bismuth Hotel",1,1,0,1,1,1,1,1,1,1,0,0,0,1,0.5,2,1.5,1.2,1,1,1,3,1,1,2023,30,6,2023,1,7,2023,31,12,2023),(4,"İzmir","Çeşme","Boyalık 35930 İnönü mh.","curiosity@gmail.com","4444444444",5,"Curiosity Hotel",1,1,1,1,1,1,0,1,1,1,0,0,0,1,0.4,1.7,1.5,1.2,1,1,1,3,1,1,2023,30,4,2023,1,5,2023,31,12,2023),(5,"İzmir","Konak","Yeşilköy 35149 Çamlık Cd.","reflecthotel@gmail.com","5555555555",5,"Reflect Hotel",1,1,1,0,1,0,0,1,1,1,0,0,0,1,0.6,2,1.5,1.2,1,1,1,3,1,1,2023,30,6,2023,1,7,2023,31,12,2023)

---

## Reservations Table
### Table Creation
> CREATE TABLE `reservations` (
`reservation_id` int NOT NULL AUTO_INCREMENT,
`user_id` int NOT NULL,
`hotel_id` int NOT NULL,
`room_id` int NOT NULL,
`person_count` int NOT NULL,
`entrance_day` int NOT NULL,
`entrance_month` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
`entrance_year` int NOT NULL,
`exit_day` int NOT NULL,
`exit_month` varchar(20) COLLATE utf8mb4_general_ci NOT NULL,
`exit_year` int NOT NULL,
`social_security_number` varchar(40) COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`reservation_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

### Sample Data Insertion for Reservation Table
> INSERT INTO reservations (user_id,hotel_id,room_id,person_count,entrance_day,entrance_month,entrance_year,exit_day,exit_month,exit_year,social_security_number) VALUES (5,1,2,2,4,"Mayıs",2023,7,"Ağustos",2023,1111111111),(5,2,6,3,4,"Eylül",2023,7,"Kasım",2023,1111111111),(6,4,14,2,16,"Mart",2023,4,"Nisan",2023,222222222),(6,5,20,3,24,"Haziran",2023,4,"Ağustos",2023,22222222222),(5,3,10,2,13,"Ocak",2023,16,"Mayıs",2023,1111111111),(6,2,8,2,17,"Mayıs",2023,7,"Ağustos",2023,222222222)

---

## Rooms Table
### Table Creation
> CREATE TABLE `rooms` (
`room_id` int NOT NULL AUTO_INCREMENT,
`room_type` enum('Single','Double','Suit','King Suit') COLLATE utf8mb4_general_ci NOT NULL,
`stock_count` int NOT NULL,
`hotel_id` int NOT NULL,
`bed_count` int NOT NULL,
`has_tv` tinyint(1) NOT NULL DEFAULT '0',
`has_mini_bar` tinyint(1) NOT NULL DEFAULT '0',
`has_game_console` tinyint(1) NOT NULL DEFAULT '0',
`has_vault` tinyint(1) NOT NULL DEFAULT '0',
`has_projection` tinyint(1) NOT NULL DEFAULT '0',
`room_size_m` int NOT NULL,
`first_period_price` int NOT NULL,
`second_period_price` int NOT NULL,
PRIMARY KEY (`room_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

### Sample Data Insertion for Room Table
> INSERT INTO rooms (room_id,room_type,stock_count,hotel_id,bed_count,has_tv,has_mini_bar,has_game_console,has_vault,has_projection,room_size_m,first_period_price,second_period_price) VALUES (1,"Single",7,1,1,1,1,0,0,0,44,100,150),(2,"Double",5,1,3,1,1,1,0,0,60,150,200),(3,"Suit",4,1,3,1,1,1,1,0,44,200,250),(4,"King Suit",1,1,4,1,1,1,1,1,100,300,400),(5,"Single",7,2,1,1,1,0,0,0,44,100,150),(6,"Double",5,2,3,1,1,1,0,0,60,150,200),(7,"Suit",4,2,3,1,1,1,1,0,44,200,250),(8,"King Suit",1,2,4,1,1,1,1,1,100,300,400),(9,"Single",7,3,1,1,1,0,0,0,44,100,150),(10,"Double",5,3,3,1,1,1,0,0,60,150,200),(11,"Suit",4,3,3,1,1,1,1,0,44,200,250),(12,"King Suit",1,3,4,1,1,1,1,1,100,300,400),(13,"Single",7,4,1,1,1,0,0,0,44,100,150),(14,"Double",5,4,3,1,1,1,0,0,60,150,200),(15,"Suit",4,4,3,1,1,1,1,0,44,200,250),(16,"King Suit",1,4,4,1,1,1,1,1,100,300,400),(17,"Single",7,5,1,1,1,0,0,0,44,100,150),(18,"Double",5,5,3,1,1,1,0,0,60,150,200),(19,"Suit",4,5,3,1,1,1,1,0,44,200,250),(20,"King Suit",1,5,4,1,1,1,1,1,100,300,400)

---

## Users Table
### Table Creation
> CREATE TABLE `users` (
`user_id` int NOT NULL AUTO_INCREMENT,
`full_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`user_name` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`pass` varchar(255) COLLATE utf8mb4_general_ci NOT NULL,
`user_type` enum('manager','employee','customer') COLLATE utf8mb4_general_ci NOT NULL,
`user_tel` varchar(25) COLLATE utf8mb4_general_ci NOT NULL,
PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci

### Sample Data Insertion for Users Table
> INSERT INTO users (user_id,full_name,user_name,pass,user_type,user_tel) VALUES (1,"Yavuz Balat","yavza","1234","manager","1111111111"),(2,"Naz Tamar","naz","1234","manager","2222222222"),(3,"Bora Çamur","bora","1234","employee","3333333333"),(4,"Hakan Uyar","hakan","1234","employee","4444444444"),(5,"Damla Gök","damla","1234","customer","5555555555"),(6,"Süleyman Ekmekçi","sülo","1234","customer","6666666666")