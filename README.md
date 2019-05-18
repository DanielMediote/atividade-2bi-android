# Projeto em Android de Farmacia Hospitalar

OBS: Leia essa bagassa

### Requisitos do Professor:

  - O App deve ter uma tela para login com usuário e senha;
  - Uma tela que liste os usuários cadastrados;
  - A tela que lista o usuário, só pode ser vista pelo usuário de nível "gerente" e ele pode excluir registros de lá;
  - Todos os dados devem ser armazenados em banco de dados;
  - O login deverá ter a opção para saber se quer "lembrar" dele para que no próximo login ele já mostre o login do último usuário que logou e ao entrar mostrar a data num “toast” da última vez que entrou na aplicação. (salvar isso nas SharedPreferences);
  - Ter além da tabela de usuários, pelo menos mais 1 para armazenar dados sobre o negócio da aplicação, ao menos um campo dessa outra tabela tem que ser do tipo data;
  - Deverá usar os seguintes componentes obrigatóriamente (spinner, progresssbar, SearchView e listas).


### Iniciando o Projeto
Clonar o repositório remoto no seu computador - geralmente na pasta Documentos.

```sh
$ git clone https://github.com/DanielMediote/atividade-2bi-android.git
```

### Salvando Alterações e Atualizando o repositório:
1. Apos fazer as alterações. Deve-se modificar o repositório local com o seguinte comando.
  ```sh
  $ git add .
  ```
2. Em seguita dar um commit.
  ```sh
  $ git commit -m "Minhas Alterações"
  ```
3. Atualizar o repositório remoto.
  ```sh
  $ git push origin master
  ```
  
#### Antes de qualquer modificação, é sempre bom manter o repositório local atualizado, assim evita de quando dar um *push*, os arquivos entrarem em conflito. Ultilize o comando para manter o repositório atualizado.
```sh
$ git pull origin master
```
