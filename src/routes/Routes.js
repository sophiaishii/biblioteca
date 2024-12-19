import React, { Fragment } from "react";
import { BrowserRouter, Route, Routes as RouterRoutes } from "react-router-dom";
import Alunos from "../pages/Aluno";
import Livros from "../pages/Livro";
import Home from "../pages/Home";

const Routes = () => {
    return (
      <BrowserRouter>
        <Fragment>
          <RouterRoutes>
              <Route path="/" element={<Home />} />
              <Route path="/alunos" element={<Alunos />} />
              <Route path="/livros" element={<Livros />} />
          </RouterRoutes>
        </Fragment>
      </BrowserRouter>
    );
  };
  
  export default Routes;