import React from 'react';

export default class EmployeeView extends React.Component {

	constructor(){
		super();
		this.state = {employee: null}
		this.getEmployeeData = this.getEmployeeData.bind(this);
	}

	componentDidMount(){
		const id = this.props.params.id;
		this.getEmployeeData(id);
	}

	getEmployeeData(id){
		return fetch('employees/' + id)
		.then((response) => response.json())
		.then((employee) => {
			this.setState({employee: employee});
		});
	}

	render(){
		return (
			<div>
				{this.state.employee ?  this.state.employee.billableHoursTotal : "test"}
				<input type="text" placeholder="52100..." className="employeeInput" />
				<button className="btn-primary">Hent timer</button>
			</div>
			)
	}
}
