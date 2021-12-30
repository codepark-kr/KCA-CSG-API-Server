import React from 'react';
import '../assets/styles/horizontalWrapper.scss';
import TestComponent from "../components/TestComponent";
import Header from "../components/common/Header";

const HorizontalWrapper=()=>{
    return(
        <>
            <div className="horizontalScroll">
                <div className="item">
                    <div className="bg1">
                        < Header />
                        < TestComponent />
                        <div className="shortReports">
                            <div className="pearlTitle">Recent Short Reports</div>
                            <table>
                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr className="tags">
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>

                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>
                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>
                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>
                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>
                                <tr>
                                    <td className="shortReportsTitle">
                                        <span>Lorem Ipsum Neque Est.</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td className="shortReportsContents">
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem recusandae tenetur, voluptates?
                                        Lorem ipsum dolor sit amet, consectetur adipisicing elit. Nemo nihil nostrum odio temporibus tenetur? Commodi deserunt esse excepturi illo iure iusto modi nam non nulla qui quidem.
                                    </td>
                                </tr>
                                <tr>
                                    <td className="tags"><span>#Agile</span> <span>#Development Methodology</span> <span>#TDD</span></td>
                                </tr>
                                <tr>
                                    <td className="dates">2021.12.30.</td>
                                </tr>


                            </table>
                        </div>
                    </div>
                </div>
                <div className="item">
                    <div className="bg2" >

                    </div>
                </div>
                <div className="item">
                    <div className="bg"></div>
                </div>
                <div className="item">
                    <div className="bg2"></div>
                </div>
                <div className="item">
                    <div className="bg" ></div>
                </div>
                <div className="item">
                    <div className="bg2"></div>
                </div>
            </div>
        </>
    );    
}
export default HorizontalWrapper;