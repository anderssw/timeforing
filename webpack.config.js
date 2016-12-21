var path = require('path');
var ExtractTextPlugin = require('extract-text-webpack-plugin');

module.exports = {
    entry: './src/main/js/app.js',
    devtool: 'sourcemaps',
    plugins: [
    new ExtractTextPlugin('[name].css'),
    ],
    cache: true,
    debug: true,
    output: {
        path: __dirname,
        filename: './src/main/resources/static/built/bundle.js'
    },
    module: {
        loaders: [
        {
         test: /\.scss$/,
         loaders: ['style','css','sass']
     },
     {
        test: /\.js$/,
        exclude: /(node_modules)/,
        loader: 'babel',
        query: {
            cacheDirectory: true,
            presets: ['es2015', 'react']
        }
    }


    ]
}
};
