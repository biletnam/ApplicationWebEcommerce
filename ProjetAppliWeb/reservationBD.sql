CREATE TABLE reservation (
       login varchar(100),
       idspec number(10),
       dateheure timestamp,
       idsalle number(10),
       idrang number(10),
       idplace number(10)
       PRIMARY KEY (login,idspec,dateheure,idsalle,idrang,idplace),
       FOREIGN KEY (login) REFERENCES users(login) ON DELETE CASCADE,
       FOREIGN KEY (idspec, idsalle, dateheure) REFERENCES representation(idspec, idsalle, dateheure) ON DELETE CASCADE,
       FOREIGN KEY (idsalle, idrang,idplace) REFERENCES place(idsalle,idrang,idplace) ON DELETE CASCADE
);