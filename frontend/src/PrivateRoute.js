import React from 'react';
import {Route, Redirect} from 'react-router-dom'
import * as userService from "./services/userService"
import {message} from "antd";

export default class PrivateRoute extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            isAuthed: false,//是否已经认证通过
            hasAuthed: false,//是否请求认证过
        };
    }

    checkAuth = (data) => {
        // console.log(data);
        if (data.status >= 0) {
            // console.log('PrivateRoute: checkAuth success ^_^');
            this.setState({isAuthed: true, hasAuthed: true});
        } else {
            // console.log('PrivateRoute: checkAuth fail *_*');
            localStorage.removeItem('user');
            this.setState({isAuthed: false, hasAuthed: true});
        }
    };


    componentDidMount() {
        userService.checkSession(this.checkAuth);
    }


    render() {

        const {component: Component, path="/",exact=false,strict=false} = this.props;

        console.log('isAuthed: ',this.state.isAuthed);

        if (!this.state.hasAuthed) {
            return null;
        }

        return <Route path={path} exact={exact} strict={strict} render={props => (
            this.state.isAuthed ? (
                <Component {...props}/>
            ) : (
                <Redirect to={{
                    pathname: '/login',
                    state: {from: props.location}
                }}/>
            )
        )}/>
    }
}

