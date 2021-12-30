import React from 'react';

const TestComponent=()=>{
    const outermostStyle={ width: '96%', marginLeft: '2%', height: '96%', marginTop: '2%',
        backgroundColor: 'var(--dusty-black)' }
    const innerWrapper={ width: '100%', height: '96%', backgroundColor: 'var(--dusty-gray)' }

    return(
        <div style={ outermostStyle }>
            <div style={ innerWrapper }>
                <div className="recentTwins">
                    <p className="twinsTitle"> Recent Posts </p>
                    <table>
                        <tr>
                            <td className="twinsIndex">&nbsp;1.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>1 days ago</td>
                            <td className="wipTwins">(WIP)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;2.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;3.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>1 days ago</td>
                            <td className="wipTwins">(WIP)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;4.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;5.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;6.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;7.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;8.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>4 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;9.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>2 days ago</td>
                            <td>(Done)</td>
                        </tr>
                        <tr>
                            <td className="twinsIndex">&nbsp;10.</td>
                            <td>Agile Methodology</td>
                            <td>Lorem ipsum dolor sit amet, consectetur adipisicing elit.</td>
                            <td>2021.12.30</td>
                            <td>2 days ago</td>
                            <td>(Done)</td>
                        </tr>
                    </table>
                </div>
                <div className="recentBooks">
                    <p className="twinsTitle"> Book Reviews </p>
                </div>
                <div className="recentShorties">
                    <p className="twinsTitle"> Short Reports </p>
                </div>
                <div className="dailyLoop"></div>
            </div>
        </div>
    );
}
export default TestComponent;