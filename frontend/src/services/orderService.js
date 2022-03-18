import config from 'config';
import {postRequest, postRequest_v2} from "../utils/ajax";

export const getOrders = (userId,callback) => {
    const data = {userId:userId};
    const url = `${config.apiUrl}/getOrders`;
    postRequest_v2(url, data, callback);
}

export const getOrderBook = (Id,callback) => {
    const data = {Id:Id};
    const url = `${config.apiUrl}/getOrderBook`;
    postRequest_v2(url, data, callback);
}

export const getOrderBooks = (IdList,callback) => {
    const data = {IdList:IdList};
    const url = `${config.apiUrl}/getOrderBooks`;
    postRequest_v2(url, data, callback);
}

export const addOrder = (userId,bookId,callback) => {
    const data = {userId:userId,bookId:bookId};
    const url = `${config.apiUrl}/addOrder`;
    postRequest_v2(url,data,callback);
}


// export const deleteCart = (userId,bookId,callback) => {
//     const data = {userId:userId,bookId:bookId};
//     const url = `${config.apiUrl}/deleteCart`;
//     postRequest_v2(url,data,callback);
// }

