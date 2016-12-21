//const React = require('react');
import React from 'react';
import ReactDOM from 'react-dom';
import EmployeeSearch from './employee/employee-search'
//const ReactDOM = require('react-dom')
require('../scss/style.scss');

class App extends React.Component {

	render(){
		return (
			<EmployeeSearch></EmployeeSearch>
			)
	}
}

ReactDOM.render(
	<App/>,
	document.getElementById('react')
	)


