import React from "react";
import {withRouter} from "react-router";
import {AutoComplete, Button, Card, Icon, Input, List} from "antd";
import {message} from 'antd';
import {getToken, getUserName} from "../services/userService";

export class ChatRoom extends React.Component{
    constructor(props) {
        super(props);
        this.state={websocket:null,username:null,messages:[],userList:null};
        this.nameRef = React.createRef();
    }

    componentDidMount() {
        this.connect();
        let val=getUserName();
        if (val!==undefined&&val.length>0) {
            this.setState({username:val});
        }else {message.error("fail to get username")}

    }

    componentWillUnmount() {
        this.state.websocket.close();
    }

    /* Connect to the Websocket endpoint
     * Set a callback for incoming messages */
    connect = ()=>{
        // wsocket = new WebSocket("ws://localhost:8080/SE3353_3_WebSocketChatRoom_war/websocketbot");
        // var opts = { reconnection: false, transports: ['websocket'], extraHeaders: { 'Authorization': 'Bearer xxxx' } }
        this.state.websocket = new WebSocket("ws://localhost:8080/chat");
        this.state.websocket.onmessage = this.onMessage;

    }

    /* Callback function for incoming messages
     * evt.data contains the message
     * Update the chat area, user's area and Websocket console */
    onMessage = (evt)=>{
        let line = "";
        /* Parse the message into a JavaScript object */
        let msg = JSON.parse(evt.data);
        if (msg.type === "chat") {
            line = msg.name + ": ";
            if (msg.target.length > 0)
                line += "@" + msg.target + " ";
            line += msg.message + "\n";
            /* Update the chat area */
            // textarea.value += "" + line;
            console.log(line);
            this.setState({
                messages: [...this.state.messages, line]
            });
        } else if (msg.type === "info") {
            line = "[--" + msg.info + "--]\n";
            /* Update the chat area */
            // textarea.value += "" + line;
            this.setState({
                messages: [...this.state.messages, line]
            });
        } else if (msg.type === "users") {
            line = "Users:\n";
            for (var i=0; i < msg.userlist.length; i++)
                line += "-" + msg.userlist[i] + "\n";
            /* Update the user list area */
            // userlist.value = line;
            this.setState({
                userList: line
            });
        }
        // textarea.scrollTop = 999999;
        /* Update the Websocket console area */
        // wsconsole.value += "-> " +  evt.data + "\n";
        // wsconsole.scrollTop = 999999;
    }

    /* Send a join message to the server */
    sendJoin = ()=>{
        // var input = document.getElementById("input");
        // var name = document.getElementById("name");
        // var join = document.getElementById("join");
        // var jsonstr;
        // this.connect();
        let val=this.state.username;
        if (val!==null) {
            /* Create a message as a JavaScript object */
            let joinMsg = {};
            joinMsg.type = "join";
            joinMsg.name = val;
            /* Convert the message to JSON */
            let jsonstr = JSON.stringify(joinMsg);
            /* Send the JSON text */
            this.state.websocket.send(jsonstr);
            /* Disable join controls */
            // name.disabled = true;
            // join.disabled = true;
            // input.disabled = false;
            // userName = name.value;
            /* Update the Websocket console area */
            // wsconsole.value += "<- " + jsonstr + "\n";
            // wsconsole.scrollTop = 999999;
        }
    }

    /* Send a chat message to the server (press ENTER on the input area) */
    sendMessage = ()=>{
        // var input = document.getElementById("input");
        let input=this.nameRef.current.state.value;
        let jsonStr;
        if (input!==undefined && input.length > 0) {
            /* Create a chat message as a JavaScript object */
            let chatMsg = {};
            chatMsg.type = "chat";
            chatMsg.name = this.state.username;
            chatMsg.target = this.getTarget(input.replace(/,/g, ""));
            chatMsg.message = this.cleanTarget(input);
            chatMsg.message = chatMsg.message.replace(/(\r\n|\n|\r)/gm,"");
            /* Convert the object to JSON */
            jsonStr = JSON.stringify(chatMsg);
            /* Send the message as JSON text */
            this.state.websocket.send(jsonStr);
            // this.nameRef.current.state.value = "";
            /* Update the Websocket console area */
            // wsconsole.value += "<- " + jsonstr + "\n";
            // wsconsole.scrollTop = 999999;
        }
    }

