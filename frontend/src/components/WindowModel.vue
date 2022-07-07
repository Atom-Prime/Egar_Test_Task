<template>
  <div class="modal-backdrop">
    <div class="modal">
      <header class="modal-header">
        <slot name="header">
          Добавление записи
          <button type="button" class="btn-close" @click="close">x</button>
        </slot>
      </header>
      <section class="modal-body">
        <slot name="body">
      
            <input type="text" placeholder="Write name" v-model="item.paper" />
            <input type="text" placeholder="Write price" v-model="item.price" />
            <input type="date" required placeholder="Write date" v-model="item.date"
                  min="2000-01-01" max="2022-12-31">
            <input type="button" value="Save" @click="save" />
     
        </slot>
      </section>  
    </div>
  </div>
</template>


<script>
import { PapersApi } from "@/api/moduls/Papers.js";
export default {
  name: "window-model",
  emits: ["AddPaper"],
  data: function () {
    return {
      item: { id: "", paper: "", price: "" ,date: ""},
    };
  },
  methods: {
    save: function () {
     
      let item = this.item;
       if (item.date===""){
        alert("Введите дату")
        return
      }
      
      PapersApi.PaperPost(item).then((result) => {
        this.$emit("addPaper", result.data);
        this.item.paper = "";
        this.item.price = "";
        this.item.date = "";
        this.$emit("close");
      });
    },

    close() {
      this.$emit("close");
    },
  },
};
</script>


<style>
.modal-backdrop {
  position: fixed;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background-color: rgba(0, 0, 0, 0.3);
  display: flex;
  justify-content: center;
  align-items: center;
}

.modal {
  background: #ffffff;
  box-shadow: 2px 2px 20px 1px;
  overflow-x: auto;
  display: flex;
  flex-direction: column;
}

.modal-footer {
  padding: 15px;
  display: flex;
}

.modal-header {
  border-bottom: 1px solid #eeeeee;
  color: #4aae9b;
  justify-content: space-between;
}

.modal-footer {
  border-top: 1px solid #eeeeee;
  justify-content: flex-end;
}

.modal-body {
  position: relative;
  padding: 20px 10px;
}

.btn-close {
  border: none;
  font-size: 20px;
  padding: 5px;
  cursor: pointer;
  font-weight: bold;
  color: #4aae9b;
  background: transparent;
}

.btn-green {
  color: white;
  background: #4aae9b;
  border: 1px solid #4aae9b;
  border-radius: 2px;
}
</style>