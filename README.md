# Encaixe as Caixas utilizando Grafos

## A resolução do segundo trabalho de Algoritmo e Estrutura de Dados II.

Este projeto implementa um algoritmo para determina se uma quantidade de caixas podem ser encaixadas uma dentro das outras, utilizando HashMap para sua ordencação e grafo direcionado para a representação das relações de encaixe.

## Estrutura do Projeto
Esses são as principais classes do projeto:
* App2.java
* Diagraph.java
* DepthFirstSearch.java

## O Que o Algortimo Faz:
O algortimo se propõe a fazer:
### 1. Leitura e Ordenação das Caixas <br/>
   Ao receber um arquivo texto para ser lido, o algorítmo lê as dimensões das caixas e as ordena de maneira crescente.
### 2. Verificação de Encaixe <br/>
   Após ordenar as dimensões de cada caixa, o algoritmo verifica a possibilidade de encaixar as caixas entre si.
### 3. Construção do Grafo Direcionado <br/>
   O algoritmo constrói um grafo direcionado _(Diagraph)_ que representa as relações de encaixe entre as caixas. Neste contexto, uma caixa maior não pode ser encaixada em uma caixa menor, portanto, as relações são unidirecionais. As caixas são representadas por vértices e as arestas as conexões que indicam a possibilidade de uma caixa entrar dentro de outra. Por exemplo, se a caixa **1** pode ser encaixada dentro da caixa **2**, uma aresta será criada direcionada do vértice **1** para o vértice **2**.
### 4. Busca pela Maior Sequência de Encaixe
   O algoritmo procura a maior sequencia de encaixe dentro do grafo, utilizando busca em profundidade _(DepthFirstSearch)_. A sequência é definido pelo caminho mais longo no grafo.
### 5. Vizualização do Grafo
   Para permitir uma melhor compreensão das relações de encaixe entre as caixas, o algoritmo gera uma representaçãao visual no formato DOT, que pode ser utulizada em ferramentas como [WebGraphViz](http://www.webgraphviz.com/).

