import { http } from './config'


export default {
  
  listar: () => {
    return http.get('/produtos');
  },

  salvar: (produto) => {
    return http.post('produtos', produto);
  },

  atualizar: (produto) => {
    return http.put(`produtos/${produto.id}`, produto);
  },

  deletar: (id) => {
    return http.delete(`produtos/${id}`);
  }

}