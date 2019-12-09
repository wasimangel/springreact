import axios from "axios";
import {
  GET_BACKLOG,
  GET_PROJECT_TASK,
  DELETE_PROJECT_TASK,
  GET_ERRORS
} from "./types";

export const addProjectTask = (
  backlog_id,
  projectTask,
  history
) => async dispatch => {
  try {
    await axios.post(`/api/backlog/${backlog_id}`, projectTask);
    history.push(`/projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    if (error.response.data.projectNotFound) {
      alert(error.response.data.projectNotFound);
    }
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getBacklog = (backlog_id, history) => async dispatch => {
  try {
    const response = await axios.get(`/api/backlog/${backlog_id}`);
    dispatch({
      type: GET_BACKLOG,
      payload: response.data
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};

export const getProjectTask = (
  backlog_id,
  pt_id,
  history
) => async dispatch => {
  try {
    const response = await axios.get(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: GET_PROJECT_TASK,
      payload: response.data
    });
  } catch (error) {
    history.push("/dashboard");
    // dispatch({
    //   type: GET_ERRORS,
    //   payload: error.response.data
    // });
  }
};

export const updateProjectTask = (
  backlog_id,
  pt_id,
  projectTask,
  history
) => async dispatch => {
  try {
    await axios.patch(`/api/backlog/${backlog_id}/${pt_id}`, projectTask);
    history.push(`/projectBoard/${backlog_id}`);
    dispatch({
      type: GET_ERRORS,
      payload: {}
    });
  } catch (error) {
    dispatch({
      type: GET_ERRORS,
      payload: error.response.data
    });
  }
};
export const deleteProjectTask = (backlog_id, pt_id) => async dispatch => {
  if (window.confirm("Are you sure you want to delete this project task")) {
    await axios.delete(`/api/backlog/${backlog_id}/${pt_id}`);
    dispatch({
      type: DELETE_PROJECT_TASK,
      payload: pt_id
    });
  }
};
