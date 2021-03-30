import CorreiosError from '../model/CorreiosError.js';
import * as CorreioService from '../service/CorreioService.js';



 export async function validaCEP(cep){

    if (isNaN(cep) || cep.length < 8) {
      
        throw new CorreiosError('CEP inválido',cep);
    }

    const dadosEndereco = await CorreioService.buscaCEP(cep);

    if (dadosEndereco.erro) {
        throw new CorreiosError('informações do CEP não foram encontradas!');
    }

    return dadosEndereco;
}
