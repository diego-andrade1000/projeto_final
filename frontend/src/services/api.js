import axios from 'axios';

export default axios.create({
  baseURL: process.env.REACT_APP_STAGE || 'http://localhost:8080',
});
