import React, { useReact, useState, Fragment } from 'react';
import Clock from 'react-live-clock';
import CountUp from 'react-countup';
import DailySchedules from './DailySchedules';

const Section1=()=>{

    return(
        <>
            <div className="outermost-wrapper">
                <div className="inner-wrapper">
                    <div className="section1-main">
                        <p className="ticking"> <Clock format={ 'YYYY MM DD HH:mm:ss' } ticking={ true } timezone={ 'US/Pacific' } /> </p>
                        <span className="youvegot">you've&nbsp;</span>
                        <span className="todos">
                            <span className="todos-content todos-doing">
                                <CountUp start={ 0 } end={ 7 } duration={ 0.98 } />&nbsp;
                            </span>
                            things to do<br/>
                        </span>
                        <span className="todos">
                            also 've finished&nbsp;
                            <span className="todos-content todos-done">4&nbsp;</span>
                            things <br/>to do.
                        </span>
                    <DailySchedules />
                    </div>
                </div>
            </div>
        </>
    );    
}
export default Section1;