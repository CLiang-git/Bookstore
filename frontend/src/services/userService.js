import config from 'config';
import {postRequest, postRequest_v2} from "../utils/ajax";
import {history} from '../utils/history';
import {message} from 'antd';

//roles,name,token都存在localStorage里，登录时保存，登出时删除

export const getUserRoles = ()=>{
    return JSON.parse(localStorage.getItem('userRoles'))
}

export const getUserName = ()=>{
    return localStorage.getItem('userName')
}

export const getToken =()=>{
    return localStorage.getItem('userToken')
}

const saveUserInfo = (data)=>{
    localStorage.setItem('userToken', data.token)
    localStorage.setItem('userRoles', JSON.stringify(data.userRoles))
    localStorage.setItem('userName', data.userName)
}

export const removeUserInfo = ()=>{
    localStorage.removeItem('userToken')
    localStorage.removeItem('userRoles')
    localStorage.removeItem('userName')
}

//获取用户身份
export const userRolesConvert = (userRoles)=>{
    let rolesList = []
    // const dict = {'ROLE_USER':'User','ROLE_ADMIN':'Admin'}
    const dict = {'User':'User','Admin':'Admin'}
    for(let role of userRoles){
        let userType = role['authority'];
        // console.log("userType: ",userType);
        if (userType in dict){
            rolesList.push(dict[userType])
        }else message.error("User Type Unknown");
    }
    return rolesList
}

//用户登录
export const login = (data) => {
    const url = `${config.apiUrl}/login`;
    const callback = (data) => {//登录请求后回调函数
        if(data.code === 20000) {
            saveUserInfo(data.data);//保存当前用户信息
            history.push("/home");//路由至主页
            // console.log("success login data:",data);
            message.success(data.message);
        }
        else{
            // console.log("unsuccess login data:",data);
            message.error(data.message);
        }
    };
    postRequest_v2(url, data, callback);
};

//用户登出
export const logout = () => {
    const url = `${config.apiUrl}/logout`;

    const callback = (data) => {
        if(data.code ===20000) {
            removeUserInfo();//删除当前用户信息
            history.push("/login");//路由至登录界面
            message.success(data.message);
        }
        else{
            message.error(data.message);
        }
    };
    postRequest(url, {}, callback);
};

export const checkSession = (callback) => {
    const url = `${config.apiUrl}/checkSession`;//这个接口后端暂时没有，但可以用返回的status判断token是否带上了本地的用户信息
    postRequest(url, {}, callback);
};

