CREATE SCHEMA `trabalho` ;

use trabalho;

create table cliente(
	id_cliente		int not null AUTO_INCREMENT,
    login		   	varchar(100) not null,
    senha          	varchar(100) not null,
    nome           	varchar(100) not null,
	cpf       	   	varchar(11) not null,
    endereco	   	varchar(100) not null,
    telefone	   	varchar(11) not null,
    id_conta		int,
    tipo		   	varchar(5) not null,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_cliente)
);

create table tickers(
	id_ticker		int not null AUTO_INCREMENT,
    nome_empresa	varchar(100) not null,
    ticker			varchar(3) not null,
    total_ativos	int not null,
    preco_inicial	double not null,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_ticker)
);

create table conta(
	id_conta		int not null AUTO_INCREMENT,
    id_cliente		int not null,
    saldo			double,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_conta)
);

create table movimentacao(
	id_movimentacao	int not null AUTO_INCREMENT,
    valor			double not null,
    id_origem		int not null,
    id_destino		int not null,
    operacao		varchar(100) not null,
    tipo_operacao	varchar(100) not null,
    descricao		varchar(1000),
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id_movimentacao)
);

ALTER TABLE `trabalho`.`cliente` 
CHARACTER SET = utf8mb4 , ENGINE = InnoDB ;

ALTER TABLE `trabalho`.`conta` 
CHARACTER SET = utf8mb4 , ENGINE = InnoDB ;

ALTER TABLE `trabalho`.`movimentacao` 
CHARACTER SET = utf8mb4 , ENGINE = InnoDB ;

ALTER TABLE `trabalho`.`tickers` 
CHARACTER SET = utf8mb4 , ENGINE = InnoDB ;

ALTER TABLE `trabalho`.`conta` 
ADD INDEX `id_cliente_idx` (`id_cliente` ASC);

ALTER TABLE `trabalho`.`conta` 
ADD CONSTRAINT `id_cliente`
  FOREIGN KEY (`id_cliente`)
  REFERENCES `trabalho`.`cliente` (`id_cliente`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;


ALTER TABLE `trabalho`.`cliente` 
ADD INDEX `id_conta_idx` (`id_conta` ASC);

ALTER TABLE `trabalho`.`cliente` 
ADD CONSTRAINT `id_conta`
  FOREIGN KEY (`id_conta`)
  REFERENCES `trabalho`.`conta` (`id_conta`)
  ON DELETE SET NULL
  ON UPDATE NO ACTION;


CREATE TABLE `trabalho`.`ordem` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `id_conta` INT NOT NULL,
  `tipo_ordem` VARCHAR(45) NULL,
  `ticker` VARCHAR(5) NOT NULL,
  `quantidade` INT NOT NULL,
  `estado` VARCHAR(45) NULL,
  `valor` DOUBLE NOT NULL,
  `valor_total` DOUBLE NOT NULL,
  `data_alteracao` DATETIME NULL,
  `data_criacao` DATETIME NULL DEFAULT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;


UPDATE `trabalho`.`cliente` SET `id_conta` = '1' WHERE (`id_cliente` = '1');

insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("adm", "123", "João Armless", "77328134054", "Rua dos Bobos, 340", "34976378219", "ADM", "80-09-01", "01-09-01");

insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("comum1", "321", "Jojo Todyson", "24520690005", "Se essa rua fosse minha, 450", "84933821382", "COMUM", "80-09-01", "01-09-01");
insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("comum2", "321", "MC Carol", "24520690005", "Se essa rua fosse minha, 82", "84933821382", "COMUM", "80-09-01", "01-09-01");
insert into cliente (login, senha, nome, cpf, endereco, telefone, tipo, data_criacao, data_alteracao)
values ("comum3", "321", "Roberto Carlos", "60514235080", "Se essa rua fsosse minha, 420", "84933821382", "COMUM", "80-09-01", "01-09-01");



insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("João Pedro & Paulo", "JPP", 5, 50.0, "80-09-01", "01-09-01");
insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("Magalu", "MAG", 5, 50.0, "80-09-01", "01-09-01");
insert into tickers (nome_empresa, ticker, total_ativos, preco_inicial, data_criacao, data_alteracao)
values ("Only fans", "ONF", 5, 50.0, "80-09-01", "01-09-01");

insert into ordem (id_conta, tipo_ordem, ticker, quantidade, estado, valor, valor_total, data_criacao, data_alteracao) 
values (1,"VENDA","JPP",5,"TOTAL",50.0,250.0,"80-09-01","01-09-01");
insert into ordem (id_conta, tipo_ordem, ticker, quantidade, estado, valor, valor_total, data_criacao, data_alteracao) 
values (1,"VENDA","MAG",5,"TOTAL",50.0,250.0,"80-09-01","01-09-01");
insert into ordem (id_conta, tipo_ordem, ticker, quantidade, estado, valor, valor_total, data_criacao, data_alteracao) 
values (1,"VENDA","ONF",5,"TOTAL",50.0,250.0,"80-09-01","01-09-01");

insert into conta (id_cliente, saldo, data_criacao, data_alteracao)
values (1 , 500000, "80-09-01", "01-09-01");

create table ultima_negociacao(
	id				int not null AUTO_INCREMENT,
    quantidade		int not null,
    valor			double not null,
    valor_total		double not null,
    ticker			varchar(100) not null,
    id_conta_compra	int not null,
    id_conta_venda	int not null,
    data_criacao   	DateTime not null,
	data_alteracao 	DateTime,
    PRIMARY KEY (id)
);

CREATE TABLE `trabalho`.`ordem_execucao` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `quantidade` INT NOT NULL,
  `id_conta_compra` INT NULL,
  `id_conta_vende` INT NULL,
  `id_ordem` INT NOT NULL,
  `data_alteracao` DATETIME NOT NULL,
  `data_criacao` DATETIME NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4;

-- drop table ultima_negociacao;
