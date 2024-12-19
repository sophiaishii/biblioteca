package com.sophia.biblioteca.decorator;

public class User implements Role{
    
    @Override
    public String getRole() {
        return "Aluno";
    }
}
