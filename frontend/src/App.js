import React from 'react';
import { BrowserRouter } from 'react-router-dom';
import  { Col, Row} from 'react-bootstrap';

import { SnackbarProvider } from 'notistack';
import Header from './components/Header';
import Sidebar from './components/SideBar';
import AllRoute from './allRoute';

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous" />

const App = () => (
    <BrowserRouter basename={process.env.REACT_APP_STAGE}>
      <SnackbarProvider maxSnack={3}>
          <Header/>
          <Row>
            <Col md={2}>
                <Sidebar/>
            </Col>
            <Col md={10}>
                <AllRoute/>
            </Col>
          </Row>
      </SnackbarProvider>
    </BrowserRouter>
);

export default App;
