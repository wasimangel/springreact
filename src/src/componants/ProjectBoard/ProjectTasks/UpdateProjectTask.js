import React, { Component } from "react";
import { connect } from "react-redux";
import { Link } from "react-router-dom";
import classnames from "classnames";
import PropTypes from "prop-types";
import {
  getProjectTask,
  updateProjectTask
} from "../../../actions/backlogActions";

class UpdateProjectTask extends Component {
  constructor(props) {
    super(props);

    this.state = {
      id: "",
      projectSequence: "",
      summery: "",
      acceptanceCriteria: "",
      status: "",
      priority: 0,
      dueDate: null,
      projectIdentifier: "",
      errors: {}
    };
    this.onChange = this.onChange.bind(this);
    this.onSubmit = this.onSubmit.bind(this);
  }

  //   onchange
  onChange(e) {
    this.setState({ [e.target.name]: e.target.value });
  }

  //   onsubmit
  onSubmit(e) {
    e.preventDefault();
    const updatedProjectTask = {
      id: this.state.id,
      summery: this.state.summery,
      projectSequence: this.state.projectSequence,
      acceptanceCriteria: this.state.acceptanceCriteria,
      projectIdentifier: this.state.projectIdentifier,
      status: this.state.status,
      priority: this.state.priority,
      dueDate: this.state.dueDate
    };

    //console.log(updatedProjectTask);

    this.props.updateProjectTask(
      this.state.projectIdentifier,
      this.state.projectSequence,
      updatedProjectTask,
      this.props.history
    );
  }

  componentDidMount() {
    const { id, backlog_id } = this.props.match.params;
    this.props.getProjectTask(backlog_id, id, this.props.histroy);
  }

  componentWillReceiveProps(nextProps) {
    if (nextProps.errors) {
      this.setState({
        errors: nextProps.errors
      });
    }

    const {
      id,
      summery,
      projectSequence,
      acceptanceCriteria,
      status,
      projectIdentifier,
      priority,
      dueDate
    } = nextProps.project_task;

    this.setState({
      id,
      summery,
      projectSequence,
      acceptanceCriteria,
      status,
      projectIdentifier,
      priority,
      dueDate
    });
  }

  render() {
    // const { id } = this.props.match.params;
    const { backlog_id } = this.props.match.params;
    const { errors } = this.state;
    return (
      <div>
        <div className="add-PBI">
          <div className="container">
            <div className="row">
              <div className="col-md-8 m-auto">
                <Link
                  to={`/projectBoard/${backlog_id}`}
                  className="btn btn-light"
                >
                  Back to Project Board
                </Link>
                <h4 className="display-4 text-center">Update Project Task</h4>
                <p className="lead text-center">
                  {this.state.projectIdentifier} +{this.state.projectSequence}
                </p>
                <form onSubmit={this.onSubmit}>
                  <div className="form-group">
                    <input
                      type="text"
                      className={classnames("form-control form-control-lg ", {
                        "is-invalid": errors.summery
                      })}
                      name="summery"
                      placeholder="Enter Summery "
                      value={this.state.summery}
                      onChange={this.onChange.bind(this)}
                    />
                    {errors.summery && (
                      <div className="invalid-feedback">{errors.summery}</div>
                    )}
                  </div>
                  <div className="form-group">
                    <textarea
                      className="form-control form-control-lg"
                      placeholder="Acceptance Criteria"
                      name="acceptanceCriteria"
                      value={this.state.acceptanceCriteria}
                      onChange={this.onChange.bind(this)}
                    ></textarea>
                  </div>
                  <h6>Due Date</h6>
                  <div className="form-group">
                    <input
                      type="date"
                      className="form-control form-control-lg"
                      name="dueDate"
                      value={this.state.dueDate}
                      onChange={this.onChange.bind(this)}
                    />
                  </div>
                  <div className="form-group">
                    <select
                      className="form-control form-control-lg"
                      name="priority"
                      value={this.state.priority}
                      onChange={this.onChange.bind(this)}
                    >
                      <option value={0}>Select Priority</option>
                      <option value={1}>High</option>
                      <option value={2}>Medium</option>
                      <option value={3}>Low</option>
                    </select>
                  </div>

                  <div className="form-group">
                    <select
                      className="form-control form-control-lg"
                      name="status"
                      value={this.state.status}
                      onChange={this.onChange.bind(this)}
                    >
                      <option value="">Select Status</option>
                      <option value="TO_DO">TO DO</option>
                      <option value="IN_PROGRESS">IN PROGRESS</option>
                      <option value="DONE">DONE</option>
                    </select>
                  </div>

                  <input
                    type="submit"
                    className="btn btn-primary btn-block mt-4"
                  />
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    );
  }
}
const mapStateToProps = state => ({
  project_task: state.backlog.project_task,
  errors: state.errors
});

UpdateProjectTask.propTypes = {
  getProjectTask: PropTypes.func.isRequired,
  project_task: PropTypes.object.isRequired,
  updateProjectTask: PropTypes.func.isRequired,
  errors: PropTypes.object.isRequired
};

export default connect(mapStateToProps, { getProjectTask, updateProjectTask })(
  UpdateProjectTask
);
