const React = require('react');
const ReactDOM = require('react-dom');
const client = require('./client');

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {users: []};
    }

    componentDidMount() {
        client({method: 'GET', path: '/api/users'}).done(response => {
            this.setState({users: response.entity._embedded.users});
        });
    }

    render() {
        return (
            <UserList users={this.state.users}/>
        )
    }
}

class UserList extends React.Component{
    render() {
        const users = this.props.users.map(user =>
            <User key={user._links.self.href} user={user}/>
        );
        return (
            <table>
                <tbody>
                <tr>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Phone Number</th>
                    <th>City</th>
                    <th>Street Address</th>
                    <th>Postal Code</th>
                </tr>
                {users}
                </tbody>
            </table>
        )
    }
}

class User extends React.Component{
    render() {
        return (
            <tr>
                <td>{this.props.user.name}</td>
                <td>{this.props.user.surname}</td>
                <td>{this.props.user.phoneNumber}</td>
                <td>{this.props.user.city}</td>
                <td>{this.props.user.streetAddress}</td>
                <td>{this.props.user.postalCode}</td>
            </tr>
        )
    }
}

ReactDOM.render(
    <App />,
    document.getElementById("react")
)