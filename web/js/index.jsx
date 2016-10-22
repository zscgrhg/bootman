import   '../css/app.css';
import { Router, Route,hashHistory , IndexRoute} from 'react-router';
import React from 'react'
import { render } from 'react-dom'
import App from './App.jsx'
import About from './About.jsx'
import Repos from './Repos.jsx'
import Repo from './Repo.jsx'
import Home from './Home.jsx'
import UserGist from './UserGist.jsx'

render((
    <Router history={hashHistory}>
        <Route path="/" component={App}>
            <IndexRoute component={Home}/>
            <Route path="/repos"  component={Repos}>
                <Route path="/repos/:userName/:repoName" component={Repo}/>
            </Route>
            <Route path="/about" component={About}/>
            <Route path="/ajax" component={UserGist}/>
        </Route>
    </Router>
), document.getElementById('main'))