<template>
  <div id="app">
    <nav>
      <div class="nav-wrapper blue darken-1">
        <a href="#" class="brand-logo center">Produtos</a>
      </div>
    </nav>

    <div class="container">
      <ul>
        <li v-for="(erro, index) of erros" :key="index">
            campo <b>{{ erro.field }}</b> - {{ erro.defaultMessage }}
        </li>
      </ul> 
      
      <form @submit.prevent="salvar">
        <label>Nome</label>
        <input type="text" placeholder="Nome" v-model="produto.nome">

        <label>Quantidade</label>
        <input type="number" placeholder="Quantidade" v-model="produto.quantidade">

        <label>Valor</label>
        <input type="number" placeholder="Valor" v-model="produto.valor">

        <button class="waves-effect waves-light btn-small salvar">
          Salvar  <i class="material-icons left">save</i>
        </button>
      </form>

      <table>
        <thead>
          <tr>
            <th>NOME</th>
            <th>Quantidade</th>
            <th>VALOR</th>
            <th>OPÇÕES</th>
          </tr>
        </thead>

        <tbody v-for="produto of produtos" :key="produto.id">
          <tr>
            <td>{{produto.nome}}</td>
            <td>{{produto.quantidade}}</td>
            <td>{{produto.valor}}</td>
            <td>
              <button @click="editar(produto)" class="waves-effect btn-small blue darken-1">
                <i class="material-icons">create</i>
              </button>
              <button @click="deletar(produto)" class="waves-effect btn-small red darken-1">
                <i class="material-icons">delete_sweep</i>
              </button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script>
  import Produto from './services/produtos';
  
  export default {
    data() {
      return {
        produto: {
          id: '',
          nome:'',
          quantidade:'',
          valor:''
        },

        produtos: [],
        erros: [],
      }
    },

    mounted() {
      this.listar();
    },

    methods: {
      listar() {
        Produto.listar().then(resposta => {
          this.produtos = resposta.data;
        });
      },

      salvar() {
        if (!this.produto.id) {
          Produto.salvar(this.produto).then(() => {
            alert('Produto salvo com sucesso!');
            this.clean();
            this.listar();
          }).catch(err => {
            this.erros = err.response.data.errors;
          });
        } else {
          Produto.atualizar(this.produto).then(() => {
            alert('Produto atualizado com sucesso!');
            this.clean();
            this.listar();
          }).catch(err => {
            this.erros = err.response.data.errors;
          });
        }
      },

      deletar(produto) {
        const { id } = produto;
        if(confirm('Deseja realmente excluir o produto ?')){
          Produto.deletar(id).then(() => {
            alert('Produto deletado com sucesso!');
            this.listar();
            this.clean();
          }).catch(err => {
            this.erros = err.response.data.errors;
          });
        }
      },

      editar(produto) {
        this.produto = produto;
      },

      clean() {
        this.produto = {};
        this.erros = [];
      },
    }
  }

</script>

<style>
  .salvar{
    float: right; 
    margin-top: 20px; 
    margin-bottom: 30px;
  }
</style>
