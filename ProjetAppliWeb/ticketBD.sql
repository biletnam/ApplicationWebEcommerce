CREATE TABLE ticket (
    idTicket number(10) NOT NULL PRIMARY KEY,
    idspec number(10) NOT NULL,
    dateheure timestamp NOT NULL,
    login varchar(100) NOT NULL,
    idsalle number(10),
    idrang number(10),
    idplace number(10),
    FOREIGN KEY(idspec) REFERENCES spectacles(idspec) ON DELETE CASCADE,
    FOREIGN KEY(dateheure) REFERENCES horaire(dateheure) ON DELETE CASCADE,
    FOREIGN KEY(login) REFERENCES users(login) ON DELETE CASCADE,
    FOREIGN KEY(idsalle, idrang, idplace) REFERENCES place(idsalle, idrang, idplace) ON DELETE CASCADE
);