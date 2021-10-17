SET REFERENTIAL_INTEGRITY FALSE ;

--countries
INSERT INTO "countries" (name,updated) VALUES ('Grenada',CURRENT_TIME);
INSERT INTO "countries" (name,updated) VALUES ('Western Sahara',CURRENT_TIME);
INSERT INTO "countries" (name,updated) VALUES ('Reunion',CURRENT_TIME);
INSERT INTO "countries" (name,updated) VALUES ('Luxembourg',CURRENT_TIME);
INSERT INTO "countries" (name,updated) VALUES ('Croatia',CURRENT_TIME);

--cities
INSERT INTO "cities" (name,country_id,updated) VALUES ('Gravelbourg',1,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Sandy',2,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Mirzapur-cum-Vindhyachal',5,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Stirling',4,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Broechem',2,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Court-Saint-Etienne',5,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Kamarhati',1,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Minitonas',3,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Oamaru',4,CURRENT_TIME);
INSERT INTO "cities" (name,country_id,updated) VALUES ('Itegem',1,CURRENT_TIME);

--addresses
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('Ap #856-4094 Sit Road','34383','(036449) 100926',6,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('173 Convallis St.','8644 PK','(04961) 1470973',7,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('189-2635 Integer Road','9844','(02260) 4860818',5,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('P.O. Box 514, 3676 Quam, Road','4794','(02901) 1931665',8,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('Ap #795-9097 Dui. Av.','V2H 2H6','(0725) 46986575',6,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('P.O. Box 854, 5291 Eu St.','99884','(030898) 938520',4,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('7678 Neque St.','98441-11616','(075) 95681739',7,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('618-964 Elit, St.','47192-694','(01948) 2507492',1,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('2697 Vivamus St.','680397','(037554) 610483',6,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('P.O. Box 123, 8679 Ullamcorper Street','34576-71904','(037504) 619658',7,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('Ap #845-2325 Vitae St.','316295','(0480) 57990331',4,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('Ap #774-2681 Varius. St.','909817','(040) 36798333',7,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('7614 Magnis Road','286341','(089) 80600020',5,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('P.O. Box 978, 3239 Suspendisse Avenue','106121','(03295) 0906795',4,CURRENT_TIME);
INSERT INTO "addresses" (address,postal_code,phone,city_id,updated) VALUES ('8309 Arcu St.','570199','(039061) 559830',2,CURRENT_TIME);


--customers
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Wallace','Austin','semper.cursus.Integer@Phasellusfermentum.ca',12,3,CURRENT_TIME,CURRENT_TIME,'Pkxzmdjsad','salknlkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Flynn','Wheeler','Nullam.scelerisque.neque@aliquam.com',7,2,CURRENT_TIME,CURRENT_TIME,'Okxzmdjsad','salasczxzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Harlan','Carrillo','auctor.nunc@tellus.ca',4,3,CURRENT_TIME,CURRENT_TIME,'Qkxzmdjsad','scvbcxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Beverly','Beard','quis.tristique.ac@sitamet.ca',11,1,CURRENT_TIME,CURRENT_TIME,'Ckxzmdjsad',' xkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Coby','Griffith','euismod.mauris@semmolestiesodales.net',9,3,CURRENT_TIME,CURRENT_TIME,'Xkxzmdjsad','jhjlkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Martena','Shepherd','nec.tempus@ipsum.net',9,2,CURRENT_TIME,CURRENT_TIME,'Tkxzmdjsad','nbvnlkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Chiquita','Holcomb','vel@sedturpisnec.org',6,3,CURRENT_TIME,CURRENT_TIME,'Gkxzmdjsad','iuknlkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Adrian','York','nec@gravidamaurisut.org',4,1,CURRENT_TIME,CURRENT_TIME,'Zkascxzsad','tewqnlkzxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Samantha','Rice','amet@sem.co.uk',11,1,CURRENT_TIME,CURRENT_TIME,'Dkxzmdjsad','daszxccx');
INSERT INTO "customers" (first_name,last_name,email,address_id,store_id,updated,created,username,password) VALUES ('Lewis','Turner','In.condimentum@nequeet.com',12,2,CURRENT_TIME,CURRENT_TIME,'Akxzmdjsad','sczxlkzxccx');

--staff
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Abra','Mcclure','non@Fusce.ca',9,1,'pede,','Donec',CURRENT_TIME);
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Yoshio','Shannon','euismod@metus.edu',6,1,'non','faucibus',CURRENT_TIME);
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Indira','Andrews','dapibus.ligula@semPellentesqueut.org',7,2,'vitae,','arcu',CURRENT_TIME);
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Cleo','Mccall','Phasellus.dolor.elit@Crassedleo.com',5,1,'facilisi.','Morbi',CURRENT_TIME);
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Tanek','Huff','dui.lectus.rutrum@massarutrummagna.co.uk',13,3,'urna,','Nulla',CURRENT_TIME);
INSERT INTO "staff" (first_name,last_name,email,address_id,store_id,username,password,updated) VALUES ('Peter','Kemp','risus.Donec@egestasFuscealiquet.net',12,1,'lacus.','ornare',CURRENT_TIME);

--store
INSERT INTO "stores" (manager_id,address_id,updated) VALUES (4,11,CURRENT_TIME);
INSERT INTO "stores" (manager_id,address_id,updated) VALUES (3,8,CURRENT_TIME );
INSERT INTO "stores" (manager_id,address_id,updated) VALUES (5,13,CURRENT_TIME );

--payments
INSERT INTO "payments" (customer_id,staff_id,rental_id,amount,updated,payment_date) VALUES (1,3,1,'9.53',CURRENT_TIME,CURRENT_TIME);
INSERT INTO "payments" (customer_id,staff_id,rental_id,amount,updated,payment_date) VALUES (2,5,2,'7.78',CURRENT_TIME,CURRENT_TIME);
INSERT INTO "payments" (customer_id,staff_id,rental_id,amount,updated,payment_date) VALUES (3,3,3,'4.37',CURRENT_TIME,CURRENT_TIME);

--rental
INSERT INTO "rental" (customer_id,staff_id,inventory_id,updated,rental_date,return_date) VALUES (1,3,1,CURRENT_TIME,CURRENT_TIME,DATEADD('DAY',10,CURRENT_DATE ));
INSERT INTO "rental" (customer_id,staff_id,inventory_id,updated,rental_date,return_date) VALUES (2,5,2,CURRENT_TIME,CURRENT_TIME,DATEADD('DAY',10,CURRENT_DATE ));
INSERT INTO "rental" (customer_id,staff_id,inventory_id,updated,rental_date,return_date) VALUES (3,3,3,CURRENT_TIME,CURRENT_TIME,DATEADD('DAY',10,CURRENT_DATE ));

--inventory
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (7,2,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (7,3,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (3,2,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (3,1,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (5,1,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (3,1,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (8,1,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (4,1,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (3,2,CURRENT_TIME);
INSERT INTO "inventory" (film_id,store_id,updated) VALUES (10,3,CURRENT_TIME);

--film
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Avengers','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam,','2019-09-20 ',10,79,'60.00','20.00','G','English',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Thor','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas','2017-10-31 ',10,107,'70.00','15.00','R','English',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Shrek','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu,','2018-09-22 ',10,108,'54.32','12.02','NC_17','English',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Star Wars','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis','2018-06-02 ',10,66,'70.00','19.00','R','Polish',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Bliźniak','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis vitae, orci. Phasellus dapibus','2020-04-25 ',10,105,'80.00','15.00','R','Polish',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Hobbit','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis vitae, orci. Phasellus dapibus quam quis diam. Pellentesque habitant','2019-02-01 ',10,111,'90.00','18.00','NC_17','Polish',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Joker','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut','2018-07-02 ',10,76,'75.00','10.00','PG_13','Polish',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Kate','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus','2020-02-21 ',10,103,'90.00','22.00','NC_17','Polish',CURRENT_TIME);
INSERT INTO "films" (title,description,release_date,rental_duration,length,replacement_cost,rental_cost,rating,language,updated) VALUES ('Kac Vegas','Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Curabitur sed tortor. Integer aliquam adipiscing lacus. Ut nec urna et arcu imperdiet ullamcorper. Duis at lacus. Quisque purus sapien, gravida non, sollicitudin a, malesuada id, erat. Etiam vestibulum massa rutrum magna. Cras convallis convallis dolor. Quisque tincidunt pede ac urna. Ut tincidunt vehicula risus. Nulla eget metus eu erat semper rutrum. Fusce dolor quam, elementum at, egestas a, scelerisque sed, sapien. Nunc pulvinar arcu et pede. Nunc sed orci lobortis augue scelerisque mollis. Phasellus libero mauris, aliquam eu, accumsan sed, facilisis vitae, orci.','2018-08-13 ',10,67,'100.00','25.00','R','Polish',CURRENT_TIME);

--film_category
INSERT INTO "film_category" (film_id,category_id) VALUES (1,3);
INSERT INTO "film_category" (film_id,category_id) VALUES (2,6);
INSERT INTO "film_category" (film_id,category_id) VALUES (3,11);
INSERT INTO "film_category" (film_id,category_id) VALUES (4,14);
INSERT INTO "film_category" (film_id,category_id) VALUES (5,13);
INSERT INTO "film_category" (film_id,category_id) VALUES (6,5);
INSERT INTO "film_category" (film_id,category_id) VALUES (7,3);
INSERT INTO "film_category" (film_id,category_id) VALUES (8,13);
INSERT INTO "film_category" (film_id,category_id) VALUES (9,13);
INSERT INTO "film_category" (film_id,category_id) VALUES (10,6);
INSERT INTO "film_category" (film_id,category_id) VALUES (1,6);
INSERT INTO "film_category" (film_id,category_id) VALUES (8,2);
INSERT INTO "film_category" (film_id,category_id) VALUES (9,3);
INSERT INTO "film_category" (film_id,category_id) VALUES (10,9);
INSERT INTO "film_category" (film_id,category_id) VALUES (1,4);

--category
INSERT INTO "categories" (name,updated) VALUES ('Action',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Horror',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Sci-fi',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Comedy',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Historical',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Anime',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('War',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Mystery',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Romance',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Documentary',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Musical',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Western',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Sport',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Crime',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Adventure',CURRENT_TIME);
INSERT INTO "categories" (name,updated) VALUES ('Biography',CURRENT_TIME);

SET REFERENTIAL_INTEGRITY TRUE ;