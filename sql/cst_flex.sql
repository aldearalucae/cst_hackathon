create table users(
   username VARCHAR(40) NOT NULL,
   password VARCHAR(256) NOT NULL,
   firstname VARCHAR(40) NOT NULL,
   lastname VARCHAR(40) NOT NULL,
   weight INT NOT NULL,
   age INT NOT NULL,
   gender VARCHAR(10) NOT NULL
);

create table training(
   username VARCHAR(40) NOT NULL,
   start_timestamp VARCHAR(256) NOT NULL,
   stop_timestamp VARCHAR(256) NOT NULL,
   total_count VARCHAR(40) NOT NULL,
   success_count VARCHAR(40) NOT NULL,
   calories_count VARCHAR(40) NOT NULL
);
