import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TestComponent=()=>{

    const outermostStyle={ border: '1px solid cream', width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-gray)' }
    const headerStyle={ fontFamily: 'DM Serif Display', color: 'var(--dusty-black)', fontSize: '2rem', padding: '14px',
        fontWeight: 'bold', border: '1px solid var(--dusty-black)', marginBottom: '20px', fontStyle: 'italic'  }

    return(
        <div style={ outermostStyle }>
            <header style={ headerStyle } >º&nbsp;&nbsp;Lorem Ipsum Neque Est</header>

        </div>
    );
}
export default TestComponent;