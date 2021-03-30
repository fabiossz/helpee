export default class CorreiosError extends Error{

    constructor(mensagem, campo = null) {
        super(mensagem);
        this.campo = campo;
    }
}