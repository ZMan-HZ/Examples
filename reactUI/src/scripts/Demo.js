import React from 'react';

class Demo extends React.Component {

    constructor(props) { //构造函数
        super(props);
        this.state = {
            mytext: '',
        }
    }

    getData() { //请求数据函数
        fetch(`http://127.0.0.1:8000/json`, {
            method: 'GET'
            /* }).then(res => res.text()).then(*/
        }).then(res => res.json()).then(
            data => {
                this.setState({ mytext: data })
            }
        )
    }

    componentWillMount() {
        this.getData();
    }

    render() {

        return (
            <div>{this.state.mytext.user}<br />
                {this.state.mytext.email}<br />
                {this.state.mytext.date}</div>
        )
    }
}

export default Demo;
