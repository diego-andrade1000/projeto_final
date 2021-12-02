import {  useState } from 'react';
import { Button, Form, Col} from 'react-bootstrap';
import api from '../../services/api';
import { useSnackbar } from 'notistack';

const Login = () => {
    const [email, setEmail] = useState('');
    const [senha, setSenha] = useState('');
    const { enqueueSnackbar } = useSnackbar();
    const logar = () => {
        var loginVm = {
            email: email,
            password: senha
        }
        api
            .post('/api/authenticate', loginVm).catch((err) =>{
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
    return(
                <Form>
                    <Form.Group className="mb-3" controlId="email">
                        <Col md = {2}>
                            <Form.Label>Email</Form.Label>
                            <Form.Control  type="text" placeholder="email" value={email} onChange={(e) => setEmail(e.target.value)}/>
                        </Col>
                    </Form.Group>
                    <Form.Group className="mb-3" controlId="password">
                        <Col md = {2}>
                            <Form.Label>Senha</Form.Label>
                            <Form.Control type="text" placeholder="Senha"  onChange={(e) => setSenha(e.target.value)}/>
                        </Col>
                    </Form.Group>
                    <Button onClick={() => logar()} type="button" variant="primary" > Logar</Button>   
                </Form>
    )
}
export default Login;
