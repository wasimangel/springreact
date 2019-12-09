import React, { Component } from "react";
import ProjectItem from "./Project/ProjectItem";
import CreateButton from "./Project/CreateButton";
import { connect } from "react-redux";
import { getProjetcs } from "../actions/ProjectActions";
import PropTypes from "prop-types";

class Dashboard extends Component {
  componentDidMount() {
    this.props.getProjetcs();
  }
  render() {
    const { projects } = this.props.project;
    return (
      <div className="projects">
        <div className="container">
          <div className="row">
            <div className="col-md-12">
              <h1 className="display-4 text-center">Projects</h1>
              <br />
              <CreateButton />
              <br />
              <hr />

              {projects.map(project => (
                <ProjectItem key={project.id} project={project} />
              ))}
            </div>
          </div>
        </div>
      </div>
    );
  }
}

Dashboard.propTypes = {
  project: PropTypes.object.isRequired,
  getProjetcs: PropTypes.func.isRequired
};

const mapStateToProps = state => ({
  project: state.project
});

export default connect(
  mapStateToProps,
  { getProjetcs }
)(Dashboard);
