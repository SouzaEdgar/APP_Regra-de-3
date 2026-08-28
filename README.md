# Regra de 3

Aplicativo Android desenvolvido em **Kotlin** utilizando **Jetpack Compose** para realizar cálculos de **Regra de 3 Simples** de forma rápida, intuitiva e eficiente.

Além de ser uma ferramenta funcional, este projeto faz parte da minha jornada de aprendizado em desenvolvimento Android, onde pratico conceitos como **arquitetura MVVM**, componentização da interface, gerenciamento de estado e boas práticas de desenvolvimento.

---

## Funcionalidades

- Cálculo de Regra de 3 Simples.
- Escolha entre cálculo direto ou inverso.
- Seleção do tipo de cálculo através de opções na interface.
- Apresentação das etapas do cálculo.
- Atualização automática do valor de X após o resultado.

---

## Tecnologias

- Kotlin
- Jetpack Compose
- Material 3
- MVVM
- Android Studio

---

## Estrutura do Projeto

```text
regrade3
├── domain 
│    ├── model 
│    │	   └── RuleOfThree.kt 
│    └── CalculateRuleOfThreeUseCase.kt 
│
├── ui 
│    ├── calculator
│    │    ├── components
│    │    │    ├── CalculateButton.kt
│    │    │    ├── CalculatorTable.kt
│    │    │    └── NumberInput.kt
│    │    │
│    │    └── CalculatorScreen.kt
│    │
│    └── theme 
│
├── utils
│    └── Validation.kt
│
└── MainActivity.kt
```

---

## Objetivo

Este projeto foi desenvolvido com o objetivo de praticar conceitos fundamentais do desenvolvimento Android, incluindo:

- Arquitetura MVVM.
- Componentização da interface.
- Gerenciamento de estado.
- Separação de responsabilidades.
- Organização de projetos.
- Boas práticas de desenvolvimento.

---
