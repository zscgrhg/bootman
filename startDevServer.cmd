@echo off
%~dp0endorsed/node-v6.9.1-windows-x64/bin/node node_modules\webpack-dev-server\bin\webpack-dev-server.js --config webpack.dev.config.js --inline --port 80%*
@echo on