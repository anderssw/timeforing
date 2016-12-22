import React from 'react';

export default class EmployeeSummaryRow extends React.Component{

	constructor(props){
		super(props);
	}

	render(){
		let employee = this.props.employee;
		return (
			<div className="employeeSummary">
				<div className="totalHours">{this.props.employee.aggregatesForCurrentYear.allBillableHoursYear + "h"}</div>
				<div className="totalRevenue">{"Utilization: " + this.props.employee.aggregatesForCurrentYear.averageUtilization + "%"} </div>
				<div> {"Revenue: " + this.props.employee.aggregatesForCurrentYear.totalRevenue + "kr"} </div>
			</div>
			)
	}

}
