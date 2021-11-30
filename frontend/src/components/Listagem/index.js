import { useEffect, useState } from 'react';
import {Table, Row, Button, Col} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';
import './index.css';


export default function Listagem(){
    const { enqueueSnackbar } = useSnackbar();
    const [products, setProducts] = useState([]);
    const getAllProducts = () => {
        api
            .get('api/estoque')
            .then((res) => {
                setProducts(res.data);
            })
            .catch((err) =>
                enqueueSnackbar("Erro ao listar os produtos." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })
            )
    }
    useEffect(() => {
        getAllProducts();
    }, [])

    return (
    <>
    <Row className="linha">
        <Col md={8}>
            <div className="title">Controle de estoque</div>
        </Col>
        <Col md={2}>
            <Button variant="success">+ Novo produto</Button>
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
                {products.map((element) => 
                            <>
                            <tr>
                                <td>{element.id}</td>
                                <td>{element.nome}</td>
                                <td>{element.quantidade}</td>
                                <td>{element.valor}</td>
                                <td> 
                                    <Row className="linha2">
                                        <Col md={4}>
                                            <Button variant="warning">+ Editar</Button>
                                        </Col>
                                        <Col md={2}>
                                            <Button variant="danger">Excluir</Button>
                                        </Col>
                                    </Row>
                                </td>
                            </tr>
                            </>
                        )}
        </tbody>
    </Table>
    </>
    );
}