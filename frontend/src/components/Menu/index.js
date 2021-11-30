import Card from 'react-bootstrap/Card';
import {Row,Col} from 'react-bootstrap';
import Button from 'react-bootstrap/Button';
import {BsFiles} from 'react-icons/bs';
import {Link} from 'react-router-dom';
import './index.css';
export default function Menu(){
    return (
        <div className="geral">
            <Row>
                <Card className="card">
                <Card.Body>
                    <Row className = "linha">
                        <Col md={6}>
                            <Card.Title className="titulo">Estoque</Card.Title>
                            <Card.Text>
                                Gestão de todos os <br/>
                                produtos da loja
                            </Card.Text>
                        </Col>
                        <Col md={6}>
                            <BsFiles size ={80}/>
                        </Col>
                    </Row>
                    <Row className = "botao">
                        <Link to = "/estoque"><Button className="corBotao" on>Saiba mais</Button></Link>
                    </Row>
                </Card.Body>
                </Card>
                <Card className="card">
                <Card.Body>
                    <Row className = "linha">
                        <Col md={6}>
                            <Card.Title className="titulo">Caixa</Card.Title>
                            <Card.Text>
                                Gestão de todo o <br/>
                                caixa da loja
                            </Card.Text>
                        </Col>
                        <Col md={6}>
                            <BsFiles size ={80}/>
                        </Col>
                    </Row>
                    <Row className = "botao">
                       <Link to = "/caixa"><Button className="corBotao" on>Saiba mais</Button></Link>
                    </Row>
                </Card.Body>
                </Card>
                <Card className="card">
                <Card.Body>
                    <Row className = "linha">
                        <Col md={6}>
                            <Card.Title className="titulo">Transação</Card.Title>
                            <Card.Text>
                                Gestão de todos as <br/>
                                transações da loja
                            </Card.Text>
                        </Col>
                        <Col md={6}>
                            <BsFiles size ={80}/>
                        </Col>
                    </Row>
                    <Row className = "botao">
                        <Link to = "/transacao"><Button className="corBotao" on>Saiba mais</Button></Link>
                    </Row>
                </Card.Body>
                </Card>
            </Row>
            <Row>
                <Card className="card">
                <Card.Body>
                    <Row className = "linha">
                        <Col md={6}>
                            <Card.Title className="titulo">Distribuidor</Card.Title>
                            <Card.Text>
                                Gestão de todos os <br/>
                                distribuidores da loja
                            </Card.Text>
                        </Col>
                        <Col md={6}>
                            <BsFiles size ={80}/>
                        </Col>
                    </Row>
                    <Row className = "botao">
                        <Link to = "/distribuidor"><Button className="corBotao" on>Saiba mais</Button></Link>
                    </Row>
                </Card.Body>
                </Card>
            </Row>   
        </div>
    );
}