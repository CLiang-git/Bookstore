import React from 'react';
import { Card } from 'antd';

import {Link} from 'react-router-dom'
import {getBookImage, getBooks} from "../services/bookService";
import {getCartItem} from "../services/cartService";
import {Book} from "./Book";

const { Meta } = Card;

//购物车里单件物品
export class CartItem extends React.Component{
    constructor(props) {
        super(props);
        this.state = {bookImage:"",bookInfo:{}};
    }

    componentDidMount(){

        getCartItem(this.props.info.cartId,(data) => {
            this.setState({bookInfo:data});
            // console.log('bookInfo now: ',this.state.bookInfo);
            getBookImage(this.state.bookInfo.bookId, (data) => {
                this.setState({bookImage:data.imageFile});
            });
        })


    }

    render() {
        // console.log('bookId: ',this.state.bookInfo.bookId);
        const {info} = this.props;

        return (
            <Link to={{
                pathname: '/bookDetails',
                search: '?id=' + this.state.bookInfo.bookId}}
            >
                <Card
                    hoverable
                    style={{width: 181}}
                    cover={<img alt="image" src={this.state.bookImage} className={"bookImg"}/>}
                >
                    <Meta title={this.state.bookInfo.name} description={'¥' + this.state.bookInfo.price}/>
                </Card>
            </Link>
        );
    }

}

