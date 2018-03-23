import React from 'react';
import ReactDom from 'react-dom';
import Nav from './components/nav';
import Table from './components/table';
import Create from './components/create';
import Info from './components/info';
import Main from './containers/main';
const App = () => {
    return (
        <div>
            <Nav />
            <Info />
        </div>
    );
}

ReactDom.render(<App />, document.querySelector('.container-fluid'));