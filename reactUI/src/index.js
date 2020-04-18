import React from 'react';
import ReactDOM from 'react-dom';
import GetRouter from './routers/GetRouter';

if (module.hot) {
    module.hot.accept();
    ReactDOM.render(
        GetRouter(), document.getElementById('app')
    );
}