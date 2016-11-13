CREATE TABLE representation (
       idspec number(10) references spectacles (idspec) ON DELETE CASCADE,
       idsalle number(10) references salle(idsalle) ON DELETE CASCADE,
       dateheure timestamp references horaire(dateheure) ON DELETE CASCADE,
       PRIMARY KEY(idspec,idsalle,dateheure)
       
       
);

insert into representation values(1,1,'01-JUN-2015 0800:00PM');
insert into representation values(2,2,'01-JUN-2015 0800:00PM');
insert into representation values(3,1,'02-JUN-2015 1000:00PM');
insert into representation values(4,1,'03-JUN-2015 0800:00PM');
