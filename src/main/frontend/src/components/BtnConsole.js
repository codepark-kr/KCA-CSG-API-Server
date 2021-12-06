import React  from 'react';

const BtnConsole=()=>{
    return(
        <>
        <div className="outermost-btnConsole">
            <div className="nav-wrapper">
                <button className="btnConsole">Write New Post</button>
                <button className="btnConsole">Add New To-Do</button>
                <button className="btnConsole btn">Add New Schedule</button>
            </div>
        </div>
        </>
    );
};
export default BtnConsole;