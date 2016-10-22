const  outputPath="/content";

var path = require('path');
var webpack = require('webpack');
var HtmlWebpackPlugin = require('html-webpack-plugin');
var ExtractTextPlugin = require('extract-text-webpack-plugin');
var extractCSS = new ExtractTextPlugin('stylesheets/[name]-[id]-[hash].css');


module.exports = {
    entry: ['whatwg-fetch','./web/js/index.jsx'],
    devtool: 'eval',
    cache: true,
    debug: true,
    output: {
        path: path.join(__dirname, './build/resources/main/static/',outputPath),
        filename: '[name]-[id]-[hash].js'
    },
    module: {
        preLoaders: [

        ],
        loaders: [
            {
                test: /\.html$/,
                loader: 'html'
            },
            {
                test: /\.jsx?$/,
                exclude: /(node_modules)/,
                loader: 'babel-loader?presets[]=es2015&presets[]=react'
            }, {
                test: /\.json/,
                loaders: ['json-loader']
            }, {
                test: /\.css$/,
                loader: ExtractTextPlugin.extract("style-loader", "css-loader", "postcss-loader")
            }
        ]
    },

    postcss: function () {
        return [require('autoprefixer')];
    },
    plugins: [
        extractCSS,
        new HtmlWebpackPlugin({
            template: path.join(__dirname, "./web/index.tmpl.html")
        }),
        new webpack.optimize.DedupePlugin(),
        new webpack.optimize.OccurenceOrderPlugin(),
        new webpack.optimize.UglifyJsPlugin()
    ],
    devServer: {
        proxy: {
            '/api': {
                target: 'http://127.0.0.1:8080',
                changeOrigin: true,
                secure: false
            }
        }
    }
};