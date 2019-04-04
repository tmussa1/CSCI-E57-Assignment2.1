DROP TABLE IF EXISTS companys;

CREATE TABLE companys (
  company_id        TEXT PRIMARY KEY NOT NULL,
  company_name TEXT NOT NULL,
  year_established  TEXT,
  number_of_employees      INT,
  capital   DOUBLE PRECISION );

