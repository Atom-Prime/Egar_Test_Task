import { axiosInstance } from "../Index";

export const PapersApi = {
    PaperGet() {
        return axiosInstance.get('/papers')
    },
    PaperPut(id,item) {
        return axiosInstance.put('/papers/' + id, item)
    },
    PaperPost(item) {
        return axiosInstance.post('/papers', item)
    }
}