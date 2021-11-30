import { Navbar, Container, Button} from "react-bootstrap";

import 'bootstrap/dist/css/bootstrap.min.css';
import './index.css';
import { Link } from "react-router-dom";

export default function Nave() {


    return (
        <Navbar className="Nav"  variant="dark">
            <Container>
                <Navbar.Brand className="TitleNav">Seja bem vindo(a)</Navbar.Brand>
                <Link to = "/login"><Button className="GetOutButton" variant="danger">Sair</Button></Link>
            </Container>
        </Navbar>
    );
}