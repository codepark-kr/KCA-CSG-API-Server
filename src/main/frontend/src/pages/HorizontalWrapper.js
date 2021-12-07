import React from 'react';
import '../assets/styles/horizontalWrapper.scss';
import TestComponent from "../components/TestComponent";

const HorizontalWrapper=()=>{
    return(
        <>
            <div className="horizontalScroll">
                <div className="item">
                    <div className="bg">
                        < TestComponent />
                    </div>
                </div>
                <div className="item">
                    <div className="bg2" ></div>
                </div>
                <div className="item">
                    <div className="bg"></div>
                </div>
                <div className="item">
                    <div className="bg2"></div>
                </div>
                <div className="item">
                    <div className="bg" ></div>
                </div>
                <div className="item">
                    <div className="bg2"></div>
                </div>
            </div>
        </>
    );    
}
export default HorizontalWrapper;