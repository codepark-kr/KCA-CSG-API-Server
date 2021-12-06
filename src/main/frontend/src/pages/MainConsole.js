import React from 'react';
import BtnConsole from '../components/BtnConsole';
import RecentPosts from '../components/RecentPosts';
import DailyTodos from '../components/DailyTodos';
import DailySchedules from '../components/DailySchedules';


const MainConsole=()=>{
    return(
        <>
            <div className="outermost-mainConsole">
                <div className="inner-mainConsole">
                    <BtnConsole />
                    <RecentPosts />
                    <DailyTodos />
                    <DailySchedules />
                </div>
            </div>
        </>    
    );
}
export default MainConsole;