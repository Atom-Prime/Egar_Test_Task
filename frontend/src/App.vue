<template>
  <div style="display:flex; width: 1400px;" >
    <div style="flex: 1 1 30%">
      <paper-table  @putPaper="putPaper" :papers="papers"> </paper-table>
      
      <button
          type="button"
          class="btn"
          @click="showModal"
        >
          Добавление
        </button>
    </div>
    <PaperBar style="flex: 1 1 70% " :papersDates="papersDates" :papersItems="papersItems"/>
    </div>
    <window-model 
      @addPaper="addPaper"
      v-show="isModalVisible"
      @close="closeModal"
    />
    

   
</template>

<script>

import PaperTable from "./components/PaperTable";
import {PapersApi} from '@/api/moduls/Papers.js'
import WindowModel from "./components/WindowModel.vue";
import PaperBar from "./components/PaperBar.ts";

export default {
  name: "App",
  components: {
    PaperTable, WindowModel, PaperBar
  },
  data: function () {
    return {
      width:700,
      papersDates:[],
      papersItems:[],
      testMas:[],
      papers: [],
      isModalVisible: false,
    };
    
  },
  mounted() {
    PapersApi.PaperGet()
      .then((result) => {
      this.papers = result.data
      this.fillData()
      });
    
    
  },
  methods: {
    fillData(){
      this.papersDays=[]
      for(this.i in this.papers){
        this.item=this.papers[this.i].date.split("-")
        this.papersDays[this.i]=this.item[0]*365+this.item[1]*30.4+this.item[2]*1-30.4

      }
      this.maxM=Math.floor(Math.max(...this.papersDays))
      this.minM=Math.floor(Math.min(...this.papersDays))

      this.papersDates.splice(0,this.papersDates.length)
      this.papersItems.splice(0,this.papersItems.length)
      this.papersDays=[]
      this.papersNames=[]
      this.i = 0

      while(this.i<=this.maxM-this.minM){
        
        this.papersDates[this.i]=Math.floor((((this.i+this.minM)%365))%30.4+1)+"-"+Math.floor(((this.i+this.minM)%365)/30.4+1)+"-"+Math.floor((this.i+this.minM)/365)
        this.papersDays[this.i]=this.minM+this.i
        this.i=this.i+1
      }

      for (this.i in this.papers ){
        if ((!this.papersNames.includes(this.papers[this.i].paper)) && (!this.papers[this.i].paper=='')){
          this.papersNames.push(this.papers[this.i].paper)
        }
       
      }
     
     
      for (this.i in this.papersNames ){
        this.prices=[]
        for(this.k in this.papersDays){
        this.prices[this.k]=null
        }
        
        for (this.j in this.papers ){
          if(this.papers[this.j].paper==this.papersNames[this.i] && this.papers[this.j].price!=""){
            this.prices[this.papersDays.indexOf(Math.floor(this.papers[this.j].date.split("-")[0]*365+this.papers[this.j].date.split("-")[1]*30.4+this.papers[this.j].date.split("-")[2]*1-30.4))]=this.papers[this.j].price
          }
        }
       
        this.count=0
        for(this.k in this.prices){
          if(this.prices[this.k]==null){
            if( this.count!=0){
              this.count++
            }
          }  
          else{
            if(this.count!=0){
              this.razn=(this.prices[this.k]-this.prices[this.k-this.count])/this.count
              this.l=this.count-1
              while(this.l>0){
                this.prices[this.k-this.l]=this.prices[this.k]-(this.razn*this.l)
                this.l--
              }
            }
            this.count=1
          }
        }

        this.colorMas=['#ff0000','#ff4500','#ffd700','#008000','#0000ff','#00008b','#8b008b','#7cfc00','#000000','#ffa07a']
  
        this.papersItems.push({
          label: this.papersNames[this.i],
          backgroundColor: this.colorMas[this.i%10],
          data: this.prices
        })
  
      }

    },
    showModal() {
        this.isModalVisible = true;
      },
    closeModal() {
      this.isModalVisible = false;
    },
    putPaper(result) {
      function getIndex(list, id) {
        for (var i = 0; i < list.length; i++) {
          if (list[i].id === id) {
            return i;
          }
        }
        return -1;
      }
      let index = getIndex(this.papers, result.id);
      this.papers.splice(index, 1, result);
      this.fillData()
    },
    addPaper(value) {
      this.papers.push(value);
      this.fillData()
    },
  },
};
</script>

<style>
</style>
