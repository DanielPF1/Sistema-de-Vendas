-- PF

INSERT INTO clientepf (id,email, telefone, nome, cpf) VALUES (1, 'joao@email.com', '1111-1111','João Silva', '123.456.789-00');


INSERT INTO clientepf (id, email, telefone, nome, cpf) VALUES (2,'maria@email.com', '2222-2222', 'Maria Souza', '987.654.321-00');

-- PJ

INSERT INTO cliente_pj (id,email,telefone, razao_social, cnpj) VALUES (3, 'contato@xyz.com', '3333-3333', 'Empresa XYZ', '12.345.678/0001-99');


INSERT INTO cliente_pj (id,email, telefone, razao_social, cnpj) VALUES (4,'contato@abc.com', '4444-4444', 'Loja ABC', '98.765.432/0001-88');

-- Produtos
INSERT INTO produto (descricao, imagens, valor) VALUES ('Arroz','arroz2.png',43.00);
INSERT INTO produto (descricao,imagens,  valor) VALUES ('Leite','leite.png',7.00);
INSERT INTO produto (descricao,imagens,  valor) VALUES ('Feijão','feijao.png',24.00);
INSERT INTO produto (descricao,imagens,  valor) VALUES ('Bolo','bolo.png', 25.00);

-- Vendas associadas a clientes
INSERT INTO venda (id_cliente, data_venda) VALUES (1, '2025-12-10');
INSERT INTO venda (id_cliente, data_venda) VALUES (1, '2025-10-10'); -- João Silva
INSERT INTO venda (id_cliente, data_venda) VALUES (2, '2025-10-11'); -- Maria Souza
INSERT INTO venda (id_cliente, data_venda) VALUES (3, '2025-10-09'); -- Empresa XYZ
INSERT INTO venda (id_cliente, data_venda) VALUES (4, '2025-09-21'); -- Loja ABC

-- Itens da venda 1
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (2, 2, 1);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (3, 2, 2);

INSERT INTO item (quantidade, id_venda, id_produto) VALUES (3, 1, 3);
-- Itens da venda 2
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (1, 2, 3);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (2, 2, 4);

-- Itens da venda 3
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (3, 3, 3);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (4, 3, 2);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (3, 3, 4);

-- Itens da venda 4
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (5, 4, 2);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (2, 4, 1);
INSERT INTO item (quantidade, id_venda, id_produto) VALUES (6, 4, 3);


ALTER SEQUENCE cliente_seq RESTART WITH 5;