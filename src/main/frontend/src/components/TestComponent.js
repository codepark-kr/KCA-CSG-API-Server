import React from 'react';

const TestComponent=()=>{
    const outermostStyle={ width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-black)' }
    const innerWrapper={ width: '100%', height: '96%', backgroundColor: 'var(--dusty-gray)' }

    return(
        <div style={ outermostStyle }>
            <div style={ innerWrapper }>
                <div className="recentTwins">
                </div>
                <div className="recentBooks">
                </div>
                <div className="recentShorties">
                </div>
            </div>
        </div>
    );
}
export default TestComponent;