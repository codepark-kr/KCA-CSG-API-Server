import React, { Component } from 'react';
import { Route, Switch } from 'react-router-dom';
import '../assets/scss/global.scss';
import MainConsole from '../pages/MainConsole';
import Section1 from '../pages/HorizontalWrapper';

class App extends Component{
    render(){
      return(
        <>
          <Switch>
            <Route exact path={ "/" } component={ Section1 } />
          </Switch>
        </>
      );
    }
}
export default App;