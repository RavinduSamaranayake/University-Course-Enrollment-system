-- phpMyAdmin SQL Dump
-- version 4.0.4.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 24, 2018 at 06:36 PM
-- Server version: 5.6.11
-- PHP Version: 5.5.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `crsdb`
--
CREATE DATABASE IF NOT EXISTS `crsdb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `crsdb`;

-- --------------------------------------------------------

--
-- Table structure for table `examresults`
--

CREATE TABLE IF NOT EXISTS `examresults` (
  `stid` varchar(20) NOT NULL,
  `subjectid` varchar(10) NOT NULL,
  `assignmentmrk` int(11) NOT NULL,
  `exammark` int(11) NOT NULL,
  `exampresentage` int(11) NOT NULL,
  `finalmark` int(11) NOT NULL,
  `finalgrade` varchar(5) NOT NULL,
  PRIMARY KEY (`stid`,`subjectid`),
  KEY `subjectid` (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `examresults`
--

INSERT INTO `examresults` (`stid`, `subjectid`, `assignmentmrk`, `exammark`, `exampresentage`, `finalmark`, `finalgrade`) VALUES
('U1100', 'SCS1100', 80, 90, 80, 88, 'A+'),
('U1100', 'SCS1110', 74, 76, 60, 75, 'A-'),
('U1100', 'SCS1240', 90, 92, 80, 91, 'A+');

-- --------------------------------------------------------

--
-- Table structure for table `faculty`
--

CREATE TABLE IF NOT EXISTS `faculty` (
  `facname` varchar(20) NOT NULL,
  PRIMARY KEY (`facname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `faculty`
--

