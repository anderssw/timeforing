import React from 'react';


export default class EmployeeSearch extends React.Component {
    constructor(){
        super();
        this.state = {employeeNumber: null}
        this.updateEmployeeNumber = this.updateEmployeeNumber.bind(this);
        this.buttonClicked = this.buttonClicked.bind(this);
    }

    updateEmployeeNumber(evt){
        this.setState({
            employeeNumber: evt.target.value
        });

        console.log(this.state);
    }
    buttonClicked(evt){
        console.log(this.state);
        debugger;
        this.props.router.push('/employee/' + this.state.employeeNumber);
    }

	render(){
		return (
			<div>
				<input type="text" placeholder="52000..." className="employeeInput" onChange={this.updateEmployeeNumber}/>
				<button className="btn-primary" onClick={this.buttonClicked}>Hent timer</button>
			</div>
			)
	}
}
