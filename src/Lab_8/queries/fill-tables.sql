INSERT INTO Holdings
    (HoldingID, Name)
VALUES
    (1, "Worldwide Group"),
    (2, "Future Group"),
    (3, "Bright Future");

INSERT INTO Newspapers
    (NewspaperID, HoldingID, Name, ApproximateAudience, TargetAudience, AnnuallyCirculation)
VALUES
    (1, 1, "Time", 3500000, "Adults", 42000000),
    (2, 1, "Above the Sky", 150000, "Mixed", 1800000),
    (3, 1, "Nature", 5000000, "Adults", 60000000),
    (4, 2, "The Sun", 200000, "Mixed", 200000),
    (5, 2, "The Herald", 450000, "Teens", 300000),
    (6, 2, "Progressive", 900000, "Adults", 1000000),
    (7, 3, "Buzz", 300000, "Teens", 600000),
    (8, 3, "Green Magazine", 250000, "Mixed", 500000),
    (9, 3, "Pro Shop", 80000, "Teens", 120000);

INSERT INTO TVChannels
    (TVChannelID, HoldingID, Name, ApproximateAudience, TargetAudience, Type)
VALUES
    (1, 1, "Action!", 450000, "Mixed", "Movies"),
    (2, 1, "MusicTV", 1000000, "Teens", "Music"),
    (3, 1, "Knowledge", 30000, "Children", "Education"),
    (4, 2, "StyleTV", 600000, "Adults", "Life"),
    (5, 2, "Action News Network", 450000, "Adults", "News"),
    (6, 2, "MusicTV Plus", 1000000, "Teens", "Music"),
    (7, 3, "Knowledge for Kids", 30000, "Children", "Education"),
    (8, 3, "Comedy Central", 250000, "Adults", "Movies"),
    (9, 3, "Lifestyle TV", 90000, "Mixed", "Life");