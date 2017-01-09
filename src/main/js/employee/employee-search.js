import React from 'react';


export default class EmployeeSearch extends React.Component {
    constructor(){
        super();
        this.state = {employeeNumber: "52000"}
        this.updateEmployeeNumber = this.updateEmployeeNumber.bind(this);
        this.buttonClicked = this.buttonClicked.bind(this);
    }

    updateEmployeeNumber(evt){
        if(evt.target.value.startsWith("52000")){
            this.setState({
                employeeNumber: evt.target.value
            });
        }

    }
    buttonClicked(evt){
        this.props.router.push('/employee/' + this.state.employeeNumber);
    }

	render(){
		return (
		    <div>
		        <div className="mtHeader"></div>
			    <div className="employeeSearchWrapper">
                    <form>
				        <input type="number" value={this.state.employeeNumber} className="employeeInput" onChange={this.updateEmployeeNumber} autoFocus/>
				        <button className="btn-primary" onClick={this.buttonClicked}>Hent timer</button>
                    </form>
                </div>
			</div>
			)
	}
}
