import React, { Component } from 'react';

class Info extends Component {
    render() {
        return (
            <div id="detail">
            <h2 id="heading1">Create new project</h2>
            <div className="form-group row">
  <label className="col-2 col-form-label">Name</label>
  <div className="col-10">
    <input className="form-control" type="text" />
  </div>
  
</div>
<div className="form-group row">
  <label className="col-2 col-form-label">Tags</label>
  <div className="col-8">
    <input className="form-control" type="text" />
  </div>
  <div className="col-1">
  <button className="btn" id="add">Add</button>
  </div>
  
</div>
<button className="btn" id="create">Create</button>
            </div>
        );
    }
}

export default Info;