# Flash-Point

Aplicação de registro de entregas.


    |Endereço
    |-------- Cliente
    |---------------- Entrega

## Navegação

- Via console
- - Por numeros

## Console

- Cadastrar Entregas
- Listar Entregas
- - Listar Todas Entregas
- - Buscar Entrega (Por ID)
- - Listar Entregas Por status
- - Deletar
- Listar Clientes
- - Listar Todos clientes
- - Buscar por CPF
- Alterar Status

### Banco de Dados
[Script DB](https://github.com/Vinicius-moura-code/Flash-Point/blob/main/src/main/java/services/database/scriptDB.sql "Script DB")

## Instalação 

- Baixe o projeto
- Crie o aquivo "bd.properties"

-- Exemplo:

    user=root
    password=123456
    dburl=jdbc:mysql://localhost:3306/flashpoint
    useSSL=false
	 
- Execulte o Script SGBD
