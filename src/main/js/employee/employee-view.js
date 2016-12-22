import React from 'react';
import EmployeeSummaryRow from './employee-summary-row'
import "isomorphic-fetch"

var PanelGroup = require('react-bootstrap').PanelGroup;
var Panel = require('react-bootstrap').Panel;

export default class EmployeeView extends React.Component {

	constructor(){
		super();
		this.state = {employee: {revenuesForCurrentYear: [], aggregatesForCurrentYear: {}}}
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
			 	{this.state.employee.revenuesForCurrentYear.map((revenue) => {
			 		return <Panel eventKey={revenue.month} key={revenue.month} header={revenue.month.toLowerCase()}>
			 			<div className="montViewWrapper">
			 				<div>{revenue.allBillableHours + "h"}</div>
			 				<div>
			 				    <div>Revenue: {revenue.revenue}</div>
			 				    <div>Prognose: {revenue.prognosis}</div>
			 				    <div>Utilization: {revenue.utilization}</div>
			 			    </div>

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
