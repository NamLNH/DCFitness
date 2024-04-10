import http from "../http-common.js";

class CategoryService {
    getCategories() {
        return http.get("/categories");
    }    
}

export default new CategoryService();