import React, { useState, useEffect } from "react";
import axios from 'axios';

const Header=()=>{
    const headerStyle={ fontFamily: 'DM Serif Display', color: 'var(--dusty-black)', fontSize: '2rem', padding: '14px',
                        fontWeight: 'bold', borderBottom: '1px solid var(--dusty-black)', marginBottom: '20px', fontStyle: 'italic',
                        border: '1px solid var(--dusty-black)' }
    const outermostStyle={
        backgroundColor: 'var(--dusty-gray)'
    }

    return(
        <div style={ outermostStyle }>
            <header style={ headerStyle } >º&nbsp;&nbsp;Lorem Ipsum Neque Est</header>
        </div>
    );
}
export default Header;