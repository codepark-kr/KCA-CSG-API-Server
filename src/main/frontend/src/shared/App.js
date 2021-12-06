import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import HorizontalWrapper  from '../pages/HorizontalWrapper';
import '../assets/scss/horizontalWrapper.scss';
import '../assets/scss/global.scss';

class App extends Component{
	constructor(props){
		super(props);
	}

	render(){
		return(
			<>
				<Switch>
					<Route path={"/"} component={ HorizontalWrapper } />
				</Switch>
			</>
		);
	}
}
export default App;