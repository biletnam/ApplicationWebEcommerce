CREATE TABLE rang (

       idsalle number(10) FOREIGN KEY(idsalle) REFERENCES salle(idsalle) ON DELETE CASCADE,
       idrang number(10),
       categorie varchar(100) NOT NULL,
       prix number(10),
       PRIMARY KEY(idsalle, idrang)
);