DELETE FROM IMAGENS;
DELETE FROM ITEM;
DELETE FROM PRODUTO;
DELETE FROM ADMINISTRADOR;
DELETE FROM SUBCATEGORIA;
DELETE FROM CATEGORIA;
DELETE FROM DIMENSOES;

INSERT INTO DIMENSOES VALUES(1, 10, 30, 10);

INSERT INTO CATEGORIA VALUES(1, 1, 'Categoria 1');
INSERT INTO CATEGORIA VALUES(2, 1, 'Categoria 2');
INSERT INTO CATEGORIA VALUES(3, 1, 'Categoria 3');

INSERT INTO SUBCATEGORIA VALUES(1, 1, 'Subcategoria 1', 1);
INSERT INTO SUBCATEGORIA VALUES(2, 1, 'Subcategoria 2', 1);
INSERT INTO SUBCATEGORIA VALUES(3, 1, 'Subcategoria 3', 1);

INSERT INTO PRODUTO VALUES(1, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 10, 'Descrição curta', 2, 5, 1, 1, 3);
INSERT INTO PRODUTO VALUES(2, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 10, 'Descrição curta', 2, 5, 1, 1, 2);
INSERT INTO PRODUTO VALUES(3, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 5, 'Descrição curta', 2, 5, 1, 1, 3);
INSERT INTO PRODUTO VALUES(4, 1, 'Nome da Marca', 'Descrição longa', 'Nome do modelo', 'Nome do produto', 50,'Descrição curta', 2, 5, 2, 1, 2);

INSERT INTO ITEM VALUES(1, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 10, 12345, 15, 1);
INSERT INTO ITEM VALUES(2, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 15, 12345, 15, 1);
INSERT INTO ITEM VALUES(3, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 200, 12345, 15, 2);
INSERT INTO ITEM VALUES(4, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 150, 12345, 15, 2);
INSERT INTO ITEM VALUES(5, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 30, 12345, 15, 3);
INSERT INTO ITEM VALUES(6, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 20, 12345, 15, 3);
INSERT INTO ITEM VALUES(7, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 98, 12345, 15, 4);
INSERT INTO ITEM VALUES(8, 1, 'Nome opcional', 'Valor opcional', 199.90, 299.90, 0, 99, 12345, 15, 4);

INSERT INTO IMAGENS VALUES(1, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 1);
INSERT INTO IMAGENS VALUES(2, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 2);
INSERT INTO IMAGENS VALUES(3, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 3);
INSERT INTO IMAGENS VALUES(4, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 4);
INSERT INTO IMAGENS VALUES(5, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 5);
INSERT INTO IMAGENS VALUES(6, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 6);
INSERT INTO IMAGENS VALUES(7, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 7);
INSERT INTO IMAGENS VALUES(8, '', '', 1, '', '', '', 'http://isuba.s8.com.br/produtos/01/00/item/117218/9/117218927G1.jpg', '', 8);

INSERT INTO ADMINISTRADOR VALUES(1,'admin@teste.com','Admin','98f97621dc9308ce4496edd3ee32c6d583c54f0e8368626697dd6de1daa98675576aba57e92220ef08b89f30e2cbafd8a92646bf5fa92a395d4c1e7133181986');

COMMIT;