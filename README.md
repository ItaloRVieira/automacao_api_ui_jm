# automacao_api_ui_jm

Projeto de automação de testes cobrindo **API**, **UI** e **Performance**, com execução integrada via pipeline de CI.

O objetivo é validar diferentes camadas da aplicação garantindo **qualidade, confiabilidade e análise de desempenho**.

---

## 🚀 Tecnologias utilizadas

- **Java 11 + Maven** → testes de API  
- **JUnit + RestAssured** → validações de serviço  
- **Cypress (JavaScript)** → testes de interface  
- **JMeter** → testes de carga  
- **GitHub Actions** → integração contínua  

---

## 📁 Estrutura do projeto

```
automacao_api_ui_jm/
├── automacao_api/              # Testes de API (RestAssured)
├── ui/                     # Testes de UI (Cypress)
│   └── cypress/
│       └── reports/        # Relatórios HTML
├── carga_api_jm/            # Testes de carga (JMeter)
├── .github/workflows/      # Pipeline CI
└── README.md
```

---

## ✅ Cenários implementados

### 🔹 API
Validação dos endpoints com foco em:

- Status code
- Dados retornados
- Regras de negócio

---

### 🔹 UI (Cypress)

Automação do fluxo de pesquisa:

- ✔️ Pesquisa válida e validação de resultados  
- ✔️ Pesquisa inválida e validação de erro  
- ✔️ Pesquisa sem preenchimento  

---

### 🔹 Performance (JMeter)

Simulação de carga no fluxo:

- Home  
- Busca de voo  
- Escolha de voo  
- Compra de passagem  

---

## ⚙️ Como executar

### Clonar o projeto

```
git clone https://github.com/ItaloRVieira/automacao_api_ui_jm.git
cd automacao_api_ui_jm
```

### Executar testes de API

```
cd automacao_api
mvn clean test
```

### Executar testes de UI

```
cd ui
npm install
npx cypress run
```

Modo interativo:

```
npx cypress open
```

### Executar testes de performance

1. Abrir o JMeter  
2. Importar o arquivo .jmx  
3. Executar o teste  

---

## 🔄 Pipeline (CI)

Executada via GitHub Actions:

- Push  
- Pull Request  
- Execução manual  

### Etapas:

- Checkout  
- Setup Java  
- Testes de API  
- Testes de UI  
- Relatórios  

---

## 📊 Resultados

### UI
- 3 cenários executados  
- 100% sucesso  
- 0 falhas
- Instabilidade na perfomance do site que afeta no carregamento dos elementos 
- ~25.6s  

### Performance
- 88.814 requisições  
- 0% erro  
- 292,8 req/s  
- 743 ms médio  

**Destaque:**  
Compra de passagem → 1855 ms  

---

## 🎯 Objetivo

Aplicar automação em múltiplas camadas garantindo:

- Qualidade  
- Cobertura  
- Confiabilidade  
- Performance  
