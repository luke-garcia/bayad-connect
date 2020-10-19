-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Oct 16, 2020 at 08:29 AM
-- Server version: 10.2.10-MariaDB
-- PHP Version: 7.2.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bayadconnect_pilot`
--

-- --------------------------------------------------------

--
-- Table structure for table `tb_advisory`
--

CREATE TABLE `tb_advisory` (
  `id` int(11) NOT NULL,
  `msg` text NOT NULL,
  `status` char(1) NOT NULL,
  `recipient` tinytext NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_advisory`
--

INSERT INTO `tb_advisory` (`id`, `msg`, `status`, `recipient`, `created_at`) VALUES
(1, 'testing of notification ', '1', 'All', '2018-12-10 02:45:08');

-- --------------------------------------------------------

--
-- Table structure for table `tb_category`
--

CREATE TABLE `tb_category` (
  `id` int(11) NOT NULL,
  `category_name` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_category`
--

INSERT INTO `tb_category` (`id`, `category_name`) VALUES
(1, 'Dairy Products'),
(2, 'Canned Goods'),
(3, 'Personal Care'),
(4, 'Snacks and Chocolates');

-- --------------------------------------------------------

--
-- Table structure for table `tb_credentials`
--

CREATE TABLE `tb_credentials` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `accountid` varchar(5) NOT NULL,
  `wallet_token` varchar(50) NOT NULL,
  `wallet_apikey` varchar(50) NOT NULL,
  `api_token` varchar(50) NOT NULL,
  `dateadded` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `eloading` tinyint(1) DEFAULT NULL,
  `billspayment` tinyint(1) DEFAULT NULL,
  `shop` tinyint(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_credentials`
--

INSERT INTO `tb_credentials` (`id`, `user_id`, `tpa_id`, `accountid`, `wallet_token`, `wallet_apikey`, `api_token`, `dateadded`, `eloading`, `billspayment`, `shop`) VALUES
(1, 1, '1A61', '9082', '0fcc6370-4968-4607-97c3-e7c92d06c124', 'e7c92d06c124', 'fusa2SzG8mR17dH+cECRFJ1W2Yno7YDYuosJyXX63b8=', '2019-02-18 07:59:07', 1, 1, 1),
(2, 2, '1A75', '9082', '0fcc6370-4968-4607-97c3-e7c92d06c124', 'e7c92d06c124', 'fusa2SzG8mR17dH+cECRFKThKnu2hA7ywqPb3yvs2NM=', '2017-10-20 05:18:24', NULL, NULL, NULL),
(3, 3, '1A76', '9082', '0fcc6370-4968-4607-97c3-e7c92d06c124', 'e7c92d06c124', 'fusa2SzG8mR17dH+cECRFKa0Hyq9Qqbc1HvNjxiXDt4=', '2017-10-20 05:41:11', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_inquiry`
--

CREATE TABLE `tb_inquiry` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `message` varchar(500) NOT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `isopened` int(1) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_inquiry`
--

INSERT INTO `tb_inquiry` (`id`, `tpa_id`, `message`, `date_created`, `isopened`) VALUES
(1, '1A61', 'test unquiry', '2018-01-11 07:04:54', 0),
(2, '1A61', 'twst', '2018-01-16 07:23:13', 0),
(3, '0', 'nn', '2018-01-30 01:55:00', 0),
(4, '1A61', 'test in aws', '2018-01-31 07:15:34', 0);

-- --------------------------------------------------------

--
-- Table structure for table `tb_login`
--

CREATE TABLE `tb_login` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tpaid` varchar(5) NOT NULL,
  `password` varchar(50) NOT NULL,
  `dateadded` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_login`
--

INSERT INTO `tb_login` (`id`, `user_id`, `tpaid`, `password`, `dateadded`) VALUES
(1, 1, '1A61', '9PV+bC7BLTjePds7JrFxEA==:KSVAKCkoXyQjKCUjK19JXg==', '2018-01-25 08:47:29'),
(2, 2, '1A75', '9PV+bC7BLTjePds7JrFxEA==:KSVAKCkoXyQjKCUjK19JXg==', '2018-01-25 08:47:14'),
(3, 3, '1A76', '123456', '2017-10-20 05:17:08'),
(5, 10, '1A75', 'FSCgJsW1fXjEvqgLLeiItg==:KSVAKCkoXyQjKCUjK19JXg==', '2018-09-04 01:43:17');

-- --------------------------------------------------------

--
-- Table structure for table `tb_logs`
--

CREATE TABLE `tb_logs` (
  `id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `tpaid` varchar(5) NOT NULL,
  `activity` varchar(5) NOT NULL,
  `dateadded` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_orders`
--

CREATE TABLE `tb_orders` (
  `order_id` int(11) NOT NULL,
  `tpaid` tinytext DEFAULT NULL,
  `product_id` tinytext DEFAULT NULL,
  `address` tinytext DEFAULT NULL,
  `pieces` tinytext DEFAULT NULL,
  `total_amount` tinytext DEFAULT NULL,
  `order_date` tinytext DEFAULT NULL,
  `status` text DEFAULT NULL,
  `delivery_date` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_orders`
--

INSERT INTO `tb_orders` (`order_id`, `tpaid`, `product_id`, `address`, `pieces`, `total_amount`, `order_date`, `status`, `delivery_date`) VALUES
(1, '1A61', '6', 'testing address pilot', '20', '1300.00', '02-19-2019 03:52:20', NULL, NULL),
(2, '1A61', '4', 'resr', '8', '360.00', '02-19-2019 04:06:23', NULL, NULL),
(3, '1A61', '5', 'tedt', '2', '110.00', '02-19-2019 04:14:54', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_portal_user`
--

CREATE TABLE `tb_portal_user` (
  `id` int(11) NOT NULL,
  `username` varchar(25) NOT NULL,
  `password` varchar(50) NOT NULL,
  `fullname` varchar(150) NOT NULL,
  `dept` varchar(120) NOT NULL,
  `createdby` varchar(25) DEFAULT NULL,
  `createdon` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_portal_user`
--

INSERT INTO `tb_portal_user` (`id`, `username`, `password`, `fullname`, `dept`, `createdby`, `createdon`) VALUES
(1, 'bcdev', '803cc9e5a6c46e843416e2260ae7263c', 'Bayad Connect', '', 'eliza', '2018-08-30 02:15:28'),
(2, 'eliza', 'e10adc3949ba59abbe56e057f20f883e', 'Eliza Vel', 'Marketing', 'eliza', '2017-10-11 02:47:24');

-- --------------------------------------------------------

--
-- Table structure for table `tb_posted`
--

CREATE TABLE `tb_posted` (
  `id` int(11) NOT NULL,
  `tpaid` varchar(5) NOT NULL,
  `validated_id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tb_products`
--

CREATE TABLE `tb_products` (
  `product_id` int(11) NOT NULL,
  `category` tinytext DEFAULT NULL,
  `name` tinytext DEFAULT NULL,
  `size` tinytext DEFAULT NULL,
  `price_per_piece` tinytext DEFAULT NULL,
  `price_per_dozen` tinytext DEFAULT NULL,
  `image` tinytext DEFAULT NULL,
  `stock_count` tinytext DEFAULT NULL,
  `added_by` tinytext DEFAULT NULL,
  `date_added` tinytext DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_products`
--

INSERT INTO `tb_products` (`product_id`, `category`, `name`, `size`, `price_per_piece`, `price_per_dozen`, `image`, `stock_count`, `added_by`, `date_added`) VALUES
(2, 'Canned Goods', 'San Marino Corned Tuna', '150g', '19.54', '18.40', NULL, '1500', NULL, NULL),
(4, 'Dairy Products', 'Dairy Cream', '250g', '45', '40', NULL, '350', NULL, NULL),
(5, 'Dairy Products', 'Margarine', '500g', '55.00', '50.00', NULL, '360', NULL, NULL),
(6, 'Canned Goods', 'Century Tuna', '350g', '65.00', '60.00', NULL, '180', NULL, NULL),
(7, 'Canned Goods', 'test', '650ml', '65.00', '55.00', NULL, '1500', NULL, NULL),
(8, 'Snacks and Chocolates', 'Koko Crunch', '500g', '150.57', '148.55', NULL, '680', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `tb_transaction`
--

CREATE TABLE `tb_transaction` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `biller_code` varchar(5) NOT NULL,
  `service_code` varchar(5) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount_due` varchar(11) NOT NULL,
  `pass_on` varchar(11) NOT NULL,
  `amount_to_pay` varchar(11) NOT NULL,
  `otherinfo` varchar(500) DEFAULT NULL,
  `otherinfoforpost` varchar(500) DEFAULT NULL,
  `date_validated` varchar(20) NOT NULL,
  `balance_old` varchar(15) NOT NULL,
  `partnerrefno` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `longlat` varchar(20) NOT NULL,
  `cbci_code` int(5) DEFAULT NULL,
  `cbci_message` varchar(500) DEFAULT NULL,
  `cbci_transaction_no` varchar(20) DEFAULT NULL,
  `cbci_otherinfo` varchar(500) DEFAULT NULL,
  `cbci_receipt` varchar(50) DEFAULT NULL,
  `cbci_date` varchar(10) DEFAULT NULL,
  `balance_new` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_transaction`
--

INSERT INTO `tb_transaction` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(1, '1A61', '00265', 'PLECO', '98745862144718001826', '100', '10.00', '110.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>9874586214</MeterNo><ContactNo>09392580863</ContactNo>', NULL, '01-11-2018 16:49:12', '15000', '1A61180118044904', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6198044904', NULL, '1411A61164928', '01/11/2018', '14895'),
(2, '1A61', '00265', 'PLECO', '78978542167117471826', '5400', '15.00', '5,415.00', '<LastName>retest</LastName><FirstName>testing</FirstName><MI></MI><MeterNo>4876154873</MeterNo><ContactNo>09362587412</ContactNo>', NULL, '01-11-2018 16:55:35', '14895', '1A61180117045527', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6107045527', NULL, '5461A61165553', '01/11/2018', '9490'),
(3, '1A61', '00265', 'PLECO', '78978542167117993826', '180', '10.00', '190.00', '<LastName>tss</LastName><FirstName>tsdd</FirstName><MI></MI><MeterNo>7897853216</MeterNo><ContactNo>1234567</ContactNo>', NULL, '01-12-2018 13:49:45', '9490', '1A61180124014939', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6194014939', NULL, '2221A61134959', '01/12/2018', '9305'),
(4, '1A61', '00265', 'PLECO', '78978542167117162326', '8495', '15.00', '8,510.00', '<LastName>restes</LastName><FirstName>rest</FirstName><MI></MI><MeterNo>1234567800</MeterNo><ContactNo>09394635867</ContactNo>', NULL, '01-12-2018 13:56:26', '9305', '1A61180122015620', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6182015620', NULL, '8421A61135720', '01/12/2018', '805'),
(5, '1A61', '00265', 'PLECO', '78978542167117955282', '565.44', '10.00', '575.44', '<LastName>yesty</LastName><FirstName>yest</FirstName><MI></MI><MeterNo>1324567890</MeterNo><ContactNo>09392588863</ContactNo>', NULL, '01-12-2018 14:18:42', '805', '1A61180123021837', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6163021837', NULL, '6071A61141859', '01/12/2018', '234.56'),
(6, '1A61', '00022', 'MWSIN', '55653219', '234.56', '0.00', '234.56', NULL, NULL, '01-12-2018 14:50:43', '20234.56', '1A61180120025038', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6100025038', NULL, '2661A61145056', '01/12/2018', '20000'),
(7, '1A61', '00191', 'PELC2', '0101442343', '200', '5.00', '205.00', '<ConsumerName>tesr</ConsumerName><DueDate>01/15/2018</DueDate>', NULL, '01-15-2018 14:49:26', '20000', '1A61180150024858', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180151A6110024858', '<OtherInfo><PELC2RetVal></PELC2RetVal></OtherInfo>', '2401A61144946', '01/15/2018', '19795'),
(8, '1A61', '00265', 'PLECO', '78978542167117955282', '565.44', '10.00', '575.44', '<LastName>juan</LastName><FirstName>dela cruz</FirstName><MI></MI><MeterNo>1234567890</MeterNo><ContactNo>09392588088</ContactNo>', NULL, '01-16-2018 15:27:05', '19795', '1A61180166032659', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6136032659', NULL, '6111A61152728', '01/16/2018', '19224.56'),
(9, '1A61', '00132', 'VIECO', '23136100007', '224.56', '0.00', '224.56', '<FirstName>retest</FirstName><LastName>test</LastName>', NULL, '01-16-2018 16:04:52', '19224.56', '1A61180162040440', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6102040440', NULL, '2601A61160520', '01/16/2018', '19000'),
(10, '1A61', '00022', 'MWSIN', '55653219', '100', '0.00', '100.00', 'none', NULL, '01-16-2018 16:15:13', '19000', '1A61180164041505', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6104041505', NULL, '1361A61161536', '01/16/2018', '18900'),
(11, '1A61', '00009', 'GLOBE', '48531016', '200', '0.00', '200.00', '<AccountName>abc edc</AccountName><Telephone_Number>09178190800</Telephone_Number>', NULL, '01-16-2018 16:40:17', '18900', '1A61180165043954', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6135043954', '<OtherInfo><Payment></Payment></OtherInfo>', '2361A61164038', '01/16/2018', '18700'),
(12, '1A61', '00265', 'PLECO', '01001015804717520717', '5001', '15.00', '5,016.00', '<LastName>fe</LastName><FirstName>hermN</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo>8124545</ContactNo>', NULL, '01-16-2018 16:55:30', '18700', '1A61180169045525', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6189045525', NULL, '5521A61165556', '01/16/2018', '13694'),
(13, '1A61', '00265', 'PLECO', '01001015804717995817', '250', '10.00', '260.00', '<LastName>Herman</LastName><FirstName>Fe</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:12:36', '13694', '1A61180168051230', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, '1A61', '00265', 'PLECO', '01001015804717995817', '250', '10.00', '260.00', '<LastName>hernan</LastName><FirstName>fe</FirstName><MI></MI><MeterNo>ba617</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:15:37', '13694', '1A61180164051526', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6184051526', NULL, '2961A61171558', '01/16/2018', '13439'),
(15, '1A61', '00265', 'PLECO', '01001001504317412892', '6000', '15.00', '6,015.00', '<LastName>tablatin</LastName><FirstName>allan</FirstName><MI></MI><MeterNo>7544883</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:21:44', '13439', '1A61180169052139', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6109052139', NULL, '6511A61172201', '01/16/2018', '7434'),
(16, '1A61', '00265', 'PLECO', '01001001504317512792', '5001', '15.00', '5,016.00', '<LastName>a</LastName><FirstName>a</FirstName><MI></MI><MeterNo>aau7</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:26:40', '7434', '1A61180169052631', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6179052631', NULL, '5521A61172655', '01/16/2018', '2428'),
(17, '1A61', '00265', 'PLECO', '78978542167117511726', '5001', '15.00', '5,016.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>1234567800</MeterNo><ContactNo>1234567</ContactNo>', NULL, '01-17-2018 09:11:28', '17428', '1A61180170091120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6120091120', NULL, '5531A61091217', '01/17/2018', '12422'),
(18, '1A61', '00265', 'PLECO', '01001001504317977847', '350.45', '10.00', '360.45', '<LastName>tablatin</LastName><FirstName>allan</FirstName><MI></MI><MeterNo>7544883</MeterNo><ContactNo></ContactNo>', NULL, '01-17-2018 11:37:23', '12422', '1A61180175113718', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6185113718', NULL, '3971A61113759', '01/17/2018', '12066.55'),
(19, '1A61', '00265', 'PLECO', '01001015804717512792', '5001', '15.00', '5,016.00', '<LastName>fe</LastName><FirstName>herman</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo></ContactNo>', NULL, '01-17-2018 11:44:49', '12066.55', '1A61180174114444', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6154114444', NULL, '5531A61114507', '01/17/2018', '7060.55'),
(20, '1A61', '00191', 'PELC2', '0101442434', '200', '5.00', '205.00', '<ConsumerName>testing</ConsumerName><DueDate>01/23/2018</DueDate>', NULL, '01-23-2018 08:49:18', '7060.55', '1A61180231084903', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6171084903', '<OtherInfo><PELC2RetVal></PELC2RetVal></OtherInfo>', '2391A61084939', '01/23/2018', '6855.55'),
(21, '1A61', '00132', 'VIECO', '23136100007', '150', '0.00', '150.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '01-23-2018 08:51:33', '6855.55', '1A61180234085129', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6104085129', NULL, '1841A61085319', '01/23/2018', '6705.55'),
(22, '1A61', '00022', 'MWSIN', '55653219', '205.55', '0.00', '205.55', 'none', NULL, '01-23-2018 08:55:33', '6705.55', '1A61180230085529', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6120085529', NULL, '2391A61085601', '01/23/2018', '6500'),
(23, '1A61', '00015', 'MWCOM', '18951519', '100', '0.00', '100.00', 'none', NULL, '01-23-2018 08:57:06', '6500', '1A61180230085701', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6150085701', NULL, '1341A61085742', '01/23/2018', '6400'),
(24, '1A61', '00009', 'GLOBE', '1032501421', '354.77', '0.00', '354.77', '<AccountName>testing</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '01-23-2018 09:01:07', '6400', '1A61180230090034', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6140090034', '<OtherInfo><Payment></Payment></OtherInfo>', '3881A61090130', '01/23/2018', '6045.23'),
(25, '1A61', '00005', 'SMART', '0176234369', '45.23', '0.00', '45.23', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>2134657</TelephoneNumber><Product>c</Product>', NULL, '01-23-2018 09:06:00', '6045.23', '1A61180239090545', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6159090545', NULL, '791A61090622', '01/23/2018', '6000'),
(26, '1A61', '00214', 'PLDT6', '0230857954', '310', '7.00', '317.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '01-23-2018 09:10:22', '6000', '1A61180232090958', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6152090958', NULL, '3511A61091042', '01/23/2018', '5683'),
(27, '1A61', '00046', 'INNOV', '841051762', '423', '0.00', '423.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number><DueDate>01/23/2018</DueDate>', NULL, '01-23-2018 09:11:55', '5683', '1A61180231091151', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6141091151', NULL, '4571A61091213', '01/23/2018', '5260'),
(28, '1A61', '00003', 'SKY01', '203015739', '320', '0.00', '320.00', '<ServiceType>0</ServiceType><DueDate>01/23/2018</DueDate><BillDate>01/23/2018</BillDate>', NULL, '01-23-2018 09:14:17', '5260', '1A61180230091413', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6120091413', NULL, '3541A61091435', '01/23/2018', '4940'),
(29, '1A61', '00158', 'CGNAL', '9006567460', '350', '0.00', '350.00', '<FirstName>test</FirstName><LastName>terms</LastName><MI>t</MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '01-23-2018 09:16:57', '4940', '1A61180237091653', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6117091653', NULL, '3841A61091721', '01/23/2018', '4590'),
(30, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Uriah</LastName><FirstName>Velunta</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>123456</ContactNo>', NULL, '01-25-2018 10:33:46', '4590', '1A61180254103341', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>u</LastName><FirstName>velunta</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>4253366</ContactNo>', NULL, '01-25-2018 10:36:43', '4590', '1A61180258103638', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '1A61', '00022', 'MWSIN', '55653219', '40', '0.00', '40.00', 'none', NULL, '01-25-2018 10:44:13', '4590', '1A61180252104409', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(33, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', 'none', NULL, '01-25-2018 10:49:39', '4590', '1A61180257104935', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, '1A61', '00015', 'MWCOM', '18951519', '40', '0.00', '40.00', 'none', NULL, '01-25-2018 10:52:00', '4590', '1A61180258105156', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6148105156', NULL, '761A61105219', '01/25/2018', '4550'),
(35, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Velunta</LastName><FirstName>Uriah</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 11:06:04', '4550', '1A61180251110559', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, '1A61', '00022', 'MWSIN', '55653219', '50', '0.00', '50.00', 'none', NULL, '01-25-2018 11:14:07', '4550', '1A61180252111402', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, '1A61', '00022', 'MWSIN', '55653219', '50', '0.00', '50.00', 'none', NULL, '01-25-2018 11:21:25', '4550', '1A61180257112120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6137112120', NULL, '861A61112138', '01/25/2018', '4500'),
(38, '1A61', '00265', 'PLECO', '74152896532417997630', '150', '10.00', '160.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>1234567890</MeterNo><ContactNo>09392580863</ContactNo>', NULL, '01-25-2018 11:31:18', '4500', '1A61180252113110', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6102113110', NULL, '1961A61113131', '01/25/2018', '4345'),
(39, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Velunta</LastName><FirstName>Uriah</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 11:32:42', '4500', '1A61180253113236', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6193113236', NULL, '6001A61113256', '01/25/2018', '3785.45'),
(40, '1A61', '00265', 'PLECO', '25469874515718002973', '100', '10.00', '110.00', '<LastName>velunta</LastName><FirstName>uriah</FirstName><MI></MI><MeterNo>2546987451</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 14:04:11', '23785.45', '1A61180256020401', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6186020401', NULL, '1461A61140451', '01/25/2018', '23680.45'),
(41, '1A61', '00132', 'VIECO', '23136100007', '777', '0.00', '777.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '01-25-2018 15:31:29', '23680.45', '1A61180252033120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6132033120', NULL, '8131A61153409', '01/25/2018', '22903.45'),
(42, '1A61', '00022', 'MWSIN', '55653219', '103.45', '0.00', '103.45', 'none', NULL, '01-25-2018 17:07:30', '22903.45', '1A61180255050725', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6105050725', NULL, '1391A61170804', '01/25/2018', '22800'),
(43, '1A61', '00265', 'PLECO', '65421465328917987926', '250.47', '10.00', '260.47', '<LastName>Velunta</LastName><FirstName>Uriah</FirstName><MI></MI><MeterNo>6542146532</MeterNo><ContactNo>09328684737</ContactNo>', NULL, '01-25-2018 17:52:24', '22800', '1A61180257055218', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6197055218', NULL, '2961A61175254', '01/25/2018', '22544.53'),
(44, '1A61', '00015', 'MWCOM', '18951519', '20.00', '0.00', '20.00', 'none', NULL, '08-13-2018 14:24:58', '996361.4225', '1A61182258022455', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182251A6198022455', NULL, '531A61142512', '08/13/2018', '996356.6445'),
(45, '1A61', '00114', 'DVOLT', '23136100007', '100.00', '0.00', '100.00', '<FirstName>tewt</FirstName><LastName>est</LastName>', NULL, '08-30-2018 11:14:54', '996356.6445', '1A61182428111450', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182421A6178111450', NULL, '1321A61111505', '08/30/2018', '996356.6445'),
(46, '1A75', '00022', 'MWSIN', '55653219', '356.64', '0.00', '356.64', 'none', NULL, '09-04-2018 09:44:06', '996356.6445', '1A75182473094402', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182471A7593094402', NULL, '3891A75094417', '09/04/2018', '996356.6445'),
(47, '1A61', '00157', 'AEON1', '1234567890', '100.00', '15.00', '115.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-10-2018 14:29:00', '996356.6445', '1A61182530022851', 'samsung', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(48, '1A61', '00157', 'AEON1', '1000003537', '10.00', '15.00', '25.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-10-2018 14:55:46', '996356.6445', '1A61182531025526', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '182531A6181025526', NULL, '551A61145628', '09/10/2018', '996356.6445'),
(49, '1A61', '00157', 'AEON1', '1000003537', '10.00', '15.00', '25.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-10-2018 15:02:40', '996356.6445', '1A61182539030233', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '182531A6149030233', NULL, '551A61150335', '09/10/2018', '996356.6445'),
(50, '1A61', '00157', 'AEON1', '1000003537', '20.00', '15.00', '35.00', '<AccountName>test</AccountName>', NULL, '09-10-2018 15:12:57', '996356.6445', '1A61182536031246', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182531A6176031246', NULL, '651A61151309', '09/10/2018', '996356.6445'),
(51, '1A61', '00132', 'VIECO', '23136100007', '200.00', '0.00', '200.00', '<FirstName>tesr</FirstName><LastName>tedt</LastName>', NULL, '09-10-2018 15:14:19', '996356.6445', '1A61182537031415', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182531A6187031415', NULL, '2301A61151432', '09/10/2018', '996356.6445'),
(52, '1A61', '00114', 'DVOLT', '23136100007', '56.00', '0.00', '56.00', '<FirstName>ted</FirstName><LastName>uk</LastName>', NULL, '09-14-2018 11:14:12', '996356.6445', '1A61182576111405', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182571A6116111405', NULL, '901A61111427', '09/14/2018', '996356.6445'),
(53, '1A61', '00132', 'VIECO', '23136100007', '356.64', '0.00', '356.64', '<FirstName>tesr</FirstName><LastName>tsd</LastName>', NULL, '09-14-2018 13:15:14', '996356.6445', '1A61182570011504', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182571A6170011504', NULL, '3901A61131526', '09/14/2018', '996000.0045'),
(54, '1A61', '00001', 'MECOP', '111234567801', '100.00', '8.00', '108.00', 'none', NULL, '09-17-2018 13:53:42', '996000.0045', '1A61182607015310', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182601A6107015310', '<OtherInfo><EquivalentKwh>100</EquivalentKwh><tariffCharge>1</tariffCharge><numOfPurchase>1</numOfPurchase><RCPT>111234567801::20180917135422160</RCPT></OtherInfo>', '1451A61135418', '09/17/2018', '995892.0045'),
(55, '1A61', '00114', 'DVOLT', '23136100007', '92.00', '0.00', '92.00', '<FirstName>tedt</FirstName><LastName>mobile</LastName>', NULL, '09-17-2018 13:58:44', '995892.0045', '1A61182605015824', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182601A6165015824', NULL, '1291A61135856', '09/17/2018', '995800.0045'),
(56, '1A61', '00157', 'AEON1', '1000003537', '100.00', '15.00', '115.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:12:02', '995800.0045', '1A61182615091154', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182611A6115091154', NULL, '1531A61091251', '09/18/2018', '995685.0045'),
(57, '1A61', '00157', 'AEON1', '1000003537', '100.00', '15.00', '115.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:39:30', '995685.0045', '1A61182619093921', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182611A6109093921', NULL, '1531A61094032', '09/18/2018', '995570.0045'),
(58, '1A61', '00157', 'AEON1', '2002209384', '200.00', '15.00', '215.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:48:32', '995570.0045', '1A61182619094828', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(59, '1A61', '00157', 'AEON1', '3000000016', '300.00', '15.00', '315.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:50:27', '995570.0045', '1A61182615095020', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(60, '1A61', '00157', 'AEON1', '4000869725', '400.00', '15.00', '415.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:51:16', '995570.0045', '1A61182618095111', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(61, '1A61', '00157', 'AEON1', '5000000014', '500.00', '15.00', '515.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:52:48', '995570.0045', '1A61182617095243', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(62, '1A61', '00157', 'AEON1', '7004356924', '700.00', '15.00', '715.00', '<AccountName>Chris Cruz</AccountName>', NULL, '09-18-2018 09:53:58', '995570.0045', '1A61182612095353', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(63, '1A61', '00157', 'AEON1', '6012345670', '600.00', '15.00', '615.00', '<AccountName>Chriz Cruz</AccountName>', NULL, '09-18-2018 09:55:57', '995570.0045', '1A61182612095550', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(64, '1A61', '00157', 'AEON1', '8003874566', '100.00', '15.00', '115.00', '<AccountName>Abby Cruz</AccountName>', NULL, '09-18-2018 09:57:33', '995570.0045', '1A61182617095728', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182611A6197095728', NULL, '1531A61095755', '09/18/2018', '995455.0045'),
(65, '1A61', '00040', 'BNKRD', '6250075102060007', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><BillDate>09/21/2017</BillDate>', NULL, '09-21-2018 10:43:49', '995455.0045', '1A61182648104344', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(66, '1A61', '00040', 'BNKRD', '9170100206521000', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><BillDate>09/21/2018</BillDate>', NULL, '09-21-2018 11:04:01', '995455.0045', '1A61182645110357', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182641A6115110357', NULL, '1321A61110436', '09/21/2018', '995355.0045'),
(67, '1A61', '00040', 'BNKRD', '9170100206521000', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><BillDate>09/21/2018</BillDate>', NULL, '09-21-2018 11:15:30', '995355.0045', '1A61182644111522', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182641A6194111522', NULL, '1321A61111548', '09/21/2018', '995255.0045'),
(68, '1A61', '00040', 'BNKRD', '4573580400010013', '200.00', '0.00', '200.00', '<AccountName>Abby Cruz</AccountName><BillDate>09/21/2018</BillDate>', NULL, '09-21-2018 11:19:57', '995255.0045', '1A61182643111952', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182641A6183111952', NULL, '2321A61112028', '09/21/2018', '995055.0045'),
(69, '1A61', '00074', 'BTCO1', '0101010010', '3244.80', '5.00', '3249.80', '<ConsumerName>Chris Cruz</ConsumerName><DueDate>09/20/2018</DueDate><BillMonth>10/2018</BillMonth>', NULL, '09-21-2018 15:13:57', '995055.0045', '1A61182645031352', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(70, '1A61', '00074', 'BTCO1', '0101010010', '3244.80', '5.00', '3249.80', '<ConsumerName>Chris Cruz</ConsumerName><DueDate>09/21/2018</DueDate><BillMonth>09/2018</BillMonth>', NULL, '09-21-2018 15:18:31', '995055.0045', '1A61182647031817', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182641A6117031817', '<OtherInfo><BTCO1TranNo>182641A610002</BTCO1TranNo></OtherInfo>', '3811A61151858', '09/21/2018', '991805.2045'),
(71, '1A61', '00129', 'BTCO2', '0372552', '100.00', '10.00', '110.00', '<ConsumerName>Chris Cruz</ConsumerName><DueDate>2018/09/21</DueDate><BillMonth>2018/09</BillMonth><BookID>123456</BookID>', NULL, '09-21-2018 16:45:21', '991805.2045', '1A61182648044516', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182641A6138044516', NULL, '1421A61164617', '09/21/2018', '991695.2045'),
(72, '1A61', '00002', 'BAYAN', '11111111111', '100.00', '0.00', '100.00', '<ConsumerName></ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '09-24-2018 10:29:42', '991695.2045', '1A61182672102935', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(73, '1A61', '00002', 'BAYAN', '11111111111', '100.00', '0.00', '100.00', '<ConsumerName>Chris Cruz</ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '09-24-2018 10:39:01', '991695.2045', '1A61182672103856', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(74, '1A61', '00002', 'BAYAN', '11111111111', '100.00', '0.00', '100.00', '<ConsumerName>Chris Cruz</ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '09-24-2018 11:02:01', '991695.2045', '1A61182670110156', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(75, '1A61', '00030', 'CLNK1', '001123456', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>12345678</ContactNo>', NULL, '09-25-2018 14:11:42', '991695.2045', '1A61182683021137', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(76, '1A61', '00030', 'CLNK1', '123456789', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:15:37', '991695.2045', '1A61182683021531', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(77, '1A61', '00030', 'CLNK1', '111111111', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:17:28', '991695.2045', '1A61182686021723', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(78, '1A61', '00030', 'CLNK1', '001123456', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:30:26', '991695.2045', '1A61182680023019', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6100023019', NULL, '1361A61143059', '09/25/2018', '991595.2045'),
(79, '1A61', '00030', 'CLNK1', '001123456', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:35:12', '991595.2045', '1A61182684023507', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6114023507', NULL, '1361A61143538', '09/25/2018', '991495.2045'),
(80, '1A61', '00030', 'CLNK1', '001123456', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:39:07', '991495.2045', '1A61182688023901', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6108023901', NULL, '1361A61143929', '09/25/2018', '991395.2045'),
(81, '1A61', '00030', 'CLNK1', '111111111', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>12345678</ContactNo>', NULL, '09-25-2018 14:46:56', '991395.2045', '1A61182689024651', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(82, '1A61', '00030', 'CLNK1', '111111111', '100.00', '0.00', '100.00', '<AccountName>Chris Cruz</AccountName><ContactNo>1234567</ContactNo>', NULL, '09-25-2018 14:48:53', '991395.2045', '1A61182682024849', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6152024849', NULL, '1361A61144911', '09/25/2018', '991295.2045'),
(83, '1A61', '00163', 'HCPHL', '3313579111', '150.00', '15.00', '165.00', '<Name>Chris Cruz</Name><PhoneNo></PhoneNo>', NULL, '09-25-2018 15:29:02', '991295.2045', '1A61182682032858', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(84, '1A61', '00163', 'HCPHL', '3313579111', '150.00', '15.00', '165.00', '<Name>Chris Cruz</Name><PhoneNo>123456</PhoneNo>', NULL, '09-25-2018 16:11:16', '991295.2045', '1A61182680041111', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6100041111', NULL, '2011A61161142', '09/25/2018', '991130.2045'),
(85, '1A61', '00163', 'HCPHL', '3313579111', '150.00', '15.00', '165.00', '<Name>Chris Cruz</Name><PhoneNo>123456</PhoneNo>', NULL, '09-25-2018 16:15:15', '991130.2045', '1A61182689041511', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6199041511', NULL, '2011A61161541', '09/25/2018', '990965.2045'),
(86, '1A61', '00163', 'HCPHL', '3424681012', '185.00', '15.00', '200.00', '<Name>Abby Cruz</Name><PhoneNo>654321</PhoneNo>', NULL, '09-25-2018 16:18:22', '990965.2045', '1A61182687041818', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182681A6197041818', NULL, '2361A61161845', '09/25/2018', '990765.2045'),
(87, '1A61', '00239', 'MPAY1', 'MP03MRGA46', '26.00', '0.00', '26.00', '<ContactNumber></ContactNumber><Affiliate>NBI</Affiliate>', NULL, '09-27-2018 14:16:29', '990765.2045', '1A61182707021601', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(88, '1A61', '00239', 'MPAY1', 'MPXIC4KZM2', '26.00', '0.00', '26.00', '<ContactNumber>09123456789</ContactNumber><Affiliate>NBI</Affiliate>', NULL, '09-27-2018 14:37:42', '990765.2045', '1A61182708023711', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(89, '1A61', '00239', 'MPAY1', 'MP03MRGA46', '26.00', '0.00', '26.00', '<ContactNumber>09123456789</ContactNumber><Affiliate>NBI</Affiliate>', NULL, '09-27-2018 14:41:45', '990765.2045', '1A61182700024120', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(90, '1A61', '00239', 'MPAY1', 'MP03MRGA46', '26.00', '0.00', '26.00', '<ContactNumber>09123456789</ContactNumber><Affiliate>NBI</Affiliate>', NULL, '09-27-2018 14:41:46', '990765.2045', '1A61182701024142', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(91, '1A61', '00239', 'MPAY1', 'MP03MRGA46', '26.00', '0.00', '26.00', '<ContactNumber>09123456789</ContactNumber><Affiliate>DFA</Affiliate>', NULL, '09-27-2018 14:43:22', '990765.2045', '1A61182702024303', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182701A6142024303', '<OtherInfo><AccountName>DUMMY ACCOUNT</AccountName><TransactionId>5BAC3527DA1B9</TransactionId><Source>National Bureau of Investigation (DEV)</Source><BillerCode>NBI02</BillerCode></OtherInfo>', '641A61144419', '09/27/2018', '990739.2045'),
(92, '1A61', '00239', 'MPAY1', 'MPJS64DCC5', '46.00', '0.00', '46.00', '<ContactNumber>09123456789</ContactNumber><Affiliate>PRC</Affiliate>', NULL, '09-27-2018 14:51:29', '990739.2045', '1A61182707025104', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182701A6167025104', '<OtherInfo><AccountName>DUMMY ACCOUNT</AccountName><TransactionId>5BAC3543D6258</TransactionId><Source>Professional Regulation Commission (DEV)</Source><BillerCode>PRC02</BillerCode></OtherInfo>', '841A61145213', '09/27/2018', '990693.2045'),
(93, '1A61', '00239', 'MPAY1', 'MPKIOLZMV6', '51.00', '0.00', '51.00', '<ContactNumber></ContactNumber><Affiliate>DFA</Affiliate>', NULL, '09-27-2018 14:56:59', '990693.2045', '1A61182705025629', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182701A6175025629', '<OtherInfo><AccountName>DUMMY ACCOUNT</AccountName><TransactionId>5BAC355FC3F18</TransactionId><Source>DFA (DEV)</Source><BillerCode>DFA01</BillerCode></OtherInfo>', '891A61145729', '09/27/2018', '990642.2045'),
(94, '1A61', '00128', 'LGNWC', '30655639', '100.00', '0.00', '100.00', '<DueDate>09/27/2018</DueDate>', NULL, '09-27-2018 15:25:18', '990642.2045', '1A61182709032513', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(95, '1A61', '00128', 'LGNWC', '30655639', '100.00', '0.00', '100.00', '<DueDate>09/26/2018</DueDate>', NULL, '09-27-2018 15:31:54', '990642.2045', '1A61182702033149', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(96, '1A61', '00132', 'ABPWR', '95631000007', '100.00', '0.00', '100.00', '<FirstName></FirstName><LastName></LastName><PowerCompany>CLPC</PowerCompany>', NULL, '09-27-2018 16:56:35', '990642.2045', '1A61182704045630', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(97, '1A61', '00257', 'ECNSS', '0156600054001128', '100.00', '0.00', '100.00', '<PayorName>Chris Cruz</PayorName><ContactNo>09123456789</ContactNo>', NULL, '09-28-2018 10:47:10', '990642.2045', '1A61182712104705', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182711A6142104705', NULL, '1391A61104733', '09/28/2018', '990542.2045'),
(98, '1A61', '00257', 'ECNSS', '0156400286001110', '42.20', '0.00', '42.20', '<PayorName>Anne Cruz</PayorName><ContactNo>09123456789</ContactNo>', NULL, '09-28-2018 10:52:42', '990542.2045', '1A61182719105237', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182711A6139105237', NULL, '811A61105322', '09/28/2018', '990500.0045'),
(99, '1A61', '00167', 'APEC1', '1000021660007091', '100.00', '0.00', '100.00', '<SOA>1</SOA><AccountName>Chris Cruz</AccountName><InvoiceNo>340</InvoiceNo><PaymentType>S</PaymentType><BillAmount>100.00</BillAmount><DeliveryDate>2018-10-01</DeliveryDate><BillMonth>10</BillMonth><BillYear>2018</BillYear>', NULL, '10-01-2018 10:40:49', '990500.0045', '1A61182744104041', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(100, '1A61', '00167', 'APEC1', '100002166000709', '100.00', '0.00', '100.00', '<SOA>2</SOA><AccountName>Chris Cruz</AccountName><InvoiceNo>340</InvoiceNo><PaymentType>S</PaymentType><BillAmount>100.00</BillAmount><DeliveryDate>2018-10-01</DeliveryDate><BillMonth>10</BillMonth><BillYear>2018</BillYear>', NULL, '10-01-2018 10:51:26', '990500.0045', '1A61182746105121', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6176105121', NULL, '1301A61105921', '10/01/2018', '990400.0045'),
(101, '1A61', '00002', 'BAYAN', '11111111111', '100.00', '0.00', '100.00', '<ConsumerName>Chris Cruz</ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '10-01-2018 14:02:28', '990400.0045', '1A61182744020223', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6114020223', NULL, '1301A61140251', '10/01/2018', '990300.0045'),
(102, '1A61', '00002', 'BAYAN', '11111111111', '100.00', '0.00', '100.00', '<ConsumerName>Chris Cruz</ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '10-01-2018 14:04:24', '990300.0045', '1A61182743020419', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6183020419', NULL, '1301A61140448', '10/01/2018', '990200.0045'),
(103, '1A61', '00128', 'LGNWC', '30655639', '100.00', '0.00', '100.00', '<DueDate>10/01/2018</DueDate>', NULL, '10-01-2018 14:17:24', '990200.0045', '1A61182742021719', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6142021719', NULL, '1301A61141743', '10/01/2018', '990100.0045'),
(104, '1A61', '00128', 'LGNWC', '30655639', '100.00', '0.00', '100.00', '<DueDate>10/01/2018</DueDate>', NULL, '10-01-2018 14:19:20', '990100.0045', '1A61182748021915', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6138021915', NULL, '1301A61141940', '10/01/2018', '990000.0045'),
(105, '1A61', '00186', 'ABSMO', '800062640', '100.00', '0.00', '100.00', '<PhoneNo></PhoneNo>', NULL, '10-01-2018 14:33:11', '990000.0045', '1A61182740023306', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(106, '1A61', '00186', 'ABSMO', '111111111', '100.00', '0.00', '100.00', '<PhoneNo>1234567</PhoneNo>', NULL, '10-01-2018 14:35:03', '990000.0045', '1A61182740023457', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(107, '1A61', '00186', 'ABSMO', '800062640', '100.00', '0.00', '100.00', '<PhoneNo>1234567</PhoneNo>', NULL, '10-01-2018 14:46:26', '990000.0045', '1A61182744024621', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6124024621', NULL, '1301A61144648', '10/01/2018', '989900.0045'),
(108, '1A61', '00186', 'ABSMO', '800062640', '100.00', '0.00', '100.00', '<PhoneNo>1234567</PhoneNo>', NULL, '10-01-2018 14:48:12', '989900.0045', '1A61182749024807', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6109024807', NULL, '1301A61145342', '10/01/2018', '989800.0045'),
(109, '1A61', '00225', 'MBCCC', '5464971885436113', '100.00', '0.00', '100.00', '<ConsName>Chris Cruz</ConsName>', NULL, '10-01-2018 16:02:35', '989800.0045', '1A61182748040230', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6168040230', NULL, '1301A61160329', '10/01/2018', '989700.0045'),
(110, '1A61', '00225', 'MBCCC', '5464971885436113', '100.00', '0.00', '100.00', '<ConsName>Chris Cruz</ConsName>', NULL, '10-01-2018 16:05:17', '989700.0045', '1A61182743040512', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182741A6163040512', NULL, '1301A61160538', '10/01/2018', '989600.0045'),
(111, '1A61', '00082', 'PWCOR', '01002386001010', '100.00', '5.00', '105.00', '<AccountName>Chris Cruz</AccountName><BillNo>12345678901234</BillNo>', NULL, '10-02-2018 11:13:36', '989600.0045', '1A61182757111331', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(112, '1A61', '00189', 'SSSC1', '3491544800', '220.00', '0.00', '220.00', '<PayFor>PRN</PayFor><PRN>V1018000009254</PRN><CountryCode>PHL</CountryCode>', NULL, '10-11-2018 13:44:57', '990335.5045', '1A61182846014451', 'samsung', '121.144 - 19.114', 200, 'Either Payment Ref No or SS ID is required.', NULL, NULL, NULL, NULL, '990335.5045'),
(113, '1A61', '00189', 'SSSC1', '3491544800', '220.00', '0.00', '220.00', '<PayFor>PRN</PayFor><PRN>V1018000009254</PRN><CountryCode>PHL</CountryCode>', NULL, '10-11-2018 13:50:23', '990335.5045', '1A61182845015018', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '182841A6115015018', '<OtherInfo><SSSTranNo>BCI182841A610001</SSSTranNo><SSID>3491544800</SSID><PayorType>VOLUNTARY</PayorType><PayorName>JEANE BCI MR</PayorName><From>05/01/2030</From><To>05/01/2030</To><BRN></BRN><PRN>V1018000009254</PRN></OtherInfo>', '2511A61135036', '10/11/2018', '990335.5045'),
(114, '1A61', '00189', 'SSSC1', '3491544787', '330.00', '0.00', '330.00', '<PayFor>PRN</PayFor><PRN>S1018000008923</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:10:49', '990335.5045', '1A61182851011039', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182851A6131011039', '<OtherInfo><SSSTranNo>BCI182851A610001</SSSTranNo><SSID>3491544787</SSID><PayorType>SELF-EMPLOYED</PayorType><PayorName>GINA BCI MR</PayorName><From>10/01/2021</From><To>12/01/2021</To><BRN></BRN><PRN>S1018000008923</PRN></OtherInfo>', '3621A61131813', '10/12/2018', '990335.5045'),
(115, '1A61', '00189', 'SSSC1', '3491551725', '110.00', '0.00', '110.00', '<PayFor>PRN</PayFor><PRN>S1018000009363</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:24:12', '990335.5045', '1A61182859012407', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182851A6119012407', '<OtherInfo><SSSTranNo>BCI182851A610002</SSSTranNo><SSID>3491551725</SSID><PayorType>SELF-EMPLOYED</PayorType><PayorName>KRIS BCI MR</PayorName><From>09/01/2020</From><To>09/01/2020</To><BRN></BRN><PRN>S1018000009363</PRN></OtherInfo>', '1421A61132430', '10/12/2018', '990335.5045'),
(116, '1A61', '00189', 'SSSC1', '3491551725', '165.00', '0.00', '165.00', '<PayFor>PRN</PayFor><PRN>V1018000009367</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:34:31', '990335.5045', '1A61182859013425', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182851A6159013425', '<OtherInfo><SSSTranNo>BCI182851A610003</SSSTranNo><SSID>3491551725</SSID><PayorType>VOLUNTARY</PayorType><PayorName>KRIS BCI MR</PayorName><From>10/01/2020</From><To>10/01/2020</To><BRN></BRN><PRN>V1018000009367</PRN></OtherInfo>', '1971A61133458', '10/12/2018', '990170.5045'),
(117, '1A61', '00189', 'SSSC1', '3491551725', '220.00', '0.00', '220.00', '<PayFor>PRN</PayFor><PRN>N1018000009375</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:43:05', '990170.5045', '1A61182851014244', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182851A6191014244', '<OtherInfo><SSSTranNo>BCI182851A610004</SSSTranNo><SSID>3491551725</SSID><PayorType>NON-WORKING SPOUSE</PayorType><PayorName>KRIS BCI MR</PayorName><From>11/01/2020</From><To>11/01/2020</To><BRN></BRN><PRN>N1018000009375</PRN></OtherInfo>', '2521A61134324', '10/12/2018', '989950.5045'),
(118, '1A61', '00189', 'SSSC1', '3491551725', '220.00', '0.00', '220.00', '<PayFor>PRN</PayFor><PRN>N1018000009375</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:43:19', '990170.5045', '1A61182853014249', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(119, '1A61', '00189', 'SSSC1', '3491551725', '550.00', '0.00', '550.00', '<PayFor>PRN</PayFor><PRN>O1018000009376</PRN><CountryCode>PHL</CountryCode>', NULL, '10-12-2018 13:49:30', '989950.5045', '1A61182857014925', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182851A6167014925', '<OtherInfo><SSSTranNo>BCI182851A610005</SSSTranNo><SSID>3491551725</SSID><PayorType>OFW</PayorType><PayorName>KRIS BCI MR</PayorName><From>12/01/2020</From><To>12/01/2020</To><BRN></BRN><PRN>O1018000009376</PRN></OtherInfo>', '5821A61134956', '10/12/2018', '989400.5045'),
(120, '1A61', '00082', 'PWCOR', '01002386001016', '100.00', '5.00', '105.00', '<AccountName>Chris Cruz</AccountName><BillNo>12345</BillNo>', NULL, '10-16-2018 09:51:31', '989400.5045', '1A61182893095126', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182891A6173095126', NULL, '1411A61095220', '10/16/2018', '989295.5045'),
(121, '1A61', '00082', 'PWCOR', '01002386001016', '100.00', '5.00', '105.00', '<AccountName>Chris Cruz</AccountName><BillNo>12345</BillNo>', NULL, '10-16-2018 09:54:57', '989295.5045', '1A61182894095451', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '182891A6154095451', NULL, '1411A61101056', '10/16/2018', '989190.5045'),
(122, '1A61', '00161', 'BPWWI', 'ttgy6yy', '55.00', '5.00', '60.00', '<AccountName>yyg</AccountName><DueDate>10/16/2018</DueDate><TypeOfService>WB</TypeOfService><DisconnectionDate>10/16/2018</DisconnectionDate>', NULL, '10-16-2018 16:25:11', '989190.5045', '1A61182896042506', 'samsung', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(123, '1A75', '00022', 'MWSIN', '55653219', '190.50', '0.00', '190.50', 'none', NULL, '10-17-2018 09:59:59', '989190.5045', '1A75182905095954', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182901A7505095954', NULL, '2271A75100013', '10/17/2018', '989000.0045'),
(124, '1A61', '00114', 'DVOLT', '23136100007', '9000.00', '0.00', '9000.00', '<FirstName>rest</FirstName><LastName>tet</LastName>', NULL, '10-17-2018 10:01:12', '989000.0045', '1A61182905100107', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182901A6185100107', NULL, '9371A61100123', '10/17/2018', '980000.0045'),
(125, '1A61', '00089', 'ASVCA', '123456', '74.00', '8.00', '82.00', '<AccountName>test</AccountName><AffiliateBranch>ASVCA1</AffiliateBranch>', NULL, '10-18-2018 10:37:07', '980165.0045', '1A61182912103703', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(126, '1A61', '00161', 'BPWWI', '135jhu', '63.00', '5.00', '68.00', '<AccountName>u</AccountName><DueDate>10/18/2018</DueDate><TypeOfService>WB</TypeOfService><DisconnectionDate>10/18/2018</DisconnectionDate>', NULL, '10-18-2018 10:38:21', '980165.0045', '1A61182918103817', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(127, '1A61', '00112', 'PHLTH', '020511097682', '300.00', '8.00', '308.00', '<PaymentType>null</PaymentType><MemberType>SEP</MemberType><FirstName>eliza</FirstName><LastName>rosario</LastName><MI></MI><BillDate>10/18/2018</BillDate><DueDate>10/18/2018</DueDate>', NULL, '10-18-2018 13:43:21', '980165.0045', '1A61182918014300', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(128, '1A61', '00112', 'PHLTH', '020511097682', '300.00', '8.00', '308.00', '<PaymentType>null</PaymentType><MemberType>SEP</MemberType><FirstName>eliza</FirstName><LastName>rosario</LastName><MI></MI><BillDate>10/18/2018</BillDate><DueDate>10/18/2018</DueDate>', NULL, '10-18-2018 13:43:21', '980165.0045', '1A61182919014315', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(129, '1A61', '00112', 'PHLTH', '020511097682', '200.00', '8.00', '208.00', '<PaymentType>null</PaymentType><MemberType>INP</MemberType><FirstName>eliza</FirstName><LastName>test</LastName><MI></MI><BillDate>10/18/2018</BillDate><DueDate>10/18/2018</DueDate>', NULL, '10-18-2018 13:45:15', '980165.0045', '1A61182916014510', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182911A6186014510', '<OtherInfo><PHLTHSeqNo>1A6110181800005</PHLTHSeqNo></OtherInfo>', '2461A61134531', '10/18/2018', '979957.0045'),
(130, '1A61', '00114', 'DVOLT', '23136100007', '444.00', '0.00', '444.00', '<FirstName>s</FirstName><LastName>s</LastName>', NULL, '11-12-2018 10:11:02', '980917.0045', '1A61183162101058', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(131, '1A61', '00136', 'HDMF1', '315000053273', '10.00', '7.00', '17.00', '<PaymentType>HL</PaymentType><ContactNo>12345678901</ContactNo><BillDate>2018,01</BillDate><DueDate>2018,01</DueDate>', NULL, '11-12-2018 15:13:24', '980917.0045', '1A61183161031315', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(132, '1A61', '00136', 'HDMF1', '190000000045', '100.00', '7.00', '107.00', '<PaymentType>MC</PaymentType><ContactNo>12345678901</ContactNo><BillDate>2018,11</BillDate><DueDate>2018,11</DueDate>', NULL, '11-12-2018 15:16:10', '980917.0045', '1A61183163031545', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(133, '1A61', '00136', 'HDMF1', '15316001947070152170', '100.00', '7.00', '107.00', '<PaymentType>HL</PaymentType><ContactNo>09123456789</ContactNo><BillDate>2018,11</BillDate><DueDate>2018,11</DueDate>', NULL, '11-13-2018 12:43:31', '980917.0045', '1A61183177124319', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(134, '1A61', '00136', 'HDMF1', '190000000045', '100.00', '7.00', '107.00', '<PaymentType>MC</PaymentType><ContactNo>09123456789</ContactNo><BillDate>2018,11</BillDate><DueDate>2018,11</DueDate>', NULL, '11-13-2018 12:49:45', '980917.0045', '1A61183173124934', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '183171A6183124934', '<OtherInfo><HDMFTranNo>183171A610002</HDMFTranNo><AccountName>MONLEON,LETICIA,ACEBEDO</AccountName></OtherInfo>', '1401A61125011', '11/13/2018', '980810.0045'),
(135, '1A61', '00136', 'HDMF1', '140000000113', '100.00', '7.00', '107.00', '<PaymentType>MC</PaymentType><ContactNo>09123456789</ContactNo><BillDate>2018,10</BillDate><DueDate>2018,10</DueDate>', NULL, '11-13-2018 12:55:37', '980810.0045', '1A61183176125532', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '183171A6166125532', '<OtherInfo><HDMFTranNo>183171A610003</HDMFTranNo><AccountName>PARENA,RODELIO,REYES</AccountName></OtherInfo>', '1401A61125606', '11/13/2018', '980703.0045'),
(136, '1A61', '00136', 'HDMF1', '315000004713', '100.00', '7.00', '107.00', '<PaymentType>HL</PaymentType><ContactNo>09123456789</ContactNo><BillDate>2018,11</BillDate><DueDate>2018,11</DueDate>', NULL, '11-13-2018 13:01:04', '980703.0045', '1A61183178010052', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '183171A6108010052', '<OtherInfo><HDMFTranNo>183171A610004</HDMFTranNo><AccountName>MARQUEZ,RAUL,FU</AccountName></OtherInfo>', '1401A61130133', '11/13/2018', '980596.0045'),
(137, '1A61', '00136', 'HDMF1', '15316001947070152170', '100.00', '7.00', '107.00', '<PaymentType>HL</PaymentType><ContactNo>09123456789</ContactNo><BillDate>2018,01</BillDate><DueDate>2018,01</DueDate>', NULL, '11-13-2018 13:03:56', '980596.0045', '1A61183170010345', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '183171A6100010345', '<OtherInfo><HDMFTranNo>183171A610005</HDMFTranNo><AccountName>MARTILLANO,SHIELA,LABRADOR</AccountName></OtherInfo>', '1401A61130418', '11/13/2018', '980489.0045'),
(138, '1A61', '00288', 'AECOR', '3216549000484341', '50.00', '0.00', '50.00', '<CustomerName></CustomerName><DueDate>11/30/2018</DueDate>', NULL, '11-28-2018 11:36:53', '980489.0045', '1A61183329113648', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6169113648', NULL, '891A61113709', '11/28/2018', '980439.0045'),
(139, '1A61', '00234', 'ANTEC', '11111111112', '1500.78', '0.00', '1500.78', '<AccountName>test</AccountName><DueDate>11/28/2018</DueDate><BillMonth>11/2018</BillMonth>', NULL, '11-28-2018 13:51:02', '980439.0045', '1A61183321015057', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6191015057', NULL, '1391A61135116', '11/28/2018', '978938.2245'),
(140, '1A61', '00249', 'CELCO', '1111111111', '658.74', '10.00', '668.74', '<ContactNumber>09392580863</ContactNumber><DueDate>11/28/2018</DueDate><BillingPeriod>11/2018</BillingPeriod>', NULL, '11-28-2018 13:56:31', '978938.2245', '1A61183324015626', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6114015626', NULL, '7071A61135645', '11/28/2018', '978269.4845'),
(141, '1A61', '00263', 'ISLC1', '1234567890', '6500.00', '0.00', '6500.00', '<ConsumerName>tesr</ConsumerName><DueDate>11/28/2018</DueDate><BillMonth>11/2018</BillMonth>', NULL, '11-28-2018 14:01:36', '978269.4845', '1A61183320020131', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6100020131', NULL, '6391A61140154', '11/28/2018', '971769.4845'),
(142, '1A61', '00285', 'ISLC2', '1234680962', '1000.00', '0.00', '1000.00', '<DueDate>11/28/2018</DueDate><ConsumerName>tesr</ConsumerName>', NULL, '11-28-2018 14:07:19', '971769.4845', '1A61183329020715', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6109020715', NULL, '1391A61140733', '11/28/2018', '970769.4845'),
(143, '1A61', '00261', 'SJEC1', '123456789a', '269.00', '10.00', '279.00', '<AccountName>teshn</AccountName><BillMonth>11/2018</BillMonth><DueDate>11/28/2018</DueDate>', NULL, '11-28-2018 14:20:51', '970769.4845', '1A61183329022046', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6179022046', NULL, '3181A61142105', '11/28/2018', '970490.4845'),
(144, '1A61', '00258', 'BCWD1', '135799924', '358.00', '5.00', '363.00', '<MeterNo>218881234</MeterNo><DueDate>11/28/2018</DueDate><AccountName>testubf</AccountName>', NULL, '11-28-2018 14:24:29', '970490.4845', '1A61183326022425', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6156022425', NULL, '4021A61142443', '11/28/2018', '970127.4845');
INSERT INTO `tb_transaction` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(145, '1A61', '00212', 'CDOWD', '074A122040', '380.00', '9.00', '389.00', '<WIN>002995062</WIN><DueDate>11/28/2018</DueDate><AccountName>ghj</AccountName>', NULL, '11-28-2018 14:28:34', '970127.4845', '1A61183323022829', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183321A6193022829', NULL, '4281A61142846', '11/28/2018', '969738.4845'),
(146, '1A61', '00305', 'CRMWD', '202101004', '250.00', '0.00', '250.00', '<AccountName>test</AccountName><DueDate>11/30/2018</DueDate>', NULL, '11-29-2018 09:26:57', '969738.4845', '1A61183334092652', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6144092652', NULL, '2901A61092712', '11/29/2018', '969488.4845'),
(147, '1A61', '00268', 'DWD01', '1162442101', '1000.00', '8.00', '1008.00', '<Name>testubg</Name><DueDate>11/29/2018</DueDate>', NULL, '11-29-2018 09:38:45', '969488.4845', '1A61183331093840', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6141093840', NULL, '1481A61093901', '11/29/2018', '968480.4845'),
(148, '1A61', '00246', 'MABWD', '3011111', '2807.00', '0.00', '2807.00', '<AccountName>test</AccountName>', NULL, '11-29-2018 09:40:36', '968480.4845', '1A61183331094031', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6101094031', NULL, '2471A61094057', '11/29/2018', '965673.4845'),
(149, '1A61', '00224', 'MALWD', '200201002', '639.10', '0.00', '639.10', 'none', NULL, '11-29-2018 09:45:53', '965673.4845', '1A61183334094546', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(150, '1A61', '00224', 'MALWD', '200201002', '639.10', '0.00', '639.10', 'none', NULL, '11-29-2018 09:46:04', '965673.4845', '1A61183332094553', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(151, '1A61', '00224', 'MALWD', '200201002', '639.10', '0.00', '639.10', 'none', NULL, '11-29-2018 09:53:57', '965673.4845', '1A61183337095352', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6167095352', NULL, '6791A61095409', '11/29/2018', '965034.3845'),
(152, '1A61', '00171', 'SILWD', '02131105912', '650.00', '8.00', '658.00', '<BillNo>3126846</BillNo><DueDate>12/04/2018</DueDate><BillMonth>January</BillMonth>', NULL, '11-29-2018 10:04:33', '965034.3845', '1A61183332100429', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6122100429', NULL, '6981A61100454', '11/29/2018', '964376.3845'),
(153, '1A61', '00216', 'TCWD1', '012-98459', '500.00', '0.00', '500.00', '<AccountName>tsdf</AccountName><DueDate>11/29/2018</DueDate><BillAmount>500</BillAmount><BeforeDue>500</BeforeDue>', NULL, '11-29-2018 10:11:07', '964376.3845', '1A61183339101103', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6129101103', NULL, '5401A61101119', '11/29/2018', '963876.3845'),
(154, '1A61', '00226', 'TWDIS', 'qryi67', '380.00', '0.00', '380.00', '<Name>uuu</Name><DueDate>11/29/2018</DueDate>', NULL, '11-29-2018 10:20:27', '963876.3845', '1A61183330102022', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6140102022', NULL, '4201A61102425', '11/29/2018', '963496.3845'),
(155, '1A61', '00244', 'LARC1', '12345678', '680.45', '0.00', '680.45', '<AccountName>tsdf</AccountName><DueDate>11/30/2018</DueDate>', NULL, '11-29-2018 10:32:26', '963496.3845', '1A61183337103221', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6117103221', NULL, '7201A61103238', '11/29/2018', '962815.9345'),
(156, '1A61', '00247', 'LCWD1', '031122314', '380.00', '10.00', '390.00', '<AccountName>yrdy</AccountName><BillMonth>112018</BillMonth>', NULL, '11-29-2018 10:49:15', '962815.9345', '1A61183332104911', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6132104911', NULL, '4301A61104927', '11/29/2018', '962425.9345'),
(157, '1A61', '00303', 'CARWD', '114566', '36.00', '0.00', '36.00', '<AccountName>tedt</AccountName><DueDate>11/29/2018</DueDate><BillAmount>36</BillAmount>', NULL, '11-29-2018 10:53:12', '962425.9345', '1A61183332105308', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6102105308', NULL, '761A61105325', '11/29/2018', '962389.9345'),
(158, '1A61', '00314', 'MAREC', '0101535226', '300.00', '0.00', '300.00', '<DueDate>11/29/2018</DueDate><BillMonth>112018</BillMonth><AccountName>tedr</AccountName><TotalPayableAmount>300.00</TotalPayableAmount><Surcharge></Surcharge>', NULL, '11-29-2018 11:00:48', '962389.9345', '1A61183334110044', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6114110044', NULL, '3401A61110100', '11/29/2018', '962089.9345'),
(159, '1A61', '00317', 'LUELC', '5153210409', '688.00', '0.00', '688.00', '<AccountName>twdt</AccountName><BillNumber>20181053210409</BillNumber><DueDate>11/29/2018</DueDate>', NULL, '11-29-2018 11:13:49', '962089.9345', '1A61183332111344', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '183331A6102111344', NULL, '7281A61111401', '11/29/2018', '961401.9345'),
(160, '1A61', '00227', 'INSTA', '-', '720.00', '0.00', '720.00', '<LastName>Lapida</LastName>\n				<FirstName>Lito</FirstName>\n				<BirthDate>12/06/1998</BirthDate>\n				<MobileNo>639176800230</MobileNo>\n				<CustomerAddress>Nakatago St., Dimakita City</CustomerAddress>\n				<NoOfPolicy>4</NoOfPolicy>\n				<ApplicableMonths>12</ApplicableMonths>\n				<MedReim>1</MedReim>', NULL, '01-16-2019 17:21:32', '941,000', '1A61190164052115', 'DUKE', '1', 0, 'Transaction Successful.', '190161A614052115', '<OtherInfo><DateFrom>05/22/2021</DateFrom><DateTo>05/22/2022</DateTo></OtherInfo>', '7571A61172115', '1/16/2019 ', '959961.9345'),
(161, '1A61', '00227', 'INSTA', '-', '720.00', '0.00', '720.00', '<LastName>Lapida</LastName>\n				<FirstName>Lito</FirstName>\n				<BirthDate>12/06/1998</BirthDate>\n				<MobileNo>639176800230</MobileNo>\n				<CustomerAddress>Nakatago St., Dimakita City</CustomerAddress>\n				<NoOfPolicy>4</NoOfPolicy>\n				<ApplicableMonths>12</ApplicableMonths>\n				<MedReim>1</MedReim>', NULL, '01-16-2019 17:22:26', '941,000', '1A61190167052211', 'DUKE', '1', 0, 'Transaction Successful.', '190161A617052211', '<OtherInfo><DateFrom>05/23/2022</DateFrom><DateTo>05/23/2023</DateTo></OtherInfo>', '7571A61172211', '1/16/2019 ', '959241.9345'),
(162, '1A61', '00227', 'INSTA', '-', '10.00', '0.00', '10', '<FirstName>t</FirstName><LastName>t</LastName><CustomerAddress>t</CustomerAddress><BirthDate>07/18/1992</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>1</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '01-17-2019 10:34:54', '959241.9345', '1A61190175103425', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190171A615103425', '<OtherInfo><DateFrom>01/17/2019</DateFrom><DateTo>02/17/2019</DateTo></OtherInfo>', '481A61103425', '1/17/2019 ', '959231.9345'),
(163, '1A61', '00227', 'INSTA', '-', '120.00', '0.00', '120', '<FirstName>test</FirstName><LastName>tes</LastName><CustomerAddress>caloocan</CustomerAddress><BirthDate>07/19/1994</BirthDate><MobileNo>639392580066</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '01-17-2019 10:41:42', '959231.9345', '1A61190176104124', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190171A616104124', '<OtherInfo><DateFrom>01/17/2019</DateFrom><DateTo>01/17/2020</DateTo></OtherInfo>', '1581A61104124', '1/17/2019 ', '0.00'),
(164, '1A61', '00227', 'INSTA', '-', '480.00', '0.00', '480', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 123 antipolo city</CustomerAddress><BirthDate>01/22/2001</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-22-2019 16:24:26', '959111.9345', '1A61190224042410', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190221A614042410', '<OtherInfo><DateFrom>01/22/2019</DateFrom><DateTo>01/22/2020</DateTo></OtherInfo>', '5141A61162410', '1/22/2019 ', '958631.9345'),
(165, '1A61', '00227', 'INSTA', '-', '480.00', '0.00', '480', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 123 antipolo city</CustomerAddress><BirthDate>01/22/1954</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-22-2019 16:51:22', '958631.9345', '1A61190223045109', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190221A613045109', '<OtherInfo><DateFrom>01/22/2019</DateFrom><DateTo>01/22/2020</DateTo></OtherInfo>', '5141A61165109', '1/22/2019 ', '958151.9345'),
(166, '1A61', '00227', 'INSTA', '-', '720.00', '0.00', '720', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 456 antipolo city</CustomerAddress><BirthDate>12/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-22-2019 16:55:13', '958151.9345', '1A61190222045456', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190221A612045456', '<OtherInfo><DateFrom>01/22/2019</DateFrom><DateTo>01/22/2020</DateTo></OtherInfo>', '7541A61165456', '1/22/2019 ', '957431.9345'),
(167, '1A61', '00227', 'INSTA', '-', '720.00', '0.00', '720', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 456 antipolo city</CustomerAddress><BirthDate>12/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-22-2019 16:57:17', '957431.9345', '1A61190222045702', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190221A612045702', '<OtherInfo><DateFrom>01/23/2020</DateFrom><DateTo>01/23/2021</DateTo></OtherInfo>', '7541A61165702', '1/22/2019 ', '956711.9345'),
(168, '1A61', '00227', 'INSTA', '-', '60.00', '0.00', '60', '<FirstName>test</FirstName><LastName>tesy</LastName><CustomerAddress>tesibg</CustomerAddress><BirthDate>07/12/1990</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>02</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>2</NoOfPolicy>', NULL, '01-23-2019 10:13:14', '956711.9345', '1A61190235101300', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A615101300', '<OtherInfo><DateFrom>01/23/2019</DateFrom><DateTo>03/23/2019</DateTo></OtherInfo>', '951A61101300', '1/23/2019 ', '956591.9345'),
(169, '1A61', '00227', 'INSTA', '-', '60.00', '0.00', '60', '<FirstName>test</FirstName><LastName>tesy</LastName><CustomerAddress>tesibg</CustomerAddress><BirthDate>07/12/1990</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>02</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>2</NoOfPolicy>', NULL, '01-23-2019 10:13:14', '956711.9345', '1A61190234101301', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A614101301', '<OtherInfo><DateFrom>03/24/2019</DateFrom><DateTo>05/24/2019</DateTo></OtherInfo>', '951A61101301', '1/23/2019 ', '956591.9345'),
(170, '1A61', '00227', 'INSTA', '-', '80.00', '0.00', '80', '<FirstName>ty</FirstName><LastName>op</LastName><CustomerAddress>tikn</CustomerAddress><BirthDate>07/7/1998</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>08</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '01-23-2019 10:24:23', '956591.9345', '1A61190239102410', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A619102410', '<OtherInfo><DateFrom>01/23/2019</DateFrom><DateTo>09/23/2019</DateTo></OtherInfo>', '1151A61102410', '1/23/2019 ', '956511.9345'),
(171, '1A61', '00227', 'INSTA', '-', '480.00', '0.00', '480', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 123 antipolo city</CustomerAddress><BirthDate>12/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-23-2019 15:13:23', '956511.9345', '1A61190230031309', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A610031309', '<OtherInfo><DateFrom>01/23/2019</DateFrom><DateTo>01/23/2020</DateTo></OtherInfo>', '5151A61151309', '1/23/2019 ', '956031.9345'),
(172, '1A61', '00227', 'INSTA', '-', '480.00', '0.00', '480', '<FirstName>Chris</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 123 antipolo city</CustomerAddress><BirthDate>12/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-23-2019 15:18:47', '956031.9345', '1A61190239031830', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A619031830', '<OtherInfo><DateFrom>01/24/2020</DateFrom><DateTo>01/24/2021</DateTo></OtherInfo>', '5151A61151830', '1/23/2019 ', '955551.9345'),
(173, '1A61', '00227', 'INSTA', '-', '720.00', '0.00', '720', '<FirstName>Jane</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 456 pasig city</CustomerAddress><BirthDate>11/12/1990</BirthDate><MobileNo>639987654321</MobileNo><ApplicableMonths>12</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>4</NoOfPolicy>', NULL, '01-23-2019 15:48:58', '955551.9345', '1A61190239034845', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A619034845', '<OtherInfo><DateFrom>01/23/2019</DateFrom><DateTo>01/23/2020</DateTo></OtherInfo>', '7551A61154845', '1/23/2019 ', '954831.9345'),
(174, '1A61', '00227', 'INSTA', '-', '15.00', '0.00', '15', '<FirstName>Jesrel</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 789 antipolo city</CustomerAddress><BirthDate>10/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>01</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '01-23-2019 15:54:28', '954831.9345', '1A61190236035414', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A616035414', '<OtherInfo><DateFrom>01/23/2019</DateFrom><DateTo>02/23/2019</DateTo></OtherInfo>', '501A61155414', '1/23/2019 ', '954816.9345'),
(175, '1A61', '00227', 'INSTA', '-', '15.00', '0.00', '15', '<FirstName>Jesrel</FirstName><LastName>Cruz</LastName><CustomerAddress>Blk 789 antipolo city</CustomerAddress><BirthDate>10/21/1990</BirthDate><MobileNo>639123456789</MobileNo><ApplicableMonths>01</ApplicableMonths><MedReim>1</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '01-23-2019 15:56:46', '954816.9345', '1A61190238035632', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction Successful.', '190231A618035632', '<OtherInfo><DateFrom>02/24/2019</DateFrom><DateTo>03/24/2019</DateTo></OtherInfo>', '501A61155632', '1/23/2019 ', '954801.9345'),
(176, '1A61', '0000', 'GLOBE', '09271234567', '50.00', '0.00', '50.00', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>GLOBE</Telco><MobileNo>09271234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '02-15-2019 16:00:17', '1000', '1A61190466040011', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, NULL),
(177, '1A61', '0000', 'GLOBE', '639569739617', '10.00', '0.00', '10.00', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>GLOBE</Telco><MobileNo>639569739617</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '02-21-2019 09:45:53', '954658.5945', '190523094512', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, NULL),
(178, '1A61', '0000', 'SMART', '63932580863', '20', '0.00', '20', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>63932580863</MobileNo><ProductCode>LAHATTXT20</ProductCode></OtherInfo>', NULL, '02-21-2019 10:05:27', '954658.5945', '190520100520', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954658.5945'),
(179, '1A61', '0000', 'SMART', '639392580863', '20', '0.00', '20', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639392580863</MobileNo><ProductCode>LAHATTXT20</ProductCode></OtherInfo>', NULL, '02-21-2019 10:06:11', '954658.5945', '190524100600', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954658.5945'),
(180, '1A61', '0000', 'SMART', '639392580863', '50', '0.00', '50', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639392580863</MobileNo><ProductCode>WGIGA50</ProductCode></OtherInfo>', NULL, '02-21-2019 10:08:17', '954658.5945', '190527100806', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954658.5945'),
(181, '1A61', '0000', 'SMART', '639392580863', '50', '0.00', '50', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639392580863</MobileNo><ProductCode>WGIGA50</ProductCode></OtherInfo>', NULL, '02-21-2019 10:09:28', '954658.5945', '190523100908', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954658.5945'),
(182, '1A61', '0000', 'SMART', '639394635867', '30', '0.00', '30', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>WU30</ProductCode></OtherInfo>', NULL, '02-21-2019 10:21:16', '954658.5945', '190526102047', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954658.5945'),
(183, '1A61', '0000', 'SMART', '639394635867', '20', '0.00', '20', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>LAHATTXT20</ProductCode></OtherInfo>', NULL, '02-21-2019 11:18:14', '954649.0385', '190524111802', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954629.9265'),
(184, '1A61', '0000', 'SMART', '639394635867', '60', '0.00', '60', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>SMARTLOAD</ProductCode></OtherInfo>', NULL, '02-21-2019 11:22:06', '954629.9265', '190527112154', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954572.5905'),
(185, '1A61', '0000', 'SMART', '639394635867', '10.00', '0.00', '10.00', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>SMARTLOAD</ProductCode></OtherInfo>', NULL, '02-21-2019 11:25:14', '954572.5905', '190527112505', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954563.0345'),
(186, '1A61', '0000', 'SMART', '639394635867', '10', '0.00', '10', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>SMARTLOAD</ProductCode></OtherInfo>', NULL, '02-21-2019 11:26:32', '954563.0345', '190527112623', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954553.4785'),
(187, '1A61', '0000', 'SMART', '639394635867', '10', '0.00', '10', '<OtherInfo><BCServices>E-Loading</BCServices><Telco>SMART</Telco><MobileNo>639394635867</MobileNo><ProductCode>SMARTLOAD</ProductCode></OtherInfo>', NULL, '02-21-2019 11:28:08', '954553.4785', '190526112645', '', '', NULL, 'Loading Unsuccessful', NULL, NULL, NULL, NULL, '954543.9225'),
(188, '1A61', '00022', 'MWSIN', '55653219', '200.00', '0.00', '200.00', 'none', NULL, '02-26-2019 13:29:29', '951815.6845', '1A61190571012922', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190571A6161012922', NULL, '2381A61132948', '02/26/2019', '951615.6845'),
(189, '1A61', '00015', 'MWCOM', '18951519', '206.00', '0.00', '206.00', 'none', NULL, '02-26-2019 13:37:50', '951615.6845', '1A61190578013744', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190571A6178013744', NULL, '2441A61133817', '02/26/2019', '951409.6845'),
(190, '1A61', '00015', 'MWCOM', '18951519', '409.68', '0.00', '409.68', 'none', NULL, '02-26-2019 13:43:22', '951409.6845', '1A61190577014317', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190571A6117014317', NULL, '4471A61134334', '02/26/2019', '951000.0045'),
(191, '1A61', '00022', 'MWSIN', '55653219', '1000.00', '0.00', '1000.00', 'none', NULL, '02-26-2019 13:49:02', '951000.0045', '1A61190576014857', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190571A6146014857', NULL, '1381A61134914', '02/26/2019', '950000.0045'),
(192, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '0.00', '18.00', '<CustomerName>Chris Cruz</CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 10:20:22', '949937.8905', '1A61190671102016', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(193, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '10.00', '28.00', '<CustomerName>Chria Cruz</CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 14:11:30', '949937.8905', '1A61190675021124', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(194, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '10.00', '28.00', '<CustomerName></CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 14:14:17', '949937.8905', '1A61190675021411', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(195, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '10.00', '28.00', '<CustomerName></CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 14:17:18', '949937.8905', '1A61190672021709', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(196, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '10.00', '28.00', '<CustomerName>Chris Cruz</CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 14:43:43', '949937.8905', '1A61190678024336', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190671A6198024336', NULL, '661A61144457', '03/08/2019', '949909.8905'),
(197, '1A61', '00288', 'AECOR', '2365863900453441', '18.00', '10.00', '28.00', '<CustomerName>Chris Cruz</CustomerName><DueDate>03/20/2019</DueDate>', NULL, '03-08-2019 15:01:32', '949909.8905', '1A61190672030125', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190671A6142030125', NULL, '661A61150204', '03/08/2019', '949881.8905'),
(198, '1A61', '00234', 'ANTEC', '12345678901', '100.00', '12.00', '112.00', '<AccountName>Chris Cruz</AccountName><DueDate>03/08/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-08-2019 16:49:16', '949881.8905', '1A61190672044910', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(199, '1A61', '00234', 'ANTEC', '02022300104', '100.00', '12.00', '112.00', '<AccountName>Chris Cruz</AccountName><DueDate>03/08/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-08-2019 17:18:25', '949881.8905', '1A61190674051820', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190671A6194051820', NULL, '1501A61171846', '03/08/2019', '949769.8905'),
(200, '1A61', '00249', 'CELCO', '1234567890', '100.00', '10.00', '110.00', '<ContactNumber>09123456789</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:00:52', '949769.8905', '1A61190704110038', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(201, '1A61', '00249', 'CELCO', '111111AAAA', '100.00', '10.00', '110.00', '<ContactNumber>09123456789</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:02:43', '949769.8905', '1A61190709110233', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(202, '1A61', '00249', 'CELCO', 'AAAAAAAAAA', '100.00', '10.00', '110.00', '<ContactNumber>09123456789</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:03:47', '949769.8905', '1A61190704110340', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(203, '1A61', '00249', 'CELCO', 'AAAAAAAAAA', '100.00', '10.00', '110.00', '<ContactNumber>09123456789</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:03:53', '949769.8905', '1A61190709110345', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(204, '1A61', '00249', 'CELCO', 'AAAAAAAAAA', '100.00', '10.00', '110.00', '<ContactNumber>12345678901</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:05:07', '949769.8905', '1A61190705110459', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(205, '1A61', '00249', 'CELCO', 'AAAAAAAAAA', '100.00', '10.00', '110.00', '<ContactNumber>12345678901</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 11:34:41', '949769.8905', '1A61190708113434', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(206, '1A61', '00249', 'CELCO', '0534567890', '100.00', '10.00', '110.00', '<ContactNumber>12345678901</ContactNumber><DueDate>03/11/2019</DueDate><BillingPeriod>03/2019</BillingPeriod>', NULL, '03-11-2019 13:27:44', '949769.8905', '1A61190706012738', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190701A6116012738', NULL, '1421A61132808', '03/11/2019', '949659.8905'),
(207, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>A03/2019</BillMonth>', NULL, '03-11-2019 14:03:58', '949659.8905', '1A61190703020342', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(208, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>1Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-11-2019 14:06:05', '949659.8905', '1A61190701020600', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(209, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>123Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-11-2019 14:07:06', '949659.8905', '1A61190704020701', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(210, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>?!Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-11-2019 14:08:20', '949659.8905', '1A61190708020813', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(211, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>?!Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-11-2019 14:12:08', '949659.8905', '1A61190708021202', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(212, '1A61', '00263', 'ISLC1', '1234567890', '100.00', '7.00', '107.00', '<ConsumerName>Chris Cruz</ConsumerName><DueDate>03/11/2019</DueDate><BillMonth>03/2019</BillMonth>', NULL, '03-11-2019 14:14:14', '949659.8905', '1A61190703021409', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190701A6133021409', NULL, '1391A61141433', '03/11/2019', '949552.8905'),
(213, '1A61', '00285', 'ISLC2', '2002010030', '100.00', '10.00', '110.00', '<DueDate>03/11/2019</DueDate><ConsumerName>123Chris Cruz</ConsumerName>', NULL, '03-11-2019 16:01:59', '949552.8905', '1A61190704040154', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(214, '1A61', '00285', 'ISLC2', '2002010030', '100.00', '10.00', '110.00', '<DueDate>03/11/2019</DueDate><ConsumerName>12345</ConsumerName>', NULL, '03-11-2019 16:03:08', '949552.8905', '1A61190700040303', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(215, '1A61', '00285', 'ISLC2', '2002010030', '200.00', '10.00', '210.00', '<DueDate>03/11/2019</DueDate><ConsumerName>Chris Cruz</ConsumerName>', NULL, '03-11-2019 16:11:27', '949552.8905', '1A61190703041122', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(216, '1A61', '00285', 'ISLC2', '2002010030', '100.00', '10.00', '110.00', '<DueDate>03/11/2019</DueDate><ConsumerName>Chris Cruz</ConsumerName>', NULL, '03-11-2019 16:21:56', '949552.8905', '1A61190701042148', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190701A6131042148', NULL, '1421A61162228', '03/11/2019', '949442.8905'),
(217, '1A61', '00261', 'SJEC1', '1234567890', '100.00', '10.00', '110.00', '<AccountName>Chris Cruz</AccountName><BillMonth>03/2019</BillMonth><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 10:19:26', '949442.8905', '1A61190711101921', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(218, '1A61', '00261', 'SJEC1', '111111AAAA', '100.00', '10.00', '110.00', '<AccountName>Chris Cruz</AccountName><BillMonth>03/2019</BillMonth><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 10:20:42', '949442.8905', '1A61190712102037', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(219, '1A61', '00261', 'SJEC1', 'AAAAAAAAAA', '100.00', '10.00', '110.00', '<AccountName>Chris Cruz</AccountName><BillMonth>03/2019</BillMonth><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 10:21:38', '949442.8905', '1A61190719102130', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(220, '1A61', '00261', 'SJEC1', '0101156975', '100.00', '10.00', '110.00', '<AccountName>Chris Cruz</AccountName><BillMonth>03/2019</BillMonth><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 10:34:02', '949442.8905', '1A61190712103357', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190711A6182103357', NULL, '1431A61103508', '03/12/2019', '949332.8905'),
(221, '1A61', '00305', 'CRMWD', '202101004', '100.00', '8.00', '108.00', '<AccountName>Chris Cruz</AccountName><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 14:43:31', '949332.8905', '1A61190713024324', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(222, '1A61', '00305', 'CRMWD', '1234567890', '100.00', '8.00', '108.00', '<AccountName>Chris Cruz</AccountName><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 14:52:10', '949332.8905', '1A61190712025204', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(223, '1A61', '00305', 'CRMWD', '202101004', '100.00', '8.00', '108.00', '<AccountName>123Chris Cruz</AccountName><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 14:56:17', '949332.8905', '1A61190719025612', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(224, '1A61', '00305', 'CRMWD', '202101004', '100.00', '8.00', '108.00', '<AccountName>Chris Cruz</AccountName><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 15:48:57', '949332.8905', '1A61190713034848', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190711A6143034848', NULL, '1411A61154921', '03/12/2019', '949224.8905'),
(225, '1A61', '00268', 'DWD01', '1234567890', '100.00', '8.00', '108.00', '<Name>Chris Cruz</Name><DueDate>03/11/2019</DueDate>', NULL, '03-12-2019 18:05:16', '949224.8905', '1A61190714060511', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(226, '1A61', '00268', 'DWD01', '1162442101', '100.00', '8.00', '108.00', '<Name>Chris Cruz</Name><DueDate>03/12/2019</DueDate>', NULL, '03-12-2019 18:17:49', '949224.8905', '1A61190719061744', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '190711A6119061744', NULL, '1411A61181809', '03/12/2019', '949116.8905'),
(227, '1A61', '00314', 'MAREC', '0101535226', '100.00', '8.00', '108.00', '<DueDate>03/13/2019</DueDate><BillMonth>032019</BillMonth><AccountName>test</AccountName><TotalPayableAmount>100.00</TotalPayableAmount><Surcharge>105.00</Surcharge>', NULL, '03-13-2019 10:04:47', '949116.8905', '1A61190722100441', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190721A6152100441', NULL, '1421A61100510', '03/13/2019', '949008.8905'),
(228, '1A61', '00314', 'MAREC', '0101234567', '100.00', '8.00', '108.00', '<DueDate>03/13/2019</DueDate><BillMonth>032019</BillMonth><AccountName>test</AccountName><TotalPayableAmount>100.00</TotalPayableAmount><Surcharge>0.00</Surcharge>', NULL, '03-13-2019 11:11:24', '949008.8905', '1A61190727111117', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190721A6147111117', NULL, '1421A61111148', '03/13/2019', '948900.8905'),
(229, '1A61', '00314', 'MAREC', '0101234568', '100.00', '8.00', '108.00', '<DueDate>03/1/2019</DueDate><BillMonth>032019</BillMonth><AccountName>test</AccountName><TotalPayableAmount>100.00</TotalPayableAmount><Surcharge>5.00</Surcharge>', NULL, '03-13-2019 13:11:46', '948900.8905', '1A61190724011140', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190721A6104011140', NULL, '1421A61131609', '03/13/2019', '948792.8905'),
(230, '1A61', '00314', 'MAREC', '9798548095', '100.00', '8.00', '108.00', '<DueDate>03/30/2019</DueDate><BillMonth>032019</BillMonth><AccountName>tedtu</AccountName><TotalPayableAmount>100.00</TotalPayableAmount><Surcharge>0.00</Surcharge>', NULL, '03-13-2019 13:59:13', '948792.8905', '1A61190729015904', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(231, '1A61', '00314', 'MAREC', '1234567890', '105.00', '8.00', '113.00', '<DueDate>03/1/2019</DueDate><BillMonth>032019</BillMonth><AccountName>test</AccountName><TotalPayableAmount>105.0</TotalPayableAmount><Surcharge>5.00</Surcharge>', NULL, '03-15-2019 10:49:05', '948792.8905', '1A61190741104858', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190741A6161104858', NULL, '1491A61104959', '03/15/2019', '948679.8905'),
(232, '1A61', '00314', 'MAREC', '0987554433', '105.00', '8.00', '113.00', '<DueDate>03/1/2019</DueDate><BillMonth>032019</BillMonth><AccountName>tedt</AccountName><TotalPayableAmount>105.0</TotalPayableAmount><Surcharge>5.00</Surcharge>', NULL, '03-15-2019 10:54:45', '948679.8905', '1A61190744105430', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190741A6104105430', NULL, '1491A61105506', '03/15/2019', '948566.8905'),
(233, '1A61', '00227', 'INSTA', '-', '10.00', '0.00', '10', '<FirstName>Eliza vel</FirstName><LastName>rosario</LastName><CustomerAddress>caloocan</CustomerAddress><BirthDate>07/09/1992</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>01</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>1</NoOfPolicy>', NULL, '04-01-2019 11:42:29', '948566.8905', '1A61190911114212', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190911A611114212', '<OtherInfo><DateFrom>04/01/2019</DateFrom><DateTo>05/01/2019</DateTo></OtherInfo>', '411A61114212', '4/01/2019 ', '948556.8905'),
(234, '1A61', '00112', 'PHLTH', '020511097682', '300.00', '8.00', '308.00', '<PaymentType>null</PaymentType><MemberType>SEP</MemberType><FirstName>test</FirstName><LastName>testing</LastName><MI></MI><BillDate>04/01/2019</BillDate><DueDate>04/01/2019</DueDate>', NULL, '04-01-2019 16:06:32', '948556.8905', '1A61190914040624', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190911A6114040624', '<OtherInfo><PHLTHSeqNo>1A6104011900006</PHLTHSeqNo></OtherInfo>', '3391A61160748', '04/01/2019', '948248.8905'),
(235, '1A61', '00112', 'PHLTH', '020511097682', '600.00', '8.00', '608.00', '<PaymentType>Annually</PaymentType><MemberType>INP</MemberType><FirstName>test</FirstName><LastName>inp</LastName><MI></MI><BillDate>5/02/2019</BillDate><SPANumber></SPANumber><DueDate>07/01/2019</DueDate>', NULL, '04-02-2019 08:53:37', '948248.8905', '1A61190924085332', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190921A6124085332', '<OtherInfo><PHLTHSeqNo>1A6104021900007</PHLTHSeqNo></OtherInfo>', '6401A61085614', '04/02/2019', '947640.8905'),
(236, '1A61', '00112', 'PHLTH', '020511097682', '3750.00', '8.00', '3758.00', '<PaymentType></PaymentType><MemberType>PRA</MemberType><FirstName>test</FirstName><LastName>pra</LastName><MI></MI><BillDate>04/02/2019</BillDate><SPANumber>SPA456789012345</SPANumber><DueDate>06/02/2019</DueDate>', NULL, '04-02-2019 09:41:49', '947640.8905', '1A61190923094143', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190921A6163094143', '<OtherInfo><PHLTHSeqNo>1A6104021900008</PHLTHSeqNo></OtherInfo>', '3901A61094206', '04/02/2019', '943882.8905'),
(237, '1A61', '00112', 'PHLTH', '020511097682', '2400.00', '8.00', '2408.00', '<PaymentType>Annually</PaymentType><MemberType>OFW</MemberType><FirstName>test</FirstName><LastName>tesss</LastName><MI></MI><BillDate>04/02/2019</BillDate><SPANumber></SPANumber><DueDate>04/01/2020</DueDate>', NULL, '04-02-2019 10:30:34', '943882.8905', '1A61190928103026', 'HMD Global', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(238, '1A61', '00112', 'PHLTH', '020511097682', '200.00', '8.00', '208.00', '<PaymentType>null</PaymentType><MemberType>INP</MemberType><FirstName>juan</FirstName><LastName>dela cruz</LastName><MI></MI><BillDate>04/02/2019</BillDate><SPANumber></SPANumber><DueDate>04/02/2019</DueDate>', NULL, '04-02-2019 10:52:53', '943882.8905', '1A61190926105247', 'HMD Global', '121.144 - 19.114', 0, 'Transaction successful.', '190921A6186105247', '<OtherInfo><PHLTHSeqNo>1A6104021900009</PHLTHSeqNo></OtherInfo>', '2401A61105330', '04/02/2019', '943674.8905'),
(239, '1A61', '00227', 'INSTA', '-', '150.00', '0.00', '150', '<FirstName>test</FirstName><LastName>test</LastName><CustomerAddress>calooocab</CustomerAddress><BirthDate>07/09/1992</BirthDate><MobileNo>639392580863</MobileNo><ApplicableMonths>05</ApplicableMonths><MedReim>0</MedReim><NoOfPolicy>3</NoOfPolicy>', NULL, '04-04-2019 16:13:28', '943674.8905', '1A61190948041306', 'DUKE7', '121.144 - 19.114', 0, 'Transaction Successful.', '190941A618041306', '<OtherInfo><DateFrom>04/04/2019</DateFrom><DateTo>09/04/2019</DateTo></OtherInfo>', '1841A61161306', '4/04/2019 ', '943524.8905'),
(240, '1A61', '00112', 'PHLTH', '020511097682', '600.00', '8.00', '608.00', '<PaymentType></PaymentType><MemberType>SEP</MemberType><FirstName>eliza</FirstName><LastName>rosario</LastName><MI></MI><BillDate>06/08/2019</BillDate><SPANumber></SPANumber><DueDate>07/08/2019</DueDate>', NULL, '04-08-2019 16:21:10', '943524.8905', '1A61190982042104', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '190981A6152042104', '<OtherInfo><PHLTHSeqNo>1A6104081900010</PHLTHSeqNo></OtherInfo>', '6461A61162255', '04/08/2019', '942916.8905'),
(241, '1A61', '00112', 'PHLTH', '020511097682', '1400.00', '8.00', '1408.00', '<PaymentType></PaymentType><MemberType>INP</MemberType><FirstName>eliza vel</FirstName><LastName>rosario</LastName><MI></MI><BillDate>06/10/2019</BillDate><SPANumber></SPANumber><DueDate>12/10/2019</DueDate>', NULL, '04-10-2019 11:32:29', '942916.8905', '1A61191009113221', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '191001A6199113221', '<OtherInfo><PHLTHSeqNo>1A6104101900011</PHLTHSeqNo></OtherInfo>', '1391A61113532', '04/10/2019', '941508.8905'),
(242, '1A61', '00158', 'CGNAL', '9010459812', '10.00', '0.00', '10.00', '<FirstName>juan</FirstName><LastName>dela cruz</LastName><MI></MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '04-16-2019 16:57:23', '941508.8905', '1A61191060045718', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '191061A6100045718', NULL, '471A61170302', '04/16/2019', '941498.8905'),
(243, '1A61', '00015', 'MWCOM', '22222220', '100.00', '0.00', '100.00', 'none', NULL, '04-24-2019 14:36:58', '941498.8905', '1A61191144023652', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(244, '1A61', '00015', 'MWCOM', '22222220', '100.00', '0.00', '100.00', 'none', NULL, '04-24-2019 14:37:51', '941498.8905', '1A61191140023745', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '191141A6180023745', NULL, '1361A61144308', '04/24/2019', '941398.8905'),
(245, '1A61', '00015', 'MWCOM', '12358959', '20.00', '0.00', '20.00', 'none', NULL, '08-07-2019 10:55:08', '941398.8905', '1A61192198105503', 'Google', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(246, '1A61', '00015', 'MWCOM', '12358959', '20.00', '0.00', '20.00', 'none', NULL, '08-07-2019 15:20:34', '941398.8905', '1A61192199032028', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(247, '1A61', '00234', 'ANTEC', '02022300104', '100.00', '12.00', '112.00', '<AccountName>Test</AccountName><DueDate>09/08/2019</DueDate><BillMonth>09/2019</BillMonth>', NULL, '09-05-2019 13:42:01', '941398.8905', '1A61192484014157', 'samsung', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(248, '1A61', '00234', 'ANTEC', '02022300104', '200.00', '12.00', '212.00', '<AccountName>test</AccountName><DueDate>10/03/2019</DueDate><BillMonth>10/2019</BillMonth>', NULL, '10-03-2019 11:32:01', '941398.8905', '1A61192769113156', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(249, '1A61', '00234', 'ANTEC', '02022300104', '200.00', '12.00', '212.00', '<AccountName>test</AccountName><DueDate>10/03/2019</DueDate><BillMonth>10/2019</BillMonth>', NULL, '10-03-2019 11:33:20', '941398.8905', '1A61192764113314', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '192761A6184113314', NULL, '2451A61113335', '10/03/2019', '941398.8905'),
(250, '1A61', '00249', 'CELCO', '0534567890', '200.00', '10.00', '210.00', '<ContactNumber>09215553589</ContactNumber><DueDate>10/03/2019</DueDate><BillingPeriod>10/2019</BillingPeriod>', NULL, '10-03-2019 11:38:28', '941398.8905', '1A61192766113823', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '192761A6156113823', NULL, '2431A61113840', '10/03/2019', '941398.8905'),
(251, '1A61', '00263', 'ISLC1', '0987654321', '100.00', '7.00', '107.00', '<ConsumerName>Test</ConsumerName><DueDate>10/03/2019</DueDate><BillMonth>10/2019</BillMonth>', NULL, '10-03-2019 11:40:35', '941398.8905', '1A61192762114030', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '192761A6102114030', NULL, '1401A61114048', '10/03/2019', '941398.8905'),
(252, '1A61', '00285', 'ISLC2', '2002010030', '100.00', '10.00', '110.00', '<DueDate>10/03/2019</DueDate><ConsumerName>Test</ConsumerName>', NULL, '10-03-2019 11:42:51', '941398.8905', '1A61192764114246', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '192761A6154114246', NULL, '1431A61114310', '10/03/2019', '941398.8905'),
(253, '1A61', '00256', 'NVLCO', '170100546320', '1638.21', '7.00', '1645.21', '<DueDate>10/15/2019</DueDate>', NULL, '10-03-2019 11:49:39', '941398.8905', '1A61192764114934', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(254, '1A61', '00261', 'SJEC1', '0101156975', '200.00', '10.00', '210.00', '<AccountName>Test</AccountName><BillMonth>10/2019</BillMonth><DueDate>10/03/2019</DueDate>', NULL, '10-03-2019 11:56:00', '941398.8905', '1A61192760115555', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(255, '1A61', '00314', 'MAREC', '0101535226', '200.00', '8.00', '208.00', '<DueDate>10/03/2019</DueDate><BillMonth>102019</BillMonth><AccountName>test</AccountName><TotalPayableAmount>200</TotalPayableAmount><Surcharge>0.00</Surcharge>', NULL, '10-03-2019 11:58:03', '941398.8905', '1A61192761115758', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(256, '1A61', '00185', 'BLIWD', '201710-004', '100.00', '12.00', '112.00', 'none', NULL, '10-03-2019 13:39:39', '941398.8905', '1A61192760013934', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(257, '1A61', '00305', 'CRMWD', '202101004', '200.00', '8.00', '208.00', '<AccountName>Test</AccountName><DueDate>10/03/2019</DueDate>', NULL, '10-03-2019 13:45:25', '941398.8905', '1A61192766014520', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(258, '1A61', '00226', 'TWDIS', '111111', '100.00', '10.00', '110.00', '<Name>Test</Name><DueDate>10/03/2019</DueDate>', NULL, '10-03-2019 14:04:20', '941398.8905', '1A61192766020411', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(259, '1A61', '00244', 'LARC1', '61112001', '100.00', '7.00', '107.00', '<AccountName>Test</AccountName><DueDate>10/03/2019</DueDate>', NULL, '10-03-2019 14:10:47', '941398.8905', '1A61192763021040', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(260, '1A61', '00247', 'LCWD1', '123456789', '100.00', '10.00', '110.00', '<AccountName>Test</AccountName><BillMonth>102019</BillMonth>', NULL, '10-03-2019 14:12:38', '941398.8905', '1A61192761021227', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(261, '1A61', '00258', 'BCWD1', '190675040', '100.00', '5.00', '105.00', '<MeterNo>190675040</MeterNo><DueDate>10/03/2019</DueDate><AccountName>Test</AccountName>', NULL, '10-03-2019 14:15:08', '941398.8905', '1A61192760021502', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(262, '1A61', '00303', 'CARWD', '133548', '500.00', '10.00', '510.00', '<AccountName>Test</AccountName><DueDate>10/03/2019</DueDate><BillAmount>500</BillAmount>', NULL, '10-03-2019 14:21:46', '941398.8905', '1A61192760022142', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(263, '1A61', '00234', 'ANTEC', '02022300104', '100.00', '12.00', '112.00', '<AccountName>Chris Cruz</AccountName><DueDate>10/15/2019</DueDate><BillMonth>10/2019</BillMonth>', NULL, '10-09-2019 08:53:40', '941398.8905', '1A61192829085330', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '192821A6139085330', NULL, '1511A61085545', '10/09/2019', '941398.8905'),
(264, '1A61', '00234', 'ANTEC', '12345678901', '100.00', '12.00', '112.00', '<AccountName>Chris Cruz</AccountName><DueDate>10/15/2019</DueDate><BillMonth>10/2019</BillMonth>', NULL, '10-09-2019 09:12:34', '941398.8905', '1A61192825091229', 'HUAWEI', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(265, '1A61', '00001', 'MECOP', '100089850101', '1000.00', '100.00', '1100.00', 'none', NULL, '01-03-2020 13:27:38', '941398.8905', '1A61200033012731', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '200031A6113012731', '<OtherInfo><EquivalentKwh>0</EquivalentKwh><tariffCharge>0</tariffCharge><numOfPurchase>0</numOfPurchase><RCPT>100089850101::20200103132712128</RCPT></OtherInfo>', '1251A61132710', '01/03/2020', '941398.8905'),
(266, '1A61', '00001', 'MECOP', '100089850101', '100.00', '10.00', '110.00', 'none', NULL, '01-03-2020 13:35:33', '941398.8905', '1A61200035013529', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '200031A6195013529', '<OtherInfo><EquivalentKwh>0</EquivalentKwh><tariffCharge>0</tariffCharge><numOfPurchase>0</numOfPurchase><RCPT>100089850101::20200103133459340</RCPT></OtherInfo>', '1351A61133459', '01/03/2020', '941398.8905'),
(267, '1A61', '00001', 'MECOP', '100089850101', '1000.00', '100.00', '1100.00', 'none', NULL, '01-08-2020 17:25:00', '941398.8905', '1A61200089052454', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '200081A6179052454', '<OtherInfo><EquivalentKwh>0</EquivalentKwh><tariffCharge>0</tariffCharge><numOfPurchase>0</numOfPurchase><RCPT>100089850101::20200108172404541</RCPT></OtherInfo>', '1301A61172404', '01/08/2020', '941398.8905'),
(268, '1A61', '00001', 'MECOP', '100089850101', '1000.00', '100.00', '1100.00', 'none', NULL, '01-08-2020 17:27:24', '941398.8905', '1A61200087052719', 'OPPO', '121.144 - 19.114', 0, 'Transaction successful.', '200081A6117052719', '<OtherInfo><EquivalentKwh>0</EquivalentKwh><tariffCharge>0</tariffCharge><numOfPurchase>0</numOfPurchase><RCPT>100089850101::20200108172628966</RCPT></OtherInfo>', '1301A61172628', '01/08/2020', '941398.8905'),
(269, '1A61', '00001', 'MECOP', '100089850101', '100.00', '10.00', '110.00', 'none', NULL, '01-21-2020 14:36:04', '941398.8905', '1A61200211023559', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '200211A6111023559', '<OtherInfo><EquivalentKwh>0</EquivalentKwh><tariffCharge>0</tariffCharge><numOfPurchase>0</numOfPurchase><RCPT>100089850101::20200121143516299</RCPT></OtherInfo>', '1351A61143515', '01/21/2020', '941288.8905');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tran_1a61`
--

CREATE TABLE `tb_tran_1a61` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `biller_code` varchar(5) NOT NULL,
  `service_code` varchar(5) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount_due` varchar(11) NOT NULL,
  `pass_on` varchar(11) NOT NULL,
  `amount_to_pay` varchar(11) NOT NULL,
  `otherinfo` varchar(500) DEFAULT NULL,
  `otherinfoforpost` varchar(500) DEFAULT NULL,
  `date_validated` varchar(20) NOT NULL,
  `balance_old` varchar(15) NOT NULL,
  `partnerrefno` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `longlat` varchar(20) NOT NULL,
  `cbci_code` int(5) DEFAULT NULL,
  `cbci_message` varchar(500) DEFAULT NULL,
  `cbci_transaction_no` varchar(20) DEFAULT NULL,
  `cbci_otherinfo` varchar(500) DEFAULT NULL,
  `cbci_receipt` varchar(50) DEFAULT NULL,
  `cbci_date` varchar(10) DEFAULT NULL,
  `balance_new` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tran_1a61`
--

INSERT INTO `tb_tran_1a61` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(1, '1A61', '00265', 'PLECO', '98745862144718001826', '100', '10.00', '110.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>9874586214</MeterNo><ContactNo>09392580863</ContactNo>', NULL, '01-11-2018 16:49:12', '15000', '1A61180118044904', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6198044904', NULL, '1411A61164928', '01/11/2018', '14895'),
(2, '1A61', '00265', 'PLECO', '78978542167117471826', '5400', '15.00', '5,415.00', '<LastName>retest</LastName><FirstName>testing</FirstName><MI></MI><MeterNo>4876154873</MeterNo><ContactNo>09362587412</ContactNo>', NULL, '01-11-2018 16:55:35', '14895', '1A61180117045527', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6107045527', NULL, '5461A61165553', '01/11/2018', '9490'),
(3, '1A61', '00265', 'PLECO', '78978542167117993826', '180', '10.00', '190.00', '<LastName>tss</LastName><FirstName>tsdd</FirstName><MI></MI><MeterNo>7897853216</MeterNo><ContactNo>1234567</ContactNo>', NULL, '01-12-2018 13:49:45', '9490', '1A61180124014939', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6194014939', NULL, '2221A61134959', '01/12/2018', '9305'),
(4, '1A61', '00265', 'PLECO', '78978542167117162326', '8495', '15.00', '8,510.00', '<LastName>restes</LastName><FirstName>rest</FirstName><MI></MI><MeterNo>1234567800</MeterNo><ContactNo>09394635867</ContactNo>', NULL, '01-12-2018 13:56:26', '9305', '1A61180122015620', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6182015620', NULL, '8421A61135720', '01/12/2018', '805'),
(5, '1A61', '00265', 'PLECO', '78978542167117955282', '565.44', '10.00', '575.44', '<LastName>yesty</LastName><FirstName>yest</FirstName><MI></MI><MeterNo>1324567890</MeterNo><ContactNo>09392588863</ContactNo>', NULL, '01-12-2018 14:18:42', '805', '1A61180123021837', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6163021837', NULL, '6071A61141859', '01/12/2018', '234.56'),
(6, '1A61', '00022', 'MWSIN', '55653219', '234.56', '0.00', '234.56', NULL, NULL, '01-12-2018 14:50:43', '20234.56', '1A61180120025038', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180121A6100025038', NULL, '2661A61145056', '01/12/2018', '20000'),
(7, '1A61', '00191', 'PELC2', '0101442343', '200', '5.00', '205.00', '<ConsumerName>tesr</ConsumerName><DueDate>01/15/2018</DueDate>', NULL, '01-15-2018 14:49:26', '20000', '1A61180150024858', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180151A6110024858', '<OtherInfo><PELC2RetVal></PELC2RetVal></OtherInfo>', '2401A61144946', '01/15/2018', '19795'),
(8, '1A61', '00265', 'PLECO', '78978542167117955282', '565.44', '10.00', '575.44', '<LastName>juan</LastName><FirstName>dela cruz</FirstName><MI></MI><MeterNo>1234567890</MeterNo><ContactNo>09392588088</ContactNo>', NULL, '01-16-2018 15:27:05', '19795', '1A61180166032659', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6136032659', NULL, '6111A61152728', '01/16/2018', '19224.56'),
(9, '1A61', '00132', 'VIECO', '23136100007', '224.56', '0.00', '224.56', '<FirstName>retest</FirstName><LastName>test</LastName>', NULL, '01-16-2018 16:04:52', '19224.56', '1A61180162040440', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6102040440', NULL, '2601A61160520', '01/16/2018', '19000'),
(10, '1A61', '00022', 'MWSIN', '55653219', '100', '0.00', '100.00', 'none', NULL, '01-16-2018 16:15:13', '19000', '1A61180164041505', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6104041505', NULL, '1361A61161536', '01/16/2018', '18900'),
(11, '1A61', '00009', 'GLOBE', '48531016', '200', '0.00', '200.00', '<AccountName>abc edc</AccountName><Telephone_Number>09178190800</Telephone_Number>', NULL, '01-16-2018 16:40:17', '18900', '1A61180165043954', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6135043954', '<OtherInfo><Payment></Payment></OtherInfo>', '2361A61164038', '01/16/2018', '18700'),
(12, '1A61', '00265', 'PLECO', '01001015804717520717', '5001', '15.00', '5,016.00', '<LastName>fe</LastName><FirstName>hermN</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo>8124545</ContactNo>', NULL, '01-16-2018 16:55:30', '18700', '1A61180169045525', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6189045525', NULL, '5521A61165556', '01/16/2018', '13694'),
(13, '1A61', '00265', 'PLECO', '01001015804717995817', '250', '10.00', '260.00', '<LastName>Herman</LastName><FirstName>Fe</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:12:36', '13694', '1A61180168051230', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, '1A61', '00265', 'PLECO', '01001015804717995817', '250', '10.00', '260.00', '<LastName>hernan</LastName><FirstName>fe</FirstName><MI></MI><MeterNo>ba617</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:15:37', '13694', '1A61180164051526', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6184051526', NULL, '2961A61171558', '01/16/2018', '13439'),
(15, '1A61', '00265', 'PLECO', '01001001504317412892', '6000', '15.00', '6,015.00', '<LastName>tablatin</LastName><FirstName>allan</FirstName><MI></MI><MeterNo>7544883</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:21:44', '13439', '1A61180169052139', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6109052139', NULL, '6511A61172201', '01/16/2018', '7434'),
(16, '1A61', '00265', 'PLECO', '01001001504317512792', '5001', '15.00', '5,016.00', '<LastName>a</LastName><FirstName>a</FirstName><MI></MI><MeterNo>aau7</MeterNo><ContactNo></ContactNo>', NULL, '01-16-2018 17:26:40', '7434', '1A61180169052631', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180161A6179052631', NULL, '5521A61172655', '01/16/2018', '2428'),
(17, '1A61', '00265', 'PLECO', '78978542167117511726', '5001', '15.00', '5,016.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>1234567800</MeterNo><ContactNo>1234567</ContactNo>', NULL, '01-17-2018 09:11:28', '17428', '1A61180170091120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6120091120', NULL, '5531A61091217', '01/17/2018', '12422'),
(18, '1A61', '00265', 'PLECO', '01001001504317977847', '350.45', '10.00', '360.45', '<LastName>tablatin</LastName><FirstName>allan</FirstName><MI></MI><MeterNo>7544883</MeterNo><ContactNo></ContactNo>', NULL, '01-17-2018 11:37:23', '12422', '1A61180175113718', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6185113718', NULL, '3971A61113759', '01/17/2018', '12066.55'),
(19, '1A61', '00265', 'PLECO', '01001015804717512792', '5001', '15.00', '5,016.00', '<LastName>fe</LastName><FirstName>herman</FirstName><MI></MI><MeterNo>8066367</MeterNo><ContactNo></ContactNo>', NULL, '01-17-2018 11:44:49', '12066.55', '1A61180174114444', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180171A6154114444', NULL, '5531A61114507', '01/17/2018', '7060.55'),
(20, '1A61', '00191', 'PELC2', '0101442434', '200', '5.00', '205.00', '<ConsumerName>testing</ConsumerName><DueDate>01/23/2018</DueDate>', NULL, '01-23-2018 08:49:18', '7060.55', '1A61180231084903', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6171084903', '<OtherInfo><PELC2RetVal></PELC2RetVal></OtherInfo>', '2391A61084939', '01/23/2018', '6855.55'),
(21, '1A61', '00132', 'VIECO', '23136100007', '150', '0.00', '150.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '01-23-2018 08:51:33', '6855.55', '1A61180234085129', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6104085129', NULL, '1841A61085319', '01/23/2018', '6705.55'),
(22, '1A61', '00022', 'MWSIN', '55653219', '205.55', '0.00', '205.55', 'none', NULL, '01-23-2018 08:55:33', '6705.55', '1A61180230085529', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6120085529', NULL, '2391A61085601', '01/23/2018', '6500'),
(23, '1A61', '00015', 'MWCOM', '18951519', '100', '0.00', '100.00', 'none', NULL, '01-23-2018 08:57:06', '6500', '1A61180230085701', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6150085701', NULL, '1341A61085742', '01/23/2018', '6400'),
(24, '1A61', '00009', 'GLOBE', '1032501421', '354.77', '0.00', '354.77', '<AccountName>testing</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '01-23-2018 09:01:07', '6400', '1A61180230090034', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6140090034', '<OtherInfo><Payment></Payment></OtherInfo>', '3881A61090130', '01/23/2018', '6045.23'),
(25, '1A61', '00005', 'SMART', '0176234369', '45.23', '0.00', '45.23', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>2134657</TelephoneNumber><Product>c</Product>', NULL, '01-23-2018 09:06:00', '6045.23', '1A61180239090545', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6159090545', NULL, '791A61090622', '01/23/2018', '6000'),
(26, '1A61', '00214', 'PLDT6', '0230857954', '310', '7.00', '317.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '01-23-2018 09:10:22', '6000', '1A61180232090958', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6152090958', NULL, '3511A61091042', '01/23/2018', '5683'),
(27, '1A61', '00046', 'INNOV', '841051762', '423', '0.00', '423.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number><DueDate>01/23/2018</DueDate>', NULL, '01-23-2018 09:11:55', '5683', '1A61180231091151', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6141091151', NULL, '4571A61091213', '01/23/2018', '5260'),
(28, '1A61', '00003', 'SKY01', '203015739', '320', '0.00', '320.00', '<ServiceType>0</ServiceType><DueDate>01/23/2018</DueDate><BillDate>01/23/2018</BillDate>', NULL, '01-23-2018 09:14:17', '5260', '1A61180230091413', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6120091413', NULL, '3541A61091435', '01/23/2018', '4940'),
(29, '1A61', '00158', 'CGNAL', '9006567460', '350', '0.00', '350.00', '<FirstName>test</FirstName><LastName>terms</LastName><MI>t</MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '01-23-2018 09:16:57', '4940', '1A61180237091653', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180231A6117091653', NULL, '3841A61091721', '01/23/2018', '4590'),
(30, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Uriah</LastName><FirstName>Velunta</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>123456</ContactNo>', NULL, '01-25-2018 10:33:46', '4590', '1A61180254103341', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>u</LastName><FirstName>velunta</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>4253366</ContactNo>', NULL, '01-25-2018 10:36:43', '4590', '1A61180258103638', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '1A61', '00022', 'MWSIN', '55653219', '40', '0.00', '40.00', 'none', NULL, '01-25-2018 10:44:13', '4590', '1A61180252104409', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(33, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', 'none', NULL, '01-25-2018 10:49:39', '4590', '1A61180257104935', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, '1A61', '00015', 'MWCOM', '18951519', '40', '0.00', '40.00', 'none', NULL, '01-25-2018 10:52:00', '4590', '1A61180258105156', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6148105156', NULL, '761A61105219', '01/25/2018', '4550'),
(35, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Velunta</LastName><FirstName>Uriah</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 11:06:04', '4550', '1A61180251110559', 'asus', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, '1A61', '00022', 'MWSIN', '55653219', '50', '0.00', '50.00', 'none', NULL, '01-25-2018 11:14:07', '4550', '1A61180252111402', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(37, '1A61', '00022', 'MWSIN', '55653219', '50', '0.00', '50.00', 'none', NULL, '01-25-2018 11:21:25', '4550', '1A61180257112120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6137112120', NULL, '861A61112138', '01/25/2018', '4500'),
(38, '1A61', '00265', 'PLECO', '74152896532417997630', '150', '10.00', '160.00', '<LastName>test</LastName><FirstName>test</FirstName><MI></MI><MeterNo>1234567890</MeterNo><ContactNo>09392580863</ContactNo>', NULL, '01-25-2018 11:31:18', '4500', '1A61180252113110', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6102113110', NULL, '1961A61113131', '01/25/2018', '4345'),
(39, '1A61', '00265', 'PLECO', '78954123658917957518', '554.55', '10.00', '564.55', '<LastName>Velunta</LastName><FirstName>Uriah</FirstName><MI></MI><MeterNo>7895412365</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 11:32:42', '4500', '1A61180253113236', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6193113236', NULL, '6001A61113256', '01/25/2018', '3785.45'),
(40, '1A61', '00265', 'PLECO', '25469874515718002973', '100', '10.00', '110.00', '<LastName>velunta</LastName><FirstName>uriah</FirstName><MI></MI><MeterNo>2546987451</MeterNo><ContactNo>09328684727</ContactNo>', NULL, '01-25-2018 14:04:11', '23785.45', '1A61180256020401', 'asus', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6186020401', NULL, '1461A61140451', '01/25/2018', '23680.45'),
(41, '1A61', '00132', 'VIECO', '23136100007', '777', '0.00', '777.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '01-25-2018 15:31:29', '23680.45', '1A61180252033120', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180251A6132033120', NULL, '8131A61153409', '01/25/2018', '22903.45'),
(42, '1A61', '00022', 'MWSIN', '55653219', '205', '0.00', '205.00', 'none', NULL, '01-31-2018 15:14:42', '122706.53', '1A61180311031436', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(43, '1A61', '00015', 'MWCOM', '18951519', '706.53', '0.00', '706.53', 'none', NULL, '01-31-2018 15:33:28', '122706.53', '1A61180319033322', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180311A6139033322', NULL, '7391A61153613', '01/31/2018', '222000'),
(44, '1A61', '00022', 'MWSIN', '55653219', '90', '0.00', '90.00', 'none', NULL, '02-01-2018 15:38:42', '231990', '1A61180328033837', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180321A6198033837', NULL, '1201A61154135', '02/01/2018', '231900'),
(45, '1A61', '00214', 'PLDT6', '0253412631', '500', '7.00', '507.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '02-06-2018 09:51:50', '231900', '1A61180372095105', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(46, '1A61', '00214', 'PLDT6', '0253412631', '500', '7.00', '507.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '02-06-2018 09:54:23', '231900', '1A61180371095407', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180371A6101095407', NULL, '5421A61095732', '02/06/2018', '231393'),
(47, '1A61', '00214', 'PLDT6', '0253412631', '650', '7.00', '657.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '02-06-2018 10:13:49', '231393', '1A61180374101326', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180371A6164101326', NULL, '6921A61101659', '02/06/2018', '230739'),
(48, '1A61', '00030', 'CLNK1', '910003740', '200', '0.00', '200.00', '<AccountName>tesdt</AccountName><ContactNo>12345679</ContactNo>', NULL, '02-23-2018 09:25:27', '230739', '1A61180540092524', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180541A6180092524', NULL, '2341A61092933', '02/23/2018', '230539'),
(49, '1A61', '00040', 'BNKRD', '4215850100250415', '200', '0.00', '200.00', '<AccountName>test</AccountName><BillDate>05/18/2018</BillDate>', NULL, '02-23-2018 13:34:19', '230539', '1A61180541013416', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180541A6181013416', NULL, '2341A61133821', '02/23/2018', '230339'),
(50, '1A61', '00022', 'MWSIN', '55653219', '2000', '0.00', '2000.00', 'none', NULL, '02-26-2018 10:23:45', '230339', '1A61180570102342', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6170102342', NULL, '2371A61102753', '02/26/2018', '228339'),
(51, '1A61', '00225', 'MBCCC', '5464971885436113', '1500', '0.00', '1500.00', '<ConsName>test</ConsName>', NULL, '02-26-2018 10:39:04', '228339', '1A61180573103901', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6183103901', NULL, '1371A61104318', '02/26/2018', '226839'),
(52, '1A61', '00001', 'MECOP', '111234567801', '100', '8.0', '108.00', 'none', NULL, '02-26-2018 10:54:54', '226839', '1A61180577105435', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6147105435', '<OtherInfo><EquivalentKwh>100</EquivalentKwh><tariffCharge>1</tariffCharge><numOfPurchase>1</numOfPurchase><RCPT>111234567801::20180226105903534</RCPT></OtherInfo>', '1451A61105901', '02/26/2018', '226731'),
(53, '1A61', '00114', 'DVOLT', '23136100007', '731', '0.00', '731.00', '<FirstName>test</FirstName><LastName>ting</LastName>', NULL, '02-26-2018 11:03:27', '226731', '1A61180578110324', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6188110324', NULL, '7681A61110735', '02/26/2018', '226000'),
(54, '1A61', '00002', 'BAYAN', '11111111111', '3500', '0.00', '3500.00', '<ConsumerName>test bayantel</ConsumerName><PhoneNo>1234567</PhoneNo>', NULL, '02-26-2018 11:19:29', '226000', '1A61180579111925', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6189111925', NULL, '3371A61112341', '02/26/2018', '222500'),
(55, '1A61', '00129', 'BTCO2', '0372552', '2560', '10.00', '2570.00', '<ConsumerName>test</ConsumerName><DueDate>2018/12/09</DueDate><BillMonth>2018/02</BillMonth><BookID>12357</BookID>', NULL, '02-26-2018 13:57:59', '222500', '1A61180577015755', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6197015755', NULL, '2071A61140211', '02/26/2018', '219930'),
(56, '1A61', '00128', 'LGNWC', '30655639', '358', '0.00', '358.00', '<DueDate>09/29/2017</DueDate>', NULL, '02-26-2018 15:14:26', '219930', '1A61180572031423', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6182031423', NULL, '3951A61151836', '02/26/2018', '219572'),
(57, '1A61', '00082', 'PWCOR', '00003315891209', '5006', '5.00', '5011.00', '<AccountName>test</AccountName><BillNo>1234567</BillNo>', NULL, '02-26-2018 15:53:50', '219572', '1A61180576035347', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6136035347', NULL, '5481A61155803', '02/26/2018', '214561'),
(58, '1A61', '00133', 'SJDWD', '11111122222', '551', '10.00', '561.00', '<AccountName>test skdwdw</AccountName><DueDate>01/24/2019</DueDate>', NULL, '02-26-2018 16:12:12', '214561', '1A61180574041209', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180571A6104041209', NULL, '5981A61161628', '02/26/2018', '214000'),
(59, '1A61', '00167', 'APEC1', '100000688262215', '250.74', '0.00', '250.74', '<SOA>1</SOA><AccountName>test</AccountName><InvoiceNo>1234567</InvoiceNo><PaymentType>S</PaymentType><BillAmount>250.74</BillAmount><DeliveryDate>2018-03-05</DeliveryDate><BillMonth>03</BillMonth><BillYear>2018</BillYear>', NULL, '03-05-2018 08:57:17', '214000', '1A61180644085713', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180641A6184085713', NULL, '2841A61090156', '03/05/2018', '213749.26'),
(60, '1A61', '00136', 'HDMF1', '121134371450', '100', '7.00', '107.00', '<PaymentType>MC</PaymentType><ContactNo>12345678910</ContactNo><BillDate>2018,02</BillDate><DueDate>2018,02</DueDate>', NULL, '03-05-2018 10:42:19', '213749.26', '1A61180646104216', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180641A6196104216', '<OtherInfo><HDMFTranNo>18641A610001</HDMFTranNo><AccountName>ROSARIO,ELIZA VEL,GARCIA</AccountName></OtherInfo>', '1411A61104654', '03/05/2018', '213642.26'),
(61, '1A61', '00114', 'DVOLT', '23136100007', '300', '0.00', '300.00', '<FirstName>test</FirstName><LastName>sms</LastName>', NULL, '03-08-2018 17:14:15', '213642.26', '1A61180676051412', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180671A6126051412', NULL, '3371A61171853', '03/08/2018', '213342.26'),
(62, '1A61', '00114', 'DVOLT', '23136100007', '250', '0.00', '250.00', '<FirstName>testing</FirstName><LastName>sma</LastName>', NULL, '03-12-2018 14:02:05', '213342.26', '1A61180714020202', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6134020202', NULL, '2821A61140653', '03/12/2018', '213092.26'),
(63, '1A61', '00015', 'MWCOM', '18951519', '25', '0.00', '25.00', 'none', NULL, '03-12-2018 14:06:36', '213092.26', '1A61180719020633', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6109020633', NULL, '571A61141141', '03/12/2018', '213067.26'),
(64, '1A61', '00015', 'MWCOM', '18951519', '650', '0.00', '650.00', 'none', NULL, '03-12-2018 14:15:06', '213067.26', '1A61180710021503', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6130021503', NULL, '6821A61142034', '03/12/2018', '212417.26'),
(65, '1A61', '00022', 'MWSIN', '55653219', '417.26', '0.00', '417.26', 'none', NULL, '03-12-2018 14:24:37', '212417.26', '1A61180714022434', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6154022434', NULL, '4491A61142956', '03/12/2018', '212000'),
(66, '1A61', '00022', 'MWSIN', '55653219', '12000', '0.00', '12000.00', 'none', NULL, '03-12-2018 14:31:12', '212000', '1A61180718023109', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6138023109', NULL, '1321A61143559', '03/12/2018', '200000'),
(67, '1A61', '00015', 'MWCOM', '18951519', '100', '0.00', '100.00', 'none', NULL, '03-12-2018 14:37:06', '200000', '1A61180714023703', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6104023703', NULL, '1321A61144153', '03/12/2018', '199900'),
(68, '1A61', '00015', 'MWCOM', '18951519', '250', '0.00', '250.00', 'none', NULL, '03-12-2018 14:44:30', '199900', '1A61180711024427', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6191024427', NULL, '2821A61144917', '03/12/2018', '199650'),
(69, '1A61', '00015', 'MWCOM', '18951519', '50', '0.00', '50.00', 'none', NULL, '03-12-2018 14:51:14', '199650', '1A61180714025111', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6134025111', NULL, '821A61145613', '03/12/2018', '199600'),
(70, '1A61', '00132', 'VIECO', '23136100007', '100', '0.00', '100.00', '<FirstName>test</FirstName><LastName>ttt</LastName>', NULL, '03-12-2018 14:58:16', '199600', '1A61180714025813', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6144025813', NULL, '1321A61150644', '03/12/2018', '199500'),
(71, '1A61', '00132', 'VIECO', '23136100007', '200', '0.00', '200.00', '<FirstName>tt</FirstName><LastName>yy</LastName>', NULL, '03-12-2018 15:08:38', '199500', '1A61180713030836', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6123030836', NULL, '2321A61151344', '03/12/2018', '199300'),
(72, '1A61', '00114', 'DVOLT', '23136100007', '150', '0.00', '150.00', '<FirstName>yest</FirstName><LastName>trsd</LastName>', NULL, '03-12-2018 15:14:31', '199300', '1A61180711031428', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6151031428', NULL, '1821A61151922', '03/12/2018', '199150'),
(73, '1A61', '00022', 'MWSIN', '55653219', '250', '0.00', '250.00', 'none', NULL, '03-12-2018 15:21:55', '199150', '1A61180713032152', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6103032152', NULL, '2821A61152654', '03/12/2018', '198900'),
(74, '1A61', '00074', 'BTCO1', '0101010010', '3244.80', '5.00', '3249.80', '<ConsumerName>test</ConsumerName><DueDate>03/12/2018</DueDate><BillMonth>02/2018</BillMonth>', NULL, '03-12-2018 16:34:26', '198900', '1A61180718043423', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180711A6178043423', '<OtherInfo><BTCO1TranNo>18711A610001</BTCO1TranNo></OtherInfo>', '3811A61163917', '03/12/2018', '195650.2'),
(75, '1A61', '00112', 'PHLTH', '020511097682', '300.00', '8.00', '308.00', '<PaymentType>null</PaymentType><MemberType>SEP</MemberType><FirstName>eliza vel</FirstName><LastName>rosario</LastName><MI></MI><BillDate>03/13/2018</BillDate><DueDate>03/13/2018</DueDate>', NULL, '03-13-2018 08:48:41', '195650.2', '1A61180729084838', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6169084838', '<OtherInfo><PHLTHSeqNo>1A6103131800001</PHLTHSeqNo></OtherInfo>', '3411A61085337', '03/13/2018', '195342.2'),
(76, '1A61', '00112', 'PHLTH', '020511097682', '200.00', '8.00', '208.00', '<PaymentType>null</PaymentType><MemberType>INP</MemberType><FirstName>lisel</FirstName><LastName>rosario</LastName><MI></MI><BillDate>01/13/2018</BillDate><DueDate>01/13/2018</DueDate>', NULL, '03-13-2018 08:53:19', '195342.2', '1A61180721085316', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6131085316', '<OtherInfo><PHLTHSeqNo>1A6103131800002</PHLTHSeqNo></OtherInfo>', '2411A61085811', '03/13/2018', '195134.2'),
(77, '1A61', '00112', 'PHLTH', '020511097682', '2600.00', '8.00', '2608.00', '<PaymentType>null</PaymentType><MemberType>INP</MemberType><FirstName>test</FirstName><LastName>tes</LastName><MI></MI><BillDate>03/13/2018</BillDate><DueDate>03/13/2019</DueDate>', NULL, '03-13-2018 09:45:38', '195134.2', '1A61180721094536', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6101094536', '<OtherInfo><PHLTHSeqNo>1A6103131800003</PHLTHSeqNo></OtherInfo>', '2411A61095034', '03/13/2018', '192526.2'),
(78, '1A61', '00112', 'PHLTH', '020511097682', '2400', '8.00', '2408', '<PaymentType>Annually</PaymentType><MemberType>OFW</MemberType><FirstName>test</FirstName><LastName>trzt</LastName><MI></MI><BillDate>03/13/2018</BillDate><DueDate>03/12/2019</DueDate>', NULL, '03-13-2018 10:20:00', '192526.2', '1A61180725101957', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(79, '1A61', '00112', 'PHLTH', '020511097682', '4800', '8.00', '4808', '<PaymentType>Two Years</PaymentType><MemberType>OFW</MemberType><FirstName>test</FirstName><LastName>twst</LastName><MI></MI><BillDate>03/13/2018</BillDate><DueDate>03/12/2020</DueDate>', NULL, '03-13-2018 10:22:02', '192526.2', '1A61180723102159', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(80, '1A61', '00112', 'PHLTH', '020511097682', '2400.00', '8.00', '2408.00', '<PaymentType>Annually</PaymentType><MemberType>OFW</MemberType><FirstName>test</FirstName><LastName>test</LastName><MI></MI><BillDate>03/13/2018</BillDate><DueDate>03/12/2019</DueDate>', NULL, '03-13-2018 10:34:59', '192526.2', '1A61180720103455', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6190103455', '<OtherInfo><PHLTHSeqNo>1A6103131800004</PHLTHSeqNo></OtherInfo>', '2411A61104006', '03/13/2018', '190118.2'),
(81, '1A61', '00189', 'SSSC1', '3448216301', '1760.00', '0.00', '1760.00', '<PaymentType>S</PaymentType><To>03/2018</To><From>03/2018</From><LastName>rosario</LastName><FirstName>eliza</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>1760</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '03-13-2018 14:44:44', '190118.2', '1A61180725024437', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6125024437', '<OtherInfo><SSSTranNo>BCI180721A610001</SSSTranNo></OtherInfo>', '1931A61144948', '03/13/2018', '188358.2'),
(82, '1A61', '00189', 'SSSC1', '0209315059', '101760.00', '0.00', '101760.00', '<PaymentType>O</PaymentType><To>03/2018</To><From>03/2018</From><LastName>cruz</LastName><FirstName>john</FirstName><SSSFlexiFund>100000</SSSFlexiFund><SSSAmount>1760</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '03-13-2018 15:00:33', '188358.2', '1A61180728030031', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6158030031', '<OtherInfo><SSSTranNo>BCI180721A610002</SSSTranNo></OtherInfo>', '1931A61150526', '03/13/2018', '86598.2'),
(83, '1A61', '00189', 'SSSC1', '0509131483', '6793.41', '0.00', '6793.41', '<PaymentType>O</PaymentType><To>09/2018</To><From>07/2018</From><LastName>cruz</LastName><FirstName>john</FirstName><SSSFlexiFund>1513.41</SSSFlexiFund><SSSAmount>1760</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '03-13-2018 15:03:41', '86598.2', '1A61180723030338', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6103030338', '<OtherInfo><SSSTranNo>BCI180721A610003</SSSTranNo></OtherInfo>', '6261A61150836', '03/13/2018', '79804.79'),
(84, '1A61', '00189', 'SSSC1', '0713241693', '10760.50', '0.00', '10760.50', '<PaymentType>O</PaymentType><To>12/2018</To><From>07/2018</From><LastName>cruz</LastName><FirstName>john</FirstName><SSSFlexiFund>200.50</SSSFlexiFund><SSSAmount>1760</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '03-13-2018 15:05:18', '79804.79', '1A61180727030515', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6177030515', '<OtherInfo><SSSTranNo>BCI180721A610004</SSSTranNo></OtherInfo>', '1931A61151013', '03/13/2018', '69044.29'),
(85, '1A61', '00163', 'HCPHL', '3312345678', '1300', '15.00', '1315.00', '<Name>Juan</Name><PhoneNo></PhoneNo>', NULL, '03-13-2018 16:30:36', '69044.29', '1A61180725043033', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6185043033', NULL, '1481A61163550', '03/13/2018', '67729.29'),
(86, '1A61', '00163', 'HCPHL', '3424681012', '250.55', '15.00', '265.55', '<Name>test hcphl</Name><PhoneNo>124576</PhoneNo>', NULL, '03-13-2018 16:40:24', '67729.29', '1A61180727044020', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6187044020', NULL, '2981A61164517', '03/13/2018', '67463.74'),
(87, '1A61', '00176', 'DRGPY', 'D47K5BC6', '50', '0.00', '50.00', '<FirstName>juan</FirstName><LastName>dela cruz</LastName><ContactNo></ContactNo>', NULL, '03-13-2018 16:46:08', '67463.74', '1A61180721044549', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6191044549', NULL, '831A61165117', '03/13/2018', '67413.74'),
(88, '1A61', '00176', 'DRGPY', 'LFRVA836', '10000', '0.00', '10000.00', '<FirstName>retest</FirstName><LastName>small</LastName><ContactNo>1234567</ContactNo>', NULL, '03-13-2018 16:48:08', '67413.74', '1A61180723044805', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6123044805', NULL, '1331A61165327', '03/13/2018', '57413.74'),
(89, '1A61', '00176', 'DRGPY', 'C5HB8QD3', '888', '0.00', '888.00', '<FirstName>test</FirstName><LastName>amount</LastName><ContactNo>123456789012</ContactNo>', NULL, '03-13-2018 16:53:02', '57413.74', '1A61180721045259', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6121045259', NULL, '9211A61165755', '03/13/2018', '56525.74'),
(90, '1A61', '00157', 'AEON1', '4000869725', '2800', '15.00', '2815.00', '<AccountName>tedt</AccountName>', NULL, '03-13-2018 17:09:54', '56525.74', '1A61180727050951', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180721A6187050951', NULL, '2481A61171450', '03/13/2018', '53710.74'),
(91, '1A61', '00114', 'DVOLT', '23136100007', '588', '0.00', '588.00', '<FirstName>tedt</FirstName><LastName>ffg</LastName>', NULL, '03-14-2018 16:43:42', '53710.74', '1A61180736044338', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6156044338', NULL, '6221A61164834', '03/14/2018', '53122.74'),
(92, '1A61', '00132', 'VIECO', '23136100007', '250', '0.00', '250.00', '<FirstName>ttedt</FirstName><LastName>gfgh</LastName>', NULL, '03-14-2018 16:47:46', '53122.74', '1A61180737044743', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6157044743', NULL, '2841A61165240', '03/14/2018', '52872.74'),
(93, '1A61', '00022', 'MWSIN', '55653219', '29', '0.00', '29.00', 'none', NULL, '03-14-2018 16:49:47', '52872.74', '1A61180734044944', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(94, '1A61', '00022', 'MWSIN', '55653219', '29', '0.00', '29.00', 'none', NULL, '03-14-2018 16:49:47', '52872.74', '1A61180731044944', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(95, '1A61', '00022', 'MWSIN', '55653219', '29', '0.00', '29.00', 'none', NULL, '03-14-2018 16:49:48', '52872.74', '1A61180731044944', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(96, '1A61', '00022', 'MWSIN', '55653219', '72.74', '0.00', '72.74', 'none', NULL, '03-14-2018 16:50:32', '52872.74', '1A61180732045030', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6132045030', NULL, '1061A61165525', '03/14/2018', '52800'),
(97, '1A61', '00022', 'MWSIN', '55653219', '250', '0.00', '250.00', 'none', NULL, '03-14-2018 16:54:23', '52800', '1A61180738045420', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6108045420', NULL, '2841A61165917', '03/14/2018', '52550'),
(98, '1A61', '00022', 'MWSIN', '55653219', '200', '0.00', '200.00', 'none', NULL, '03-14-2018 17:04:58', '52550', '1A61180733050455', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6103050455', NULL, '2341A61170956', '03/14/2018', '52350'),
(99, '1A61', '00022', 'MWSIN', '55653219', '200', '0.00', '200.00', 'none', NULL, '03-14-2018 17:17:08', '52350', '1A61180736051705', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '180731A6126051705', NULL, '2341A61172203', '03/14/2018', '52150'),
(100, '1A61', '00114', 'DVOLT', '23136100007', '150', '0.00', '150.00', '<FirstName>yrzf</FirstName><LastName>test</LastName>', NULL, '03-15-2018 09:30:01', '52150', '1A61180740092957', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180741A6160092957', NULL, '1851A61093733', '03/15/2018', '52000'),
(101, '1A61', '00022', 'MWSIN', '55653219', '500', '0.00', '500.00', 'none', NULL, '03-15-2018 09:43:44', '52000', '1A61180745094341', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180741A6135094341', NULL, '5351A61095902', '03/15/2018', '51500'),
(102, '1A61', '00009', 'GLOBE', '97422667', '10', '0.00', '10.00', '<AccountName>kors</AccountName><Telephone_Number>09178024413</Telephone_Number>', NULL, '03-16-2018 16:35:41', '51500', '1A61180751043532', 'HUAWEI', '121.144 - 19.114', 0, 'Transaction successful.', '180751A6101043532', '<OtherInfo><Payment></Payment></OtherInfo>', '461A61164040', '03/16/2018', '51490'),
(103, '1A61', '00132', 'ABPWR', '23136100007', '350.55', '0.00', '350.55', '<FirstName>test</FirstName><LastName>test abo</LastName><PowerCompany>SEZ</PowerCompany>', NULL, '04-11-2018 16:03:46', '51490', '1A61181018040343', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181011A6198040343', NULL, '3811A61161019', '04/11/2018', '51139.45'),
(104, '1A61', '00186', 'ABSMO', '666666666', '428.88', '0.00', '428.88', '<PhoneNo></PhoneNo>', NULL, '04-11-2018 16:15:15', '51139.45', '1A61181016041513', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181011A6196041513', NULL, '4591A61162209', '04/11/2018', '50710.57'),
(105, '1A61', '00009', 'GLOBE', '97422667', '100', '0.00', '100.00', '<AccountName>Korina</AccountName><Telephone_Number>09178024413</Telephone_Number>', NULL, '04-12-2018 14:33:44', '50710.57', '1A61181022023318', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181021A6102023318', '<OtherInfo><Payment></Payment></OtherInfo>', '1321A61144043', '04/12/2018', '50610.57'),
(106, '1A61', '00099', 'CAINC', '310708151504', '1879.44', '0.00', '1879.44', '<PayorName>test pacific</PayorName>', NULL, '04-12-2018 16:54:11', '50610.57', '1A61181026045407', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181021A6136045407', '<OtherInfo><TransRefNo>6A2F2C3F92F598647324</TransRefNo></OtherInfo>', '1111A61170054', '04/12/2018', '48731.13'),
(107, '1A61', '00188', 'INEC1', '123456789t', '500', '5.00', '505.00', '<DueDate>04/26/2018</DueDate><Name>test</Name>', NULL, '04-16-2018 14:17:49', '48731.13', '1A61181066021747', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181061A6156021747', NULL, '5411A61142431', '04/16/2018', '48226.13'),
(108, '1A61', '00139', 'ILECO', '31-3546-78', '250', '10.00', '260.00', '<DueDate>04/17/2018</DueDate><ConsumerID>1234567890</ConsumerID><BillNumber>027280078</BillNumber><FirstName>test</FirstName><LastName>ffd</LastName>', NULL, '04-17-2018 09:46:35', '48226.13', '1A61181076094632', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6126094632', NULL, '2971A61095318', '04/17/2018', '47966.13'),
(109, '1A61', '00199', 'LEYC2', '123456789h', '2800', '5.00', '2805.00', '<DueDate>04/17/2018</DueDate><BillMonth>201804</BillMonth><FirstName>test</FirstName><LastName>ting</LastName><ContactNumber></ContactNumber>', NULL, '04-17-2018 10:14:31', '47966.13', '1A61181071101428', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6171101428', NULL, '2421A61102119', '04/17/2018', '45161.13'),
(110, '1A61', '00099', 'CAINC', '163419222608', '4914.36', '0.00', '4914.36', '<PayorName>test</PayorName>', NULL, '04-17-2018 13:21:41', '45161.13', '1A61181079012137', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6199012137', '<OtherInfo><TransRefNo>54F0C4D2D0D420E8F061</TransRefNo></OtherInfo>', '4511A61132839', '04/17/2018', '40246.77'),
(111, '1A61', '00099', 'CAINC', '350514272516', '7437.00', '0.00', '7437.00', '<PayorName>test</PayorName>', NULL, '04-17-2018 13:26:22', '40246.77', '1A61181073012618', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6153012618', '<OtherInfo><TransRefNo>029DAC772CB15DD2C9BB</TransRefNo></OtherInfo>', '7741A61133311', '04/17/2018', '32809.77'),
(112, '1A61', '00099', 'CAINC', '151411251431', '7403.40', '0.00', '7403.40', '<PayorName>test</PayorName>', NULL, '04-17-2018 13:28:30', '32809.77', '1A61181072012826', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6102012826', '<OtherInfo><TransRefNo>D2B6B6D7FFFA493616C6</TransRefNo></OtherInfo>', '7401A61133519', '04/17/2018', '25406.37'),
(113, '1A61', '00099', 'CAINC', '010321161519', '1879.44', '0.00', '1879.44', '<PayorName>test</PayorName>', NULL, '04-17-2018 13:30:08', '25406.37', '1A61181077013004', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6157013004', '<OtherInfo><TransRefNo>B26F7042260CE1AAB9CE</TransRefNo></OtherInfo>', '1161A61133658', '04/17/2018', '23526.93'),
(114, '1A61', '00189', 'SSSC1', '3413585232', '110.00', '0.00', '110.00', '<PaymentType>S</PaymentType><To>04/2018</To><From>04/2018</From><LastName>Guanlao</LastName><FirstName>Roana Aila</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>110</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:33:29', '23526.93', '1A61181076013324', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6106013324', '<OtherInfo><SSSTranNo>BCI181071A610001</SSSTranNo><SSID>3413585232</SSID><PayorType>SELF-EMPLOYED</PayorType><PayorName>Roana Aila,,Guanlao</PayorName><From>04/01/2018</From><To>04/01/2018</To><PRN></PRN></OtherInfo>', '1471A61134034', '04/17/2018', '23416.93'),
(115, '1A61', '00189', 'SSSC1', '0216923638', '110.00', '0.00', '110.00', '<PaymentType>S</PaymentType><To>04/2018</To><From>04/2018</From><LastName>Company</LastName><FirstName>Sample</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>110</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:41:29', '23416.93', '1A61181078014126', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6168014126', '<OtherInfo><SSSTranNo>BCI181071A610002</SSSTranNo><SSID>0216923638</SSID><PayorType>SELF-EMPLOYED</PayorType><PayorName>Sample,,Company</PayorName><From>04/01/2018</From><To>04/01/2018</To><PRN></PRN></OtherInfo>', '1471A61134855', '04/17/2018', '23306.93'),
(116, '1A61', '00189', 'SSSC1', '3375040585', '495.00', '0.00', '495.00', '<PaymentType>F</PaymentType><To>06/2018</To><From>04/2018</From><LastName>Dela Cruz</LastName><FirstName>Juan</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>165</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:44:25', '23306.93', '1A61181077014422', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6147014422', '<OtherInfo><SSSTranNo>BCI181071A610003</SSSTranNo><SSID>3375040585</SSID><PayorType>FISHERMAN</PayorType><PayorName>Juan,,Dela Cruz</PayorName><From>04/01/2018</From><To>06/01/2018</To><PRN></PRN></OtherInfo>', '5321A61135117', '04/17/2018', '22811.93'),
(117, '1A61', '00189', 'SSSC1', '0611309213', '550.00', '0.00', '550.00', '<PaymentType>V</PaymentType><To>04/2018</To><From>04/2018</From><LastName>Juan</LastName><FirstName>test</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>550</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:46:00', '22811.93', '1A61181072014555', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6162014555', '<OtherInfo><SSSTranNo>BCI181071A610004</SSSTranNo><SSID>0611309213</SSID><PayorType>VOLUNTARY</PayorType><PayorName>test,,Juan</PayorName><From>04/01/2018</From><To>04/01/2018</To><PRN></PRN></OtherInfo>', '5871A61135248', '04/17/2018', '22261.93'),
(118, '1A61', '00189', 'SSSC1', '3445413576', '1980.00', '0.00', '1980.00', '<PaymentType>N</PaymentType><To>12/2018</To><From>07/2018</From><LastName>test</LastName><FirstName>test</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>330</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:48:20', '22261.93', '1A61181073014817', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6103014817', '<OtherInfo><SSSTranNo>BCI181071A610005</SSSTranNo><SSID>3445413576</SSID><PayorType>NON-WORKING SPOUSE</PayorType><PayorName>test,,test</PayorName><From>07/01/2018</From><To>12/01/2018</To><PRN></PRN></OtherInfo>', '2171A61135512', '04/17/2018', '20281.93'),
(119, '1A61', '00189', 'SSSC1', '0336471332', '6600.00', '0.00', '6600.00', '<PaymentType>O</PaymentType><To>12/2019</To><From>01/2019</From><LastName>test</LastName><FirstName>test</FirstName><SSSFlexiFund></SSSFlexiFund><SSSAmount>550</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:54:00', '20281.93', '1A61181075015358', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6185015358', '<OtherInfo><SSSTranNo>BCI181071A610006</SSSTranNo><SSID>0336471332</SSID><PayorType>OFW</PayorType><PayorName>test,,test</PayorName><From>01/01/2019</From><To>12/01/2019</To><PRN></PRN></OtherInfo>', '6371A61140321', '04/17/2018', '13681.93'),
(120, '1A61', '00189', 'SSSC1', '0335169315', '10760.00', '0.00', '10760.00', '<PaymentType>O</PaymentType><To>06/2019</To><From>01/2019</From><LastName>test</LastName><FirstName>test</FirstName><SSSFlexiFund>200</SSSFlexiFund><SSSAmount>1760</SSSAmount><CountryCode>PHL</CountryCode>', NULL, '04-17-2018 13:58:48', '13681.93', '1A61181073015845', 'samsung', '121.144 - 19.114', 0, 'Transaction successful.', '181071A6113015845', '<OtherInfo><SSSTranNo>BCI181071A610007</SSSTranNo><SSID>0335169315</SSID><PayorType>OFW</PayorType><PayorName>test,,test</PayorName><From>01/01/2019</From><To>06/01/2019</To><PRN></PRN></OtherInfo>', '1971A61140542', '04/17/2018', '2921.93'),
(121, '1A61', '00009', 'GLOBE', '8667812900', '100', '0.00', '100.00', '<AccountName>paolo</AccountName><Telephone_Number>09175582221</Telephone_Number>', NULL, '04-24-2018 14:50:12', '2921.93', '1A61181146024959', 'LENOVO', '121.144 - 19.114', 0, 'Transaction successful.', '181141A6196024959', '<OtherInfo><Payment></Payment></OtherInfo>', '1351A61145731', '04/24/2018', '2821.93'),
(122, '1A61', '0000', 'GLOBE', '09171234567', '10', '0.00', '10', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09171234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-10-2018 16:25:06', '1517.54', '1A61181309042500', '', '', 0, 'Loading Successful', '1A61181309042500', '', '1181805100000075', '2018-05-10', '848.616'),
(123, '1A61', '0000', 'GLOBE', '09171234567', '10', '0.00', '10', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09171234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-10-2018 16:26:59', '1517.54', '1A61181309042654', '', '', 0, 'BAYADCENTER PREPAID RELOAD<test><test> <test> <a>\r\nRef #: 1181805100000076\r\nDate: 2018-05-10 08:34\r\nMobile: 09171234567\r\nProduct: AMAX\r\nAmount: PHP 10.00\r\nTelco-Id: \r\n\r\nThank you for using BayadCenter!', '1A61181309042654', '', '1181805100000076', '2018-05-10', '839.06'),
(124, '1A61', '0000', 'GLOBE', '09171234567', '50', '0.00', '50', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09171234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-10-2018 16:28:08', '1517.54', '1A61181300042802', '', '', 0, 'Loading Successful', '1A61181300042802', '', '1181805100000077', '2018-05-10', '791.28'),
(125, '1A61', '0000', 'GLOBE', '09171234567', '67.3352', '0.00', '67.3352', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09171234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-10-2018 16:52:48', '1067.3352', '1A61181302045244', '', '', 0, 'Loading Successful', '1A61181302045244', '', '1181805100000096', '2018-05-10', '1002.9897'),
(126, '1A61', '0000', 'GLOBE', '09151234567', '20', '0.00', '20', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09151234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-15-2018 09:51:29', '1000.1229', '1A61181353095124', '', '', 0, 'Loading Successful', '1A61181353095124', '', '1181805150000003', '2018-05-15', '981.0109'),
(127, '1A61', '0000', 'GLOBE', '09151234567', '50', '0.00', '50', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09151234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-15-2018 10:10:15', '980.0553', '1A61181351101011', '', '', 0, 'Loading Successful', '1A61181351101011', '', '1181805150000005', '2018-05-15', '932.2753'),
(128, '1A61', '0000', 'GLOBE', '09151234657', '25', '0.00', '25', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09151234657</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '05-15-2018 10:57:28', '932.2753', '1A61181356105724', '', '', 0, 'Loading Successful', '1A61181356105724', '', '1181805150000006', '2018-05-15', '908.3853'),
(129, '1A61', '00009', 'GLOBE', '1006736921', '100.00', '0.00', '100.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '05-28-2018 13:08:03', '1002.0341', '1A61181483010730', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(130, '1A61', '00009', 'GLOBE', '1006736921', '100.00', '0.00', '100.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '05-28-2018 13:16:58', '1002.0341', '1A61181488011636', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(131, '1A61', '00009', 'GLOBE', '1006736921', '1000.00', '0.00', '1000.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '05-28-2018 13:24:40', '1002.0341', '1A61181481012418', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(132, '1A61', '00009', 'GLOBE', '1006736921', '1002.03', '0.00', '1002.03', '<AccountName>twdt</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '05-28-2018 13:25:42', '1002.0341', '1A61181480012540', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(133, '1A61', '0000', 'GLOBE', '09271234567', '50.00', '0.00', '50.00', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09271234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '06-29-2018 16:36:58', '1000', '1A61181807043653', '', '', 0, 'Loading Successful', '1A61181807043653', '', '1181806290000001', '2018-06-29', '380.8941'),
(134, '1A61', '0000', 'GLOBE', '09171234567', '60', '0.00', '60', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09171234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '06-29-2018 16:39:20', '380.8941', '1A61181806043915', '', '', 0, 'Loading Successful', '1A61181806043915', '', '1181806290000002', '2018-06-29', '323.5581'),
(135, '1A61', '0000', 'GLOBE', '09271234567', '30', '0.00', '30', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09271234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '06-29-2018 16:41:58', '318.7801', '1A61181801044154', '', '', 0, 'Loading Successful', '1A61181801044154', '', '1181806290000004', '2018-06-29', '290.1121'),
(136, '1A61', '0000', 'GLOBE', '09271234567', '10', '0.00', '10', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09271234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '07-03-2018 08:54:39', '285.3341', '1A61181844085434', '', '', 0, 'Loading Successful', '1A61181844085434', '', '1181807030000001', '2018-07-03', '275.7781'),
(137, '1A61', '0000', 'SMART', '09392580893', '5', '0.00', '5', '<OtherInfo><Telco>SMART</Telco><MobileNo>09392580893</MobileNo><ProductCode>WBIG5</ProductCode></OtherInfo>', NULL, '07-03-2018 09:09:59', '275.7781', '1A61181840090954', '', '', 0, 'Loading Successful', '1A61181840090954', '', '1181807030000002', '2018-07-03', '271.0001'),
(138, '1A61', '0000', 'SUN', '09321234567', '10', '0.00', '10', '<OtherInfo><Telco>SUN</Telco><MobileNo>09321234567</MobileNo><ProductCode>WRTSWIN10</ProductCode></OtherInfo>', NULL, '07-03-2018 09:10:32', '271.0001', '1A61181846091027', '', '', 0, 'Loading Successful', '1A61181846091027', '', '1181807030000003', '2018-07-03', '261.4441'),
(139, '1A61', '0000', 'GLOBE', '09271234567', '10', '0.00', '10', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09271234567</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '07-03-2018 09:22:39', '242.3321', '1A61181847092234', '', '', 0, 'Loading Successful', '1A61181847092234', '', '1181807030000008', '2018-07-03', '232.7761');
INSERT INTO `tb_tran_1a61` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(140, '1A61', '0000', 'SMART', '09086710566', '10', '0.00', '10', '<OtherInfo><Telco>SMART</Telco><MobileNo>09086710566</MobileNo><ProductCode>W10</ProductCode></OtherInfo>', NULL, '07-03-2018 15:44:52', '232.7761', '1A61181847034447', '', '', 0, 'Loading Successful', '1A61181847034447', '', '1181807030000009', '2018-07-03', '223.2201'),
(141, '1A61', '0000', 'SMART', '09086710566', '10', '0.00', '10', '<OtherInfo><Telco>SMART</Telco><MobileNo>09086710566</MobileNo><ProductCode>W10</ProductCode></OtherInfo>', NULL, '07-03-2018 16:18:49', '223.2201', '1A61181841041844', '', '', 0, 'Loading Successful', '1A61181841041844', '', '1181807030000010', '2018-07-03', '213.6641'),
(142, '1A61', '00230', 'RFID1', '1234567890123456', '10.00', '0.00', '10.00', NULL, NULL, '07-10-2018 13:37:44', '204.1081', '1A61181911013742', 'samsung', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(143, '1A61', '00230', 'RFID1', '123456', '5.00', '0.00', '5.00', 'none', NULL, '07-10-2018 13:42:39', '204.1081', '1A61181916014237', 'samsung', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(144, '1A61', '0000', 'GLOBE', '09165019918', '150', '0.00', '150', '<OtherInfo><Telco>GLOBE</Telco><MobileNo>09165019918</MobileNo><ProductCode>AMAX</ProductCode></OtherInfo>', NULL, '07-10-2018 15:46:53', '41.6561', '1A61181919034649', '', '', 0, 'Loading Successful', '1A61181919034649', '', '1181807100000003', '2018-07-10', '-101.6839'),
(145, '1A61', '00239', 'MPAY1', 'mpwxh5xik6', '1015.00', '0.00', '1015.00', '<ContactNumber></ContactNumber>', NULL, '07-20-2018 10:47:16', '998941.7605', '1A61182013104653', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182011A6193104653', '<OtherInfo><AccountName>Not Applicable</AccountName><TransactionId>00012018080700001</TransactionId><Source>DFA (DEV)</Source><BillerCode>DFA_DEV01</BillerCode></OtherInfo>', '1461A61105904', '07/20/2018', '997926.7605'),
(146, '1A61', '00239', 'MPAY1', 'mpnrv1e6d6', '1265.00', '0.00', '1265.00', '<ContactNumber></ContactNumber><Affiliate>DFA</Affiliate>', NULL, '07-20-2018 11:05:13', '997926.7605', '1A61182012110511', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(147, '1A61', '00239', 'MPAY1', 'mpnrv1e6d6', '1265.00', '0.00', '1265.00', '<ContactNumber></ContactNumber><Affiliate>DFA</Affiliate>', NULL, '07-20-2018 11:18:49', '997926.7605', '1A61182013111846', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '182011A6173111846', '<OtherInfo><AccountName>Not Applicable</AccountName><TransactionId>00012018082200001</TransactionId><Source>DFA (DEV)</Source><BillerCode>DFA_DEV01</BillerCode></OtherInfo>', '1961A61113120', '07/20/2018', '996661.7605');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tran_1a61_old`
--

CREATE TABLE `tb_tran_1a61_old` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `biller_code` varchar(5) NOT NULL,
  `service_code` varchar(5) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount_due` varchar(11) NOT NULL,
  `pass_on` varchar(11) NOT NULL,
  `amount_to_pay` varchar(11) NOT NULL,
  `otherinfo` varchar(500) DEFAULT NULL,
  `otherinfoforpost` varchar(500) DEFAULT NULL,
  `date_validated` varchar(20) NOT NULL,
  `balance_old` varchar(15) NOT NULL,
  `partnerrefno` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `longlat` varchar(20) NOT NULL,
  `cbci_code` int(5) DEFAULT NULL,
  `cbci_message` varchar(500) DEFAULT NULL,
  `cbci_transaction_no` varchar(20) DEFAULT NULL,
  `cbci_otherinfo` varchar(500) DEFAULT NULL,
  `cbci_receipt` varchar(50) DEFAULT NULL,
  `cbci_date` varchar(10) DEFAULT NULL,
  `balance_new` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tran_1a61_old`
--

INSERT INTO `tb_tran_1a61_old` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(1, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', NULL, NULL, '09-21-2017 15:31:17', '953500', '1A61172642033112', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', NULL, NULL, '09-21-2017 16:03:24', '953500', '1A61172649040319', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(3, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', NULL, NULL, '09-21-2017 16:05:30', '953500', '1A61172645040524', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(4, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', '', NULL, '09-21-2017 16:27:45', '100', '1A61172642042740', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(5, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', '', NULL, '09-21-2017 16:37:27', '100', '1A61172642043721', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(6, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', '', NULL, '09-21-2017 16:40:21', '100', '1A61172640044016', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(7, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', '', NULL, '09-21-2017 16:41:20', '100', '1A61172647044115', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(8, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-21-2017 16:48:38', '953500', '1A61172648044833', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(9, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-21-2017 16:49:14', '953500', '1A61172646044909', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(10, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-21-2017 16:52:43', '953500', '1A61172646045237', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(11, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-21-2017 16:52:44', '953500', '1A61172646045239', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(12, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 09:33:57', '953500', '1A61172651093351', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(13, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 09:38:48', '953500', '1A61172655093842', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(14, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 09:50:43', '953500', '1A61172651095038', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(15, '1A61', '00022', 'MWSIN', '55653219', '320.00', '0.00', '320.00', NULL, NULL, '09-22-2017 09:54:49', '953500', '1A61172658095444', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172651A6188095444', NULL, '3521A61135528', '09/22/2017', '952690'),
(16, '1A61', '00022', 'MWSIN', '55653219', '350.00', '0.00', '350.00', NULL, NULL, '09-22-2017 09:56:22', '953500', '1A61172653095615', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6163095615', NULL, '3821A61131128', '09/22/2017', '953110'),
(17, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 10:05:36', '953500', '1A61172650100531', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172651A6120100531', NULL, '521A61130817', '09/22/2017', '953460'),
(18, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 10:27:35', '953500', '1A61172650102730', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6180102730', NULL, '521A61104937', '09/22/2017', '953480'),
(19, '1A61', '00022', 'MWSIN', '55653219', '100.00', '0.00', '100.00', NULL, NULL, '09-22-2017 13:52:09', '953110', '1A61172658015203', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6118015203', NULL, '1321A61135231', '09/22/2017', '953010'),
(20, '1A61', '00022', 'MWSIN', '55653219', '50.00', '0.00', '50.00', NULL, NULL, '09-22-2017 14:19:20', '952690', '1A61172657021915', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6147021915', NULL, '821A61141940', '09/22/2017', '952640'),
(21, '1A61', '00022', 'MWSIN', '55653219', '640.00', '0.00', '640.00', NULL, NULL, '09-22-2017 14:35:13', '952640', '1A61172659023508', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6139023508', NULL, '6721A61143537', '09/22/2017', '952000'),
(22, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:28:34', '952000', '1A61172656032806', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(23, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:28:38', '952000', '1A61172652032818', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(24, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:28:55', '952000', '1A61172650032841', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(25, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:29:47', '952000', '1A61172657032927', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(26, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:31:18', '952000', '1A61172652033059', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(27, '1A61', '00009', 'GLOBE', '1040748430', '100.00', '0.00', '100.00', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:32:31', '100', '1A61172651033214', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(28, '1A61', '00009', 'GLOBE', '1040748430', '167.46', '0.00', '167.46', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:33:11', '952000', '1A61172657033250', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(29, '1A61', '00009', 'GLOBE', '1040748430', '167.00', '0.00', '167.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:35:07', '952000', '1A61172659033449', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(30, '1A61', '00009', 'GLOBE', '1040748430', '167.00', '0.00', '167.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:35:48', '952000', '1A61172659033534', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(31, '1A61', '00009', 'GLOBE', '1040748430', '167.00', '0.00', '167.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:36:27', '952000', '1A61172650033614', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(32, '1A61', '00009', 'GLOBE', '1040748430', '11.00', '0.00', '11.00', '<AccountName>ww</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:37:25', '952000', '1A61172654033711', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(33, '1A61', '00009', 'GLOBE', '1040748430', '11.00', '0.00', '11.00', '<AccountName>ww</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:37:26', '952000', '1A61172655033711', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(34, '1A61', '00009', 'GLOBE', '1040748430', '11.00', '0.00', '11.00', '<AccountName>ww</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:38:31', '952000', '1A61172658033811', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(35, '1A61', '00009', 'GLOBE', '1040748430', '10.00', '0.00', '10.00', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 15:41:36', '952000', '1A61172651034122', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(36, '1A61', '00001', 'MECOA', '01372718951170913117092804', '3351.71', '0.00', '3.00', NULL, NULL, '09-22-2017 15:49:37', '952000', '1A61172658034930', 'DUKE7', '121.144 - 19.114', 400, 'Sum of Cash and Check should be equal to TotalAmou', NULL, NULL, NULL, NULL, '952000'),
(37, '1A61', '00001', 'MECOA', '01372718951170913117092804', '3351.71', '0.00', '3.00', NULL, NULL, '09-22-2017 15:55:13', '952000', '1A61172657035508', 'DUKE7', '121.144 - 19.114', 300, 'Duplicate transaction is not allowed.', NULL, NULL, NULL, NULL, '948580.29'),
(38, '1A61', '00015', 'MWCOM', '18951519', '28.00', '0.00', '28.00', NULL, NULL, '09-22-2017 16:00:10', '952000', '1A61172652040006', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172651A6102040006', NULL, '601A61160024', '09/22/2017', '951972'),
(39, '1A61', '00022', 'MWSIN', '55653219', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 16:00:44', '952000', '1A61172652040038', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6192040038', NULL, '521A61160058', '09/22/2017', '951952'),
(40, '1A61', '00015', 'MWCOM', '18951519', '20.00', '0.00', '20.00', NULL, NULL, '09-22-2017 16:08:55', '952000', '1A61172655040847', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172651A6135040847', NULL, '521A61160913', '09/22/2017', '951932'),
(41, '1A61', '00001', 'MECOA', '01372718951170913117092804', '3351.71', '0.00', '3,351.71', NULL, NULL, '09-22-2017 16:13:21', '952000', '1A61172652041316', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6122041316', NULL, '3831A61161347', '09/22/2017', '948580.29'),
(42, '1A61', '00001', 'MECOA', '00323026488170918717100300', '1019.92', '0.00', '1,019.92', NULL, NULL, '09-22-2017 16:22:56', '948580.29', '1A61172651042250', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172651A6131042250', NULL, '1511A61162309', '09/22/2017', '947560.37'),
(43, '1A61', '00009', 'GLOBE', '1054630321', '1871', '0.00', '1,871.00', '<AccountName></AccountName><Telephone_Number>1234765</Telephone_Number>', NULL, '09-22-2017 16:25:29', '948580.29', '1A61172657042500', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(44, '1A61', '00009', 'GLOBE', '1054630321', '1871', '0.00', '1,871.00', '<AccountName></AccountName><Telephone_Number>1234765</Telephone_Number>', NULL, '09-22-2017 16:26:10', '948580.29', '1A61172657042551', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(45, '1A61', '00009', 'GLOBE', '1054630321', '1871', '0.00', '1,871.00', '<AccountName></AccountName><Telephone_Number>1234765</Telephone_Number>', NULL, '09-22-2017 16:27:38', '948580.29', '1A61172652042720', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(46, '1A61', '00005', 'SMART', '0779496740', '20', '0.00', '20.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>1234567</TelephoneNumber><Product>c</Product>', NULL, '09-22-2017 16:29:33', '948580.29', '1A61172652042921', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(47, '1A61', '00009', 'GLOBE', '1040748430', '350.55', '0.00', '350.55', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 16:37:56', '947560.37', '1A61172652043731', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(48, '1A61', '00015', 'MWCOM', '18951519', '20', '0.00', '20.00', NULL, NULL, '09-22-2017 16:38:48', '947560.37', '1A61172657043840', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6197043840', NULL, '521A61163903', '09/22/2017', '947540.37'),
(49, '1A61', '00005', 'SMART', '0779496740', '3587', '0.00', '3,587.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>c</Product>', NULL, '09-22-2017 16:40:21', '947560.37', '1A61172651044015', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6131044015', NULL, '3191A61164048', '09/22/2017', '943953.37'),
(50, '1A61', '00009', 'GLOBE', '1040748430', '4110.77', '0.00', '4,110.77', '<AccountName></AccountName><Telephone_Number>2478665</Telephone_Number>', NULL, '09-22-2017 16:42:24', '947560.37', '1A61172657044208', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(51, '1A61', '00005', 'SMART', '0779496740', '446', '0.00', '446.00', '<ServiceReferenceNumber>1234578890</ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>b</Product>', NULL, '09-22-2017 16:44:13', '947560.37', '1A61172653044400', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(52, '1A61', '00005', 'SMART', '0779496740', '446', '0.00', '446.00', '<ServiceReferenceNumber>1234578890</ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>b</Product>', NULL, '09-22-2017 16:46:44', '947560.37', '1A61172656044639', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172651A6106044639', NULL, '4781A61164703', '09/22/2017', '943507.37'),
(53, '1A61', '00009', 'GLOBE', '1054630321', '1871', '0.00', '1,871.00', '<AccountName></AccountName><Telephone_Number>1236674</Telephone_Number>', NULL, '09-22-2017 16:55:33', '943507.37', '1A61172651045512', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(54, '1A61', '00009', 'GLOBE', '1054630321', '100', '0', '100', '<AccountName>test</AccountName><Telephone_Number>1343222</Telephone_Number>', NULL, '09-22-2017 16:59:19', '100', '1A61172658045905', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(55, '1A61', '00009', 'GLOBE', '1054630321', '14407', '0.00', '14,407.00', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 17:00:30', '943507.37', '1A61172650050009', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(56, '1A61', '00009', 'GLOBE', '1054630321', '14407', '0.00', '14,407.00', '<AccountName></AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-22-2017 17:01:25', '943507.37', '1A61172659050108', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(57, '1A61', '00214', 'PLDT6', '0258428234', '350', '0.00', '350.00', '<PhoneNumber>1234678905</PhoneNumber><Service>PL</Service>', NULL, '09-22-2017 17:16:57', '943507.37', '1A61172652051640', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(58, '1A61', '00132', 'VIECO', '23136100007', '32585.78', '0.00', '32,585.78', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '09-25-2017 15:17:08', '943507.37', '1A61172682031703', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(59, '1A61', '00132', 'VIECO', '23136100007', '3500', '0.00', '3,500.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '09-25-2017 15:34:50', '943507.37', '1A61172683033446', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(60, '1A61', '00132', 'VIECO', '23136100007', '3580', '0.00', '3,580.00', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '09-25-2017 15:36:30', '943507.37', '1A61172687033626', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172681A6177033626', NULL, '3151A61153648', '09/25/2017', '939927.37'),
(61, '1A61', '00022', 'MWSIN', '55653219', '580', '0.00', '580.00', NULL, NULL, '09-26-2017 09:24:15', '939927.37', '1A61172690092410', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172691A6160092410', NULL, '6161A61092428', '09/26/2017', '939347.37'),
(62, '1A61', '00009', 'GLOBE', '1046090712', '455.54', '0.00', '455.54', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-26-2017 10:15:03', '939347.37', '1A61172697101435', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(63, '1A61', '00009', 'GLOBE', '1046090712', '455.54', '0.00', '455.54', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-26-2017 10:17:58', '939347.37', '1A61172691101738', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(64, '1A61', '00009', 'GLOBE', '1046090712', '455.54', '0.00', '455.54', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-26-2017 10:18:25', '939347.37', '1A61172694101810', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(65, '1A61', '00015', 'MWCOM', '18951519', '3280', '0.00', '3,280.00', NULL, NULL, '09-26-2017 14:12:47', '939347.37', '1A61172695021242', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172691A6155021242', NULL, '3161A61141303', '09/26/2017', '936067.37'),
(66, '1A61', '00009', 'GLOBE', '1050858840', '123', '0.00', '123.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 09:47:47', '936067.37', '1A61172706094709', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(67, '1A61', '00009', 'GLOBE', '1050858840', '3580', '0.00', '3,580.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 09:51:55', '936067.37', '1A61172701095135', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(68, '1A61', '00009', 'GLOBE', '1050858840', '350.47', '0.00', '350.47', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 09:53:57', '936067.37', '1A61172702095338', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(69, '1A61', '00015', 'MWCOM', '18951519', '24', '0.00', '24.00', NULL, NULL, '09-27-2017 10:05:06', '936067.37', '1A61172706100501', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(70, '1A61', '00001', 'MECOA', '01775589414170918217100300', '3528.92', '0.00', '3,528.92', NULL, NULL, '09-27-2017 10:37:49', '936067.37', '1A61172703103743', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6103103743', NULL, '3651A61103809', '09/27/2017', '932538.45'),
(71, '1A61', '00022', 'MWSIN', '55653219', '350', '0.00', '350.00', NULL, NULL, '09-27-2017 10:49:28', '932538.45', '1A61172707104922', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6187104922', NULL, '3871A61104949', '09/27/2017', '932188.45'),
(72, '1A61', '00001', 'MECOP', '111234567801', '100', '8.00', '108.0', NULL, NULL, '09-27-2017 14:04:01', '932188.45', '1A61172705020356', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6115020356', NULL, '1451A61140430', '09/27/2017', '932080.45'),
(73, '1A61', '00132', 'VIECO', '07736929709', '80.45', '0.00', '80.45', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '09-27-2017 14:17:38', '932080.45', '1A61172709021700', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(74, '1A61', '00132', 'VIECO', '07736929709', '80.45', '0.00', '80.45', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '09-27-2017 14:19:28', '932080.45', '1A61172707021923', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6157021923', NULL, '1171A61141959', '09/27/2017', '932000'),
(75, '1A61', '00009', 'GLOBE', '1050858840', '300', '0.00', '300.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 15:37:01', '932000', '1A61172708033638', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(76, '1A61', '00009', 'GLOBE', '1050858840', '300', '0.00', '300.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 15:38:43', '932000', '1A61172700033828', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(77, '1A61', '00046', 'INNOV', '871865013', '350', '0.00', '350.00', '<AccountName>tedt</AccountName><Telephone_Number>1234567</Telephone_Number><DueDate>9/27/2017</DueDate>', NULL, '09-27-2017 15:43:58', '932000', '1A61172707034353', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6107034353', NULL, '3871A61154417', '09/27/2017', '931650'),
(78, '1A61', '00046', 'INNOV', '855500337', '650', '0.00', '650.00', '<AccountName>testing</AccountName><Telephone_Number>9874563</Telephone_Number><DueDate>9/27/2017</DueDate>', NULL, '09-27-2017 15:48:00', '931650', '1A61172706034755', 'DUKE7', '121.144 - 19.114', 0, 'Posted', '172701A6136034755', NULL, '6871A61154817', '09/27/2017', '931000'),
(79, '1A61', '00009', 'GLOBE', '1029920648', '1000', '0.00', '1,000.00', '<AccountName>testing globe</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 15:49:47', '931000', '1A61172700034927', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(80, '1A61', '00009', 'GLOBE', '1054630321', '100', '0', '100', '<AccountName>test</AccountName><Telephone_Number>1343222</Telephone_Number>', NULL, '09-27-2017 15:50:21', '100', '1A61172700035006', 'DUKE7', '19.5486-121.5487', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(81, '1A61', '00214', 'PLDT6', '0259128161', '1000', '0.00', '1,000.00', '<PhoneNumber>6321234567</PhoneNumber><Service>PD</Service>', NULL, '09-27-2017 15:56:50', '931000', '1A61172703035636', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(82, '1A61', '00214', 'PLDT6', '0259128161', '1000', '0.00', '1,000.00', '<PhoneNumber>6321234567</PhoneNumber><Service>PD</Service>', NULL, '09-27-2017 15:57:47', '931000', '1A61172705035737', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(83, '1A61', '00214', 'PLDT6', '0259128161', '1000', '0.00', '1,000.00', '<PhoneNumber>6321234567</PhoneNumber><Service>PD</Service>', NULL, '09-27-2017 15:58:46', '931000', '1A61172704035836', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(84, '1A61', '00214', 'PLDT6', '0259128161', '1000', '0.00', '1,000.00', '<PhoneNumber>6321234567</PhoneNumber><Service>PD</Service>', NULL, '09-27-2017 15:59:55', '931000', '1A61172707035943', 'DUKE7', '121.144 - 19.114', 182, 'PartnerRefNo already exist with the TransactionNo: 172701A6167035943.', '172701A6167035943', NULL, '1371A61160041', '09/27/2017', '930000'),
(85, '1A61', '00214', 'PLDT6', '0255054252', '30000', '0.00', '30,000.00', '<PhoneNumber>1234567890</PhoneNumber><Service>PL</Service>', NULL, '09-27-2017 16:01:57', '930000', '1A61172708040146', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172701A6158040146', NULL, '3371A61160250', '09/27/2017', '900000'),
(86, '1A61', '00009', 'GLOBE', '1054873305', '225', '0.00', '225.00', '<AccountName>test</AccountName><Telephone_Number>1234567</Telephone_Number>', NULL, '09-27-2017 16:04:11', '900000', '1A61172705040355', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172701A6125040355', '<OtherInfo><Payment></Payment></OtherInfo>', '2621A61160429', '09/27/2017', '899775'),
(87, '1A61', '00003', 'SKY01', '674213174', '775', '0.00', '775.00', '<ServiceType>0</ServiceType><DueDate>09/27/2017</DueDate><BillDate>09/27/2017</BillDate>', NULL, '09-27-2017 16:16:33', '899775', '1A61172708041629', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172701A6128041629', NULL, '8121A61161704', '09/27/2017', '899000'),
(88, '1A61', '00239', 'MPAY1', 'MPJXFSNO77', '26', '0.00', '26.00', '<ContactNumber>123456</ContactNumber>', NULL, '09-28-2017 08:55:27', '899000', '1A61172716085522', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6166085522', NULL, '641A61085549', '09/28/2017', '898974'),
(89, '1A61', '00239', 'MPAY1', 'MPRXXGCEU0', '26', '0.00', '26.00', '<ContactNumber>123456789012</ContactNumber>', NULL, '09-28-2017 09:02:04', '898974', '1A61172715090159', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6195090159', NULL, '641A61090221', '09/28/2017', '898948'),
(90, '1A61', '00239', 'MPAY1', 'MPCzzwfmk6', '46', '0.00', '46.00', '<ContactNumber>123456789</ContactNumber>', NULL, '09-28-2017 09:10:39', '898948', '1A61172713091032', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6103091032', NULL, '841A61091055', '09/28/2017', '898902'),
(91, '1A61', '00068', 'PILTS', '1726380897', '450.00', '0.00', '450.00', '<AccountName>test</AccountName>', NULL, '09-28-2017 09:22:19', '898902', '1A61172714092213', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6104092213', NULL, '4881A61092239', '09/28/2017', '898452'),
(92, '1A61', '00015', 'MWCOM', '18951519', '20', '0.00', '20.00', NULL, NULL, '09-28-2017 09:43:04', '898452', '1A61172717094258', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6157094258', NULL, '581A61094322', '09/28/2017', '898432'),
(93, '1A61', '00022', 'MWSIN', '55653219', '288', '0.00', '288.00', NULL, NULL, '09-28-2017 10:18:56', '898432', '1A61172711101851', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6131101851', NULL, '3261A61101925', '09/28/2017', '898144'),
(94, '1A61', '00132', 'VIECO', '23136100007', '35', '0.00', '35.00', '<FirstName>testing</FirstName><LastName>testing</LastName>', NULL, '09-28-2017 11:31:37', '898144', '1A61172716113130', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(95, '1A61', '00132', 'VIECO', '23136100007', '35', '0.00', '35.00', '<FirstName>testing</FirstName><LastName>other info</LastName>', NULL, '09-28-2017 11:33:43', '898144', '1A61172710113337', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(96, '1A61', '00132', 'VIECO', '23136100007', '35', '0.00', '35.00', '<FirstName>testing</FirstName><LastName>other info</LastName>', NULL, '09-28-2017 11:34:09', '898144', '1A61172711113404', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(97, '1A61', '00132', 'VIECO', '23136100007', '35', '0.00', '35.00', '<FirstName>testingwtu</FirstName><LastName>other infohyy</LastName>', NULL, '09-28-2017 11:34:24', '898144', '1A61172710113418', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172711A6130113418', NULL, '731A61113445', '09/28/2017', '898109'),
(98, '1A61', '00001', 'MECOP', '111234567801', '100', '8.00', '108.0', NULL, NULL, '10-02-2017 09:56:35', '898109', '1A61172758095619', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(99, '1A61', '00001', 'MECOP', '111234567801', '100', '8.00', '108.0', NULL, NULL, '10-02-2017 09:57:05', '898109', '1A61172757095659', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(100, '1A61', '00001', 'MECOP', '111234567801', '100', '8.00', '108.0', NULL, NULL, '10-02-2017 09:59:16', '898109', '1A61172753095848', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(101, '1A61', '00001', 'MECOP', '111234567801', '100', '8.00', '108.0', NULL, NULL, '10-02-2017 10:05:25', '898109', '1A61172753100518', 'DUKE7', '121.144 - 19.114', 999, 'General Web Exception.', NULL, NULL, NULL, NULL, '898109'),
(102, '1A61', '00022', 'MWSIN', '55653219', '2', '0.00', '2.00', NULL, NULL, '10-02-2017 10:07:27', '898109', '1A61172754100721', 'DUKE7', '121.144 - 19.114', 999, 'General Web Exception.', NULL, NULL, NULL, NULL, '898109'),
(103, '1A61', '00009', 'GLOBE', '1054630321', '100', '0', '100', '<AccountName>test</AccountName><Telephone_Number>1343222</Telephone_Number>', NULL, '10-02-2017 10:12:06', '100', '1A61172751101128', 'DUKE7', '19.5486-121.5487', 999, 'General Web Exception.', NULL, NULL, NULL, NULL, '898109'),
(104, '1A61', '00132', 'VIECO', '23136100007', '280', '0.00', '280.00', '<FirstName>test</FirstName><LastName>ting</LastName>', NULL, '10-03-2017 13:36:31', '898109', '1A61172762013626', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6182013626', NULL, '3111A61133648', '10/03/2017', '898109'),
(105, '1A61', '00132', 'VIECO', '23136100007', '2478', '0.00', '2,478.00', '<FirstName>w</FirstName><LastName>s</LastName>', NULL, '10-03-2017 13:38:47', '898109', '1A61172764013842', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6194013842', NULL, '2091A61133910', '10/03/2017', '898109'),
(106, '1A61', '00132', 'VIECO', '23136100007', '109', '0.00', '109.00', '<FirstName>tesy</FirstName><LastName>Tomas</LastName>', NULL, '10-03-2017 13:46:13', '898109', '1A61172769014609', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6109014609', NULL, '1401A61134629', '10/03/2017', '898109'),
(107, '1A61', '00022', 'MWSIN', '55653219', '2000', '0.00', '2,000.00', NULL, NULL, '10-03-2017 13:55:55', '898109', '1A61172767015550', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6167015550', NULL, '2311A61135616', '10/03/2017', '896109'),
(108, '1A61', '00022', 'MWSIN', '55653219', '2000', '0.00', '2,000.00', NULL, NULL, '10-03-2017 13:55:57', '898109', '1A61172767015553', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(109, '1A61', '00015', 'MWCOM', '18951519', '6100', '0.00', '6,100.00', NULL, NULL, '10-03-2017 14:34:11', '896109', '1A61172763023407', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6113023407', NULL, '6311A61143428', '10/03/2017', '890009'),
(110, '1A61', '00022', 'MWSIN', '55653219', '9', '0.00', '9.00', NULL, NULL, '10-03-2017 15:22:55', '890009', '1A61172766032249', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6186032249', NULL, '401A61152312', '10/03/2017', '890000'),
(111, '1A61', '00265', 'PLECO', '02050094724516991904', '1086.61', '10.00', '1,096.61', '<LastName>Moral</LastName><FirstName>Evelyn</FirstName><MI></MI><DueDate>10/05/2017</DueDate><MeterNo>3470747</MeterNo><TelephoneNumber>09392580863</TelephoneNumber>', NULL, '10-03-2017 16:47:21', '890000', '1A61172766044715', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172761A6106044715', NULL, '1271A61164737', '10/03/2017', '888903.39'),
(112, '1A61', '00132', 'VIECO', '23136100007', '200', '0.00', '200.00', '<FirstName>tes</FirstName><LastName>ted</LastName>', NULL, '10-06-2017 09:40:58', '888903.39', '1A61172793094052', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6103094052', NULL, '2341A61094113', '10/06/2017', '888703.39'),
(113, '1A61', '00265', 'PLECO', '02024444446816601955', '5000.00', '10.00', '5,010.00', '<LastName>Dela Cruz</LastName><FirstName>Juan</FirstName><MI>P</MI><DueDate>10/19/2017</DueDate><MeterNo>1234567890</MeterNo><ContactNo>09062698712</ContactNo>', NULL, '10-06-2017 16:16:25', '888703.39', '1A61172790041619', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6120041619', NULL, '5441A61161710', '10/06/2017', '883693.39'),
(114, '1A61', '00265', 'PLECO', '02023222223812101874', '50000.00', '15.00', '50,015.00', '<LastName>Dela Cruz</LastName><FirstName>Juan</FirstName><MI>P</MI><DueDate>10/18/2017</DueDate><MeterNo>1234567890</MeterNo><ContactNo>09062698712</ContactNo>', NULL, '10-06-2017 16:22:02', '888703.39', '1A61172797042157', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6167042157', NULL, '5491A61162236', '10/06/2017', '833678.39'),
(115, '1A61', '00265', 'PLECO', '02023702362817076531', '250.00', '10.00', '260.00', '<LastName>Dela Cruz</LastName><FirstName>Juan</FirstName><MI>P</MI><DueDate>10/15/2017</DueDate><MeterNo>1234567890</MeterNo><ContactNo>09062698712</ContactNo>', NULL, '10-06-2017 16:25:01', '888703.39', '1A61172795042456', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6105042456', NULL, '2941A61162534', '10/06/2017', '833418.39'),
(116, '1A61', '00265', 'PLECO', '02027777775216101955', '10000', '15.00', '10,015.00', '<LastName>Dela Cruz</LastName><FirstName>Juan</FirstName><MI></MI><DueDate>10/19/2017</DueDate><MeterNo>12345678</MeterNo><ContactNo>09123456789</ContactNo>', NULL, '10-06-2017 16:30:22', '833418.39', '1A61172795043017', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6105043017', NULL, '1491A61163056', '10/06/2017', '823403.39'),
(117, '1A61', '00265', 'PLECO', '02023333333012101774', '50001', '15.00', '50,016.00', '<LastName>test</LastName><FirstName>test</FirstName><MI>t</MI><DueDate>10/06/2017</DueDate><MeterNo>12345678</MeterNo><ContactNo>09392580866</ContactNo>', NULL, '10-06-2017 17:21:23', '823403.39', '1A61172793052117', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172791A6193052117', NULL, '5501A61172248', '10/06/2017', '773387.39'),
(118, '1A61', '00265', 'PLECO', '01070122050716868849', '2326.82', '10.00', '2,336.82', '<LastName>lastnams</LastName><FirstName>dmsms</FirstName><MI>a</MI><DueDate>10/11/2017</DueDate><MeterNo>12345</MeterNo><ContactNo>09178190800</ContactNo>', NULL, '10-11-2017 10:52:24', '773387.39', '1A61172846105218', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6156105218', NULL, '2661A61105253', '10/11/2017', '771050.57'),
(119, '1A61', '00265', 'PLECO', '01010105763316951531', '1500', '10.00', '1,510.00', '<LastName>last</LastName><FirstName>first</FirstName><MI>m</MI><DueDate>10/11/2017</DueDate><MeterNo>jajajaja</MeterNo><ContactNo>09178190800</ContactNo>', NULL, '10-11-2017 13:52:21', '771050.57', '1A61172842015215', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6192015215', NULL, '1401A61135248', '10/11/2017', '769540.57'),
(120, '1A61', '00001', 'MECOA', '00535586493170505717061507', '100.00', '0.00', '100.00', NULL, NULL, '10-11-2017 14:19:36', '769540.57', '1A61172843021909', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6103021909', NULL, '1301A61142043', '10/11/2017', '769440.57'),
(121, '1A61', '00001', 'MECOA', '00100682378170505917061500', '50', '0.00', '50.00', NULL, NULL, '10-11-2017 14:26:45', '769440.57', '1A61172847022638', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6127022638', NULL, '801A61142722', '10/11/2017', '769390.57'),
(122, '1A61', '00265', 'PLECO', '01010105763316401531', '7000', '15.00', '7,015.00', '<LastName>last</LastName><FirstName>first</FirstName><MI>m</MI><DueDate>10/11/2017</DueDate><MeterNo>jajajaja66</MeterNo><ContactNo>09178190800</ContactNo>', NULL, '10-11-2017 14:27:26', '771050.57', '1A61172840022719', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6150022719', NULL, '7451A61142750', '10/11/2017', '762375.57'),
(123, '1A61', '00001', 'MECOA', '00133110558170505017061509', '89', '0.00', '89.00', NULL, NULL, '10-11-2017 14:31:32', '769390.57', '1A61172843023124', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6133023124', NULL, '1191A61143207', '10/11/2017', '762286.57'),
(124, '1A61', '00001', 'MECOA', '00121212732170929317103001', '15000.01', '0.00', '15,000.01', NULL, NULL, '10-11-2017 14:58:33', '762286.57', '1A61172849025828', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(125, '1A61', '00001', 'MECOA', '00121212732170929217103005', '100000.01', '0.00', '100,000.01', NULL, NULL, '10-11-2017 16:01:07', '762286.57', '1A61172848040102', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(126, '1A61', '00022', 'MWSIN', '54404946', '100', '0.00', '100.00', NULL, NULL, '10-11-2017 16:04:40', '762286.57', '1A61172841040435', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6141040435', NULL, '1301A61160548', '10/11/2017', '762186.57'),
(127, '1A61', '00022', 'MWSIN', '59767358', '120', '0.00', '120.00', NULL, NULL, '10-11-2017 16:09:22', '762186.57', '1A61172842040917', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6162040917', NULL, '1501A61160943', '10/11/2017', '762066.57'),
(128, '1A61', '00022', 'MWSIN', '64341111', '140', '0.00', '140.00', NULL, NULL, '10-11-2017 16:12:22', '762066.57', '1A61172841041217', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172841A6191041217', NULL, '1701A61161308', '10/11/2017', '761926.57'),
(129, '1A61', '00022', 'MWSIN', '64341111', '15000.01', '0.00', '15,000.01', NULL, NULL, '10-11-2017 16:25:43', '761926.57', '1A61172842042537', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(130, '1A61', '00001', 'MECOA', '00121212732170929317103001', '15000.01', '0.00', '15,000.01', NULL, NULL, '10-11-2017 16:28:11', '761926.57', '1A61172846042804', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(131, '1A61', '00015', 'MWCOM', '10371073', '20', '0.00', '20.00', NULL, NULL, '10-13-2017 09:05:02', '761926.57', '1A61172861090457', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6151090457', NULL, '521A61090658', '10/13/2017', '761906.57'),
(132, '1A61', '00015', 'MWCOM', '22222220', '100', '0.00', '100.00', NULL, NULL, '10-13-2017 09:13:54', '761906.57', '1A61172864091349', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6184091349', NULL, '1321A61091431', '10/13/2017', '761806.57'),
(133, '1A61', '00015', 'MWCOM', '10371073', '15000.01', '0.00', '15,000.01', NULL, NULL, '10-13-2017 09:25:26', '761806.57', '1A61172861092521', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(134, '1A61', '00009', 'GLOBE', '1042055440', '100', '0.00', '100.00', '<AccountName></AccountName><Telephone_Number>9989801</Telephone_Number>', NULL, '10-13-2017 09:58:49', '761806.57', '1A61172862095824', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6102095824', '<OtherInfo><Payment></Payment></OtherInfo>', '1321A61095917', '10/13/2017', '761706.57'),
(135, '1A61', '00009', 'GLOBE', '82759253', '230', '0.00', '230.00', '<AccountName>Juan Santos</AccountName><Telephone_Number>09172609866</Telephone_Number>', NULL, '10-13-2017 10:05:45', '761706.57', '1A61172861100526', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6141100526', '<OtherInfo><Payment></Payment></OtherInfo>', '2621A61100734', '10/13/2017', '761476.57'),
(136, '1A61', '00009', 'GLOBE', '1111111111', '225', '0.00', '225.00', '<AccountName>Hya Lao</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:14:14', '761476.57', '1A61172865101355', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(137, '1A61', '00009', 'GLOBE', '1111111111', '225', '0.00', '225.00', '<AccountName>Hya Lao</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:17:54', '761476.57', '1A61172863101746', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(138, '1A61', '00009', 'GLOBE', '1032501421', '225', '0.00', '225.00', '<AccountName>?!Juan Dela Cruz</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:27:14', '761476.57', '1A61172865102701', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(139, '1A61', '00009', 'GLOBE', '1032501421', '225', '0.00', '225.00', '<AccountName>?!@#$%</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:29:10', '761476.57', '1A61172863102901', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(140, '1A61', '00009', 'GLOBE', '1032501421', '225', '0.00', '225.00', '<AccountName>123456</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:29:45', '761476.57', '1A61172862102933', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(141, '1A61', '00009', 'GLOBE', '1032501421', '15000.01', '0.00', '15,000.01', '<AccountName>Hya Lao</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-13-2017 10:33:08', '761476.57', '1A61172861103300', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(142, '1A61', '00214', 'PLDT6', '0160576391', '120', '0.00', '120.00', '<PhoneNumber>9182609866</PhoneNumber><Service>PL</Service>', NULL, '10-13-2017 10:55:47', '761476.57', '1A61172864105528', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(143, '1A61', '00214', 'PLDT6', '0160576391', '120', '0.00', '120.00', '<PhoneNumber>9182609866</PhoneNumber><Service>PL</Service>', NULL, '10-13-2017 13:54:01', '761476.57', '1A61172860015337', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6170015337', NULL, '1521A61135501', '10/13/2017', '761356.57'),
(144, '1A61', '00214', 'PLDT6', '0224734239', '15000.01', '0.00', '15,000.01', '<PhoneNumber>9182609866</PhoneNumber><Service>PL</Service>', NULL, '10-13-2017 14:11:29', '761356.57', '1A61172860021110', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(145, '1A61', '00046', 'INNOV', '841051762', '20', '0.00', '20.00', '<AccountName></AccountName><Telephone_Number>9989801</Telephone_Number><DueDate>10/13/2017</DueDate>', NULL, '10-13-2017 14:26:07', '761356.57', '1A61172865022601', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6145022601', NULL, '521A61142631', '10/13/2017', '761336.57'),
(146, '1A61', '00046', 'INNOV', '100259508', '100', '0.00', '100.00', '<AccountName>Maricar Villasotes</AccountName><Telephone_Number>09182609866</Telephone_Number><DueDate>10/13/2017</DueDate>', NULL, '10-13-2017 14:29:48', '761336.57', '1A61172863022943', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6123022943', NULL, '1321A61143032', '10/13/2017', '761236.57'),
(147, '1A61', '00046', 'INNOV', '859379641', '120', '0.00', '120.00', '<AccountName>Catherine Penolio</AccountName><Telephone_Number>09172609866</Telephone_Number><DueDate>10/12/2017</DueDate>', NULL, '10-13-2017 14:32:16', '761236.57', '1A61172865023210', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6135023210', NULL, '1521A61143234', '10/13/2017', '761116.57'),
(148, '1A61', '00046', 'INNOV', '861533397', '150', '0.00', '150.00', '<AccountName>Kiel Diaz</AccountName><Telephone_Number>9989870</Telephone_Number><DueDate>10/14/2017</DueDate>', NULL, '10-13-2017 14:34:08', '761116.57', '1A61172861023403', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172861A6101023403', NULL, '1821A61143426', '10/13/2017', '760966.57'),
(149, '1A61', '00046', 'INNOV', '100259508', '15000.01', '0.00', '15,000.01', '<AccountName>Hya Lao</AccountName><Telephone_Number>09182609866</Telephone_Number><DueDate>10/13/2017</DueDate>', NULL, '10-13-2017 14:51:59', '760966.57', '1A61172867025154', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(150, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', NULL, NULL, '10-13-2017 16:08:38', '760966.57', '1A61172863040830', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(151, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', NULL, NULL, '10-13-2017 16:09:51', '760966.57', '1A61172865040946', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(152, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', NULL, NULL, '10-13-2017 16:13:23', '760966.57', '1A61172864041317', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(153, '1A61', '00132', 'VIECO', '23136100007', '20', '0.00', '20.00', '<FirstName>tsd</FirstName><LastName>jj</LastName>', NULL, '10-13-2017 16:30:09', '760966.57', '1A61172868043004', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(154, '1A61', '00022', 'MWSIN', '55653219', '200', '0.00', '200.00', NULL, NULL, '10-16-2017 13:37:43', '760966.57', '1A61172893013738', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172891A6153013738', NULL, '2351A61133801', '10/16/2017', '760766.57'),
(155, '1A61', '00132', 'VIECO', '23136100007', '200', '0.00', '200.00', '<FirstName>tedt</FirstName><LastName>tff</LastName>', NULL, '10-16-2017 16:21:24', '760766.57', '1A61172899042119', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(156, '1A61', '00022', 'MWSIN', '54404946', '100.00', '0.00', '100.00', NULL, NULL, '10-20-2017 15:21:58', '725000', '1A61172932032153', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6122032153', NULL, '1301A61152221', '10/20/2017', '724900'),
(157, '1A61', '00022', 'MWSIN', '59767358', '120.00', '0.00', '120.00', NULL, NULL, '10-20-2017 15:23:52', '724900', '1A61172937032348', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6107032348', NULL, '1501A61152424', '10/20/2017', '724780'),
(158, '1A61', '00022', 'MWSIN', '64341111', '140.00', '0.00', '140.00', NULL, NULL, '10-20-2017 15:25:29', '724780', '1A61172930032524', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6180032524', NULL, '1701A61152553', '10/20/2017', '724640'),
(159, '1A61', '00015', 'MWCOM', '10371073', '20.00', '0.00', '20.00', NULL, NULL, '10-20-2017 15:41:21', '724640', '1A61172935034113', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6195034113', NULL, '501A61154137', '10/20/2017', '724620'),
(160, '1A61', '00015', 'MWCOM', '22222220', '100.00', '0.00', '100.00', NULL, NULL, '10-20-2017 15:42:44', '724620', '1A61172937034239', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6157034239', NULL, '1301A61154301', '10/20/2017', '724520'),
(161, '1A61', '00214', 'PLDT6', '0160576391', '120.00', '7.00', '127.00', '<PhoneNumber>9182609866</PhoneNumber><Service>PL</Service>', NULL, '10-20-2017 16:36:57', '724520', '1A61172932043650', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6102043650', NULL, '1571A61163716', '10/20/2017', '724393'),
(162, '1A61', '00214', 'PLDT6', '0224734239', '230.00', '7.00', '237.00', '<PhoneNumber>9182609866</PhoneNumber><Service>PD</Service>', NULL, '10-20-2017 16:38:28', '724393', '1A61172934043822', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6134043822', NULL, '2671A61163850', '10/20/2017', '724156'),
(163, '1A61', '00009', 'GLOBE', '1042055440', '100.00', '0.00', '100.00', '<AccountName></AccountName><Telephone_Number>9989801</Telephone_Number>', NULL, '10-20-2017 16:57:15', '724156', '1A61172939045654', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6109045654', '<OtherInfo><Payment></Payment></OtherInfo>', '1301A61165735', '10/20/2017', '724056'),
(164, '1A61', '00009', 'GLOBE', '1111111111', '230.00', '0.00', '230.00', '<AccountName>Hya Lao</AccountName><Telephone_Number>9989801</Telephone_Number>', NULL, '10-20-2017 17:00:35', '724056', '1A61172933050018', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(165, '1A61', '00009', 'GLOBE', '82759253', '230.00', '0.00', '230.00', '<AccountName>Juan Santos</AccountName><Telephone_Number>09172609866</Telephone_Number>', NULL, '10-20-2017 17:07:28', '724056', '1A61172934050710', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6114050710', '<OtherInfo><Payment></Payment></OtherInfo>', '2601A61170746', '10/20/2017', '723826'),
(166, '1A61', '00009', 'GLOBE', '1032501421', '340.00', '0.00', '340.00', '<AccountName>Juan Reyes</AccountName><Telephone_Number>9989870</Telephone_Number>', NULL, '10-20-2017 17:09:24', '723826', '1A61172939050912', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A6119050912', '<OtherInfo><Payment></Payment></OtherInfo>', '3701A61170940', '10/20/2017', '723486'),
(167, '1A61', '00022', 'MWSIN', '55653219', '20', '0.00', '20.00', NULL, NULL, '10-23-2017 10:15:41', '723486', '1A61172965101533', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6135101533', NULL, '531A61101603', '10/23/2017', '723466'),
(168, '1A61', '00132', 'VIECO', '23136100007', '20', '0.00', '20.00', '<FirstName>tsdt</FirstName><LastName>yedt</LastName>', NULL, '10-23-2017 10:19:56', '723466', '1A61172965101951', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6175101951', NULL, '531A61102012', '10/23/2017', '723446'),
(169, '1A61', '00046', 'INNOV', '841051762', '20.00', '0.00', '20.00', '<AccountName></AccountName><Telephone_Number>9989801</Telephone_Number><DueDate>10/23/2017</DueDate>', NULL, '10-23-2017 10:42:10', '723446', '1A61172964104206', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6154104206', NULL, '531A61104226', '10/23/2017', '723426'),
(170, '1A61', '00046', 'INNOV', '100259508', '100.00', '0.00', '100.00', '<AccountName>Maricar Villasotes</AccountName><Telephone_Number>09182609866</Telephone_Number><DueDate>10/23/2017</DueDate>', NULL, '10-23-2017 10:44:46', '723426', '1A61172969104438', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6179104438', NULL, '1331A61104505', '10/23/2017', '723326'),
(171, '1A61', '00046', 'INNOV', '859379641', '120.00', '0.00', '120.00', '<AccountName>Catherie Penolio</AccountName><Telephone_Number>09172609866</Telephone_Number><DueDate>10/22/2017</DueDate>', NULL, '10-23-2017 10:55:01', '723326', '1A61172965105456', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6185105456', NULL, '1531A61105515', '10/23/2017', '723206'),
(172, '1A61', '00046', 'INNOV', '861533397', '150.00', '0.00', '150.00', '<AccountName>Kiel Diaz</AccountName><Telephone_Number>9979870</Telephone_Number><DueDate>10/24/2017</DueDate>', NULL, '10-23-2017 10:56:32', '723206', '1A61172962105628', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172961A6102105628', NULL, '1831A61105651', '10/23/2017', '723056'),
(173, '1A61', '00158', 'CGNAL', '9000000914', '120.00', '0.00', '120.00', '<FirstName>Juan</FirstName><LastName>Dela Cruz</LastName><MI></MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '10-24-2017 09:57:56', '723056', '1A61172978095750', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172971A6138095750', NULL, '1541A61095823', '10/24/2017', '722936');
INSERT INTO `tb_tran_1a61_old` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(174, '1A61', '00158', 'CGNAL', '9006387195', '230.00', '0.00', '230.00', '<FirstName>Christal</FirstName><LastName>Reyes</LastName><MI>N</MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '10-24-2017 10:00:58', '722936', '1A61172979100053', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172971A6179100053', NULL, '2641A61100119', '10/24/2017', '722706'),
(175, '1A61', '00265', 'PLECO', '01070122050716955573', '1560.25', '10.00', '1,570.25', '<LastName>aa</LastName><FirstName>hh</FirstName><MI>a</MI><DueDate>10/24/2017</DueDate><MeterNo>s</MeterNo><ContactNo>12345678901</ContactNo>', NULL, '10-24-2017 11:23:17', '722706', '1A61172973112312', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '172971A6153112312', NULL, '1041A61112602', '10/24/2017', '721135.75'),
(176, '1A61', '00265', 'PLECO', '03015000138716610598', '5010', '15.00', '5,025.00', '<LastName>abab</LastName><FirstName>ahaha</FirstName><MI>aa</MI><DueDate>10/24/2017</DueDate><MeterNo>ahhaha778</MeterNo><ContactNo>89704516707</ContactNo>', NULL, '10-24-2017 11:30:37', '721135.75', '1A61172972113031', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '172971A6102113031', NULL, '5591A61113056', '10/24/2017', '716110.75'),
(177, '1A61', '00005', 'SMART', '0194380380', '100', '0.00', '100.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>1234</TelephoneNumber><Product>b</Product>', NULL, '10-24-2017 17:14:18', '716110.75', '1A61172977051412', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(178, '1A61', '00132', 'VIECO', '90561200008', '450.00', '0.00', '450.00', '<FirstName>Juan</FirstName><LastName>Dela Cruz</LastName>', NULL, '10-25-2017 09:29:33', '716110.75', '1A61172989092928', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6159092928', NULL, '4851A61093012', '10/25/2017', '715660.75'),
(179, '1A61', '00132', 'VIECO', '74299100003', '560.00', '0.00', '560.00', '<FirstName>Justine</FirstName><LastName>Buduan</LastName>', NULL, '10-25-2017 09:31:52', '715660.75', '1A61172983093147', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6173093147', NULL, '5951A61093212', '10/25/2017', '715100.75'),
(180, '1A61', '00132', 'VIECO', '50438469392', '670.00', '0.00', '670.00', '<FirstName>Rolaine</FirstName><LastName>Cruz</LastName>', NULL, '10-25-2017 09:33:22', '715100.75', '1A61172988093317', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6198093317', NULL, '7051A61093349', '10/25/2017', '714430.75'),
(181, '1A61', '00005', 'SMART', '0208471929', '120.00', '0.00', '120.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>b</Product>', NULL, '10-25-2017 09:44:06', '714430.75', '1A61172986094355', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6106094355', NULL, '1551A61094506', '10/25/2017', '714310.75'),
(182, '1A61', '00005', 'SMART', '0137638304', '200.00', '0.00', '200.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>s</Product>', NULL, '10-25-2017 09:46:49', '714310.75', '1A61172986094644', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6146094644', NULL, '2351A61094712', '10/25/2017', '714110.75'),
(183, '1A61', '00005', 'SMART', '0208471929', '150.00', '0.00', '150.00', '<ServiceReferenceNumber>1234567890</ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>b</Product>', NULL, '10-25-2017 09:49:57', '714110.75', '1A61172982094940', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6182094940', NULL, '1851A61095019', '10/25/2017', '713960.75'),
(184, '1A61', '00005', 'SMART', '0137638304', '230.00', '0.00', '230.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>6565596</TelephoneNumber><Product>s</Product>', NULL, '10-25-2017 09:51:33', '713960.75', '1A61172986095128', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6166095128', NULL, '2651A61095204', '10/25/2017', '713730.75'),
(185, '1A61', '00005', 'SMART', '0176234369', '340.00', '0.00', '340.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>1234567</TelephoneNumber><Product>c</Product>', NULL, '10-25-2017 09:53:21', '713730.75', '1A61172985095311', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6165095311', NULL, '3751A61095341', '10/25/2017', '713390.75'),
(186, '1A61', '00005', 'SMART', '0208471929', '50.00', '0.00', '50.00', '<ServiceReferenceNumber>454823122</ServiceReferenceNumber><TelephoneNumber></TelephoneNumber><Product>b</Product>', NULL, '10-25-2017 09:56:31', '713390.75', '1A61172987095625', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(187, '1A61', '00005', 'SMART', '0000907832', '100.00', '0.00', '100.00', '<ServiceReferenceNumber></ServiceReferenceNumber><TelephoneNumber>9989801</TelephoneNumber><Product>c</Product>', NULL, '10-25-2017 10:16:47', '713390.75', '1A61172980101642', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6150101642', NULL, '1351A61101706', '10/25/2017', '713290.75'),
(188, '1A61', '00003', 'SKY01', '632642717', '130.00', '0.00', '130.00', '<ServiceType>0</ServiceType><DueDate>10/27/2017</DueDate><BillDate>10/03/2017</BillDate>', NULL, '10-25-2017 13:54:58', '713290.75', '1A61172987015453', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6187015453', NULL, '1651A61135517', '10/25/2017', '713160.75'),
(189, '1A61', '00003', 'SKY01', '629598405', '120.00', '0.00', '120.00', '<ServiceType>0</ServiceType><DueDate>10/25/2017</DueDate><BillDate>10/01/2017</BillDate>', NULL, '10-25-2017 13:57:25', '713160.75', '1A61172989015720', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(190, '1A61', '00003', 'SKY01', '629598405', '120.00', '0.00', '120.00', '<ServiceType>1</ServiceType><DueDate>10/25/2017</DueDate><BillDate>10/01/2017</BillDate>', NULL, '10-25-2017 13:58:58', '713160.75', '1A61172984015853', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172981A6194015853', NULL, '1551A61135917', '10/25/2017', '713040.75'),
(191, '1A61', '00068', 'PILTS', '1602751740', '700', '0.00', '700.00', '<AccountName>test</AccountName>', NULL, '10-26-2017 09:09:16', '713040.75', '1A61172996090911', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(192, '1A61', '00239', 'MPAY1', 'MPMKOC6TE5', '26', '0.00', '26.00', '<ContactNumber>333</ContactNumber>', NULL, '10-26-2017 10:29:54', '713040.75', '1A61172996102948', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(193, '1A61', '00239', 'MPAY1', 'MPMKOC6TE5', '26', '0.00', '26.00', '<ContactNumber>66</ContactNumber>', NULL, '10-26-2017 10:35:59', '713040.75', '1A61172997103554', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(194, '1A61', '00239', 'MPAY1', 'MPPPPCNQL2', '26', '0.00', '26.00', '<ContactNumber>336</ContactNumber>', NULL, '10-26-2017 10:37:29', '713040.75', '1A61172999103724', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(195, '1A61', '00265', 'PLECO', '01070122050716947003', '1560.75', '10.00', '1,570.75', '<LastName>Reyes</LastName><FirstName>Jessica</FirstName><MI>K</MI><DueDate>10/26/2017</DueDate><MeterNo>12345</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-26-2017 10:44:48', '713040.75', '1A61172998104442', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6188104442', NULL, '1061A61104534', '10/26/2017', '711470'),
(196, '1A61', '00265', 'PLECO', '26026002206417114054', '75.01', '10.00', '85.01', '<LastName>Lingad</LastName><FirstName>Pauline</FirstName><MI></MI><DueDate>10/26/2017</DueDate><MeterNo>6325</MeterNo><ContactNo>09078899445</ContactNo>', NULL, '10-26-2017 10:54:07', '711470', '1A61172991105403', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6151105403', NULL, '1211A61105438', '10/26/2017', '711384.99'),
(197, '1A61', '00265', 'PLECO', '10003018831917066550', '550.05', '10.00', '560.05', '<LastName>Ortiz</LastName><FirstName>Guiller</FirstName><MI>M</MI><DueDate>10/26/2017</DueDate><MeterNo>AAP1</MeterNo><ContactNo>09991523685</ContactNo>', NULL, '10-26-2017 11:06:20', '711384.99', '1A61172998110615', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6168110615', NULL, '5961A61110637', '10/26/2017', '710824.94'),
(198, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>X</LastName><FirstName>X</FirstName><MI>X</MI><DueDate>10/26/2017</DueDate><MeterNo>ett</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-26-2017 11:31:10', '710824.94', '1A61172993113105', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(199, '1A61', '00239', 'MPAY1', 'MPJWTGQQY7', '26', '0.00', '26.00', '<ContactNumber>9989801</ContactNumber>', NULL, '10-26-2017 13:32:50', '710824.94', '1A61172990013242', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6150013242', '<OtherInfo><AccountName>DUMMY ACCOUNT</AccountName><TransactionId>59F172B8DC865</TransactionId><Source>National Bureau of Investigation (DEV)</Source><BillerCode>NBI02</BillerCode></OtherInfo>', '621A61133336', '10/26/2017', '710798.94'),
(200, '1A61', '00068', 'PILTS', '1534781038', '450.00', '0.00', '450.00', '<AccountName>Camilla Abella</AccountName>', NULL, '10-26-2017 13:59:56', '710798.94', '1A61172992015951', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6162015951', NULL, '4861A61140029', '10/26/2017', '710348.94'),
(201, '1A61', '00068', 'PILTS', '1214100403', '350.00', '0.00', '350.00', '<AccountName>Kean Capistral</AccountName>', NULL, '10-26-2017 14:12:46', '710348.94', '1A61172998021241', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6168021241', NULL, '3861A61141316', '10/26/2017', '709998.94'),
(202, '1A61', '00068', 'PILTS', '1527880218', '450.00', '0.00', '450.00', '<AccountName>Justin Timberlake</AccountName>', NULL, '10-26-2017 14:17:07', '709998.94', '1A61172998021701', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172991A6198021701', NULL, '4861A61141728', '10/26/2017', '709548.94'),
(203, '1A61', '00265', 'PLECO', '01010105763316602978', '5001.00', '15.00', '5,016.00', '<LastName>Olfindo</LastName><FirstName>Dan</FirstName><MI>C</MI><DueDate>10/27/2017</DueDate><MeterNo>1234AB</MeterNo><ContactNo>09164522224</ContactNo>', NULL, '10-27-2017 10:25:01', '709548.94', '1A61173003102455', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173001A6193102455', NULL, '5531A61102605', '10/27/2017', '704532.94'),
(204, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>Lao</LastName><FirstName>Hya</FirstName><MI>C</MI><DueDate>10/27/2017</DueDate><MeterNo>qwe</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 10:31:27', '704532.94', '1A61173005103122', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(205, '1A61', '00265', 'PLECO', '01070122050716907003', '1960.75', '10.00', '1,970.75', '<LastName>Reyes</LastName><FirstName>Jessica</FirstName><MI>K</MI><MeterNo>12345</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 11:02:24', '704532.94', '1A61173002110219', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173001A6152110219', NULL, '2071A61110244', '10/27/2017', '702562.19'),
(206, '1A61', '00265', 'PLECO', '01010105763316603059', '5001.00', '15.00', '5,016.00', '<LastName>Olfindo</LastName><FirstName>Dan</FirstName><MI>C</MI><MeterNo>1234AB</MeterNo><ContactNo>09164522224</ContactNo>', NULL, '10-27-2017 11:05:21', '702562.19', '1A61173003110516', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173001A6183110516', NULL, '5531A61110539', '10/27/2017', '697546.19'),
(207, '1A61', '00265', 'PLECO', '26026002206417095268', '75.01', '10.00', '85.01', '<LastName>Lingad</LastName><FirstName>Pauline</FirstName><MI></MI><MeterNo>6325</MeterNo><ContactNo>09078899445</ContactNo>', NULL, '10-27-2017 11:07:48', '697546.19', '1A61173001110743', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173001A6111110743', NULL, '1221A61110805', '10/27/2017', '697461.18'),
(208, '1A61', '00265', 'PLECO', '10003018831917066712', '550.05', '10.00', '560.05', '<LastName>Ortiz</LastName><FirstName>Guiller</FirstName><MI>M</MI><MeterNo>AAP1</MeterNo><ContactNo>09991523685</ContactNo>', NULL, '10-27-2017 11:10:25', '697461.18', '1A61173002111020', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173001A6112111020', NULL, '5971A61111042', '10/27/2017', '696901.13'),
(209, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>X</LastName><FirstName>X</FirstName><MI>X</MI><MeterNo>X</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 11:12:05', '696901.13', '1A61173007111200', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(210, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>x</LastName><FirstName>x</FirstName><MI>C</MI><MeterNo>qwe</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 11:16:48', '696901.13', '1A61173007111624', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(211, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>x</LastName><FirstName>x</FirstName><MI>C</MI><MeterNo>1234</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 11:20:55', '696901.13', '1A61173005112050', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(212, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>Lao</LastName><FirstName>Hya</FirstName><MI>C</MI><MeterNo>1234</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 11:27:33', '696901.13', '1A61173008112728', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(213, '1A61', '00265', 'PLECO', '40041100695213612644', '35000.01', '15.00', '35,015.01', '<LastName>Lao</LastName><FirstName>Hya</FirstName><MI>C</MI><MeterNo>12345</MeterNo><ContactNo>09182609866</ContactNo>', NULL, '10-27-2017 13:19:13', '696901.13', '1A61173006011907', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(214, '1A61', '00265', 'PLECO', '40041100695213602978', '35001.00', '15.00', '35,016.00', '<LastName>f</LastName><FirstName>f</FirstName><MI>f</MI><MeterNo>f</MeterNo><ContactNo>12345678900</ContactNo>', NULL, '10-27-2017 13:24:34', '696901.13', '1A61173009012429', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(215, '1A61', '00132', 'VIECO', '23136100007', '901.13', '0.00', '901.13', '<FirstName>test</FirstName><LastName>test</LastName>', NULL, '11-02-2017 13:07:38', '696901.13', '1A61173069010732', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173061A6109010732', NULL, '9311A61130756', '11/02/2017', '696901.13'),
(216, '1A61', '00022', 'MWSIN', '55653219', '900', '0.00', '900.00', NULL, NULL, '11-02-2017 13:20:53', '696901.13', '1A61173065012048', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173061A6105012048', NULL, '9301A61132107', '11/02/2017', '696901.13'),
(217, '1A61', '00132', 'VIECO', '23136100007', '66', '0.00', '66.00', '<FirstName>h</FirstName><LastName>h</LastName>', NULL, '11-02-2017 13:29:13', '696901.13', '1A61173066012908', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173061A6166012908', NULL, '961A61132929', '11/02/2017', '696901.13'),
(218, '1A61', '00158', 'CGNAL', '9018773923', '350', '0.00', '350.00', '<FirstName>test</FirstName><LastName>testing</LastName><MI></MI><ExternalEntityName>BAYAD</ExternalEntityName>', NULL, '11-07-2017 15:37:05', '696901.13', '1A61173113033657', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173111A6153033657', NULL, '3851A61153722', '11/07/2017', '696551.13'),
(219, '1A61', '00132', 'VIECO', '23136100007', '551.13', '0.00', '551.13', '<FirstName>you</FirstName><LastName>are</LastName>', NULL, '11-07-2017 15:38:21', '696551.13', '1A61173110033815', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173111A6180033815', NULL, '5861A61153846', '11/07/2017', '696000'),
(220, '1A61', '00265', 'PLECO', '98745632109617012140', '1000', '10.00', '1,010.00', '<LastName>test</LastName><FirstName>testing</FirstName><MI>t</MI><MeterNo>1234567</MeterNo><ContactNo>09392580863</ContactNo>', NULL, '11-07-2017 15:41:29', '696000', '1A61173113034124', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173111A6123034124', NULL, '1451A61154145', '11/07/2017', '694990'),
(221, '1A61', '00022', 'MWSIN', '55653219', '990', '0.00', '990.00', NULL, NULL, '11-21-2017 15:57:15', '694990', '1A61173254035708', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173251A6154035708', NULL, '1211A61155730', '11/21/2017', '694000'),
(222, '1A61', '00265', 'PLECO', '85214796324216956539', '1560.25', '10.00', '1,570.25', '<LastName>ye</LastName><FirstName>t</FirstName><MI>y</MI><MeterNo>yuuuu</MeterNo><ContactNo>33333333333</ContactNo>', NULL, '11-21-2017 16:13:21', '694000', '1A61173252041315', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173251A6192041315', NULL, '1011A61161410', '11/21/2017', '692429.75'),
(223, '1A61', '00015', 'MWCOM', '22222220', '1000', '0.00', '1,000.00', NULL, NULL, '12-04-2017 10:20:16', '692429.75', '1A61173389101953', 'Cherry Mobile', '121.144 - 19.114', 0, 'Transaction successful.', '173381A6119101953', NULL, '1321A61102350', '12/04/2017', '691429.75'),
(224, '1A61', '00015', 'MWCOM', '22222220', '500', '0.00', '500.00', NULL, NULL, '12-04-2017 11:03:54', '691429.75', '1A61173386110348', 'Cherry Mobile', '121.144 - 19.114', 0, 'Transaction successful.', '173381A6116110348', NULL, '5321A61110833', '12/04/2017', '690929.75'),
(225, '1A61', '00015', 'MWCOM', '15325854', '929.75', '0.00', '929.75', NULL, NULL, '12-04-2017 11:09:08', '690929.75', '1A61173389110903', 'Cherry Mobile', '121.144 - 19.114', 0, 'Transaction successful.', '173381A6149110903', NULL, '9611A61111229', '12/04/2017', '690000'),
(226, '1A61', '00015', 'MWCOM', '22222220', '500', '0.00', '500.00', NULL, NULL, '12-04-2017 13:52:38', '690000', '1A61173385015233', 'Cherry Mobile', '121.144 - 19.114', 0, 'Transaction successful.', '173381A6125015233', NULL, '5321A61135557', '12/04/2017', '689500'),
(227, '1A61', '00265', 'PLECO', '03015000138717071455', '501', '10.00', '511.00', '<LastName>hhh</LastName><FirstName>hahah</FirstName><MI>a</MI><MeterNo>12hje8</MeterNo><ContactNo>91895955585</ContactNo>', NULL, '12-05-2017 16:15:28', '689500', '1A61173391041521', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '173391A6101041521', NULL, '5441A61161852', '12/05/2017', '688989'),
(228, '1A61', '00265', 'PLECO', '01001015951317919713', '911.37', '10.00', '921.37', '<LastName>Marvin</LastName><FirstName>opiala</FirstName><MI></MI><MeterNo>10321002959</MeterNo><ContactNo>12345678907</ContactNo>', NULL, '12-18-2017 14:14:20', '688989', '1A61173522021415', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(229, '1A61', '00265', 'PLECO', '01001015951317919713', '911.37', '10.00', '921.37', '<LastName>marvin</LastName><FirstName>opiala</FirstName><MI></MI><MeterNo>10321002959</MeterNo><ContactNo></ContactNo>', NULL, '12-18-2017 14:22:50', '688989', '1A61173522022230', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(230, '1A61', '00015', 'MWCOM', '18951519', '35000', '0.00', '35,000.00', NULL, NULL, '12-18-2017 14:43:57', '688989', '1A61173521024352', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173521A6161024352', NULL, '3371A61144727', '12/18/2017', '653989'),
(231, '1A61', '00022', 'MWSIN', '55653219', '35000', '0.00', '35,000.00', NULL, NULL, '12-18-2017 14:44:55', '653989', '1A61173521024451', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173521A6141024451', NULL, '3371A61144823', '12/18/2017', '618989'),
(232, '1A61', '00132', 'VIECO', '23136100007', '35000', '0.00', '35,000.00', '<FirstName>test</FirstName><LastName>ter</LastName>', NULL, '12-18-2017 14:45:45', '618989', '1A61173523024540', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173521A6113024540', NULL, '3371A61144917', '12/18/2017', '583989'),
(233, '1A61', '00265', 'PLECO', '01001015951317919713', '911.37', '10.00', '921.37', '<LastName>w</LastName><FirstName>w</FirstName><MI></MI><MeterNo>1</MeterNo><ContactNo></ContactNo>', NULL, '12-18-2017 14:49:17', '583989', '1A61173523024912', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(234, '1A61', '00015', 'MWCOM', '18951519', '300000', '0.00', '300,000.00', NULL, NULL, '12-18-2017 14:51:48', '583989', '1A61173520025143', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173521A6110025143', NULL, '3371A61145518', '12/18/2017', '283989'),
(235, '1A61', '00015', 'MWCOM', '18951519', '263989', '0.00', '263,989.00', NULL, NULL, '12-18-2017 14:52:45', '283989', '1A61173525025238', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '173521A6125025238', NULL, '2261A61145626', '12/18/2017', '20000'),
(236, '1A61', '00265', 'PLECO', '01001000010017761607', '2492.43', '10.00', '2,502.43', '<LastName>queano</LastName><FirstName>rosie</FirstName><MI></MI><MeterNo>16688</MeterNo><ContactNo></ContactNo>', NULL, '12-19-2017 16:13:18', '20000', '1A61173536041312', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '173531A6146041312', NULL, '2401A61161654', '12/19/2017', '17497.57'),
(237, '1A61', '00265', 'PLECO', '01001000100317761607', '2492.43', '10.00', '2,502.43', '<LastName>queano</LastName><FirstName>rosie</FirstName><MI></MI><MeterNo>19796488</MeterNo><ContactNo></ContactNo>', NULL, '01-08-2018 11:06:38', '17497.57', '1A61180083110628', 'LGE', '121.144 - 19.114', 0, 'Transaction successful.', '180081A6113110628', NULL, '2391A61110719', '01/08/2018', '14995.14'),
(238, '1A61', '00265', 'PLECO', '02023702223918001169', '100', '10.00', '110.00', '<LastName>test</LastName><FirstName>testing</FirstName><MI></MI><MeterNo>0202370222</MeterNo><ContactNo>123466</ContactNo>', NULL, '01-10-2018 10:58:42', '14995.14', '1A61180108105837', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180101A6168105837', NULL, '1401A61110111', '01/10/2018', '14885.14'),
(239, '1A61', '00265', 'PLECO', '78948562187817511169', '5000', '10.00', '5,010.00', '<LastName>retest</LastName><FirstName>test</FirstName><MI></MI><MeterNo>7894856218</MeterNo><ContactNo>12346</ContactNo>', NULL, '01-10-2018 11:05:32', '14885.14', '1A61180104110527', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180101A6144110527', NULL, '5401A61110559', '01/10/2018', '9875.14'),
(240, '1A61', '00001', 'MECOA', '01775759662180109018012405', '217.33', '0.00', '217.33', NULL, NULL, '01-11-2018 10:52:11', '9875.14', '1A61180113105205', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6123105205', NULL, '2481A61105239', '01/11/2018', '9657.81'),
(241, '1A61', '00265', 'PLECO', '02023702362817986826', '250', '10.00', '260.00', '<LastName>resty</LastName><FirstName>tutu</FirstName><MI></MI><MeterNo>020237023628</MeterNo><ContactNo>09394635867</ContactNo>', NULL, '01-11-2018 15:36:44', '9657.81', '1A61180111033627', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6111033627', NULL, '2911A61153748', '01/11/2018', '9402.71'),
(242, '1A61', '00265', 'PLECO', '02023702362817511726', '5001', '15.00', '5,016.00', '<LastName>redy</LastName><FirstName>redu</FirstName><MI></MI><MeterNo>0202370236</MeterNo><ContactNo>1234677</ContactNo>', NULL, '01-11-2018 15:43:42', '9402.71', '1A61180112034325', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6102034325', NULL, '5471A61154423', '01/11/2018', '4396.51'),
(243, '1A61', '00132', 'VIECO', '23136100007', '96.51', '0.00', '96.51', '<FirstName>tedt</FirstName><LastName>tesr</LastName>', NULL, '01-11-2018 15:50:27', '4396.51', '1A61180116035021', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6166035021', NULL, '1271A61155056', '01/11/2018', '4300'),
(244, '1A61', '00265', 'PLECO', '02023705362817981826', '300', '10.00', '310.00', '<LastName>test</LastName><FirstName>tss</FirstName><MI></MI><MeterNo>0202370236</MeterNo><ContactNo>09394635867</ContactNo>', NULL, '01-11-2018 16:18:31', '4300', '1A61180113041824', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6103041824', NULL, '3411A61161906', '01/11/2018', '3995'),
(245, '1A61', '00022', 'MWSIN', '55653219', '3995', '0.00', '3,995.00', NULL, NULL, '01-11-2018 16:24:16', '3995', '1A61180116042409', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '180111A6106042409', NULL, '4261A61162434', '01/11/2018', '0');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tran_1a75`
--

CREATE TABLE `tb_tran_1a75` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `biller_code` varchar(5) NOT NULL,
  `service_code` varchar(5) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount_due` varchar(11) NOT NULL,
  `pass_on` varchar(11) NOT NULL,
  `amount_to_pay` varchar(11) NOT NULL,
  `otherinfo` varchar(500) DEFAULT NULL,
  `otherinfoforpost` varchar(500) DEFAULT NULL,
  `date_validated` varchar(20) NOT NULL,
  `balance_old` varchar(15) NOT NULL,
  `partnerrefno` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `longlat` varchar(20) NOT NULL,
  `cbci_code` int(5) DEFAULT NULL,
  `cbci_message` varchar(500) DEFAULT NULL,
  `cbci_transaction_no` varchar(20) DEFAULT NULL,
  `cbci_otherinfo` varchar(500) DEFAULT NULL,
  `cbci_receipt` varchar(50) DEFAULT NULL,
  `cbci_date` varchar(10) DEFAULT NULL,
  `balance_new` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tran_1a75`
--

INSERT INTO `tb_tran_1a75` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(1, '1A75', '00132', 'VIECO', '23136100007', '120', '0.00', '120.00', '<FirstName>testing </FirstName><LastName>testing</LastName>', NULL, '10-20-2017 13:38:39', '760766.57', '1A75172930013832', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A7500013832', NULL, '1501A75133857', '10/20/2017', '760646.57');

-- --------------------------------------------------------

--
-- Table structure for table `tb_tran_1a76`
--

CREATE TABLE `tb_tran_1a76` (
  `id` int(11) NOT NULL,
  `tpa_id` varchar(5) NOT NULL,
  `biller_code` varchar(5) NOT NULL,
  `service_code` varchar(5) NOT NULL,
  `account_no` varchar(50) NOT NULL,
  `amount_due` varchar(11) NOT NULL,
  `pass_on` varchar(11) NOT NULL,
  `amount_to_pay` varchar(11) NOT NULL,
  `otherinfo` varchar(500) DEFAULT NULL,
  `otherinfoforpost` varchar(500) DEFAULT NULL,
  `date_validated` varchar(20) NOT NULL,
  `balance_old` varchar(15) NOT NULL,
  `partnerrefno` varchar(20) NOT NULL,
  `model` varchar(20) NOT NULL,
  `longlat` varchar(20) NOT NULL,
  `cbci_code` int(5) DEFAULT NULL,
  `cbci_message` varchar(500) DEFAULT NULL,
  `cbci_transaction_no` varchar(20) DEFAULT NULL,
  `cbci_otherinfo` varchar(500) DEFAULT NULL,
  `cbci_receipt` varchar(50) DEFAULT NULL,
  `cbci_date` varchar(10) DEFAULT NULL,
  `balance_new` varchar(15) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_tran_1a76`
--

INSERT INTO `tb_tran_1a76` (`id`, `tpa_id`, `biller_code`, `service_code`, `account_no`, `amount_due`, `pass_on`, `amount_to_pay`, `otherinfo`, `otherinfoforpost`, `date_validated`, `balance_old`, `partnerrefno`, `model`, `longlat`, `cbci_code`, `cbci_message`, `cbci_transaction_no`, `cbci_otherinfo`, `cbci_receipt`, `cbci_date`, `balance_new`) VALUES
(1, '1A76', '00022', 'MWSIN', '55653219', '646.57', '0.00', '646.57', NULL, NULL, '10-20-2017 13:41:19', '760646.57', '1A76172939014114', 'DUKE7', '121.144 - 19.114', NULL, NULL, NULL, NULL, NULL, NULL, NULL),
(2, '1A76', '00015', 'MWCOM', '18951519', '646.57', '0.00', '646.57', NULL, NULL, '10-20-2017 14:01:52', '760646.57', '1A76172938020146', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A7668020146', NULL, '6781A76140211', '10/20/2017', '760000'),
(3, '1A76', '00022', 'MWSIN', '55653219', '35000', '0.00', '35,000.00', NULL, NULL, '10-20-2017 14:04:10', '760000', '1A76172930020404', 'DUKE7', '121.144 - 19.114', 0, 'Transaction successful.', '172931A7640020404', NULL, '3321A76140428', '10/20/2017', '725000');

-- --------------------------------------------------------

--
-- Table structure for table `tb_unposted`
--

CREATE TABLE `tb_unposted` (
  `id` int(11) NOT NULL,
  `tpaid` varchar(5) NOT NULL,
  `validated_id` varchar(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_unposted`
--

INSERT INTO `tb_unposted` (`id`, `tpaid`, `validated_id`) VALUES
(1, '1A61', '112'),
(2, '1A61', '112'),
(3, '1A61', '112'),
(4, '1A61', '112'),
(5, '1A61', '112'),
(6, '1A61', '112'),
(7, '1A61', '112'),
(8, '1A61', '112');

-- --------------------------------------------------------

--
-- Table structure for table `tb_user`
--

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password` varchar(150) NOT NULL,
  `fname` varchar(150) NOT NULL,
  `lname` varchar(150) NOT NULL,
  `mobile` varchar(150) NOT NULL,
  `telephone` varchar(150) DEFAULT NULL,
  `model` varchar(150) DEFAULT NULL,
  `serial` varchar(150) DEFAULT NULL,
  `active` varchar(2) DEFAULT NULL,
  `ispaid` varchar(2) DEFAULT NULL,
  `referral` varchar(150) DEFAULT NULL,
  `photo` varchar(150) DEFAULT NULL,
  `package` varchar(2) DEFAULT NULL,
  `date_created` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_user`
--

INSERT INTO `tb_user` (`id`, `email`, `password`, `fname`, `lname`, `mobile`, `telephone`, `model`, `serial`, `active`, `ispaid`, `referral`, `photo`, `package`, `date_created`) VALUES
(1, '2msDDZHr3CwJRtPbCD7tMYlLDqogzsS5zpksFtdQBzw=:KSVAKCkoXyQjKCUjK19JXg==', 'mh/tiX/F/V/a5CD9yRS+hQ==:KSVAKCkoXyQjKCUjK19JXg==', 'ZQeRDlB5xRZjDJ20CIhmZA==:KSVAKCkoXyQjKCUjK19JXg==', 'pVpQNFRl7z5Cs6CjiU3H3g==:KSVAKCkoXyQjKCUjK19JXg==', 'SbWjYHySlK4f+rYiS6z6zw==:KSVAKCkoXyQjKCUjK19JXg==', 'y/aYBoEoqpTJ3J/wnEXbQw==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '1', '0', 'N/A', 'BC_Mobile_bcmobiletest1@gmail.com.jpg', 'p1', '2018-02-22 07:14:15'),
(4, 'MOb7g79UDlVfGuIcXOqlXA==:KSVAKCkoXyQjKCUjK19JXg==', '4bUWkaJT1hydKVPV3OToxA==:KSVAKCkoXyQjKCUjK19JXg==', 'ecMXAAH+b0YMsYp76I5/lQ==:KSVAKCkoXyQjKCUjK19JXg==', 'c2KTG6pFeFOdOcqXNp6DFw==:KSVAKCkoXyQjKCUjK19JXg==', 'LvrRMvVd8QLT/jel04XNWQ==:KSVAKCkoXyQjKCUjK19JXg==', 'IopjCHUKk+nt4Z5JfUgXPw==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'tedting_tyes_tes@yjm.jkk.jpg', 'p1', '2018-05-23 07:49:19'),
(5, 'afSxhsLTmNiwftfeq3qOxA==:KSVAKCkoXyQjKCUjK19JXg==', '74E/nkTANWESh4+tlh8Dlw==:KSVAKCkoXyQjKCUjK19JXg==', 'oNWtQ5eTs5zEdXzuNh+z5g==:KSVAKCkoXyQjKCUjK19JXg==', 'Bm+XCxR9GZ1ig1Rr0wd1kA==:KSVAKCkoXyQjKCUjK19JXg==', '1nysCaBykb+bkILSSXQmbw==:KSVAKCkoXyQjKCUjK19JXg==', 'y/aYBoEoqpTJ3J/wnEXbQw==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'twj_sis_zusj@gs.djd.jpg', 'p1', '2018-05-23 07:55:06'),
(6, 'VO36EfepEsLB0xkPN6KAHQ==:KSVAKCkoXyQjKCUjK19JXg==', '', '4bUWkaJT1hydKVPV3OToxA==:KSVAKCkoXyQjKCUjK19JXg==', 'UN16W864ebhTOhbnJmRtAA==:KSVAKCkoXyQjKCUjK19JXg==', 'EMZoT4srEqIrAzp/ZrSoGA==:KSVAKCkoXyQjKCUjK19JXg==', 'y/aYBoEoqpTJ3J/wnEXbQw==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'hhh_jh_ujjjj@ggh.jj.jpg', 'p1', '2018-05-23 07:59:41'),
(7, 'axsM6xQXvZXfie6eO0bWKQ==:KSVAKCkoXyQjKCUjK19JXg==', 'uXLjwCweX4HRAtuhThd4ZQ==:KSVAKCkoXyQjKCUjK19JXg==', '4bUWkaJT1hydKVPV3OToxA==:KSVAKCkoXyQjKCUjK19JXg==', 'UN16W864ebhTOhbnJmRtAA==:KSVAKCkoXyQjKCUjK19JXg==', 'EMZoT4srEqIrAzp/ZrSoGA==:KSVAKCkoXyQjKCUjK19JXg==', 'THXe3XxKAsZiz84B5E+uow==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'hhh_jh_ujjjj@ggh.jj.jpg', 'p1', '2018-05-23 08:02:17'),
(8, 'JnzwCTI5tOUe3VhbWdY9fg==:KSVAKCkoXyQjKCUjK19JXg==', 'Ck5pegVVb4idhVSl/jyZzA==:KSVAKCkoXyQjKCUjK19JXg==', 'e9YOo9rKjHxXHmgFXKZa5g==:KSVAKCkoXyQjKCUjK19JXg==', 'qBvJ0PHLTXi3fk/QqBJMyQ==:KSVAKCkoXyQjKCUjK19JXg==', 'vYd0AH3sfHKSA9nrUIISIg==:KSVAKCkoXyQjKCUjK19JXg==', 'vYd0AH3sfHKSA9nrUIISIg==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'testai_z_t@ta.zk.jpg', 'p1', '2018-05-23 08:19:02'),
(9, 'x84FmM+tskqOXKAZ8yY8EA==:KSVAKCkoXyQjKCUjK19JXg==', 'Ck5pegVVb4idhVSl/jyZzA==:KSVAKCkoXyQjKCUjK19JXg==', 'e9YOo9rKjHxXHmgFXKZa5g==:KSVAKCkoXyQjKCUjK19JXg==', 'qBvJ0PHLTXi3fk/QqBJMyQ==:KSVAKCkoXyQjKCUjK19JXg==', 'vYd0AH3sfHKSA9nrUIISIg==:KSVAKCkoXyQjKCUjK19JXg==', 'vYd0AH3sfHKSA9nrUIISIg==:KSVAKCkoXyQjKCUjK19JXg==', 'DUKE7', '0123456789ABCDEF', '0', '0', 'N/A', 'testai_z_t@ta.zk.jpg', 'p1', '2018-05-23 08:22:05'),
(10, 'f/KscwDhsgqcpEmr3ulLpuYWyEUpaXNnKAygdNq/rok=:KSVAKCkoXyQjKCUjK19JXg==', '9PV+bC7BLTjePds7JrFxEA==:KSVAKCkoXyQjKCUjK19JXg==', '7ebCrf8VFUzL+9CLBkRTgw==:KSVAKCkoXyQjKCUjK19JXg==', '4K0KTwmD67kNQQ2uyp9qsA==:KSVAKCkoXyQjKCUjK19JXg==', 'K5uE0fOwXOS8ChVpGfMscA==:KSVAKCkoXyQjKCUjK19JXg==', 'ITa0VWjvJSURnzFZrBRqFw==:KSVAKCkoXyQjKCUjK19JXg==', '', '', '1', '0', '', '', '', '2018-09-04 01:43:17');

-- --------------------------------------------------------

--
-- Table structure for table `tb_version`
--

CREATE TABLE `tb_version` (
  `id` int(11) NOT NULL,
  `version` varchar(10) NOT NULL,
  `dateadded` varchar(20) NOT NULL,
  `status` varchar(2) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tb_version`
--

INSERT INTO `tb_version` (`id`, `version`, `dateadded`, `status`) VALUES
(1, '1.0', '09/20/2017', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tb_advisory`
--
ALTER TABLE `tb_advisory`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_category`
--
ALTER TABLE `tb_category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_credentials`
--
ALTER TABLE `tb_credentials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_inquiry`
--
ALTER TABLE `tb_inquiry`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_login`
--
ALTER TABLE `tb_login`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_logs`
--
ALTER TABLE `tb_logs`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_orders`
--
ALTER TABLE `tb_orders`
  ADD PRIMARY KEY (`order_id`);

--
-- Indexes for table `tb_portal_user`
--
ALTER TABLE `tb_portal_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_posted`
--
ALTER TABLE `tb_posted`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_products`
--
ALTER TABLE `tb_products`
  ADD PRIMARY KEY (`product_id`);

--
-- Indexes for table `tb_transaction`
--
ALTER TABLE `tb_transaction`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tran_1a61`
--
ALTER TABLE `tb_tran_1a61`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tran_1a61_old`
--
ALTER TABLE `tb_tran_1a61_old`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tran_1a75`
--
ALTER TABLE `tb_tran_1a75`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_tran_1a76`
--
ALTER TABLE `tb_tran_1a76`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_unposted`
--
ALTER TABLE `tb_unposted`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_user`
--
ALTER TABLE `tb_user`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `tb_version`
--
ALTER TABLE `tb_version`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tb_advisory`
--
ALTER TABLE `tb_advisory`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_category`
--
ALTER TABLE `tb_category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_credentials`
--
ALTER TABLE `tb_credentials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `tb_inquiry`
--
ALTER TABLE `tb_inquiry`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `tb_login`
--
ALTER TABLE `tb_login`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `tb_logs`
--
ALTER TABLE `tb_logs`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_orders`
--
ALTER TABLE `tb_orders`
  MODIFY `order_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_portal_user`
--
ALTER TABLE `tb_portal_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `tb_posted`
--
ALTER TABLE `tb_posted`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tb_products`
--
ALTER TABLE `tb_products`
  MODIFY `product_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_transaction`
--
ALTER TABLE `tb_transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=270;

--
-- AUTO_INCREMENT for table `tb_tran_1a61`
--
ALTER TABLE `tb_tran_1a61`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;

--
-- AUTO_INCREMENT for table `tb_tran_1a61_old`
--
ALTER TABLE `tb_tran_1a61_old`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=246;

--
-- AUTO_INCREMENT for table `tb_tran_1a75`
--
ALTER TABLE `tb_tran_1a75`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `tb_tran_1a76`
--
ALTER TABLE `tb_tran_1a76`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `tb_unposted`
--
ALTER TABLE `tb_unposted`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `tb_user`
--
ALTER TABLE `tb_user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `tb_version`
--
ALTER TABLE `tb_version`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
