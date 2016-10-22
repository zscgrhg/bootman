@echo off
node node_modules\webpack-dev-server\bin\webpack-dev-server.js --config webpack.dev.config.js --inline --port 80%*
@echo on