export async function buscaCEP(cep){  
   
    const resposta = await fetch(`https://viacep.com.br/ws/${cep}/json/`);
    const cepRetornado = await resposta.json();
    
    return cepRetornado;
}