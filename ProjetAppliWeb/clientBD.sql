CREATE TABLE client (
       login varchar(100) NOT NULL PRIMARY KEY,
       nom varchar(100) NOT NULL,
       prenom varchar(100) NOT NULL,
       password varchar(100) NOT NULL,
       email varchar(100),
       FOREIGN KEY(login, nom, prenom, password, email) REFERENCES users([login,
         nom, prenom, password, email]) ON DELETE CASCADE
);