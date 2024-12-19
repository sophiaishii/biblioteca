import React, { useState } from 'react';
import '../styles/Aluno.css';
import api from '../util/Api';

function Aluno() {
  const [matricula, setMatricula] = useState(0);
  const [nome, setNome] = useState('');
  const [endereco, setEndereco] = useState('');
  const [cpf, setCpf] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();

    const aluno = {
      matricula: parseInt(matricula),
      nome: nome,
      cpf: cpf,
      endereco: endereco,
    };

    console.log(aluno);

    api.post('/aluno', {
      matricula: parseInt(matricula),
      nome: nome,
      cpf: cpf,
      endereco: endereco,
    })
      .then((data) => {
        console.log('Aluno cadastrado com sucesso:', data);
        // Limpar os campos do formulário após o cadastro
        setMatricula('');
        setNome('');
        setEndereco('');
        setCpf('');
      })
      .catch((error) => {
        console.error('Erro ao cadastrar aluno:', error);
      });
  };

  return (
    <div className="container">
      <h1>Biblioteca</h1>
      <h2>Cadastro de Aluno</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="matricula">Matrícula:</label>
        <input
          type="number"
          id="matricula"
          name="matricula"
          value={matricula}
          onChange={(e) => setMatricula(e.target.value)}
        />
        <br />
        <label htmlFor="nome">Nome:</label>
        <input
          type="text"
          id="nome"
          name="nome"
          value={nome}
          onChange={(e) => setNome(e.target.value)}
        />
        <br />
        <label htmlFor="endereco">Endereço:</label>
        <input
          type="text"
          id="endereco"
          name="endereco"
          value={endereco}
          onChange={(e) => setEndereco(e.target.value)}
        />
        <br />
        <label htmlFor="cpf">CPF:</label>
        <input
          type="text"
          id="cpf"
          name="cpf"
          value={cpf}
          onChange={(e) => setCpf(e.target.value)}
        />
        <br />
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
}

export default Aluno;

