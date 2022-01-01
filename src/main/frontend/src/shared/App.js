import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import HorizontalWrapper  from '../pages/HorizontalWrapper';
import SignIn from '../pages/SignIn';
import '../assets/styles/horizontalWrapper.scss';
import '../assets/styles/global.scss';

class App extends Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<>
				<Switch>
					<Route path={"/console"} component={ HorizontalWrapper } />
				</Switch>
			</>
		);
	}
}
export default App;