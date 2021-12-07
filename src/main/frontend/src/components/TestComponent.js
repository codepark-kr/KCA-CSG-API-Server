import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TestComponent=()=>{
    const [ systime, setSystime ] = useState('curr-systime');
    useEffect (()=>{ axios.get('/testee/all').then(( response )=>{ setSystime( response.data ) }) });

    const loadAllPosts =async()=>{
        axios({
            method: 'GET',
            url: '/testee/all'
            // ,
            // headers: {'Authorization': 'Bearer '+JSON.parse(localStorage.getItem('token'))}
        }).then((data)=>{
            setSystime(data.data);
        }).catch();
    }


    const testWrapperStyle= { margin: '20px', padding: '20px', border: '1px solid white', width: 'fit-content', fontSize: '1.2rem', textAlign: 'center' };

    return(
        <div style={ testWrapperStyle }>
            <h2>test</h2>
            <p>{ systime }</p>
        </div>
    );
}
export default TestComponent;