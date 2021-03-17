import axios from "axios";

export default axios.create({
    baseURL: "http://localhost:8081/bean-app",
// headers: {'Content-type': 'application/x-www-form-urlencoded'}
    })