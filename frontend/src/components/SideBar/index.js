import React from "react";
import { Col, Row } from "react-bootstrap";
import './index.css';
import { BsBoxSeam, BsPiggyBank} from "react-icons/bs";
import { Link } from "react-router-dom";
import {MdOutlineLocalGroceryStore} from 'react-icons/md';
import {RiMoneyDollarCircleFill} from 'react-icons/ri';


   export default function SideBar(){
        return (
        <div className="background">
            <div className="sidebar">
                <Row className="linha">
                    <Col md={2}>
                        <BsBoxSeam className="icon" size={25}/>
                    </Col>
                    <Col md={10}>
                        <Link className="link"to="/estoque">Controle de estoque</Link>
                    </Col>
                </Row>
                <Row className="linha">
                    <Col md={2}>
                        <BsPiggyBank className="icon" size={25}/>
                    </Col>
                    <Col md={10}>
                        <Link className="link"to="/caixa">Controle de caixa</Link>
                    </Col>
                </Row>
                <Row className="linha">
                    <Col md={2}>
                        <MdOutlineLocalGroceryStore className="icon" size={25}/>
                    </Col>
                    <Col md={10}>
                        <Link className="link"to="/distribuidor">Distribuidores</Link>
                    </Col>
                </Row>
                <Row className="linha">
                    <Col md={2}>
                        <RiMoneyDollarCircleFill className="icon" size={25}/>
                    </Col>
                    <Col md={10}>
                        <Link className="link"to="/transacao">Transação</Link>
                    </Col>
                </Row>
            </div>
        </div>
        );
    }

  