import React from 'react';
import { Routes, Route } from 'react-router-dom';
import Menu from '../components/Menu';
import Listagem from '../components/Listagem'


export default function AllRoute (){
    return(
    <>
        <Routes>
          <Route path='/' element={<Menu/>} />
          <Route path='/estoque' element={<Listagem/>}/>
        </Routes>
    </>
  )
};
