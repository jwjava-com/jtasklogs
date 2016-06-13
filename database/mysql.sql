/* Database export results for db tasklogs */

/* Preserve session variables */
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS;
SET FOREIGN_KEY_CHECKS=0;

/* Export data */

/* Table structure for daily_totals */
CREATE TABLE `daily_totals` (
  `date` date NOT NULL,
  `task_id` int(11) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Table structure for entries */
CREATE TABLE `entries` (
  `task_id` int(11) NOT NULL,
  `start_timestamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Table structure for tasks */
CREATE TABLE `tasks` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) NOT NULL,
  `is_billable` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Table structure for timesheets */
CREATE TABLE `timesheets` (
  `week_end_date` date NOT NULL,
  `billable_total` double NOT NULL,
  `nonbillable_total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Table structure for weekly_totals */
CREATE TABLE `weekly_totals` (
  `week_end_date` date NOT NULL,
  `task_id` int(11) NOT NULL,
  `total` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* Restore session variables to original values */
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
