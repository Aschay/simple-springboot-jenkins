--
-- Database: sbdb
--
CREATE DATABASE IF NOT EXISTS sbdb DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE sbdb;

-- --------------------------------------------------------

--
-- Table structure for table hibernate_sequence
--

DROP TABLE IF EXISTS hibernate_sequence;
CREATE TABLE IF NOT EXISTS hibernate_sequence (
  next_val bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;



-- --------------------------------------------------------

--
-- Table structure for table product
--

DROP TABLE IF EXISTS product;
CREATE TABLE IF NOT EXISTS product (
  id int(11) NOT NULL,
  brand varchar(255) COLLATE utf8_bin DEFAULT NULL,
  service_tag varchar(255) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;