import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TestComponent=()=>{
    const [ systime, setSystime ] = useState('curr-systime');
    useEffect (()=>{ axios.get('/api/v1/now').then(( response )=>{ setSystime( response.data ) }) });

    const testWrapperStyle= { textAlign: 'center' }
    const testStyle= { margin: '20px', padding: '20px', border: '1px solid white', width: 'fit-content', fontSize: '1.2rem' };


    return(
        <div style={ testWrapperStyle }>
            <p style={ testStyle }>test ? <br/> { systime }</p>
        </div>
    );
}
export default TestComponent;