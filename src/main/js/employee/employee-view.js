import React from 'react';
import EmployeeSummaryRow from './employee-summary-row'
import "isomorphic-fetch"
import Loader from 'react-loader';


var PanelGroup = require('react-bootstrap').PanelGroup;
var Panel = require('react-bootstrap').Panel;

export default class EmployeeView extends React.Component {

	constructor(){
		super();
		this.state = {employee: {revenuesForCurrentYear: [], aggregatesForCurrentYear: {}}, loaded: false};
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
			this.setState({employee: employee, loaded: true});
		}).catch((error) => {
            this.setState({employee: null, loaded: true})
        });
	}

	render(){
		return (
            <div>
            <Loader loaded={this.state.loaded}>

            {Boolean(this.state.employee) ?
                <div>
			    <div className="mtHeader"></div>
			    <div className = "employeeView">
			        <EmployeeSummaryRow employee={this.state.employee}></EmployeeSummaryRow>
                    <PanelGroup defaultActiveKey="2" accordion>
			 	        {this.state.employee.revenuesForCurrentYear.map((revenue) => {
			 		        return <Panel eventKey={revenue.month} key={revenue.month} header={revenue.month.toLowerCase()}>
			 			        <div className="montViewWrapper">
                                    <div>{revenue.allBillableHours + "h"}</div>
			 				        <div>
			 				            <div>Revenue: {revenue.revenue}kr</div>
			 				            <div>Prognosis: {revenue.prognosis}kr</div>
			 				            <div>Utilization: {revenue.utilization}%</div>
			 			            </div>
			 			        </div>
			 		        </Panel>
			 		        })
               	        }
			        </PanelGroup>
			    </div>
                </div>
            : <div>Kunne ikke hente data for anstattnummer: {this.props.params.id}</div>}
            </Loader>

            </div>

			)
	}
}
