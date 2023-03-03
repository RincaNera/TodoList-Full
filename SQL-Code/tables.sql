USE todolistfull;



CREATE TABLE User (

idUser  int primary key auto_increment not null,
lastNameUser Varchar(50),
firstNameUser Varchar(50)
);



CREATE TABLE Urgency (
	idUrgency int primary key auto_increment not null,
    nameUrgency varchar(50)
);


CREATE TABLE Todo (
	idTodo int primary key auto_increment not null,
    titleTodo Varchar(50),
    descriptionTodo Text,
    dateTodo DATE,
    idUser INT,
    idUrgency INT,
    Foreign key (idUser) references User(idUser),
    Foreign key (idUrgency) references Urgency(idUrgency)
);



