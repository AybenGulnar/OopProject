-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 07 Oca 2022, 10:14:21
-- Sunucu sürümü: 10.4.22-MariaDB
-- PHP Sürümü: 8.0.13

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `denemeoop`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `accountapplication`
--

CREATE TABLE `accountapplication` (
  `id` int(11) NOT NULL,
  `applicationNo` varchar(150) NOT NULL,
  `applicant` varchar(150) NOT NULL,
  `applicationStatus` varchar(50) NOT NULL DEFAULT 'Beklemede',
  `applicationResult` int(11) DEFAULT 0,
  `chargeEmployee` varchar(150) DEFAULT NULL,
  `accountNo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `accountapplication`
--

INSERT INTO `accountapplication` (`id`, `applicationNo`, `applicant`, `applicationStatus`, `applicationResult`, `chargeEmployee`, `accountNo`) VALUES
(1, '111', '1111', 'onaylandı', 1, 'deniz', '1112'),
(2, '222', '2222', 'Confirmed', 0, 'ayse', '1134'),
(10, '560', '6667', 'Confirmed', 0, 'deniz', '6668'),
(11, '561', '6667', 'Waiting', 0, NULL, '6668'),
(12, '562', '6667', 'Waiting', 0, NULL, '6668'),
(15, '223036629', '6667', 'Confirmed', 0, 'deniz', '648493316'),
(16, '278502063', '6667', 'Waiting', 0, NULL, '943649477');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `cardapplication`
--

CREATE TABLE `cardapplication` (
  `id` int(11) NOT NULL,
  `applicationNo` varchar(150) NOT NULL,
  `applicant` varchar(150) NOT NULL,
  `applicationStatus` varchar(50) NOT NULL DEFAULT 'Beklemede',
  `applicationResult` int(11) DEFAULT 0,
  `chargeEmployee` varchar(150) DEFAULT NULL,
  `kardNo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `cardapplication`
--

INSERT INTO `cardapplication` (`id`, `applicationNo`, `applicant`, `applicationStatus`, `applicationResult`, `chargeEmployee`, `kardNo`) VALUES
(1, '333', '3333', 'Confirmed', 1, 'deniz', '1111222233334444 '),
(5, '559', '6667', 'Denied', 0, 'deniz', '1212346'),
(6, '563', '6667', 'Waiting', 0, NULL, '1212346'),
(8, '667957409', '6667', 'Waiting', 0, NULL, '322213112');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `corporatecard`
--

CREATE TABLE `corporatecard` (
  `id` int(11) NOT NULL,
  `cardName` varchar(150) NOT NULL,
  `cardNo` varchar(150) NOT NULL,
  `customer` varchar(150) NOT NULL,
  `cvv` varchar(10) NOT NULL,
  `exprationDate` date NOT NULL,
  `cardPass` varchar(10) NOT NULL,
  `cardPoint` double NOT NULL,
  `cardLimit` double DEFAULT NULL,
  `maxCardLimit` double DEFAULT NULL,
  `availableLimit` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `account` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `corporatecard`
--

INSERT INTO `corporatecard` (`id`, `cardName`, `cardNo`, `customer`, `cvv`, `exprationDate`, `cardPass`, `cardPoint`, `cardLimit`, `maxCardLimit`, `availableLimit`, `balance`, `account`) VALUES
(1, 'BankCardBusiness', '1212343', '5555', '123', '2021-12-31', '1111', 5, 5000, NULL, 0, 1000, '1112'),
(2, 'SilverCardBusiness', '1212344', '6666', '345', '2021-12-31', '1111', 14, 1000, NULL, 5000, 0, '1123'),
(3, 'GoldCardBusiness', '1212345', '5555', '456', '2021-12-31', '1111', 23, 1000, NULL, 5000, 0, '1145');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `corporatecustomer`
--

CREATE TABLE `corporatecustomer` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `userPass` varchar(150) NOT NULL,
  `customerNo` varchar(150) NOT NULL,
  `adress` varchar(500) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `income` double NOT NULL,
  `chargeEmployee` varchar(150) DEFAULT NULL,
  `taxNo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `corporatecustomer`
--

INSERT INTO `corporatecustomer` (`id`, `name`, `userName`, `userPass`, `customerNo`, `adress`, `mail`, `income`, `chargeEmployee`, `taxNo`) VALUES
(1, 'abcsirket', '5555', '1234', '5555', 'istanbul', 'abcsirket@gmail.com', 130000000, 'deniz', '123456789'),
(2, 'efgsirket', '6666', '9876', '6666', 'izmir', 'efgsirket@gmail.com', 1400000, 'merve', '987654321');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `corporateloan`
--

CREATE TABLE `corporateloan` (
  `id` int(11) NOT NULL,
  `loanName` varchar(150) NOT NULL,
  `loanNo` varchar(150) NOT NULL,
  `loanTaker` varchar(150) NOT NULL,
  `guarantor` varchar(150) NOT NULL,
  `interest` double NOT NULL,
  `maturity` int(11) NOT NULL,
  `amount` double NOT NULL,
  `refundedAmount` double NOT NULL,
  `remainingDebt` double NOT NULL,
  `lastPaymentDate` date NOT NULL,
  `givenLoanDate` date NOT NULL,
  `givenLoanAccount` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `corporateloan`
--

INSERT INTO `corporateloan` (`id`, `loanName`, `loanNo`, `loanTaker`, `guarantor`, `interest`, `maturity`, `amount`, `refundedAmount`, `remainingDebt`, `lastPaymentDate`, `givenLoanDate`, `givenLoanAccount`) VALUES
(1, 'DebitCurrentAccountLoan', '2344', '5555', '1111', 0.5, 5, 25000, 0, 25000, '2023-12-31', '2021-12-31', '1112'),
(2, 'SpotLoan', '1233', '5555', '2222', 0.5, 5, 25000, 0, 25000, '2022-12-31', '2021-12-31', '1112'),
(3, 'InstallmentLoan', '4566', '6666', '3333', 0.5, 5, 25000, 0, 25000, '2022-12-31', '2021-12-31', '1123');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `currentaccount`
--

CREATE TABLE `currentaccount` (
  `id` int(11) NOT NULL,
  `customerNo` varchar(150) NOT NULL,
  `accountNo` varchar(150) NOT NULL,
  `amount` double NOT NULL DEFAULT 0,
  `isBlocked` int(11) NOT NULL DEFAULT 0,
  `chargeEmployee` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `currentaccount`
--

INSERT INTO `currentaccount` (`id`, `customerNo`, `accountNo`, `amount`, `isBlocked`, `chargeEmployee`) VALUES
(1, '1111', '1112', 1000, 0, 'merve'),
(2, '2222', '1123', 2000, 0, 'ayse'),
(3, '3333', '1134', 4567, 0, 'ahmet'),
(4, '4444', '1145', 10, 0, 'deniz'),
(5, '1111', '1234', 13456, 0, 'merve'),
(18, '6667', '6668', 0, 0, ' '),
(26, '6667', '187065990', 18900, 0, ' '),
(27, '6667', '648493316', 1100, 0, ' '),
(28, '6667', '943649477', 0, 0, ' ');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `individualcard`
--

CREATE TABLE `individualcard` (
  `id` int(11) NOT NULL,
  `cardName` varchar(150) NOT NULL,
  `cardNo` varchar(150) NOT NULL,
  `customer` varchar(150) NOT NULL,
  `cvv` varchar(10) NOT NULL,
  `exprationDate` date NOT NULL,
  `cardPass` varchar(10) NOT NULL,
  `cardPoint` double NOT NULL,
  `cardLimit` double DEFAULT NULL,
  `maxCardLimit` double DEFAULT NULL,
  `availableLimit` double DEFAULT NULL,
  `balance` double DEFAULT NULL,
  `account` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `individualcard`
--

INSERT INTO `individualcard` (`id`, `cardName`, `cardNo`, `customer`, `cvv`, `exprationDate`, `cardPass`, `cardPoint`, `cardLimit`, `maxCardLimit`, `availableLimit`, `balance`, `account`) VALUES
(2, 'BankCardCustomer', '546', '1111', '345', '2021-12-31', '2222', 5, 0, 0, 0, 1000, '1112'),
(3, 'YoungCard', '47864', '2222', '567', '2021-12-31', '2222', 50, 0, 0, 0, 1000, '1234'),
(4, 'SilverCard', '864564', '3333', '346', '2021-12-31', '2222', 15, 1000, 0, 2650, 0, '1234'),
(5, 'GoldCard', '098765', '4444', '435', '2021-12-31', '2222', 20, 1000, 0, 3000, 0, ' 1145'),
(9, 'SilverCard', '1212346', '6667', '201', '2022-01-06', ' 3868', 0, 2500, 0, 2500, 0, ' '),
(10, 'GoldCard', '1212346', '6667', '524', '2022-01-06', ' 7761', 0, 7500, 0, 7500, 0, ' '),
(11, 'GoldCard', '1212346', '6667', '413', '2022-01-06', ' 1003', 0, 7500, 0, 7500, 0, ' '),
(13, 'SilverCard', '322213112', '6667', '967', '2022-01-06', ' 7744', 0, 4000, 0, 4000, 0, ' '),
(14, 'SilverCard', '322213112', '6667', '967', '2022-01-06', ' 7744', 0, 4000, 0, 4000, 0, ' ');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `individualcustomer`
--

CREATE TABLE `individualcustomer` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `userPass` varchar(150) NOT NULL,
  `customerNo` varchar(150) NOT NULL,
  `adress` varchar(500) NOT NULL,
  `mail` varchar(150) NOT NULL,
  `income` double NOT NULL,
  `chargeEmployee` varchar(150) NOT NULL,
  `individualId` varchar(20) NOT NULL,
  `occupation` varchar(50) NOT NULL,
  `birthDate` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `individualcustomer`
--

INSERT INTO `individualcustomer` (`id`, `name`, `userName`, `userPass`, `customerNo`, `adress`, `mail`, `income`, `chargeEmployee`, `individualId`, `occupation`, `birthDate`) VALUES
(1, 'rabia', '1111', '1234', '1111', 'ankara', 'rabia@gmail.com', 8000, 'deniz', '11111111111', ' muhendis', '1999-04-22'),
(2, 'ayben', '2222', '9876', '2222', 'ankara', 'ayben@gmail.com', 10000, 'deniz', '22222222222', 'muhendis', '2001-01-13'),
(3, 'derda', '3333', '12345', '3333', 'adiyaman', 'derda@gmail.com', 90000, 'ahmet', '3333333333', 'muhendis', '2001-02-04'),
(4, 'alimert', '4444', '4567', '4444', 'burdur', 'alimert@gmail.com', 70000, 'deniz', '44444444444', 'muhendis', '2000-09-22'),
(11, 'Derda', '6667', '987', '6667', 'adiyaman', 'mailim', 888, ' ', '1111123', ' ', '2000-02-02');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `individualloan`
--

CREATE TABLE `individualloan` (
  `id` int(11) NOT NULL,
  `loanName` varchar(150) NOT NULL,
  `loanNo` varchar(150) NOT NULL,
  `loanTaker` varchar(150) NOT NULL,
  `guarantor` varchar(150) NOT NULL,
  `interest` double NOT NULL,
  `maturity` int(11) NOT NULL,
  `amount` double NOT NULL,
  `refundedAmount` double NOT NULL,
  `remainingDebt` double NOT NULL,
  `lastPaymentDate` date NOT NULL,
  `givenLoanDate` date NOT NULL,
  `givenLoanAccount` varchar(150) NOT NULL,
  `licencePlate` varchar(150) DEFAULT NULL,
  `vehicleAge` varchar(150) DEFAULT NULL,
  `insuranceNo` varchar(150) DEFAULT NULL,
  `deedNo` varchar(150) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `individualloan`
--

INSERT INTO `individualloan` (`id`, `loanName`, `loanNo`, `loanTaker`, `guarantor`, `interest`, `maturity`, `amount`, `refundedAmount`, `remainingDebt`, `lastPaymentDate`, `givenLoanDate`, `givenLoanAccount`, `licencePlate`, `vehicleAge`, `insuranceNo`, `deedNo`) VALUES
(1, 'RetiredPersonalFinanceLoan', '4567', '1111', '2222', 0.5, 1, 25000, 0, 25000, '2023-12-30', '2021-12-30', '1112', ' ', '0', '', ' '),
(2, 'Insuranced', '7890', '2222', '1111', 1, 2, 25000, 0, 25000, '2022-12-31', '2021-12-31', '1123', 'plaka', '20', 'sigortaNo', ' '),
(3, 'NotInsuranced', '6789', '3333', '4444', 0.7, 5, 25000, 0, 25000, '2022-12-31', '2021-12-31', '1134', 'plaka', '5', ' ', ' '),
(4, 'EmployeeHousingLoan', '3456', '4444', '3333', 0.3, 4, 25000, 0, 25000, '2024-12-31', '2021-12-31', '1145', ' ', '0', ' ', 'tapuNo1'),
(5, 'RetiredHousingLoan', '2345', '1111', '3333', 0.5, 7, 25000, 0, 25000, '2023-12-31', '2021-12-31', '1112', ' ', '0', ' ', 'tapuNo2'),
(6, 'EmployeePersonalFinanceLoan', '1235', '2222', '4444', 1, 6, 25000, 0, 25000, '2022-12-31', '2021-12-31', '1145', ' ', '0', ' ', ' '),
(7, 'ConsumerPersonalFinanceLoan', '5689', '3333', '1111', 1.5, 5, 25000, 0, 25000, '2023-12-31', '2021-12-31', '1234', ' ', '0', ' ', ' '),
(10, 'RetiredPersonalFinanceLoan', '5764531', '6667', '2222', 0.25, 2, 3232, 0, 3232, '2022-01-06', '2022-01-06', '187065990', ' ', '0', ' ', ' '),
(11, 'RetiredPersonalFinanceLoan', '5710608', '6667', '2222', 0.25, 3, 6565, 0, 6565, '2022-01-06', '2022-01-06', '187065990', ' ', '0', ' ', ' '),
(12, 'RetiredPersonalFinanceLoan', '2968359', '6667', '2222', 0.25, 32, 252525, 0, 252525, '2022-01-06', '2022-01-06', '187065990', ' ', '0', ' ', ' '),
(13, 'RetiredPersonalFinanceLoan', '6640431', '6667', '2222', 0.25, 12, 25000, 0, 25000, '2022-01-06', '2022-01-06', '187065990', ' ', '0', ' ', ' ');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `loanapplication`
--

CREATE TABLE `loanapplication` (
  `id` int(11) NOT NULL,
  `applicationNo` varchar(150) NOT NULL,
  `applicant` varchar(150) NOT NULL,
  `applicationStatus` varchar(50) NOT NULL DEFAULT 'Beklemede',
  `applicationResult` int(11) DEFAULT 0,
  `chargeEmployee` varchar(150) DEFAULT NULL,
  `loanNo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `loanapplication`
--

INSERT INTO `loanapplication` (`id`, `applicationNo`, `applicant`, `applicationStatus`, `applicationResult`, `chargeEmployee`, `loanNo`) VALUES
(1, '444', '3333', 'Denied', 0, 'deniz', '4567'),
(2, '555', '3333', 'Confirmed', 1, 'deniz', '7890'),
(9, '363164759', '6667', 'Waiting', 0, NULL, '5764531'),
(10, '561414234', '6667', 'Waiting', 0, NULL, '5710608'),
(11, '346282455', '6667', 'Waiting', 0, NULL, '2968359'),
(12, '376199533', '6667', 'Confirmed', 1, 'deniz', '6640431');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `manager`
--

CREATE TABLE `manager` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `userPass` varchar(150) NOT NULL,
  `employeeId` varchar(150) NOT NULL,
  `salary` double NOT NULL,
  `mail` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `manager`
--

INSERT INTO `manager` (`id`, `name`, `userName`, `userPass`, `employeeId`, `salary`, `mail`) VALUES
(1, 'deniz', '9999', 'pass2', 'deniz', 7000, 'deniz@gmail.com'),
(2, 'merve', '1010', 'passs', 'merve', 5000, 'merve@gmail.com');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `moneytransfer`
--

CREATE TABLE `moneytransfer` (
  `id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  `description` varchar(250) NOT NULL,
  `forwarder` varchar(150) NOT NULL,
  `receiver` varchar(150) NOT NULL,
  `amount` double NOT NULL,
  `accountTransactionNo` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `moneytransfer`
--

INSERT INTO `moneytransfer` (`id`, `date`, `description`, `forwarder`, `receiver`, `amount`, `accountTransactionNo`) VALUES
(1, '2022-01-06 03:18:51', 'yemek-parasi', '2222', '3333', 30, '125'),
(4, '2022-01-06 00:00:00', 'hj', '187065990', '648493316', 1000, '127'),
(5, '2022-01-06 00:00:00', 'gsd', '187065990', '648493316', 100, '128');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `payment`
--

CREATE TABLE `payment` (
  `id` int(11) NOT NULL,
  `date` date NOT NULL,
  `description` varchar(250) NOT NULL,
  `card` varchar(150) NOT NULL,
  `amount` double NOT NULL,
  `accountTransactionNo` varchar(150) NOT NULL,
  `forwarder` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `payment`
--

INSERT INTO `payment` (`id`, `date`, `description`, `card`, `amount`, `accountTransactionNo`, `forwarder`) VALUES
(1, '2021-12-30', 'odeme1', '111122233334444', 100, '123', '1112'),
(2, '2022-01-01', 'odeme2', '2222333344445555', 1000, '124', '1134'),
(3, '2022-01-06', 'Payment with card', '864564', 250, '126', ' '),
(4, '2022-01-06', 'Payment with card', '864564', 100, '129', ' ');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `timedepositaccount`
--

CREATE TABLE `timedepositaccount` (
  `id` int(11) NOT NULL,
  `customerNo` varchar(150) NOT NULL,
  `accountNo` varchar(150) NOT NULL,
  `amount` double NOT NULL DEFAULT 0,
  `isBlocked` int(11) NOT NULL DEFAULT 0,
  `chargeEmployee` varchar(150) NOT NULL,
  `mainAccount` varchar(150) NOT NULL,
  `startingDate` date NOT NULL,
  `endDate` date NOT NULL,
  `interest` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `timedepositaccount`
--

INSERT INTO `timedepositaccount` (`id`, `customerNo`, `accountNo`, `amount`, `isBlocked`, `chargeEmployee`, `mainAccount`, `startingDate`, `endDate`, `interest`) VALUES
(1, '1111', '9876', 1234, 0, 'merve', '1112', '2021-12-30', '2022-12-30', 1),
(2, '2222', '8765', 7753, 0, 'ayse', '1123', '2022-01-01', '2022-06-01', 0.3);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `worker`
--

CREATE TABLE `worker` (
  `id` int(11) NOT NULL,
  `name` varchar(150) NOT NULL,
  `userName` varchar(150) NOT NULL,
  `userPass` varchar(150) NOT NULL,
  `employeeId` varchar(150) NOT NULL,
  `salary` double NOT NULL,
  `mail` varchar(150) NOT NULL,
  `chargeEmployee` varchar(150) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Tablo döküm verisi `worker`
--

INSERT INTO `worker` (`id`, `name`, `userName`, `userPass`, `employeeId`, `salary`, `mail`, `chargeEmployee`) VALUES
(1, 'ahmet', '7777', '12345', 'ahmet', 12000, 'ahmet@gmail.com', 'deniz'),
(2, 'ayse', '8888', '7654', 'ayse', 14000, 'ayse@gmail.com', 'merve'),
(5, 'ahmet', 'ahmet4653', 'CDLCGTUYWN', '4653', 721893, 'ahmet@ahmet.com', 'deniz');

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `accountapplication`
--
ALTER TABLE `accountapplication`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `cardapplication`
--
ALTER TABLE `cardapplication`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `corporatecard`
--
ALTER TABLE `corporatecard`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `corporatecustomer`
--
ALTER TABLE `corporatecustomer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `corporateloan`
--
ALTER TABLE `corporateloan`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `currentaccount`
--
ALTER TABLE `currentaccount`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `individualcard`
--
ALTER TABLE `individualcard`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `individualcustomer`
--
ALTER TABLE `individualcustomer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `individualloan`
--
ALTER TABLE `individualloan`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `loanapplication`
--
ALTER TABLE `loanapplication`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `manager`
--
ALTER TABLE `manager`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `moneytransfer`
--
ALTER TABLE `moneytransfer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `timedepositaccount`
--
ALTER TABLE `timedepositaccount`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `worker`
--
ALTER TABLE `worker`
  ADD PRIMARY KEY (`id`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `accountapplication`
--
ALTER TABLE `accountapplication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Tablo için AUTO_INCREMENT değeri `cardapplication`
--
ALTER TABLE `cardapplication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- Tablo için AUTO_INCREMENT değeri `corporatecard`
--
ALTER TABLE `corporatecard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Tablo için AUTO_INCREMENT değeri `corporatecustomer`
--
ALTER TABLE `corporatecustomer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `corporateloan`
--
ALTER TABLE `corporateloan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `currentaccount`
--
ALTER TABLE `currentaccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;

--
-- Tablo için AUTO_INCREMENT değeri `individualcard`
--
ALTER TABLE `individualcard`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;

--
-- Tablo için AUTO_INCREMENT değeri `individualcustomer`
--
ALTER TABLE `individualcustomer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- Tablo için AUTO_INCREMENT değeri `individualloan`
--
ALTER TABLE `individualloan`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- Tablo için AUTO_INCREMENT değeri `loanapplication`
--
ALTER TABLE `loanapplication`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- Tablo için AUTO_INCREMENT değeri `manager`
--
ALTER TABLE `manager`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Tablo için AUTO_INCREMENT değeri `moneytransfer`
--
ALTER TABLE `moneytransfer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Tablo için AUTO_INCREMENT değeri `payment`
--
ALTER TABLE `payment`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `timedepositaccount`
--
ALTER TABLE `timedepositaccount`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Tablo için AUTO_INCREMENT değeri `worker`
--
ALTER TABLE `worker`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
