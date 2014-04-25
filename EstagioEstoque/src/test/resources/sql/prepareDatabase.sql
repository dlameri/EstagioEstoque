DELETE FROM IMAGENS;
DELETE FROM ITEM;
DELETE FROM PRODUTO;
DELETE FROM ADMINISTRADOR;
DELETE FROM SUBCATEGORIA;
DELETE FROM CATEGORIA;
DELETE FROM DIMENSOES;

ALTER TABLE IMAGENS AUTO_INCREMENT = 1;
ALTER TABLE ITEM AUTO_INCREMENT = 1;
ALTER TABLE PRODUTO AUTO_INCREMENT = 1;
ALTER TABLE ADMINISTRADOR AUTO_INCREMENT = 1;
ALTER TABLE SUBCATEGORIA AUTO_INCREMENT = 1;
ALTER TABLE CATEGORIA AUTO_INCREMENT = 1;
ALTER TABLE DIMENSOES AUTO_INCREMENT = 1;

INSERT INTO DIMENSOES VALUES(1, 10, 30, 10);

INSERT INTO CATEGORIA VALUES(1, 1, 'Categoria 1');
INSERT INTO CATEGORIA VALUES(2, 1, 'Categoria 2');
INSERT INTO CATEGORIA VALUES(3, 1, 'Categoria 3');
INSERT INTO CATEGORIA VALUES(4, 1, 'Categoria 4');
INSERT INTO CATEGORIA VALUES(5, 1, 'Categoria 5');
INSERT INTO CATEGORIA VALUES(6, 1, 'Categoria 6');
INSERT INTO CATEGORIA VALUES(7, 1, 'Categoria 7');
INSERT INTO CATEGORIA VALUES(8, 1, 'Categoria 8');
INSERT INTO CATEGORIA VALUES(9, 1, 'Categoria 9');
INSERT INTO CATEGORIA VALUES(10, 1, 'Categoria 10');

INSERT INTO SUBCATEGORIA VALUES(1, 1, 'Subcategoria 1', 1);
INSERT INTO SUBCATEGORIA VALUES(2, 1, 'Subcategoria 2', 1);
INSERT INTO SUBCATEGORIA VALUES(3, 1, 'Subcategoria 3', 1);
INSERT INTO SUBCATEGORIA VALUES(4, 1, 'Subcategoria 4', 1);
INSERT INTO SUBCATEGORIA VALUES(5, 1, 'Subcategoria 5', 2);
INSERT INTO SUBCATEGORIA VALUES(6, 1, 'Subcategoria 6', 2);
INSERT INTO SUBCATEGORIA VALUES(7, 1, 'Subcategoria 7', 3);
INSERT INTO SUBCATEGORIA VALUES(8, 1, 'Subcategoria 8', 3);
INSERT INTO SUBCATEGORIA VALUES(9, 1, 'Subcategoria 9', 3);
INSERT INTO SUBCATEGORIA VALUES(10, 1, 'Subcategoria 10', 4);

INSERT INTO PRODUTO VALUES(1, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 10, 'Descrição curta', 2, 5, 1, 1, 10);
INSERT INTO PRODUTO VALUES(2, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 10, 'Descrição curta', 2, 5, 1, 1, 2);
INSERT INTO PRODUTO VALUES(3, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 5, 'Descrição curta', 2, 5, 1, 1, 3);
INSERT INTO PRODUTO VALUES(4, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 29, 'Descrição curta', 2, 5, 2, 1, 5);
INSERT INTO PRODUTO VALUES(5, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 50,'Descrição curta', 2, 5, 2, 1, 2);
INSERT INTO PRODUTO VALUES(6, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 3, 'Descrição curta', 2, 5, 3, 1, 5);
INSERT INTO PRODUTO VALUES(7, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 100, 'Descrição curta', 2, 5, 3, 1, 6);
INSERT INTO PRODUTO VALUES(8, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 90, 'Descrição curta', 2, 5, 3, 1, 8);
INSERT INTO PRODUTO VALUES(9, 0, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 17, 'Descrição curta', 2, 5, 4, 1, 8);
INSERT INTO PRODUTO VALUES(10, 0, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 35, 'Descrição curta', 2, 5, 4, 1, 8);

INSERT INTO ITEM VALUES(1, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 10, 12345, 15, 1);
INSERT INTO ITEM VALUES(2, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 15, 12345, 15, 1);
INSERT INTO ITEM VALUES(3, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 5, 12345, 15, 1);
INSERT INTO ITEM VALUES(4, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 200, 12345, 15, 2);
INSERT INTO ITEM VALUES(5, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 150, 12345, 15, 2);
INSERT INTO ITEM VALUES(6, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 30, 12345, 15, 3);
INSERT INTO ITEM VALUES(7, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 20, 12345, 15, 3);
INSERT INTO ITEM VALUES(8, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 3, 12345, 15, 3);
INSERT INTO ITEM VALUES(9, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 98, 12345, 15, 4);
INSERT INTO ITEM VALUES(10, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 99, 12345, 15, 4);
INSERT INTO ITEM VALUES(11, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 12, 12345, 15, 5);
INSERT INTO ITEM VALUES(12, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 12, 12345, 15, 6);
INSERT INTO ITEM VALUES(13, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 12, 12345, 15, 7);
INSERT INTO ITEM VALUES(14, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 12, 12345, 15, 8);
INSERT INTO ITEM VALUES(15, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 14, 12345, 15, 8);
INSERT INTO ITEM VALUES(16, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 36, 12345, 15, 9);
INSERT INTO ITEM VALUES(17, 0, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 37, 12345, 15, 9);

INSERT INTO IMAGENS VALUES(1, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 15);

INSERT INTO ADMINISTRADOR VALUES(1,'admin@teste.com','Admin','98f97621dc9308ce4496edd3ee32c6d583c54f0e8368626697dd6de1daa98675576aba57e92220ef08b89f30e2cbafd8a92646bf5fa92a395d4c1e7133181986');

COMMIT;