CREATE TABLE `address` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `rua` varchar(50) NOT NULL,
                           `numero` int NOT NULL,
                           `bairro` varchar(50) NOT NULL,
                           `cidade` varchar(50) NOT NULL,
                           `cep` varchar(50) NOT NULL,
                           `uf` varchar(50) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `client` (
                          `id` int NOT NULL AUTO_INCREMENT,
                          `nome` varchar(50) NOT NULL,
                          `cpf` varchar(15) NOT NULL,
                          `produto` varchar(50) NOT NULL,
                          `client_address` int NOT NULL,
                          PRIMARY KEY (`id`),
                          KEY `client_address` (`client_address`),
                          CONSTRAINT `client_ibfk_1` FOREIGN KEY (`client_address`) REFERENCES `address` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `delivery` (
                            `id` int NOT NULL AUTO_INCREMENT,
                            `status` varchar(50) NOT NULL,
                            `delivery_client` int NOT NULL,
                            PRIMARY KEY (`id`),
                            KEY `delivery_client` (`delivery_client`),
                            CONSTRAINT `delivery_ibfk_1` FOREIGN KEY (`delivery_client`) REFERENCES `client` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `statussolicitacao` (
                                     `id` int NOT NULL AUTO_INCREMENT,
                                     `status` varchar(50) NOT NULL,
                                     PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;