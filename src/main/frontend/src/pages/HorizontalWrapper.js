import React, { useReact, useState, Fragment } from 'react';
import Sec1 from '../components/Section1';

const HorizontalWrapper=()=>{
    return(
        <>
            <div class="horizontalScroll">
                <div class="item">
                    <div class="item section1">
                        <Sec1 />
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