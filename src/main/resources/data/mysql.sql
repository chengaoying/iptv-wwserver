create table if not exists `GameRole` (
	`roleId` int not null auto_increment,
	`roleName` varchar(32) not null unique,
	`nickName` varchar(32),
	`password` varchar(32),
	`privilege` int not null default 0,
	`userId` varchar(32),
	`gender` varchar(8),
	`headIcon` varchar(32),
	`goldCoin` int not null default 0,
    `gameCoin` int not null default 0,
	`scores` int not null default 0,
	`state` int not null default 0,
    `onlineSeconds` int not null default 0,
    `victoryCount` int not null default 0,
    `failureCount` int not null default 0,
    `escapeCount` int not null default 0,
    `playCount` int not null default 0,
	`createTime` datetime not null,
	`updateTime` datetime not null,
    `loginTime` datetime,
	`logoutTime` datetime,
	`loginIp` varchar(64),
	primary key(`roleId`),
	index(scores)
)engine=InnoDB auto_increment=10000;