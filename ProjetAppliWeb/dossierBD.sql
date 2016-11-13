CREATE TABLE dossier (
    idDossier number(10) PRIMARY KEY,
    nbPlacesAchetees number(10) CHECK (nbPlacesAchetees > 0),
    login varchar(100) NOT NULL
)
