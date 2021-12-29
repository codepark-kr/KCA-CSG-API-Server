import React, { useState, useEffect } from 'react';
import axios from 'axios';

const TestComponent=()=>{
    const outermostStyle={ width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-black)' }

    return(
        <div style={ outermostStyle }>
        </div>
    );
}
export default TestComponent;