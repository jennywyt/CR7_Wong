SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;


CREATE TABLE `classes` (
  `Class_ID` int(11) NOT NULL,
  `Class_Name` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `classes` (`Class_ID`, `Class_Name`) VALUES
(1, '1A'),
(2, '1B'),
(3, '2A'),
(4, '3A');

CREATE TABLE `student` (
  `Student_ID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL,
  `Class_ID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `student` (`Student_ID`, `NAME`, `Surname`, `Email`, `Class_ID`) VALUES
(1, 'Charlie', 'Brown', 'charlie.brown@gmail.com', 1),
(2, 'Lucy', 'Van Pelt', 'lucy@gmail.com', 3),
(3, 'Linus', 'Van Pelt', 'linus@gmx.at', 1),
(4, 'Peppermint', 'Patty', 'pepa@gmail.com', 3),
(5, 'Sally ', 'Brown', 'sally.brown@hotmail.com', 2),
(6, 'Rerun', 'van Pelt', 'rerun@yahoo.com', 3),
(7, 'Shroeder', 'Smith', 'shroeder.music@gmail.com', 2),
(8, 'Franklin', 'Peanuts', 'franklin@gmail.com', 3),
(9, 'Violet ', 'Gray', 'vio.gray@gmx.com', 4),
(10, 'Marcie', 'Johnson', 'marcie.johnson@gmail.com', 4);

CREATE TABLE `teacher` (
  `Teacher_ID` int(11) NOT NULL,
  `NAME` varchar(100) NOT NULL,
  `Surname` varchar(100) NOT NULL,
  `Email` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `teacher` (`Teacher_ID`, `NAME`, `Surname`, `Email`) VALUES
(1, 'Rolanda ', 'Hooch', 'flying.rolanda@gmail.com'),
(2, 'Remus', 'Lupin', 'remus.lupin@gmx.com'),
(3, 'Minerva ', 'McGonagall', 'm.mcgoagall@gmail.com'),
(4, 'Filius', 'Flitwick', 'f.flitwick@hotmail.com');

CREATE TABLE `teacher_class` (
  `teacher_id` int(11) NOT NULL,
  `class_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

INSERT INTO `teacher_class` (`teacher_id`, `class_id`) VALUES
(1, 1),
(2, 3),
(3, 4),
(4, 2);


ALTER TABLE `classes`
  ADD PRIMARY KEY (`Class_ID`);

ALTER TABLE `student`
  ADD PRIMARY KEY (`Student_ID`),
  ADD KEY `fk_student_class` (`Class_ID`);

ALTER TABLE `teacher`
  ADD PRIMARY KEY (`Teacher_ID`);

ALTER TABLE `teacher_class`
  ADD UNIQUE KEY `teacher_id` (`teacher_id`,`class_id`),
  ADD KEY `class_id` (`class_id`);


ALTER TABLE `classes`
  MODIFY `Class_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

ALTER TABLE `student`
  MODIFY `Student_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

ALTER TABLE `teacher`
  MODIFY `Teacher_ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;


ALTER TABLE `student`
  ADD CONSTRAINT `fk_student_class` FOREIGN KEY (`Class_ID`) REFERENCES `classes` (`Class_ID`);

ALTER TABLE `teacher_class`
  ADD CONSTRAINT `teacher_class_ibfk_1` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`Teacher_ID`),
  ADD CONSTRAINT `teacher_class_ibfk_2` FOREIGN KEY (`class_id`) REFERENCES `classes` (`Class_ID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
