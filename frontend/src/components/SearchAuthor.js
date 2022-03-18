import React from "react";
import Search from "antd/es/input/Search";
import {message} from "antd";
import {authorSearch} from "../services/searchService";

export class SearchAuthor extends React.Component {

    constructor(props) {
        super(props);
        this.state={author:""};
    }
    handleSearch = value => {
        console.log("handleSearch");
        const callback = (data) => {
            if(data.message===null){
                message.warn("bookName not found");
            }else {
                message.success(data.message);
            }
            this.setState({author:data.message})
        }
        authorSearch(value,callback);
    }
    render() {

        return(
            <div>
                输入书名，搜索作者：
                <Search
                    placeholder="input search text"
                    onSearch={value => this.handleSearch(value)}
                    style={{ width: 200 }}
                />
            </div>
        )
    }
}