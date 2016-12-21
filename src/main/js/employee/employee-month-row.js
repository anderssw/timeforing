import React from 'react';

export default class EmployeeMonthRow extends React.Component{

	constructor(props){
		super(props);
	}

	render(){
		return (
			<div className="employeeMonthRow">
			<span>
			    {this.props.revenue.month.charAt(0) + this.props.revenue.month.toLowerCase().slice(1) + ":"}
			</span>
			<span>{" " + this.props.revenue.allBillableHours + " timer"}</span>
			</div>
			)
	}

}
