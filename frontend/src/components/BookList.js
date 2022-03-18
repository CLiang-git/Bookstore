import React from 'react';
import {List} from 'antd'
import {Book} from './Book'
import {getBooks} from "../services/bookService";

//主页里的书籍列表组件
export class BookList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {books:[]}; //主页中的图书列表
    }

    componentDidMount() {

        const callback =  (data) => {
            // console.log('callback data: ',data)
           this.setState({books:data});
           // console.log('books now: ',this.state.books);
        };

        getBooks({"search":null}, callback);

    }

    render() {
        return (
            <List
                grid={{gutter: 10, column: 4}}
                dataSource={this.state.books}
                pagination={{
                    onChange: page => {
                        console.log(page);
                    },
                    pageSize: 16,
                }}

                renderItem={item => (
                    <List.Item>
                        <Book info={item} />
                    </List.Item>
                )}
            />
        );
    }

}