INSERT INTO `faculty` (`facname`) VALUES
('Business'),
('Computing'),
('Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `instructor`
--

CREATE TABLE IF NOT EXISTS `instructor` (
  `insid` varchar(5) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `passwd` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`insid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `instructor`
--

INSERT INTO `instructor` (`insid`, `fname`, `lname`, `passwd`, `email`) VALUES
('I021', 'Nalaka', 'Gamage', '123456', 'gamage@gmail.com'),
('I023', 'Kasuni', 'Wigethunga', '2468', 'tharaka@gmail.com'),
('I042', 'Malith', 'Wijesinghe', '34567', 'wigesinghe@gmail.com'),
('I345', 'Jayan', 'Kamalka', '12345', 'kamalka@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `labmanage`
--

CREATE TABLE IF NOT EXISTS `labmanage` (
  `labid` varchar(10) NOT NULL,
  `subid` varchar(10) NOT NULL,
  `instrucid` varchar(5) NOT NULL,
  PRIMARY KEY (`labid`,`subid`,`instrucid`),
  KEY `subid` (`subid`),
  KEY `instrucid` (`instrucid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labmanage`
--

INSERT INTO `labmanage` (`labid`, `subid`, `instrucid`) VALUES
('L002', 'BS1010', 'I021'),
('L003', 'EE1110', 'I023'),
('L007', 'EE1110', 'I023'),
('L005', 'SCS1100', 'I042'),
('L005', 'SCS1100', 'I345');

-- --------------------------------------------------------

--
-- Table structure for table `labs`
--

CREATE TABLE IF NOT EXISTS `labs` (
  `labid` varchar(10) NOT NULL,
  PRIMARY KEY (`labid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `labs`
--

INSERT INTO `labs` (`labid`) VALUES
('L001'),
('L002'),
('L003'),
('L004'),
('L005'),
('L006'),
('L007'),
('L008');

-- --------------------------------------------------------

--
-- Table structure for table `lecture`
--

CREATE TABLE IF NOT EXISTS `lecture` (
  `empid` varchar(5) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `passwd` varchar(20) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `lecture`
--

INSERT INTO `lecture` (`empid`, `fname`, `lname`, `passwd`, `email`) VALUES
('D008', 'Naleen', 'Ranasinghe', '12345', 'nrd@gmail.com'),
('D015', 'Sarath', 'Marage', '1234', 'marge@gmail.com'),
('D021', 'Ajantha', 'Silva', '12345', 'ajantha@gmail.com'),
('D033', 'Charith', 'Hewage', '3456', 'hewage@gmail.com');

-- --------------------------------------------------------

--
-- Table structure for table `semesters`
--

CREATE TABLE IF NOT EXISTS `semesters` (
  `semid` varchar(5) NOT NULL,
  `facname` varchar(20) NOT NULL,
  `semno` int(1) NOT NULL,
  `year` int(1) NOT NULL,
  PRIMARY KEY (`semid`,`facname`),
  KEY `facname` (`facname`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `semesters`
--

INSERT INTO `semesters` (`semid`, `facname`, `semno`, `year`) VALUES
('S11', 'Business', 1, 1),
('S12', 'Business', 2, 1),
('S21', 'Business', 1, 2),
('S22', 'Business', 2, 2),
('S31', 'Business', 1, 3),
('S32', 'Business', 2, 3),
('S41', 'Engineering', 1, 4),
('S42', 'Engineering', 2, 4);

-- --------------------------------------------------------

--
-- Table structure for table `stsub`
--

CREATE TABLE IF NOT EXISTS `stsub` (
  `studentid` varchar(10) NOT NULL,
  `subjectid` varchar(10) NOT NULL,
  `semid` varchar(4) NOT NULL,
  PRIMARY KEY (`studentid`,`subjectid`),
  KEY `subjectid` (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `stsub`
--

INSERT INTO `stsub` (`studentid`, `subjectid`, `semid`) VALUES
('U1100', 'SCS1100', 'S11'),
('U1100', 'SCS1110', 'S11'),
('U1100', 'SCS1111', 'S11'),
('U1100', 'SCS1240', 'S11');

-- --------------------------------------------------------

--
-- Table structure for table `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `stid` varchar(10) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `passwd` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  `dob` date NOT NULL,
  `zscore` varchar(6) NOT NULL,
  `qtype` varchar(20) NOT NULL,
  `facname` varchar(20) NOT NULL,
  `enroldate` date NOT NULL,
  `semid` varchar(5) NOT NULL,
  PRIMARY KEY (`stid`),
  KEY `facname` (`facname`),
  KEY `semid` (`semid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `student`
--

INSERT INTO `student` (`stid`, `fname`, `lname`, `passwd`, `email`, `dob`, `zscore`, `qtype`, `facname`, `enroldate`, `semid`) VALUES
('P1100', 'Malith', 'Gamage', '12345', 'malith@gmail.com', '1998-08-13', '', 'Diploma', 'Computing', '2018-02-08', 'S11'),
('U1100', 'Hasini', 'Sandeepani', '12345', 'hasitharu@gmail.com', '1996-12-26', '1.414', '', 'Computing', '2018-02-06', 'S11'),
('U1101', 'Tharika', 'Samaranayake', '12345', 'tharika@gmail.com', '1998-08-05', '1.818', '', 'Engineering', '2018-02-08', 'S11'),
('U1102', 'Buddhika', 'Gamage', '123456', 'gam@gmail.com', '2000-06-13', '1.516', '', 'Computing', '2017-07-03', 'S12');

-- --------------------------------------------------------

--
-- Table structure for table `subjects`
--

CREATE TABLE IF NOT EXISTS `subjects` (
  `subid` varchar(10) NOT NULL,
  `subname` varchar(50) NOT NULL,
  `faculty` varchar(20) NOT NULL,
  `type` varchar(20) NOT NULL,
  `credits` int(1) NOT NULL,
  `numassign` int(2) NOT NULL,
  `cost` int(6) NOT NULL,
  `semid` varchar(5) NOT NULL,
  `lecid` varchar(5) NOT NULL,
  `duration` int(5) NOT NULL,
  `location` varchar(50) NOT NULL,
  PRIMARY KEY (`subid`),
  KEY `faculty` (`faculty`),
  KEY `semid` (`semid`),
  KEY `lecid` (`lecid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subjects`
--

INSERT INTO `subjects` (`subid`, `subname`, `faculty`, `type`, `credits`, `numassign`, `cost`, `semid`, `lecid`, `duration`, `location`) VALUES
('BS011', 'Business Analization', 'Business', 'Core', 3, 5, 34000, 'S11', 'D021', 35, 'B006 hall'),
('BS1010', 'Business System', 'Business', 'Optional', 3, 4, 45000, 'S11', 'D033', 40, 'B002 hall'),
('BS1110', 'Foundation Of  Business', 'Business', 'Core', 3, 5, 45000, 'S11', 'D021', 40, 'B002 hall'),
('EE1010', 'Electronic System', 'Engineering', 'Optional', 3, 4, 45000, 'S11', 'D033', 40, 'E004 hall'),
('EE1110', 'Foundation Of  Electronic Eng', 'Engineering', 'Core', 3, 5, 45000, 'S11', 'D015', 40, 'E003 hall'),
('ES1200', 'Foundation Of sivil Eng', 'Engineering', 'Core', 3, 5, 45000, 'S11', 'D033', 40, 'E002 hall'),
('SCS1100', 'Programming1', 'Computing', 'Core', 3, 5, 22000, 'S11', 'D015', 40, 'L002 hall'),
('SCS1110', 'Computer System', 'Computing', 'Optional', 3, 4, 45000, 'S11', 'D021', 40, 'L004 hall'),
('SCS1111', 'Lab1', 'Computing', 'Optional', 2, 3, 15000, 'S11', 'D008', 24, 'L003 hall'),
('SCS1240', 'Foundation Of Computing', 'Computing', 'Core', 3, 5, 45000, 'S11', 'D033', 40, 'L003 hall');

-- --------------------------------------------------------

--
-- Table structure for table `subresults`
--

CREATE TABLE IF NOT EXISTS `subresults` (
  `studentid` varchar(10) NOT NULL,
  `subjectid` varchar(10) NOT NULL,
  `compassign` int(2) NOT NULL,
  `marks` int(5) NOT NULL,
  PRIMARY KEY (`studentid`,`subjectid`),
  KEY `subjectid` (`subjectid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `subresults`
--

INSERT INTO `subresults` (`studentid`, `subjectid`, `compassign`, `marks`) VALUES
('U1100', 'SCS1100', 5, 80),
('U1100', 'SCS1110', 4, 74),
('U1100', 'SCS1240', 4, 90);

-- --------------------------------------------------------

--
-- Table structure for table ` usertable`
--

CREATE TABLE IF NOT EXISTS ` usertable` (
  `userid` varchar(20) NOT NULL,
  `fname` varchar(50) NOT NULL,
  `lname` varchar(50) NOT NULL,
  `passwd` varchar(15) NOT NULL,
  `email` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table ` usertable`
--

INSERT INTO ` usertable` (`userid`, `fname`, `lname`, `passwd`, `email`) VALUES
('Kushan95', 'Kushan', 'Ravindu', 'Kushan@13579', 'ovkravindu95@gmail.com'),
('Nav45', 'klllll', 'mklll', '1234567', 'mal@gmail.com'),
('Ravindu95', 'Ravindu', 'Samaranayake', '12345', 'Ravindu@gmail.com');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `examresults`
--
ALTER TABLE `examresults`
  ADD CONSTRAINT `examresults_ibfk_1` FOREIGN KEY (`stid`) REFERENCES `student` (`stid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `examresults_ibfk_2` FOREIGN KEY (`subjectid`) REFERENCES `subjects` (`subid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `labmanage`
--
ALTER TABLE `labmanage`
  ADD CONSTRAINT `labmanage_ibfk_1` FOREIGN KEY (`labid`) REFERENCES `labs` (`labid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `labmanage_ibfk_3` FOREIGN KEY (`subid`) REFERENCES `subjects` (`subid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `labmanage_ibfk_4` FOREIGN KEY (`instrucid`) REFERENCES `instructor` (`insid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `semesters`
--
ALTER TABLE `semesters`
  ADD CONSTRAINT `semesters_ibfk_1` FOREIGN KEY (`facname`) REFERENCES `faculty` (`facname`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `stsub`
--
ALTER TABLE `stsub`
  ADD CONSTRAINT `stsub_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`stid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `stsub_ibfk_2` FOREIGN KEY (`subjectid`) REFERENCES `subjects` (`subid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `student`
--
ALTER TABLE `student`
  ADD CONSTRAINT `student_ibfk_1` FOREIGN KEY (`facname`) REFERENCES `faculty` (`facname`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `student_ibfk_2` FOREIGN KEY (`semid`) REFERENCES `semesters` (`semid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subjects`
--
ALTER TABLE `subjects`
  ADD CONSTRAINT `subjects_ibfk_2` FOREIGN KEY (`faculty`) REFERENCES `faculty` (`facname`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `subjects_ibfk_3` FOREIGN KEY (`semid`) REFERENCES `semesters` (`semid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `subjects_ibfk_4` FOREIGN KEY (`lecid`) REFERENCES `lecture` (`empid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `subresults`
--
ALTER TABLE `subresults`
  ADD CONSTRAINT `subresults_ibfk_1` FOREIGN KEY (`studentid`) REFERENCES `student` (`stid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `subresults_ibfk_2` FOREIGN KEY (`subjectid`) REFERENCES `subjects` (`subid`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
