import * as React from "react/lib/React";
import * as ReactDOM from "react/lib/ReactDOM";


export default React.createClass({
    getDefaultProps : function () {
        return {
            source : 'https://api.github.com/users/octocat/gists'
        };
    },
    getInitialState: function() {
        return {
            username: '',
            lastGistUrl: ''
        };
    },

    componentDidMount: function() {

    },

    render: function() {
        return (
            <div>
                {this.state.username}'s last gist is
                <a href={this.state.lastGistUrl}>here</a>.
            </div>
        );
    }
});