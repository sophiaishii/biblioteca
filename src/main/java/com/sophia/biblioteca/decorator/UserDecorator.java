package com.sophia.biblioteca.decorator;

public abstract class UserDecorator implements Role{
    protected Role role;

    public UserDecorator(Role role){
        this.role = role;
    }

    public String getRole(){
        return role.getRole();
    }
}
