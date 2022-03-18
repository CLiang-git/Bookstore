import React from "react";
import {withRouter} from "react-router";
import {Button, Icon, Input} from "antd";
import {ChatRoom} from "../components/ChatRoom";

class ChatView extends React.Component{

    render(){
        return(
            <div>

                <ChatRoom/>
            </div>
        );
    }

}

export default withRouter(ChatView);