import { useEffect, useState } from 'react';
import {Table, Row, Button, Col, Modal, Form} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';
import './index.css';


const Produtos = () => {
    const { enqueueSnackbar } = useSnackbar();
    const [products, setProducts] = useState([]);
    const [nomeProduto, setNomeProduto] = useState('');
    const [quantidadeProduto, setQuantidadeProduto] = useState(0);
    const [valorProduto, setValorProduto] = useState('');
    const [showModalCadastro, setShowModalCadastro] = useState(false);
    const [showModal, setShowModal] = useState(false);
    const [idAtual, setIdAtual] = useState(0);
    const validation = () => {
        return !!nomeProduto.trim() && valorProduto.trim() && quantidadeProduto > 0; 
    }
    const getAllProducts = () => {
        api
            .get('/api/estoque')
            .then((res) => {
                setProducts(res.data);
            })
            .catch((err) =>{
                console.log(products);
                enqueueSnackbar("Erro ao listar os produtos." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })}
            )
    }
    const cadastrarProduto = () => {
        var product = {
            nome: nomeProduto,
            quantidade: quantidadeProduto,
            valor: valorProduto
        }
        api.post('/api/estoque', product).then(()=>{
            enqueueSnackbar("Produto salvo com sucesso! Por favor, atualize a página.", {
                variant: 'success',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
        }).catch((err) =>{
                enqueueSnackbar("Erro ao salvar o produto." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            })
    }
    const editarProduto = (id) => {
        var product = {
            nome: nomeProduto,
            quantidade: quantidadeProduto,
            valor: valorProduto
        }
        api.put(`/api/estoque/${id}`, product).then(() =>{
                enqueueSnackbar("Produto editado com sucesso! Por favor, atualize a página.", {
                    variant: 'success',
                    anchorOrigin: {
                        vertical: 'top',
                        horizontal: 'center',
                    },
                    preventDuplicate: true,
            })
        }).catch((err) =>{
                enqueueSnackbar("Erro ao editar o produto." || err.message, {
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
        api.delete(`/api/estoque/${id}`).then(() =>{
                enqueueSnackbar("Produto deletado com sucesso! Por favor, atualize a página.", {
                    variant: 'success',
                    anchorOrigin: {
                        vertical: 'top',
                        horizontal: 'center',
                    },
                    preventDuplicate: true,
                })
            }).catch((err) =>{
                enqueueSnackbar("Erro ao editar o produto." || err.message, {
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
        getAllProducts();
    }, [enqueueSnackbar])

    return (
    <>
    <Row className="linha">
        <Col md={8}>
            <div className="title">Controle de estoque</div>
        </Col>
        <Col md={2}>
            <Button variant="success" onClick={() => {setShowModal(true); setShowModalCadastro(true)}}>+ Novo produto</Button>
        </Col>
        <Col md={2}>
            <Button variant="primary" onClick={() => getAllProducts()}>Atualizar</Button>
        </Col>
    </Row>

    <Table >
        <thead>
            <tr>
            <th>ID</th>
            <th>Nome do produto</th>
            <th>Quantidade</th>
            <th>Valor</th>
            <th>Opções</th>
            </tr>
        </thead>
        <tbody>
                {products.length ? products.map((element) => 
                            <>
                            <tr>
                                <td>{element.id}</td>
                                <td>{element.nome}</td>
                                <td>{element.quantidade}</td>
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
                        ) : <p> Não existe nenhum produto cadastrado. </p>}
        </tbody>
        {<Modal show={showModal} onHide={!showModal}>
            <Modal.Header>
                {showModalCadastro ?
                    <Modal.Title>Cadastrar produto</Modal.Title>
                :   <Modal.Title>Editar produto</Modal.Title>
                }
            </Modal.Header>
            <Modal.Body>
                <Form>
                    <Form.Group className="mb-3" controlId="nomeProduto">
                        <Form.Label>Nome do produto</Form.Label>
                        <Form.Control type="text" placeholder="Nome do produto" value={nomeProduto} onChange={(e) => setNomeProduto(e.target.value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="quantidade">
                        <Form.Label>Quantidade</Form.Label>
                        <Form.Control type="number" placeholder="Quantidade" min="0" value={quantidadeProduto} onChange={(e) => setQuantidadeProduto(e.target.value)}/>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="valor">
                        <Form.Label>Valor</Form.Label>
                        <Form.Control type="text" placeholder="Valor" value={valorProduto} onChange={(e) => setValorProduto(e.target.value)}/>
                    </Form.Group>
                    <Button variant="secondary" onClick={() => {setShowModalCadastro(false); setShowModal(false)}}>Cancelar</Button>
                    {showModalCadastro ?
                        <Button onClick={() => {cadastrarProduto(); setShowModal(false); setShowModalCadastro(false)}} type="button" variant="primary" disabled={!validation()}>Salvar produto</Button>
                        :   <Button onClick={() => {editarProduto(idAtual); setShowModal(false)}} type="button" variant="primary" disabled={!validation()}>Salvar produto</Button>
                    }    
                </Form>
            </Modal.Body>
        </Modal>
        }
    </Table>
    </>
    );
};
export default Produtos;