import React from "react";
import { Link } from "react-router-dom";
import "../styles/Home.css";

const Home = () => {
  return (
    <div className="home-container">
      <h1>Bem-vindo</h1>
      <p>Escolha uma das opções abaixo para continuar:</p>
      <div className="home-buttons">
        <Link to="/alunos">
          <button>Gerenciar Alunos</button>
        </Link>
        <Link to="/livros">
          <button>Gerenciar Livros</button>
        </Link>
      </div>
    </div>
  );
};

export default Home;
