import React from 'react';
import EmployeeSummaryRow from './employee-summary-row'
import EmployeeMonthRow from './employee-month-row'

export default class EmployeeView extends React.Component {

	constructor(){
		super();
		this.state = {employee: {revenuesForCurrentYear: []}}
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
			<div className = "employeeView">
			    <EmployeeSummaryRow employee={this.state.employee}></EmployeeSummaryRow>
			    {this.state.employee.revenuesForCurrentYear.map(function(revenue){
                    return <EmployeeMonthRow key={revenue.month} revenue={revenue}/>
                })
                }
			</div>
			)
	}
}
