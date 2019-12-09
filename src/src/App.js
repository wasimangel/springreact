import React from "react";
// import logo from "./logo.svg";
import "./App.css";
import Dashboard from "./componants/Dashboard";
import Header from "./componants/Project/Header";
import "bootstrap/dist/css/bootstrap.min.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import AddProject from "./componants/Project/AddProject";

import { Provider } from "react-redux";
import store from "./store";
import UpdateProject from "./componants/Project/UpdateProject";
import ProjectBoard from "./componants/ProjectBoard/ProjectBoard";
import AddProjectTask from "./componants/ProjectBoard/ProjectTasks/AddProjectTask";
import UpdateProjectTask from "./componants/ProjectBoard/ProjectTasks/UpdateProjectTask";

function App() {
  return (
    <Provider store={store}>
      <Router>
        <div className="App">
          <Header />

          <Route exact path="/dashboard" component={Dashboard} />
          <Route exact path="/addProject" component={AddProject} />
          <Route exact path="/updateProject/:id" component={UpdateProject} />
          <Route exact path="/projectBoard/:id" component={ProjectBoard} />
          <Route exact path="/addProjectTask/:id" component={AddProjectTask} />
          <Route
            exact
            path="/updateProjectTask/:backlog_id/:id"
            component={UpdateProjectTask}
          />
        </div>
      </Router>
    </Provider>
  );
}

export default App;
