import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import { Route } from 'react-router-dom';
import App from "../shared/App";

const Root =( { store } )=> (
		<BrowserRouter>
				<Route path="/" component={ App } />
		</BrowserRouter>
);
export default Root;