import React from "react";
import '../../assets/styles/global.scss';

const Header=()=>{
    const headerWrapper={ fontWeight: 'bold', borderBottom: '1px solid var(--dusty-black)', height: '70px', fontStyle: 'italic',
                           }
    const outermostWrapper={ backgroundColor: 'var(--dusty-gray)' }
    const headerLogo={ marginTop: '10px',  fontFamily: 'DM Serif Display', color: 'var(--dusty-black)',
                       fontSize: '2rem', width: 'fit-content', marginLeft: '14px', display: 'inline-block' }
    const navbar={ height: '80px', width: '400px', right: '0%', position: 'absolute',
        display: 'inline-block' }

    return(
        <div style={ outermostWrapper }>
            <header style={ headerWrapper } >
                <p style={ headerLogo }>º&nbsp; KCA-CSG ─ &nbsp;SUMMARY OF TODAY'S</p>
                <div style={ navbar }>
                    <div className="buttonsWrapper">
                        <div className='writeButton'></div>
                    </div>
                    <div className="buttonsWrapper2">
                        <div className='writeButton2'></div>
                    </div>
                </div>
            </header>
        </div>
    );
}
export default Header;