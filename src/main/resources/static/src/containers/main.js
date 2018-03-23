import React, { Component } from 'react';

class Main extends Component {
    render() {
        return <div id="main">
        <h2>1. Select Data</h2>
        <div id="graph"></div>
        <button className="btn">Generate</button>
        <button className="btn">Add Data</button>
        </div>;
    }
}

export default Main;