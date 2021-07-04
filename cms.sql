create schema if not exists `claim`;
use `claim`;

drop table if exists `claim`.`claims`;
create table if not exists `claim`.`claims`(
	`claim_id` bigint not null auto_increment,
    `claim_status` varchar(25) not null ,
    `remarks` varchar(50),
    `policy_Details` varchar(25) not null,
    `hospital_Detail` varchar(25) not null,
    `benefits_Availed` varchar(25) not null,
    `amount_Claimed` bigint not null,
    `settled_Amt` bigint not null,
    primary key (`claim_id`)
)ENGINE = InnoDB;

drop table if exists `claim`.`claimstatus`;
create table if not exists `claim`.`claimstatus`(
	`claimstatus_id` bigint not null auto_increment,
    `claim_Id` varchar(10) not null,
    `member_Id` varchar(10) not null,
    `policy_id` varchar(10) not null,
    `claim_Status` varchar(15) not null,
    `status_Des` varchar(35) not null,
    primary key(`claimstatus_id`)
);

drop table if exists `claim`.`members`;
create table if not exists `claim`.`members`(
	`member_id` bigint not null auto_increment,
    `first_Name` varchar(15) not null,
    `last_Name` varchar(15) not null,
    `age` int(4) not null,
    `gender` varchar(7) not null,
    `dob` date not null,
    `policy_Code` varchar(10) not null,
    `address` varchar(20) not null,
    primary key(`member_id`)
);

drop table if exists `claim`.`member_policy`;
create table if not exists `claim`.`member_policy`(
	`member_policy_id` bigint not null auto_increment,
    `member_Id` varchar(10) not null,
    `policy_Id` varchar(10) not null,
    `last_premium_date` date not null,
    `premium_amt_due` bigint not null,
    `last_payment_info` varchar(10) not null,
    `due_Date` date not null,
    primary key(`member_policy_id`)
);

drop table if exists `claim`.`member_submit_claim`;
create table if not exists `claim`.`member_submit_claim`(
	`claims_id` bigint not null auto_increment,
    `policy_Id` varchar(10) not null,
    `member_Id` varchar(10) not null,
    `hospital_Id` varchar(10) not null,
    `total_Bill` bigint not null,
    `total_claimed_amt` bigint not null,
    primary key(`claims_id`)
);

drop table if exists `claim`.`provider_policy`;
create table if not exists `claim`.`provider_policy`(
	`provider_id` bigint not null auto_increment,
    `policy_Id` varchar(10) not null,
    `policy_Name` varchar(25) not null,
    `provider_Name` varchar(15) not null,
    `location` varchar(10) not null,
    `benefits` varchar(20) not null,
    `premium_Amt` bigint not null,
    `tenure` bigint not null,
    `claim_Amt` bigint not null,
    primary key(`provider_id`)
);