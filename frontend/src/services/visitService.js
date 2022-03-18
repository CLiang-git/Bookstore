import config from 'config';
import {postRequest, postRequest_v2} from "../utils/ajax";

export const visit = (callback)=>{
    const url = `${config.apiUrl}/visit`;
    function data() {}
    postRequest_v2(url, data,callback);
}