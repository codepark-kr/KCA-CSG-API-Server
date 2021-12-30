import React from 'react';

const TestComponent=()=>{
    const outermostStyle={ width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-black)' }
    const innerWrapper={ width: '100%', height: '96%', backgroundColor: 'var(--dusty-gray)' }

    return(
        <div style={ outermostStyle }>
            <div style={ innerWrapper }>
                <div className="recentTwins">
                    <table>
                        <tr>
                            <td className="twinsIndex">&nbsp;1.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>(WIP)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;2.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;3.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>(WIP)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;4.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;5.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>(Done)</td>
                        </tr>
                    </table>
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