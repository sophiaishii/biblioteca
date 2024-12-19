import React, { useState } from 'react';
import '../styles/Livro.css';
import api from '../util/Api';

function Livro() {
  const [tituloIsbn, setTituloIsbn] = useState('');
  const [disponivel, setDisponivel] = useState(false);
  const [exemplarBiblioteca, setExemplarBiblioteca] = useState(false);

  const handleSubmit = (e) => {
    e.preventDefault();

    const livro = {
      titulo: { isbn: tituloIsbn },
      disponivel: disponivel,
      exemplarBiblioteca: exemplarBiblioteca,
    };

    console.log(livro);

    api.post('/livro', livro)
      .then((data) => {
        console.log('Livro cadastrado com sucesso:', data);
        // Limpar os campos do formulário após o cadastro
        setTituloIsbn('');
        setDisponivel(false);
        setExemplarBiblioteca(false);
      })
      .catch((error) => {
        console.error('Erro ao cadastrar livro:', error);
      });
  };

  return (
    <div className="container">
      <h1>Biblioteca</h1>
      <h2>Cadastro de Livro</h2>
      <form onSubmit={handleSubmit}>
        <label htmlFor="tituloIsbn">Título/ISBN:</label>
        <input
          type="text"
          id="tituloIsbn"
          name="tituloIsbn"
          value={tituloIsbn}
          onChange={(e) => setTituloIsbn(e.target.value)}
        />
        <br />
        <label htmlFor="disponivel">Disponível:</label>
        <input
          type="checkbox"
          id="disponivel"
          name="disponivel"
          checked={disponivel}
          onChange={(e) => setDisponivel(e.target.checked)}
        />
        <br />
        <label htmlFor="exemplarBiblioteca">Exemplar da Biblioteca:</label>
        <input
          type="checkbox"
          id="exemplarBiblioteca"
          name="exemplarBiblioteca"
          checked={exemplarBiblioteca}
          onChange={(e) => setExemplarBiblioteca(e.target.checked)}
        />
        <br />
        <button type="submit">Cadastrar</button>
      </form>
    </div>
  );
}

export default Livro;