    // /* Send a join message if the user presses ENTER in the name area */
    // checkJoin = (evt)=>{
    //     var name = document.getElementById("name");
    //     var input = document.getElementById("input");
    //     if (evt.keyCode === 13 && name.value.length > 0) {
    //         sendJoin();
    //         input.focus();
    //     }
    // }

    /* Get the @User (target) for a message */
    getTarget = (str)=>{
        let arr = str.split(" ");
        let target = "";
        for (let i=0; i < arr.length; i++) {
            if (arr[i].charAt(0) === '@') {
                target = arr[i].substring(1,arr[i].length);
                target = target.replace(/(\r\n|\n|\r)/gm,"");
            }
        }
        return target;
    }

    /* Remove the @User (target) from a message */
    cleanTarget = (str)=>{
        let arr = str.split(" ");
        let cleanstr = "";
        for (let i=0; i < arr.length; i++) {
            if (arr[i].charAt(0) !== '@')
                cleanstr += arr[i] + " ";
        }
        return cleanstr.substring(0,cleanstr.length-1);
    }

    // /* Show or hide the WebSocket console */
    // showHideConsole = ()=>{
    //     var chkbox = document.getElementById("showhideconsole");
    //     var consolediv = document.getElementById("consolediv");
    //     if (chkbox.checked)
    //         consolediv.style.visibility = 'visible';
    //     else
    //         consolediv.style.visibility = 'hidden';
    // }


    nameJoin = () =>{
        let val=this.nameRef.current.state.value;
        // alert(val);
        console.log(val);
    }

    render(){
        return(
            <div>
                <div className="global-search-wrapper" style={{ width: 300 }}>
                    <Button
                        className="search-btn"
                        // style={{ marginLeft: -12 }}
                        // size="small"
                        type="primary"
                        onClick={this.sendJoin}
                    >
                        {/*<Icon type="search" />*/}
                        join chatroom
                    </Button>
                    <br/>
                    <br/>
                    <Card size="small" title="member list" style={{ width: 300 }}>
                        <p>{this.state.userList}</p>
                    </Card>
                    <br/>
                    <Input
                        type="text"
                        placeholder="please input nickname here"
                        size={"large"}
                        ref={this.nameRef}
                        suffix={
                            <Button
                                className="search-btn"
                                style={{ marginRight: -12 }}
                                size="large"
                                type="primary"
                                onClick={this.sendMessage}
                            >
                                {/*<Icon type="search" />*/}
                                send message
                            </Button>
                        }
                    />

                    <List
                        itemLayout="horizontal"
                        dataSource={this.state.messages}
                        renderItem={item => (
                            <List.Item>
                                <List.Item.Meta
                                    // avatar={<Avatar src={item.icon}/>}
                                    // title={<p>{item.name}</p>}
                                    description={item}
                                />
                            </List.Item>
                        )}
                    />
                </div>
            </div>

            // <div>
            // <h1>Chatroom</h1>
            // Your name: <input id="name" type="text" size="20" maxLength="20" onKeyUp="checkJoin(event);"/>
            // <input type="submit" id="join" value="Join!" onClick="sendJoin();"/>
            //     <br/><br/>
            // <textarea id="input" cols="70" rows="1" disabled="true"
            //           onKeyUp="sendMessage(event);"></textarea><br/>
            // <textarea id="textarea" cols="70" rows="20" readOnly="true"></textarea>
            // <textarea id="userlist" cols="20" rows="20" readOnly="true"></textarea>
            // <br/><br/><br/>
            // <input id="showhideconsole" type="checkbox" onClick="showHideConsole();"/>
            // Show WebSocket console<br/>
            // <div id="consolediv"><textarea id="wsconsole" cols="80" rows="8" readOnly="true"
            //                                style="font-size:8pt;"></textarea></div>
            // </div>
        );
    }

}