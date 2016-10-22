import React from "react";
import NavLink from "./NavLink.jsx";
import { IndexLink } from 'react-router'

export default React.createClass({
    render() {
        return <div><h1>Hello, React Router!</h1>
            <ul role="nav">
                <li><NavLink  to="/" onlyActiveOnIndex={true}>Home</NavLink ></li>
                <li><NavLink to="/about" >About1</NavLink></li>
                <li><NavLink to="/repos" >Repos</NavLink></li>
                <li><NavLink to="/ajax" >ajax</NavLink></li>
            </ul>
            {this.props.children}
        </div>
    }
})