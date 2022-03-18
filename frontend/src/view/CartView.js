import React from "react";
import {SearchBar} from "../components/SearchBar";
import {BookCarousel} from "../components/BookCarousel";
import {BookList} from "../components/BookList";
import {withRouter} from "react-router";
import {CartList} from "../components/CartList";

//购物车界面
class CartView extends React.Component{

    constructor(props) {
        super(props);
        // this.state = {cart:[]};
    }

    render(){
        return(
            <div className="cart-content">
                <CartList />
                <div className={"foot-wrapper"}>
                </div>
            </div>
        );
    }


}

export default withRouter(CartView);