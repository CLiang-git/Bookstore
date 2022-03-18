import config from 'config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {history} from "../utils/history";
import {message} from "antd";

export const getCart = (userId,callback) => {
    const data = {userId:userId};
    const url = `${config.apiUrl}/getCart`;
    postRequest_v2(url, data, callback);
}

export const getCartItem = (cartId,callback) => {
    const data = {cartId:cartId};
    const url = `${config.apiUrl}/getCartItem`;
    postRequest_v2(url, data, callback);
}

export const addCart = (userId,bookId) => {
    const data = {userId:userId,bookId:bookId};
    const url = `${config.apiUrl}/addCart`;
    const callback = (data) => {//登录请求后回调函数
        console.log("data: ",data.toString());
        if(data === true) {
            message.success("加购成功");
        }
        else{
            // console.log("unsuccess login data:",data);
            message.error("加购失败");
        }
    };
    postRequest_v2(url,data,callback);
}

export const deleteCart = (userId,bookId,callback) => {
    const data = {userId:userId,bookId:bookId};
    const url = `${config.apiUrl}/deleteCart`;
    postRequest_v2(url,data,callback);
}

