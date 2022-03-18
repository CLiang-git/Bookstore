import React from "react";
import {SearchBar} from "../components/SearchBar";
import {BookCarousel} from "../components/BookCarousel";
import {BookList} from "../components/BookList";
import {withRouter} from "react-router";
import {CartList} from "../components/CartList";
import {OrderList} from "../components/OrderList";

//订单界面
class OrderView extends React.Component{

    constructor(props) {
        super(props);
    }

    render(){
        return(
            <div className="cart-content">
                <OrderList />
                <div className={"foot-wrapper"}>
                </div>
            </div>
        );
    }


}

export default withRouter(OrderView);