import React from 'react';
import { BrowserRouter, Route } from 'react-router-dom';
import App from '../shared/App';

const Root =()=> (
	<BrowserRouter>
		<Route path="/" component={ App } />
	</BrowserRouter>
);
export default Root;