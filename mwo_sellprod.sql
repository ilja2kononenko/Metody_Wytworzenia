-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1
-- Время создания: Дек 06 2021 г., 21:05
-- Версия сервера: 10.4.21-MariaDB
-- Версия PHP: 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `mwo_sellprod`
--

-- --------------------------------------------------------

--
-- Структура таблицы `backend_users`
--

CREATE TABLE `backend_users` (
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `backend_users`
--

INSERT INTO `backend_users` (`login`, `password`) VALUES
('admin', 'admin');

-- --------------------------------------------------------

--
-- Структура таблицы `ordergroup`
--

CREATE TABLE `ordergroup` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `creation_date` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `ordergroup`
--

INSERT INTO `ordergroup` (`id`, `user_id`, `creation_date`) VALUES
(2, 5, '2021-11-19 00:20:23'),
(3, 5, '2021-11-19 00:20:46'),
(4, 5, '2021-11-19 00:22:04'),
(5, 10, '2021-11-19 00:37:16'),
(6, 6, '2021-11-19 01:14:39'),
(7, 6, '2021-11-19 18:21:13'),
(8, 5, '2021-12-06 18:49:22'),
(9, 5, '2021-12-06 19:44:08');

-- --------------------------------------------------------

--
-- Структура таблицы `order_item`
--

CREATE TABLE `order_item` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `ordergroup_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `order_item`
--

INSERT INTO `order_item` (`id`, `item_id`, `ordergroup_id`) VALUES
(1, 3, 2),
(2, 3, 3),
(3, 1, 3),
(4, 2, 3),
(5, 6, 4),
(6, 2, 5),
(7, 3, 6),
(8, 3, 7),
(9, 2, 7),
(10, 6, 7),
(11, 2, 8),
(12, 3, 9);

-- --------------------------------------------------------

--
-- Структура таблицы `products`
--

CREATE TABLE `products` (
  `id` int(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `description` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `products`
--

INSERT INTO `products` (`id`, `title`, `price`, `description`) VALUES
(1, 'iphone 1', 1999, 'iphone description'),
(2, 'iphone 2', 2099, 'iphone description'),
(3, 'iphone 3', 2199, 'iphone description'),
(4, 'iphone 4', 2299, 'iphone description'),
(5, 'iphone 5', 2399, 'iphone description'),
(6, 'iphone 6', 2499, 'iphone description'),
(7, 'iphone 7', 2599, 'iphone description'),
(8, 'xiaomi 1', 899, 'iphone description'),
(9, 'xiaomi redmi note 2', 999, 'iphone description'),
(10, 'xiaomi 2', 849, 'iphone description'),
(11, 'xiaomi 3', 949, 'iphone description'),
(12, 'xiaomi 4', 799, 'iphone description'),
(13, 'Samsung A30', 749, 'iphone description'),
(14, 'Samsung A60', 1099, 'iphone description'),
(15, 'Samsung A30+', 1049, 'iphone description');

-- --------------------------------------------------------

--
-- Структура таблицы `users`
--

CREATE TABLE `users` (
  `id` int(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `surname` varchar(255) DEFAULT NULL,
  `money` int(255) DEFAULT 1000,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Дамп данных таблицы `users`
--

INSERT INTO `users` (`id`, `name`, `surname`, `money`, `email`, `password`) VALUES
(2, 'Illia', 'Kononenko', 1900, 'test@test.com', 'test123'),
(3, 'Vadim', 'Lyss', 1000, 'test1111@test.com', 'test123'),
(4, 'Jan', 'Kowalski', 1000, 'test123@test.com', 'test123'),
(5, 'test', 'test', 1000, 'test', 'test'),
(6, 'test1', 'test1', 1000, 'test1', 'test1'),
(7, 'test1', 'test1', 1000, 'test1', 'test1'),
(8, 'test1', 'test1', 1000, 'test1', 'test1'),
(9, 'test1', 'test1', 1000, 'test1', 'test1'),
(10, 'test1', 'test1', 1000, 'test1', 'test1');

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `ordergroup`
--
ALTER TABLE `ordergroup`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `order_item`
--
ALTER TABLE `order_item`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `ordergroup`
--
ALTER TABLE `ordergroup`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT для таблицы `order_item`
--
ALTER TABLE `order_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT для таблицы `products`
--
ALTER TABLE `products`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;

--
-- AUTO_INCREMENT для таблицы `users`
--
ALTER TABLE `users`
  MODIFY `id` int(255) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
