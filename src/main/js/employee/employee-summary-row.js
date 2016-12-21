import React from 'react';

export default class EmployeeSummaryRow extends React.Component{

	constructor(props){
		super(props);

	}

	render(){
		let employee = this.props.employee;
		return (
			<div className="employeeSummary">
				<div className="totalRevenue"> {"Faktureringsgrad: " + employee.utilizationTotal} </div>
				<div> {"Omsetning: " + employee.revenueTotal} </div>
				<div>
					<span>Fakturerbare timer: </span><span>{employee.billableHoursTotal}</span>
				</div>
			</div>
			)
	}

}
