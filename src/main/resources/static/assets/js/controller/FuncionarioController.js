import * as FuncionarioView from '../view/FuncionarioView.js';



export function validarCampos(camposObrigatorios) {

    const errors = [];    

    const validadorEmail = /^[a-zA-Z0-9_+-]+[a-zA-Z0-9._+-]*[a-zA-Z0-9_+-]+@[a-zA-Z0-9_+-]+[a-zA-Z0-9._+-]*[.]{1,1}[a-zA-Z]{2,}$/;

    for (let campo of camposObrigatorios) {

   

        if (campo.value.trim() === '' ||
            campo.type === 'number' && isNaN(campo.value) ||
            campo.type === 'email' && !validadorEmail.test(campo.value)) {

            errors.push(campo);
            FuncionarioView.showErrors(campo);            

        } else {            
            const index =  errors.findIndex(element => element === campo);
            let removed = errors.splice(index, 1);
            FuncionarioView.removeErrors(campo);            
        }

    }

    return errors;

}