import React, { Component } from 'react';
import Searchbar from './search-bar';
class Table extends Component {
    render() {
        return <div><table className="table" id="listing">
        <thead>
            <tr>
                <th>ID</th>
                <th>Project Name</th>
                <th>Date</th>
                <th>Size</th>
                <th>Created by</th>
                <th>Last used</th>
                <th></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td>1</td>
                    <td>Project X</td>
                    <td>03/02/2017</td>
                    <td>50</td>
                    <td>QA Tester 1</td>
                    <td>04/05/2018</td>
                    <td><a href="#">View Detail</a></td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>Project X</td>
                    <td>03/02/2017</td>
                    <td>50</td>
                    <td>QA Tester 1</td>
                    <td>04/05/2018</td>
                    <td><a href="#">View Detail</a></td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>Project X</td>
                    <td>03/02/2017</td>
                    <td>50</td>
                    <td>QA Tester 1</td>
                    <td>04/05/2018</td>
                    <td><a href="#">View Detail</a></td>
                </tr>
                <tr>
                    <td>4</td>
                    <td>Project X</td>
                    <td>03/02/2017</td>
                    <td>50</td>
                    <td>QA Tester 1</td>
                    <td>04/05/2018</td>
                    <td><a href="#">View Detail</a></td>
                </tr>
                </tbody>
            </table></div>;
    }
}

export default Table;