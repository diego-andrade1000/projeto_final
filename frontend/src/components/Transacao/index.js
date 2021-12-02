import { useEffect, useState } from 'react';
import {Table, Row, Button, Col, Modal, Form} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';
import './index.css';


const Transacao = () => {
    const { enqueueSnackbar } = useSnackbar();
    const [transacoes, setTransacoes] = useState([]);
    const [compraOuVendaTransacao, setCompraOuVendaTransacao] = useState(false);
    const [nomeProdutoTransacao, setNomeProdutoTransacao] = useState('');
    const [nomeDistribuidorTransacao, setNomeDistribuidorTransacao] = useState('');
    const [valorTransacao, setValorTransacao] = useState('');
    const [showModalCadastro, setShowModalCadastro] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [idAtual, setIdAtual] = useState(0);
    const getAllTransacoes = () => {
        api
            .get('/api/transacao')
            .then((res) => {
                console.log(res.data);
                setTransacoes(res.data);
                console.log(res);
                console.log(transacoes);
            })
            .catch((err) =>{
                enqueueSnackbar("Erro ao listar as transações." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })}
            )
    }
    const cadastrarTransacao = () => {
        console.log(compraOuVendaTransacao);
        var transacao = {
            compraOuVenda: compraOuVendaTransacao,
            nomeDoProduto: nomeProdutoTransacao,
            nomeDoDistribuidor: nomeDistribuidorTransacao,
            valor: valorTransacao
        }
        api
            .post('/api/transacao', transacao).catch((err) =>{
                enqueueSnackbar("Erro ao salvar a transação." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            })
    }
    const editarTransacao = (id) => {
        var transacao = {
            compraOuVenda: compraOuVendaTransacao,
            nomeDoProduto: nomeProdutoTransacao,
            nomeDoDistribuidor: nomeDistribuidorTransacao,
            valor: valorTransacao
        }
        api
            .put(`/api/transacao/${id}`, transacao).catch((err) =>{
                enqueueSnackbar("Erro ao editar a transação." || err.message, {
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
            .delete(`/api/transacao/${id}`).catch((err) =>{
                enqueueSnackbar("Erro ao deletar a transação." || err.message, {
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
        getAllTransacoes();
    }, [enqueueSnackbar])

    return (
    <>
    <Row className="linha">
        <Col md={8}>
            <div className="title">Controle de transação</div>
        </Col>
        <Col md={2}>
            <Button variant="success" onClick={() => {setShowModal(true); setShowModalCadastro(true)}}>+ Novo transação</Button>
        </Col>
        <Col md={2}>
            <Button variant="primary" onClick={() => getAllTransacoes()}>Atualizar</Button>
        </Col>
    </Row>

    <Table >
        <thead>
            <tr>
            <th>ID</th>
            <th>Compra ou venda</th>
            <th>Nome do produto</th>
            <th>Nome do distribuidor</th>
            <th>Valor</th>
            <th>Opções</th>
            </tr>
        </thead>
        <tbody>
                {transacoes.length ? transacoes.map((element) => 
                            <>
                            <tr>
                                <td>{element.id}</td>
                                {element.compraOuVenda ? 
                                    <td> venda </td>
                                   : <td> compra </td>
                                }
                                <td>{element.nomeDoProduto}</td>
                                <td>{element.nomeDoDistribuidor}</td>
                                <td>{element.valor}</td>
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
                        ) : <p> Não existe nenhum transação cadastrada. </p>}
        </tbody>
        {<Modal show={showModal} onHide={!showModal}>
            <Modal.Header>
                {showModalCadastro ?
                    <Modal.Title>Cadastrar transação</Modal.Title>
                :   <Modal.Title>Editar transação</Modal.Title>
                }
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Row>
                        <Col md={2}>
                            <p>Compra</p>
                        </Col>
                        <Col>
                            <Form.Check label="Venda "type="switch" id="compraOuVenda" value={compraOuVendaTransacao} onChange={() => {setCompraOuVendaTransacao(!compraOuVendaTransacao); console.log(compraOuVendaTransacao)}}/>
                        </Col>
                    </Row>
                    <Form.Group className="mb-3" controlId="nomeDoProduto">
                        <Form.Label>Nome do Produto</Form.Label>
                        <Form.Control type="text" placeholder="Nome do produto" value={nomeProdutoTransacao} onChange={(e) => setNomeProdutoTransacao(e.target.value)}/>
                    </Form.Group>
                    {!compraOuVendaTransacao ? 
                    <Form.Group className="mb-3" controlId="nomeDoDistribuidor">
                        <Form.Label>Nome do Distribuidor</Form.Label>
                        <Form.Control type="text" placeholder="Nome do distribuidor" value={nomeDistribuidorTransacao} onChange={(e) => setNomeDistribuidorTransacao(e.target.value)}/>
                    </Form.Group>
                    :
                    <Form.Group className="mb-3" controlId="nomeDoDistribuidor">
                        <Form.Label>Nome do Distribuidor</Form.Label>
                        <Form.Control type="text" placeholder="Nome do distribuidor" value={nomeDistribuidorTransacao} onChange={(e) => setNomeDistribuidorTransacao(e.target.value)} disabled/>
                    </Form.Group>}
                    <Form.Group className="mb-3" controlId="valor">
                        <Form.Label>Valor</Form.Label>
                        <Form.Control type="text" placeholder="Valor" value={valorTransacao} onChange={(e) => setValorTransacao(e.target.value)}/>
                    </Form.Group>
                    <Button variant="secondary" onClick={() => {setShowModalCadastro(false); setShowModal(false)}}>Cancelar</Button>
                    {showModalCadastro ?
                        <Button onClick={() => {cadastrarTransacao(); setShowModal(false); setShowModalCadastro(false); console.log(compraOuVendaTransacao)}} type="button" variant="primary" >Salvar transacao</Button>
                        :   <Button onClick={() => {editarTransacao(idAtual); setShowModal(false)}} type="button" variant="primary" >Salvar transacao</Button>
                    }    
                </Form>
            </Modal.Body>
        </Modal>
        }
    </Table>
    </>
    );
};
export default Transacao;