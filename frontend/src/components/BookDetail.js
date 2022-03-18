import React from 'react';
import { Descriptions, Button } from 'antd';
import {getBookImage} from "../services/bookService";
import {addCart} from "../services/cartService";
import {history} from "../utils/history";

//书籍详情组件
export class BookDetail extends React.Component{
    constructor(props) {
        super(props);
        this.state = {bookImage:null};
    }

    componentDidMount() {
        if(this.props.info && this.props.info.bookId){
            this.setImage(this.props.info.bookId)
        }
    }

    setImage = (bookId)=>{
        console.log(bookId)
        const callback =  (data) => {
           this.setState({bookImage:data.imageFile});
        };
        if(bookId && !this.state.bookImage){
            getBookImage(bookId, callback);
        }
    }

    cartOnClick = ()=>{
        // console.log("cartOnClick!");
        addCart(1,this.props.info.bookId);//callback
        // history.push("/cart");
    }

    orderOnClick = ()=>{

    }

    render() {

        const {info} = this.props;

        if(info == null){
            return null;
        }

        return (
            <div className={"content"}>
                <div className={"book-detail"}>
                    <div className={"book-image"}><img alt="image" src={this.state.bookImage} style={{width:"350px", height:"350px"}}/></div>
                    <div className={"descriptions"}>
                        <Descriptions>
                            <Descriptions.Item className={"title"} span={3}>{info.name}</Descriptions.Item>
                            <Descriptions.Item label={"作者"} span={3}>{info.author}</Descriptions.Item>
                            <Descriptions.Item label={"分类"} span={3}>{info.type}</Descriptions.Item>
                            <Descriptions.Item label={"定价"} span={3}>{<span className={"price"}>{'¥' + info.price}</span>}</Descriptions.Item>
                            <Descriptions.Item label={"状态 "} span={3}>{info.inventory !== 0? <span>有货 <span className={"inventory"}>库存{info.inventory}件</span></span> : <span className={"status"}>无货</span>}</Descriptions.Item>
                            <Descriptions.Item label={"作品简介"} span={3}>{info.description}</Descriptions.Item>
                        </Descriptions>
                    </div>
                </div>
                <div className={"button-groups"}>
                    <Button type="danger" icon="shopping-cart" size={"large"} onClick={this.cartOnClick}>
                        加入购物车
                    </Button>

                    <Button type="danger" icon="pay-circle" size={"large"} style={{marginLeft:"15%"}}ghost onClick={this.orderOnClick}>
                        立即购买
                    </Button>
                </div>
            </div>

        )

    }

}
