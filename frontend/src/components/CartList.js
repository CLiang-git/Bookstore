import React from "react";
import {Avatar, List} from "antd";
import {getBooks} from "../services/bookService";
import {Book} from "./Book";
import {getCart} from "../services/cartService";
import {CartItem} from "./CartItem";

//购物车组件
export class CartList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {cart:[]};
    }

    componentDidMount() {
        const callback =  (data) => {
            // console.log('Received values of cart: ', data);
            this.setState({cart:data});
            console.log('cart now: ',this.state.cart);
        };
        getCart(1, callback);

    }

    deleteOnClick = ()=>{
        let i=this.state.cart[2].cartId
    }

    render() {
        return(
            <List
                itemLayout="horizontal"
                dataSource={this.state.cart}
                renderItem={item => (
                    <List.Item actions={[<a key="list-loadmore-edit">edit</a>, <a key="list-loadmore-more" onClick={this.deleteOnClick}>删除</a>]}>
                        {/*<List.Item.Meta*/}
                        {/*    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}*/}
                        {/*    title={<a href="https://ant.design">{item.title}</a>}*/}
                        {/*    description="Ant Design, a design language for background applications, is refined by Ant UED Team"*/}
                        {/*/>*/}
                        <CartItem info={item} />
                    </List.Item>
                )}
            />
        )
    }
}