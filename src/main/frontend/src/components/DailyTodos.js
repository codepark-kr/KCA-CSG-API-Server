import React from 'react';

const DailyTodos=()=>{
    return(
        <>
            <div className="outermost-dailyTodos">
                <div className="inner-dailyTodos">
                    <div className="title-set-wrapper">
                        <p className="medium-title center">HORIZONTAL MARQUEE ON HERE</p>
                    </div>

                    <table className="dailyTodos-table">
                        <colGroup>
                            <col width={ 5+"%" }/>
                            <col width={ 10+"%" }/>
                            <col width={ 55+"%" }/>
                            <col width={ 15+"%" }/>
                            <col width={ 15+"%" }/>
                        </colGroup>
                        <tr>
                            <td><input type="checkbox" name="Todo1" id="Todo1" /></td>
                            <td><a href="">priority</a></td>
                            <td><a href="">todo title on here</a></td>
                            <td><a href="">due date</a></td>
                            <td><a href="">spent time</a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="Todo1" id="Todo1" /></td>
                            <td><a href="">priority</a></td>
                            <td><a href="">todo title on here</a></td>
                            <td><a href="">due date</a></td>
                            <td><a href="">spent time</a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="Todo1" id="Todo1" /></td>
                            <td><a href="">priority</a></td>
                            <td><a href="">todo title on here</a></td>
                            <td><a href="">due date</a></td>
                            <td><a href="">spent time</a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="Todo1" id="Todo1" /></td>
                            <td><a href="">priority</a></td>
                            <td><a href="">todo title on here</a></td>
                            <td><a href="">due date</a></td>
                            <td><a href="">spent time</a></td>
                        </tr>
                        <tr>
                            <td><input type="checkbox" name="Todo1" id="Todo1" /></td>
                            <td><a href="">priority</a></td>
                            <td><a href="">todo title on here</a></td>
                            <td><a href="">due date</a></td>
                            <td><a href="">spent time</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </>
    );
}
export default DailyTodos;