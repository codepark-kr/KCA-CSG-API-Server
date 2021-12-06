import React from 'react';

const RecentPosts=()=>{
    return(
        <>
            <div className="outermost-recentPosts">
                <div className="inner-recentPosts">
                    <div className="title-set-wrapper">
                        <p className="medium-title">Lorem ipsum dolor, sit amet consectetur adipisicing elit.</p>
                        <div className="description">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Illo, distinctio. Debitis velit voluptatibus veritatis, eum eos dicta minus reiciendis eveniet voluptatem.
                        </div>
                    </div>
                    <table className="recentPosts-table">
                        <colGroup>
                            <col width={ 5+"%" }/>
                            <col width={ 80+"%" }/>
                            <col width={ 15+"%" }/>
                        </colGroup>
                        <tr>
                            <td colSpan="0.7">NO.</td>
                            <td>Title</td>
                            <td>DATE</td>
                        </tr>
                        <tr>
                            <td><a href="">IDX</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                        </tr>
                        <tr>
                            <td><a href="">lorem</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                        </tr>
                        <tr>
                            <td><a href="">lorem</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                        </tr>
                        <tr>
                            <td><a href="">lorem</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                        </tr>
                        <tr>
                            <td><a href="">lorem</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                            <td><a href="">lorem Ipsum</a></td>
                        </tr>
                    </table>
                </div>
            </div>
        </>
    );
}
export default RecentPosts;