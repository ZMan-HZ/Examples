import React from 'react';
import logo from '../images/logo.svg';
import '../css/Login.css';
import { Segment, Input, Button, Table, TableBody, TableRow, TableCell } from 'semantic-ui-react';

class Login extends React.Component {

  constructor(props) {
    super(props);
    this.state = {
      user: "",
      password: "",
    }
    this._inputChange = this._inputChange.bind(this);
    this._submit = this._submit.bind(this);
  }

  _inputChange(e) {
    this.setState(
      {
        [e.target.name]: e.target.value,
      }
    );
  }

  _submit() {
    console.log(this.state.user);
    console.log(this.state.password);
  }

  render() {
    return (
      <div id="logo" className="App-header" >
        <img src={logo} className="App-logo" alt="aaa" />
        <Segment style={{ textAlign: 'center', width: '360px', background: '#282c34' }}>
          <Table style={{ width: "100%", textAlign: "center", background: '#282c34' }}>
            <TableBody style={{ width: "100%" }}>
              <TableRow>
                <TableCell colSpan="2">
                  <Input id="user" type="text" name="user" value={this.state.user} placeholder="用户名/手机号/邮箱" style={{ width: '95%' }} onChange={this._inputChange} /><br />
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell colSpan="2">
                  <Input id="password" type="password" name="password" value={this.state.password} placeholder="密码" style={{ width: '95%' }} onChange={this._inputChange} /><br />
                </TableCell>
              </TableRow>
              <TableRow>
                <TableCell >
                  <Button primary content="登录" style={{ width: '60%' }} onClick={this._submit} />
                </TableCell>
                <TableCell >
                  <Button primary content="注册" style={{ width: '60%' }} />
                </TableCell>
              </TableRow>
            </TableBody>
          </Table>
        </Segment>
      </div>
    )
  }
}
export default Login;