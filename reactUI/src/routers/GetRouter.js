import React from 'react';
import { BroswerRouter as Router, Route, Link, hashHistory, Switch } from 'react-router-dom';
import Demo from '../pages/Demo';
import Demo2 from '../pages/Demo2';
import Demo3 from '../pages/Demo3';
import Login from '../pages/Login';

const GetRouter = () => (
    <Router history={hashHistory}>
        <div>
            <ul>
                <li><Link to="/demo">Demo</Link></li>
                <li><Link to="/demo2">Demo 2</Link></li>
                <li><Link to="/demo3">Demo 3</Link></li>
                <li><Link to="/login">Login</Link></li>
            </ul>
            <Switch>
                <Route exact path="/login" component={Login} />
                <Route exact path="/demo" component={Demo} />
                <Route exact path="/demo2" component={Demo2} />
                <Route exact path="/demo3" component={Demo3} />
            </Switch>
        </div>
    </Router>
)
export default GetRouter;