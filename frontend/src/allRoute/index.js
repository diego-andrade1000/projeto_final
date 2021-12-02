import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Menu from '../components/Menu';
import Transacao from '../components/Transacao';
import Produtos from '../components/Produtos';
import Distribuidores from '../components/Distribuidores';
import Caixa from '../components/Caixa';


export default function AllRoute (){
    return(
    <>
        <Routes>
          <Route path='/' element={<Menu/>} />
          <Route path='/estoque' element={<Produtos/>}/>
          <Route path='/distribuidor' element={<Distribuidores/>}/>
          <Route path='/caixa' element={<Caixa/>}/>
          <Route path='/transacao' element={<Transacao/>}/>
        </Routes>
    </>
  )
};
