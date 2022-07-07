import axios from "axios";

const axiosBaseConfig = {
    baseURL: 'http://localhost:8080/'
}

export const axiosInstance = axios.create(axiosBaseConfig)