import React from 'react';
import '../assets/scss/horizontalWrapper.scss';
import TestComponent from "../component/TestComponent";

const HorizontalWrapper=()=>{
    return(
        <>
            <div class="horizontalScroll">
                <div class="item">
                    <div class="bg">
                        < TestComponent />
                    </div>
                </div>
                <div class="item">
                    <div class="bg2" ></div>
                </div>
                <div class="item">
                    <div class="bg"></div>
                </div>
                <div class="item">
                    <div class="bg2"></div>
                </div>
                <div class="item">
                    <div class="bg" ></div>
                </div>
                <div class="item">
                    <div class="bg2"></div>
                </div>
            </div>
        </>
    );    
}
export default HorizontalWrapper;