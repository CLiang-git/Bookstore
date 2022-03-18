import React from "react";
import { Table, Badge, Menu, Dropdown, Icon } from 'antd';
import {getBooks} from "../services/bookService";
import {Book} from "./Book";
import {getCart} from "../services/cartService";
import {CartItem} from "./CartItem";
import {getOrderBook, getOrderBooks, getOrders} from "../services/orderService";

//购物车组件
export class OrderList extends React.Component{

    constructor(props) {
        super(props);
        this.state = {orders:[],books:[]};
    }

    componentDidMount() {
        const callback =  (data) => {
            // console.log('Received values of orders: ', data);
            this.setState({orders:data});
            console.log('orders now: ',this.state.orders);
            console.log('orders[0] now: ',this.state.orders[0]);
            console.log('orders[0].orders now: ',this.state.orders[0].orders);
            this.getBooks();
        };
        getOrders(1, callback);



    }

    getBooks(){
        // let index=0;
        const callback=(data)=>{
            this.setState({books:data});
            console.log('books now: ',this.state.books);
        };
        let ids=[];
        // console.log('ids before: ',ids);
        for(let i=0;i<this.state.orders.length;i++){
            for (let j=0;j<this.state.orders[i].orders.length;j++){
                // getOrderBook(this.state.orders[i].orders[j].id,callback);
                ids.push(this.state.orders[i].orders[j].id);
            }
        }
        console.log('ids now: ',ids);
        getOrderBooks(ids,callback);
    }
    deleteOnClick = ()=>{
        // let i=this.state.orders[2].orders
    }


    render() {
        const expandedRowRender = (record) => {
            console.log('record now: ',record);
            let index=record.key;
            const columns = [
                // { title: 'Date', dataIndex: 'date', key: 'date' },
                // { title: 'Name', dataIndex: 'name', key: 'name' },
                {title: 'Id',dataIndex:'id',key:'id'},
                {title: 'BookName',dataIndex:'bookName',key:'bookName'},
                {title: 'Author',dataIndex:'author',key:'author'},
                {title: 'BookType',dataIndex:'bookType',key:'bookType'},
                {title: 'Quantity',dataIndex:'quantity',key:'quantity'},
                {title: 'UnitPrice',dataIndex: 'unitPrice',key:'unitPrice'},

                {
                    title: 'Status',
                    key: 'state',
                    render: () => (
                        <span>
            <Badge status="success" />
            Finished
          </span>
                    ),
                },
                // { title: 'Upgrade Status', dataIndex: 'upgradeNum', key: 'upgradeNum' },
          //       {
          //           title: 'Action',
          //           dataIndex: 'operation',
          //           key: 'operation',
          //           render: () => (
          //               <span className="table-operation">
          //   <a>Pause</a>
          //   <a>Stop</a>
          //   <Dropdown overlay={Menu}>
          //     <a>
          //       More <Icon type="down" />
          //     </a>
          //   </Dropdown>
          // </span>
          //           ),
          //       },
            ];

            const data = [];
            for (let i = 0; i < this.state.orders[index].orders.length; ++i) {

                data.push({
                    key: i,
                    id:this.state.orders[index].orders[i].id,
                    bookName:this.state.books[record.bookIndex+i].name,
                    author:this.state.books[record.bookIndex+i].author,
                    bookType:this.state.books[record.bookIndex+i].type,
                    quantity:"×"+this.state.orders[index].orders[i].purchaseNumber,
                    unitPrice:"￥"+this.state.orders[index].orders[i].purchasePrice,
                    // date: '2014-12-24 23:12:00',
                    // name: 'This is production name',
                    // upgradeNum: 'Upgraded: 56',
                });
            }
            return <Table columns={columns} dataSource={data} pagination={false} />;
        };

        const columns = [
            { title: 'OrderId', dataIndex: 'orderId', key: 'orderId' },
            { title: 'UserId', dataIndex: 'userId', key: 'userId' },
            { title: 'Address', dataIndex: 'address', key: 'address' },
            { title: 'Receiver', dataIndex: 'receiver', key: 'receiver' },
            { title: 'Tel', dataIndex: 'tel', key: 'tel' },
            { title: 'Quantity', dataIndex: 'quantity', key: 'quantity' },
            { title: 'TotalPrice', dataIndex: 'totalPrice', key: 'totalPrice' },
            { title: 'Time', dataIndex: 'time', key: 'time' },
            // { title: 'Version', dataIndex: 'version', key: 'version' },
            // { title: 'Upgraded', dataIndex: 'upgradeNum', key: 'upgradeNum' },
            // { title: 'Creator', dataIndex: 'creator', key: 'creator' },
            // { title: 'Date', dataIndex: 'createdAt', key: 'createdAt' },
            // { title: 'Action', key: 'operation', render: () => <a>Publish</a> },
        ];

        const data = [];
        let bookIndex=0;
        let quantity=0;
        let price=0;
        for (let i = 0; i < this.state.orders.length; ++i) {
            quantity=0;
            price=0;
            for (let j=0;j<this.state.orders[i].orders.length;++j){
                quantity+=this.state.orders[i].orders[j].purchaseNumber;
                price+=this.state.orders[i].orders[j].purchasePrice*this.state.orders[i].orders[j].purchaseNumber;
            }
            data.push({
                key: i,
                bookIndex:bookIndex,
                orderId: this.state.orders[i].orderId,
                userId: this.state.orders[i].userId,
                address:this.state.orders[i].orderAddress,
                receiver:this.state.orders[i].orderReceiver,
                tel:this.state.orders[i].orderTel,
                quantity:"×"+quantity,
                totalPrice:"￥"+price,
                time:this.state.orders[i].orderTime,
                // version: '10.3.4.5654',
                // upgradeNum: 500,
                // creator: 'Jack',
                // createdAt: '2014-12-24 23:12:00',
            });
            bookIndex+=this.state.orders[i].orders.length;
        }

        return (
            <Table
                className="components-table-demo-nested"
                columns={columns}
                expandedRowRender={expandedRowRender}
                dataSource={data}
            />
        );
        // return(
        //     <List
        //         itemLayout="horizontal"
        //         dataSource={this.state.orders}
        //         renderItem={item => (
        //             <List.Item actions={[<a key="list-loadmore-edit">edit</a>, <a key="list-loadmore-more" onClick={this.deleteOnClick}>删除</a>]}>
        //                 {/*<List.Item.Meta*/}
        //                 {/*    avatar={<Avatar src="https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png" />}*/}
        //                 {/*    title={<a href="https://ant.design">{item.title}</a>}*/}
        //                 {/*    description="Ant Design, a design language for background applications, is refined by Ant UED Team"*/}
        //                 {/*/>*/}
        //                 <OrderItem info={item} />
        //             </List.Item>
        //         )}
        //     />
        // )
    }
}