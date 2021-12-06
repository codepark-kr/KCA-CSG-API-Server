import React from 'react';

function Header(){
    const [ focus, setFocus ] = useState(0);

    useEffect(()=>{
    });

    return(
        <Fragment>
            <div className="outermost-header">
                <div className="nav-bar-wrapper">
                    <ul className="nav-bar">
                        <div className="li-wrapper">
                            <li><a href="#" className="first-nav">home</a></li>
                            <li><a href="#">about</a></li>
                            <li><a href="#">schedules</a></li>
                            <li><a href="#">posts</a></li>
                            <li><a href="#">slides</a></li>
                            <li><a href="#" className="last-nav">contact</a></li>
                        </div>
                    </ul>
                </div>
            </div>
        </Fragment>
    );
}
export default Header;