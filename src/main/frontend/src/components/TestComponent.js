import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TestComponent=()=>{
    const outermostStyle={ width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-gray)' }
    const headerStyle={ fontFamily: 'DM Serif Display', color: 'var(--dusty-black)', fontSize: '2rem', padding: '14px',
        fontWeight: 'bold', borderBottom: '1px solid var(--dusty-black)', marginBottom: '20px', fontStyle: 'italic'  }

    const [curr, setCurr] = useState("Nothing to present");

    const loadResult =async()=>{
        axios({
            method: 'GET',
            url: 'localhost:3000'+ '/test',
            headers: {}
        }).then((data)=>{
            setCurr(data.data);
            console.log(curr)
        }).catch(
            setCurr("it failed")
        );
    }

    return(
        <div style={ outermostStyle }>
            <header style={ headerStyle } >º&nbsp;&nbsp;Lorem Ipsum Neque Est</header>
            { curr }
        </div>
    );
}
export default TestComponent;