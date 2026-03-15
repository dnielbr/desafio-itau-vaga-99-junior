# [desafio-itau-vaga-99-junior](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior?tab=readme-ov-file)

## Documentação do Sistema

**Como construir e executar a aplicação**

Esta aplicação foi desenvolvida utilizando **Java 21**, **Spring Boot** e **Gradle**. Você pode executar a aplicação de duas formas: diretamente na sua máquina instalando as dependências, ou através de contêineres Docker.

### Pré-requisitos
- **Java 21** instalado (para execução local)
- **Docker** instalado (opcional, para execução via contêinerização)

---

### Opção 1: Executando localmente via Gradle ou JAR

**1. Construir a aplicação**
No diretório raiz do projeto (onde o arquivo `build.gradle` está localizado), execute o comando para baixar as dependências e criar o arquivo construído `.jar`:

* No Linux/macOS:
  ```bash
  ./gradlew build
  ```
* No Windows:
  ```cmd
  gradlew.bat build
  ```

**2. Executar a aplicação via Gradle**
Você pode iniciar a aplicação utilizando a sub-tarefa do plugin Spring Boot:

* No Linux/macOS:
  ```bash
  ./gradlew bootRun
  ```
* No Windows:
  ```cmd
  gradlew.bat bootRun
  ```

*Alternativa: Rodar o `.jar` diretamente*
Caso prefira rodar a aplicação construída na primeira etapa sem depender do Gradle (comum em servidores sem suporte ao build gradle):
```bash
java -jar build/libs/desafio-itau-vaga-99-junior-0.0.1-SNAPSHOT.jar
```
*(O nome exato do `.jar` pode variar com a versão no `build.gradle`. Ele estará dentro do diretório `build/libs/`)*

A partir daqui a aplicação irá expor os endpoints e a porta padrão deve estar rodando em: `http://localhost:8080/`

---

### Opção 2: Executando com Docker

Se preferir não instalar o Java localmente, você pode empacotar e executar a aplicação em um ambiente isolado usando o Dockerfile do projeto.

**1. Construir a Imagem Docker**
No diretório raiz (onde está o `Dockerfile`), digite no terminal para começar o processo de construção da imagem:

```bash
docker build -t desafio-itau-vaga-99-junior .
```
> Obs: Esse passo compila o código e gera o `.jar` dentro da primeira camada da imagem a partir de uma base com gradle, e em seguida copia de forma limpa para a imagem final utilizando um `jre`.

**2. Executar o Contêiner**
Com a imagem feita, basta rodá-la mapendo as portas para acesso:

```bash
docker run -p 8080:8080 desafio-itau-vaga-99-junior
```

Sua aplicação subirá em poucos segundos em: `http://localhost:8080/`
