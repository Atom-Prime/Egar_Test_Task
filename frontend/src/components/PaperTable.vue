<template>
 
  <table>
    <thead>
      <tr>
        <th>Бумага</th>
        <th>Цена</th>
        <th>Дата</th>
       </tr>
    </thead> 
    <tbody>
    <tr v-for="item in papers" :key="item.id" >
        <td>  <input type = text  v-model="item.paper" @change="updatePaper(item,item.id)"/></td>
        <td>  <input type = text  v-model="item.price" @change="updatePaper(item,item.id)"/></td>
        <td>  <input type = "date"  v-model="item.date" @change="updatePaper(item,item.id)" min="2000-01-01" max="2022-12-31"/></td>
     </tr>
    </tbody>
    
  </table>

</template>

<script>
import { PapersApi } from '@/api/moduls/Papers.js'
export default {
  emits: ["putPaper"],
  props: ["papers"],
  data: function () {
    return {
      paper: null,
    };
  },
  methods: {
    updatePaper(item,id){
      PapersApi.PaperPut(id, item)
      this.$emit("putPaper", item);
    },
    editMethod: function (item) {
      this.paper = item;
    },
  },

};
</script>

<style>
table {
   border: 1px solid grey;
   width: 150px;
}


th {
   border: 1px solid grey;
}
td {
   border: 1px solid grey;
}
</style>