import {postRequest_v2,getRequest} from "../utils/ajax";

export const bookSearch = (keyword,callback) => {
    const data = {keyword:keyword};
    const url = `http://localhost:8050/getBooks`;
    getRequest(url, data, callback);
}

export const authorSearch = (bookName,callback) => {
    const data = {bookName:bookName};
    const url = `http://localhost:8050/search/authorSearch`;
    postRequest_v2(url, data, callback);
}