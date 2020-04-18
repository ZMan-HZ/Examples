const path = require("path")
const express = require('express');
const webpack = require('webpack');
const webpackDEVmiddleware = require("webpack-dev-middleware");
const webpackHotmiddleware = require("webpack-hot-middleware");
const webpackConfig = require('../config/webpack.prod.js');

const app = express();
const router = express.Router()
const DIST_PATH = path.join(__dirname, "../../src/main/webapp");
const PORT = 8000;
const complier = webpack(webpackConfig);
const contextRoot = process.argv[2];

app.all('*', function (req, res, next) {
  res.header("Access-Control-Allow-Origin", "*");
  res.header("Access-Control-Allow-Headers", "X-Requested-With");
  res.header("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
  res.header("X-Powered-By", ' 3.2.1');
  res.header("Content-Type", "application/json;charset=utf-8");
  next();
});
if (!contextRoot) {
  console.error('ERROR!')
  console.error('Context must be passed in as 2nd argument: node server.js <ContextRoot>')
  process.exit()
} else {
  console.log('context mapped to: ' + contextRoot)
}
let devMiddleware = webpackDEVmiddleware(complier, {
  publicPath: webpackConfig.out_put.publicPath,
  quiet: true
});
let hotMiddleware = webpackHotmiddleware(complier, {
  log: false,
  heartbeat: 2000
});
app.use(devMiddleware);
app.use(hotMiddleware);
//与下面注释功能一样
app.use(express.static(DIST_PATH))
/*
app.get('/json', function (req, res) {
  let jsonString = {
    user: 'guest',
    email: 'guest@zzm.com',
    date: '2019-08-18'
  }
  res.send(jsonString);
})*/
var server = app.listen(PORT, function () {
  var host = server.address().address
  var port = server.address().port
  console.log("应用实例，访问地址为 http://%s:%s", host, port)
});

