-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: localhost
-- Üretim Zamanı: 09 Kas 2023, 11:20:37
-- Sunucu sürümü: 8.0.31
-- PHP Sürümü: 7.4.33

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `agency`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `hotels`
--

CREATE TABLE `hotels` (
  `hotel_id` int NOT NULL,
  `hotel_city` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_district` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_adress` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_tel` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `hotel_star` int NOT NULL,
  `hotel_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `second_period_end_year` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `hotels`
--

INSERT INTO `hotels` (`hotel_id`, `hotel_city`, `hotel_district`, `hotel_adress`, `hotel_email`, `hotel_tel`, `hotel_star`, `hotel_name`, `freePark`, `SPA`, `twentyForSevenService`, `freeWifi`, `swimmingPool`, `gym`, `concierge`, `ultraAllIncluded`, `allIncluded`, `roomBreakfast`, `fullType`, `halfType`, `onlyBed`, `fullCreditExceptAlcohol`, `kid_price_mult`, `ultra_all_inc_price_mult`, `all_inc_price_mult`, `room_break_price_mult`, `full_type_price_mult`, `half_type_price_mult`, `only_bed_price_mult`, `except_alc_price_mult`, `first_period_start_day`, `first_period_start_month`, `first_period_start_year`, `first_period_end_day`, `first_period_end_month`, `first_period_end_year`, `second_period_start_day`, `second_period_start_month`, `second_period_start_year`, `second_period_end_day`, `second_period_end_month`, `second_period_end_year`) VALUES
(1, 'İstanbul', 'Bakırköy', 'Yeşilköy 34149 İskele Cd.', 'tanjanthotel@gmail.com', '1111111111', 5, 'Tanjant Hotel', 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0.6, 2, 1.5, 1.2, 1, 1, 1, 3, 1, 1, 2023, 30, 6, 2023, 1, 7, 2023, 31, 12, 2023),
(2, 'İstanbul', 'Beşiktaş', 'Akaretler 34890 Liman Cd.', 'alian@gmail.com', '2222222222', 4, 'Alian Hotel', 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 1, 1, 0, 1, 0.5, 2, 1.5, 1.2, 1, 1, 1, 3, 1, 1, 2023, 31, 5, 2023, 1, 6, 2023, 31, 12, 2023),
(3, 'İstanbul', 'Bakırköy', 'Yeşilköy 34149 Candan Cd.', 'bismuth@gmail.com', '3333333333', 5, 'Bismuth Hotel', 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 1, 0.5, 2, 1.5, 1.2, 1, 1, 1, 3, 1, 1, 2023, 30, 6, 2023, 1, 7, 2023, 31, 12, 2023),
(4, 'İzmir', 'Çeşme', 'Boyalık 35930 İnönü mh.', 'curiosity@gmail.com', '4444444444', 5, 'Curiosity Hotel', 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 0, 0, 1, 0.4, 1.7, 1.5, 1.2, 1, 1, 1, 3, 1, 1, 2023, 30, 4, 2023, 1, 5, 2023, 31, 12, 2023),
(5, 'İzmir', 'Konak', 'Yeşilköy 35149 Çamlık Cd.', 'reflecthotel@gmail.com', '5555555555', 5, 'Reflect Hotel', 1, 1, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0.6, 2, 1.5, 1.2, 1, 1, 1, 3, 1, 1, 2023, 30, 6, 2023, 1, 7, 2023, 31, 12, 2023),
(14, 'test', 'test', 'test', 'test', 'test', 5, 'NEW', 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `reservations`
--

CREATE TABLE `reservations` (
  `reservation_id` int NOT NULL,
  `user_id` int NOT NULL,
  `hotel_id` int NOT NULL,
  `room_id` int NOT NULL,
  `person_count` int NOT NULL,
  `entrance_day` int NOT NULL,
  `entrance_month` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `entrance_year` int NOT NULL,
  `exit_day` int NOT NULL,
  `exit_month` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `exit_year` int NOT NULL,
  `social_security_number` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `rooms`
--

CREATE TABLE `rooms` (
  `room_id` int NOT NULL,
  `room_type` enum('Single','Double','Suit','King Suit') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
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
  `second_period_price` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `rooms`
--

INSERT INTO `rooms` (`room_id`, `room_type`, `stock_count`, `hotel_id`, `bed_count`, `has_tv`, `has_mini_bar`, `has_game_console`, `has_vault`, `has_projection`, `room_size_m`, `first_period_price`, `second_period_price`) VALUES
(1, 'Single', 7, 1, 1, 1, 1, 0, 0, 0, 44, 100, 150),
(2, 'Double', 6, 1, 3, 1, 1, 1, 0, 0, 60, 150, 200),
(3, 'Suit', 3, 1, 3, 1, 1, 1, 1, 0, 44, 200, 250),
(4, 'King Suit', 0, 1, 4, 1, 1, 1, 1, 1, 100, 300, 400),
(5, 'Single', 7, 2, 1, 1, 1, 0, 0, 0, 44, 100, 150),
(6, 'Double', 6, 2, 3, 1, 1, 1, 0, 0, 60, 150, 200),
(7, 'Suit', 4, 2, 3, 1, 1, 1, 1, 0, 44, 200, 250),
(8, 'King Suit', 2, 2, 4, 1, 1, 1, 1, 1, 100, 300, 400),
(9, 'Single', 7, 3, 1, 1, 1, 0, 0, 0, 44, 100, 150),
(10, 'Double', 5, 3, 3, 1, 1, 1, 0, 0, 60, 150, 200),
(11, 'Suit', 4, 3, 3, 1, 1, 1, 1, 0, 44, 200, 250),
(12, 'King Suit', 0, 3, 4, 1, 1, 1, 1, 1, 100, 300, 400),
(13, 'Single', 7, 4, 1, 1, 1, 0, 0, 0, 44, 100, 150),
(14, 'Double', 5, 4, 3, 1, 1, 1, 0, 0, 60, 150, 200),
(15, 'Suit', 4, 4, 3, 1, 1, 1, 1, 0, 44, 222, 250),
(16, 'King Suit', 1, 4, 4, 1, 1, 1, 1, 1, 100, 300, 400),
(17, 'Single', 7, 5, 1, 1, 1, 0, 0, 0, 44, 100, 150),
(18, 'Double', 5, 5, 3, 1, 1, 1, 0, 0, 60, 150, 200),
(19, 'Suit', 3, 5, 3, 1, 1, 1, 1, 0, 44, 200, 250),
(20, 'King Suit', 2, 5, 4, 1, 1, 1, 1, 1, 100, 300, 400);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `users`
--

CREATE TABLE `users` (
  `user_id` int NOT NULL,
  `full_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `pass` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_type` enum('manager','employee','customer') CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `user_tel` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Tablo döküm verisi `users`
--

INSERT INTO `users` (`user_id`, `full_name`, `user_name`, `pass`, `user_type`, `user_tel`) VALUES
(1, 'Yavuz Balat', 'yavza', '1234', 'manager', '1111111111'),
(2, 'Naz Tamar', 'naz', '1234', 'manager', '2222222222'),
(3, 'Bora Çamur', 'bora', '1234', 'employee', '3333333333'),
(4, 'Hakan Uyar', 'hakan', '1234', 'employee', '4444444444'),
(21, 'patika', 'testttttt', '1234', 'customer', '111111111'),
(22, 'haha', 'haha', '1234', 'customer', '1121132123');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `hotels`
--
ALTER TABLE `hotels`
  ADD PRIMARY KEY (`hotel_id`);

--
-- Tablo için indeksler `reservations`
--
ALTER TABLE `reservations`
  ADD PRIMARY KEY (`reservation_id`);

--
-- Tablo için indeksler `rooms`
--
ALTER TABLE `rooms`
  ADD PRIMARY KEY (`room_id`);

--
-- Tablo için indeksler `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `hotels`
--
ALTER TABLE `hotels`
  MODIFY `hotel_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- Tablo için AUTO_INCREMENT değeri `reservations`
--
ALTER TABLE `reservations`
  MODIFY `reservation_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Tablo için AUTO_INCREMENT değeri `rooms`
--
ALTER TABLE `rooms`
  MODIFY `room_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=33;

--
-- Tablo için AUTO_INCREMENT değeri `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
