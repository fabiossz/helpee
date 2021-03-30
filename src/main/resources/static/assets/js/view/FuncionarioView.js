import * as FuncionarioService from '../service/FuncionarioService.js';
import * as FuncionarioController from '../controller/FuncionarioController.js';
import * as CorreioController from '../controller/CorreiosController.js';
import * as CorreiosError from '../model/CorreiosError.js';

const formCadastroFuncionario = document.querySelector(
    ".form--cadastro-funcionario"
);

let selectSetores = document.querySelector(".setores__show");
let containerDepartamentos = document.querySelector(".departamentos");

const mensagemErro = [];

let inputCepValid = [];

const formulario = {};

let opcoesSelectSetores = '';

const todosCamposObrigatorios = document.querySelectorAll('[data-required="required"]');


todosCamposObrigatorios.forEach(campo => {
    formulario[campo.id] = campo;
});

window.addEventListener('load', (event) => {
    formulario.logradouro.disabled = true;
    formulario.numeroEndereco.disabled = true;
    formulario.complemento.disabled = true;
    formulario.cidade.disabled = true;
    formulario.bairro.disabled = true;
    formulario.estado.disabled = true;

});

formulario.cep.addEventListener('input', async (event) => {

    try {

        const endereco = await CorreioController.validaCEP(event.target.value);

        formulario.logradouro.value = endereco.logradouro;
        formulario.cidade.value = endereco.localidade;
        formulario.bairro.value = endereco.bairro
        formulario.estado.value = endereco.uf;        

        inputCepValid = [formulario.logradouro, formulario.cidade, formulario.bairro, formulario.estado];

        inputCepValid.forEach(campo => {
            removeErrors(campo);
        });
        
        formulario.numeroEndereco.focus();
        

        
    } catch (erro) {
        formulario.cep.dataset.message = "CEP não encontrado";
        formulario.logradouro.value = '';
        formulario.numeroEndereco.value = '';
        formulario.complemento.value = '';
        formulario.cidade.value = '';
        formulario.bairro.value = '';
        formulario.estado.value = '';
        showErrors(event.target);
    } finally {
        formulario.logradouro.disabled = false;
        formulario.numeroEndereco.disabled = false;
        formulario.complemento.disabled = false;
        formulario.cidade.disabled = false;
        formulario.bairro.disabled = false;
        formulario.estado.disabled = false;
        formulario.cep.dataset.message = "CEP é obrigatório";
    }
});


formCadastroFuncionario.addEventListener("change", async (event) => {

    if (event.target.classList.contains("departamentos__selected")) {
        const idDepartamento = event.target.value;
        removeErrors(formulario.setor);
        FuncionarioService.findSetorByDepartamento(idDepartamento);
    }
});

export async function showSetorContent(setores) {

    try {
        opcoesSelectSetores = '';

        setores.forEach(setor => {
            opcoesSelectSetores += `
                 <option value="${setor.id}">${setor.descricao}</option>
            `;

            selectSetores.innerHTML = opcoesSelectSetores;
        });

        containerDepartamentos.classList.remove("has-error");

    } catch (error) {
        opcoesSelectSetores = '';

        opcoesSelectSetores += `
            <option value="">Selecione o Setor</option>
        `;

        selectSetores.innerHTML = opcoesSelectSetores;

        containerDepartamentos.classList.toggle("has-error");
    }

}



export function removeErrors(campo) {
    campo.parentElement.classList.remove("has-error");

    let idCampo = campo.id;
    let paragrafoErro = document.querySelector("#" + idCampo + " + p");

    if (paragrafoErro !== null) {
        paragrafoErro.remove();
    }

}

export function showErrors(campo) {

    if (mensagemErro.length == 0) {
        mensagemErro.push(campo.dataset.message);
    } else {

        const saoIguais = mensagemErro.some(element => element === campo.dataset.message);

        if (!saoIguais) {
            mensagemErro.push(campo.dataset.message);
        }
    }

    let index = mensagemErro.findIndex(element => element === campo.dataset.message);


    let idCampo = campo.id;
    let paragrafoErro = document.querySelector("#" + idCampo + " + p");


    if (campo.dataset.required === 'required' && paragrafoErro === null) {

        let mensagem = mensagemErro[index];
        let paragrafo = document.createElement('p');
        paragrafo.innerHTML = `${mensagem}`;
        campo.parentElement.classList.add('has-error');
        paragrafo.classList.add('control-label');
        campo.parentElement.append(paragrafo);
    }

}

formCadastroFuncionario.addEventListener('submit', async (event) => {

    event.preventDefault();

    let camposObrigatorios = Array.from(todosCamposObrigatorios).filter(campo => campo.dataset.required == 'required' && campo.value.trim() === '');

    let qtdeErros = FuncionarioController.validarCampos(camposObrigatorios);

    if (qtdeErros.length == 0) {
        formCadastroFuncionario.submit();
    }

});

formCadastroFuncionario.addEventListener('blur', function (event) {

    event.preventDefault();

    FuncionarioController.validarCampos(Array.from(todosCamposObrigatorios).filter(campo => campo.name === event.target.name));

}, true);

formCadastroFuncionario.addEventListener('click', (event) => {
    if (event.target.classList.contains("btn__limpar")) {
        formCadastroFuncionario.reset();
    }
});