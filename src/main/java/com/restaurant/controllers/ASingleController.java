package com.restaurant.controllers;

import com.restaurant.repositorys.Repository;
import lombok.Getter;

@Getter
public abstract class ASingleController<T> {
    protected T productOrClient;
    protected static final Repository repository = Repository.getInstance();
    protected static final String[] dialogStrings = {".title", ".header", ".description"};

    public void setProductOrClient(final boolean init, final T productOrClient){
        this.productOrClient = productOrClient;
        if(init) this.initFields();
    }

    protected abstract void initialize();

    protected abstract void initFields();

    protected abstract void addDeleteButtonAction();

    protected abstract void initPersistButton(final boolean noProductOrClient);
}
