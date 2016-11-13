CREATE TABLE place (
       idsalle number(10),
       idrang  number(10),
       idplace number(10),
       PRIMARY KEY(idsalle, idrang, idplace),
       FOREIGN KEY(idsalle, idrang) REFERENCES rang(idsalle, idrang) ON DELETE CASCADE
);