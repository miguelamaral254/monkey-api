

## Endpoints da API de Macacos

### 1. **Obter Detalhes de um Macaco por ID**

* **URL**: `GET {{base_url}}/monkeys/{id}`

* **Descrição**: Este endpoint retorna os detalhes de um macaco específico pelo ID.

* **Exemplo de Requisição**:

```http
GET {{base_url}}/monkeys/1
```

* **Resposta**:

```json
{
  "id": 1,
  "name": "Charlie",
  "species": "Chimpanzee",
  "averageWeight": 60.5,
  "diet": "Fruits and Insects",
  "habitat": "Tropical Forest",
  "enabled": true,
  "imageUrl": "https://example.com/images/charlie.jpg"
}
```

---

### 2. **Criar um Novo Macaco**

* **URL**: `POST {{base_url}}/monkeys`

* **Descrição**: Este endpoint cria um novo macaco com as informações fornecidas no corpo da requisição.

* **Exemplo de Requisição**:

```http
POST {{base_url}}/monkeys
```

* **Corpo da Requisição**:

```json
{
  "name": "Charlie",
  "species": "Chimpanzee",
  "averageWeight": 60.5,
  "diet": "Fruits and Insects", 
  "habitat": "Tropical Forest",
  "imageUrl": "https://example.com/images/charlie.jpg"
}
```

* **Resposta**:

```json
{
  "id": 1,
  "name": "Charlie",
  "species": "Chimpanzee",
  "averageWeight": 60.5,
  "diet": "Fruits and Insects",
  "habitat": "Tropical Forest",
  "enabled": true,
  "imageUrl": "https://example.com/images/charlie.jpg"
}
```

---

### 3. **Ativar ou Desativar um Macaco (Habilitar/Desabilitar)**

* **URL**: `GET {{base_url}}/monkeys/{id}/enabled?enabled=true`

* **Descrição**: Este endpoint altera o status de habilitação de um macaco. A URL permite ativar ou desativar um macaco com base no parâmetro `enabled`.

* **Exemplo de Requisição**:

```http
GET {{base_url}}/monkeys/1/enabled?enabled=true
```

* **Resposta**:

```json
{
  "message": "Monkey with ID 1 has been successfully updated to enabled."
}
```

---

### 4. **Buscar Macacos com Filtros de Especificação**

* **URL**: `GET {{base_url}}/monkeys`

* **Descrição**: Este endpoint permite buscar macacos com filtros de especificação, como `species`, `habitat`, e `enabled`. Além disso, ele suporta paginação para controlar os resultados.

* **Parâmetros de Query**:

  * `species` (opcional): Filtra os macacos pela espécie.
  * `habitat` (opcional): Filtra os macacos pelo habitat.
  * `enabled` (opcional): Filtra os macacos com base no status de habilitação.
  * `page` (opcional): Número da página para a paginação.
  * `size` (opcional): Tamanho da página.

* **Exemplo de Requisição**:

```http
GET {{base_url}}/monkeys?species=Chimpanzee&habitat=Tropical%20Forest&enabled=true&page=1&size=10
```

* **Resposta**:

```json
{
  "content": [
    {
      "id": 1,
      "name": "Charlie",
      "species": "Chimpanzee",
      "averageWeight": 60.5,
      "diet": "Fruits and Insects",
      "habitat": "Tropical Forest",
      "enabled": true,
      "imageUrl": "https://example.com/images/charlie.jpg"
    },
    {
      "id": 2,
      "name": "Mia",
      "species": "Orangutan",
      "averageWeight": 55.0,
      "diet": "Fruits and Leaves",
      "habitat": "Tropical Forest",
      "enabled": true,
      "imageUrl": "https://example.com/images/mia.jpg"
    }
  ],
  "pageable": {
    "sort": {
      "unsorted": true,
      "sorted": false,
      "empty": true
    },
    "pageSize": 10,
    "pageNumber": 1,
    "offset": 10,
    "unpaged": false,
    "paged": true
  },
  "totalPages": 5,
  "totalElements": 50,
  "last": false,
  "size": 10,
  "number": 1,
  "sort": {
    "unsorted": true,
    "sorted": false,
    "empty": true
  },
  "first": false,
  "numberOfElements": 10,
  "empty": false
}
```

---
