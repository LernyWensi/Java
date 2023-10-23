create table Newspapers
(
    NewspaperID int primary key not null,
    HoldingID int,
    Name varchar(50),
    ApproximateAudience bigint,
    TargetAudience varchar(50),
    AnnuallyCirculation bigint,
    foreign key(HoldingID) references Holdings(HoldingID)
);

create table TVChannels
(

    TVChannelID int primary key not null,
    HoldingID int,
    Name varchar(50),
    ApproximateAudience bigint,
    TargetAudience varchar(50),
    Type varchar(50),
    foreign key(HoldingID) references Holdings(HoldingID)
);

create table Holdings
(
    HoldingID int primary key not null,
    Name varchar(50)
);