import React, { Component } from 'react';
import Upload from './upload';
import URI from './uri';
class Create extends Component {
    render() {
        return (
            <div id="profile"><Upload />
            or
            <URI />
            <button className="btn" id="next">Next >></button>
            </div>
        );
    }
}

export default Create;