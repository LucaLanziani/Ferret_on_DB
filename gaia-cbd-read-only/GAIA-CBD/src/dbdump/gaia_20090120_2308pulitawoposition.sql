-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.0.67-0ubuntu6


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema gaia
--

CREATE DATABASE IF NOT EXISTS gaia;
USE gaia;

--
-- Definition of table `gaia`.`Atom`
--

DROP TABLE IF EXISTS `gaia`.`Atom`;
CREATE TABLE  `gaia`.`Atom` (
  `ID_ATOM` int(11) NOT NULL auto_increment,
  `CONSTANT` varchar(50) default NULL,
  `FATHER` int(11) default NULL,
  `TYPE` int(11) NOT NULL,
  `EXCHANGE_ID` int(11) NOT NULL,
  `FK_REFER` int(11) default NULL,
  `SIDE` varchar(2) NOT NULL,
  `EQUALITY` int(11) default NULL,
  `INEQUALITY_CONSTANT` varchar(50) default NULL,
  PRIMARY KEY  (`ID_ATOM`),
  KEY `father_fk` (`FATHER`),
  KEY `type_fk` (`TYPE`),
  KEY `refer_fk` (`FK_REFER`),
  KEY `se_fk` (`EXCHANGE_ID`),
  KEY `equality_fk` (`EQUALITY`),
  CONSTRAINT `equality_fk` FOREIGN KEY (`EQUALITY`) REFERENCES `Atom` (`ID_ATOM`) ON DELETE SET NULL ON UPDATE NO ACTION,
  CONSTRAINT `father_fk` FOREIGN KEY (`FATHER`) REFERENCES `Atom` (`ID_ATOM`),
  CONSTRAINT `refer_fk` FOREIGN KEY (`FK_REFER`) REFERENCES `Atom` (`ID_ATOM`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `se_fk` FOREIGN KEY (`EXCHANGE_ID`) REFERENCES `SchemaExchange` (`ID_SE`),
  CONSTRAINT `type_fk` FOREIGN KEY (`TYPE`) REFERENCES `AtomType` (`ID_TYPE`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaia`.`Atom`
--

/*!40000 ALTER TABLE `Atom` DISABLE KEYS */;
LOCK TABLES `Atom` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `Atom` ENABLE KEYS */;


--
-- Definition of table `gaia`.`AtomType`
--

DROP TABLE IF EXISTS `gaia`.`AtomType`;
CREATE TABLE  `gaia`.`AtomType` (
  `ID_TYPE` int(11) NOT NULL auto_increment,
  `DESCRIPTION` varchar(50) NOT NULL,
  PRIMARY KEY  (`ID_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaia`.`AtomType`
--

/*!40000 ALTER TABLE `AtomType` DISABLE KEYS */;
LOCK TABLES `AtomType` WRITE;
INSERT INTO `gaia`.`AtomType` VALUES  (1,'Relation'),
 (2,'Attribute'),
 (3,'AttributeKey'),
 (4,'AttributeFKey');
UNLOCK TABLES;
/*!40000 ALTER TABLE `AtomType` ENABLE KEYS */;


--
-- Definition of table `gaia`.`SchemaExchange`
--

DROP TABLE IF EXISTS `gaia`.`SchemaExchange`;
CREATE TABLE  `gaia`.`SchemaExchange` (
  `ID_SE` int(11) NOT NULL auto_increment,
  `DESCRIPTION` varchar(100) default NULL,
  `TYPE` int(11) NOT NULL,
  PRIMARY KEY  (`ID_SE`),
  KEY `SETypeFk` (`TYPE`),
  CONSTRAINT `SETypeFk` FOREIGN KEY (`TYPE`) REFERENCES `SchemaExchangeType` (`ID_SE_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaia`.`SchemaExchange`
--

/*!40000 ALTER TABLE `SchemaExchange` DISABLE KEYS */;
LOCK TABLES `SchemaExchange` WRITE;
UNLOCK TABLES;
/*!40000 ALTER TABLE `SchemaExchange` ENABLE KEYS */;


--
-- Definition of table `gaia`.`SchemaExchangeType`
--

DROP TABLE IF EXISTS `gaia`.`SchemaExchangeType`;
CREATE TABLE  `gaia`.`SchemaExchangeType` (
  `ID_SE_TYPE` int(11) NOT NULL auto_increment,
  `SN_REL_NUM` int(11) NOT NULL,
  `DX_REL_NUM` int(11) NOT NULL,
  PRIMARY KEY  USING BTREE (`ID_SE_TYPE`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `gaia`.`SchemaExchangeType`
--

/*!40000 ALTER TABLE `SchemaExchangeType` DISABLE KEYS */;
LOCK TABLES `SchemaExchangeType` WRITE;
INSERT INTO `gaia`.`SchemaExchangeType` VALUES  (1,1,1),
 (2,3,3);
UNLOCK TABLES;
/*!40000 ALTER TABLE `SchemaExchangeType` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
