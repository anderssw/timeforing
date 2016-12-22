import React from 'react';
import EmployeeSummaryRow from './employee-summary-row'
var PanelGroup = require('react-bootstrap').PanelGroup;
var Panel = require('react-bootstrap').Panel;


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
			<div>
			<div className="mtHeader">MineTimer</div>
			<div className = "employeeView">
			    <EmployeeSummaryRow employee={this.state.employee}></EmployeeSummaryRow>
			 <PanelGroup defaultActiveKey="2" accordion>
			 	{this.state.employee.revenuesForCurrentYear.map((revenue, index) => {
			 		return <Panel eventKey={revenue.month} key={revenue.month} header={revenue.month}>
			 			<div>
			 				<div>Fakturerbare timer hos kunde: {revenue.customerBillableHours}</div>
			 				<div>Alle fakturerbare timer: {revenue.allBillableHours}</div>
			 				<div>Omsetning: {revenue.revenue}</div>
			 				<div>Prognose: {revenue.prognosis}</div>
			 				<div>Faktureringsgrad: {revenue.utilization}</div>
			 			</div>
			 		</Panel>
			 		})
               	}
			 </PanelGroup>

			</div>
			</div>
			)
	}
}
