import React from 'react';

export default class EmployeeSearch extends React.Component {
	render(){
		return (
			<div>
				<input type="text" placeholder="52000..." className="employeeInput" />
				<button className="btn-primary">Hent timer</button>
			</div>
			)
	}
}
