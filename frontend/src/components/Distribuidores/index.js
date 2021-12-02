import { useEffect, useState } from 'react';
import {Table, Row, Button, Col, Modal, Form} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';
import './index.css';


const Distribuidores = () => {
    const { enqueueSnackbar } = useSnackbar();
    const [distribuidores, setDistribuidores] = useState([]);
    const [nomeDistribuidor, setNomeDistribuidor] = useState('');
    const [telefoneDistribuidor, setTelefoneDistribuidor] = useState(0);
    const [emailDistribuidor, setEmailDistribuidor] = useState('');
    const [registroDistribuidor, setRegistroDistribuidor] = useState('');
    const [showModalCadastro, setShowModalCadastro] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [idAtual, setIdAtual] = useState(0);
    const getAllDistribuidor = () => {
        api
            .get('/api/distribuidor')
            .then((res) => {
                setDistribuidores(res.data);
            })
            .catch((err) =>{
                enqueueSnackbar("Erro ao listar os distribuidores." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })}
            )
    }
    const cadastrarDistribuidor = () => {
        var distribuidor = {
            nome: nomeDistribuidor,
            telefone: telefoneDistribuidor,
            email: emailDistribuidor,
            registro: registroDistribuidor
        }
        api
            .post('/api/distribuidor', distribuidor).catch((err) =>{
                enqueueSnackbar("Erro ao salvar o distribuidor." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            })
    }
    const editarDistribuidor = (id) => {
        var distribuidor = {
            nome: nomeDistribuidor,
            telefone: telefoneDistribuidor,
            email: emailDistribuidor,
            registro: registroDistribuidor
        }
        api
            .put(`/api/distribuidor/${id}`, distribuidor).catch((err) =>{
                enqueueSnackbar("Erro ao editar o distribuidor." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            })
    }

    const deletar = (id) => {
        api
            .delete(`/api/distribuidor/${id}`).catch((err) =>{
                enqueueSnackbar("Erro ao editar o distribuidor." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            })
    }
    useEffect(() => {
        getAllDistribuidor();
    }, [enqueueSnackbar])

    return (
    <>
    <Row className="linha">
        <Col md={8}>
            <div className="title">Controle de distribuidor</div>
        </Col>
        <Col md={2}>
            <Button variant="success" onClick={() => {setShowModal(true); setShowModalCadastro(true)}}>+ Novo distribuidor</Button>
        </Col>
        <Col md={2}>
            <Button variant="primary" onClick={() => getAllDistribuidor()}>Atualizar</Button>
        </Col>
    </Row>

    <Table >
        <thead>
            <tr>
            <th>ID</th>
            <th>Nome do distribuidor</th>
            <th>Telefone</th>
            <th>Email</th>
            <th>Opções</th>
            </tr>
        </thead>
        <tbody>
                {distribuidores.length ? distribuidores.map((element) => 
                            <>
                            <tr>
                                <td>{element.id}</td>
                                <td>{element.nome}</td>
                                <td>{element.telefone}</td>
                                <td>{element.email}</td>
                                <td>{element.registro}</td>
                                <td> 
                                    <Row className="linha2">
                                        <Col md={4}>
                                            <Button variant="warning" onClick={() => {setShowModal(true); setIdAtual(element.id)}}>+ Editar</Button>
                                        </Col>
                                        <Col md={2}>
                                            <Button variant="danger"onClick={() => deletar(element.id)}>Excluir</Button>
                                        </Col>
                                    </Row>
                                </td>
                            </tr>
                            </>
                        ) : <p> Não existe nenhum distribuidor cadastrado. </p>}
        </tbody>
        {<Modal show={showModal} onHide={!showModal}>
            <Modal.Header>
                {showModalCadastro ?
                    <Modal.Title>Cadastrar distribuidor</Modal.Title>
                :   <Modal.Title>Editar distribuidor</Modal.Title>
                }
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3" controlId="nome">
                        <Form.Label>Nome do distribuidor</Form.Label>
                        <Form.Control type="text" placeholder="Nome do distribuidor" value={nomeDistribuidor} onChange={(e) => setNomeDistribuidor(e.target.value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="telefone">
                        <Form.Label>Telefone</Form.Label>
                        <Form.Control type="text" placeholder="Telefone" value={telefoneDistribuidor} onChange={(e) => setTelefoneDistribuidor(e.target.value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="email">
                        <Form.Label>Email</Form.Label>
                        <Form.Control type="text" placeholder="Email" value={emailDistribuidor} onChange={(e) => setEmailDistribuidor(e.target.value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="registro">
                        <Form.Label>Registro</Form.Label>
                        <Form.Control type="text" placeholder="Registro" value={registroDistribuidor} onChange={(e) => setRegistroDistribuidor(e.target.value)}/>
                    </Form.Group>
                    <Button variant="secondary" onClick={() => {setShowModalCadastro(false); setShowModal(false)}}>Cancelar</Button>
                    {showModalCadastro ?
                        <Button onClick={() => {cadastrarDistribuidor(); setShowModal(false); setShowModalCadastro(false)}} type="button" variant="primary" >Salvar distribuidor</Button>
                        :   <Button onClick={() => {editarDistribuidor(idAtual); setShowModal(false)}} type="button" variant="primary" >Salvar distribuidor</Button>
                    }    
                </Form>
            </Modal.Body>
        </Modal>
        }
    </Table>
    </>
    );
};
export default Distribuidores;