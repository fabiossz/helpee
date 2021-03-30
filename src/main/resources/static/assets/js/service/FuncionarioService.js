import * as FuncionarioView from '../view/FuncionarioView.js'

export async function findSetorByDepartamento(id_departamento) {

        const resposta = await fetch('/departamentos/setores', {
            method: 'POST',
            body: id_departamento
        });

        let listaDeSetores = await resposta.json();

        FuncionarioView.showSetorContent(listaDeSetores);

}

export async function cadastrarFuncionario() {

    const resposta = await fetch('/funcionarios/novo', {
        method: 'POST'
    });

    let obj = await resposta.json();    

}

