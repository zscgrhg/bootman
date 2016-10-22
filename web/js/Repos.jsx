import * as React from "react";
import NavLink from "./NavLink.jsx"
import { browserHistory } from 'react-router'
export default React.createClass({
    contextTypes: {
        router: React.PropTypes.object
    },
    handleSubmit(event) {
        event.preventDefault()
        const userName = event.target.elements[0].value
        const repo = event.target.elements[1].value
        const path = `/repos/${userName}/${repo}`
        this.context.router.push(path)
        console.log(path)
    },
    render() {
        return <div>Repos
            <ul>
                <li><NavLink to="/repos/reactjs/react-router">React Router</NavLink></li>
                <li><NavLink to="/repos/facebook/react">React</NavLink></li>
                <form onSubmit={this.handleSubmit}>
                    <input type="text" placeholder="userName"/> / {' '}
                    <input type="text" placeholder="repo"/>{' '}
                    <button type="submit">Go</button>
                </form>
            </ul>
            {this.props.children}
        </div>
    }
})