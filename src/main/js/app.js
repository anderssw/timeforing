import React from 'react';
import ReactDOM from 'react-dom';
import { Router, Route, IndexRoute, Link, hashHistory } from 'react-router'
import EmployeeSearch from './employee/employee-search'
import EmployeeView from './employee/employee-view'
require('../scss/style.scss');

export default class App extends React.Component {

	render(){
		return (
			<div>
				{this.props.children}
			</div>
			)
	}
}

ReactDOM.render((
  <Router history={hashHistory}>
      <Route path="/" component={App}>
        <IndexRoute component={EmployeeSearch} />
        <Route path="employee/:id" component={EmployeeView} />
      </Route>
    </Router>
), document.getElementById("react"));


