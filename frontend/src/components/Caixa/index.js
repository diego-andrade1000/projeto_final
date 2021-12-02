import { useEffect, useState } from 'react';
import {Table, Row, Button, Col, Modal, Form} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';
import './index.css';


const Caixa = () => {
    const { enqueueSnackbar } = useSnackbar();
    const [caixas, setCaixas] = useState([]);
    const getAllCaixas = () => {
        api
            .get('/api/caixa')
            .then((res) => {
                setCaixas(res.data);
            })
            .catch((err) =>{
                enqueueSnackbar("Erro ao listar os caixas." || err.message, {
                variant: 'error',
                anchorOrigin: {
                    vertical: 'top',
                    horizontal: 'center',
                },
                preventDuplicate: true,
            })}
            )
    }
    useEffect(() => {
        getAllCaixas();
    }, [enqueueSnackbar])

    return (
    <>
    <Row className="linha">
        <Col md={8}>
            <div className="title">Controle de caixa</div>
        </Col>
        <Col md={2}>
            <Button variant="primary" onClick={() => getAllCaixas()}>Atualizar</Button>
        </Col>
    </Row>

    <Table >
        <thead>
            <tr>
            <th>ID</th>
            <th>Valor de entrada</th>
            <th>Valor de saida</th>
            <th>Total</th>
            </tr>
        </thead>
        <tbody>
                {caixas.length ? caixas.map((element) => 
                            <>
                            <tr>
                                <td>{element.id}</td>
                                <td>{element.valorEntrada}</td>
                                <td>{element.valorSaida}</td>
                                <td>{element.total}</td>
                            </tr>
                            </>
                        ) : <p> Não existe nenhum caixa cadastrado. </p>}
        </tbody>
    </Table>
    </>
    );
};
export default Caixa;