import React from 'react';
import {Layout, Carousel, Statistic} from 'antd'
import '../css/home.css'
import {withRouter} from "react-router-dom";
import {BookCarousel} from "../components/BookCarousel";
import {SearchBar} from "../components/SearchBar";
import {BookList} from "../components/BookList";
import {visit} from "../services/visitService";
import {SearchAuthor} from "../components/SearchAuthor";

const { Header, Content, Footer } = Layout;

//Books界面
class HomeView extends React.Component{

    constructor(props) {
        super(props);
        this.state = {visitNum:""};
    }

    componentDidMount(){
        console.log("did mount");
        visit((data) => {
            this.setState({visitNum: data});
            // console.log('visitNum now: ',this.state.visitNum);
        });

    }

    render(){
        return(
            <div className="home-content">
                <p></p>
                <Statistic title="访问量：" value={this.state.visitNum} />
                <SearchAuthor/>
                <SearchBar />
                <BookCarousel />
                <BookList />
                <div className={"foot-wrapper"}>
                </div>
            </div>
        );
    }
}

export default withRouter(HomeView);